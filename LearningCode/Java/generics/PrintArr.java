class PrintArr{
    /*public void print(E[] arr){
        for(E e: arr){
            System.out.println(e);
        }
    }*/

    public static <E> void printStat(E[] arr){
        for(E e: arr){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        Integer[] arri={1,2,3};
        String[] arrs={"kartik","rustagi"};
        //PrintArr<String> pa = new PrintArr<String>();
        //pa.print(arrs);
        printStat(arri);//Array of primitive type wont do
        System.out.println(5);
    }
}
        
