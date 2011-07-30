import java.io.*;
import java.util.*;
import java.util.regex.*;

class AlienLangObj{

	static int matches = 0;
	static int penultimate, last;
	static ArrayList<ArrayList<Character>> pattern;
	
	public static void recPatternMatch(Object LevelMap, int level, ArrayList<Character> levelToken){
		if (level==last){
			//LevelMap is an ArrayList
			for(int i = 0; i<levelToken.size(); i++){
				if(((ArrayList<Character>)LevelMap).contains(levelToken.get(i)))
					matches++;		
			}
			return;
		}
		//LevelMap is a HashMap
		for(int i=0;i<levelToken.size();i++){
			Object obj = ((HashMap<Character,Object>)LevelMap).get(levelToken.get(i));
			if(obj!=null){
				recPatternMatch(obj, level+1, pattern.get(level+1));
			}else{
				continue;
			}
		}
		
		/*
		Set mapEntries = ((HashMap<Character,Object>)LevelMap).keySet();
		Iterator mapIterator = mapEntries.iterator();
		while(mapIterator.hasNext()){
			//Get the map for this key
			Object obj = ((HashMap<Character,Object>)LevelMap).get(mapIterator.next());
			if(obj!=null){
				recPatternMatch(obj, level+1, pattern.get(level+1));
			}else{
				return;
			}
		}*/
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String[] LangPara = br.readLine().split(" ");
			int L;
			L = Integer.parseInt(LangPara[0]);
			int D = Integer.parseInt(LangPara[1]);
			int N = Integer.parseInt(LangPara[2]);//Number of test cases
			HashMap<Character,Object> LangDict = new HashMap<Character,Object>();
			//Build Dictionary
			String word;
			int i = 0;
			HashMap<Character,Object> LevelMap;
			
			while(D>0){
				//Word of length L 
				word = br.readLine();
				i = -1;
				LevelMap = LangDict;
				last = (L-1);
				penultimate = (L-2);
				while(i<last){
					++i;
					//Check at i-th level
					//Get Hash map for this key
					if(i==last){
						//Last level, We need the ArrayList
						((ArrayList<Character>)LevelMap.get(word.charAt(penultimate))).add(word.charAt(i));
						continue;
					}
					if(!LevelMap.containsKey(word.charAt(i))){
						//This key does not exist in this map
						if(i==penultimate){
							//Penultimate will just need a ArrayList 
							LevelMap.put(word.charAt(i),(new ArrayList<Character>()));
							//So after penultimate's loop LevelMap is still pointing to the penultimate Map
							continue;
						}else{
							LevelMap.put(word.charAt(i),(new HashMap<Character,Object>()));
						}	
					}
					LevelMap = (HashMap<Character,Object>)LevelMap.get(word.charAt(i));
					//So now LevelMap is pointing to Map for the next level
				}
				--D;
			}
			
			//Dictionary complete, now need to go throgh test cases
			int testCase = 1;
			String input;
			boolean bracketOn = false;
			while(testCase<=N){
				bracketOn = false;
				//Each pattern will be of L tokens and each token of variable length(ArrayList) of characters
				pattern = new ArrayList<ArrayList<Character>>();
				//Now pattern is a array of reference variable of type ArrayList<char>
				input = br.readLine();
				//Let us start scanning character by character
				i = 0;//Token number;
				char c;
				int temp = -1;
				for(int j=0; j<input.length(); j++){
					c = input.charAt(j);
					if(temp!=i){
						//Add a ArrayList<character> to pattern ArrayList
						pattern.add(new ArrayList<Character>()); //Reference added to the reference ArrayList
						temp=i;
					}
					if(c=='('){
						bracketOn = true;
						continue;
					}
					else if(c==')'){
						bracketOn = false;
						i++; //Next character should go to next token
						continue;
					}
					pattern.get(i).add(c);
					if(bracketOn==true){
						//We are inside a token, so just add this character to this token
					}else{
						//We are probably dealing with a token of length=1
						i++;
					}
				}
				matches = 0;
				//Now pattern is complete, time to verify if it exists in the dict or not
				recPatternMatch(LangDict,0, pattern.get(0));
				System.out.println("Case #"+testCase+": "+matches);
				++testCase;
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
			ex.printStackTrace();
		}
	}
}