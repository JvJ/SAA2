package planning;
import goapI.*;
import java.util.*;
public class ActionPath {
	LinkedList<GoapAction> actionPath;
	public ActionPath(){}
	public GoapAction getAction(int i){
		return(actionPath.get(i));
	}
	public void setAction(int i,GoapAction act){
		actionPath.add(i, act);
	}
	public LinkedList<GoapAction> getPath(){
		return(actionPath);
	}
	public void addLast(GoapAction act){
		actionPath.addLast(act);
	}
	public int Size(){
		return (this.actionPath.size());
	}
	public void addAll(ActionPath a){
		LinkedList<GoapAction> help=a.getPath();
		this.actionPath.addAll(help);
		//return(this.actionPath);
	}
	
}
