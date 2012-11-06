package appraisal;

///import emotionalRules.EmotionGeneration.EmotionalTraitVar;
import worldPresentation.*;
public class EmotionTraitHopeFear extends EmotionTraits{

	public EmotionTraitHopeFear(Event event,int o,int n,int a){
		//calculating the prode and guilt based on personality!
				//put a formula that use the agent personality trait upon how selfish they are!
		name="hopeFear";
		if( event.eventAction.actionEmotionalAffect.desireability>0 ) 
			value=1;
		if( event.eventAction.actionEmotionalAffect.desireability<0 ) 
			value=-1;
		//linkToCause=event;
	
	}
}
