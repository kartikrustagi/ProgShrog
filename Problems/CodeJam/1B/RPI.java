import java.io.*;

class RPI{
	public static void main(String[] args){
		int T=0;
		int t=1;
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			T = Integer.parseInt(br.readLine());
			int N=0;
			double[][] m;
			int n = 0;
			int i = 0;
			String row;
			char c;
			int w = 0;
			int g = 0;
			int rpi = 0;
			double[][] r;
			int count = 0;
			double sum = 0;
			
			while(t<=T){
				N = Integer.parseInt(br.readLine());
				m = new double[N][N+2]; //w,g
				r = new double[N][3];
				n = 0;
				
				for(n=0;n<N;n++){
					row = br.readLine();
					w = 0;
					g = 0;
					for(i=0; i<N; i++){
						c = row.charAt(i);
						if(c=='0'){
							m [n][i] = 0;
							g += 1;
						}
						else if(c=='1'){
							m[n][i] = 1;
							g += 1;
							w += 1;
						}
						else
							m[n][i] = -1;
					}
					m[n][N] = w;
					m[n][N+1] = g;
				}
				//m defined
				/*
				for(n=0;n<N;n++){
					for(i=0; i<(N+2); i++){
						System.out.print(m[n][i] + " ");
					}
					System.out.print(" \n");
				}
				*/
				System.out.println("Case #"+t+":");
				for(n=0; n<N ; n++){
					//WP
					r[n][0] = (m[n][N]/m[n][N+1]);
					
					//OWP
					count = 0;
					sum = 0;
					for(i=0;i<N;i++){
						if(i==n)
							continue;
						if(m[i][n] == -1)
							continue;
						if(m[i][n] == 0)
							sum += (m[i][N]/(m[i][N+1]-1));
						else
							sum += ((m[i][N]-1)/(m[i][N+1]-1));
						count++;			
					}
					r[n][1] = (sum/count);
				}
				
				for(n=0; n<N ; n++){
					//OOWP
					count = 0;
					sum = 0;
					for(i=0;i<N;i++){
						if(i==n)
							continue;
						if(m[i][n] == -1)
							continue;
						sum += r[i][1];
						//System.out.println("SUM: "+sum);
						count++;			
					}
					//System.out.println("SUM: "+sum);
					r[n][2] = (sum/count);
				}
				
				/*
				for(n=0;n<N;n++){
					for(i=0; i<3; i++){
						System.out.print(r[n][i] + "   ");
					}
					System.out.print(" \n");
				}*/
				
				for(n=0; n<N ; n++){
					System.out.println((0.25*r[n][0]) + (0.50*r[n][1]) + (0.25*r[n][2]));
				}
				++t;
			}
		}catch(Exception ex){
		}
	}
}