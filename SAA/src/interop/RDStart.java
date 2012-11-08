package interop;
import clojure.lang.*;
import agent.*;
import goal.*;

public class RDStart {
	public static void main(String[] args) {
		RDL rdl = new RDL();
		rdl.loadFile("testRD.clj");
		RDAgent jack=new RDAgent();
		GoalTest money=new GoalTest();
		jack.agentGoal=money;
		
		//goal test=new goal();

		Relation agent=rdl.defRel("agent", "SELF");
		
		Relation goalweight=rdl.defRel("goalweight" ,"GOAL" ,"AGENT" ,"WEIGHT");
		
		Relation goalsatistank=rdl.defRel("goalsatistank","GOAL" ,"AGENT" ,"VALUESATIS");
		
		Relation goalmain=rdl.defRel("goalmain","GOAL", "STATSMAIN" ,"DECAYRATE");
		//goalmain[:GOAL :STATSMAIN :DECAYRATE]
			
		
		
		
		Relation agentgoal=rdl.defRel("agentgoal","GOAL", "AGENT" ,"STATE");
		Relation goaldecay=rdl.defRel("goaldecay","AGENT","GOAL","DECAY");
		//goaldecay[:AGENT :GOAL :DECAY]
		Relation desire=rdl.defRel("desire", "GOAL" ,"AGENT" ,"VALUE");
		IPersistentMap[] results;
		results = rdl.query(
				agent.assertRel("SELF","jacky"),
				goalweight.assertRel( "GOAL","money","AGENT","jacky","WEIGHT",3),
				goalsatistank.assertRel("GOAL","money" ,"AGENT","jacky","VALUESATIS",40),
				goalmain.assertRel("GOAL","money", "STATSMAIN",10.8 ,"DECAYRATE",10.9),
		//		goaldecay.assertRel("AGENT","jacky", "GOAL","money", "DECAY",3),
				goaldecay.assertRel("AGENT","jacky","GOAL","money","DECAY",10),
				desire.assertRel("GOAL","money" ,"AGENT","jacky" ,"VALUE",0),
				agentgoal.assertRel("GOAL","money","AGENT","jacky","STATE",20)
				
								);
		results = rdl.query(
				agent.term("SELF", ":A"),
				goalweight.term("GOAL",":G","AGENT",":A","WEIGHT",":W"),
				goalsatistank.term("GOAL",":G","AGENT" ,":A","VALUESATIS",":VS"),
		
				goalmain.term("GOAL",":G", "STATSMAIN",":SM" ,"DECAYRATE",":D"),
				agentgoal.term("GOAL",":G","AGENT",":A","STATE",":S"),
				goaldecay.term("AGENT",":A","GOAL",":G","DECAY",":DS"),
				desire.term("GOAL",":G" ,"AGENT",":A" ,"VALUE",":V")//,
			
				
				);
		for (IPersistentMap m : results){
			
			System.out.println(m);
			//jack.name=(String)m.valAt(rdl.var("A"));
		    jack.agentGoal.name= ((Symbol)m.valAt(rdl.var("G"))).getName();
			jack.agentGoal.desire=(Long)m.valAt(rdl.var("V"));
			//=( long)m.valAt(rdl.var("A"));
		//	m.valAt("", notFound)
		}
		

		rdl.updateHead();
		rdl.updateTail();
		results = rdl.query(
				desire.term("GOAL",":G" ,"AGENT",":A" ,"VALUE",":V")
				);
		results = rdl.query(
				agent.term("SELF", ":A"),
				goalweight.term("GOAL",":G","AGENT",":A","WEIGHT",":W"),
				goalsatistank.term("GOAL",":G","AGENT" ,":A","VALUESATIS",":VS"),
		
				goalmain.term("GOAL",":G", "STATSMAIN",":SM" ,"DECAYRATE",":D"),
				agentgoal.term("GOAL",":G","AGENT",":A","STATE",":S"),
				goaldecay.term("AGENT",":A","GOAL",":G","DECAY",":DS"),
				desire.term("GOAL",":G" ,"AGENT",":A" ,"VALUE",":V")//,
			
				
				);
		for (IPersistentMap m : results){
			
			System.out.println(m);
			//jack.name=(String)m.valAt(rdl.var("A"));
		    jack.agentGoal.name= ((Symbol)m.valAt(rdl.var("G"))).getName();
			jack.agentGoal.desire=(Long)m.valAt(rdl.var("V"));
			//=( long)m.valAt(rdl.var("A"));
		//	m.valAt("", notFound)
		}
		System.out.println("*******************");
		System.out.println(jack.agentGoal.desire);
		System.out.println(jack.agentGoal.name);
		
		/*
		Relation agent=rdl.defRel("agent",)
		Relation goalweight=rdl.defRel("goalweight" ,"AGENT" ,"GOAL" ,"WEIGHT");
		
		Relation 	goalsatistank=rdl.defRel("goalsatistank","AGENT" ,"GOAL" ,"VALUESATIS");
		
		Relation goalmain=rdl.defRel("goalmain","GOAL", "SATISMAIN" ,"DECAYRATE");
		
		Relation goaldecay=rdl.defRel("goaldecay", "AGENT", "GOAL", "DECAY");		
		
		Relation desire=rdl.defRel("desire", "GOAL" ,"AGENT" ,"VALUE");
		Relation agentgoal=rdl.defRel("agentgoal","GOAL", "AGENT" ,"STATE");
		Relation agent = rdl.defRel("agent", "SELF");
	*/
	
	}
}
