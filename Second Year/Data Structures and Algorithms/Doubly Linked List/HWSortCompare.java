/*
 * HWSortCompare.java
 *
 * Author: Brendon Peters
 * Submitted on: 9/13/2024
 *
 * Academic Honesty Declaration:
 *
 * The following code represents my own work and I have neither received nor given assistance
 * that violates the collaboration policy posted with this assignment. I have not copied or modified code
 * from any other source other than the homework assignment, course textbook, or course lecture slides.
 * Any unauthorized collaboration or use of materials not permitted will be subjected to academic integrity policies of
 * WPI and CS 2223.
 *
 * I acknowledge that this homework assignment is based upon an assignment created by WPI and that any publishing or
 * posting of this code is prohibited unless I receive written permission from WPI.
 */
package wpialgs.hw02;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Date;
import wpialgs.sorts.*;

/**
 * This is a modified version of {@code SortCompare} from pp. 256 for homework assignment.
 *
 * @author Yu-Shan Sun
 *
 * @version 1.0
 */
public class HWSortCompare {

    /**
     * The main entry point to the program.
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("USAGE: java HWSortCompare <size of array> <number of trials>");
        } else {
            int sizeOfN = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

            // Welcome message
            Date today = new Date();
            StdOut.println("Welcome to CS 2223 - Sort Compare!");
            StdOut.printf("\tTODAY: %s (%d)\n", today, today.getTime());
            StdOut.printf("\tRunning sorting algorithms on %d random Doubles (%d trials)\n\n", sizeOfN, trials);
            StdOut.println("------------------------------------");

            // Perform number of trials
            double bubbleTotal = 0;
            double selectionTotal = 0;
            double insertionTotal = 0;
            double topdownMergeTotal = 0;
            double bottomUpMergeTotal = 0;
            double quickTotal = 0;
            double quickBMTotal = 0;
            Double[] a = new Double[sizeOfN];
            for (int trial = 0; trial < trials; trial++) {
                // Generate and perform one experiment on each of the algorithms
                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                bubbleTotal += timeBubbleSort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                selectionTotal += timeSelectionSort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                insertionTotal += timeInsertionSort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                topdownMergeTotal += timeTopdownMergesort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                bottomUpMergeTotal += timeBottomUpMergesort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                quickTotal += timeQuicksort(a);

                for (int i = 0; i < sizeOfN; i++) {
                    a[i] = StdRandom.uniformDouble(0, 1.0);
                }
                quickBMTotal += timeQuicksortBentleyMcllroy(a);
            }

            // Print results
            StdOut.printf("Bubble sort took an average of: %.5f seconds to sort.\n", bubbleTotal / trials);
            StdOut.printf("Selection sort took an average of: %.5f seconds to sort.\n", selectionTotal / trials);
            StdOut.printf("Insertion sort took an average of: %.5f seconds to sort.\n", insertionTotal / trials);
            StdOut.printf("Topdown mergesort took an average of: %.5f seconds to sort.\n", topdownMergeTotal / trials);
            StdOut.printf("Bottom up mergesort took an average of: %.5f seconds to sort.\n",
                    bottomUpMergeTotal / trials);
            StdOut.printf("Quicksort took an average of: %.5f seconds to sort.\n", quickTotal / trials);
            StdOut.printf("Quicksort (Bentley-Mcllroy) took an average of: %.5f seconds to sort.\n",
                    quickBMTotal / trials);
            StdOut.println("------------------------------------");
        }
    }

    /***************************************************************************
     * Helper functions.
     ***************************************************************************/

    /**
     * A helper method that times the amount of time to run bubble sort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeBubbleSort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        BubbleSort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run selection sort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeSelectionSort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        SelectionSort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run insertion sort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeInsertionSort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        InsertionSort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run top down mergesort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeTopdownMergesort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        TopDownMergesort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run bottom up mergesort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeBottomUpMergesort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        BottomUpMergesort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run quicksort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeQuicksort(Double[] a) {
        Stopwatch timer = new Stopwatch();
        Quicksort.sort(a);
        return timer.elapsedTime();
    }

    /**
     * A helper method that times the amount of time to run improved quicksort.
     *
     * @param a
     *            An array to be sorted
     *
     * @return Elapsed time to sort the array.
     */
    private static double timeQuicksortBentleyMcllroy(Double[] a) {
        Stopwatch timer = new Stopwatch();
        QuicksortBentleyMcIlroy.sort(a);
        return timer.elapsedTime();
    }
}