package appraisal;

import java.util.*;
import worldPresentation.*;

//import test.EnumTest.Size;


public class EmotionalAffect {
	/*public enum  EmotionalAffectVar{
	    desireability,desireabilityForother,praiseworthiness,liklehood,expectedness
	}
	public EnumMap<EmotionalAffectVar, Float> emotionVar = new EnumMap<EmotionalAffectVar, Float>(EmotionalAffectVar.class);
	
	public EmotionalAffect(){
		
		this.emotionVar.put(EmotionalAffectVar.desireability, 1f);
		this.emotionVar.put(EmotionalAffectVar.desireabilityForother, 1f);
	}
	
	*/
	

	public float desireability;
	public float desireabilityForother;
	public float praiseworthiness;
	public float liklehood;
	public EmotionalAffect(){
		
		this.desireability=1;
		this.desireabilityForother=1;//.emotionVar.put(EmotionalAffectVar.desireabilityForother, 1f);
	}
	
	
}
