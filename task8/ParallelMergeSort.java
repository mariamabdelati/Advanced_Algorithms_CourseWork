package task8;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *
 */
public class ParallelMergeSort {

    /**
     * @param array
     */
    public void parallelMergeSort(int[] array){
        SortTask mainTask = new SortTask(array, 0, array.length-1);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()-1);
        pool.invoke(mainTask);
    }

    /**
     *
     */
    private static class SortTask extends RecursiveAction {
        private final int[] arr;
        private final int startIndex;
        private final int endIndex;

        public SortTask(int[] arr, int startIndex, int endIndex){
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        /**
         *
         */
        @Override
        protected void compute(){
            if (startIndex < endIndex){
                if (endIndex - startIndex <= 8192){
                    MergeSort mergeSort = new MergeSort();
                    mergeSort.mergeSort(arr, startIndex, endIndex);
                } else {
                    int midIndex = (startIndex + endIndex) / 2;
                    SortTask firstSubarrayTask = new SortTask(arr, startIndex, midIndex);
                    SortTask secondSubarrayTask = new SortTask(arr, midIndex + 1, endIndex);

                    invokeAll(firstSubarrayTask, secondSubarrayTask);

                    merge(arr, startIndex, midIndex, endIndex);
                }
            }
        }

        /**
         * @param midIndex
         */
        private void merge(int[] arr, int startIndex, int midIndex, int endIndex) {
            int[] leftArray = Arrays.copyOfRange(arr, startIndex, midIndex+1);
            int[] rightArray = Arrays.copyOfRange(arr, midIndex+1, endIndex+1);
            int listIndex = startIndex;

            int leftIndex = 0, rightIndex = 0;
            while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                    arr[listIndex++] = leftArray[leftIndex++];
                } else {
                    arr[listIndex++] = rightArray[rightIndex++];
                }
            }

            while (leftIndex < leftArray.length) {
                arr[listIndex++] = leftArray[leftIndex++];
            }

            while (rightIndex < rightArray.length) {
                arr[listIndex++] = rightArray[rightIndex++];
            }
        }
    }
}
