package task1;

/**
 * This is the node class used for the binary tree.
 * It stores an int data, which holds the value of the tree
 * It also contains the left and right nodes pointing to the
 * left and right children of the node.
 * The parent for the node is also specified to connect nodes
 * to each other for insertion and deletion checks. The parent
 * node is important to the implementation of simple binary trees
 * as the insertion into the tree can be random unlike in binary
 * search trees which require a specific order and allow no duplicates.
 *
 * @author  Mariam Abdelati
 */
public class Node
{
    public int data;
    public Node right;
    public Node left;
    public Node parent;

    /**
     * This is a node constructor which creates a new node
     * with the given data.
     *
     * @param data the value that the node is to store
     */
    public Node(int data)
    {
        this(data,null,null, null);
    }


    /**
     * This is a node constructor which creates a new node with
     * the given data, the left and right child and the parent node
     *
     * @param data the value that the node is to store
     * @param left the left node of the binary tree
     * @param right the right node of the binary tree
     * @param parent the parent node of the node
     */
    public Node(int data, Node left, Node right, Node parent)
    {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}