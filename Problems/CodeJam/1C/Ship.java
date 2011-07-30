import java.io.*;
import java.util.*;

public class Ship{
	public static void main(String[] args){
		int T=0;
		int t=1;
		String[] para;
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			int L,N,C; //time
			Double time;
			ArrayList<Double> planet;
			ArrayList<Double> planetSort;
			ArrayList<Double> newPlanet;
			double[] dArr;
			
			while(t<=T){
				
				para = br.readLine().split(" ");
				L = Integer.parseInt(para[0]);
				time = Double.parseDouble(para[1]); 
				N = Integer.parseInt(para[2]);
				C = Integer.parseInt(para[3]);
				dArr = new double[C];
				
				
				
				
				for(int i=0;i<C;i++){
					dArr[i] = Integer.parseInt(para[4+i]);
				}
				planet = new ArrayList<Double>();
				for(int i=0;i<N;){
					for(int j=0;j<C;j++){
						if(i>=N)
							break;
						planet.add(dArr[j]);
						++i;
					}
				}
				if(L==0){
					double res = 0;
					for(int i=0;i<N;++i){
						res += planet.get(i);
					}
					System.out.println("Case #"+t+": "+(long)(res*2));
					++t;
					continue;
				}
				
				double dCons = (time/2);
				//System.out.println("dCons: "+dCons);
				planetSort = new ArrayList<Double>();
				newPlanet = new ArrayList<Double>(); 
				double sum = 0;
				double prevSum = 0;
				int index = -1;
				for(int i=0;i<N;i++){
					sum+=planet.get(i);
					if(index != -1){
						//Time has been broken
						newPlanet.add(planet.get(i));
						planetSort.add(planet.get(i));
					}
					else if(sum>dCons){
						//Time to break;
						newPlanet.add(dCons-prevSum); 
						newPlanet.add(sum-dCons);
						planetSort.add(sum-dCons);
						index = i; 
					}else{
						newPlanet.add(planet.get(i));
					}
					prevSum = sum;
				}
				
				/*
				System.out.println("planet: ");
				for(int i=0;i<planet.size();i++){
					System.out.print(planet.get(i)+" ");
				}
				System.out.println();
				
				System.out.println("newPlanet: ");
				for(int i=0;i<newPlanet.size();i++){
					System.out.print(newPlanet.get(i)+" ");
				}
				System.out.println();
				*/
				
				Collections.sort(planetSort);
				/*
				System.out.println("planetSort: ");
				for(int i=0;i<planetSort.size();i++){
					System.out.print(planetSort.get(i)+" ");
				}
				System.out.println();
				*/
				double res = 0;
				for(int i=(planetSort.size()-1);i>=0;i--){
					if(L>0){
						res += planetSort.get(i);
						--L;
					}else{
						res += (planetSort.get(i)*2);
					}
				}
				for(int i=index; i>=0; i--){
				
					res += (newPlanet.get(i)*2);
				
				}
				
				System.out.println("Case #"+t+": "+(long)res);
				
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}