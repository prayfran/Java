
public class Item {

	protected String ItemID = "";
	protected String ItemTitle = "";
	protected int NumberofAvailable = 0;
	
	public String getID() 
	{
		return this.ItemID;
	}
	public String getTitle() 
	{
		return this.ItemTitle;
	}
	public int getCopiesAvailable()
	{
		return this.NumberofAvailable;
	}
	public boolean equals(Object other){
		if(other instanceof Item)
		{
		if(this.ItemID.equals(((Item)other).getID()))
		{
			return true;
		}
		}
		return false;
		
		
	}
	public  Item(String id, String title, int copiesAvailable)
	{
		this.ItemID = id;
		this.ItemTitle = title;
		this.NumberofAvailable = copiesAvailable;
	}

}
/////////////////////////////////////////
/////////////////////////////////////////
class DVD extends Item{

	protected int playtime = 0;
	
	public DVD(String id, String title, int copiesAvailable, int playtime) {
		super(id, title, copiesAvailable);
		
	}
	public int getPlayingTime()
	{
		return this.playtime;
	}

}
///////////////////////////////////////////
///////////////////////////////////////////
class Book extends Item{

protected String Author = "";
protected int numpages = 0;

public Book(String id, String title, int copiesAvailable, String Author, int numpages) {
	super(id, title, copiesAvailable);
	
	
}
public String getAuthor() 
{
	return this.Author;
}
public int getPages()
{
	return this.numpages;
}
}
