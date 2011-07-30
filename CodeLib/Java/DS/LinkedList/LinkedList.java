class LinkedList{
	class node{
		int data;
		node next;
	}
	node head;
	
	public void insert(int obj){
		node p = new node();
		p.data = obj;
		p.next = head;
		head = p;
	}
	
	public node search(int key){
		node p=null;
		for(p=head;((p!=null)&&(p.data==key));p=p.next); /*TODO: Equality condition is poorly defined*/
		return p;
	}
	
	public void delete(int key){
		//Delete all instances of key
		node q = head;
		if(head==null)
			return;
		System.out.println("In delete, head: "+head.data);
		for(node p=head;(p!=null);){
			if(p.data == key){
				if(p==head){
					head = p.next;
					p.next=null;
					p = head;
					q = head;
				}else{
					q.next = p.next;
					p.next = null;
					p = q.next;
				}
			}else{
				q = p;
				p = p.next;
			}
			printList();
		}
	}
	
	public void printList(){
		for(node p=head;(p!=null);p = p.next){
			System.out.print(p.data+" => ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		int[] arr = {14};
		int[] arr2 = {5,4,3,2,1};
		LinkedList list = new LinkedList();
		for(int i: arr2){
			list.insert(i);
			list.printList();
		}
		for(int i: arr){
			list.delete(i);
			list.printList();
		}
	}
}