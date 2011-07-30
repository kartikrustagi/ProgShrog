import java.io.*;

class RPI{
	public static void main(String[] args){
		int T=0;
		int t=1;
		int C = 0;
		int D  = 0;
		int P = 0;
		int V = 0;
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			String para;
			while(t<=T){
				para = br.readLine().split(" ");
				C = Integer.parseInt(para[0]);
				D = Integer.parseInt(para[1]);
				ArrayList<Integer> pv;
				ArrayList<Integer> gpv;
				for(int i=0; i<C; i++){
					pv = new ArrayList<Integer>();
					gpv = new ArrayList<Integer>();
					para = br.readLine().split(" ");
					P = Integer.parseInt(para[0]);
					V = Integer.parseInt(para[1]);
					gpv.add(P);
					for(int j = 0; j<V; j++){
						pv.add(P);
					}
				}
				//Position vector pv
				
				//Find out max time each group willl need to spred within them self
				int maxT = 0;
				int tempT = 0;
				int tempD = 0;
				int maxD = 0;
				ArrayList<Integer> arrD = new ArrayList<Integer>();
				for(int i=0; i<gpv.size();i++){
					if(gpv.get(i)%2 == 0){
						tempT = (D/2);
						if((gpv.get(i)/2)>1){
							tempT += ((gpv.get(i)/2)-1)*D;
						}
					}
					else{
						tempT = (gpv.get(i)-1)/2;
					}
					tempD = tempT;
					
					if(tempT>maxT)
						maxT = tempT;
					if(tempD>maxD)
						maxD = tempD;
						
					arrD.add(tempD);	
				}
				
				int sg = gpv.size();
				if((sg%2)==0){
					
				}
					
				
				++t;
			}
		}catch(Exception ex){
		}
	}
}