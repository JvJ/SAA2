package interop;

import clojure.lang.*;

public class RDLTesting {

	
	public static void reflectionTests(){
		
	}
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
	/*	class goal{
			
			public double desire;
		}*/
		// First, generate an RDL instance
		RDL rdl = new RDL();
		Relation emotion = rdl.defRel(Emotion.class);
		rdl.loadFile("testRD.clj");
		IPersistentMap[] results;
		/*
		//goal test=new goal();
		//Relation goalweight=rdl.defRel("goalweight" ,"AGENT" ,"GOAL" ,"WEIGHT");
		
		//Relation agent = rdl.defRel("agent", "SELF");
		
		// MAHOOR!  Look at this!
		
		
		
		Emotion mrEmo = new Emotion();
		mrEmo.setAgent("kaylen");
		mrEmo.setAnger(1.0);
		mrEmo.setSadness(10.0);
		mrEmo.setHappiness(1.0);
				results = rdl.query(
				emotion.assertObject(mrEmo)
				);
		
		results = rdl.query(
					rdl.bindVar(":X", emotion.autoTerm())
					);
		
		// When it prints out, you can see a fully formed java object!!
		for (IPersistentMap m : results){
			
			System.out.println("Here's the results! "+m);
			
			//m.
			//test.desire=( long)m.valAt(rdl.var("C"));
		//	mrEmo = (Emotion)rdl.get(m, ":X");	
			//mrEmo = (Emotion)rdl.get(m, ":X");
		}
		rdl.updateHead();
		rdl.updateTail();
	//	results = rdl.query(
		//		emotion.modRel(mrEmo)
		//		);
		
		results = rdl.query(
				rdl.bindVar(":X", emotion.autoTerm())//,
				//System.out.println(mrEmo.getHappiness())
			
				);
		
		
		System.out.println(mrEmo.getHappiness());
	// When it prints out, you can see a fully formed java object!!
	for (IPersistentMap m : results){
		
		System.out.println("Here's the results! "+m);
	//	mrEmo = (Emotion)rdl.get(m, ":X");
		//m.
		//test.desire=( long)m.valAt(rdl.var("C"));
		
	}
	System.out.println(mrEmo);
		//System.out.println(emotion.)
		
		/*Relation 	goalsatistank=rdl.defRel("goalsatistank","AGENT" ,"GOAL" ,"VALUESATIS");
		
		Relation goalmain=rdl.defRel("goalmain","GOAL", "SATISMAIN" ,"DECAYRATE");
		
		Relation goaldecay=rdl.defRel("goaldecay", "AGENT", "GOAL", "DECAY");		
		
		Relation desire=rdl.defRel("desire", "GOAL" ,"AGENT" ,"VALUE");
		Relation agentgoal=rdl.defRel("agentgoal","GOAL", "AGENT" ,"STATE");
		
		
		results = rdl.query(
			agent.assertRel("SELF","jacky"),
			agentgoal.assertRel("GOAL","money","AGENT","jacky","STATE",20),
			goalsatistank.assertRel("AGENT","jacky","GOAL","money","VALUESATIS",40),
			goalweight.assertRel("AGENT","jacky","GOAL","money","WEIGHT",3),
			desire.assertRel("GOAL","money","AGENT","jacky","VALUE",10)
				// Use assertRel to create a new instance of a relation
				// The number of arguments MUST be even. And each pair should
				// be of the form : FIELD, value.
				//mother.assertRel("SELF", "sue", "CHILD", "kaylen"),
				//mother.assertRel("SELF", "marion", "CHILD", "sue")
			//goalweight.assertRel(args)
				
				);
		results = rdl.query(
				agent.term("SELF", ":X"),
				agentgoal.term("GOAL",":G","AGENT",":X","STATE",":S"),
				goalsatistank.term("AGENT",":X","GOAL",":G","VALUESATIS",":V"),
				goalweight.term("AGENT",":X","GOAL",":G","WEIGHT",":W"),
				desire.term("GOAL",":G","AGENT",":X","VALUE",":C")
				
				);
		
		System.out.println("Results from !!!!!!!!!!!!!!!!!!!!!!!: ");
		for (IPersistentMap m : results){
			
			System.out.println(m);
		}
		
			rdl.updateHead();
		rdl.updateTail();
		results = rdl.query(
		        desire.term("GOAL",":G","AGENT",":X","VALUE",":C")
				
				);

		
		System.out.println("Results from??????????????????: ");
		for (IPersistentMap m : results){
			
			System.out.println(m);
			test.desire=( long)m.valAt(rdl.var("C"));
		}
		System.out.println("Results from************************ ");
		System.out.println(test.desire);
		System.out.println("Keyword test: "+ rdl.trans(":X", ":Y", "+", 0.1));
		*/
		//PersistentVector v = new PersistentVector
		
		// Define a relation with appropriate fields
		
