package RDTest;
import java.util.*;
public class AStar {
	LinkedList <Node> open;
	LinkedList <Node> close;
	LinkedList <Node> path;
	//LinkedList <DsGraph> graph ;
	GraphNew gNew;
	///LinkedList <>
	public Node start;
	public Node goal;
	public AStar(GraphNew g){
		open=new LinkedList<Node>();
		close=new LinkedList<Node>();
		path=new LinkedList<Node>();
		//graph=new LinkedList<DsGraph>();
		this.gNew=g;
		//open.add(start);
		int SID=getStart();
		//(int id,double g, double h 
		start=new Node(SID,0,gNew.returnH(SID) );
		//int graphID=findInGraph(SID);
	//	start=new Node(graph.get(graphID));
		//int GID=getGoal();
		//goal=new Node(GID,0,0);
		//what would be g?!?
		open.add(start);
	}
/*	public void getGraph(LinkedList<DsGraph> g){
		this.graph=g;
		
	}*/
	/*public int findInGraph(int id){
		for (int i=0;i<graph.size();i++)
			if (graph.get(i).id==id)
				return i;
		return (-1);
	}*/
	public int getStart(){
		System.out.println("what is the start node id?!");
		Scanner in = new Scanner(System.in);
		int id=in.nextInt();
		///int id = Integer.parseInt(System.console().readLine());
		return id;
	}
	public int getGoal(){
		System.out.println("what is the goal node id?!");
		Scanner in = new Scanner(System.in);
		int id=in.nextInt();
		return id;
	}
	
	public void predictingHeu(Node n){
		//comparing n and goal!
	}
	
	/*public void setGoal(Node goal){
		this.goal=goal;
	}
	*/
	public void setStart(Node s){
		this.start=s;
	}
	public void addOpen(Node o){
		open.add(o);
		
	}
	public void addClose(Node c){
		close.add(c);
	}
	public void removeOpen(Node c){
		if (open.contains(c))
			open.remove(c);
	}
	public boolean checkOpen(Node c)
	{
		if( open.contains(c))
			return true;
		else 
			return false;
	}
	public boolean checkclose(Node c)
	{
		if( close.contains(c))
			return true;
		else 
			return false;
	}
	public void SetStart(){
		
	}
	public Node BestOpen(){
		Node best=open.get(0);
		for (int i=0;i<open.size();i++){
			if (open.get(i).getf()<best.getf())
			{
				best=open.get(i);
			}	
		}
		return (best);
	}
/*	public DsGraph findNodInGraph(Node n){
		
		for (int i=0;i<graph.size();i++){
		  if (n.id==graph.get(i).id)
			  return graph.get(i);
		}
		
		return(null);
	}
	*/
	public int checkOpenList(int id){
	
		for(int i=0;i<open.size();i++){
			if( open.get(i).id==id)
				return( i);
		}
		return (-1);//-1 means it is not in the close list!
	}
	public int checkClosedList(int id){
		for (int i=0;i<close.size();i++){
			if (close.get(i).id==id)
				return (i);
		}
	
		return (-1);//-1 means it is not in the close list!
	}
	public void mainsearch(){
		Node currentN;
	//	DsGraph currentG;
		
		while (!open.isEmpty()){
			currentN=BestOpen();
			
			if (gNew.returnH(currentN.id)==0)
			{	
				System.out.println("gooooooooooooooooooooooooool"+currentN.id);
				goal=currentN;
				path.add(currentN);
				break;
			}
			open.remove(currentN);
			close.add(currentN);
			//currentG=findNodInGraph(currentN);
		//	Set <String> currentGSet=currentG.returnHashKey();
			
		//	for (int i=0;i<gNew.size;i++){
				for( int j=0;j<gNew.size;j++){
					if ((gNew.element(currentN.id, j)!=0 )&&(currentN.id!=j) ){
						int indexClose=checkClosedList(j);
				
						if( indexClose >-1){//if the ellemnt is in the close list
				
							if( (currentN.getG()+gNew.element(currentN.id, j))<(close.get(indexClose).getG())){
								close.get(indexClose).setParent(currentN.id);
								close.get(indexClose).setG(currentN.getG()+gNew.element(currentN.id, j));
							}
						}
						else{
							int indexOpen=checkOpenList(j);
							if( indexOpen >-1){//if the ellemnt is in the close list
								if( (currentN.getG()+gNew.element(currentN.id, j))<(open.get(indexOpen).getG())){
									open.get(indexOpen).setParent(currentN.id);
									open.get(indexOpen).setG(currentN.getG()+gNew.element(currentN.id, j));
								}
		
							}
							else{
								if ((checkClosedList(j)<0 )&&(checkOpenList(j)<0)){
									//Node(int pId,int id,double pweight,double h)
									Node newNode=new Node(currentN.id,j,gNew.element(currentN.id, j),gNew.returnH(j));
									open.add(newNode);
							//Node(DsGraph p,int id,double pweight)
								}
					
							}				
						}
					}
				}
			//}		
		}
		System.out.println("************************");
		for (int i=0;i<open.size();i++){
			System.out.println(open.get(i).id);
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		for (int i=0;i<close.size();i++){
			System.out.println(close.get(i).id);
		}	
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		Node help=new Node();
		help=goal;
		
		while (help.parent!=-1){
			System.out.println(help.id);
			Node parent=new Node();
			for (int i=0;i<close.size();i++){
				if (close.get(i).id==help.parent)
				{
					parent=close.get(i);
				}
			}
			path.add(parent);
			help=parent;
		}
		System.out.println(help.id);
		path.add(help);
		System.out.println("!!!!!!!!!!!!!!!!sd,jhfkjhsdkfkahfkjd!!!!!!!!");
		for(int i=0;i<path.size();i++){
			System.out.println(path.get(i).id);
		}
		
		
		
	}
		
}


