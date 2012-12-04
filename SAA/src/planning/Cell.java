package planning;

import goapI.*;
//import goapI.GoapAction;
//import goapI.GoapWorldFact;
import java.util.*;

public class Cell implements TreeEl{
	String name;
	boolean leaf=false;
	GoapWorldFact fact;
	LinkedList<Link> link;
	ActionMaps actionMap;
	//remember to link this action mp to the one in the knowledge base
	public Cell(){}
/*	public Cell(GoapWorldFact f, ActionMaps m){
		this.actionMap=m;
		this.fact=f;	
		LinkedList <GoapAction> actionList=actionMap.getActionMap(f);
		LinkedList <Link> linkList=new LinkedList<Link>();
		setName();
		this.link=linkList;
		if (actionList.isEmpty())
			return;
		else{
			for (int j=0;j<actionList.size();j++){
				Node hNode=new Node(actionList.get(j).getPre(),actionMap);
				Link hLink=new Link(actionList.get(j),hNode);
				linkList.add(hLink);	
			//	System.out.println("cell loop for the link");
			}	
			this.link=linkList;
		}
	//	System.out.println("cell"+getName());
	}
	*/
	public int numberOfLink(){
		return(link.size());
	}
	public Cell(GoapWorldFact f, ActionMaps m,int l){
		this.actionMap=m;
		this.fact=f;	
		LinkedList <GoapAction> actionList=actionMap.getActionMap(f);
		LinkedList <Link> linkList=new LinkedList<Link>();
		setName();
		this.link=linkList;
		if (actionList.isEmpty()){
			leaf=true;
			System.out.println("+++++++++++++++++++++++++++++++++++");
			return;
		}
			
		else{
			for (int j=0;j<actionList.size();j++){
				Node hNode=new Node(actionList.get(j).getPre(),actionMap,l);
				Link hLink=new Link(actionList.get(j),hNode);
				linkList.add(hLink);	
			}	
			this.link=linkList;
		}
	}
	public void setName(){
		this.name=fact.getName();
	}
	public String getName(){
		return(this.name);
	}
	public boolean checkEmptylinklist(){
		return(this.link.isEmpty());
	}
	public int numberOfLinkInCell(){
		return (this.link.size());
	}
	public Link getLinkCellAt(int i){
		return (this.link.get(i));
	}
	public Node getLinkNodeAt(int i){
		return (this.link.get(i).getNode());
	}
	public GoapAction getLinkAction(int i){
		return(this.link.get(i).getAction());
	}
	public Node getLinkKidAt(int i){
		return(this.link.get(i).getKid());
	}
	public void print(){
		System.out.println("cell: here is the name of fact in the cell ");
		System.out.println(getName());
		if (leaf){
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("leeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaf");
		}
			for (int i=0; i<link.size();i++){
			System.out.println("here are the name of"+i +"  th link in the node! ");
			link.get(i).print();
		}
		
		System.out.println("end of cccccccccccccell"+getName());
		
	}
	
	public void printTraverse(){
		printTraverse(0);
	}
	
	public void printTraverse(int indentation){
		for(int i = 0; i < indentation; i++) System.out.print("\t");
		System.out.println("<Cell: "+name+">");
		
		for (Link l : link){
			l.printTraverse(indentation+1);
		}
	}
	
	public ArrayList<LinkedList<TreeEl>> pathTraverse(LinkedList<TreeEl> rootList){
		
		// Btw this is bullshit!
		LinkedList<TreeEl> newRoot = (LinkedList<TreeEl>)rootList.clone();
				
		newRoot.addFirst(this);
				
		ArrayList<LinkedList<TreeEl>> ret = new ArrayList<LinkedList<TreeEl>>();
				
		for (Link c : link){
			ret.addAll(c.pathTraverse(newRoot));
		}
				
		return ret;
		
	}
}