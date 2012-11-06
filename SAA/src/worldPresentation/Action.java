package worldPresentation;
import agent.*;

import appraisal.EmotionalAffect;


public class Action extends WorldObjects{
	fact preCondition[];
	fact postCondition[];
	double postConditionProb[];
	public Agent actor;
	public WorldObjects target;//this gonna be changed!
  	public EmotionalAffect actionEmotionalAffect;
  	
}
