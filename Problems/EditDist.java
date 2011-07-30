class EditDist{
	static int DC=2;
	static int IC=3;
	static int RC=0;
	static int ReC=4;
	
	public static int recEditDist(String s1, String s2, int i, int j){
		if(j==s2.length()){
			//The operation is over, just need to *delete* all extraneous elements at the end of s1
			if(s1.length()!=s2.length()){
				String temp = s1.substring(0,i)+s1.substring(i+1);//s1 minus the ith element 
				return (DC + recEditDist(temp,s2,i,j));
			}
		}else{
			if(i==s1.length()){
				//So now we should just do inserts
				String temp = (s1+s2.substring(j,j+1));
				return (IC + recEditDist(temp,s2,i+1,j+1));
			}else{
				if(s1.charAt(i)==s2.charAt(j)){
					return (RC + recEditDist(s1,s2,++i,++j));
				}else{
					String temp;
					//*replace*
					temp = s1.substring(0,i)+ s2.substring(j,j+1) + s1.substring(i+1);
					int min1 = (ReC + recEditDist(temp,s2,i+1,j+1));
					//*insert*
					temp = s1.substring(0,i)+ s2.substring(j,j+1) + s1.substring(i);
					int min2 = (IC + recEditDist(temp,s2,i+1,j+1));
					//*delete*
					temp = s1.substring(0,i) + s1.substring(i+1);
					int min3 = (DC + recEditDist(temp,s2,i,j));
					//Select the minimum
					return ((min1>min2)?((min2>min3)?min3:min2):((min1>min3)?min3:min1));
				}
			}
		}
		return 0; 
	}
	
	public static int recEditDistRef(String s1, String s2, int i, int j){
		System.out.println("i:"+i+" j:"+j);
		System.out.println(s1.length()+" "+s2.length());
		if((i==s1.length())&&(j==s2.length())){
			return 0;
		}
		int[] m = new int[4];
		for(int x=0;x<4;x++)
			m[x]=-1;
		if((i<s1.length())&&(j<s2.length())){
			if(s1.charAt(i)==s2.charAt(j))
				m[0]=recEditDistRef(s1,s2,i+1,j+1);
			m[1]=(4+recEditDistRef(s1,s2,i+1,j+1));
		}
		if(i<s1.length()){
			m[2]=(2+recEditDistRef(s1,s2,i+1,j));
		}
		if(j<s2.length()){
			m[3]=(3+recEditDistRef(s1,s2,i,j+1));
		}
		int min = 99999;
		for(int x: m){
			if((x!=-1)&&(x<min))
				min = x;
		}
		return min;
	}
	
	public static int dpEditDist(String s1, String s2){
		int n = s1.length();
		int m = s2.length();
		int[][] grid = new int[n+1][m+1];
		grid[n][m]=0;
		int[] minArr = new int[4];
		int min = 99999;
		int i=0,j=0;
		
		for(j=(m-1);j>=0;j--)
			grid[n][j]=(3+grid[n][j+1]);
		for(j=(n-1);j>=0;j--)
			grid[j][m]=(2+grid[j+1][m]);
		
		for(i=(n-1);i>=0;i--){
			for(j=(m-1);j>=0;j--){
				//System.out.println("i: "+i+" j:"+j);
				min = 99999;
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
					System.out.println("ANARTHHH HUI GAVAAAA");
				grid[i][j]=min;
			}
		}
		/*for(i=0;i<=n;i++){
			for(j=0;j<=m;j++){
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}*/
		return grid[0][0];
	}
			
	
	public static void main(String[] args){
		//System.out.println(recEditDist("C is a relatively 'low level' language.","Java is an object-oriented language.",0,0));
		//System.out.println(recEditDistRef("C is a relatively 'low level' language.","Java is an object-oriented language.",0,0));
		//System.out.println(dpEditDist("C is a relatively 'low level' language.","Java is an object-oriented language."));
		//System.out.println(recEditDistRef("kartik","kashes",0,0));
		//System.out.println(dpEditDist("tihs","aa"));
		//System.out.println(dpEditDist("electrical engineering","computer science"));
		System.out.println(recEditDistRef("tihs","aa",0,0));
		/*
		for(int i=0;i<=6;i++){
			for(int j=0;j<=6;j++){
				System.out.print(recEditDistRef("kartik","kashes",i,j)+" ");
			}
			System.out.println();
		}*/
		
	}
}