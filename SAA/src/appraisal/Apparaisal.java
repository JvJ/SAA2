package appraisal;
//import memory.*;

import worldPresentation.*;
import agent.*;
import personality.*;
import goal.*;

import java.util.*;
public class Apparaisal {

	public LinkedList <SimpleGoal> simpleGoalList =new LinkedList<SimpleGoal>();
	
	public EmotionBias agentEmotionBias;
	public LinkedList <EmotionTraits>emoList=new LinkedList <EmotionTraits>();
	public Agent agent;
	//public MainMemory agentMemory;
	public Apparaisal(EmotionBias agentEmotionBias,Agent agent){
		this.agentEmotionBias=agentEmotionBias;
		this.agent=agent;
	}
	
	
	public Apparaisal(EmotionBias agentEmotionBias,Agent agent,LinkedList<SimpleGoal> sg){
		this.agentEmotionBias=agentEmotionBias;
		this.agent=agent;
		this.simpleGoalList=sg;
		
	}
	public LinkedList<EmotionTraits> eventEmoAffect(){
		return(this.emoList);
		
	}
	public void emoStackHandler(){
		
	}
	public boolean checkGoalPath(Event event){
		//if it make any significant chnages!!! to goal paths!
		return true;
	}
	public boolean checkGoalPathEvent(Event event,SimpleGoal goal){
		//if it make any significant chnages!!! to goal paths!
		return true;
	}
	public boolean plusGoalPath(Event event){
		//if it affect the goal path in a positive way!
		//check if it makes some more preconditions true or not
		return true;
	}
	public float howImportantGoal(SimpleGoal goal){
		float himgoal=1f;
		//take the intention for specific goal!
		return (himgoal);
	}
	public float howMuchAffect(Event event){
		float affect=1f;
		//how many preconditions it has made true!
		return (affect);
	}
	public float EventAffectGoal(Event event){
		
	//	return(howMuchAffect(event)*howImportantGoal(event));
		return howMuchAffect(event);
	}
	public float EventAffectgoalver2(Event event,SimpleGoal goal){
		
		//float goalImportance=howImportantGoal (goal);
		int wigth= goal.succConList.size();
		int num=0;
		
		for (int i=0;i<event.eventPostFact.size();i++)
		{	
			for (int j=0 ;j<goal.succConList.size();j++)
			{
				if (event.eventPostFact.get(i).name==goal.succConList.get(j).name)
				{
					if (event.eventPostFact.get(i).var==goal.succConList.get(j).var)
						num++;
					else
						num--;				
				}
			}
			
		}
		if (num!=0)
			return ((num/wigth)*howImportantGoal (goal));
		else 
			return(0f);
	  
	}
	public void appraisEventGoal(Event event,SimpleGoal simpleG){//it has already affected the goal path!
		
		float affect=EventAffectgoalver2(event,simpleG);
	
		sadHappy(event, affect);
		
		if (event.eventAction.actor==this.agent)
				
			prideGuilt(event, affect);//(event, affect);
		else
			gratitude(event, affect);

	}
	public void appraisEventGoalver2(Event event,SimpleGoal simpleG){//it has already affected the goal path!
		
		float affect=EventAffectgoalver2(event,simpleG);

		sadHappy(event, affect);
		
		if( (event.eventAction.actor.getClass()==this.agent.getClass())&(event.eventAction.actor==this.agent))
			
			prideGuilt(event, affect);//(event, affect);
		else
			if ( (event.eventAction.target.getClass()==this.agent.getClass())&(event.eventAction.target==this.agent))
				gratitude(event, affect);

}
	public SimpleGoal getActiveGoal(){
		
		return (simpleGoalList.get(0));
	}
	public void recieveEventver2(Event event){
		SimpleGoal activeGoal=getActiveGoal();
		switch (event.thisEventType){
			case internal:
				
				break;
			case external:
				if (checkGoalPath(event))
					appraisEventGoal(event,activeGoal);
				break;
			
				
			default:
				break;
				}
		 	//internal:
		 		
		
		//check to see if its internal or external!
		//if its (external)
			//if related to active goal
		//		{//if actor me ->distress/joy based on initial hope and fear
				//if actor not me
						
		
				
	}
	

	public void updatAgentMotiveState(){}
	public void UpdatingActiveGoal(){}
	public void updateActiveRole(){
		
	}
	public void overRideDesireability(){
		
		
	}
	public void updateEmostack()
	{
		
		///cry and stuff+reaction action!+updating mood 
	}
	//public void processRecieveEmo(){}
//	public void updateEmo(){
		
//	}
	
	//public boolean EventReleventGoal(){
		
//	}
	
	public void gratitude(Event event,float affect)
	{
		EmotionalTraitGratitudeAnger empg=new EmotionalTraitGratitudeAnger(event,affect);
		//Math.abs(empg.value);//abs();
		if ( Math.abs(empg.value) > Math.abs(empg.treshold) )
		{
			
			emoList.add(empg);
		}
	}
	public void sadHappy(Event event,float affect){
		
		EmotionTraitSadHappy emsp=new EmotionTraitSadHappy(event,affect);
		if ( Math.abs(emsp.value) > Math.abs(emsp.treshold) )
		{	
			emoList.add(emsp);
		}
		
	}
	public void prideGuilt(Event event,float affect){
		
		EmotionTraitPrideGuilt emsp=new EmotionTraitPrideGuilt(event, affect);
		if ( Math.abs(emsp.value) > Math.abs(emsp.treshold) )
		{	
			emoList.add(emsp);
		}
		
	}
	public void IntegratingEmotionalState(){
		///find a way to integrate emotional state!
	
}
	public LinkedList<EmotionTraits> returnEmoAffect(){
		return(emoList);
	}


}
