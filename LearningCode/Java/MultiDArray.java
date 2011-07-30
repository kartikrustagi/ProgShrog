class MultiDArray{

    public static void takes(int[][] arr){
        System.out.println("Here");
    }

    public static void main(String[] args){
        /* Multi dimensional array m*n is like an array or pointers. Where M is the number of array pointers and n is the size of each array pointer. It is possible to have multiD array where meach of M array of pointers have different number of pointers. */
        int [][] arr;
        arr = new int[2][];
        takes(arr);
        //arr = new int[][2];
        arr[0]=new int[3];
    }
}