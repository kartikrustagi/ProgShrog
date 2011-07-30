class SimpleThread{
    public static void main(String[] args){
        PrintName p = new PrintName();
        Thread t = new Thread(p);
        t.run();
        t.start();
        System.out.println("In main");
    }
}
