package task8;

import java.util.Arrays;
import java.util.Random;

/**
 *
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args){
        ParallelMergeSort mergeSortTask = new ParallelMergeSort();
        MergeSort mergeSort = new MergeSort();

        int[] array1 = {7, 5, 4, 8, 3, 9};
        int[] array2 = {7, 5, 4, 8, 3, 9};

        long startTime = System.currentTimeMillis();
        mergeSortTask.parallelMergeSort(array1);
        long endTime = System.currentTimeMillis();

        System.out.println("Sorted Array Using Parallel Merge Sort: " + Arrays.toString(array1));
        System.out.println("Parallel Merge Sort: The Total Computation Time for the Small Array was: " + (endTime-startTime));


        startTime = System.currentTimeMillis();
        mergeSort.mergeSort(array2, 0, array2.length - 1);
        endTime = System.currentTimeMillis();

        System.out.println("Sorted Array Using Non-Parallel Merge Sort: " + Arrays.toString(array2));
        System.out.println("Non-Parallel Merge Sort: The Total Computation Time for the Small Array was: " + (endTime-startTime) + "\n");


        int[] bigArray1 =new int[10000000];
        Random rand = new Random();
        for(int j=0;j<10000000;j++) {
            bigArray1[j] = rand.nextInt();
        }

        int[] bigArray2 = bigArray1.clone();

        startTime = System.currentTimeMillis();
        mergeSortTask.parallelMergeSort(bigArray1);
        endTime = System.currentTimeMillis();

        System.out.println("Parallel Merge Sort: The Total Computation Time for the Big Array was: " + (endTime-startTime));


        startTime = System.currentTimeMillis();
        mergeSort.mergeSort(bigArray2, 0,  bigArray2.length -1 );
        endTime = System.currentTimeMillis();

        System.out.println("Non-Parallel Merge Sort: The Total Computation Time for the Big Array was: " + (endTime-startTime));
    }
}
