class ConvexPoly{
    public static void main(String[] args){
        int[][] v = new int[7][2];
        //v[0][0] = 0;
        //v[0][1] = 1;
        //v[0] = new int[2];
        v[0][0] = 0;
        v[0][1] = 1;
        v[1][0] = 2;
        v[1][1] = -2;
        v[2][0] = 5;
        v[2][1] = -3;
        v[3][0] = 6;
        v[3][1] = -1;
        v[4][0] = 4;
        v[4][1] = 3;
        v[5][0] = 3;
        v[5][1] = 2;
        v[6][0] = 1;
        v[6][1] = 1;
        System.out.println("Max X: "+(new UnimodalSearch().searchv2(v,0,6)));
    }
}
