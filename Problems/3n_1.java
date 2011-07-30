/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;
import java.math.*;

class Main implements Runnable{
    static String ReadLn(int maxLength){  // utility function to read from stdin,
                                          // Provided by Programming-challenges, edit for style only
        byte line[] = new byte [maxLength];
        int length = 0;
        int input = -1;
        try{
            while (length < maxLength){//Read untill maxlength
                input = System.in.read();
                if ((input < 0) || (input == '\n')||(input == 13)) break; //or until end of line input
                line [length++] += input;
            }

            if ((input < 0) && (length == 0)) return null;  // eof
            return new String(line, 0, length);
        }catch (IOException e){
            return null;
        }
    }
	
    public static void main(String args[])  // entry point from OS
    {
        Main myWork = new Main();  // Construct the bootloader
        myWork.run();            // execute
    }

    public void run() {
        new myStuff().run();
    }
}
class myStuff implements Runnable{

	static int cl=0;
	static BigInteger TWO;
	static BigInteger THREE;
	static{
		TWO=BigInteger.ONE.add(BigInteger.ONE);
		THREE=TWO.add(BigInteger.ONE);
	}

	public static int[] strToIntArr(String inStr){
		if(inStr==null)
			return null;
	
		String[] inputArr=null;
		
		inputArr=inStr.split(" ");//TODO: Make this whitespace generic whitespace using regex
		int[] inputIntArr = new int[inputArr.length];
		
		for(int i=0;i<inputArr.length;i++){
			inputIntArr[i] = Integer.parseInt(inputArr[i],10);
		}
		return inputIntArr;
	}
	
	public static BigInteger[] strToBigIntArr(String inStr){
		if(inStr==null)
			return null;
	
		String[] inputArr=null;
		//System.out.println("Here");
		inputArr=inStr.split(" ");//TODO: Make this whitespace generic whitespace using regex
		BigInteger[] inputBigIntArr = new BigInteger[inputArr.length];
		
		for(int i=0;i<inputArr.length;i++){
			//System.out.println(inputArr[i]);
			inputBigIntArr[i] = new BigInteger(inputArr[i],10);
		}
		return inputBigIntArr;
	}
	
	public void cycle(int n){
		cl++;
		//System.out.println(n);
		if(n==1)
			return;
		else if((n%2)==0)
			cycle(n/2);
		else
			cycle(3*n+1);
			//cycle(2*1*(n/2)+1+(n/2)+1);//2*x*y+x+y+1
	}	
	
	public void cycleBI(BigInteger n){
		cl++;
		//System.out.println(n);
		BigInteger[] tempArr = n.divideAndRemainder(TWO);
		if(n.compareTo(BigInteger.ONE)==0)
			return;
		else if((tempArr[1].compareTo(BigInteger.ZERO))==0)
			cycleBI(tempArr[0]);
		else
			cycleBI(THREE.multiply(n).add(BigInteger.ONE));
			//cycle(2*1*(n/2)+1+(n/2)+1);//2*x*y+x+y+1
	}
	
	public int giveMaxCycle(int i, int j){
		int max = 1;
		int ul, ll;
		if(i>j){
			ul=i;
			ll=j;
		}else{
			ul=j;
			ll=i;
		}

		for(int x=ll; x<=ul;x++){
			cl = 0;
			cycle(x);
			if(cl>max)
				max=cl;
		}
		return max;
	}
	
	public int giveMaxCycleBI(BigInteger i, BigInteger j){
		int max = 1;
		BigInteger ul=null, ll=null;
		if(i.compareTo(j)==1){
			ul=i;
			ll=j;
		}else{
			ul=j;
			ll=i;
		}
		//System.out.println("In giveMax");
		for(BigInteger x=ll; ((x.compareTo(ul)==0)||(x.compareTo(ul)==-1));){
			cl = 0;
			//System.out.println("Calling Cycle: "+x);
			cycleBI(x);
			if(cl>max)
				max=cl;
			//System.out.println("Max:"+max+" for X:"+x);
			x=x.add(BigInteger.ONE);
		}
		return max;
	}
	
	
    public void run(){
        Main M = new Main();
		String inputStr=null;
		BigInteger[] inputBigIntArr=null;
	
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line=null;
			while((inputStr=br.readLine())!=null){
				inputBigIntArr=strToBigIntArr(inputStr);
				//System.out.println("In run: after strToBigIntArr"+inputBigIntArr);//TODO: Debug
				System.out.println(inputBigIntArr[0]+" "+inputBigIntArr[1]+" "+giveMaxCycleBI(inputBigIntArr[0],inputBigIntArr[1]));
			}
		}catch(Exception e){}
	}
	/*
	public void run(){
        Main M = new Main();
		String inputStr=null;
		BigInteger[] inputBigIntArr=null;
	
		try{
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line=null;
			while((line=Main.ReadLn(20))!=null){
				//System.out.println(line);
				inputBigIntArr=strToBigIntArr(line);
				//System.out.println("In run: after strToBigIntArr"+inputBigIntArr);//TODO: Debug
				System.out.print(inputBigIntArr[0]+" "+inputBigIntArr[1]+" "+giveMaxCycleBI(inputBigIntArr[0],inputBigIntArr[1]));
			}
		}catch(Exception e){}
	}*/	
}