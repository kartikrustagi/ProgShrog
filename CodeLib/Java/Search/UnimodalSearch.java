class UnimodalSearch{
    public int search(int[] arr, int l, int h){
        if(l>h)
            return -1;
        else if(l==h)
            return l;
        else if(h==(l+1)){
            if(arr[l]>arr[h])
                return l;
            else
                return h;
        }
        int mid = (l+h)/2;
        //Check neighbours
        if((arr[mid]>arr[mid-1])&&(arr[mid]>arr[mid+1]))
            return mid;
        else if((arr[mid]>arr[mid+1])&&(arr[mid-1]>arr[mid]))
            return search(arr,l,mid-1);
        //else if((arr[mid]>arr[mid-1])&&(arr[mid+1]>arr[mid]))
        else    
            return search(arr,mid+1,h);
    }

    public int searchv2(int[] arr, int l, int h){
        if(l==h)
            return l;
        int mid=((l+h)/2);
        if(arr[mid]>arr[mid+1])
            return searchv2(arr,l,mid);
        else
            return searchv2(arr,mid+1,h);
    }
        
    public static void main(String[] args){
        int[] arr=/*{1,2,1};*/{1,2,3,6,5,4,3,2,1,0};
        System.out.println(new UnimodalSearch().searchv2(arr,0,arr.length-1));
    }
}


            

