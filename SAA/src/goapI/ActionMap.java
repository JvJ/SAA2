
package goapI;
import java.util.*;
public class ActionMap {
	GoapWorldFact fact;
	LinkedList<GoapAction> actions=new  LinkedList<GoapAction>();
	public void setFact(GoapWorldFact f){
		this.fact=f;
	}
	
	public ActionMap(){
		//actions=new LinkedList<GoapAction>();
		
	}
	public ActionMap(LinkedList<GoapAction> action,GoapWorldFact f){
		setFact(f);
		addAction(action);
		
	}
	public ActionMap(GoapAction action,GoapWorldFact f){
		setFact(f);
		addAction(action);
		
	}
	//public boolean findAction(LinkedList<GoapAction> list){
	//	for 
	//}
	public boolean findAction (GoapAction act){
		return(actions.contains(act));
		
	}
	public void addAction(LinkedList<GoapAction> action){
	//	this.actions=action;
		this.actions.addAll(action);
	}
	public void addAction(GoapAction a){
		this.actions.add(a);
	}
	public boolean factEqual(GoapWorldFact f){
		if (f.getName()==this.fact.getName()){
			if (f.getBvalue()==this.fact.getBvalue()){	
				return true;
			}
		}
		return false;
	}
	public LinkedList<GoapAction> getActions(){
		return(actions);
	}
	//public void printEffects(){
	//	for (int i=0;i<this.fact.bvalue)
//	}
	public void print(){
		System.out.println("fact:");
		this.fact.print();
		System.out.println("actions with definet outcome!:");
		for (int i=0;i<this.actions.size();i++){
			System.out.println(i+".  action:");
			this.actions.get(i).print();
		}
	} 
}
