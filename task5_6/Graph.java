package task5_6;


import java.util.*;

/**
 *
 */
public class Graph {

    /**
     *
     */
    private static class ConnectedVertex {
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
    private final Map<Character, LinkedList<ConnectedVertex>> adjacencyList = new HashMap<>();
    /**
     *
     */
    private final int[][] adjacencyMatrix;
    /**
     *
     */
    private final Map<Character, Integer> adjacencyMatrixMapping = new HashMap<>();
    /**
     *
     */
    private int mapping;
    /**
     *
     */
    private final int Vertices;

    /**
     * @param vertices
     */
    public Graph(int vertices){
        mapping = 0;
        Vertices = vertices;
        adjacencyMatrix = new int[Vertices][Vertices];
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

        int src = adjacencyMatrixMapping.get(sourceVertex);
        int dest = adjacencyMatrixMapping.get(targetVertex);
        adjacencyMatrix[src][dest] = weight;
    }

    /**
     * @param vertex
     */
    private void addVertex(char vertex){
        adjacencyList.put(vertex, new LinkedList<>());
        adjacencyMatrixMapping.put(vertex, mapping++);
    }


    /**
     *
     */
    public void printGraph(){
        for (char vertex : adjacencyList.keySet()) {
            LinkedList<ConnectedVertex> v = adjacencyList.get(vertex);
            for (ConnectedVertex connectedVertex : v) {
                System.out.println("vertex " + vertex + " points to " +
                        connectedVertex.targetVertex + " with weight " + connectedVertex.weight);
            }
        }
    }

    /**
     *
     */
    public void printMatrix(){
        System.out.format("%4s", "     ");
        for (char vertex : adjacencyMatrixMapping.keySet()) {
            System.out.format("%4s",vertex);
        }
        System.out.println("\n");

        for (char vertex : adjacencyMatrixMapping.keySet()) {
            System.out.format("%4s:", vertex);
            int i = adjacencyMatrixMapping.get(vertex);
            for (int  j = 0; j < Vertices; j++){
                System.out.format("%4s", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     *
     */
    public void DFSRecursive(){
        int totalVertices = adjacencyList.size();
        boolean [] visited = new boolean[totalVertices];
        for (char sourceVertex : adjacencyList.keySet()) {
            int index = adjacencyMatrixMapping.get(sourceVertex);
            if (!visited[index]){
                DFSRecursiveHelper(sourceVertex, index, visited);
            }
        }
    }

    /**
     * @param src
     * @param index
     * @param visited
     */
    private void DFSRecursiveHelper(char src,int index, boolean[] visited){
        visited[index] = true;

        System.out.print(src + " ");
        LinkedList<ConnectedVertex> linkedVertices = adjacencyList.get(src);
        for (ConnectedVertex connectedVertex: linkedVertices){
            char dest = connectedVertex.targetVertex;
            int idx = adjacencyMatrixMapping.get(dest);
            if (!visited[idx]){
                DFSRecursiveHelper(dest, idx, visited);
            }
        }
    }

    public void DFSIter(char vertex){
        int totalVertices = adjacencyList.size();
        boolean [] visited = new boolean[totalVertices];

        Stack<Character> stack = new Stack<>();

        stack.push(vertex);

        while(!stack.empty()){
            vertex = stack.peek();
            stack.pop();
            int index = adjacencyMatrixMapping.get(vertex);

            if(!visited[index]){
                System.out.print(vertex + " ");
                visited[index] = true;
            }


            LinkedList<ConnectedVertex> linkedVertices = adjacencyList.get(vertex);
            Iterator<ConnectedVertex> iter = linkedVertices.descendingIterator();
            while(iter.hasNext()) {
                char v = iter.next().targetVertex;
                int idx = adjacencyMatrixMapping.get(v);
                if (!visited[idx]) {
                    stack.push(v);
                }
            }
        }
    }

    /**
     *
     */
    public void DFSIterative()
    {
        int totalVertices = adjacencyList.size();
        boolean [] visited = new boolean[totalVertices];

        for (char sourceVertex : adjacencyList.keySet()) {
            int index = adjacencyMatrixMapping.get(sourceVertex);
            if (!visited[index]){
                DFSIterativeHelper(sourceVertex, visited);
            }
        }
    }

    /**
     * @param src
     * @param visited
     */
    private void DFSIterativeHelper(char src,  boolean [] visited){
        Stack<Character> stack = new Stack<>();

        stack.push(src);

        while(!stack.empty()){
            src = stack.peek();
            stack.pop();
            int index = adjacencyMatrixMapping.get(src);

            if(!visited[index]){
                System.out.print(src + " ");
                visited[index] = true;
            }

            LinkedList<ConnectedVertex> linkedVertices = adjacencyList.get(src);
            Iterator<ConnectedVertex> iterator = linkedVertices.descendingIterator();
            while(iterator.hasNext()) {
                char v = iterator.next().targetVertex;
                int idx = adjacencyMatrixMapping.get(v);
                if (!visited[idx]) {
                    stack.push(v);
                }
            }
        }
    }

    /**
     *
     */
    public void BFSIterative(){
        int totalVertices = adjacencyList.size();
        boolean [] visited = new boolean[totalVertices];

        for (char sourceVertex : adjacencyList.keySet()) {
            int index = adjacencyMatrixMapping.get(sourceVertex);
            if (!visited[index]){
                BFSIterativeHelper(sourceVertex, visited);
            }
        }

    }

    /**
     * @param src
     * @param visited
     */
    private void BFSIterativeHelper(char src, boolean[] visited) {
        Queue<Character> queue = new LinkedList<>();

        queue.add(src);

        while (!queue.isEmpty()){
            src = queue.remove();
            int index = adjacencyMatrixMapping.get(src);
            visited[index] = true;

            System.out.print(src + " ");

            //add unvisited adjacent vertices
            for (ConnectedVertex connectedVertex : adjacencyList.get(src)) {
                char v = connectedVertex.targetVertex;
                int idx = adjacencyMatrixMapping.get(v);
                if (!visited[idx]) {
                    visited[idx] = true;
                    queue.add(v);
                }
            }
        }
    }

    public void BFSIter(char vertex){
        int totalVertices = adjacencyList.size();
        boolean [] visited = new boolean[totalVertices];

        Queue<Character> queue = new LinkedList<>();

        queue.add(vertex);

        while(!queue.isEmpty()){
            vertex = queue.poll();
            int index = adjacencyMatrixMapping.get(vertex);
            System.out.print(vertex + " ");
            visited[index] = true;

            for (ConnectedVertex connectedVertex : adjacencyList.get(vertex)) {
                char v = connectedVertex.targetVertex;
                int idx = adjacencyMatrixMapping.get(v);
                if (!visited[idx]) {
                    queue.add(v);
                    visited[idx] = true;
                }
            }
        }
    }
}


