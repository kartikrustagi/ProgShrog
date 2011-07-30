class NumberReversal{
	
	static int printReverse(int num){
		boolean neg = false;
		
		if(num<0){
			neg = true;
			num *= -1;
		}
		
		int revNum = 0;
		int div = 1;
		while(num!=0){
			revNum = (revNum*10)+(num%10);
			System.out.println(num+": "+(num%10));
			num = (num/10);
		}
		
		if(neg)
			revNum *= -1;
		
		return revNum;
	}
	
	public static void main(String[] args){
		System.out.println(printReverse(Integer.parseInt(args[0])));
	}

}