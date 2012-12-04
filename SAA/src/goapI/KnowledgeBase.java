package goapI;

import java.util.*;

public class KnowledgeBase {
	public LinkedList<GoapAction> actions=new LinkedList<GoapAction>();
	public LinkedList<GoapWorldFact> facts=new LinkedList<GoapWorldFact>();
	public ActionMaps actionMapKb=new ActionMaps();
	public KnowledgeBase(){
		//actions=new LinkedList<GoapAction>();
	}
	
	public void putFactInKb(GoapWorldFact f){
		facts.add(f);
		
	}
	public void putActionInKb(GoapAction a){
		
		actions.add(a);
	
		//public LinkedList<GoapWorldFact> getEffect(){
		//return(this.effects);
		
	//}
		//public void addNewActionFact(LinkedList<GoapWorldFact> f, GoapAction a){
		actionMapKb.addNewActionFact(a.getEffect(), a);
	
	}
	//public void modifyAction(GoapAction a){
		//if (actions.contains(a))
		 //;
		
//	}
	
	public void actionInsert(){
		GoapWorldFact fact1=new GoapWorldFact("KweaponIsLoaded",true);
		putFactInKb(fact1);
		//////////////
		GoapWorldFact fact1plusplus=new GoapWorldFact("KweaponIsLoaded++",true);
		putFactInKb(fact1plusplus);
	///////////////////////////////
		
		GoapWorldFact fact2=new GoapWorldFact("KTargetIsDead",true);
		putFactInKb(fact2);
		LinkedList<GoapWorldFact> test=new LinkedList<GoapWorldFact>();
		test.add(fact1);
		test.add(fact1plusplus);
		GoapAction actionAttack=new GoapAction("actionAttack",fact1,fact2);
		putActionInKb(actionAttack);
		
	//	GoapAction actionAttack=new GoapAction("actionAttack",fact1plusplus,fact2);
	//	putActionInKb(actionAttack);
		
		//////////////////////
		GoapAction actionAttackplusplus=new GoapAction("actionAttack+++",fact1plusplus,fact2);
		putActionInKb(actionAttackplusplus);
////////////////////////
		GoapWorldFact fact3=new GoapWorldFact("KweaponIsArmed",true);
		putFactInKb(fact3);
	
		GoapAction actionWeapon=new GoapAction("actionWeapon",fact3,fact1);
	
		putActionInKb(actionWeapon);
		GoapAction drawWeapon=new GoapAction("drawWeapon",fact3);
			
		putActionInKb(drawWeapon);
	}
}
