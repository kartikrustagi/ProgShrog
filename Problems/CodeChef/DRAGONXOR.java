import java.io.*;
import java.util.*;

class Main{

    public static int numOfSetBits(int x){
        int num = 0;
        while(x!=0){
            if((x&1)==1){
                ++num;
            }
            x=(x>>1);
        }
        return num;
    }

    public static int findNum(int N, int zeroes){
        int num=0;
        for(int i=zeroes;i<N;i++){
            num += Math.pow(2,i);
        }
        return num;
    }

	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String temp = br.readLine();
			int T = Integer.parseInt(temp);
            int N=0;
            int A=0;
            int B=0;
            
            while(T>0){    
                temp = br.readLine();
                String[] para = temp.split(" ");
                N = Integer.parseInt(para[0]);
                A = Integer.parseInt(para[1]);
                B = Integer.parseInt(para[2]);
                int a = numOfSetBits(A);
                int b = numOfSetBits(B);
                if((a+b)>=N){
                    int zeroes = ((a+b)-N);
                    System.out.print(findNum(N,zeroes)+"\n");
                }else{
                    int zeroes = (N-(a+b));
                    System.out.print(findNum(N,zeroes)+"\n");
                }
                --T;
            }
			
		}catch(Exception e){
			e.printStackTrace();
        }
    }

}