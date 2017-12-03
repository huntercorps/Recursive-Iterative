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


    /*Returns the efficiency counter of the calls to the computeIterative and Recursive methods and resets counter.
    * I chose to reset efficiency this way rather than including it in the other methods*/
    public static int getEfficiency(){
        int efficiencyCounter = efficiency;
        efficiency = 0;
        return efficiencyCounter;
    }


    /* Accepts a value of count(term) and returns the corresponding term value in the sequence using iteration. */
    public static long computeIterative(long count){
        //efficiency = 0 //moved to getEfficiency
        if (count == 0 || count == 1){
            efficiency++;
            return count;
        }
            long countVal1 = 0, countVal2 = 1, countTemp;
            for (int i = 1; i < count; i++) {
                countTemp= countVal1 + countVal2 * 2;
                countVal1=countVal2;
                countVal2=countTemp;
                efficiency++;
            }
            return countVal2;
    }

    /* unused method using stream just testing*/
    @Deprecated
    public static void streamSequence(int nth) {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1] + t[1]})
                .limit(nth) //limit sequence to th nth pair group
                .skip(nth - 1) //skip over non nth numbers
                .map(t -> t[0]) //grab the 1st number of the pairs
                .forEach(System.out::println);
    }

    /* helper method, Accepts a count(term) and returns 0, 1, or passes count to the recursive method.*/
    public static long computeRecursive(long count){
        //efficiency = 0 //moved to getEfficiency
        if (count ==0 || count ==1) {
            efficiency++;
            return count;
        }
        return computeRecursive(count-1,0,1);
    }

    /* Accepts the count(term) and returns the value of the count term and increments efficiency token.*/
    private static long computeRecursive(long count, long prev, long current){
        efficiency++;
        return (count == 0) ? current : computeRecursive(--count, current, prev+(current*2));

    }

}
