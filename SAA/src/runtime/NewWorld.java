package runtime;
import java.io.*;

import worldPresentation.*;
import agent.*;
import goal.*;
import java.util.*;

import interop.*;


public class NewWorld {

	
	public static void main(String[] args) throws IOException{
		
		//new RDLInterface().printSomething("fqhgads");
		
		/*String filename ="my.txt";
		PrintStream out;
		out=new PrintStream(new File(filename));
		out.println("my");
		out.println();
		out.flush();
		/*String filename2="input.txt";
		InputStream in;
		in=new FileInputStream(filename2);
		in.*/
		fact fact1=new fact();
		fact1.name="my";
		fact1.var=true;
		
		fact fact2=new fact();
		fact2.name="my1";
		fact2.var=false;
		Agent agent1=new Agent();
		
		Agent agent2=new Agent();
		
		agent1.name="sandy";
		agent2.name="mana";
		SimpleGoal goal=new SimpleGoal();
		LinkedList<fact> list=new LinkedList<fact>();
		list.add(fact1);
		list.add(fact2);
		goal.assignsuccCon(list);
		
		agent1.TestAssigngoal(goal);
		//System.out.println()
		
		Event event=new Event(agent2,agent1,"external");
		event.TestAttachFact(fact1);
		event.TestAttachFact(fact2);
		
		//agent1.agentAppraisal.recieveEventver2(event);
		
		agent1.receiveEvent(event);
		
		for(int i=0;i<agent1.agentAppraisal.emoList.size();i++){
			System.out.println("my************");
			System.out.println(agent1.agentAppraisal.emoList.get(i).name);
			System.out.println(agent1.agentAppraisal.emoList.get(i).value);
		}
		
		for(int i=0;i<agent1.agentMemory.listMemoryCell.size();i++){
			System.out.println("my************");
			System.out.println(agent1.agentMemory.listMemoryCell.get(i).event.eventAction.actor.name);
			//System.out.println(agent1.agentAppraisal.emoList.get(i).value);
		}
		
		FileInputStream fstream = new FileInputStream("text.txt");
		  // Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  //br.readLine();
		System.out.println(br.readLine());
		  
		
	}

}
