import java.io.*;
import java.util.*;

class BaPrepro{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			try{
				f = new File("twl06.txt"); /* /var/tmp/twl06.txt */
			}catch(Exception ex){
				System.out.println("In exception");
				f = new File("twl06.txt");
			}
			br = new BufferedReader(new FileReader(f));
			HashSet<String> dictHash = new HashSet<String>();
			int i=0;
			String[] dict = new String[178691];
			String line;
			while((line=br.readLine())!=null){
				dict[i]=line.toLowerCase();
				dictHash.add(dict[i]);
				i++;
			}
			//Store dict, dictHash
			FileOutputStream fileStream = new FileOutputStream("prepro.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(dict);
			os.writeObject(dictHash);
			os.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}