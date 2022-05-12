package task8;

import java.util.Arrays;

/**
 *
 */
public class MergeSort {

    /**
     * @param arr
     * @param start
     * @param end
     */
    void mergeSort(int[] arr, int start, int end)
    {
        if (start < end)
        {
            // Find the middle index
            int mid = (start+end)/2;
            // Sort first half
            mergeSort(arr, start, mid);
            // Sort second half
            mergeSort(arr , mid+1, end);
            // Merge the sorted halves
            merge(arr, start, mid, end);
        }
    }

    /**
     * @param arr
     * @param startIndex
     * @param midIndex
     * @param endIndex
     */
    private void merge(int[] arr, int startIndex, int midIndex, int endIndex) {
        int[] leftArray = Arrays.copyOfRange(arr, startIndex, midIndex+1);
        int[] rightArray = Arrays.copyOfRange(arr, midIndex+1, endIndex+1);
        int f = startIndex;

        int leftIndex = 0, rightIndex = 0;
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                arr[f++] = leftArray[leftIndex++];
            } else {
                arr[f++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftArray.length) {
            arr[f++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            arr[f++] = rightArray[rightIndex++];
        }
    }
}
