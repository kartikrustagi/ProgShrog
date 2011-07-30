import java.io.*;
import java.util.*;
import java.math.BigInteger;

class MinScalar{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int testCase = 1;
			int n;
			String[] str1;
			String[] str2;
			ArrayList<Integer>  arr1;
			ArrayList<Integer>  arr2;
			
			while(testCase<=T){
				n = Integer.parseInt(br.readLine());
				str1 = br.readLine().split(" ");
				str2 = br.readLine().split(" ");
				//To use internal sort
				arr1 = new ArrayList<Integer>();
				arr2 = new ArrayList<Integer>();
				for(String s: str1){
					arr1.add(Integer.parseInt(s));
				}
				for(String s: str2){
					arr2.add(Integer.parseInt(s));
				}
				Collections.sort(arr1);
				Collections.sort(arr2);
				long minScalarProduct = 0;
				int i=0;
				int j=(arr2.size()-1);
				while((i<arr1.size())&&(j>=0)){
					minScalarProduct += (arr1.get(i)*arr2.get(j));
					//System.out.println(minScalarProduct);
					i++;
					j--;
				}
				System.out.println("Case #"+testCase+": "+minScalarProduct);
				testCase++;
			}
		}catch(Exception ex){}
	}
}