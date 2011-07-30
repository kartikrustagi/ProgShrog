class LongestIncSeq{

	public static void main(String[] args){
		int[] a = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int[] m = new int[a.length];
		int[] l = new int[a.length];
		for(int j=0;j<a.length;j++){
			if(j==0){
				m[j]=a[j];
				l[j]=1;
			}
			else{
				int minM = 1000;
				int maxL = -1;
				for(int k=(j-1);k>=0;k--){
					if(m[k]<a[j]){
						if((l[k]+1)>maxL){
							maxL = (l[k]+1);
							minM = a[j];
						}else if((l[k]+1)==maxL){
							if(a[j]<minM)
								minM = a[j];
						}
					}else{
						if(l[k]>maxL){
							maxL = l[k];
							minM = m[k];
						}
					}
				}
				l[j] = maxL;
				m[j] = minM;
			}
		}
		for(int i=0;i<a.length;i++){
			System.out.print(m[i]+" ");
		}
		System.out.print("\n");
		System.out.println(l[a.length-1]);
	}
}