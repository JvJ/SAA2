package personality;

public class EmotionBias {
	int openness=1;//+alert,perceptive
	//-:narrow ignorant
	int consc=1;//+:persistent,orderly, predistable,dependable,promp
	//-:messy,careless,predictable,dependable,promp
	int extra=1;//social,active,assertive,dominaennt,energic
	//- distant,unsocial,lathargic,vigoreless,shy
	int neuro=1;//over sensetive,fearful,dependent,
	//calm confident,independent
	int agree=1;
	
	public EmotionBias(){
	}
	public int getOpennes(){
		return openness;
	}
	public int getConsc(){
		return consc;
	}
	public int getExtra(){
		return extra;
	}
	public int getAgree(){
		return agree;
	}
	public int getNeuro(){
		return neuro;
	}
}
