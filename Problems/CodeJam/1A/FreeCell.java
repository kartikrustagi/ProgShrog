import java.io.*;

class FreeCell{
	public static void main(String[] args){
		int T=0;
		int N=0;
		int Pd=0;
		int Pg=0;
		int t=1;
		String res;
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			while(t<=T){
				N=0;
				Pd=0;
				Pg=0;
				res = "Broken";
				String[] para = br.readLine().split(" ");
				String temp = para[0];
				Pd = Integer.parseInt(para[1]);
				Pg = Integer.parseInt(para[2]);
				
				if(temp.length()>=3){
					if(((Pg==100)&&(Pd!=100))||((Pg==0)&&(Pd!=0))){
						System.out.println("Case #"+t+": Broken");
						++t;
						continue;
					}else{
						System.out.println("Case #"+t+": Possible");
						++t;
						continue;
					}
				}
				N = Integer.parseInt(para[0]);
				if(((Pg==100)&&(Pd!=100))||((Pg==0)&&(Pd!=0))){
					res = "Broken";
				}else{
					if(N>=100){
						res = "Possible";
					}
					else{
						for(int n = N; n>=1; n--){
							if(((n*Pd)%100)==0){
								res = "Possible";
								break;
							}
							
						}					
					}
				}
				System.out.println("Case #"+t+": "+res);
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}