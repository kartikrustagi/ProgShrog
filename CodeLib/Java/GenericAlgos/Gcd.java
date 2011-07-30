//GCD

class Gcd{
	
	public static long gcdEuc(long a, long b){
		if((a==0)||(b==0)){
			return ((a==0)?b:a);
		}
		if((a==1)||(b==1)){
			return 1;
		}
		if(a>b){
			return gcdEuc((a-b),b);
		}
		return gcdEuc(a, (b-a));
	}

	public static void main(String args[]){
		System.out.println(gcdEuc(100,150));
	}	


}