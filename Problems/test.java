class test{
	public static void print(int[] arr){
		arr[1] = 5;
	}
	public static void main(String[] args){
		int[] arr = {1,2,3};
		System.out.println(arr[1]);
		print(arr);
		System.out.println(arr[1]);
	}

}