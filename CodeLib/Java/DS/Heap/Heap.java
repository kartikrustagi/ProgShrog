public class Heap{
	/* functions 
		* Max-Heapify
		* Build-max-heap
		* Heap sort
	*/
	
	int[] heapArr;
	int n; //HeapSize
	
	Heap(int n, int[] arr){
		this.n = n;
		this.heapArr = arr;
	}
	
	Heap(int n){
		this.n = n;
	}
	
	void maxHeapify(int index){
		int left = (2*index+1); //TODO: Why?
		int right = left+1;
		int largest = index;
		if((left<n)&&(heapArr[left]>heapArr[largest]))
			largest=left;
		if((right<n)&&(heapArr[right]>heapArr[largest]))
			largest=right;
		if(largest!=index){
			int temp = heapArr[largest];
			heapArr[largest] = heapArr[index];
			heapArr[index] = temp;
			maxHeapify(largest);
		}
	}
	
	void minHeapify(int index){
		int left = (2*index+1); //TODO: Why?
		int right = left+1;
		int smallest = index;
		if((left<n)&&(heapArr[left]<heapArr[smallest]))
			smallest=left;
		if((right<n)&&(heapArr[right]<heapArr[smallest]))
			smallest=right;
		if(smallest!=index){
			int temp = heapArr[smallest];
			heapArr[smallest] = heapArr[index];
			heapArr[index] = temp;
			maxHeapify(smallest);
		}
	}
	
	void buildMaxHeap(){
		//Non leaf nodes start at ((n/2)-1) where n is the number of nodes (heap-size)
		for(int i=((n/2)-1); i>=0; i--){
			maxHeapify(i);
		}
		
	}
	
	void buildMinHeap(){
		//Non leaf nodes start at ((n/2)-1) where n is the number of nodes (heap-size)
		for(int i=((n/2)-1); i>=0; i--){
			minHeapify(i);
		}
		
	}
	
	int[] heapSort(){
		buildMaxHeap();
		printArr(heapArr);
		int[] sortedArr = new int[n];
		int i;
		for(i=(n-1);i>=1;i--){
			//Swap 1 and i, put i in sortedArr, reduce n
			//System.out.println("Adding "+heapArr[1]+" at "+i); 
			sortedArr[i]=heapArr[0];
			heapArr[0]=heapArr[i];
			--n; 
			maxHeapify(0);
			printArr(heapArr);
		}
		//System.out.println("Value of i (which should be 0): "+i);
		sortedArr[i]=heapArr[i]; //i should be 0 here
		return sortedArr;
	}
	
	public static void printArr(int[] arr){
		for(int i: arr){
			System.out.print(i+" ");
		}
		System.out.print("\n");
	}
	
	public void printArr(){
		for(int i: heapArr){
			System.out.print(i+" ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args){
		int[] arr = {1,10,2,9,3,4,8,7,5,6};
		printArr(arr);
		Heap h = new Heap(arr.length,arr);
		h.printArr(h.heapSort());
	}

}