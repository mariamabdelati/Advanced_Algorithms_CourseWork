package task1;

/**
 * <h1>Task One: Duplicate Binary Tree Recursive Program</h1>
 * The main function program calls the createDupTree recursive
 * method defined in the BinaryTree class. The method traverses the tree
 * and duplicates every node, inserting it as the left child of the
 * node. The main method declares a new binary tree and inserts some nodes
 * into the tree then calls the createDupTree method then stores the newly
 * created tree and prints the output of the original tree and the duplicated
 * tree on the screen.
 * <p>
 *
 * @author  Mariam Abdelati
 * @since   2022-04-23
 */

public class Main {

    /**
     * This is the main method which makes use of the BinaryTree and Node Classes
     * defined in the package. First a new binary tree is created then elements
     * are added by inserting the desired nodes into the tree using the {@link com.taskone.BinaryTree#insertNode(int, Node, boolean) insert} method
     * defined in the BinaryTree class. The  {@link BinaryTree#createDupTree() createDupTree} method is called on the BinaryTree
     * object and the returned tree is stored in another BinaryTree object.
     * The first tree is displayed then the second tree is displayed to show the result after duplication.
     * @param args Unused
     */
    public static void main(String[] args) {

        BinaryTree A = new BinaryTree();

        Node root = A.setRoot(2);
        Node node1 = A.insertNode(1, root, BinaryTree.LEFT);
        Node node2 = A.insertNode(3, root, BinaryTree.RIGHT);
        Node node3 = A.insertNode(4, node1, BinaryTree.RIGHT);

        BinaryTree duplicatedTree = A.createDupTree();

        System.out.println("Original Binary Tree (Before Duplicating Nodes): ");
        A.displayTree();

        System.out.println("\n\nBinary Tree with Duplicated Nodes: ");
        duplicatedTree.displayTree();
        //A.displayTree();
        System.out.println();
    }
}