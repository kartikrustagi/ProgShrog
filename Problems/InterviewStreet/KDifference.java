import java.io.*;
import java.util.*;

class Solution{

	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String temp = br.readLine();
			String[] para = temp.split(" ");
			int N = Integer.parseInt(para[0]);
		        long K = Long.parseLong(para[1]);
			para = br.readLine().split(" ");
			long[] arr = new long[N];
			for(int i=0; i<N; i++){
				arr[i] = Long.parseLong(para[i]);
			}
			Arrays.sort(arr);
			int num_pairs = 0;
			for(int i=0;i<N;i++){
				long key = (K+arr[i]);
				if(key<arr[i]){
					continue;
				}
				if((i+1)>=N)
					break;
				if(Arrays.binarySearch(arr,i+1,N,key)>i){
					++num_pairs;
				}
			}
			System.out.print(num_pairs+"\n");
		}catch(Exception e){
			e.printStackTrace();
       	 	}
    }

}
