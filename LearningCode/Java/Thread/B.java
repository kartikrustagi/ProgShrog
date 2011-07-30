class B implements Runnable{
	public void run(){
		System.out.println("B: In run, starting loop");
		while(true){
			System.out.println("B: Inside while");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ex){
				System.out.println("B: Interrupt caused, breaking loop");
				break;
			}
		}
		System.out.println("B: Outside while loop");
	}
}