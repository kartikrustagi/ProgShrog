class PowerRec{

    public static int power(int x, int n){
        if(n==1)
            return x;

        if((n%2)==0){
            int temp=power(x,n/2);
            return temp*temp;
        }else{
            int temp=power(x,n/2);
            return temp*temp*x;
        }
    }

    public static void main(String[] args){
        System.out.println(power(2,4));
    }
}