package appraisal;
//import emotionalRules.EmotionGeneration.EmotionalTraitVar;
import worldPresentation.*;

//import personality.*;
//import java.io.*;

public class EmotionTraitPrideGuilt extends EmotionTraits{
	
	public EmotionTraitPrideGuilt(Event event,int o,int n,int a){
	
		name="praideguilt";
		//calculating the prode and guilt based on personality!
		//based on initial fear and how much he wants this action or goal!
		//put a formula that use the agent personality trait upon how selfish they are!
		if (event.eventAction.actionEmotionalAffect.desireabilityForother> 0.0)
			value=1;
		else
			value=-1;
		//puting target here!
	//	linkToCause=event;
		
	}
	public EmotionTraitPrideGuilt(Event event,float affect,int o, int n, int a){
		//System.out.println("********************");
		name="praideguilt";
		//calculating the prode and guilt based on personality!
		//based on initial fear and how much he wants this action or goal!
		//put a formula that use the agent personality trait upon how selfish they are!
		//puting target here!
		//linkToCause=event;
		value=affect;
		
	}
	public EmotionTraitPrideGuilt(Event event, float affect){
		
		name="PrideGuilt";
		value=affect;
	}
	
}
