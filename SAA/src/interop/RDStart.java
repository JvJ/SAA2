package interop;
import RDTest.*;
import RDTest.GoalTest;
import clojure.lang.*;
import agent.*;
import goal.*;

public class RDStart {
	public static void main(String[] args) {
		/*
		 * private String name;
	private String agentName;
	private double satisTank;
	private double goalDecay;
	private double desire;
	private double weight;
	private double state;
	
		 */
		RDL rdl = new RDL();
		rdl.loadFile("testRD.clj");
		IPersistentMap[] results;
		Relation emoRel = rdl.defRel(Emotion.class);
		Relation goalemo=rdl.defRel(GoalTest.class);
		results=rdl.query(
				goalemo.assertRel("name","money","agent","nanaaa",
						"satistank",10,"goaldecay",20,"desire",10,"weight",80,"state",0)
			    //emoRel.assertRel(10,20,10,"asshole")
				);
		results=rdl.query(
				goalemo.term("name",":N","agent",":A","satistank",":S","goaldecay",":D","desire",":X","weight",":W","state",":L")
					//	"sadness",":S","happiness",":H","anger",":L","agent",":X")
				//	goalemo.term("name",":N","agent",":A")
				);
		for (IPersistentMap m : results){
			
			System.out.println(m);
		}
		/*
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
				desire.term("GOAL",":G" ,"AGENT",":A" ,"VALUE",":V")		
				);
		
		IPersistentMap[] res = rdl.query(agent.term("SELF", "johnny"));
		
		// EXAMPLE IS HERE!!!
		Relation emoRel = rdl.defRel(Emotion.class);
		
		System.out.println("Emotion relation: "+ emoRel);
		System.out.println(goalweight);
		
		for (IPersistentMap m : results){
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
			System.out.println(m);
			//jack.name=(String)m.valAt(rdl.var("A"));
		    //jack.agentGoal.name= ((Symbol)m.valAt(rdl.var("G"))).getName();
		//    jack.agentGoal.name=(String)rdl.get(m, "G");
			jack.agentGoal.desire=(long)rdl.get(m, "V");
			//=( long)m.valAt(rdl.var("A"));
		//	m.valAt("", notFound)
		//	jack.agentGoal.desire=(long)rdl.get(m, "V");
			System.out.println(rdl.get(m, "G"));
			
		}
		results=rdl.query(
				emoRel.assertRel("sadness",10,"happiness",20,"anger",10,"agent","asshole")
			    //emoRel.assertRel(10,20,10,"asshole")
				);
		results=rdl.query(
				emoRel.term("sadness",":S","happiness",":H","anger",":L","agent",":X")
				);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		for (IPersistentMap m : results){
			
			System.out.println(m);
		}
		System.out.println("*******************");
		System.out.println(jack.agentGoal.desire);
		//System.out.println(jack.agentGoal.name);
		
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
	/*	Relation goalweight=rdl.defRel("goalweight" ,"GOAL" ,"AGENT" ,"WEIGHT");
		
		Relation smack=rdl.defRel("smack","INST", "TARG");
	//	defrel 'smack [:INST :TARG])
		Relation anger=rdl.defRel("anger","SELF" ,"VAL");
		Relation agent=rdl.defRel("agent", "SELF");
		//(defrel 'anger [:SELF :VAL])
		results = rdl.query(
				agent.assertRel("SELF", "jack"),
				agent.assertRel("SELF","niiiiiii"),
				anger.assertRel("SELF" ,"jack" ,"VAL",100),
				anger.assertRel("SELF" ,"niiiiiii" ,"VAL",100),
				
				smack.term("INST","sally","TARG","jack"),
				smack.term("INST","sally","TARG","niiiiiii")
				);	
		results = rdl.query(
				anger.term("SELF" ,":X" ,"VAL",":Y")
				);
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		(update-head-on-agent 'johnny)
		(update-tail-on-agent 'johnny)
	*/
	}
	
}
