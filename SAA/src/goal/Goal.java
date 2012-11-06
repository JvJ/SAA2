package goal;

import worldPresentation.fact;

public abstract class Goal {
	String goalName;
	fact preCondition[];
	fact postConditionSucc[];
	fact postConditionFail[];
	int desirabilityVect[];
	int desirability;
	boolean active;
	//function(achive?)
	//function(desirebility)
	public boolean achieveGoal(){
		
		/*if (){
			
			
		}*/
		return false;
		
	}
	
}
