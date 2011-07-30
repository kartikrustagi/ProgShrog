class BubbleSort{

    public static void sort(int[] arr){
        //In each iteration max element go to the end 
        int i=arr.length-1,j=0;
        boolean done=false;
    
        while((i>=1)&&!done){
            done=true;
            for(j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    done=false;
                    arr[j]=arr[j]+arr[j+1];
                    arr[j+1]=arr[j]-arr[j+1];
                    arr[j]=arr[j]-arr[j+1];
                }
            }
            printArr(arr);
            i--;
        }
    }

    public static void printArr(int[] arr){
        for(int i: arr)
            System.out.print(i);
        System.out.println();
    }

    public static void main(String[] args){
        int arr[]={6,1,5,2,4,3};
        sort(arr);
    }
}


