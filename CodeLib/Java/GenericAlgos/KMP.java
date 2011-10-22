class KMP{

	public static int[] constructTable(String w){
		int[] T = new int[w.length()];
		//Each entry in T is the amount of backtracing required
		T[0] = -1;
		if(w.length()<2)
			return T;
		else
			T[1] = 0;
		for(int i=2; i<w.length(); i++){
			int x = T[i-1];
			if(w.charAt(i-1)==w.charAt(x))
				T[i] = (T[i-1]+1);
			else
				T[i] = 0;
		}
		return T;
	}
	
	public static int kmpSearch(String s, String w){
		int[] T = constructTable(w);
		int i=0;
		int m=0;
		while((m+i)<s.length()){
			if(s.charAt(m+i)==w.charAt(i)){
				++i;
			}else{
				m = (m + i - T[i]);
				if(T[i]==0)
					m += 1;
				//System.out.println(m);
				i = ((i==0)?0:T[i]);
			}
			if(i==w.length())
				return m;
		}
		//System.out.println("No result");
		return s.length();
	}
	
	public static void main(String[] args){
		String w = "ABCAABC";//"EXAMPLE";
		String s = "HERE IS A SIMPLE EXAMPLE";
		
		int[] T = constructTable(w);
		for(int i=0;i<w.length();i++){
			System.out.print(T[i] + " ");
		}
		System.out.println();
		
		//System.out.println(kmpSearch(s,w));
	}

}
