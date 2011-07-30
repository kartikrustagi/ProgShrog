import java.io.*;

class ReadFile
{
    public static void main(String[] args)
    {
        try{
        BufferedReader br = new BufferedReader(new FileReader("test.html"));
        String line=null;
        while((line=br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}