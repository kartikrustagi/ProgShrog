import java.io.*;
import java.util.*;

class Main{
	
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			String line;
			line = br.readLine();
			st = new StringTokenizer(line);
			long n = Long.parseLong(st.nextToken(),10);
			long k = Long.parseLong(st.nextToken(),10);
			long count = 0L;
			
			for(long l=0L;l<n;l++){
				line = br.readLine();
				st = new StringTokenizer(line);
				count+=(((Long.parseLong(st.nextToken(),10)%k)==0)?1:0);
			}
			System.out.println(count);
		}catch(Exception e){
		}
	}
	
}