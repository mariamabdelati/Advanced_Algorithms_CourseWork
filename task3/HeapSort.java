package com.taskthree;

/**
 *
 */
public class HeapSort {

    /**
     * @param array
     * @return
     */
    public int[] heapSort(int[] array) {
        buildHeap(array);

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }

        return array;
    }

    /**
     * @param array
     */
    private void buildHeap(int[] array) {
        // identify the last parent node to optimize approach
        int lastParent = array.length / 2 - 1;

        // heapify the array backwards from the last parent
        for (int i = lastParent; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    /**
     * @param array
     * @param heapSize
     * @param parentIndex
     */
    private void heapify(int[] array, int heapSize, int parentIndex) {
        //final int length = Math.min(array.length, heapSize) - 1;
        if (parentIndex > heapSize || parentIndex < 0) {
            throw new IllegalArgumentException("Parent is out of range.");
        }

        while (true) {
            int leftChildIndex = getLeftChildPos(parentIndex);
            int rightChildIndex = getRightChildPos(parentIndex);

            // find the index of the largest element
            int largestIndex = parentIndex;
            if (leftChildIndex < heapSize && array[leftChildIndex] > array[largestIndex]){
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < heapSize && array[rightChildIndex] > array[largestIndex]){
                largestIndex = rightChildIndex;
            }

            if (parentIndex == largestIndex){
                break;
            }

            swap(array, parentIndex, largestIndex);
            parentIndex = largestIndex;

        }
    }

    /**
     * @param pos
     * @return
     */
    private int getParentPos(int pos) {
        return (pos - 1) / 2;
    }

    /**
     * @param pos
     * @return
     */
    private int getLeftChildPos(int pos) {
        return pos * 2 + 1;
    }

    /**
     * @param pos
     * @return
     */
    private int getRightChildPos(int pos) {
        return pos * 2 + 2;
    }

    /**
     * @param array
     * @param parentPos
     * @param childPos
     */
    private void swap(int[] array, int parentPos, int childPos) {
        int temp = array[parentPos];
        array[parentPos] = array[childPos];
        array[childPos] = temp;
    }

    private static String toStr(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int element : array) {
            sb.append(element).append(", ");
        }

        return sb.substring(0, sb.length() - 2);
    }

    private static void printArray(int[] arr) {
        int n = arr.length;
        for (int elm : arr) System.out.print(elm + " ");
        System.out.println();
    }
}