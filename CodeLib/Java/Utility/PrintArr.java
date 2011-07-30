class PrintArr{

    public static void print2d(int[][] arr){
        int i=0,j=0;
        for(i=0;i<arr.length;i++){
            for(j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}