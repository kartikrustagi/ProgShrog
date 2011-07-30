import java.util.*;

class IncSequence{

	public static void main(String[] args){
		
		int[] A = {5,2,8,7,1,6,4};//Default array of numbers
		int len = A.length;
		int[][] T = new int[len][len]; //Table where each row is stroing longest sequence of numbers, each starting from a different number
		int[] L = new int[len]; //Each L[i] represent the lenth of sequence of numbers in Row T[i]
		int i = 0, j = 0;
		
		for(i = 0;i < len; i++){
			L[i] = 0;
		}
		
		for(i = 0;i < len; i++){
			j = 0;
			boolean flag = false;
			while(true){
			    if(j>=len){
					break;
				}
				if(L[j]>0){
					if(T[j][(L[j]-1)]<A[i]){ //-1 so as to offset the base from 1 to 0
						L[j]+=1;
						T[j][L[j]-1]=A[i];
						flag = false;
					}
				}else{
					//Reached a new row, lets check if we found added A[i] to any row till now
					if(!flag){
						//So we did not add A[i] to any row till now
						//L[j]==0
						T[j][L[j]]=A[i];
						L[j]+=1;
						break;
					}
				}
			}
			//Now we need to find the max of L[j] and the value of J for it
			int max=0, maxJ=0;
			for(j=0;i<len;i++){
				if(L[i]>max){
					max=L[j];
					maxJ=j;
				}
			}
			//Print longest sequence
			for(i=0;i<max;i++)
				System.out.println(T[maxJ][i]+" ");
				
		}
	}
}

			
		
		
		
		