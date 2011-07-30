class Hanoi{

	public static int recSol3(int[] pegArr,char A, char B, char C,int l,int h){
		if(l==h){
			System.out.println("Move "+pegArr[l]+" from "+A+" to "+C); 
			return 1;
		}
		int x = recSol3(pegArr,A,C,B,l,h-1);
		int y = recSol3(pegArr,A,B,C,h,h);
		int z = recSol3(pegArr,B,A,C,l,h-1);
		return(x+y+z);
	}
	
	public static int dynSol3(int[] pegArr){
		int[] arr = new int[pegArr.length];
		arr[0]=1;
		for(int i=1;i<pegArr.length;i++){
			arr[i]=(arr[i-1]+1+arr[i-1]);
		}
		return(arr[pegArr.length-1]);
	}

	public static void main(String[] args){
		int[] pegArr = {1,2,3,4};
		//System.out.println(recSol3(pegArr,'A','B','C',0,3));
		System.out.println(dynSol3(pegArr));
	}
}