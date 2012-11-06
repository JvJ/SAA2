package interop;

import rdl.interop.*;

public class Relation extends RDLRelation{

	private RDLRelation superInst;
	
	public Relation(RDLRelation r){
		super(r.getName());
		superInst = r;
	}
	
	public Relation(String name){
		super(name);
		superInst = new RDLRelation(name);
	}
	
	
	public Object term(Object... args){
		return super.term(args);
	}
	
	public Object retractRel(Object... args){
		return super.retractRel(args);
	}
	
	public Object assertRel(Object... args){
		return super.assertRel(args);
	}
	
	public Object modRel(Object... args){
		return super.modRel(args);
	}
}
