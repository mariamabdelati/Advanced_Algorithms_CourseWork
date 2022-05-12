package task7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 */
public class PrimsAlgorithm {
    /**
     *
     */
    private static class ConnectedVertex{
        char targetVertex;
        int weight;

        /**
         * @param targetVertex
         * @param weight
         */
        public ConnectedVertex(char targetVertex, int weight) {
            this.targetVertex = targetVertex;
            this.weight = weight;
        }
    }

    /**
     *
     */
    private static class SpanTreeResult {
        char parent;
        int weight;
    }

    /**
     *
     */
    static class Graph {
        private final int Vertices;
        private final Map<Character, LinkedList<ConnectedVertex>> adjacencyList = new HashMap<>();
        private final Map<Character, Integer> vertexMapping = new HashMap<>();
        private final Map<Integer, Character> integerMapping = new HashMap<>();
        private int mapping;
        private int integerMap;

        /**
         * @param vertices
         */
        public Graph(int vertices) {
            mapping = 0;
            integerMap = 0;
            this.Vertices = vertices;
        }

        /**
         * @param vertex
         */
        private void addVertex(char vertex){
            adjacencyList.put(vertex, new LinkedList<>());
            vertexMapping.put(vertex, mapping++);
            integerMapping.put(integerMap++, vertex);
        }

        /**
         * @param sourceVertex
         * @param targetVertex
         * @param weight
         */
        public void addEdge(char sourceVertex, char targetVertex, int weight){
            if (!adjacencyList.containsKey(sourceVertex)){
                addVertex(sourceVertex);
            }

            if (!adjacencyList.containsKey(targetVertex)){
                addVertex(targetVertex);
            }

            ConnectedVertex connectedVertex = new ConnectedVertex(targetVertex, weight);
            adjacencyList.get(sourceVertex).add(connectedVertex);

            connectedVertex = new ConnectedVertex(sourceVertex, weight);
            adjacencyList.get(targetVertex).add(connectedVertex); // for undirected graph
        }


        /**
         *
         */
        public void primsMaxSpanningTree() {
            // array to keep track of which set of vertices are included in the spanning tree
            boolean[] maxSpanningTree = new boolean[Vertices];

            //array to store the MST that has been constructed
            SpanTreeResult[] spanTreeResult = new SpanTreeResult[Vertices];

            // array to help select the edge with the maximum weight
            int[] priorityKeys = new int[Vertices];

            //Initialize all the priorityKeys to negative infinity and
            //initialize resultSet for all the vertices
            for (char vertex : vertexMapping.keySet()) {
                int eq = vertexMapping.get(vertex);
                priorityKeys[eq] = Integer.MIN_VALUE;
                spanTreeResult[eq] = new SpanTreeResult();
            }

            //Initialize priority queue
            //override the comparator to do the sorting based priorityKeys
            PriorityQueue<ConnectedVertex> pq = new PriorityQueue<>(Vertices, (edge1, edge2) -> {
                int weight1 = edge1.weight;
                int weight2 = edge2.weight;
                return weight2-weight1;
            });
            priorityKeys[0] = 0;
            pq.add(new ConnectedVertex(integerMapping.get(0), 0));

            while (!pq.isEmpty()) {
                char extractedEdge = pq.poll().targetVertex;
                int idx = vertexMapping.get(extractedEdge);
                maxSpanningTree[idx] = true;

                LinkedList<ConnectedVertex> edgesList = adjacencyList.get(extractedEdge);

                for (ConnectedVertex edge : edgesList) {
                    char v = edge.targetVertex;
                    int index = vertexMapping.get(v);
                    if (!maxSpanningTree[index]) {
                        if (edge.weight > priorityKeys[index]) {
                            pq.add(edge);

                            spanTreeResult[index].parent = extractedEdge;
                            spanTreeResult[index].weight = edge.weight;

                            priorityKeys[index] = edge.weight;
                        }
                    }
                }
            }

            printMST(spanTreeResult);
        }


        /**
         * @param resultSet
         */
        private void printMST(SpanTreeResult[] resultSet){
            int total_max_weight = 0;
            System.out.println("\nMaximum Spanning Tree: \n");
            for (int i = 1; i < adjacencyList.size() ; i++) {
                System.out.println("Edge: " +  resultSet[i].parent + " - " + integerMapping.get(i) +
                        "    Weight: " + resultSet[i].weight);
                total_max_weight += resultSet[i].weight;
            }
            System.out.println("\nTotal maximum weight: " + total_max_weight);
        }
    }

}