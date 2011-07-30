/*
 * Main.java
 *  java program model for www.programming-challenges.com
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
	
	public static void printField(int[][] matrix, int n, int m){
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
		//System.out.println("Outside for");
		char star='*';
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print((field[i][j]==42)?star:Character.forDigit(field[i][j], 10));
			}
			System.out.print('\n');
		}
	}
	
	public static void main(String[] args){
		String inputStr=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] inputIntArr=null;
			int n,m;
			int[][] matrix;
			int fieldNum=0;
			while((inputStr=br.readLine())!=null){
				++fieldNum;
				
				/*
				n=Integer.parseInt(inputStr,10);
				m=n;
				*/
				
				//Should check for n and m now
				inputIntArr=strToIntArr(inputStr);
				n=inputIntArr[0];//Number of rows
				m=inputIntArr[1];//Number of columns
				
				if((n==0)&&(m==0))
					break;
				matrix = new int[n][m]; 
				for(int i=0;i<n;i++){
					inputStr=br.readLine();//String representation of ith row
					for(int j=0;j<m;j++){
						matrix[i][j]=inputStr.charAt(j);
					}
				}
				System.out.println("Field #"+fieldNum+":");
				printField(matrix,n,m);
				System.out.print('\n');
			}
		}catch(Exception e){
		}
	}
}