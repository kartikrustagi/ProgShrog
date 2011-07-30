class Queue{
	int size=100;
	Object[] queueArr;
	int tail=0;//Tail will point to a location before, where we need to delete an element. ie initially it will be 1 position behind. When removing we will do increment and then remove
	int head=0;//Head will always point to the position which contains the last element. We will first check for overflow(by incrementing head and then checking if it is equal to tail). And if no increment, then we will do insert
	
	//In this scheme we will use only size-1 elements of the queueArr
	
	Queue(){
		setArr();
	}
	
	private void setArr(){
		queueArr = new Object[size];
	}
	
	Queue(int n){
		size = n;
		setArr();	
	}
	
	private boolean isEmpty(){
		return (tail == head);
	}
	
	private boolean isOverFlow(){
		if(head == (size-1)){
			//Incrementing head will bring it to 0th position
			return (0 == tail);
		}else{
			return ((head+1)==tail);
		}
	}
	
	boolean insert(Object obj){
		if(isOverFlow()){
			return false;
		}
		if(head == (size-1)){
			//Incrementing head will bring it to 0th position
			head=0;
		}else{
			head++;
		}
		queueArr[head] = obj;
		return true;
	}
	
	Object remove(){
		if(isEmpty()){
			return null;
		}
		if(tail == (size-1)){
			//Incrementing head will bring it to 0th position
			tail=0;
		}else{
			tail++;
		}
		return queueArr[tail];
	}
	
	public static void main(String[] args){
	}
}