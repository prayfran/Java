public class Evaluator {

	public LabeledData test;
	public Classifier pop;
	public Evaluator(Classifier classifier, LabeledData data)
	{
		this.test = data;
		this.pop = classifier;
	}
	public float getAccuracy()
	{
		int count = 0;
		for(int i = 0; i < this.test.size(); i++)
		{
			int jk = pop.classify(this.test, i);
			if(jk==this.test.getLabel(i))
				{
				count++;
				}
		}
		float accuracy = (float)count/test.size();
		return accuracy;
		
	}
}
