import java.io.*;
import java.util.*;
import java.util.regex.*;

class AlienLang{

	static int matches = 0;
	static int penultimate, last;
	static ArrayList<Character>[] pattern;
	
	public static void recPatternMatch(Object LevelMap, int level, ArrayList<Character> levelToken){
		if (level==last){
			LevelMap = (ArrayList<Character>)LevelMap;
			//LevelMap is an ArrayList
			for(int i = 0; i<levelToken.size(); i++){
				if(LevelMap.contains(levelToken.get(i)))
					matches++;		
			}
			return;
		}
		//LevelMap is a HashMap
		LevelMap = (HashMap<Character,HashMap<Character,HashMap>>)LevelMap;
		Set mapEntries = LevelMap.keySet();
		Iterator mapIterator = mapEntries.Iterator();
		while(mapIterator.hasNext()){
			//Get the map for this key
			Object obj = LevelMap.get(mapIterator.next());
			if(obj!=null){
				recPatternMatch(obj, level+1, pattern[level+1]);
			}else{
				return;
			}
		}
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String[] LangPara = br.readLine().split(" ");
			int L = Integer.parseInt(LangPara[0]);
			int D = Integer.parseInt(LangPara[1]);
			int N = Integer.parseInt(LangPara[2]);//Number of test cases
			HashMap<Character,HashMap<Character,HashMap>> LangDict = new HashMap<Character,HashMap<Character,HashMap>>();
			//Build Dictionary
			String word;
			int i = 0;
			HashMap<Character,HashMap<Character,HashMap>> LevelMap;
			
			while(D>0){
				//Word of length L 
				word = br.readLine();
				i = 0;
				LevelMap = LangDict;
				last = (L-1);
				penultimate = (L-2);
				while(i<=last){
					//Check at i-th level
					//Get Hash map for this key
					if(i==last){
						//Last level, We need the ArrayList
						LevelMap.get(word.charAt(penultimate)).add(word.charAt(i));
					}
					
					if(!LevelMap.containsKey(word.charAt(i))){
						//This key does not exist in this map
						if(i==penultimate){
							//Penultimate will just need a ArrayList 
							LevelMap.put(word.charAt(i),(new ArrayList<Character>()));
							//So after penultimate's loop LevelMap is still pointing to the penultimate Map
							continue;
						}else{
							LevelMap.put(word.charAt(i),(new HashMap<Character,HashMap<Character,HashMap>>()));
						}	
					}
					LevelMap = LevelMap.get(word.charAt(i));
					//So now LevelMap is pointing to Map for the next level
				}
			}
			//Dictionary complete, now need to go throgh test cases
			int testCase = 1;
			String input;
			boolean bracketOn = false;
			while(testCase<=N){
				bracketOn = false;
				//Each pattern will be of L tokens and each token of variable length(ArrayList) of characters
				pattern = new ArrayList<Character>[L];
				//Now pattern is a array of reference variable of type ArrayList<char>
				input = br.readLine();
				//Let us start scanning character by character
				i = 0;//Token number;
				char c;
				for(int j=0; j<input.length(); j++){
					c = input.charAt(j);
					if(c=='('){
						bracketOn = true;
						//Start of new token, i is already incremented
						pattern[i] = new ArrayList<Character>();
						continue;
					}
					else if(c==')'){
						bracketOn = false;
						i++; //Next character should go to next token
						continue;
					}
					pattern[i].add(c);
					if(bracketOn==true){
						//We are inside a token, so just add this character to this token
					}else{
						//We are probably dealing with a token of length=1
						i++;
					}
				}
				matches = 0;
				//Now pattern is complete, time to verify if it exists in the dict or not
				recPatternMatch(LangDict,0, pattern[0]);
				System.out.println(matches);
			}
			/* 
			//Do it using regex later
			String regex = "(?[a-z]+)?{"+L+"}";
			Pattern p = Pattern.compile(regex);
			String pattern;
			while(testCase<=N){
				pattern = br.readLine();
				if(!p.matcher(pattern).matches()){
					//Problem in regex
					System.out.println("Problem in regex");
				}
			}*/
		}catch(Exception ex){
		}
	}
}