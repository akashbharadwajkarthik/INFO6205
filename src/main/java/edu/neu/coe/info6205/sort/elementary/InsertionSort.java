/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class InsertionSort.
 *
 * @param <X> the underlying comparable type.
 */
public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for(int i = from + 1; i < to; i++){
            int j = i - 1;
            while(j >= from && helper.swapConditional(xs,j,j+1)){j--;}
        }
        // FIXME
        // END 
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    public static void main(String[] args) {
        // Define number of runs for sort on each type of array
        int runs = 30;

        // define the range of array values
        int max = 100000;
        int min = -100000;
        int range = max - min + 1;

        //Define consumer sort function (insertion sort defined in this class) and initialize BenchmarkTimer object with this consumer
        InsertionSort<Integer> insertSort=new InsertionSort<Integer>();
        Consumer<Integer[]> consumer= array->insertSort.sort(array, 0, array.length);
        Benchmark_Timer<Integer[]> benchmarkTimer = new Benchmark_Timer<Integer[]>("Insertion Sort", consumer);

        //Conducting tests for randomly ordered array
        System.out.println("RANDOMLY ORDERED ARRAY");

        for (int N = 1000; N <= 16000; N += N) {
            int arrSize = N;
            Supplier<Integer[]> supplier = () -> {  //Supplier
                Integer[] a = new Integer[arrSize];
                for (int i = 0; i < arrSize; i++)
                    a[i] = (int)(Math.random()*range) + min;//Generating random numbers with uniform distribution in range [-min,max]
                return a;
            };
            System.out.println("N= " + N +", Time Taken: " + benchmarkTimer.runFromSupplier(supplier, runs));
        }

        System.out.println("\nORDERED ARRAY");

        for (int N = 1000; N <= 16000; N += N) {
            int arrSize = N;

            Supplier<Integer[]> supplier = () -> { //Supplier
                Integer[] a = new Integer[arrSize];
                for (int i = 0; i < arrSize; i++)
                    a[i] = (int)(Math.random()*range) + min; //Generating random numbers with uniform distribution in range [-min,max]
                Arrays.sort(a);//Sorting to order array
                return a;
            };

            System.out.println("N= " + N +", Time Taken: " + benchmarkTimer.runFromSupplier(supplier, runs));
        }

        System.out.println("\nPARTIALLY ORDERED ARRAY");

        for (int N = 1000; N <= 16000; N += N) {
            int arrSize=N;
            Supplier<Integer[]> supplier = () -> { //Supplier
                Integer[] a = new Integer[arrSize];
                for (int i = 0; i < arrSize; i++)
                    a[i] = (int)(Math.random()*range) + min; //Generating random numbers with uniform distribution in range [-min,max]
                Arrays.sort(a);//Sort array completely

                for (int i = 0; i < arrSize/2; i++)
                    a[i] = (int)(Math.random()*range) + min; //create inversions in left half of array

                return a;
            };
            System.out.println("N= " + N +", Time Taken: " + benchmarkTimer.runFromSupplier(supplier, runs));
        }

        System.out.println("\nREVERSE ORDERED ARRAY");

        for (int N = 1000; N <= 16000; N += N) {
            int arrSize = N;
            Supplier<Integer[]> supplier = () -> { //Supplier
                Integer[] a = new Integer[arrSize];
                for (int i = 0; i < arrSize; i++)
                    a[i] = (int)(Math.random()*range) + min; //Generating random numbers with uniform distribution in range [-min,max]
                Arrays.sort(a, Collections.reverseOrder());//Sort in non-increasing order using reverseOrder comparator
                return a;
            };
            System.out.println("N= " + N +", Time Taken: " + benchmarkTimer.runFromSupplier(supplier, runs));
        }
    }
}
