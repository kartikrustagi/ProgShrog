class MergeSort
{
    public static void main(String[] str)
    {
        int[] arr={2,5,1,3,7,23,5,7,12,45,98}; //Array to be sorted
        //int[] arr={2,5,1,3};
        sort(arr,0,arr.length-1);
        printArr(arr,0,arr.length-1);
    }

    public static void sort(int[] arr,int l,int h)
    {
        System.out.println("In sort:  l:"+l+" h:"+h); 
        printArr(arr,l,h);
        if(l==h)
            //One element: sorted
            return;
        /*
        else if(h==(l+1))
        {
            //Two elements, compare and sort
            if(arr[l]>arr[h])
            {
                //Interchange
                arr[l]=arr[l]+arr[h];//TODO: what if max limits of array bypassed?
                arr[h]=arr[l]-arr[h];
                arr[l]=arr[l]-arr[h];
                //Replaced
            }
            return;
        }*/

        
        //Size of array is more than 1
        sort(arr,l,(l+h)/2);
        sort(arr,((l+h)/2)+1,h);
        merge(arr,l,(l+h)/2,h);
        System.out.print("Printing after sort");
        printArr(arr,l,h);

    }

    public static void printArr(int[] arr, int l, int h){
        for(int i=l;i<=h;i++)
            System.out.println(arr[i]);
        System.out.println("\n");
    }

    public static void merge(int[] arr,int l,int m,int h)
    {
        //arr1: [l,m]
        //arr2: [m+1,h]
        System.out.println("In merge:  l:"+l+" m:"+m+" h:"+h);
        printArr(arr,l,h);

        if(h==(l+1)){
            //Two elements, compare and sort
            if(arr[l]>arr[h]){
                //Interchange
                arr[l]=arr[l]+arr[h];//TODO: what if max limits of array bypassed?
                arr[h]=arr[l]-arr[h];
                arr[l]=arr[l]-arr[h];
                //Replaced
                }   
            return;
            }

        //More than two elements
        //Create an array
        int[] arrm = new int[h-l+1];
        int i=l,j=m+1,k=0;
        while((i<=m)&&(j<=h)){
            if(arr[i]<arr[j]){
                arrm[k++]=arr[i++];
            }
            else if(arr[i]>arr[j]){
                arrm[k++]=arr[j++];
            }
            else{
                //Both equal, Including repetition;
                arrm[k++]=arr[i++];
                arrm[k++]=arr[j++];
            }
        }
        System.out.println("\nIn merge: after half merge");
        printArr(arr,l,h);
        /*
        System.out.println("\n\n");
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        */
        //Now check if each subarray is exhausted or not
        while(i<=m){
            arrm[k++]=arr[i++];
        }
        while(j<=h){
            arrm[k++]=arr[j++];
        }
        System.out.println("\nIn merge: after full merge");
        printArr(arr,l,h);
        //Now fit in arrm in arr
        System.arraycopy(arrm,0,arr,l,(h-l+1));
    }
}

