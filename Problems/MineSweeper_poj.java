/*
 * Main.java
 */

import java.io.*;
import java.util.*;

class Main{

	public static int[] strToIntArr(String inStr){
		if(inStr==null)
			return null;
	
		String[] inputArr=null;
		
		inputArr=inStr.split(" ");//TODO: Make this whitespace generic whitespace using regex
		int[] inputIntArr = new int[inputArr.length];
		
		for(int i=0;i<inputArr.length;i++){
			inputIntArr[i] = Integer.parseInt(inputArr[i],10);
		}
		return inputIntArr;
	}
	
	public static int[][] printField(int[][] matrix, int n, int m){
		//System.out.println("here, n:"+n);
		int[][] field = new int[n][m];
		for(int i=0;i<n;i++){
			//System.out.println("Row: "+i);
			for(int j=0;j<m;j++){
				//System.out.println("Column: "+j+" Element:"+matrix[i][j]);
				
				if(matrix[i][j]=='*'){
					//Increment neighbour field cells
					//System.out.println(	"here");
					for(int x=(i-1);x<=(i+1);x++){
						for(int y=(j-1);y<=(j+1);y++){
							//System.out.println(	"here4");
							if((x==i)&&(y==j)){
								//System.out.println(	"x:"+x+" y:"+y);
								field[x][y]='*';
								continue;
							}
							if((x<0)||(x>(n-1))||(y<0)||(y>(m-1))||(matrix[x][y]=='*')){
								//System.out.println(	"here3");
								continue;
							}
							field[x][y]+=1;
						}
					}
				}
			}
		}
		
		return field;
		/*
		char star='*';
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print((field[i][j]==42)?star:Character.forDigit(field[i][j], 10));
			}
			System.out.print('\n');
		}*/
	}
	
		
	public static void printRes(int[][] moveMatrix, int[][] fieldMatrix, int n, boolean mineStruck){
		char star='*';
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(moveMatrix[i][j]=='x'){
					System.out.print((fieldMatrix[i][j]==42)?star:Character.forDigit(fieldMatrix[i][j], 10));
				}else{
					System.out.print((mineStruck&&(fieldMatrix[i][j]=='*'))?'*':'.');
				}
			}
			System.out.print((i==(n-1))?"":'\n');	
		}
	}
	
	public static void main(String[] args){
		String inputStr=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] inputIntArr=null;
			int n;
			int[][] inputMatrix;
			int[][] moveMatrix;
			int[][] fieldMatrix;
			int fieldNum=0;
			inputStr=br.readLine();
			n=Integer.parseInt(inputStr);
			
			inputMatrix = new int[n][n]; 
			moveMatrix = new int[n][n]; 
			
			//Fill inputMatrix
			for(int i=0;i<n;i++){
				inputStr=br.readLine();//String representation of ith row
				for(int j=0;j<n;j++){
					inputMatrix[i][j]=inputStr.charAt(j);
				}
			}
			
			//Fill moveMatrix
			boolean mineStruck=false;
			for(int i=0;i<n;i++){
				inputStr=br.readLine();//String representation of ith row
				for(int j=0;j<n;j++){
					moveMatrix[i][j]=inputStr.charAt(j);
					if((moveMatrix[i][j]=='x')&&(inputMatrix[i][j]=='*')){
						mineStruck=true;
					}
				}
			}
			
			//Get field matrix
			fieldMatrix=printField(inputMatrix,n,n);
			
			//Now use fieldMatrix and moveMatrix to get the result
			printRes(moveMatrix,fieldMatrix,n,mineStruck);
		
		}catch(Exception e){
		}
	}
}