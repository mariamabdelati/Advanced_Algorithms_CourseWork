package task5_6;

/**
 *
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int vertices = 5;
        Graph graph = new Graph(vertices);
        graph.addEdge('A', 'B', 5);
        graph.addEdge('A', 'C', 2);
        graph.addEdge('B', 'D', 1);
        graph.addEdge('B', 'E', 7);
        graph.addEdge('C', 'D', 5);
        graph.addEdge('C', 'E', 8);
        graph.addEdge('D', 'E', 5);

        System.out.println("\nThe following represents the Graph Edges using Adjacency List Approach: \n");
        graph.printGraph();
        System.out.println("\n");

        System.out.println("The Adjacency Matrix representing the Graph is: \n");
        graph.printMatrix();
        System.out.println("\n");

        System.out.println("Breadth First Traversal (Iterative): \n");
        graph.BFSIterative();
        System.out.println("\n");

        System.out.println("Breadth First Traversal from Specific Vertex (Iterative): ");
        System.out.println("Starting from Vertex A: \n");
        graph.BFSIter('A');
        System.out.println("\n");


        System.out.println("Depth First Traversal (Recursive): \n");
        graph.DFSRecursive();
        System.out.println("\n");

        System.out.println("Depth First Traversal (Iterative): \n");
        graph.DFSIterative();
        System.out.println("\n");


        System.out.println("Depth First Traversal from Specific Vertex (Iterative): ");
        System.out.println("Starting from Vertex A: \n");
        graph.DFSIter('A');
        System.out.println("\n");
    }
}

