class SimpleStack<T>{
	//TODO: Generic SimpleStack for int/char etc
	int pos=-1;
	int maxSize;
	T def;
	T[] stackArr =  new T[maxSize];

	SimpleStack(int size, T def)
	{
                this.def = def; 
		maxSize = size;
		//stackArr = new T[size];
		for(T t:stackArr)
			t=new T(def);
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

	public void push(T x)
	{
		if(isFull())
		{
			System.out.println("Overflow");	
			return;
		}
		stackArr[++pos]=x;
	}

	public T pop() throws StackException
	{
		if(isEmpty())
		{
			System.out.println("Empty stack");
			return def;
		}
		return stackArr[pos--];
	}

	public static void main(String[] args)
	{
		SimpleStack<Integer> s=new SimpleStack<Integer>(3,-100);
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






