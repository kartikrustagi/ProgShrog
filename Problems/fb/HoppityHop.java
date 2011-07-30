import java.io.*;
import java.util.*;

class hoppity{
	public static void main(String args[]){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			StringTokenizer st = new StringTokenizer(br.readLine());
			String numString = st.nextToken();
			int num = Integer.parseInt(numString);
			
			for(int i=1;i<=num;i++){
				int rem3 = (i%3);
				int rem5 = (i%5);
				if((rem3==0)&&(rem5==0))
					System.out.print("Hop\n");
				else if(rem3==0)
					System.out.print("Hoppity\n");
				else if(rem5==0)
					System.out.print("Hophop\n");
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
		