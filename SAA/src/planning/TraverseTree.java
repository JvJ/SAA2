package planning;
import java.util.*;
import goapI.*;
public class TraverseTree {
	LinkedList<ActionPath> path;
	public void TraverseTreeNonOptimal(){
		path=new LinkedList<ActionPath>();
	}
	public void TraversingTreeComp(Node root, ActionPath p ){
		if( root.IsItLeaf()){
			path.addLast(p);
			return;
		}
		else{
			for (int i=0;i<root.cellSize();i++){
				for (int j=0;j<root.NumberOfLinksInCell(i);j++){
					ActionPath help=new ActionPath();
				///	GoapAction newAction=root. 
				///help.addAll(p);
			//	LinkedList<GoapAction> LastPath=p.getAction(i)
				//if (root.)
			}
		}
		}
	}
	
	

}
