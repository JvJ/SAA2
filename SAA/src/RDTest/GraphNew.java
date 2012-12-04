package RDTest;

public class GraphNew {
	public double[][] matrix;
	public int size;
	public GraphNew(double[][]ma,int s){
		this.matrix=ma;
		this.size=s;
	}
	public double element(int i ,int j){
		return (matrix[i][j]);
	}
	public double returnW(int i,int j){
		return(matrix[i][j]);
		
	}
 
	public double returnH(int i){
		return(matrix[i][i]);
	}
}
