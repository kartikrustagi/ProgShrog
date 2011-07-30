class PriorityQueues{

    private int maxKey(){
        return A[0];
    }

    private int maxKeyRemove(){
        int key = A[0];
        A[0]+=A[heapSize];
        A[heapSize]=(A[0]-A[heapSize]);
        A[0]-=A[heapSize];
        --heapSize;
        maxHeapify(0);
        return key;
    }

    private void increaseKey(int i, int key){
        if(key<=A[i]){
            System.out.println("New key is not greater than the original key. Returning");
            return;
        }
        A[i]=key;
        int p = (((i+1)/2)-1);
        while(p<=0){
            if(A[p]>A[i]){
                A[p]+=A[i];
                A[i]=(A[p]-A[i]);
                A[p]-=A[i];
                i=p;
                p=(((i+1)/2)-1);
            }
            else
                break;
        }
        printArr();
    }

    private void addKey(int key){
        ++heapSize;
        A[heapSize-1]=-99999;//Large negative value;
        increaseKey(heapSize-1,key);
        printArr();
    }
}
        

