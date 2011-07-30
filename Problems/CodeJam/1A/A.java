import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A {

	public static final String POSSIBLE = "Possible";
	public static final String BROKEN = "Broken";

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Exactly 1 argument should be passed");
			return;
		}
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);

		int T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			long N = scan.nextLong();
			int Pd = scan.nextInt();
			int Pg = scan.nextInt();

			if (Pg == 100 && Pd < 100) {
				System.out.println("Case #" + (i + 1) + ": " + BROKEN);
				continue;
			}

			if (Pg == 0 && Pd > 0) {
				System.out.println("Case #" + (i + 1) + ": " + BROKEN);
				continue;
			}
			
			if(N >= 100) {
				System.out.println("Case #" + (i + 1) + ": " + POSSIBLE);
				continue;
			}
			boolean flag = false;
			for (int d = 1; d <= N; d++) {
				if (d * Pd % 100 == 0) {
					int Wd = d * Pd / 100;
					System.out.println("Case #" + (i + 1) + ": " + POSSIBLE);
					flag = true;
					break;
				}
			}
			if(!flag)
				System.out.println("Case #" + (i + 1) + ": " + BROKEN);

		}
	}
}
