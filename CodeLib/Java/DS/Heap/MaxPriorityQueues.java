class MaxPriorityQueues{

	Heap h;
	
    MaxPriorityQueues(int n){
		h = new Heap(n);
	}
	
	private int maxKey(){
        return h.heapArr[0];
    }

    private int maxKeyRemove(){
        int key = h.heapArr[0];
        h.heapArr[0] = h.heapArr[h.n-1];
        --h.n;
        h.maxHeapify(0);
        return key;
    }

    private void increaseKey(int i, int key){
        if(key<=h.heapArr[i]){
            System.out.println("New key is not greater than the original key. Returning");
            return;
        }
        h.heapArr[i]=key;
        int p = (((i+1)/2)-1);
        while(p<=0){
            if(h.heapArr[p]>h.heapArr[i]){
                h.heapArr[p]+=h.heapArr[i];
                h.heapArr[i]=(h.heapArr[p]-h.heapArr[i]);
                h.heapArr[p]-=h.heapArr[i];
                i=p;
                p=(((i+1)/2)-1);
            }
            else
                break;
        }
        h.printArr(h.heapArr);
    }

    private void addKey(int key){
        ++h.n;
        h.heapArr[h.n-1]=-99999;//Large negative value;
        increaseKey(h.n-1,key);
        h.printArr();
    }
}
        

