/* File: Sequence.java
* Author: Hunter Smith
* Date: 12/03/17
* Purpose: a utility class that contains methods to find the value of the passed in term.
* and track the efficiency w/ a efficiency token. Sequence is 0 1 2 5 12 29 ...
* where each term of the sequence is twice the previous term plus the second previous term.
* The 0th term of the sequence is 0 and the 1st term of the sequence is 1. Class is abstract to avoid instantiation
*/

import java.util.stream.Stream;

public abstract class Sequence {
    //Class Fields
    private static int efficiency;


    /*Return the efficiency counter of the calls to the computeIterative and Recursive methods and resets counter.*/
    public static int getEfficiency(){
        int efficiencyCounter = efficiency;
        efficiency = 0;
        return efficiencyCounter;
    }


    /* Accepts a value of n and return the corresponding element in the sequence using iteration. */
    public static int computeIterative(int n){
        if (n == 0 || n == 1){
            efficiency++;
            return n;
        }
            int n1 = 0, n2 = 1, nTemp;
            for (int i = 1; i < n; i++) {
                nTemp= n1 + n2 * 2;
                n1=n2;
                n2=nTemp;
                efficiency++;
            }
            return n2;
    }

    /* unused method using stream*/
    @Deprecated
    public static void streamSequence(int nth) {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1] + t[1]})
                .limit(nth) //limit sequence to th nth pair group
                .skip(nth - 1) //skip over non nth numbers
                .map(t -> t[0]) //grab the 1st number of the pairs
                .forEach(System.out::println);
    }

    /* helper method, Accepts a value of n and returns 0, 1,  or passes values to the recursive method recursion.*/
    public static int computeRecursive(int n){
        if (n ==0 || n ==1) {
            efficiency++;
            return n;
        }
        return computeRecursive(n-1,0,1);
    }

    /* Accepts the value of n and returns the value of the nth term values and increments efficiency token.*/
    private static int computeRecursive(int n, int prev, int current){
       // System.out.println(current);
        System.out.println(n);
        efficiency++;
            return (n == 0) ? current : computeRecursive(--n, current, prev+(current*2));

    }

}
