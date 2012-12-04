package planning;

//import goapI.GoapWorldFact;
import goapI.*;

import java.util.LinkedList;

public class Test {
	public static void  main(String[] args){
		
			GoapWorldFact fact2=new GoapWorldFact("KTargetIsDead",true);
			KnowledgeBase k=new KnowledgeBase();
			k.actionInsert();
			LinkedList<GoapWorldFact> goalTest=new LinkedList<GoapWorldFact>();
			System .out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111");
			goalTest.add(fact2);
			//KnowledgeBase k=new KnowledgeBase();
			//k.actionInsert();
			Planning plan=new Planning(k); 
			plan.buildTree(goalTest);
			//System.out.println("****************");
			plan.buildTree(goalTest);
			System.out.println("root print function!!!!!!!!!!!!!!!!!!!111");
			plan.root.print();
	}

}
