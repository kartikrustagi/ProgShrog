class OrderK{
	public static int findOrder(int[] A, int p, int q, int k){
		System.out.println("p: "+p+"q: "+q+"k: "+k);
		if(p==q)
			return A[p];
		else if (q > p){
			int r = partition(A,p,q);
			if(r==k)
				return A[r];
			else if(k<r)
				return findOrder(A,p,(r-1),k);
			else
				return findOrder(A,(r+1),q,(k));//TODO: Check ir k-r or k-r-1
		}
		else
			return -1;//When will we end up here?
	}
	
	public static int partition(int[] A, int p, int q){
		System.out.println("p: "+p+"q: "+q);
		int pivot = A[p];
		int i = p;
		for(int j = i+1; j<=q;j++){
			if(A[j]<=pivot){
				i+=1;
				{
					int temp = A[i];
					A[i]=A[j];
					A[j]=temp;
				}
			}
		}
		{
			int temp = A[i];
			A[i]=A[p];
			A[p]=temp;
		}
		System.out.println("i: "+i);
		return i;
	}
	
	public static void main(String[] args){
		int[] arr = {6,10,13,5,8,3,2,11};
		System.out.println("Result: "+findOrder(arr,0,(arr.length-1),2));
	}
}