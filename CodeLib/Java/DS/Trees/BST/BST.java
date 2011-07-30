class BST{
	
	static class node{
		node parent;
		node left;
		node right;
		int key;
		node(){
			parent=null;
			left=null;
			right=null;
			key=0;
		}
	}
	
	node root;
	
	BST(){
		root=null;
	}
	
	node search(int k){
		node x = root;
		while(x!=null){
			if(x.key==k){
				return x;
			}
			if(k>=x.key)
				x=x.right;
			else
				x=x.left;
		}
		return null;
	}
	
	/*
	void inorderNonRecUsingStack(){
		node p = root;
		Stack s = new Stack();
		while((p!=null)||(!s.empty())){
			if(p==null){
				p = s.pop();
				System.out.println(p.key+" ");
				p = p.right;
			}else{
				s.push(p);
				p = p.left;
			}
		}
	}
	*/
	
	/*
	void inorderNonRec(){
		node cursor = root;
		node prev = null;
		while(cursor!=null){
			//temp = cursor;
			if(prev==cursor.parent){
				if(cursor!=null){}
			}
		}
	}
	*/
	
	void insert(int k){
		node x = root;
		node y = null;
		while(x != null){
			y=x;
			if(k >= x.key)
				x = x.right;
			else
				x = x.left;
		}
		x = new node();
		x.key = k;
		x.parent = y;
		if(root==null){
			root=x;
		}else{
			if(k>=y.key){
				y.right = x;
			}else{
				y.left = x;
			}
		}
	}

	node minimum(node x){
		while(x.left!=null){
			x=x.left;
		}
		return x;
	}
	
	
	static int max(int x, int y){
		return ((x>y)?x:y);
	}
	
	
	
	//Recursive inorder traversal
	void recInorder(node x){
		if(x==null)
			return;
		recInorder(x.left);
		System.out.print(x.key+" ");
		recInorder(x.right);
	}
	
	node successor(node x){
		if(x.right!=null)
			return minimum(x.right);
		node y = x.parent;
		while((y!=null)&&(y.right==x)){
			x=y;
			y=x.parent;
		}
		return y;
	}
	
	void printPaths(node x,String pathPrefix){
		if(x != root)
			pathPrefix += "=>";
		
		pathPrefix += x.key;
		if((x.left == null) && (x.right == null)){
			System.out.println(pathPrefix);
			return;
		}
		if(x.left != null)
			printPaths(x.left, pathPrefix);
		if(x.right != null)
			printPaths(x.right, pathPrefix);
	}
	
	void deleteNode(node x){
		boolean lChild = (x.left!=null);
		boolean rChild = (x.right!=null);
		if(!lChild&&!rChild){
			System.out.println("Case 1");
			if(x.parent!=null){
				if(x==x.parent.left)
					x.parent.left=null;
				else
					x.parent.right=null;
			}else{
				root=null;
			}
			x=null;
		}else if((lChild||rChild)&&!(lChild&&rChild)){
			System.out.println("Case 2");
			if(x.parent!=null){
				if(x==x.parent.left)
					x.parent.left=(lChild?x.left:x.right);
				else
					x.parent.right=(lChild?x.left:x.right);
			}else{
				//x is root
				if(lChild){
					root = x.left;
				}else{
					root = x.right;
				}
			}
			if(lChild)
				x.left.parent=x.parent;
			else
				x.right.parent=x.parent;
			x=null;
		}else{
			//lChild&&rChild
			System.out.println("Case 3");
			node succ = successor(x);
			x.key=succ.key;
			if(succ.right!=null){
				succ.right.parent=succ.parent;
			}
			//The below should hold true even in the case when succ.right==null
			if(succ==succ.parent.right)
				succ.parent.right=succ.right;
			else
					succ.parent.left=succ.right;

			succ=null;
		}
	}
	
	public static void main(String[] args){
		//int[] arr = {1,10,2,9,3,8,4,7,5,6};
		int[] arr = {5,1,6,2,9,3,6,5,144,98};
		//int[] arr= {2,1,3};
		BST tree = new BST();
		for(int i: arr){
			tree.insert(i);
			//System.out.println("Insert: "+i);
			//tree.recInorder(tree.root);
			//System.out.println();
		}
		
		tree.printPaths(tree.root, "");
		//System.out.println("Minimum: "+tree.minimum(tree.root).key);
		//System.out.println("Successor: "+tree.successor(tree.root).key);
		/*
		for(int i: arr){
			node temp = tree.search(i);
			tree.deleteNode(temp);
			if(temp==null)
				System.out.println("Search null kyu dai raha hai yaar");
			System.out.println("Delete: "+temp.key);
			tree.recInorder(tree.root);
			System.out.println();
		}*/
	}

}