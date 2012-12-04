package goapI;

import java.util.*;

public class GoapAction {
	public LinkedList<GoapWorldFact> precondition=new LinkedList<GoapWorldFact>();
	public LinkedList<GoapWorldFact> effects=new LinkedList<GoapWorldFact>();
	public String actionName; 
	public GoapAction(){
	}
	public String getName(){
		return(this.actionName);
	}
	public GoapAction(String s,LinkedList<GoapWorldFact> pre,LinkedList<GoapWorldFact> eff){
		this.precondition.addAll(pre);
		this.effects.addAll(eff);
		this.actionName=s;
	}
	public GoapAction(String s,LinkedList<GoapWorldFact> eff){
	
		this.effects.addAll(eff);
		this.actionName=s;
	}
	
	public GoapAction(String s,GoapWorldFact eff){
	this.effects.add(eff);
		this.actionName=s;
		}
	
	public GoapAction(String s,GoapWorldFact pre,GoapWorldFact eff){
		this.precondition.add(pre);
		this.effects.add(eff);
		this.actionName=s;
	}

	public GoapAction(String s,GoapWorldFact pre,LinkedList<GoapWorldFact> eff){
		this.precondition.add(pre);
		this.effects.addAll(eff);
		this.actionName=s;
	}
	public GoapAction(String s,LinkedList<GoapWorldFact> pre,GoapWorldFact eff){
		this.precondition.addAll(pre);
		this.effects.add(eff);
		this.actionName=s;
	}
	
	public void setName(String s){
		actionName=s;
		
	}
	public void setPre(LinkedList<GoapWorldFact> p){
		precondition=p;
	}
	public void setEffect(LinkedList<GoapWorldFact> e){
		effects=e;
		
	}
	public void setPre(GoapWorldFact p){
		precondition.add(p);//=p;
	}
	public void setEffect(GoapWorldFact e){
		effects.add(e);		
	}

	public LinkedList<GoapWorldFact> getPre(){
		return(this.precondition);//=p;
	}
	public LinkedList<GoapWorldFact> getEffect(){
		return(this.effects);
		
	}
	public void addpre(GoapWorldFact f){
		this.precondition.add(f);
	}
	public void addpre(LinkedList<GoapWorldFact> f){
		this.precondition.addAll(f);
	}
	public void addeff(GoapWorldFact f){
		this.effects.add(f);
	}
	public void addeff(LinkedList<GoapWorldFact> f){
		this.effects.addAll(f);
	}
	public void print(){
		System.out.println("action namee:");
		System.out.println(actionName);
		for (int i=0;i<this.effects.size();i++)
		{
			System.out.println("eff"+i);
			this.effects.get(i).print();
		}
		System.out.println("*******");
		for (int i=0;i<this.precondition.size();i++)
		{
			System.out.println("pre"+i);
			this.precondition.get(i).print();
		}
	}
}
