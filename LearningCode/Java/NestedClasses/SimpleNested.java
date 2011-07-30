class SimpleNested{
    class Nested{
        public void go(){
            System.out.println("go");
        }
    }
    public static void main(String[] args){
        SimpleNested.Nested n = new SimpleNested().new Nested();
        n.go();
    }
}