class InToPost{
    private boolean precedenceOp(byte op1,byte op2){
        //Deal with '(','+','*'
        //op1 is from expression and op2 is from stack
        
        if(op2=='(')
            return false;
        if(op1=='(')
            return true;
        else if(op2=='(')
            return false;
        //In case of op1==')', just flush all till '('. DO this in the calling function it self
        else if(op1=='*')
           return true;
        else
           return false;
    }
    
    public String convert(String exp){
        String postFix="";
        int i=0;
        byte c='0';
        SimpleStack stack = new SimpleStack(exp.length(),'0');
        try{
        while(i<exp.length()){
            c=(byte)exp.charAt(i++);
            if(c>='0'&&c<='9')
                postFix+=c;
            else{
                //an operator 
                if(stack.isEmpty()){
                    stack.push(c);
                    continue;
                }
                else{
                    while((!stack.isEmpty())&&(!precedenceOp(c,(byte)stack.showTop()))){
                        postFix+=(byte)stack.pop();
                    }
                    stack.push(c);
                }
            }
        System.out.println(postFix);
        }
        while(!stack.isEmpty())
            postFix+=(byte)stack.pop();

        return postFix;
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    }


    public static void main(String[] args){
        String exp="1*2*3+4+5*6";
        InToPost itp = new InToPost();
        System.out.println(itp.convert(exp));
    }
        
}