package goapI;
import java.util.*;
import planning.*;

public class runTime {
	public static void main(String[] args){
		System.out.println("**********");
		KnowledgeBase k=new KnowledgeBase();
		k.actionInsert();
	/*	
		for( int i=0;i<k.actionMapKb.map.size();i++){
			
				
				k.actionMapKb.map.get(i).print();
	
			System.out.println("**********");
		}*/
		//KnowledgeBase k
		Planning test=new Planning(k);
		GoapWorldFact fact2=new GoapWorldFact("KTargetIsDead",true);
		LinkedList<GoapWorldFact> goalTest=new LinkedList<GoapWorldFact>();
		goalTest.add(fact2);
		test.buildTree(goalTest);
		System.out.println();
	}
}
