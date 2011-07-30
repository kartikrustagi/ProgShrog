class BinarySearch{

    public static int search(int[] arr, int l, int h, int x){
       
        System.out.println("In search l:"+l+" h:"+h+" x:"+x+" arr[l]:"+arr[l]);
        if(l==h){
            if(arr[l]==x)
                return l;
            else
                return -1;
        }

        int mid = (l+h)/2;
        if(x>arr[mid])
            return search(arr,mid+1,h,x);
        else if(x<arr[mid])
            return search(arr,l,mid-1,x);
        else
            return mid;
    }
            
    public static void main(String[] args){
        int arr[] = {1,2,3,4,5,6};
        System.out.println(search(arr,0,arr.length-1,2));
    }
}


