package goapI;




public class GoapWorldFact {
	public String name;
	public boolean bvalue;
	public GoapWorldFact(){
		
	}
	public GoapWorldFact(String s, boolean b){
		this.name=s;
		this.bvalue=b;
		
	}
	public void setName(String s){
		this.name=s;
	}
	//public String getName(){}
	public void setBvalue(boolean b){
		this.bvalue=b;
	}	
	public boolean getBvalue(){
		return(this.bvalue);
	}
	public String getName(){
		return(this.name);
	}
	
	public void print(){
		System.out.print("fact:  ");
		System.out.print(name);
		if (this.bvalue)
			System.out.println("true");
		else
			System.out.println("false");

		
	}

}

