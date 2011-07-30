import java.io.*;
import java.util.*;

class FileSystem{
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine()); //TestCases
			int t=1;
			HashMap<String, Object> FileDict;
			//Build Dictionary
			String path;
			int i = 0;
			HashMap<String, Object> LevelMap;
			String[] FilePara;
			String[] FileArr;
			int N,M;
			int ans=0;
			
			while(t<=T){
				FileDict = new HashMap<String,Object>();
				ans=0;
				FilePara = br.readLine().split(" ");
				N = Integer.parseInt(FilePara[0]); //Pre exisiting files
				M = Integer.parseInt(FilePara[1]); //New files to be created
				
				//Build existing structure
				while(N>0){
					path = br.readLine();
					FileArr = path.split("/"); 
					i = 0; //To handle 1st empty string 
					LevelMap = FileDict;
					while(i<(FileArr.length-1)){
						++i;
						//Check at i-th level
						//Get Hash map for this key
						if(!LevelMap.containsKey(FileArr[i])){
							//This key does not exist in this map
							LevelMap.put(FileArr[i],(new HashMap<String,Object>()));
						}
						LevelMap = (HashMap<String,Object>)LevelMap.get(FileArr[i]);
						//So now LevelMap is pointing to Map for the next level
					}
					--N;
				}
				
				while(M>0){
					path = br.readLine();
					FileArr = path.split("/"); 
					i =  0; //To handle 1st empty string
					LevelMap = FileDict;
					while(i<(FileArr.length-1)){
						++i;
						//System.out.println(path);
						//System.out.println(i+" "+FileArr[i]);
						//Check at i-th level
						//Get Hash map for this key
						if(!LevelMap.containsKey(FileArr[i])){
							//This key does not exist in this map
							++ans; //+1 to number of mkdir calls
							LevelMap.put(FileArr[i],(new HashMap<String,Object>()));
						}
						LevelMap = (HashMap<String,Object>)LevelMap.get(FileArr[i]);
						//So now LevelMap is pointing to Map for the next level
					}
					--M;
				}
			
				System.out.println("Case #"+t+": "+ans);
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}