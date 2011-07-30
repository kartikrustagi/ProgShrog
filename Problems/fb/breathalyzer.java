import java.io.*;
import java.util.*;

class breathalyzer{
		
	public static int dpEditDist(String s1, String s2){
		int n = s1.length();
		int m = s2.length();
		int[][] grid = new int[n+1][m+1];
		grid[n][m]=0;
		int[] minArr = new int[4];
		int min = 99999;
		int i=0,j=0;
		
		for(j=(m-1);j>=0;j--)
			grid[n][j]=(1+grid[n][j+1]);//3
		for(j=(n-1);j>=0;j--)
			grid[j][m]=(1+grid[j+1][m]);
		
		for(i=(n-1);i>=0;i--){
			for(j=(m-1);j>=0;j--){
				//System.out.println("i: "+i+" j:"+j);
				min = 999999;
				for(int x=0;x<4;x++)
					minArr[x]=-1;
				if(s1.charAt(i)==s2.charAt(j))
					minArr[0]=grid[i+1][j+1];
				minArr[1]=(1+grid[i+1][j+1]);//4
				minArr[2]=(1+grid[i+1][j]);//2
				minArr[3]=(1+grid[i][j+1]);//3
				for(int x: minArr){
					if((x!=-1)&&(x<min))
						min = x;
				}
				if(min==99999)
					System.out.println("Crap");
				grid[i][j]=min;
			}
		}
		return grid[0][0];
	}
			
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String wallPost = "";
			String line;
			while((line=br.readLine())!=null){
				wallPost+=(line+" ");	
			}
			String[] wallWords = wallPost.split("\\s+");
			HashMap<String,Integer> calcDict = new HashMap<String,Integer>();
			HashSet<String> dictHash;
			String[] dict;
			int i=0;
			
			/*
			FileInputStream fileStream = new FileInputStream("prepro.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			dict = (String[])os.readObject();
			dictHash = (HashSet<String>)os.readObject();
			*/
			
			
			f = new File("D:/ProgShrog/fb/twl06.txt"); /*/var/tmp/twl06.txt*/
			br = new BufferedReader(new FileReader(f));
			dictHash = new HashSet<String>();
			dict = new String[178691];
			while((line=br.readLine())!=null){
				dict[i]=line.toLowerCase();
				dictHash.add(dict[i]);
				i++;
			}
			
			int netScore=0;
			int min=999999;
			String minWord="";
			int temp;
			for(String p: wallWords){
				if(p==""){
					min=0;
					//System.out.println("Empty: "+p);
				}
				else if(dictHash.contains(p)){
					min=0;
					minWord=p;
					//System.out.println("Direct: "+p);
				}
				else if(calcDict.containsKey(p)){
					//System.out.println("Fetched directly: "+p);
					min = calcDict.get(p);
				}else{
					for(String q: dict){
						temp=dpEditDist(p,q);
						//System.out.println(temp);
						if(temp<min){
							min=temp;
							minWord=q;
							if(temp==0)
								break;
						}
					}
					//System.out.println("Calculated: "+p);
					calcDict.put(p,min);
				}
				
				//System.out.println("In:"+p+" minWord:"+minWord+" min:"+min);
				//System.out.println("ns: "+netScore);
				netScore+=min;
				min=999999;
				minWord="";
			}
			System.out.print(netScore+"\n");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}