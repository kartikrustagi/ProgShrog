class StaticVar{
    public static int i;
    public static void main(String[] args){
        while(true){
            System.out.println(i++);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
            }
        }
    }
}
