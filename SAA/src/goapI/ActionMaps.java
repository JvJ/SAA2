
package goapI;
import java.util.*;

public class ActionMaps {

	
	public LinkedList<ActionMap> map;
	//public Set<String> factsName;
	
	public ActionMaps(){
		map=new LinkedList<ActionMap>();
		
	}
//	public void getAction(GoapAction a){
		
	//}
	public void addNewActionFact(GoapWorldFact f,LinkedList<GoapAction> action){
		for(int i=0;i<action.size();i++){
			addNewActionFact(f,action.get(i));
		}
	}
	public void addNewActionFact(LinkedList<GoapWorldFact> f, GoapAction a){
		if (f.size()!=0)
			for(int i=0;i<f.size();i++){
				addNewActionFact(f.get(i),a);
			}
	}	
	public void addNewActionFact(GoapWorldFact f,GoapAction action){
		boolean found=false;
		for (int i=0 ;i<map.size();i++)
		{
			if ( map.get(i).equals(f) ){///look for the fact!
				found=true;
				if (! (map.get(i).findAction(action) ))//if there is a fact but not the action we add the action in the same map
				{
					map.get(i).addAction(action);
					break;
				}		
			}
		}
		if (!found){//if it couldnt fond the fact !it creat a new map for the fact and the action!
			ActionMap help=new ActionMap(action,f);
			map.add(help);
		}
	}
	public LinkedList<GoapAction> getActionMap(GoapWorldFact f){
		LinkedList <GoapAction> actionList=new LinkedList<GoapAction>();
//it looks for the fact in the table and find the action list
		for (int i=0;i<map.size();i++){
			if (map.get(i).factEqual(f))
			{//actionList=map.get(i).getActions();
				actionList.addAll(map.get(i).getActions());
			}
		}
		//it return the action list!if there is no action necessary it returns an empty list!
		return(actionList);
	}
}
