import java.util.regex.*;

class RegExTry{
	public static void main(String[] args){
		String regex = "\\(?[a-z]+\\)?{3}";
		//String regex = "\\(?[a-z]+";
		Pattern p = Pattern.compile(regex);
		String pattern = args[0];
		Matcher m = p.matcher(pattern);
		if(m.matches())
			System.out.println("Matches");
		else
			System.out.println("No match");
	}
}