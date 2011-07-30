import java.math.BigInteger;

class StringHashCode{
    public static void main(String[] args){
        String F = "kartik";
        String M = "kartik";
        String L = "rustagi"; 
        BigInteger x = BigInteger.ONE.add(BigInteger.ONE);
        BigInteger y = BigInteger.ONE;
        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(F.hashCode());
        System.out.println(M.hashCode());
        System.out.println(L.hashCode());
    }
}