import java.io.*;
import java.math.*;

class StoreCredit{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int N = Integer.parseInt(br.readLine());
			int testCase = 1;
			int C;
			int I;
			int[] reqCost;
			String str;
			String[] origCost;
			
			while(testCase<=N){
				C = Integer.parseInt(br.readLine());
				I = Integer.parseInt(br.readLine());
				reqCost = new int[1000];//Limit (Can be optimized at the cost of time(finding max and all))
				origCost = br.readLine().split(" ");
				int x=0,y;
				int me;
				for(String s: origCost){
					x++;//Index+1
					me = Integer.parseInt(s);
					//Does some one needs me?
					if(reqCost[me-1]!=0){
						System.out.println("Case #"+testCase+": "+reqCost[me-1]+" "+x);
						break;
					}
					//No one needs me, I will tell what I need
					y=(C-me);//Cost required for me 
					//I should need sth +ve only
					if(y<=0)
						continue; //Nothing to ask for since I am invalid
					reqCost[y-1]=x;//If you come, let me know I am leaving my address(x)
				}
				++testCase;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}