package worldPresentation;
//import emotionalRules.*;
import agent.*;
import appraisal.EmotionalAffect;
import java.util.*;
public class Event extends WorldObjects {
	//WorldState eventWorldState;
	public Action eventAction=new Action(); 
	public EmotionalAffect eventEmotionalAffect=new EmotionalAffect();
	public LinkedList <fact> eventPostFact=new LinkedList<fact>();
	//public String eventType;
	public enum eventType {
	  internal,external,action
	  }
	public eventType thisEventType;
	
	//String value; // assume input
	//Fruit fruit = Fruit.valueOf(value);
	public Event(EmotionalAffect eea,Agent actor,Agent target,String s){
		this.eventEmotionalAffect=eea;
		this.eventAction.actionEmotionalAffect=eea;
		this.eventAction.actor=actor;
		this.eventAction.target=target;
		//this.thisEventType="action";
		//eventType thisEventType=eventType.valueOf("action");
		this.thisEventType=eventType.valueOf(s);
	}
	public Event(Agent actor,Agent target,String s){
		//this.eventEmotionalAffect=eea;
		//this.eventAction.actionEmotionalAffect=eea;
		this.eventAction.actor=actor;
		this.eventAction.target=target;
		//this.thisEventType="action";
		//eventType thisEventType=eventType.valueOf("action");
		this.thisEventType=eventType.valueOf(s);
	}
	public Event(){
		
	}
	public void TestAttachFact(fact fact1){
		this.eventPostFact.add(fact1);
	}
	
	
}
