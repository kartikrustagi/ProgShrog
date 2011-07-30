import java.io.*;
import java.util.*;

class HD_algo{

	public static boolean check(double t, ArrayList<Double> pv, int D){
		System.out.println("In check t: "+t);	
		for(int i=0; i<pv.size(); i++){
			if(i==0){
				pv.set(i,(pv.get(i)-t));
			}else if(i==(pv.size()-1)){
				pv.set(i,(pv.get(i)+t));
			}else{
				double c = pv.get(i);
				double p = pv.get(i-1);
				double gap = Math.abs(c-p);
				if((c<p)||((c>p)&&(gap<D))){
					//That is we need to move right
					if(c>p){
						if((D-gap)<=t){
							pv.set(i,(pv.get(i)+(D-gap)));
						}else{
							System.out.println("In check f1");
							return false;
						}
					}
					if(c<p){
						if(t>=(gap+D)){
							pv.set(i,(pv.get(i)+(gap+D)));
						}else{
							System.out.println("In check f2");
							return false;
						}
					}
				}else{
					//We need to move left only
					double shiftAmt = (((gap-D)<t)?(gap-D):t);
					pv.set(i,(pv.get(i)-shiftAmt));
				}
			}
		}
		if(!((pv.get(pv.size()-1)-pv.get(pv.size()-2))>=D)){
			System.out.println("In check f3");
			return false;
		}
		else
			return true;
	}
	
	public static void main(String[] args){
		int T=0;
		int t=1;
		int C = 0;
		int D  = 0;
		double P = 0;
		double V = 0;
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			String[] para;
			ArrayList<Double> pv;
			
			while(t<=T){
				para = br.readLine().split(" ");
				C = Integer.parseInt(para[0]);
				D = Integer.parseInt(para[1]);
				pv = new ArrayList<Double>();
				for(int i=0; i<C; i++){
					para = br.readLine().split(" ");
					P = Integer.parseInt(para[0]);
					V = Integer.parseInt(para[1]);
					for(int j = 0; j<V; j++){
						pv.add(P);
					}
				}
				//Position vector pv
				//Sort pv
				Collections.sort(pv);
				
				double lower_bound = 0;
				double upper_bound = Math.pow(10,10);
				double time=0;
				while ((upper_bound - lower_bound) > Math.pow(10,-6)){
				  time = ((lower_bound + upper_bound) / 2);
				  if(check(time,pv,D)){
					upper_bound = time;
				  }	
				  else{
					lower_bound = time;
				  }
				}
				
				System.out.println("Case #"+t+": "+upper_bound);		
				++t;
			}
		}catch(Exception ex){
		}
	}
}