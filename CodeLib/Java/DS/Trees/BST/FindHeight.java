class FindHeight{
	static int findHeight(BST.node x){
		if((x==null)||((x.left==null)&&(x.right==null)))
			return 0;
		return(1+BST.max(findHeight(x.left),findHeight(x.right)));
	}
	
	public static void main(String[] args){
		int[] arr = {5,1,6,2,9,3,6,5,144,98};
		BST tree  = new BST();
		for(int i: arr){
			tree.insert(i);
		}
		System.out.println("Height: "+ findHeight(tree.root));
	}
}
