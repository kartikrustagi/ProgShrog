import java.io.*;

class Main{

    public static long numRotation(int L, int M, int P){
        //System.out.println("L:"+L+" M:"+M+" P:"+P);
        if((M==0)&&(P==0)){
            //System.out.println("zero");
            return 0;
        }
        //If the 1st rotation it self takes care of things
        if((M%L)==0){
            return 1;
        }

        //aL = x(M+P)+M
        long left = L;
        long netRot = (M+P);
        while(true){
            long rem = (left%netRot);
            if((rem==0)||(rem==M)){
                //System.out.println("Returning after calc");
                return ((2*(left/netRot))+((rem==0)?0:1));
            }
            left += L;
        }
    }
   
    public static int subStrConst(String str){
        for(int i=1;i<=(str.length()/2);i++){
            String regex = ("("+str.substring(0,i)+")+");
            //System.out.println(regex);
            if(str.matches(regex)){
                return (i);
            }
        }
        return str.length();
    }

	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(br.readLine());
            int M=0;
            int P=0;
            String S;
            String[] para;

            while(T>0){    
                S = br.readLine();
                para = br.readLine().split(" ");
                M = Integer.parseInt(para[0]);
                P = Integer.parseInt(para[1]);
                int L=0;
                /*
                if((S.length()>1)&&((S.length()%2)==0)){
                    //Chances of two equal parts of the string
                    if(S.substring(0,(S.length()/2)).equals(S.substring(S.length()/2))){
                        //System.out.println("Equal subparts");
                        L=(S.length()/2);
                    }else{
                        L=S.length();
                    }
                }else{
                    L=S.length();
                }
                */
                System.out.print(numRotation(subStrConst(S),M,P)+"\n");
                --T;
            }
			
		}catch(Exception e){
			e.printStackTrace();
        }
    }
}
