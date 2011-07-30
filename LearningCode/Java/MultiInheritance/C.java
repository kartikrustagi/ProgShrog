public class C implements A,B{
    
    public void display(){
        System.out.println(x);
    }

    public static void main(String[] args){
        new C().display();
    }
}