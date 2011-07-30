import java.io.*;
import java.util.*;

public class Har{
	public static class gC{
			int a;
			int b;
			gC(){
				a = 0;
				b = 0;
			}
			gC(int x,int y){
				a = x;
				b = y;
			}
	}
	
	
	public static int gcd(int a, int b) {        
       if (b==0)  
           return a;  
       else  
           return gcd(b, a % b);  
	}  
		
    public static int lcm(int a, int b) {  
		//System.out.println("LCM of "+a+" "+b);
		return (a / gcd(a, b)) * b;  
    }  
	
	
	public static void main(String[] args){
		int T=0;
		int t=1;
		int N = 0;
		int L  = 0;
		int H = 0;
		String[] para;
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			ArrayList<Integer> hList;
			boolean possible = false;
			
			while(t<=T){
				para = br.readLine().split(" ");	
				N = Integer.parseInt(para[0]);
				L = Integer.parseInt(para[1]);
				H = Integer.parseInt(para[2]);
				possible = false;
				
				if((1>=L)&&(1<=H)){
					System.out.println("Case #"+t+": 1");
					++t;
					continue;
				}
						
				hList = new ArrayList<Integer>();
				para = br.readLine().split(" ");
				for(int i = 0; i< para.length; i++){
					hList.add(Integer.parseInt(para[i]));
				}
				int i = 0;
				if(N==1){
					for(i=L; i<=H; i++){
						if((hList.get(0)%i)==0){
							possible = true;
							break;
						}
					}
					if(possible){
						System.out.println("Case #"+t+": "+i);
					}else{
						System.out.println("Case #"+t+": NO");
					}
					++t;
					continue;
				}		
				
				
				//Find gcd 
				int g = hList.get(0);
				
				for(int j=1; j<(hList.size()); j++){
					g = gcd(g,hList.get(j));
				}
				System.out.println("GCD: "+g);
				
				//Find lcm 
				int l = hList.get(0);
				
				for(int j=1; j<(hList.size()); j++){
					l = lcm(l,hList.get(j));
					//System.out.println(l);
				}
				System.out.println("LCM: "+l);
				
				//Check each gcd and lcm
				if(g>H){
					possible = false;
				}else if (g<L){
					if((l>=L)&&(l<=H)){
						possible = true;
						System.out.println("Case #"+t+": "+l);
					}else{
						possible = false;
					}					
				}else{
					int x = L;
					for(x=L;x<=H;x++){
						if(x<=g){
							if((g%x)==0){
								possible = true;
								System.out.println("Case #"+t+": "+x);
							}
						}else{
							possible = false;
							break;
						}
					}
					if(!possible){
						if((l>=L)&&(l<=H)){
							possible = true;
							System.out.println("Case #"+t+": "+l);
						}
					}
				}
				if(!possible){
					System.out.println("Case #"+t+": NO");
				}
				++t;
			}
		}catch(Exception ex){
		}
	}
}