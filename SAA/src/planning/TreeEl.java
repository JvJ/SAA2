package planning;

import java.util.*;

public interface TreeEl {

	public String getName();
	
	public ArrayList<LinkedList<TreeEl>> pathTraverse(LinkedList<TreeEl> rootList);
	
	
}
