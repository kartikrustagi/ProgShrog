import java.io.*;
import java.util.*;

class Main{	
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			/*File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));*/
			String[] para;
			para = br.readLine().split(" ");
			int N = Integer.parseInt(para[0]);
			int Q = Integer.parseInt(para[1]);
			HashMap<String,String> fileAsso = new HashMap<String,String>();
			String ext;
			String mime;
			while(N>0){
				para = br.readLine().split(" ");
				ext = para[0];
				mime = para[1];
				fileAsso.put(ext,mime);
				N--;
			}
			while(Q>0){
				String temp = br.readLine();
				para = temp.split("\\.");
				if((fileAsso.containsKey(para[para.length-1]))&&(para.length>1)){
					System.out.println(fileAsso.get(para[para.length-1]));
				}else{
					System.out.println("unknown");
				}
				Q--;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}