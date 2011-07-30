class ParanthesisCount{
    
    public static int recCount(String exp){
        int len = exp.length();
        if(len==1)
            return (1);
        int res = 0;
        res++;
        //Now recursively call on two substring: left and right. Left starting from one element
        int i = 1;
        String leftSubString, rightSubString;
        for(;i<=(len-1);i++){
            leftSubString = exp.substring(0,i);
            rightSubString = exp.substring(i,exp.length());
            //System.out.println(leftSubString + "    " + rightSubString);
            res += (recCount(leftSubString)+recCount(rightSubString));
        }
        return res;
    }

    public static void main(String[] args){
        String exp = "ka";
        System.out.println(recCount(exp));
    }
}