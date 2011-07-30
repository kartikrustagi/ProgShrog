class SimpleStack
{
	//TODO: Generic SimpleStack for int/char etc
	int pos=-1;
	int maxSize;
	int[] stackArr ;//=  new int[maxSize];
    int def;

	SimpleStack(int size,int def)
	{
		maxSize = size;
        this.def = def;
		stackArr = new int[size];
	}

	public boolean isEmpty()
	{
		if(pos<0)
			return true;
		else
			return false;
	}

	public boolean isFull()
	{
		if(pos>=(maxSize-1))
			return true;
		else
			return false;
	}

	public void push(int x) throws StackException
	{
		if(isFull())
		{
			System.out.println("Overflow");	
			throw new StackException();
		}
		stackArr[++pos]=x;
	}
  
    public int showTop(){
        if(isEmpty()){
            System.out.println("Empty stack");
            return def;
        }
        else
            return stackArr[pos];
    }

	public int pop() throws StackException
	{
		if(isEmpty())
		{
			System.out.println("Empty stack");
			throw new StackException();
		}
		return stackArr[pos--];
	}

	public static void main(String[] args)
	{
		SimpleStack s=new SimpleStack(3,1);
		try
		{
			s.push(1);
			System.out.println(s.pop());
		}
		catch(Exception ex)
		{
			System.out.println("In catch of stack exception");
		}
	}
}






