class SpiralMatrix{
	
	static void printMatrixSpiralOutToIn(int[][] matrix,int x,int n){ /* COnsider it a square matrix for now */
		/* 
			x,x : Starting position of this spiral
			n: End of Row/Column for this spiral
		*/
		if(n<x)
			return;
		for(int j=x;j<=n;j++){
			System.out.print(" "+matrix[x][j]);
		}
		for(int i=(x+1);i<=n;i++){
			System.out.print(" "+matrix[i][n]);
		}
		for(int j=(n-1);j>=x;j--){
			System.out.print(" "+matrix[n][j]);
		}
		for(int i=(n-1);i>=(x+1);i--){
			System.out.print(" "+matrix[i][x]);
		}
		printMatrixSpiralOutToIn(matrix,x+1,n-1);
	}
	
	static void printMatrixSpiralInToOut(int[][] matrix,int x,int n){ /* COnsider it a square matrix for now */
		/* 
			x,x : Starting position of this spiral
			n: End of Row/Column for this spiral
		*/
		if(n<x)
			return;
		printMatrixSpiralInToOut(matrix,x+1,n-1);
		//System.out.println("One call");
		for(int i=(x+1);i<=(n-1);i++){
			System.out.print(" "+matrix[i][x]);
		}
		for(int j=x;j<=(n-1);j++){
			System.out.print(" "+matrix[n][j]);
		}
		for(int i=n;i>=(x+1);i--){
			System.out.print(" "+matrix[i][n]);
		}
		for(int j=n;j>=x;j--){
			System.out.print(" "+matrix[x][j]);
		}
	}
	
	
	public static void main(String[] args){
		 int[][] matrix = {{1,2,3,10}
						 ,{4,5,6,11}
						 ,{7,8,9,12}
						 ,{13,14,15,16}};
		/*int[][] matrix = {{1,2},{3,4}};*/
		String res="";
		//System.out.println(printMatrixSpiralOutToInStr(matrix,0,matrix.length-1,res));
		printMatrixSpiralInToOut(matrix,0,matrix.length-1);
	}
}