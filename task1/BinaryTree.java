package task1;

/**
 * This class defines some basic operations of a binary tree
 * such as insertion or deletion of a node. The class also
 * defines a method to create a new tree with duplicated nodes
 * from the original tree.
 */
public class BinaryTree {
    /**
     * Private root attribute to the binary tree pointing to the root
     * of the binary tree, only accessible within this class and updated
     * within this class only.
     */
    private Node root;

    /**
     * Static variables used to reference whether the node is inserted to
     * the right or to the left. Using the static key word ensures that only
     * one copy of that value exists no matter how many objects of the binary
     * tree class are initialized.
     */
    static final boolean LEFT = false;
    static final boolean RIGHT = true;

    /**
     * BinaryTree constructor used to initialize an empty tree, so sets
     * the root to null.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * This is a method that creates a new tree object and calls
     * the {@link #createDupTree(Node) createdupTree} helper method which
     * is a recursive method that traverses the original tree and creates
     * duplicated nodes inserted to the left of the node from the original
     * tree. The newTree's root is set to the returned root node from the
     * duplication method.
     *
     * @return the new binary tree with duplicated nodes
    */
    public BinaryTree createDupTree(){
        BinaryTree newTree = new BinaryTree();
        newTree.root = createDupTree(root);
        return newTree;
    }

    /**
     * This is a recursive helper method where the actual duplication of
     * the nodes occurs. The recursion starts from the original tree's root
     * then it is traversed. The newly created node is returned.
     * @param node the original tree node
     * @return  null is returned to break the recursion of the node is null (base case)
     *         at the end of recursion, secondTreeNode is returned which is the root node in the new tree
     */
    private Node createDupTree(Node node) {
        if (node == null) {
            return null;
        }

        Node secondTreeNode = new Node(node.data);
        secondTreeNode.left = new Node(node.data);
        secondTreeNode.left.parent = secondTreeNode;

        secondTreeNode.left.left = createDupTree(node.left);
        secondTreeNode.left.left.parent = secondTreeNode.left;
        secondTreeNode.right = createDupTree(node.right);
        secondTreeNode.right.parent = secondTreeNode.right;

        return secondTreeNode;
    }


    /**
     * This method inserts a node with the given value as the root of the
     * tree if the root was not already defined for the tree.
     *
     * @param value the value of the root to be inserted
     * @return the root inserted
     * @exception IllegalStateException On entry of a new root when the root has already been set
     * @see IllegalStateException
     */
    public Node setRoot(int value){
        if (root == null){
            root = new Node(value);
            return root;
        } else {
            throw new IllegalStateException("\nRoot has already been defined.");
        }
    }

    /**
     * This method inserts a node with the value provided as a left or right child to the parent provided,
     * if a node is already defined for the provided parent then the node is inserted as a child to the parent and
     * its left or right children are made children to the new node depending on which side the new node is inserted
     *
     * @param value gives the value of the new node that will be inserted
     * @param parent gives the node that the new node will be inserted under
     * @param position whether the node is to be inserted as right or left child to the parent
     * @return the new node inserted
     * @exception IllegalArgumentException On entry of a null Parent Node.
     * @see IllegalArgumentException
     */
    public Node insertNode(int value, Node parent, boolean position){
        if (parent == null){
            throw new IllegalArgumentException("\nParent cannot be null.");
        }

        Node newNode = new Node(value);
        if (position == LEFT){
            if(parent.left != null) {
                newNode.left = parent.left;
                parent.left.parent = newNode;
            }
            parent.left = newNode;
        } else {
            if (parent.right != null){
                newNode.right = parent.right;
                parent.right.parent = newNode;
            }
            parent.right = newNode;
        }
        newNode.parent = parent;

        return newNode;
    }

    /**
     * This method deletes a specified node from the tree. The following
     * cases are considered:
     * <ol>
     *     <li>Node with no children (null)</li>
     *     <li>Node with one child</li>
     *     <ol style="margin-top: 0; margin-bottom: 0">
     *          <li> Has only left child </li>
     *          <li> Has only right child </li>
     *     </ol>
     *     <li>Node with two children</li>
     *</ol>
     *
     * For the first two cases, the node, its child and the child's parent are updated accordingly.
     * For the third case, the leftsubtree is traversed
     *
     * @param node the node that is to be deleted from the tree
     * @exception IllegalStateException On entry of a node that is not part of the tree
     *            for example a newly created node that is passed to the function will
     *            throw an exception
     * @see IllegalStateException
     */
    public void deleteNode(Node node){
        if (node != root && node.parent == null){
            throw new IllegalStateException("\nNode is not the root and has no parent.");
        }

        // Case 1: Children are null (has no children)
        if (node.right == null && node.left == null){
            updateNodesChild(node, null);
        }

        // Case 2.1: Has one child (left child)
        else if (node.right == null){
            updateNodesChild(node, node.left);
        }

        // Case 2.2: has one child (right child)
        else if (node.left == null){
            updateNodesChild(node, node.right);
        }

        // Case 3: has two children
        else {
            Node leftSubTree = node.left;
            Node rightSubTree = node.right;

            updateNodesChild(node, leftSubTree);

            // find right-most child of left subtree
            Node rightMostChildOfLeftSubTree = leftSubTree;
            while (rightMostChildOfLeftSubTree.right != null) {
                rightMostChildOfLeftSubTree = rightMostChildOfLeftSubTree.right;
            }

            // add right subtree to right child
            rightMostChildOfLeftSubTree.right = rightSubTree;
            rightSubTree.parent = rightMostChildOfLeftSubTree;

        }


        node.parent = null;
        node.left = null;
        node.right = null;
    }

    /**
     * @param node
     * @param child
     */
    public void updateNodesChild(Node node, Node child){
        if (node == root){
            root = child;
            if (child != null){
                child.parent = null;
            }
            return;
        }

        if (node.parent.left == node){
            node.parent.left = child;
        } else if (node.parent.right == node){
            node.parent.right = child;
        } else {
            throw new IllegalStateException("\nThe Node with value: " + node.data + " is not its parent's right or left child");
        }

        if (child != null){
            child.parent = node.parent;
        }
    }

    /**
     * @return
     */
    public int height(){
        return height(root, 0);
    }

    /**
     * @param node
     * @param height
     * @return
     */
    public int height(Node node, int height){
        if (node == null){
            return height;
        }
        height++;
        int leftSubTreeHeight = height(node.left, height);
        int rightSubTreeHeight = height(node.right, height);

        return Math.max(leftSubTreeHeight, rightSubTreeHeight);
    }

    /**
     *
     */
    public void displayTree()
    {
        int treeHeight = height();
        java.util.Stack<Node> globalStack = new java.util.Stack<Node>();
        globalStack.push(root);

        double nBlanks = Math.pow(2, treeHeight+1);
        boolean isRowEmpty = false;
        while(!isRowEmpty)
        {
            java.util.Stack<Node> localStack = new java.util.Stack<Node>();
            isRowEmpty = true;
            System.out.println();
            for(int j=0; j<=nBlanks; j++)
                System.out.print(' ');
            while(!globalStack.isEmpty())
            {
                Node temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)   isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<=nBlanks*2-3; j++)
                    System.out.print(' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(!localStack.isEmpty())
                globalStack.push( localStack.pop() );
        } // end while isRowEmpty is false
        System.out.println();
        for(int j=0; j< treeHeight*30; j++)
            System.out.print('.');
    }

    /**
     * @return
     */
    public Node getRoot()
    {
        return root;
    }
}