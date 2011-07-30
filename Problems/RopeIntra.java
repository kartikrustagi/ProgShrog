import java.io.*;
import java.util.*;

class RopeIntra{
	
	public static int[] intArr(String[] strArr){
		int[] arr = new int[strArr.length];
		for(int i = 0; i< strArr.length; i++){
			arr[i] = Integer.parseInt(strArr[i]);
		}
		return arr;
	}
	
	public static class Wire{
		int b1;
		int b2;
		Wire(){
			b1 = 0;
			b2 = 0;
		}
	}
	
	public static class WireCompare implements Comparator <Wire>{
		public int compare(Wire a, Wire b){
			return (a.b1-b.b1);
		}
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int testCase = 1;
			int N;
			RopeIntra.Wire[] building;
			int i=0;
			String[] temp;
			int ans = 0;
			int x;
			while(testCase<=T){
				N = Integer.parseInt(br.readLine());
				building = new RopeIntra.Wire[N]; 
				i = 0;
				while(i<N){
					temp = br.readLine().split(" ");
					building[i] = new RopeIntra.Wire();
					building[i].b1 = Integer.parseInt(temp[0]);
					building[i].b2 = Integer.parseInt(temp[1]);
					++i;
				}
				//So building is complete now
				//Sort building on the basis of 1st column
				Arrays.sort(building,new WireCompare());
				//Building should be sorted by now
				ans = 0;
				for(i=(N-1);i>=0;i--){
					x = building[i].b2;
					//All floor with b1 smaller and b2 greater than this should be in the answer
					for(int j = (i-1); j>=0; j--){
						if(building[j].b2>x)
							++ans;
					}
				}
				System.out.println("Case #"+testCase+": "+ans);
				++testCase;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	}
}