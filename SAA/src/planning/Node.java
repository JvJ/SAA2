package planning;
import goapI.*;


import java.util.*;
public class Node {
	public String name="";
	public int level;
	boolean leaf =false;
	public LinkedList <Cell> cells=new LinkedList<Cell>();
	public Node(){}
	/*public Node(LinkedList<GoapWorldFact> facts, ActionMaps m){
		setName( facts);
		for (int i=0;i<facts.size();i++ ){
			Cell help =new Cell(facts.get(i),m);
			cells.add(help);
			}
		}*/
	public Node(LinkedList<GoapWorldFact> facts, ActionMaps m,int l){
		setName( facts);
		l++;
		this.level=l;
		if (facts.isEmpty())
		{	//System.out.println("+++++++++++++++++++++++++++++++++++");
		//	System.out.println(arg0)
			leaf=true;
		}
		for (int i=0;i<facts.size();i++ ){
			Cell help =new Cell(facts.get(i),m,l);
			cells.add(help);
			//System.out.println(help.getName());
			}
		}
	public int cellSize(){
		return(this.cells.size());
	}
	public Cell getCellTh(int i){
		return(cells.get(i));
	}
	public int NumberOfLinksInCell(int i){
		return (this.cells.get(i).numberOfLinkInCell());
	}
	public Link linkInCellAt(int i,int j){
		return(this.cells.get(i).getLinkCellAt(j));
	}
	public Node LinKidAtCell(int i,int j){
		return (this.cells.get(i).getLinkKidAt(j));
	}
	public GoapAction LinkActionAtCell(int i, int j){
		return(this.cells.get(i).getLinkAction(j));
	}
	public void path(){
		if (cells.isEmpty())
			return;
		else
		{
			for (int i=0; i<cells.size();i++){
				Cell help=cells.get(i);
				
			}
		}
	}
	public void setName(LinkedList<GoapWorldFact> facts){
		for (int i=0; i<facts.size();i++){
			name=name+facts.get(i).getName();
		}
	}
	public String getName(){
		return(this.name);
	}
	public int getLevel(){
		return(this.level);
	}
	public boolean IsItLeaf(){
		return (leaf);
	}
	public void print(){
		//System.out.println();
		System.out.println("Node.."+ getName()+ "here is the number"+getLevel());
//		if (leaf)
	//		System.out.println("+++++++++++++++++++++++++++++++++++");
		for(int i=0;i<cells.size();i++ ){
			System.out.println("here is the "+i+" th cell ");
			cells.get(i).print();
			
		}
		System.out.println("******************************");
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNN"+getName());
//System.
	
	}
	
}
