/*
 *  JollyJumpers.java
 *  java program model for www.programming-challenges.com
*/

import java.util.*;
import java.io.*;

class JollyJumpers{
	HashSet<Integer> succDiffSet;

	public static boolean checkJollyBasic(int[] inputArr, int size){
		//Take succDiff and push them in a HashSet
		succDiffSet = new HashSet<Integer>();
		/*
		System.out.println("inputIntArr: "+inputArr);
		for (int i: inputArr){
			System.out.print(i+" ");
		}
		System.out.println("");*/
		//TODO: check if comparable, hascode, equals are implemnted fine for Integer
		for(int i=0; i<(size-1);i++){
			if(!succDiffSet.add(Math.abs(inputArr[i+1]-inputArr[i])))
				return false;
		}
		//System.out.println("succDiffSet: "+succDiffSet);
		inputArr.toString();
		//So if we have not returned, then we have a sorted succDiffSet
		//just check if it has:
		//1. n-1 elements
		//2. 1st element is 1
		//3. last element is n-1
		//Using the above 3 and the fact that Hashset is sorted, no duplication and contains only integer, we should be done
		for(int i=1;i<size;i++){
			if(!succDiffSet.contains(new Integer(i)))
				return false;
		}
		return true;
	}

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
		System.out.println("Size: "+size);
		for(int i=0; i<(size-1);i++){
			System.out.println("Entering: "+i);
			temp=Math.abs(inputArr[i+1]-inputArr[i]);
			System.out.println(temp);
			if((temp>(size-1))||(temp==0))
				return false;
			else{
				if(checkArr[temp-1]==1)
					return false;
				checkArr[temp-1]=1;
				System.out.println("Set");
			}
		}
		System.out.println("Over");
		for(int i: inputArr){
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
		
		
		
	private void readInputFile(String file){
		File f = null;
		BufferedReader rb = null;
		FileReader fr = null;
		try{
			f = new File(file);
			fr = new FileReader(f);
			rb = new BufferedReader(fr);
		}catch(Exception e){
		}
		String input=null;
		String[] inputArr=null;
		//ArrayList<Integer> inputIntList=null;
		int[] inputIntArr=null;
		int inputArrSize=0;
		try{
			while((input=rb.readLine())!=null){
				inputArr=input.split(" ");
				//Now 1st element will be the number of elements
				inputArrSize=Integer.parseInt(inputArr[0]);
				System.out.println("inputArrSize: "+inputArrSize);
				//inputIntList = new ArrayList<Integer>();
				inputIntArr = new int[inputArr.length-1];
				for(int i=1;i<inputArr.length;i++){
					inputIntArr[i-1] = Integer.parseInt(inputArr[i]);
				}
				//inputIntSet is ready for computation
				if(checkJollyBasic(inputIntArr, inputArrSize))
					System.out.println("Jolly");
				else
					System.out.println("Not Jolly");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		String str=null;
		int[] inputIntArr=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while((str=br.readLine())!=null){
				inputIntArr=strToIntArr(str);
				if(checkJollyBasic(inputIntArr,inputIntArr.length))
					System.out.println("Jolly");
				else
					System.out.println("Not Jolly");
			}
		}catch(Exception e){
			
		}
	}
}
