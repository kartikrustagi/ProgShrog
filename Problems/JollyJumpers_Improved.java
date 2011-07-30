/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;

class Main{

	public static boolean checkJollyBetter(int[] inputArr, int size){
		if(size==1)
			return true;
		int[] checkArr = new int[size-1];
		//Initialize with 0: Not required as default value is it self 0
		/*
		for(int i=0;i<(size-1);i++){
			checkArr[i]=0;
		}*/
		int temp=0;
		//System.out.println("Size: "+size);
		for(int i=0; i<(size-1);i++){
			//System.out.println("Entering: "+i);
			temp=Math.abs(inputArr[i+1]-inputArr[i]);
			//System.out.println(temp);
			if((temp>(size-1))||(temp==0))
				return false;
			else{
				if(checkArr[temp-1]==1)
					return false;
				checkArr[temp-1]=1;
				//System.out.println("Set");
			}
		}
		//System.out.println("Over");
		for(int i: checkArr){
			if(i==0)
				return false;
		}
		return true;
	}

	public static int[] strToIntArr(String inStr){
		if(inStr==null)
			return null;
		String[] inputArr=null;
		inputArr=inStr.split(" ");
		int[] inputIntArr = new int[inputArr.length-1];//As first element is the number of following elements
		int size=Integer.parseInt(inputArr[0]);
		for(int i=0;i<size;i++){
			inputIntArr[i] = Integer.parseInt(inputArr[i+1]);
		}
		return inputIntArr;
	}

	public static void main(String[] args){
		String str=null;
		int[] inputIntArr=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while((str=br.readLine())!=null){
				inputIntArr=strToIntArr(str);
				if(checkJollyBetter(inputIntArr,inputIntArr.length))
					System.out.println("Jolly");
				else
					System.out.println("Not jolly");
			}
		}catch(Exception e){
			
		}
	}
}
