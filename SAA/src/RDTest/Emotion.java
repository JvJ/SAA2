package RDTest;

public class Emotion {

	private String agent;
	private double anger;
	private double sadness;
	private double happiness;
	
	public Emotion(){
		
	}
	
	public void setAgent(String s){
		agent = s;
	}
	
	public String getAgent(){
		return agent;
	}
	
	public double getAnger(){
		return anger;
	}
	
	public void setAnger(double d){
		anger = d;
	}
	
	public double getHappiness(){
		return happiness;
	}
	
	public void setHappiness(double d){
		happiness = d;
	}
	
	public double getSadness(){
		return sadness;
	}
	
	public void setSadness(double d){
		sadness = d;
	}
	
}
