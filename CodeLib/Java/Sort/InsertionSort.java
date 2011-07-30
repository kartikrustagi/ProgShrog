class Insertion{

    public void SortIncreasing(int[] arr){
        int key=0;
        int i=0;
        for(int j=1;j<arr.length;j++){
            key = arr[j];
            i = j-1;
            while(i>=0&&key<arr[i]){//key>arr[i] for decreasing order
                arr[i+1]=arr[i];
                i--;
            }
            arr[i+1]=key;
        }
        for(int n:arr){
            System.out.println(n);
        }

    }

    public static void main(String[] args){
        int[] arr={1,6,2,5,3,4};
        new Insertion().SortIncreasing(arr);
    }
}


            