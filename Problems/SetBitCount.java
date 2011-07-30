class SetBitCount{
	public static void main(String[] args){
		int i = 1;
		int c = 0;
		while(i!=0){
			if((i&1) == 1){
				++c;
			}
			i = i>>1;
		}
		System.out.println(c);
	}
}