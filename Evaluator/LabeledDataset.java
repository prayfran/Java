import java.util.ArrayList;
import java.util.Scanner;


public class LabeledDataset implements LabeledData {
	public ArrayList <float[]> pointdata;
	public int arraycountrow = 0;
	public int arraycountcol = 0;
	
	public LabeledDataset(String Filename){
		Scanner scan = new Scanner(Filename).useDelimiter(",");
		
		String save = scan.nextLine();
		Scanner scan1 = new Scanner(save).useDelimiter(",");
		while (scan1.hasNext())
		{
			
		arraycountcol++;
		scan1.next();
			
		}
		pointdata = new ArrayList<float[]>();
		Scanner scan2 = new Scanner(Filename);
		while (scan2.hasNextLine())
		{
			String single = scan2.nextLine();
			Scanner scanline = new Scanner(single).useDelimiter(",");
			float[] savearray = new float[arraycountcol];
			for(int i =0; i < arraycountcol; i++)
			{
				savearray[i]=Float.parseFloat(scanline.next());
			}
		pointdata.add(savearray);
		}
		
	}

	public float[] getExample(int i) {
		float[] vector = new float[pointdata.get(i).length-1];
		for(int j = 1; j < pointdata.get(i).length-1; j++)
		{
		vector[j] = pointdata.get(i)[j+1];
		}
		return vector;
	}

	
	public int getLabel(int i) {
		int Label = (int)pointdata.get(i)[0];
		return Label;
	}
	
	public int size()
	{
		return pointdata.size();
	}
	public static void main(String args[])
	{
		LabeledDataset test = new LabeledDataset("test");
		System.out.println(">>"+test.getExample(1).toString());
		System.out.println(test.getLabel(1));
	}

}
