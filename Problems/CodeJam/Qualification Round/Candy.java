import java.util.*;
import java.io.*;

class Candy{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int t=1;
			int N=0;
			String[] candies;
			ArrayList<Integer> sums;
			ArrayList<Integer> sumDec;
			int x=0;
			int netSum=0;
			int netXor=0;
			int y=0;
			int max=0;
			int size;
			int xp=0,yp=0;
			
			while(t<=T){
				netSum=0;
				netXor=0;
				N=Integer.parseInt(br.readLine());	
				candies=br.readLine().split(" ");
				sums = new ArrayList<Integer>();
				sumDec = new ArrayList<Integer>();
				
				for(int i=0;i<candies.length;i++){
					x=Integer.parseInt(candies[i]);
					netSum+=x;
					netXor^=x;
					if(sums.size()==0){
					}else{
						size = sums.size();
						for(int j=0;j<size;j++){
							sums.add(sums.get(j)^x);
							sumDec.add(sumDec.get(j)+x);
						}
					}
					sums.add(x);
					sumDec.add(x);
				}
				//System.out.println(sumDec.size());
				//System.out.println("netSum :"+netSum);
				max=0;
				y=0;
				if(netXor!=0){
					System.out.println("Case #"+t+": NO");
					++t;
					continue;
				}
				
				for(int i=0;i<sumDec.size();i++){
					x=sums.get(i);
					xp = sumDec.get(i);
					//for(int j=(i+1);j<sums.size();j++){
					for(int j=(sums.size()-1);j>=(i+1);j--){
						if(sums.get(j)==x){
							yp = sumDec.get(j);
							if((xp+yp)==netSum){
								if(max<((xp>yp)?xp:yp)){
									max=((xp>yp)?xp:yp);
									//System.out.println("max: "+max);
								}
							}
							sumDec.remove(j);
							sums.remove(j);
							break;
						}
					}
				}
				
				
				/*
				for(int i=0;i<sumDec.size();i++){
					x=sumDec.get(i);
					y=(netSum-x);
					//System.out.println("x:"+x+" y:"+y+" size:"+sumDec.size());
					for(int j=(sumDec.size()-1);j>=(i+1);j--){
					//for(int j=(i+1);j<sumDec.size();j++){
						//System.out.println("here:"+sumDec.get(j));
						if(sumDec.get(j)==y){
							//System.out.println("x:"+x+" y:"+y+" size:"+sumDec.size());
							if(sums.get(i)==sums.get(j)){
								//System.out.println("here3");
								if(max<((x>y)?x:y)){
									max=((x>y)?x:y);
									//System.out.println("max: "+max);
								}
							}
							sumDec.remove(j);
							sums.remove(j);
							break;
						}
					}
				}*/
				
				System.out.println("Case #"+t+": "+max);
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}