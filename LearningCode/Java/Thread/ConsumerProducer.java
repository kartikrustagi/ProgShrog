class SharedObj{
	boolean empty;
	String message;
	
	SharedObj(){
		empty = true;
	}
	
	public synchronized void produce(){
		String[] msgs = {"a","b","c","d"};
		for(String s: msgs){
			while(!empty){
				try{
					//System.out.println("Producer: wait");
					wait();
				}catch(InterruptedException ex){
					//System.out.println("Producer: After wait");
				}
			}
			try{
				//System.out.println("Producer: Sleep");
				Thread.sleep(2000);
			}catch(Exception ex){
				//System.out.println("Producer: Ye kisnai kiya");
			}
			System.out.println("Producer: "+s);
			message = s;
			empty = false;
			notifyAll();
		}
	}

	public synchronized void consume(){	
		//System.out.println("Consumer: ");
		for(int i=0; i<4; i++){
			while(empty){
				try{
					wait();
				}catch(InterruptedException ex){
				}
			}
			System.out.println("Consumer: "+message);
			try{
				Thread.sleep(2000);
			}catch(Exception ex){
				//System.out.println("Consumer: Ye kisnai kiya");
			}
			empty = true;;
			notifyAll();
		}
	}

}


class Consumer implements Runnable{
	SharedObj so;
	
	Consumer(SharedObj so){
		this.so = so;
	}
	
	public void run(){
		so.consume();
	}
}

class Producer implements Runnable{
	SharedObj so;
	
	Producer(SharedObj so){
		this.so = so;
	}
	
	public void run(){
		so.produce();
	}
}

class ConsumerProducer{
	public static void main(String[] args){
		SharedObj so = new SharedObj();
		new Thread(new Producer(so)).start();
		new Thread(new Consumer(so)).start();
	}
}