class PostFixEval
{
	public static int evalFn(String exp, SimpleStack stack) throws Exception
	{
		byte[] expChar = exp.getBytes();
		for(Byte b:expChar)
		{
			if(b=='+')
			{
				try
				{
					stack.push(stack.pop()+stack.pop());
				}
				catch(Exception ex)
				{
					System.out.println("Invalid expression");
					ex.printStackTrace();
				}
			}
			else
			{
				stack.push(b-'0');
			}
		}

		return stack.pop();
	}

	public static void main(String[] str)
	{
		String[] exp = FileOp.readFile("test.txt");
		for(String s: exp)
		{
			SimpleStack stack = new  SimpleStack(s.length());
			try
			{
				System.out.println(evalFn(s,stack));
		
			}
			catch(Exception ex)
			{
			}
		}
	}
}

