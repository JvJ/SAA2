package memory;
import worldPresentation.*;
//import agent.*;
//import agent.*;
public class Profile {
	//profile()
	String name;
	//creating attribute!each attribute should be linked to several event!
	public Profile(){
		
	}
	
	public Profile(Event event,boolean which){
		if (which==true) 
			this.name=event.eventAction.actor.name;
		else
			this.name=event.eventAction.target.name;
		//create a profile for the agent!
	}
      
      //list of attributes that we want to track!
    public void updateProfile(Event event){
    	//updating profile of an already exist profile
    	//check for consistency!
    	
    }
}
