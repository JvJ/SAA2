package goal;

import java.util.LinkedList;

//import appraisal.EmotionTraits;
import worldPresentation.*;

public class SimpleGoal {

	String goalName;
	
	//public LinkedList <fact>preConList = new LinkedList<fact>();
	///what makes the goal activated
	public LinkedList <fact>succConList = new LinkedList<fact>();
	///how we realised the goal has been achieved!
	public LinkedList <Rule> updateRule=new LinkedList<Rule>();
	public WorldObjects target;
	//rule based on preson and succ con to increase or decreease desireability
	public int goalDesireablity;///by appling goal updaterule 
	public boolean goalActivation;
	/*public void assignPreCon(LinkedList<fact> preConL){
		this.preConList=preConL;
	}*/
	public void assignsuccCon(LinkedList<fact> succConList){
		this.succConList=succConList;
	}


}

