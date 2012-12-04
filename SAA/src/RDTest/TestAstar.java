package RDTest;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class TestAstar {
	public static void main(String args[]) throws IOException{
//	System.out.println("!!!!!!!!!!!!!!!!");
	LinkedList< DsGraph> graph=new LinkedList<DsGraph>();
//	System.out.println("get the node and neighbours");
	Scanner in = new Scanner(System.in);
	//int i=in.nextInt();
	//System.out.println(i);
	System.out.println("how many rows?");
	int raw=in.nextInt();
	double [][]graphIn=new double[raw][raw]; 
	for (int k=0;k<raw;k++)
		for (int j=0;j<raw;j++){
			System.out.println("enter the raw"+k+"enter the colomu"+j);
			graphIn[k][j]=in.nextInt();
		}
	for (int k=0;k<raw;k++){
		for (int j=0;j<raw;j++){
			System.out.println("enter the raw"+k+"enter the colomu"+j);
		//	graphIn[k][j]=in.nextInt();
			System.out.print(graphIn[k][j]);
			System.out.print("****");
		}
	System.out.println();
	}
	GraphNew graphT=new GraphNew(graphIn,raw);
	AStar astar=new AStar(graphT);
	astar.mainsearch();
	
	
	/*
	
	FileInputStream fstream = new FileInputStream("text.txt");
	  // Get the object of DataInputStream
	DataInputStream inf = new DataInputStream(fstream);
	BufferedReader br = new BufferedReader(new InputStreamReader(inf));
	  //br.readLine();
	System.out.println(br.readLine());
	
*/
	}
	
	
}
