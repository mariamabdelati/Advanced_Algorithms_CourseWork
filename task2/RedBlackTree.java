package task2;

public class RedBlackTree {
    private Node root;


    static final boolean BLACK = false;
    static final boolean RED = true;


    public RedBlackTree() {
        root = null;
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    private Node searchTreeHelper(Node node, int key) {
        Node current = node;
        while (current != null) {
            if (key < current.data) {
                current = current.left;
            } else if (key > current.data){
                current = current.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private void updateParentChild(Node parent, Node originalChild, Node updatedChild) {
        if (parent == null){ // parent is the root
            root = updatedChild;
        } else if (parent.left == originalChild){ // the og child is left child
            parent.left = updatedChild;
        } else {
            parent.right  = updatedChild;
        }

        if (updatedChild != null){
            updatedChild.parent = parent;
        }
    }

    //Rotations
    private void rightRotate(Node original) {
        // store the values for the original parent, left child and
        // the right child of the that left child
        Node parent = original.parent;
        Node leftChild = original.left;
        original.left = leftChild.right;

        if (leftChild.right != null){
            leftChild.right.parent = original;
        }
        // Perform rotation: the original root becomes the right node of the root
        // the leftChild is the original left child
        // in a right left rotation, the right child of the og node's left child
        // becomes the left child of the og node
        leftChild.right = original;
        original.parent = leftChild;

        // Update parent
        updateParentChild(parent, original, leftChild);
    }

    private void leftRotate(Node original) {
        Node parent = original.parent; // store parent
        Node rightChild = original.right; // store the right child of the og node
        original.right = rightChild.left;

        if(rightChild.left != null){
            rightChild.left.parent = original; // setting original as the parent of the left subtree of og
        }

        rightChild.left = original;
        original.parent = rightChild;

        updateParentChild(parent, original, rightChild);
    }


    //insert
    public void insert(int key) {
        /* 1. Perform the normal BST insertion */
        Node parent = null;
        Node node = root;


        // traversing the tree
        while (node != null) {
            parent = node;
             if (key > node.data) {
                 node = node.right;
            } else if (key < node.data){
                 node = node.left;
            } else {
                 System.out.println("The RedBlack Tree already " +
                         "contains a node with value " + key);
            }
        }

        // inserting the node
        Node addedNode = new Node(key);
        addedNode.color = RED;
        if (parent == null) {
            root = addedNode;
        } else if (addedNode.data > parent.data) {
            parent.right = addedNode;
        } else {
            parent.left = addedNode;
        }

        addedNode.parent = parent;

        fixInsertion(addedNode);
    }

    private void fixInsertion(Node node) {
        Node parentNode = node.parent;
        //case A: if parent is null, base case
        if (parentNode == null) {
            // enforce black root
            node.color = BLACK;
            return;
        }

        // Case B: parent is black, no fixing needed
        if (parentNode.color == BLACK) {
            return;
        }

        // Case C: parent is red
        Node grandparentNode = parentNode.parent;
        Node uncleNode = getUncle(parentNode);

        // C.1 if uncle is red, parent, uncle and grandparent are recolored
        if (uncleNode != null && uncleNode.color == RED){
            parentNode.color = BLACK;
            grandparentNode.color = RED;
            uncleNode.color = BLACK;

            // to enforce the rule that red nodes cannot have red children
            // the recolor and rebalance method is called on the grandparent
            // which is  red now
            fixInsertion(grandparentNode);

        // C.2,C.3 if the left child of the grandparent is the parent
        } else if (parentNode == grandparentNode.left){

            // uncle is black and node is inner child  of grandparent
            if (node == parentNode.right){
                leftRotate(parentNode);

                // set the parent to be the new root node of the subtree
                parentNode = node;
            }

            // node is  an outer child
            rightRotate(grandparentNode);

            // recolor grandparent and parent
            parentNode.color = BLACK;
            grandparentNode.color = RED;

        // C.2,C.3 parent is right child of grandparent
        } else {
            // the node is left-right (inner child) of grandparent
            if (node == parentNode.left){
                rightRotate(parentNode);

                // set the parent to be the new node of the rotated tree
                parentNode = node;
            }

            // node is right-right of its grandparent
            leftRotate(grandparentNode);

            // recolor parent and grandparent
            parentNode.color = BLACK;
            grandparentNode.color = RED;
        }
    }

    private Node getUncle(Node parentNode) {
        Node grandparentNode = parentNode.parent;
        if (grandparentNode.left == parentNode) {
            return grandparentNode.right;
        } else if (grandparentNode.right == parentNode) {
            return grandparentNode.left;
        } else {
            throw new IllegalStateException("The parent given is not a " +
                    "child for the grandparent node.");
        }
    }


    public void deleteNode(int key) {
        Node node = root;
        Node tempNode;

        while (node != null && node.data != key){
            if (key < node.data){
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null) {
            System.out.println("The RedBlack Tree does not " +
                    "contain a node with value " + key);
            return;
        }

        boolean colorOfDeletedNode = node.color;
        // if node has 0 children or 1 right child
        if (node.left == null){
            tempNode = node.right;
            updateParentChild(node.parent, node, node.right);
        } else if (node.right == null) {
            tempNode = node.left;
            updateParentChild(node.parent, node, node.left);
        } else {
            //
            Node rightSubtreeMinimum = getMinimum(node.right);

            colorOfDeletedNode = rightSubtreeMinimum.color;
            tempNode = rightSubtreeMinimum.right;

            if (rightSubtreeMinimum.parent == node){
                tempNode.parent = rightSubtreeMinimum;
            } else {
                updateParentChild(rightSubtreeMinimum.parent, rightSubtreeMinimum, rightSubtreeMinimum.right);
                rightSubtreeMinimum.right = node.right;
                rightSubtreeMinimum.right.parent = rightSubtreeMinimum;
            }

            updateParentChild(node.parent, node, rightSubtreeMinimum);
            rightSubtreeMinimum.left = node.left;
            rightSubtreeMinimum.left.parent = rightSubtreeMinimum;
            rightSubtreeMinimum.color = node.color;
        }
        
        if (colorOfDeletedNode == BLACK){
            fixDeletion(tempNode);
        }
    }

    private void fixDeletion(Node node) {
        // Balance the tree after deletion of a node
        //Case A
        if (node == root) {
            node.color = BLACK;
        }

        Node sibling;

        sibling = getSibling(node);

        // Case B
        if (sibling.color == RED) {
            sibling.color = BLACK;
            node.parent.color = RED;

            if (node == node.parent.left) {
                leftRotate(node.parent);
            } else {
                rightRotate(node.parent);
            }
            // Get new sibling for to be fixed in the coming steps
            sibling = getSibling(node); 
        }

        // Case C
        if (sibling.left.color == BLACK && sibling.right.color == BLACK) {
            sibling.color = RED;

            // Case C.1
            if (node.parent.color == RED) {
                node.parent.color = BLACK;

            // Case C.2
            } else {
                fixDeletion(node.parent);
            }
        } else {
            // Case C.3
            boolean nodeIsLeftChild = node == node.parent.left;
            if (nodeIsLeftChild && sibling.right.color == BLACK) {
                sibling.left.color = BLACK;
                sibling.color = RED;
                rightRotate(sibling);
                sibling = node.parent.right;
            } else if (!nodeIsLeftChild && sibling.left.color == BLACK) {
                sibling.right.color = BLACK;
                sibling.color = RED;
                leftRotate(sibling);
                sibling = node.parent.left;
            }

            // Case C.4
            sibling.color = node.parent.color;
            node.parent.color = BLACK;
            if (nodeIsLeftChild) {
                sibling.right.color = BLACK;
                leftRotate(node.parent);
            } else {
                sibling.left.color = BLACK;
                rightRotate(node.parent);
            }
        }
    }

    private Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node getSibling(Node node) {
        Node parentNode = node.parent;
        if (node == parentNode.left) {
            return parentNode.right;
        } else if (node == parentNode.right) {
            return parentNode.left;
        } else {
            throw new IllegalStateException("Parent is does not belong " +
                    "to given grandparent");
        }
    }

    public void displayTree()
    {
        int treeHeight = 4;
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
                String color = "";
                if(temp != null)
                {
                    color = temp.color ? "RED" : "BLACK";
                    System.out.print(temp.data + " " + color);
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
        for(int j=0; j< treeHeight*20; j++)
            System.out.print('.');
        System.out.println();
    }
}