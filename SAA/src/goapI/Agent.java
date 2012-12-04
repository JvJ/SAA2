
package goapI;
import java.util.*;
public class Agent {
	public GoapWorldFact agentGoal;
	public String name;
    LinkedList<GoapWorldFact> agentState;
	public void setname(String s){
		name=s;
		agentState=new LinkedList<GoapWorldFact>();
	}
	public LinkedList<GoapWorldFact> getAgentState(){
		return (agentState);
	}
	public void setGoal(){
		agentGoal=new GoapWorldFact();
		agentGoal.setName("KTargetIsDead");
		agentGoal.setBvalue(true);
	}
	public void setState(){
		GoapWorldFact s=new GoapWorldFact();
		agentGoal.setName("KTargetIsDead");
		agentGoal.setBvalue(false);
		agentState.add(s);
	}
	public boolean goalActive(){
		
		for (int i =0;i<agentState.size();i++){
			if (agentState.get(i).getName()==agentGoal.getName())
				if (agentState.get(i).getBvalue()==agentGoal.getBvalue())
					return true;
		}
		return false;
	}
	
	/*public void  SetGoal(GoapGoal g ){
		this.agentGoal=g;
	}*/
//	public 
	
	/*public boolean achieve(){
		return ()
	}*/
	
}
