package interop;
import clojure.lang.*;
import agent.*;
import goal.*;

public class RDStart {
	public static void main(String[] args) {
		RDL rdl = new RDL();
		rdl.loadFile("testRD.clj");
		RDAgent jack=new RDAgent();
		GoalTest money=new GoalTest();
		
		
	
	
	}
}
