package task7;

public class Main {
    public static void main(String[] args) {
        int vertices = 6;
        PrimsAlgorithm.Graph graph = new PrimsAlgorithm.Graph(vertices);
        graph.addEdge('S', 'C', 8);
        graph.addEdge('S', 'A', 7);
        graph.addEdge('A', 'B', 6);
        graph.addEdge('A', 'C', 3);
        graph.addEdge('C', 'B', 4);
        graph.addEdge('C', 'D', 3);
        graph.addEdge('B', 'D', 2);
        graph.addEdge('B', 'T', 5);
        graph.addEdge('D', 'T', 2);

        graph.primsMaxSpanningTree();
        System.out.println("\n");
    }
}
