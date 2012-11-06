package memory;
import java.util.*;
import worldPresentation.*;

public class theoryOfMind {
	LinkedList <Profile> agentsProfile=new LinkedList <Profile>();
	LinkedList <String> agentName=new LinkedList<String>();
	public theoryOfMind(){}
	public boolean checkActorName(Event event){
		
		return(agentName.contains(event.eventAction.actor.name));
	
		
	}
	public boolean checkTargetName(Event event){
		
		return(agentName.contains(event.eventAction.target.name));
		//agentsProfile.
		
	}
	public void addAgentsProfileActor(Event event){
	
		Profile newAgent= new Profile(event,true);
		agentsProfile.add(newAgent);
		agentName.add(event.eventAction.actor.name);
	
	}
	public void addAgentsProfileTarget(Event event){
		
		Profile newAgent= new Profile(event,false);
		agentsProfile.add(newAgent);
		agentName.add(event.eventAction.target.name);
	
	}
	public void updatingAgentsProfile(Event event){
		
		//already exist profile may become updated 
		/*int index=agentsProfile.indexOf(event.eventAction.actor);
		Profile updateAgent=agentsProfile.get(index);
		agentsProfile.remove(index);
		updateAgent.updateProfile(event);
		*/
		
	}
	public void createProfile(Event event){
		////
	}
	//public void checkEventActor(){}
	public void checkEventActor(Event event){
	
		if (this.agentsProfile.isEmpty())
			this.addAgentsProfileActor(event);
		else
		{	
			if (!(this.checkActorName(event)))
				this.addAgentsProfileActor(event);
			else
				if ( (this.checkActorName(event) ))
					this.updatingAgentsProfile(event);
		}
	
	
	}
	public void checkEventTarget(Event event){
		
		if (this.agentsProfile.isEmpty())
			this.addAgentsProfileTarget(event);
		else
		{	
			if (!(this.checkTargetName(event)))
				this.addAgentsProfileTarget(event);
			else
				if ( (this.checkTargetName(event) ))
					this.addAgentsProfileTarget(event);
		}
	
	
	}

	public void callingCheckEvent(Event event){
		
		if( (event.eventAction.actor.getClass()==agent.Agent.class))
			checkEventActor(event);
		if( (event.eventAction.target.getClass()==agent.Agent.class))
			checkEventTarget(event);
		
		///if (event.eventAction.target.)
	}
}