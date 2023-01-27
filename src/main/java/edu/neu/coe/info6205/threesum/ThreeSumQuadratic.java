package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        // Provided array is sorted in asc order, allowing degrees of freedom to apply the spread from center approach
        // the spread from center approach will result in duplicate triplets, but the stream().distinct() in parent function resolves this
        int i = j - 1, k = j + 1;
        int sum = -1; // initialize triplet sum with dummy value to be updated in loop
        while(i >= 0 && k <= length - 1){
            sum = a[i] + a[j] + a[k]; // 2 conditions using sum, store in var for reduced array access
            if(sum > 0){i--;} // try to compensate with lower value : decrement i
            else if(sum < 0){k++;} // try to compensate with bigger value : increment k
            else{
                triples.add(new Triple(a[i], a[j], a[k])); // valid triplet found
                i--; // more triplets with same center point could  continue to spread out
                k++;
            }
        }
        // END 
        return triples;
    }

    private final int[] a;
    private final int length;
}
