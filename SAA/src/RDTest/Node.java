package RDTest;
import java.util.*;
public class Node {
	public int id;
	public double h;
	public double g;
	public double f;
	public int parent;
	
	public Node(){
		
	}
	public Node(int id,double g, double h ){
		this.g=g;
		this.h=h;
		this.f=g+h;
		this.parent=-1;
		this.id=id;
	}
	public Node(int pId,int id,double pweight,double h){
		this.id=id;
		this.parent=pId;
		this.h=h;
		this.g=pweight;
		this.h=h;
		setF();
		this.f=getf();
	}
	public Node(DsGraph p){
		this.parent=-1;
		this.g=0;
		this.h=p.h;
		this.f=this.g+this.h;
		this.id=p.id;
	}
	public void setParent(int pId){
		this.parent=pId;
	}
	public void setH(double h){
		this.h=h;
	}
	public void setG(double g){
		this.g=g;
	}
	public double getH(){
		return(this.h);
	}
	public double getG(){
		return(this.g);
	}
	public void setF(){
		this.f=getH()+getG();
	}
	public double getf(){
		return (this.f);
	}
	public void newNode(){
		
	}
}
