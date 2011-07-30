import java.io.*;
import java.util.*;

class Main{
	static int[][] memoData;
	
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			String line;
			int[][] trian;
			//Lets us get nukber of testcases;
			int testCases = Integer.parseInt(br.readLine());
			int n;
			while(testCases>0){
				--testCases;
				//Number of rows
				//System.out.println("Before:");
				
				line = br.readLine();
				st = new StringTokenizer(line);
				n = Integer.parseInt(st.nextToken());
				
				//System.out.println("n: "+n);
				trian = new int[n][n];//Optimize??
				memoData = new int[n][n];
				int m =0;
				for(int i=0;i<n;i++){
					m=i+1;//Number of columns
					line = br.readLine();
					st = new StringTokenizer(line);
					for(int j=0;j<m;j++){
						trian[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				/*
				for(int i=0;i<n;i++){
					m=i+1;
					for(int j=0;j<m;j++){
						System.out.println(trian[i][j]+" ");
					}
					System.out.println("\n");
				}*/
				System.out.println(trianSum(trian,n,0,0));
			}
		}catch(Exception e){
		}
	}
	
	public static int trianSum(int[][] trian, int n, int x, int y){
		//We are at x,y
		//System.out.println("x:"+x+" y:"+(y));
		//If exceeding last row, end
		if(x>=n) //TODO: Can x ever exceed?
			return 0;
		
		//Add the current position and branch to next row
		if(memoData[x][y] != 0){
			return memoData[x][y];
		}
			
		int sum1 = trianSum(trian,n,x+1,y); //TODO: Memoization required? yes implement it 
		int sum2 = trianSum(trian,n,x+1,y+1);
		/*
		if(sum1>sum2)
			System.out.println("x:"+x+" y:"+(y+1));
		else
			System.out.println("x:"+(x+1)+" y:"+(y+1));
		*/
		int maxSum = (trian[x][y] +((sum1>sum2)?sum1:sum2));
		memoData[x][y] = maxSum;
		return maxSum;
	}
			
}
					
					
					