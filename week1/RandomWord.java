package week1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        int cnt = 1;
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            if (StdRandom.bernoulli((double) 1/cnt)) {
                champion = name;
            }
            ++cnt;
        }
        StdOut.println(champion);
    }
}