package interop;
import clojure.lang.*;
import agent.*;
import goal.*;

public class RDStart {
	public static void main(String[] args) {
		RDL rdl = new RDL();
		//rdl.loadFile("testRD.clj");
		RDAgent jack=new RDAgent();
		GoalTest money=new GoalTest();
		
		//goal test=new goal();

		Relation agent=rdl.defRel("agent", "SELF");
		
		Relation goalweight=rdl.defRel("goalweight" ,"GOAL" ,"AGENT" ,"WEIGHT");
		
		Relation goalsatistank=rdl.defRel("goalsatistank","GOAL" ,"AGENT" ,"VALUESATIS");
		
		Relation goalmain=rdl.defRel("goalmain","GOAL", "STATSMAIN" ,"DECAYRATE");
		
		Relation goaldecay=rdl.defRel("goaldecay", "AGENT", "GOAL", "DECAY");		
		
		Relation desire=rdl.defRel("desire", "GOAL" ,"AGENT" ,"VALUE");
		
		Relation agentgoal=rdl.defRel("agentgoal","GOAL", "AGENT" ,"STATE");
		
		IPersistentMap[] res = rdl.query(agent.term("SELF", "johnny"));
		
		rdl.get(res[0], "SELF");
		
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
