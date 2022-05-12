package task3;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Task Three: Heapsort A User-Defined Array Program</h1>
 * The main function program implements the .
 * <p>
 *
 * @author  Mariam Abdelati
 * @since   2022-04-23
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to HeapSort Program");

        Scanner reader = new Scanner(System.in);  

        boolean correctInput = false;
        int numberofElements = 0;

        while (!correctInput) {
            try {
                System.out.print("\nEnter the total number of values to be inserted in the array: ");
                numberofElements = reader.nextInt();
                if (numberofElements < 2){
                    throw new IllegalArgumentException();
                }
                correctInput = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid Input Type. Input must be an Integer.");
                reader.nextLine(); // clear input from scanner
            } catch (IllegalArgumentException a) {
                System.err.println("Invalid Value for Array Size. Value must be greater than 1.");
                reader.nextLine(); // clear input from scanner
            }
        }

        int[] array = new int[numberofElements];

        for (int i = 0; i < numberofElements; i++) {
            correctInput = false;
            while (!correctInput) {
                try {
                    System.out.print("\nEnter element " + (i + 1) + " to be inserted in array: ");
                    array[i] = (reader.nextInt());
                    correctInput = true;
                } catch (InputMismatchException e) {
                    System.err.println("Invalid Input Type. Input must be an Integer.");
                    reader.nextLine(); // clear input from scanner
                }
            }
        }

        //once finished close the scanner
        reader.close();

        System.out.println("\nThe unsorted array entered is: " + Arrays.toString(array));

        HeapSort hs = new HeapSort();
        int[] x = hs.heapSort(array);
        System.out.println("\nThe sorted array is: " + Arrays.toString(x));

        System.out.println("================================================================");
    }
}
