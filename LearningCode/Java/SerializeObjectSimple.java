import java.io.*;

public class SerializeObjectSimple implements Serializable 
{
	int x;

	public void set()
	{
		x = 10;
	}

	public void printX()
	{		
		System.out.println(x);
	}
}
