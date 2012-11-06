package memory;
import java.util.*;

import worldPresentation.*;
import java.util.*;
import appraisal.*;
public class MainMemory {
	theoryOfMind agentTheoryOfMind=new theoryOfMind();
	//LinkedList <Event> events;
	//LinkedList <EmotionalTraitReaction> eventAffect=new LinkedList <EmotionalTraitReaction>();
	public MainMemory(){
		//events=new LinkedList<Event>();
	}
	public LinkedList<MemoryCell> listMemoryCell=new LinkedList<MemoryCell>();
	//pastEvents agentPastEvents;
	 //get an event from the appraisal part!
	public void checkEvent(Event event){
	
		this.agentTheoryOfMind.callingCheckEvent(event);
		
	}
	public void receiveEvent(Event event,LinkedList<EmotionTraits>eventAffect){
	//	checkEvent(event);
		MemoryCell mc=new MemoryCell(event,eventAffect);
		listMemoryCell.add(mc);
		checkEvent(event);
	}

}
