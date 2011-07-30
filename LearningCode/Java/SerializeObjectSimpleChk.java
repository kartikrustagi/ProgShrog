import java.io.*;

class SerializeObjectSimpleChk
{
	public static void main(String[] str)
	{
		SerializeObjectSimple s = new SerializeObjectSimple();
		s.set();
		try
		{
			FileOutputStream f = new FileOutputStream("chk.ser");
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(s);
			o.close();
			FileInputStream fi = new FileInputStream("chk.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			Object obj = oi.readObject();
			SerializeObjectSimple si = (SerializeObjectSimple)obj;
			si.printX();
			oi.close();

		}
		/*catch(IOException ex)
		{
			ex.printStackTrace();
			System.out.println("In exception");
		}*/
		catch(Exception ex)
		{
		}

	}
}
		

