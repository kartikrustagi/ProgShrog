import java.utils.ArrayList;

class WeightedMedian{

	int partition(float[] A,int p, int q)
	{
		if(p==q)
			return p;
		else if(q > p){
			float pivot = A[p];
			int i=p, j=(i+1);
			float temp;
			while(j<=q){
				if(arr[j] >= pivot){
					i+=1;
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
				j++;
			}
			temp = arr[i];
			arr[i] = arr[p];
			arr[p] = arr[i];
			return i;
		}
		return -1;//Make sure we don't reach here
	}
	
	float median(float[] A, int p, int q){
		
		if(p==q)
			return p;
		else if (p<q){
			int m = partition(A,p,q);
			if(m==(A.length/2)){
				return A[m];
			}
			else if(m>(A.length/2)){
				return median(A, p , m-1);
			}
			else{
				return median(A, m+1, q);
			}
		}
		
	}
	
	float weightedMedian(ArrayList<Float>[] A, float carryW){
		if(A.length==1)
			return A[0];
		float m = median(A);
		ArrayList<Float> B = new ArrayList<Float>(); //Less than median
		ArrayList<Float> C = new ArrayList<Float>(); //More than median
		float weightB=0.0;
		
		for(int i=0;i<A.length();i++){
			float temp = A.get(i);
			if(temp<m){
				B.add(temp);
				weightB+=temp;
			}
			else{
				C.add(temp);
			}
		}
		
		if((carryW+weightB)>(0.5)){
			return weightedMedian(B,carryW);
		}
		else{
			return weightedMedian(C,(carryW+weightB));
		}
	}
	
	public static void main(String[] args){
		float[] A = [];
		System.out.println(weightedMedian(A,0));
	}
}