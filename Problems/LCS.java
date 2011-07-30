import java.util.*;
//Only the length of LCS
class LCS{

	public static int recursiveLCS(String X, String Y, int x,int y){
		//We are only considering X[1--x] and Y[1--y]
		if((x==-1)||(y==-1)){//0 or something lesser?
			return 0;
		}else if(X.charAt(x)==Y.charAt(y)){
			return (recursiveLCS(X,Y,x-1,y-1)+1);
		}else{
			//Selecting max of the two situations
			int a = recursiveLCS(X,Y,x-1,y);
			int b = recursiveLCS(X,Y,x,y-1);
			return((a>b)?a:b);
		}
	}
	
	public static int dynamicLCS(String X, String Y,int x,int y){
		int lenX = X.length();
		int lenY = Y.length();
		int tableSize = (((lenX>lenY)?lenX:lenY)+1);//+1 In order to take care of the case when one string is null
		int[][] T =  new int[tableSize][tableSize];
		//No need of initialization as all are already 0 #Assumption #Check
		int i=0, j=0;
		for(i=0;i<tableSize;i++){
			if(i>lenX){
				T[i][0]=-1;//Break point for the rows
				break;//Done with execution
			}
			for(j=0;j<tableSize;j++){
				if((i==0)||(j==0)){
					T[i][j]=0;
				}else if(j>lenY){
					//Done with this row, move to next row
					T[i][j]=-1;//Break point for this row
					break;
				}else if(X.charAt(i-1)==Y.charAt(j-1)){
					T[i][j]=(T[i-1][j-1]+1);
				}else{
					int a = T[i-1][j];
					int b = T[i][j-1];
					T[i][j] = ((a>b)?a:b);
				}
			}
		}
		//So now we have the table ready, finding the max entry (Could be integerated in 
		//Should be the last element man?
		return T[lenX][lenY];
		/*
		int max=-1, maxX=0, maxY=0;
		for(i=0;i<lenX;i++){}
		return 0;
		*/
	}
	
	public static char[] completeLCS(String X, String Y,int x,int y){
		int lenX = X.length();
		int lenY = Y.length();
		int tableSize = (((lenX>lenY)?lenX:lenY)+1);//+1 In order to take care of the case when one string is null
		int[][] T =  new int[tableSize][tableSize];
		int[][][] C = new int[tableSize][tableSize][2]; //Will store i,j
		
		//No need of initialization as all are already 0 #Assumption #Check
		int i=0, j=0;
		for(i=0;i<tableSize;i++){
			if(i>lenX){
				T[i][0]=-1;//Break point for the rows
				break;//Done with execution
			}
			for(j=0;j<tableSize;j++){
				if((i==0)||(j==0)){
					T[i][j]=0;
				}else if(j>lenY){
					//Done with this row, move to next row
					T[i][j]=-1;//Break point for this row
					break;
				}else if(X.charAt(i-1)==Y.charAt(j-1)){
					System.out.println("i: "+i+" j: "+j);
					T[i][j]=(T[i-1][j-1]+1);
					C[i][j][0] = (i-1);
					C[i][j][1] = (j-1);
				}else{
					int a = T[i-1][j];
					int b = T[i][j-1];
					if(a>b){
						System.out.println("i: "+i+" j: "+j);
						T[i][j] = a;
						C[i][j][0] = (i-1);
						C[i][j][1] = (j);
					}else{
					    System.out.println("i: "+i+" j: "+j);
						T[i][j] = b;
						C[i][j][0] = (i);
						C[i][j][1] = (j-1);
					}
				}
			}
		}
		//Construct LCS 
		//1. We add elements only when i,j gets value using i-1,j-1
		//2. We construct the String backwards
		//3. We need to stop at when we reach i==1 or j==1
		//4. We start from lenX,lenY
		int sizeLCS = T[lenX][lenY];
			char[] lcdStr = new char[sizeLCS];
		i=lenX;
		j=lenY;
		int tempX, tempY;
		--sizeLCS;
		System.out.println("LCS: "+lenY);
		while((i>=1)&&(j>=1)&&(sizeLCS>=0)){
			System.out.println("i: "+i+" j: "+j);
			tempX = C[i][j][0];
			tempY = C[i][j][1];
			//if Diagnol, add element
			if((tempX==(i-1))&&(tempY==(j-1))){
				lcdStr[sizeLCS--]=X.charAt(i-1);
			}
			i=tempX;
			j=tempY;
		}
		return lcdStr;
	}
	
	public static String constructLCS(String X, String Y, int[][] T){
		return null;
	}
	
	public static void main(String[] args){
		String X = "ABCBDAB";
		String Y = "BDCABA";
		//String X = "kartik";
		//String Y = "rustagi";
		//System.out.println(dynamicLCS(X,Y,(X.length()-1),(Y.length()-1)));
		char[] lcdStr = completeLCS(X,Y,(X.length()-1),(Y.length()-1));
		for(char c: lcdStr){
			System.out.print(c);
		}
		
	}
}