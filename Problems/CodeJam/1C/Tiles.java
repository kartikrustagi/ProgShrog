import java.io.*;
import java.util.*;

class Tiles{
	public static void main(String[] args){
		int T=0;
		int t=1;
		
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			int R = 0;
			int C = 0;
			String[] para;
			String temp;
			Character[][] m;
			
			while(t<=T){
				para = br.readLine().split(" ");
				R = Integer.parseInt(para[0]);
				C = Integer.parseInt(para[1]);
				m = new Character[R][C];
				
				//Construct m
				for(int i=0; i<R; i++){
					temp = br.readLine();
					for(int j=0; j<C; j++){
						m[i][j]=temp.charAt(j);
					}
				}
				
				//Start checking for 2*2 of #
				boolean possible = true;
				
				for(int i=0; i<R; i++){
					for(int j=0; j<C; j++){
						if(m[i][j]=='#'){
							//Should be left upper corner
							
							//Limits check
							if(!(((j+1)<C) && ((i+1)<R))){
								possible = false;
								break;
							}
							//Blue check
							if(!((m[i][j+1]=='#')&&(m[i+1][j]=='#')&&(m[i+1][j+1]=='#'))){
								possible = false;
								break;
							}
							//All fine, resplace
							m[i][j] = '/';
							m[i][j+1] = '\\';
							m[i+1][j] = '\\';
							m[i+1][j+1] = '/';
						}
					}
				}
				
				System.out.println("Case #"+t+":");
				if(possible == true){
					for(int i=0; i<R; i++){
						for(int j=0; j<C; j++){
							System.out.print(m[i][j]);
						}
						System.out.print("\n");
					}
				}else{
					System.out.println("Impossible");
				}
				++t;
			}
		}catch(Exception ex){
		}
	}
}