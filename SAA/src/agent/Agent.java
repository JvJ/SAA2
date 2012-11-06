package agent;

import personality.*;
import appraisal.*;
//import emotionalRules.*;
import worldPresentation.*;
import java.util.*;

import memory.*;
import goal.*;

public class Agent extends WorldObjects{
	//public String name;
	public MainMemory agentMemory=new MainMemory();
	
	public LinkedList <SimpleGoal> simpleGoalList =new LinkedList<SimpleGoal>();
	//public fact AgentMotiveState[];
	EmotionBias agentEmotionBias=new EmotionBias();

	
	public Apparaisal agentAppraisal=new Apparaisal(agentEmotionBias,this,simpleGoalList);
	//public LinkedList<Goal> activeGoal;//=new LinkedList();
	public void receiveEvent(Event event){
		this.agentAppraisal.recieveEventver2(event);
		
		this.agentMemory.receiveEvent(event,this.agentAppraisal.emoList);
	}
	public void TestAssigngoal(SimpleGoal sg){
		
		this.simpleGoalList.add(sg);
		this.agentAppraisal.simpleGoalList.add(sg);
	}


}

