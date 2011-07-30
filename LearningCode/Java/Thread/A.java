class A{
	public static void main(String[] args){
		System.out.println("A: Creating thread");
		Thread t = (new Thread(new B()));
		t.start();
		System.out.println("A: Going to sleep");
		try{
			Thread.sleep(4000);
		}catch(InterruptedException ex){
			System.out.println("A: Who may have caused this?");
		}
		/*
		System.out.println("A: Causing interrupt");
		t.interrupt();
		System.out.println("A: After interrupt");
		*/
		try{
			t.join();
		}catch(InterruptedException ex){
			System.out.println("A: Who may have caused this?");
		}
		System.out.println("A: After join");
	}
}