package RDTest;

import java.util.*;

public class DsGraph {
	int id;
	double h;
	public class nei{
		public int id;
		public double weight;
		public double h;
		public nei(){}
		
	}
	LinkedList <nei> neighbours;
	public DsGraph(int id){
		this.id=id;
		neighbours=new LinkedList<nei>();	
	}
	public void setNeigh(int id, double d,double h){
		nei next=new nei();
		next.id=id;
		next.weight=d;
		next.h=h;
		neighbours.add(next);
	}	
	public void setH(double h){
		this.h=h;
	}
	public int returnID(int id){
		return (this.neighbours.get(id).id);
	}
	public double returnW(int id){
		return(neighbours.get(id).weight);
	}
	
}
