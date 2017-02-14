import java.util.Arrays;


public class KNN implements Classifier{
	
	int amount;
	public LabeledData n ;
	
	public KNN(int k)
	{
		amount = k;
	}
	
	public void train(LabeledData trainingData) {
		
	 this.n = trainingData;
		
	}

	
	public int classify(LabeledData testingData, int i) {
		LabeledData test = testingData;
		
		float[] keep = test.getExample(i);
		float distance = 0;
		
		float[][] helddata = new float[n.size()][2];
		for(int k =0; k < n.size(); k++)
		{
			float[] temp = n.getExample(k);
			for(int m = 0; m < keep.length; m++)
			{
				distance += Math.sqrt(keep[m]-temp[m]);
				
			}
			helddata[k][0] = n.getLabel(k);
			helddata[k][1] = distance;
		}
		for(int a =0; a< helddata.length;a++)
		{
			for(int b = 0; b < helddata.length-a; b++)
			{
				if(helddata[b-1][1]>helddata[b][1])
				{
					float temp1= helddata[b][0];
					float temp2 = helddata[b][1];
					helddata[b][0] = helddata[b-1][0];
					helddata[b][1] = helddata[b-1][1];
					helddata[b-1][0] = temp1;
					helddata[b-1][1] = temp1;
					
				}
			}
		}
		int label = 0;
		int maximum = 0;
		for(int h = 0; h < this.amount; h++)
		{
		  int er = 0;
		  int ert = (int)helddata[h][0];
			for(int z = 0; z< this.amount; z++)
			{
				if(helddata[z][0] == ert)
				{
					er++;
				}
				if(er-1>maximum)
				{
					maximum = er-1;
					label = ert;
				}
			}
		}
		
		
		return label;
	}

}
