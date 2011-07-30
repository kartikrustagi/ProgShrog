
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author antony
 */
public class Main {

    private static long solve(long L, long t, int N, long[] c) {
        long[] dist = new long[N];
        for (int i = 0; i < N; i++) {
            dist[i] = c[i % c.length];
        }

        int pos = 0;
        long time = 0;
        while (pos < N && time + 2 * dist[pos] < t) {
            time += 2 * dist[pos];
            pos++;
        }
        if (pos == N) {
            return time;
        }
        long borderTime = time;

        int[] speed = new int[N];
        for (int i = 0; i < N; i++) {
            speed[i] = 2;
        }


        while (L-- > 0) {
            long before = (t - borderTime) / 2;
            long after = dist[pos] - before;

            long max = after;
            int maxIndex = pos;
            for (int i = pos+1; i < N; i++) {
                if (dist[i] > max && speed[i] == 2) {
                    max = dist[i];
                    maxIndex = i;
                }
            }
            speed[maxIndex] = 1;
        }

        if (speed[pos] == 1) {
            long before = (t - borderTime) / 2;
            long after = dist[pos] - before;
            time += before * 2 + after;

            pos++;
        }

        while (pos < N) {
            time += speed[pos] * dist[pos];
            pos++;
        }
        return time;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner s = new Scanner(new File("b.in"));
//        Scanner s = new Scanner(new File("B-small-attempt0.in"));
//        Scanner s = new Scanner(new File("B-small-attempt1.in"));
        Scanner s = new Scanner(new File("B-small-practice.in"));
        int T = s.nextInt();
        for (int i = 0; i < T; i++) {

            long L = s.nextLong();
            long t = s.nextLong();
            int N = s.nextInt();
            int C = s.nextInt();

            long [] a = new long [C];
            for (int j = 0; j < C; j++) {
                a[j] = s.nextLong();
            }

            System.out.println("Case #" + (i + 1) + ": " + solve(L, t, N, a));
        }
    }
}
