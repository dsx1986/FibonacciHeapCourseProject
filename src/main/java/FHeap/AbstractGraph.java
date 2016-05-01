package FHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <V>
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    /**
     *
     */
    protected List<V> vertices = new ArrayList<V>();

    /**
     *
     */
    protected List<List<Integer>> neighbors = new ArrayList<List<Integer>>();

    /**
     *
     */
    protected AbstractGraph() {
    }

    /**
     * @param edges
     * @param vertices
     */
    protected AbstractGraph(int[][] edges, V[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);

        }

        createAdjacencyLists(edges, vertices.length);
    }

    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }

    /**
     * @param edges
     * @param vertices
     */
    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            this.vertices.add(vertices.get(i));
        }

        createAdjacencyLists(edges, vertices.size());
    }

    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (Edge edge : edges) {
            neighbors.get(edge.u).add(edge.v);
        }

    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));
        }

        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));
        }

        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Override interface Graph
     *
     * @return
     */
    @Override
    public int getSize() {
        return vertices.size();
    }

    /**
     * @return
     */
    @Override
    public List<V> getVertices() {
        return vertices;

    }

    /**
     * @param index
     * @return
     */
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    /**
     * @param index
     * @return
     */
    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    /**
     *
     */
    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.println(getVertex(u) + "(" + u + "):");
            for (int j = 0; j < neighbors.get(u).size(); j++) {
                System.out.println("(" + u + "," + neighbors.get(u).get(j) + ")");
            }
        }
    }

    /**
     *
     */
    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    /**
     * @param vertex
     */
    @Override
    public void addVertex(V vertex) {
        vertices.add(vertex);
        neighbors.add(new ArrayList<Integer>());
    }

    /**
     * @param u
     * @param v
     */
    @Override
    public void addEdge(int u, int v) {
        neighbors.get(u).add(v);
        neighbors.get(v).add(u);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrder, isVisited);

        return new Tree(v, parent, searchOrder);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;

        java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<Integer>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (int w : neighbors.get(u)) {
                if (!isVisited[w]) {
                    queue.offer(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;

        for (int i : neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v;
                dfs(i, parent, searchOrder, isVisited);
            }
        }


    }

    /**
     * Definition
     * Inner class Edge in AbstraceGraph
     */
    public static class Edge {

        /**
         *
         */
        public int u;

        /**
         *
         */
        public int v;

        /**
         * @param u
         * @param v
         */
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

    }

    /**
     * Tree inner class
     */
    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        /**
         * @param root
         * @param parent
         * @param searchOrder
         */
        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        /**
         * @return
         */
        public int getRoot() {
            return root;
        }

        /**
         * @param v
         * @return
         */
        public int getParent(int v) {
            return parent[v];
        }

        /**
         * @return
         */
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        /**
         * @return
         */
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        /**
         * @param index
         */
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " +
                    vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }

        /**
         * @param index
         * @return
         */
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<V>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);

            return path;
        }

        /**
         *
         */
        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " +
                            vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }

}
