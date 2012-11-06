package appraisal;

public class EmotioneRange {
	
	public float value;
	public int least=10;
	public int most=-10;
	public EmotioneRange(int l,int m){
		least=l;
		most=m;
	}
	public EmotioneRange(){
		
	}
	public void checkRange(){
		if (value<least)
			value=least;
		if (value>most)
			value=most;
	}

}
