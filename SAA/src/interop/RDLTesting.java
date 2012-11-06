package interop;

import clojure.lang.*;

public class RDLTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		
		// First, generate an RDL instance
		RDL rdl = new RDL();
		
		System.out.println("Keyword test: "+ rdl.trans(":X", ":Y", "+", 0.1));
		
		// Define a relation with appropriate fields
		Relation mother = rdl.defRel("mother", "SELF", "CHILD");
		
		Relation grandmother = rdl.defRel("grandmother", "SELF", "GCHILD");
		
		// The query results are an array of persistent maps.
		IPersistentMap[] results;
		
		
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
		
		Relation agent = rdl.defRel("agent", "SELF");
		
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
		
	}

}
