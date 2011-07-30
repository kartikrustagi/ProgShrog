import java.io.*;
import java.util.*;
import java.math.BigInteger;

class MinScalarBi{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int testCase = 1;
			int n;
			String[] str1;
			String[] str2;
			ArrayList<BigInteger>  arr1;
			ArrayList<BigInteger>  arr2;
			
			while(testCase<=T){
				n = Integer.parseInt(br.readLine());
				str1 = br.readLine().split(" ");
				str2 = br.readLine().split(" ");
				//To use internal sort
				arr1 = new ArrayList<BigInteger>();
				arr2 = new ArrayList<BigInteger>();
				for(String s: str1){
					arr1.add(new BigInteger(s));
				}
				for(String s: str2){
					arr2.add(new BigInteger(s));
				}
				Collections.sort(arr1);
				Collections.sort(arr2);
				BigInteger minScalarProduct = BigInteger.ZERO;
				int i=0;
				int j=(arr2.size()-1);
				while((i<arr1.size())&&(j>=0)){
					//System.out.println(arr2.get(j));
					minScalarProduct = minScalarProduct.add(arr1.get(i).multiply(arr2.get(j))); 
					i++;
					j--;
				}
				System.out.println("Case #"+testCase+": "+minScalarProduct.toString());
				testCase++;
			}
		}catch(Exception ex){}
	}
}