		/*
		Relation mother = rdl.defRel("mother", "SELF", "CHILD");
		
		Relation grandmother = rdl.defRel("grandmother", "SELF", "GCHILD");
		
		// The query results are an array of persistent maps.
		//IPersistentMap[] results;
		
		
		results = rdl.query(
				// Use assertRel to create a new instance of a relation
				// The number of arguments MUST be even. And each pair should
				// be of the form : FIELD, value.
				mother.assertRel("SELF", "sue", "CHILD", "kaylen"),
				mother.assertRel("SELF", "marion", "CHILD", "sue")
			);
		
		System.out.println("Results from first query: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		// Now we want to execute an actual query:
		results = rdl.query(
				// The strings you give the query will be interpreted in Clojure
				// to mean different things.
				
				// A normal string is interpreted as a Prolog atom
				// A keyword, which begins with :, will be interpreted as
				// a prolog variable.  Make sure variables start with upper-case.
				// Any other object (i.e. integer, float) will be interpreted
				// appropriately.
				mother.term("SELF", ":X", "CHILD", ":Y")
				);
		
		System.out.println("Results from second query: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		// This time around, we can use unification.
		// See how :Y is used twice?  That means :Y will be sue,
		// since it's the only object that satisfies both relationships
		results = rdl.query(
				mother.term("SELF", ":X", "CHILD", ":Y"),
				mother.term("SELF", ":Y", "CHILD", ":Z")
				);
		
		System.out.println("Results from third query: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		// Now we will define a rule
		Rule r = rdl.defRule(
				// The name of the rule
				"granny",
				// The precondition - same as the previous query
				mother.term("SELF", ":X", "CHILD", ":Y"),
				mother.term("SELF", ":Y", "CHILD", ":Z"),
				":==>",// This is a special identifier to separate preconditions from post-conditions
				// Postcondition is to assert a grandmother relationship
				grandmother.assertRel("SELF", ":X", "GCHILD", ":Z")
				);
		
		// Query the grandmother relationship before and after
		results = rdl.query(
				grandmother.term("SELF", ":X", "GCHILD", ":Y")
				);
		
		System.out.println("Before the rule has executed: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		// Now, we execute the 2-pass update cycle
		rdl.updateHead();
		rdl.updateTail();
		
		results = rdl.query(
				grandmother.term("SELF", ":X", "GCHILD", ":Y")
				);
		
		System.out.println("After the rule has executed: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		System.out.println("\nTransformers!\nThey help you modify variables!");

		// Let's define an emotion relationship
		Relation emotion = rdl.defRel("emotion", "SELF", "ANGER", "HAPPINESS", "SADNESS");
		
		// And a smacking relationship
		Relation smack = rdl.defRel("smack", "INST", "TARG");
		
		//Relation agent = rdl.defRel("agent", "SELF");
		
		// Let's assert 2 facts
		rdl.query(
				agent.assertRel("SELF", "johnny"),
				// First, we establish that Johnny has some feelings
				emotion.assertRel("SELF", "johnny", "ANGER", 0.0, "HAPPINESS", 1.0, "SADNESS", 0.0),
				// Next, we establish that ace hits johnny!
				smack.assertRel("INST", "ace", "TARG", "johnny")
				);
		
		// Check to make sure we have the right facts
		results = rdl.query(
				emotion.term("SELF", "johnny", "ANGER", ":V", "HAPPINESS", ":H", "SADNESS", ":S")
				);
		
		System.out.println("johnny's anger value before: ");
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		
		IFn trans = new AFn(){
			public Object invoke(Object v1, Object v2){
				
				return (Double)v1 + (Double)v2;
			}
		};
			
		
		Rule getSmacked = rdl.defRule(
				"getSmacked",
				smack.term("INST", ":I", "TARG", ":X"),
				// WARNING: MAKE SURE ALL FIELDS ARE USED!
				// THIS IS A BUG THAT MUST BE FIXED!
				emotion.term("SELF", ":X", "ANGER", ":V", "HAPPINESS", ":H", "SADNESS", ":S"),
				":==>",
				// We've introduced a transformer, which goes from :V to :V2
				// The action taken is to add 0.1
				rdl.trans(":V", ":V2", trans, 0.1),
				//rdl.trans(":H", ":H", "(fn [x & r] (println \"HEY!\") x)"),
				":==>",
				// The effect of the rule is to modify the relation
				emotion.modRel("SELF", ":X", ":==>", "ANGER", ":V2", "HAPPINESS", ":H", "SADNESS", ":S"),
				smack.retractRel("INST", ":I", "TARG", ":X")
				);
		
		
		
		Rule smackBack = rdl.defRule(
				"smackBack",
				smack.term("INST", ":I", "TARG", ":X"),
				agent.term("SELF", ":X"),
				":==>",
				smack.assertRel("INST", ":X", "TARG", ":I")
				//smack.retractRel("INST", ":I", "TARG", ":T")
				);
		
		
				
		
		
		rdl.updateHead();
		rdl.updateTail();
		
		// Check to make sure we have the right facts
		results = rdl.query(
				smack.term("INST", ":X", "TARG", ":Y"),
				//agent.term("SELF", ":X"),
				emotion.term("SELF", "johnny", "ANGER", ":V", "HAPPINESS", ":H", "SADNESS", ":S")
				);
			
		System.out.println("johnny's anger value after: ");
		for (IPersistentMap m : results){
			
			
			
			System.out.println(m);
			//m.
		}
		*/
		Relation mother = rdl.defRel("mother", "SELF", "CHILD");
		Relation danger=rdl.defRel("danger","SELF");
		Relation protect =rdl.defRel("protect","SELF","TARGET");
		results = rdl.query(
				// Use assertRel to create a new instance of a relation
				// The number of arguments MUST be even. And each pair should
				// be of the form : FIELD, value.
				mother.assertRel("SELF", "sue", "CHILD", "kaylen"),
				//mother.assertRel("SELF", "marion", "CHILD", "sue"),
				danger.assertRel("SELF","kaylen")
				);
		
		results = rdl.query(
				mother.term("SELF", ":X", "CHILD", ":Y"),
				danger.term("SELF",":Z")
				
				);
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		rdl.updateHead();
		rdl.updateTail();
		System.out.println("*************");
		results = rdl.query(
			//mother.term("SELF", ":X", "CHILD", ":Y"),
				//danger.term("SELF",":X")//,
				protect.term("SELF",":J","TARGET",":K")
				);
		for (IPersistentMap m : results){
			System.out.println(m);
		}
		System.out.println("After the rule has executed: ");
		//for (IPersistentMap m : results){
		//	System.out.println(m);
		//}/*
	
//	*/
	}

}
