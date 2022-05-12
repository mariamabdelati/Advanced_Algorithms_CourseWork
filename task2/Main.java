package com.tasktwo;

/**
 *
 * @author  Mariam Abdelati
 * @since   2022-04-23
 */

public class Main {


    public static void main(String[] args) {

        RedBlackTree A = new RedBlackTree();

        System.out.println("\nTree After Tree Creation: ");
        A.displayTree();

        int[] insertNodes = {30, 15, 45, 35, 60, 55};
        for (int node : insertNodes) {
            System.out.println("\nTree After Inserting " + node + ":");
            A.insert(node);
            A.displayTree();
        }

        int deleteNode = 45;
        System.out.println("\nTree After Deleting " + deleteNode + ":");
        A.deleteNode(deleteNode);

        A.displayTree();

        System.out.println();
    }
}
