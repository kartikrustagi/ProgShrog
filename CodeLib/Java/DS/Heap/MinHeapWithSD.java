package DS.Heap;

public class MinHeapWithSD{
    public static class HeapWithSD{ /*Decide whether to make it public or package-private */
        int key;
        Object sd;
        public HeapWithSD(){
            key = 0;
            sd = null;
        }
        public HeapWithSD(k,sd){
            key = k;
            this.sd = sd;
        }
    }

    /* Methods to be defined: */
    /* 
       1. Extract-Min
       2. Return-Min: later
       3. Heap Minify
       4. DecreaseKey: Later
    */

    int heapSize;
    HeapWithSD[] heapArr;
    
    /*Constructors*/
    public MinHeapWithSD(){
        heapSize = 0;
        heapArr = null;
    }
    public MineHeapWithSD(int hs){
        heapSize = hs;
        heapArr = new heapWithSD[hs];
    }

    /*HeapMinify*/
    /*Attributes: Array index*/
    public void HeapMinify(int i){
        int l = (2*i + 1);
        int r = (2*i + 2);
        int min = 0;
        if((l<heapSize)&&(heapArr[l].key<heapArr[i].key)){
            min = l;
        }else{
            min = i;
        }
        if((r<heapSize)&&(heapArr[r].key<heapArr[min].key)){
            min = r;
        }
        if(min!=i){
            //Exchange min<->i
            swapNode(min,i);
            HeapMinify(min);
        }
        else{
            //Done
            return;
        }
    }

    public swapNode(int a, int b){
        //Index a and Index b
        HeapWithSD temp;
        temp = heapArr[a];
        heapArr[a] = heapArr[b];
        hearArr[b] = temp;
    }
    
    public ExtractMin(){
        if(heapSize<=0){
            raise Error;//Heap empty
            return;
        }
        HeapWithSD returnNode = heapArr[0];
        swapNode(0,(heapSize-1));
        --heapSize;
        minHeapify(0);
        return returnNode;
    }

    public DecreseKey(int i,int newKey){
        if(i>=heapSize){
            raise OverFlowError;
        }
        if(heapArr[i].key<newKey){
            //Do nothing
            return;
        }
        heapArr[i].key = newKey;
        int p = ((i-1)/2);
        while((p>=0)&&(heapArr[p].key<heapArr[i].key)){
            swapNode(p,i);
            i = p;
            p = ((i-1)/2);
        }
    }
        
}