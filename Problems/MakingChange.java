class MakingChange{
	public static void main(String[] ars){
		int c = 10;
		//int[] v = {1,3,5,7,9,11,13};
		int[] v = {1,2,3,4};
		int[] m = new int[c];
		for(int i=0;i<c;i++){
			m[i] = 0;
		}
		m[0] = 1;
		for(int i = 1;i<c;i++){
			
			System.out.println("i: "+i);
			if((i+1) <= 4 && (i+1) >= 1){
				m[i] = 1;
				continue;
			}
				
			 int min = 9999999;
			 for(int j = (i-1);j>=0;j--){
				int req = (i-j);
				System.out.println("req: "+req+" ");
				if(req <= 4 && req >= 1){
					int temp = (m[j] + 1);
					if(temp<min){
						min = temp;
					}
				}
				System.out.println("min: "+ min+" ");
			 }
			 m[i] = min;
			 
		}
		System.out.println();
		for(int i=0; i<c;i++)
			System.out.println(m[i]);
	}
}