import java.io.*;
class ReverseLine{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int N = Integer.parseInt(br.readLine());
			int testCase = 1;
			String[] wordsArr;
			boolean start=true;
			int len;
			while(testCase<=N){
				wordsArr = br.readLine().split(" ");
				start = true;
				len = wordsArr.length;
				for(int i=(len-1);i>=0;i--){
					if(start){
						start = false;
						System.out.print("Case #"+testCase+":");
					}
					System.out.print(" ");
					System.out.print(wordsArr[i]);
				}
				System.out.print("\n");
				++testCase;
			}
		}catch(Exception ex){}
	}
}