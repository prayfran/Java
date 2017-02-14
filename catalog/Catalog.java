import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalog
{
    public ArrayList<Item> ItemsinLibrary2;
    public ArrayList<String> ItemsinLibrary1;
    
	public Catalog(String string) {
		
		String Type = "";
		String ID = "";
		String Title = "";
		String copiesava = "";
		String author = "";
		String Pages = "";
		String Playtime = "";
		ItemsinLibrary1 = new ArrayList<String>();
		ItemsinLibrary2 = new ArrayList<Item>();
		File Catalog = new File(string);
		Scanner scan;
		try {
			scan = new Scanner(Catalog);
			while(scan.hasNextLine())
			{
				ItemsinLibrary1.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < ItemsinLibrary1.size(); i++)
		{
			Scanner scan1 = new Scanner(this.ItemsinLibrary1.get(i)).useDelimiter(";");
			while(scan1.hasNext())
			{
			String temptype = scan1.next();
			
			Scanner scan2 = new Scanner(temptype).useDelimiter("=");
			
			String Key = scan2.next().trim();
			String Value = scan2.next().trim();
			
			if(Key.equals("itemType"))
			{
				Type = Value.toLowerCase();
			}
			if(Key.equals("itemID"))
			{
				ID = Value;
			}
			if(Key.equals("title"))
			{
				Title = Value;
			}
			if(Key.equals("copiesAvailable"))
			{
				copiesava = Value;
			}
			
			if(Key.equals("author"))
				
			{    author = Value;
				
			}
			if(Key.equals("pages"))
			{
				Pages = Value;
			}
			if(Key.equals("playingTime"))
			{
				Playtime = Value;
			} 
			}
			if(Type.equals("book"))
			{
			System.out.println(ID + "|" +  Title + "|" + copiesava + "|" + author + "|" + Pages );
			Book book = new Book(ID, Title, Integer.parseInt(copiesava), author, Integer.parseInt(Pages));
				
			ItemsinLibrary2.add(book);	
			}
			if(Type.equals("dvd"))
			{
				System.out.println(Type + "|" + ID + "|" +  Title + "|" + copiesava +"|" + Playtime);
				DVD dvd = new DVD(ID, Title, Integer.parseInt(copiesava), Integer.parseInt(Playtime));
				ItemsinLibrary2.add(dvd);
				
			}
			
		}
		
	}
	public boolean borrow(Patron patron, String itemID)
	{
		boolean borrow = false;
		try{
			if(!(patron instanceof Youth) && !(patron instanceof Adult) )
			{
				throw new Exception();
			}
			
			
		}catch(Exception e){
			System.out.println("Man that Patron isn't human");
		}
		try{
			boolean list = false;
			for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
			{
				if(this.ItemsinLibrary2.get(i).ItemID.equals(itemID))
				{
					list = true;
				}
			}
			if(list = false)
			{
				throw new Exception();
			}
			
		}catch(Exception e){
			System.out.println("Man that not available at this library");
		}
		if(patron.borrow(itemID)==true)
		{
			
			for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
			{
				
				if(this.ItemsinLibrary2.get(i).NumberofAvailable > 0)
				{
					
						if(this.ItemsinLibrary2.get(i).ItemID.equals(itemID))
							{
				
							this.ItemsinLibrary2.get(i).NumberofAvailable--;
							
				
							}
						borrow = true;
			
				}
			else 
				{
				borrow = false;}
			}
		}
		return borrow;
		
	}
	public boolean returnItem(Patron patron, String itemID)
	{
		try{
			if(!(patron instanceof Youth) && !(patron instanceof Adult))
			{
				throw new Exception();
			}
			
		}catch(Exception e){
			System.out.println("Man that Patron isn't human");
		}
		try{
			boolean list = false;
			boolean borrow = false;
			for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
			{
				if(this.ItemsinLibrary2.get(i).equals(itemID))
				{
					list = true;
				}
			}
			for(int i =0 ;i < patron.borrowed.size(); i++)
			{
				if(patron.borrowed.get(i).equals(itemID) )
				{
					borrow = true;
				}
			}
			if(list = false)
			{
				throw new Exception();
			}
			if(borrow = false)
			{
				throw new Exception();
			}
			
		}catch(Exception e){
			System.out.println("Man that not available at this library");
		}
		if(patron.returnItem(itemID)==true)
		{
			for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
			{
			if(this.ItemsinLibrary2.get(i).ItemID.equals(itemID))
			{
				this.ItemsinLibrary2.get(i).NumberofAvailable++;
				
			}
			}
			return true;
		}
		return false;
	}
	public boolean available(String itemID)
	{
		for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
		{
			if(this.ItemsinLibrary2.get(i).ItemID.equals(itemID))
			{
				if(this.ItemsinLibrary2.get(i).NumberofAvailable > 0)
				{
					return true;
				}
			}
		}
		return false;
	}
 
	public ArrayList<String> search(String title)
	{
		ArrayList<String> Names = new ArrayList<String>();
		
		for(int i = 0; i < this.ItemsinLibrary2.size(); i++)
		{
			if(this.ItemsinLibrary2.get(i).ItemTitle.equals(title))
			{
				Names.add(this.ItemsinLibrary2.get(i).ItemID);
			}
		}
		return Names;
	}
	public static void main(String[] args) throws Exception
	{	
		Catalog cl1 = new Catalog("catalog.txt");

		Patron youth1 = new Youth("01","adam");
		Patron adult1 = new Adult("02","walter");	
		Patron youth2 = new Youth("03","eve");
		Patron adult2 = new Adult("04","eli");
		  
		System.out.println(adult1.getQuota());
		System.out.println(youth2.getQuota());

		System.out.println(adult1.belowQuota());
		System.out.println(youth2.getID());		

		

		System.out.println(cl1.borrow(youth2,"004"));	
		System.out.println(cl1.borrow(youth2,"004"));		
		System.out.println(cl1.borrow(youth2,"004")); //youth2 has insufficient quota now
		System.out.println(">>"+ cl1.borrow(adult1,"004")); // All copies of itemID 004 have been borrowed

		System.out.println(cl1.available("004"));		//No more copies available
		System.out.println(cl1.available("003"));

		try
		{
			System.out.println(cl1.available("009"));
		}
		catch(Exception e10){	System.out.println(e10.getMessage());}

		try
		{
			System.out.println(cl1.borrow(youth2,"019"));
		}
		catch(Exception e11){	System.out.println(e11.getMessage());}

		try
		{
			System.out.println(cl1.returnItem(youth2,"019"));
		}
		catch(Exception e12){	System.out.println(e12.getMessage());}
	
		System.out.println(cl1.returnItem(youth2,"004"));
		System.out.println(cl1.returnItem(youth2,"004"));

		try
		{
			System.out.println(cl1.returnItem(youth2,"004"));
		}
		catch(Exception e13){	System.out.println(e13.getMessage());}

		Catalog c = new Catalog( "catalog.txt" );		
		Youth y = new Youth( "youth", "1" );
		Adult a = new Adult( "adult", "2" );
		String workingItem = "001"; // has 3 in the .txt
		String workingTitle = "The Hobbit: An Unexpected Journey"; // a book and dvd
		String badTitle = "bla"; // not a title
		
		
		
		System.out.println( ">> available items" );
		for ( int i = 0; i < c.ItemsinLibrary2.size(); i++ )
		{
			System.out.print( "Item: " + c.ItemsinLibrary2.get(i).ItemID + ", Copies: " + c.ItemsinLibrary2.get(i).NumberofAvailable );
			System.out.println();
		}
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> youth borrow" );
		c.borrow( y, workingItem );
		c.borrow( y, workingItem );
		c.borrow( y, workingItem );
		c.borrow( y, workingItem );
		for ( int i = 0; i < y.borrowed.size(); i++ )
		{
			System.out.print( "Item: " + y.borrowed.get(i) + ", Total inventory: (belowQuota: " + y.belowQuota() + ": " + y.borrowed.size() + ")" );
			System.out.println();
		}
		System.out.println( ">> new available items" );
		for ( int i = 0; i < c.ItemsinLibrary2.size(); i++ )
		{
			System.out.print( "Item: " + c.ItemsinLibrary2.get(i).ItemID + ", Copies: " + c.ItemsinLibrary2.get(i).NumberofAvailable );
			System.out.println();
		}
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> adult borrow" );
		c.borrow( a, workingItem );
		c.borrow( a, workingItem );
		c.borrow( a, workingItem );
		c.borrow( a, workingItem );
		for ( int i = 0; i < a.borrowed.size(); i++ )
		{
			System.out.print( "Item: " + a.borrowed.get(i) + ", Total inventory: (belowQuota: " + a.belowQuota() + ": " + a.borrowed.size() + ")" );
			System.out.println();
		}
		
		System.out.println( ">> new available items" );
		for ( int i = 0; i < c.ItemsinLibrary2.size(); i++ )
		{
			System.out.print( "Item: " + c.ItemsinLibrary2.get(i).ItemID + ", Copies: " + c.ItemsinLibrary2.get(i).NumberofAvailable );
			System.out.println();
		}
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> youth return" );
		c.returnItem( y, workingItem );
		c.returnItem( y, workingItem );
		c.returnItem( y, workingItem );
		c.returnItem( y, workingItem );
		for ( int i = 0; i < y.borrowed.size(); i++ )
		{
			System.out.print( "Item: " + y.borrowed.get(i) + ", Total inventory: (belowQuota: " + y.belowQuota() + ": " + y.borrowed.size() + ")" );
			System.out.println();
		}
		
		System.out.println( ">> new available items" );
		for ( int i = 0; i < c.ItemsinLibrary2.size(); i++ )
		{
			System.out.print( "Item: " + c.ItemsinLibrary2.get(i).ItemID + ", Copies: " + c.ItemsinLibrary2.get(i).NumberofAvailable );
			System.out.println();
		}
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> adult return" );
		c.returnItem( a, workingItem );
		c.returnItem( a, workingItem );
		c.returnItem( a, workingItem );
		c.returnItem( a, workingItem );
		for ( int i = 0; i < a.borrowed.size(); i++ )
		{
			System.out.print( "Item: " + a.borrowed.get(i) + ", Total inventory: (belowQuota: " + a.belowQuota() + ": " + a.borrowed.size() + ")" );
			System.out.println();
		}
		
		System.out.println( ">> new available items" );
		for ( int i = 0; i < c.ItemsinLibrary2.size(); i++ )
		{
			System.out.print( "Item: " + c.ItemsinLibrary2.get(i).ItemID + ", Copies: " + c.ItemsinLibrary2.get(i).NumberofAvailable );
			System.out.println();
		}
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> item available" );
		System.out.println( c.available(workingItem) );
		
		/*********************************************************/
		
		System.out.println( "\n ********** \n" );
		System.out.println( ">> search" );
		ArrayList<String> ids = c.search(badTitle);
		for ( int i = 0; i < ids.size(); i++ )
		{
			System.out.print( "\"" + badTitle + "\" ids: " + ids.get(i) );
			System.out.println();
		}
	}



		

}



