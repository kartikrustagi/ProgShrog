import java.io.*;
import java.util.*;

class FileOp
{
	public static void writeFile(String[] str,String filename)
	{
		try
		{
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for(String s : str)
				bw.write(s);
			bw.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static String[] readFile(String filename)
	{
		String s="";
		try
		{

			FileReader fr = new FileReader(filename);
		        BufferedReader br = new BufferedReader(fr);
			ArrayList<String> stral = new ArrayList<String>(); 
			while((s=br.readLine())!=null)
			{
				stral.add(s);
			}
			br.close();
			s= stral.toString();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		//s = stral.toString();//Can we convert this to String[]?
		return(s.split("\\[")[1].split("\\]")[0].split(","));
	}
}
