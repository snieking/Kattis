package rbn;

import java.util.Scanner;

/**
 * A solution to the reversed binary number problem on Kattis.
 * @author Viktor Plane
 * @since 2016-09-19
 */
public class Main {

    /**
     * Reads an integer input and calls {@link #reverseBinary(int)} with the input.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            System.out.println(reverseBinary(scanner.nextInt()));
        }
    }

    /**
     * Takes an integer value and reverses all the binaries in it.
     * @param binary which is an integer that it should look at as a binary.
     * @return the integer value of the binary after the reverse has been done.
     */
    private static int reverseBinary(int binary) {
        int b = 0;
        while (binary!=0){
            b<<=1;
            b|=( binary &1);
            binary>>=1;
        }

        return b;
    }

}
