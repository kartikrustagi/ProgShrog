import java.util.*;
import java.io.*;

class Magicka{
	
	public static class Pair{
		char l;
		char h;
		Pair(){
			l=' ';
			h=' ';
		}
		Pair(char x, char y){
			if(x<y){
				l=x;
				h=y;
			}else{
				l=y;
				h=x;
			}
		}
		
		public int hashCode(){
			String str="";
			str+=l;
			str+=h;
			return str.hashCode();
		}
		
		public boolean equals(Object x){
			Pair p = (Pair)x;
			if((p.l==l)&&(p.h==h))
				return true;
			else
				return false;
		}
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int t=1;
			String[] temp;
			Pair b;
			int co=0,op=0,i=0,j=0;
			HashMap<Pair,Character> coMap;
			HashSet<Pair> opMap;
			ArrayList<Character> elements;
			int inLen=0;
			
			while(t<=T){
				temp=br.readLine().split(" ");
				coMap = new HashMap<Pair,Character>();
				opMap = new HashSet<Pair>();
				elements = new ArrayList<Character>();
				
				co=Integer.parseInt(temp[0]);
				//System.out.println("co: "+co);
				i=1;
				while(i<=co){
					//System.out.println("Adding in coMap");
					b= new Pair(temp[i].charAt(0),temp[i].charAt(1)); //i starting from 1 takes care of starting from 0
					coMap.put(b,temp[i].charAt(2));
					++i;
				}
				i=(co+1);
				op = Integer.parseInt(temp[i]);
				//System.out.println("op: "+op);
				++i;
				j=0;
				while(j<op){
					//System.out.println("Adding in opMap");
					b= new Pair(temp[i].charAt(0),temp[i].charAt(1)); //i starting from 1 takes care of starting from 0
					opMap.add(b);
					++i;
					++j;
				}
				//Maps done
				inLen = Integer.parseInt(temp[i]);
				//System.out.println("inLen: "+inLen);
				++i;
				j=0;
				int k=0;
				char ch;
				char pen;
				
				while(j<inLen){
					ch = temp[i].charAt(j);
					//System.out.println("ch: "+ch);
					if(elements.size()>0){
						//System.out.println("Lets see: "+elements.size());
						pen = elements.get(elements.size()-1);
						Pair tempp = new Pair(ch,pen);
						if(coMap.containsKey(tempp)){
							//System.out.println("Found in coMap");
							elements.remove(elements.size()-1);
							//System.out.println("Found in coMap1: "+elements.size());
							elements.add(coMap.get(tempp));
							//System.out.println("Found in coMap: "+elements.size());
							++j;
							continue;
						}else{
							elements.add(ch);
						}
						for(k=0;k<(elements.size()-1);k++){
							tempp = new Pair(ch,elements.get(k));
							if(opMap.contains(tempp)){
								elements = new ArrayList<Character>();
								//System.out.println("Found in opMap");
								break;
							}
						}
					}else{
						elements.add(ch);
					}
					++j;	
				}
				System.out.print("Case #"+t+": [");
				for(i=0;i<elements.size();i++){
					System.out.print(elements.get(i));
					if(i!=(elements.size()-1))
						System.out.print(", ");
				}
				System.out.println("]");
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}