/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tejas
 */

public class whatsup extends JPanel{//A new class which implements graphics
    
    int visited[]=new int[6];
    int x1[]=new int[6];
    int y1[]=new int[6];
    String name[]=new String[5];
    int head;
     public whatsup(int a[],int x[],int y[],String names[],int v) {//Constructor to initialise the object whatsup
        setBackground(Color.WHITE);
        visited=a;
        x1=x;
        y1=y;
        name=names;
        head=v-1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);
        for(int i=0;i<5;i++)
        {
            if(head==i)//For the headquarter
            {
            g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.green);
            }
            
            g.fillOval(x1[i], y1[i],30, 30);//To create the city node graphics
            g.setColor(Color.MAGENTA);
            g.drawString(name[i], x1[i]+45, y1[i]+45);//To give the cities their appropriate names
        }
        
        for(int i=0;i<5;i++)
        {//To connect the cities according to the order formed by the algorithms implemented
            g.drawLine((x1[visited[i]]+15), (y1[visited[i]]+15), (x1[visited[i+1]]+15), (y1[visited[i+1]]+15));
        }
        
    }
}
