import java.math.BigInteger;

class BigInt1{
    public static void main(String[] args){
        BigInteger bi = ONE;
        BigInteger max = new BigInteger("10");
        System.out.println(max);
        //return;
        while(bi.compareTo(max)==-1){
            System.out.println(bi);
            bi=bi.add(BigInteger.ONE);
        }
    }
}
        