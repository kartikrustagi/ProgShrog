import java.io.*;
import java.util.*;

class Har_bekar{
	
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
			boolean possible = true;
			
			while(t<=T){
				para = br.readLine().split(" ");	
				N = Integer.parseInt(para[0]);
				L = Integer.parseInt(para[1]);
				H = Integer.parseInt(para[2]);
				possible = true;
						
				hList = new ArrayList<Integer>();
				para = br.readLine().split(" ");
				for(int i = 0; i< para.length; i++){
					hList.add(Integer.parseInt(para[i]));
				}
				int i=0; int j=0;
				for(i = L; i<= H; i++){
					possible = true;
					for(j=0;j<hList.size();j++){
						if(!(((hList.get(j)%i)==0)||((i%hList.get(j))==0))){
							possible = false;
							break;
						}
					}
					if(possible){
						System.out.println("Case #"+t+": "+i);
						break;
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