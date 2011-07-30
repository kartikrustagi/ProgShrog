class OneInstance{

    private OneInstance(){
    }
    
    static int i =0;

    public static OneInstance getInstance(){
        i++;
        if(i<=1){
            System.out.println("Mil gaya");
            return new OneInstance();
        }
        else{
            System.out.println("Thenga");
            return null;
        }
    }

    public static void main(String[] args){
        OneInstance o = OneInstance.getInstance();
        o= OneInstance.getInstance();
    }
}