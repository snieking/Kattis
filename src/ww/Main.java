package ww;

import java.io.*;
import java.util.*;

/**
 * Program that implements an solution to the Walrus Weight problem from Kattis.
 * Tried first an recursive solution but no matter how much I tried to optimize
 * the time complexity it wasn't quick enough.
 * Instead this solution resorts to an iterative solution instead.
 *
 * @author Viktor Plane
 * @since 2016-09-17
 */
public class Main {

    /**
     * ww.Main method of the program which waits for input,
     * and then runs the {@link #getCorrectWeight(int[], int)}  algorithm to solve
     * the Kattis Walrus Weight problem.
     *
     * @param args
     */
    public static void main(String[] args) {
        final Kattio io = new Kattio(System.in, System.out);
        final int plates = io.getInt();

        int[] platesArr = new int[plates];

        int i = 0;
        while (io.hasMoreTokens()) {
            platesArr[i++] = io.getInt();
            if (i == plates)
                break;
        }

        io.write("" + getCorrectWeight(platesArr, 1000));

        io.close();
    }

    /**
     * Iterative method that sums all the different combinations of values of the inputs,
     * and finds the value from this these sums which is closet to the provided target.
     * It prefers larger values if two different sums are the same distance from the target.
     *
     * @param platesArr which is the list of inputs provided.
     * @param target    which is the target it tries to find a value as close as possible to.
     * @return the value of sums as int that is closest to the target.
     */
    public static int getCorrectWeight(int[] platesArr, int target) {
        /* Creates two lists, one for storing completed values after each iteration,
        one for storing new values during iteration. */
        Set<Integer> vals = new HashSet<>();
        Set<Integer> newVals = new HashSet<>();

        // Inserts 0 as a first value so that we can start the first iteration.
        int best = 0;
        vals.add(best);

        for (int i = 0; i < platesArr.length; i++) {
            if (platesArr[i] == target) // If target is already an input, it's pointless to continue.
                return target;

            for (Integer j : vals) {
                int newVal = j + platesArr[i];
                if (newVal == target) // If found the target, it's pointless to continue.
                    return target;
                if (newVal <= target) {
                    newVals.add(newVal);
                    if (newVal > best) {
                        best = newVal;
                    }
                }
                if ((Math.abs(target - newVal) < Math.abs(target - best)) || (Math.abs(target - newVal) == Math.abs(target - best) && newVal > best)) {
                    best = newVal;
                }
            }
            vals.addAll(newVals);
            newVals = new HashSet<>();
        }
        return best;
    }
}


/**
 * An optimized and faster class for I/O to quicker, specifically designed for Kattis assignments.
 * Proven to be a lot quicker than using Scanner over larger amount of inputs.
 */
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
