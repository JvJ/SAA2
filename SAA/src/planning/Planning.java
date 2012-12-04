
package planning;
import goapI.*;

import java.util.*;

public class Planning {
	KnowledgeBase kb;
	public Node root;
	public void planning(){
	}
	public Planning(KnowledgeBase k){
		kb=k;
	}
	/*public  Node buildTree(LinkedList<GoapWorldFact> f){
		
		root=new Node(f,kb.actionMapKb);
		return (root);
		//root.print();
	}*/
public void  buildTree(LinkedList<GoapWorldFact> f){
		
		root=new Node(f,kb.actionMapKb,0);
		//System.out.println("!!!!!!!!!!!!1");
	//	return (root);
	//	root.print();
	}
/*	public static void  main(String[] args){
	//	System.out.println("**********");
		GoapWorldFact fact2=new GoapWorldFact("KTargetIsDead",true);
		KnowledgeBase k=new KnowledgeBase();
		k.actionInsert();
		LinkedList<GoapWorldFact> goalTest=new LinkedList<GoapWorldFact>();
		System .out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111");
		goalTest.add(fact2);
		//buildTree(goalTest);
		System.out.println("****************");
		//buildTree(goalTest);
		System.out.println("root print function!!!!!!!!!!!!!!!!!!!111");
		//root.print();
		}
	*/
//	public GoapAction findAction(GoapWorldFact g){
		
	//}
	///public boolean goalActive(){}

}
