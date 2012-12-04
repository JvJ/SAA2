
package goapI;
//import interop.GoapGoal;

import java.util.*;

import planning.Planning;

public class blackBoard {
	public Agent agent;
	public GoapGoal agentGoal;
	public Planning plan;
	public KnowledgeBase kb;
	public ActionMaps agentActionMap=new ActionMaps();
	//ActionMaps agentActionmap();
	//	LinkedList<GoapWorldFact> currentState;
	public blackBoard(Agent a, GoapGoal ag){
		agentGoal=ag;
		agent=a;
		kb=new KnowledgeBase();
		plan=new Planning(kb);
	//	currentState=a.getAgentState();	
	}
	public static void main(String[] args){
		//for(int i=0;i<kb.)
		//kb.
		
	}
	
	
	
}
