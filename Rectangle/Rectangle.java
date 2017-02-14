	import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

	import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;





	public class Rectangle {
		 private int upper_x;
		 private int upper_y;
		 private int lower_x;
		 private int lower_y;
		
		
		public Rectangle(int upper_x, int upper_y, int lower_x, int lower_y) 
		{
			boolean rectangle;
			
				try
				{
					if(upper_x < lower_x && upper_y < lower_y){
						rectangle = true;
					if(rectangle == false)
					{
						throw new Exception("IllegalArguementException");
					}
					}
				} catch (Exception e) {
					System.out.println("man thats not a rectangle");
				}
				
			
			if (lower_x > 500)
			{
				lower_x = 500;
			}
			if (lower_y > 500)
			{
				lower_y = 500;
			}
			if (upper_x < 0)
			{
				upper_x = 0;
			}
			if (upper_y < 0)
			{
				upper_y = 0;
			}
			this.upper_x = upper_x;
			this.lower_x = lower_x;
			this.upper_y = upper_y;
			this.lower_y = lower_y;
		}
		
		
		
		
		public int getUpperX() {
			return this.upper_x;
			}
		public int getLowerX() {
			return this.lower_x;
			}
		public int getUpperY() {
			return this.upper_y;
			}
		public int getLowerY() {
			return this.lower_y;
			}
		
		public boolean equals(Rectangle object)
		{
			boolean rect = false;
			if (this.upper_x == object.upper_x && this.lower_x == object.lower_x && this.upper_y == object.upper_y && this.lower_y == object.lower_y)
			{
				rect = true;
			}
			return rect;

		}
		
		
		public Rectangle(Rectangle other)
		{
			this.lower_x = other.lower_x;
			this.lower_y = other.lower_y;
			this.upper_x = other.upper_x;
			this.upper_y = other.upper_y;
		}
		
		
		
		public String toString()
		{
			
			return "Rectangle with the coordinates of : Upper X = " + upper_x + ", Upper Y = " + upper_y + ", Lower X = " + lower_x + ", Lower Y = " + lower_y ;
			
		}

		
		
		

	


		public boolean overlap(Rectangle other)
		{
			
			boolean overlap = false;
			
			if (this.upper_y < other.lower_y && other.upper_y < this.lower_y &&
				this.upper_x < other.lower_x && other.upper_x < this.lower_x)
			{
				overlap = true;
				
			}
			
			
			
			return overlap;
			}
		
		
		public boolean containedIn(Rectangle other)
		{
			boolean contained = false;
			if(this.upper_x > other.upper_x && this.upper_y > other.upper_y&& 
				this.lower_x < other.lower_x&& this.lower_y < other.lower_y)
			{
				contained = true;
			}
			
			
			return contained;
			
		}
		
		
		public boolean drag(int x,int y)
		{
			boolean rect = false;
			int l = this.upper_x - this.lower_x;
			int w = this.upper_y - this.lower_y;
			
			
			this.upper_x = x + l/2;
			this.upper_y = y + w/2;
			this.lower_x = x - l/2;
			this.lower_y = y - w/2;
			
			if(this.upper_x < this.lower_x && this.upper_y < this.lower_y && this.upper_x > 0 && this.upper_y > 0 && this.lower_x < 500 && this.lower_y < 500 ){
				rect = true;
				
			}
	        
			return rect;
			
		}
		
		
		public boolean resize(int x,int y)
		{
			boolean rect = false;
			this.lower_x = x;
			this.lower_y = y;
				
			if(this.upper_x < this.lower_x && this.upper_y < this.lower_y && this.upper_x > 0 && this.upper_y > 0 && this.lower_x < 500 && this.lower_y < 500 ){
					rect = true;
			}
		
		
			return rect;
			
		}

		public static void main(String[] args)  throws IOException {
			
		
			Rectangle rect1 = new Rectangle(150, 250, 290, 400);
			Rectangle rect2 = new Rectangle(100, 200, 300, 450);
			System.out.println("rectangle1.overlaps(rectangle2): " + rect1.overlap(rect2));
			System.out.println("rectangle1.containedIn(rectangle2): " + rect1.containedIn(rect2)); 
			Rectangle [] rects = new Rectangle[2];
			rects[0] = rect1;
			rects[1] = rect2;
			System.out.println(""+ rect1.toString());
	
			
			

		}

	}
