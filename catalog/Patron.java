import java.util.ArrayList;


public class Patron {
public String PatronID = "";
public String PatronName = "";
public ArrayList<String> borrowed;
public int quota = 0;

    public Patron(String id, String name)
    {
	this.PatronID = id;
	this.PatronName = name;
	this.borrowed = new ArrayList<String>();
    }
    
    public String getID() {
	return PatronID;
     }
    
    public String getName() {
	return PatronName;
    }
    
	public int getQuota() {
		
		return quota;
	}

	public boolean belowQuota() {
		if(this.borrowed.size() < this.getQuota())
		{
			return true;
		}
		return false;
	}
	
	public boolean returnItem(String id)
	{
		for(int i = 0; i < this.borrowed.size(); i++)
		{
			if(this.borrowed.get(i) == id)
			{
				borrowed.remove(i);
				return true;
			}
			break;
		}
		return false;
	}
	
    public boolean borrow(String ID) {
		if(this.belowQuota() == true)
		{
			borrowed.add(ID);
			return true;
		}
		return false;
	}


}
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
class Youth extends Patron {

	public Youth(String id, String name) {
		super(id,name);
		this.quota = 2;
	}

}
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
class Adult extends Patron {

	public Adult(String id, String name) {
		super(id,name);
		this.quota = 3;
	}

}
