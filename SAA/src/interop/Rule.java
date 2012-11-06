package interop;

import rdl.interop.*;

public class Rule extends RDLRule{
	
	private RDLRule superInst;
	
	public Rule(RDLRule r){
		super(r.getName());
		superInst = r;
	}
	
	public Rule(String name){
		super(name);
	}
	
}
