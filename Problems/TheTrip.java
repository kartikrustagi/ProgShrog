/*
 * Main.java
 */

import java.io.*;
import java.util.*;
import java.text.*;

class Main{

	public static String minTransfer(int[] inputIntArr, int n, int total){
		//divide by n
		int avg = (total/n);
		int amnt=0;
		int amnt2=0;
		//System.out.println(avg);
		for(int i: inputIntArr){
			if(avg>i)
				amnt+=(avg-i);
			//else
			//	amnt2+=(i-avg);
		}
		//System.out.println(amnt2-(total%n));
		DecimalFormat dec = new DecimalFormat("############0.00");
		return "$"+(dec.format(amnt/100.0));
		//return "$"+(amnt/100)+"."+(amnt%100);
	}

	public static void main(String[] args){
		String line=null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			//double[] inputDoubleArr=null;
			int[] inputIntArr=null;
			int n;
			int total=0;
			while((line=br.readLine())!=null){
				st = new StringTokenizer(line);
				n = Integer.parseInt(st.nextToken());
				if(n==0)
					break;
				inputIntArr=new int[n];
				total=0;
				for(int i=0;i<n;i++){
					line=br.readLine();
					inputIntArr[i]=(int)(Double.parseDouble(line)*(100.00));
					total+=inputIntArr[i];
				}
				System.out.println(minTransfer(inputIntArr,n,total));
					
			}
		}catch(Exception e){}
	}
}

