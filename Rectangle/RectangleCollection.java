import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;









public class RectangleCollection {

	private ArrayList<Rectangle> rectList;
	
	public RectangleCollection(String fileName)
	{
		{
			rectList = new ArrayList<Rectangle>();
			File rectFile = new File(fileName);
			Scanner scan;
			
			try {
				scan = new Scanner(rectFile);
				while(scan.hasNextLine()){

					String l = scan.nextLine();
					String[] k = l.split("\\s*,\\s*");

					String upper_x1 = k[0];
					String upper_y1 = k[1];
					String lower_x1 = k[2];
					String lower_y1 = k[3];

					int upper_x = Integer.parseInt(upper_x1);
					int upper_y = Integer.parseInt(upper_y1);
					int lower_x = Integer.parseInt(lower_x1);
					int lower_y = Integer.parseInt(lower_y1);

					Rectangle r1 = new Rectangle(upper_x, upper_y, lower_x, lower_y);

					rectList.add(r1);
					

				}
				scan.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}



	public RectangleCollection()
	{
		rectList = new ArrayList<Rectangle>();
		
	}


	public boolean add(Rectangle rectangle)
	{

		if(rectangle == null || this.contains(rectangle))
		{
			return false;
		}
		else
		
		rectList.add(rectangle);
		return true;


	}


	public Rectangle get(int i) throws IndexOutOfBoundsException 
	{
		
		return this.rectList.get(i);
		
		
	}
	

	public boolean addAll(RectangleCollection other)
	{
		boolean rect = true;
		if(other == null)
		{
			rect = false;
		}
		for(int i = 0 ; i <  other.size(); i++)
		{
		
				this.add(other.get(i));
		
			
			
		}
		
		return rect;

	}
	

	public boolean contains(Rectangle rectangle)
	{
		boolean rect = false;
		
		for(int i = 0 ; i <  this.size(); i++)
		{
			if(rectangle.equals(this.rectList.get(i)))
			{
				rect = true;
				
			}
			
		}
		return rect;

	}
	

	public RectangleCollection intersect(RectangleCollection other)
	{

		RectangleCollection r1= new RectangleCollection();
		
		for(int i = 0 ; i <  other.size(); i++)
		{
			if(this.rectList.contains(other.get(i)))
			{
				r1.add(other.get(i));
				
			}
			
		}
		
		return r1;

	}
	
	public int size()
	{
			
		return rectList.size();
	}

	public boolean equals(Object object)
	{
		
		return this.equals(object);

	}
	public String toString()
	{
		String s = "";
		for ( int i = 0 ; i < this.size(); i++)
		{
			s += this.rectList.get(i);
		}
		System.out.println("" + s);
		return s;
		
	}
	public static void main(String[] args)  {

		Rectangle rectangle1 = new Rectangle(80, 30, 120, 64);
		Rectangle rectangle2 = new Rectangle(100, 200, 300, 450);
		RectangleCollection rc = new RectangleCollection();
		rc.add(rectangle1);
		rc.add(rectangle2);
		RectangleCollection rc2 = new RectangleCollection("coordinates.txt");
		RectangleCollection rc21 = new RectangleCollection("coords2");
		RectangleCollection rc3 = rc2.intersect(rc21); 
		System.out.println(rc3.toString());
		new showRecList(rc3);



	}
}
/*
 * 
 * The code below this comment is for visual display of a Collection of Rectangles stored in an ArrayList.
 * You are not supposed to make any changes or add any code below this comment.
 *	 
 */
class showRecList extends JFrame {
	   public showRecList(RectangleCollection rectList){
//		   super("Display Arrays");
//		   Rectangle[] r1 = rt.rArr;
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      displayRecs recs = new displayRecs();
		      getContentPane().add(recs);
//		      rectList
		      for (int i=0; i<rectList.size();i++ ) {
		    	  int w = rectList.get(i).getLowerX()-rectList.get(i).getUpperX();
	              int h = rectList.get(i).getLowerY()-rectList.get(i).getUpperY();
		         recs.addArrays(rectList.get(i).getUpperX(),rectList.get(i).getUpperY(), w, h);
		      }

		      pack();
		      setLocationRelativeTo(null);
		      setVisible(true);
		   
	   }

	}

class displayRecs extends JPanel {
private static final int frame_width = 500;
private static final int frame_height = frame_width;
private List<java.awt.Rectangle> rects = new ArrayList<java.awt.Rectangle>();

public void addArrays(int x, int y, int width, int height) {
   java.awt.Rectangle rect = new java.awt.Rectangle(x, y, width, height);
   rects.add(rect);
}

@Override
public Dimension getPreferredSize() {
   return new Dimension(frame_width, frame_height);
}

@Override
protected void paintComponent(Graphics g) {
   super.paintComponent(g);
   Graphics2D g2 = (Graphics2D) g;
   for (java.awt.Rectangle rect : rects) {
 	 Random rand = new Random();
 	 int R = rand.nextInt(255);
 	 int G = rand.nextInt(255);
 	 int B = rand.nextInt(255);

 	 Color color = new Color(R,G,B);
 	 g.setColor(color);
 	 g.drawString("("+rect.x+","+rect.y+")", rect.x-45, rect.y);
 	 int m = rect.x + rect.width;
 	 int l = rect.y + rect.height;
 	 int idx = rects.indexOf(rect);
 	 g.drawString("("+m+","+l+")", m+1, l+1);
 	 int locx = rect.x + rect.width/2;
 	 int locy = rect.y + rect.height/2;
 	 g.drawString((String.valueOf(idx)), locx, locy);
 	 g2.draw(rect);
   }
}

}