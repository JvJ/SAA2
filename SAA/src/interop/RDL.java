package interop;

import rdl.interop.*;

import clojure.lang.*;
import clojure.core.*;

public class RDL extends RDLInterface{
	
	public RDL(){
		super();
	}
	
	public Relation defRel(String name, Object... args){
		
		return new Relation(super.defRel(name, args));
		
	}
	
	public Rule defRule(String name, Object... args){
		return new Rule(super.defRule(name, args));
	}
	
	public clojure.lang.IPersistentMap[] query(Object... args){
		return super.query(args);
	}
	
	public Object pList(Object... args){
		return super.pList(args);
	}
	
	public Object trans(String kw1, String kw2, Object func, Object... args){
		return super.trans(kw1, kw2, func, args);
	}
	
	public Object var(String name){
		
		if (name.length() > 1 && name.charAt(0) == ':'){
			name = name.substring(1);
		}
		
		return RT.keyword(null, name);
	}
}
