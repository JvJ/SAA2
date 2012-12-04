package planning;

import goapI.*;
import java.util.*;

public class Link {
	public String actName;
	public GoapAction act;
	public Node kid;
	public int level;
	public LinkedList<GoapWorldFact> linkActEffect=new LinkedList<GoapWorldFact>();
	public Link(GoapAction a,Node n){
		this.act=a;
		this.kid=n;
		setName();
		linkActEffect.addAll(a.getEffect());
		//System.out.println("link"+getName());
		//a.print();
	}
	public Node getNode(){
		return(this.kid);
	}
	public Node getKid(){
		return(this.kid);
	} 
	public void setKid(Node k){
		this.kid=k;
	}
	public GoapAction getAction(){
		return(this.act);
	}
	public void actEvaluation(){
		//find a way to evalute links!if the character find the link non useful he will leave it!
	}
	public void setName(){
		this.actName=act.getName();
	}
	public String getName(){
		return(this.actName);
	}
	/*public void print(){	
		System.out.println("name of the action in the link:   "+getName());
		System.out.print("Here is the kid:  ");
		kid.print();
		System.out.println("LLLLLLLLLLLLLL"+getName());
	}*/
	public void print(){	
		System.out.println("name of the action in the link:   "+getName());
		System.out.print("Here is the kid:  ");
		kid.print();
		System.out.println("LLLLLLLLLLLLLL"+getName());
	}

}
