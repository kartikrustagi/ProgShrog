class MaxSumSubsequence{
	public static void main(String[] args){
		int[] arr = {-1,-1,-10,4,5};
		int max_sum_so_far = 0;
		int max_sum_till_here = 0;
		int s = -1;
		int f = -1;
		int x  = 0;
		int temp = 0;
		for(int i=0; i<arr.length; i++){
			x = arr[i];
			temp = max_sum_till_here;
			max_sum_till_here = Math.max(0,(max_sum_till_here+x));
			max_sum_so_far = Math.max(max_sum_so_far,max_sum_till_here);
			if(max_sum_so_far == max_sum_till_here){
				if(max_sum_till_here == 0){
					s = -1;
					f = -1;
				}else{
					if(temp == 0){
						//In the previous loop max_sum_till_here was 0 but in this loop value of x was +ve and hence we have a new start
					}
					if(s==-1){
						s = i;
						f = i;
					}else{
						f++;
					}
				}
			}
		}
		System.out.println(max_sum_so_far);
		System.out.println(s+" to "+f);
	}
}