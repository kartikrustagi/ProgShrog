class MatrixMulti{

    public static int[][] naiveMul(int[][] arr1, int[][] arr2){
        int i=0,j=0,k=0;
        //System.out.println(arr1.length);
        //System.out.println(arr1[0].length);
        int [][] arr3 = new int[arr1.length][arr2[0].length];
        for(i=0;i<arr1.length;i++){
            for(k=0;k<arr2[i].length;k++){
                arr3[i][k]=0;
                for(j=0;j<arr2.length;j++)
                    arr3[i][k]+=(arr1[i][j]*arr2[j][k]);
            }
        }
        return arr3;
    }

    public static void main(String[] args){
        int arr1[][] = {{1,2},{3,4}};
        int arr2[][] = {{4,3,5},{2,1,5}};
        int res[][] = naiveMul(arr1,arr2);
        PrintArr.print2d(res);
    }
}