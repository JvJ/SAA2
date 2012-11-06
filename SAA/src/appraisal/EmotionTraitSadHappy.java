package appraisal;
import worldPresentation.*;
public class EmotionTraitSadHappy extends EmotionTraits{
	public EmotionTraitSadHappy(Event event,int o,int n,int a){
	name="sadhappy";
	//weight for fear prevoisly and weight for how mnuch he wants this action
	if( event.eventAction.actionEmotionalAffect.desireability>0 ) 
		value=1;
		//value=value;
	if( event.eventAction.actionEmotionalAffect.desireability<0 ) 
		value=-1;
	//linkToCause=event;
	}
	public EmotionTraitSadHappy(Event event,float affect){
		name="sadhappy";
		value=affect;
	}
	public EmotionTraitSadHappy(Event event,float affect,int o ,int n,int a){
		name="sadhappy";
		value=affect;
	}
	

}
