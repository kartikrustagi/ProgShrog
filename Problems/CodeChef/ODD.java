import java.io.*;
import java.util.*;

class Main{

	/*
	public static long check(int n, int x){
		
	}
	*/
	
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			String line;
			long n;
			long x = 1;
			int testCases = Integer.parseInt(br.readLine());
			while(testCases>0){
				--testCases;
				x=1L;
				line = br.readLine();
				st = new StringTokenizer(line);
				n = Long.parseLong(st.nextToken());
				while(true){
					if((n/x)==1)
						break;
					x=x<<1;
					//x*=2;
				}
				System.out.print(x);
				if(!(testCases==0))
					System.out.print('\n');
			}
		}catch(Exception e){
		}
	}
}
				
				