import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(br.readLine());
			long N = 0;
			while(T>0){
				N = Long.parseLong(br.readLine());//TODO: Check for large values
				if((N%2)==0)
					System.out.print("ALICE \n");
				else
					System.out.print("BOB \n");
				T--;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}