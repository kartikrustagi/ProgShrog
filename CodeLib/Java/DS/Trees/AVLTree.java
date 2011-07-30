class AVLTree{
	
	public static class AVLNode{
		int k;//key;
		AVLNode l;
		AVLNode r;
		AVLNode p;
		int b;
		AVLNode(){
			k=0;
			b=0;
			l=r=p=null;
		}
	}
	
	AVLNode root;
	
	AVLTree(){
		root=null;
	}
	
	void insert(int key){
		//Do a BST Insert, keep track of ya which can get unbalanced
		AVLNode p = root;
		AVLNode q = null;
		AVLNode ya = null;
		
		//Look for correct position of p
		while(p!=null){
			q=p;
			if(key>p.k){
				p=p.r;
			}else{
				p=p.l;
			}
			if((q.b == -1) || (q.b  == 1)){
				//Then q can be ya to be unbalanced
				ya = q;
			}
		}
		
		/* If tree was null:
			p == null
			q == null
			ya == null
		*/
		
		//insert
		p = new AVLTree.AVLNode();
		p.k = key;
		
		//TODO: Fails for q == null, DONE
		if(root == null){
			root = p;
			//TODO: We are done, time to leave
			return;
		}
		else{
			if(key>q.k){
				q.r = p;
			}else{
				q.l = p;
			}
		}
		p.p = q;
			
		//All nodes between ya and p have b==0 (not including ya and p)
		//Set new b values of all nodes between ya and p
		
		//TODO: ya == null?, DONE  
		if( ya == null){
			q = root; //All elements from root to p are b == 0  elements and needs reassigning of balances
		}else{
			q = ((p.k>ya.k)?ya.r:ya.l);
		}
		while(q!=p){
			if(p.k>q.k){
				q.b -= 1;
				q = q.r;
			}else{
				q.b += 1;
				q = q.l;
			}
		}
		
		//Now check if balance of ya, and see if roatation is required or not
		
		if(ya == null){
			//In this case, all we needed was reassigning of balances value, which we did in the last run
			return;
		}
		
		if(p.k>ya.k){
			//p added in right subtree, and since initially all nodes between ya and p were balanced therefore it eill impack balance of ya
			//will increase balance of -1
			
			//TODO: Case where 
			if(ya.b == -1){
				//Rotation/s required
				//Right tilted
				if(ya.r.b == -1){
					//Only one left rotation on ya;
					System.out.println();
				}else{
					//Right rotation on ya.r followed by left rotation on ya
					System.out.println();
				}				
			}
			if(ya.b == 1){
				ya.b = 0;
				//Done
			}
		}else{
			//p added in left subtree, and since initially all nodes between ya and p were balanced therefore it eill impack balance of ya
				//will increase balance of +1
				if(ya.b == +1){
					//Rotation/s required
					if(ya.r.b == +1){
						//Only one right rotation on ya;
					}else{
						//Left rotation on ya.r followed by right rotation on ya
					}	
				}
				if(ya.b == -1){
					ya.b = 0;
					//Done
				}
		}
		
	}
	
	void leftRoatation (AVLNode p){
		if(p.r == null){
			//TODO: Who asked to do a left rotation on p?
		}
		AVLNode parent = p.p; //TODO: take care incase when p == root
		AVLNode right = p.r;
		right.p = parent;
		if(parent!=null){
			if(parent.r==p)
				parent.r = right;
			else
				parent.l = right;
		}
		p.p = right;
		if(right.l != null){
			right.l.p = p;
			p.r = right.l;
			right.l = null;
		}
		right.l = p;
	}
	
	void rightRoatation (AVLNode p){
		if(p.l == null){
			//TODO: Who asked to do a left rotation on p?
		}
		AVLNode parent = p.p; //TODO: take care incase when p == root
		AVLNode left = p.l;
		left.p = parent;
		if(parent!=null){
			if(parent.r==p)
				parent.r = left;
			else
				parent.l = left;
		}
		p.p = left;
		if(left.r != null){
			left.r.p = p;
			p.l = left.r;
			left.r = null;
		}
		left.r = p;
	}
	
	void delete(int key){
	}
	
}