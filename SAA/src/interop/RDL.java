package interop;

import rdl.interop.*;

import clojure.lang.*;
import clojure.core.*;

public class RDL extends RDLInterface{
	
	public RDL(){
		super();
	}
	
	public Relation defRel(String name){
		return new Relation(super.defRel(name));
	}
	
	public Relation defRel(Class c){
		return new Relation(super.defRel(c));
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
	
	/**
	 * Get a value from a map!
	 * 
	 * @param m A Clojure map
	 * @param s A string key
	 */
	public Object get(IPersistentMap m, String s){
		
		// First, convert the string to a keyword and look it up
		// in the thingy.
		return typeConvert(m.valAt(var(s)));
		
	}
	
	/**
	 * Recursive type conversion function.
	 * 
	 * @param o An object to convert.
	 * @return Converted objects.
	 */
	private Object typeConvert(Object o){
		
		if (o instanceof Symbol){
			return ((Symbol)o).getName();
		}
		else if (o instanceof Keyword){
			return ((Keyword)o).getName();
		}
		else if (o instanceof IPersistentVector){
			
			IPersistentVector vRet = (IPersistentVector)o;
			Object[] ret = new Object[vRet.length()];
			
			for (int i = 0; i < vRet.length(); i++){
				ret[i] = typeConvert(vRet.nth(i));
			}
			
			return ret;
		}
		
		return o;
		
	}
}
