package planning;

//import goapI.GoapWorldFact;
import goapI.*;

import java.util.*;

public class Test {
	
	public static void printList(LinkedList<TreeEl> lst){
		
		for (TreeEl t: lst){
			System.out.print(t.getName()+", ");
		}
	}
	
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
			//System.out.println("root print function!!!!!!!!!!!!!!!!!!!111");
			//plan.root.print();
			
			plan.root.printTraverse();
			
			ArrayList<LinkedList<TreeEl>> lst = plan.root.pathTraverse(new LinkedList<TreeEl>());
			for (LinkedList<TreeEl> ll : lst){
				printList(ll);
				System.out.println("\n");
			}
			
	}

}
