package tsp;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tsp {      //Class with the main method
Scanner sc = new Scanner(System.in);
    int n,v;
    int x[] = new int[5];
    int y[] = new int[5];
    int dist[][] = new int[5][5];
    String names[] = new String[5];
    int visited[] = new int[5];
    //Global Variables for storage purposes
    int path;
    int city[]=new int [6];
    int arr[] = new int[6];
    int min = 9999;
    int ans[] = new int[6];

    void calculate()//Method to claculate the permutation with the minimum cost
    {
        
        int i;
        int cost=0;
        arr[5] = 0;
        for(i=0; i<=3 ; i++)
        {
            cost+=dist[arr[i]][arr[i+1]];
        }
        cost+=dist[arr[i]][arr[5]];
        if(min > cost)
        {
            min = cost ;
            for(i=0 ; i<5 ; i++) 
            {
               ans[i] = arr[i];
            }
        }
    }
    
    void swap(int p , int q)//Utility Method to swap two integers to create the permutations
    {
        int temp;
        temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }
    
void permute(int l , int r)//Recursive function to calculate all the permutations
{
    
    int i;
    if (l == r) {
        if(arr[0]==0) {
            for(int k =0 ; k<5 ; k++){
            }
            calculate();
        }
    }
    else
    {
        for (i = l; i <= r; i++)
        {
            swap(l, i);
            permute( l+1, r);
            swap(l, i); //backtrack
        }
    }

}

void recurssion(){//Method to start the brute force way   
        for(int i=0 ; i<5 ; i++){
            arr[i] = i;
        }

        permute(0,4);
    }
    
  void shortest(int w)//Function to calculate the city nearest to the current city
  {//For the greedy algorithm
      int i;
      //For finding the minimum distance to the next node that is not visited
      int min=100000;int no=100000;
      for(i=0;i<5;i++)
      {
          if((dist[w][i]<min) && (dist[w][i]!=0) && (visited[i]==0))
          {
              min=dist[w][i];
              no=i;
          }
      }

      //If the minimum distance remains unchanged, then this means that all the adjacent cities have been visited
      if(min==100000)
      {
          System.out.println("DONE!!");
      }
      else
      {
          path=path+min;//Increment the path length
          visited[no]=1;//Update visited
          int ctr=0;
          //For finding out if there are any more cities that are still left to be taken into consideration
          for(i=0;i<5;i++)
          {
              if(visited[i]==0)
              {
                  ctr++;
              }
          }

          if(ctr!=0)//That is , if there are more cities left to be considered
          {
              //System.out.print(names[no] + "   ");
              printing(no);
              //printf("%d,",g[no]->ele);
              shortest(no);
          }
          else//That is , if there are no more cities left to be considered
          {
            path=path+dist[no][v-1];
            printing(no);
            //System.out.print(names[no] + "   ");
          }
      }

  }

    void getip(){//Function which gets the input from the user to create the adjacency matrix
        
        System.out.println("The salesman has to travel in 5 cities ");
        System.out.println("Get ready to give the input of the names of the city");
        for(int i = 0 ; i<5 ; i++)
        {
            if(i==0) {
                System.out.println("Enter the name of the city with the headquarters");//The headquarter is given first
                names[i] = sc.nextLine();
            }
            else{
                System.out.println("Enter the name of city number :" +(i+1));
                names[i] = sc.nextLine();
            }
        }
            //To get the location of the above entered cities
        for(int i=0 ; i<5 ; i++){
            System.out.println("Enter the x-cordinate and y-cordinate of " + names[i]);
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        //To print the distance between the inputted cities
        for(int i=0 ; i<5 ; i++){
            for(int j=0 ; j<5 ; j++){
                if(i!=j) {
                    System.out.println("The distance between " + names[i] + " and " + names[j] + " is");
                    double si1=Math.pow(Math.abs(x[i]-x[j]),2);
                    double si2=Math.pow(Math.abs(y[i]-y[j]),2);
                    dist[i][j]=(int)Math.round(Math.sqrt(si1+si2));
                    System.out.println(dist[i][j]);
                }
                else
                {
                    dist[i][j]=0;
                }
            }
        }
        //To print the adjacency matrix
        System.out.println("This is the adjacency matrix formed");
        for(int i=0 ; i<5 ; i++){
            for(int j=0 ; j<5 ; j++){
                System.out.print(dist[i][j] + "    ");
            }
            System.out.println();
        }
    }
    
    //Function to create an array which stores the order of cities formed by the greedy algorithm
    void printing(int a)
    {
        for(int i=0;i<=5;i++)
        {
            if(city[i]==-1)
            {
                city[i]=a;
                break;
            }
        }
    }
    
    public static void main(String[] args) {
    
        Tsp obj = new Tsp();//Creating an object tsp
        //Equating to zero
	for(int i=0;i<5;i++)
		{
                obj.city[i]=-1;
		obj.visited[i]=0;
		obj.x[i]=0;
		obj.y[i]=0;
		for(int j=0;j<5;j++)
			{
			obj.dist[i][j]=0;
		}
	}
	obj.path=0;
        
        //Calling the getip function to create the adjacency matrix
	obj.getip();
	
        
        //GREEDY ALGORITHM
        obj.v = 1;
        System.out.println("The salesman is greedy. He uses the GREEDY algorithm");
        obj.city[0]=obj.v-1;
	obj.visited[obj.v-1]=1;
	obj.shortest(obj.v-1);
	obj.city[5]=obj.v-1;
        System.out.println("The shortest distance is :" + obj.path);
        for(int i=0;i<5;i++)
        {
            System.out.print(obj.names[obj.city[i]]+"    ");
        }
        System.out.println(obj.names[(obj.v)-1]);
        
        
        //BRUTE FORCE ALGORITHM
        System.out.println("\n\nAnother salesman is a hard worker. He uses the BRUTE FORCE method");
        obj.recurssion();
        System.out.println("The minimum cost is :" + obj.min);
        System.out.println("The shortest route is :");
        for(int k =0 ; k <= 5 ; k++){
            System.out.print(obj.names[obj.ans[k]] + "    ");
        }
        
        //Graphics of the greedy algorithm
        JFrame jFrame = new JFrame();//Creating a jframe
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
        //Creating an object of the class whatsup
        jFrame.add(new whatsup(obj.city,obj.x,obj.y,obj.names,obj.v));
        jFrame.setVisible(true);
        
        
        //Graphics of the Brute force algorithm
        JFrame jFrame2 = new JFrame();//Creating another jframe
        jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame2.setSize(800, 800);
        //Creating an object of the class whatsup
        jFrame2.add(new whatsup(obj.ans,obj.x,obj.y,obj.names,obj.v));
        jFrame2.setVisible(true);
    
    }
}