/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.math.*;

class Main{

	public static boolean checkJollyBetter(BigInteger[] inputArr, int size){
		if(size==1)
			return true;
		int[] checkArr = new int[size-1];
		//BigInteger sizeLessOne = new BigInteger
		//Initialize with 0: Not required as default value is it self 0
		/*
		for(int i=0;i<(size-1);i++){
			checkArr[i]=0;
		}*/
		int temp;
		//System.out.println(size);
		for(int i=0; i<(size-1);i++){
			//System.out.println("Entering: "+i);
			temp=(inputArr[i+1].subtract(inputArr[i])).abs().intValue();//Assumption that difference should not be more than the limit of an integer
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

	public static BigInteger[] strToBigIntArr(String inStr){
		if(inStr==null)
			return null;
	
		String[] inputArr=null;
		//System.out.println("Here");
		inputArr=inStr.split(" ");//TODO: Make this whitespace generic whitespace using regex
		BigInteger[] inputBigIntArr = new BigInteger[inputArr.length-1];
		int size=Integer.parseInt(inputArr[0]);
		for(int i=0;i<size;i++){
			//System.out.println(inputArr[i]);
			inputBigIntArr[i] = new BigInteger(inputArr[i+1],10);
		}
		return inputBigIntArr;
	}

	public static void main(String[] args){
		String inputStr=null;
		BigInteger[] inputBigIntArr=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while((inputStr=br.readLine())!=null){
				inputBigIntArr=strToBigIntArr(inputStr);
				if(checkJollyBetter(inputBigIntArr,inputBigIntArr.length))
					System.out.println("Jolly");
				else
					System.out.println("Not jolly");
			}
		}catch(Exception e){
			
		}
	}
}
