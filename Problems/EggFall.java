class EggFall{
	public static int recSol(int n){
		//System.out.println(n);
		if(n==1){
			return 0;
		}
		
		int min = 999999;
		int temp=0;
		for(int k=1;k<n;k++){
			temp=((n/k)+recSol(k));
			if(temp<min)
				min=temp;
		}
		return min;		
	}
	
	public static int recSol(int n,int e){
		//e eggs
		//System.out.println(n);
		if(e==1){
			return n;
		}
		
		if(n==1){
			return 0;
		}
		
		int min = 999999;
		int temp=0;
		for(int k=1;k<n;k++){
			temp=((n/k)+recSol(k));
			if(temp<min)
				min=temp;
		}
		return min;		
	}
	
	public static int dynSol(int n){
		int[] arr = new int[n];
		arr[0]=0; //k==1
		for(int k=2;k<=n;k++){
			int min = 999999;
			int temp=0;
			for(int x=1;x<k;x++){
				temp=((k/x)+arr[x-1]);
				if(temp<min)
					min=temp;
			}
			arr[k-1]=min;
			System.out.println(k+": "+arr[k-1]);
		}
		return arr[n-1];
	}
	
	public static void main(String[] args){
		System.out.println("ans:"+recSol(100));
	}
}
