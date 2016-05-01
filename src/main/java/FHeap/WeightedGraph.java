package FHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Standard weighted graph using priority queue for neighbor list
 *
 * @param <V>
 */
public class WeightedGraph<V> extends AbstractGraph<V> {

    //adjacent lists using priority queue
    private List<PriorityQueue<WeightedEdge>> queues = new ArrayList<PriorityQueue<WeightedEdge>>();

    /**
     *
     */
    public WeightedGraph() {
    }

    /**
     * @param edges
     * @param vertices
     */
    public WeightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
        createQueues(edges, vertices.length);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public void createQueues(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];
            queues.get(u).offer(new WeightedEdge(u, v, weight));
        }
    }

    /**
     * @param edges
     * @param vertices
     */
    public WeightedGraph(List<WeightedEdge> edges, List<V> vertices) {
        super((List) edges, vertices);
        createQueues(edges, vertices.size());
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public void createQueues(List<WeightedEdge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (WeightedEdge edge : edges) {
            queues.get(edge.u).offer(edge);
        }
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super((List) edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     *
     */
    public void printWeightedEdges() {
        for (int i = 0; i < queues.size(); i++) {
            System.out.print(getVertex(i) + " (" + i + "): ");
            for (WeightedEdge edge : queues.get(i)) {
                System.out.print("(" + edge.u +
                        ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * @return
     */
    public List<PriorityQueue<WeightedEdge>> getWeightedEdges() {
        return queues;
    }

    /**
     *
     */
    public void clear() {
        vertices.clear();
        neighbors.clear();
        queues.clear();
    }

    /**
     * @param vertex
     */
    public void addVertex(V vertex) {
        super.addVertex(vertex);
        queues.add(new PriorityQueue<WeightedEdge>());
    }

    /**
     * @param u
     * @param v
     * @param weight
     */
    public void addEdge(int u, int v, int weight) {
        super.addEdge(u, v);
        queues.get(u).add(new WeightedEdge(u, v, weight));
        queues.get(v).add(new WeightedEdge(v, u, weight));
    }

    /**
     * @param sourceVertex
     * @return
     */
    public ShortestPathTree getShortestPath(int sourceVertex) {

        List<Integer> T = new ArrayList<Integer>();

        T.add(sourceVertex);

        int numberOfVertices = vertices.size();

        int[] parent = new int[numberOfVertices];
        parent[sourceVertex] = -1;

        double[] cost = new double[numberOfVertices];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.MAX_VALUE; // Initial cost set to infinity
        }
        cost[sourceVertex] = 0;

        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);


        while (T.size() < numberOfVertices) {
            int v = -1;
            double smallestCost = Double.MAX_VALUE;
            for (int u : T) {
                while (!queues.get(u).isEmpty() &&
                        T.contains(queues.get(u).peek().v)) {
                    queues.get(u).remove();
                }

                if (queues.get(u).isEmpty()) {

                    continue;
                }

                WeightedEdge e = queues.get(u).peek();
                if (cost[u] + e.weight < smallestCost) {
                    v = e.v;
                    smallestCost = cost[u] + e.weight;
                    parent[v] = u;
                }
            }

            T.add(v);
            cost[v] = smallestCost;
        }


        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    //Dijkstra

    /**
     * Clone an array of queues
     */
    private List<PriorityQueue<WeightedEdge>> deepClone(
            List<PriorityQueue<WeightedEdge>> queues) {
        List<PriorityQueue<WeightedEdge>> copiedQueues =
                new ArrayList<PriorityQueue<WeightedEdge>>();

        for (int i = 0; i < queues.size(); i++) {
            copiedQueues.add(new PriorityQueue<WeightedEdge>());
            for (WeightedEdge e : queues.get(i)) {
                copiedQueues.get(i).add(e);
            }
        }

        return copiedQueues;
    }

    /**
     *
     */
    public class ShortestPathTree extends Tree {
        private double[] cost;

        /**
         * @param source
         * @param parent
         * @param searchOrder
         * @param cost
         */
        public ShortestPathTree(int source, int[] parent,
                                List<Integer> searchOrder, double[] cost) {
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        /**
         * @param v
         * @return
         */
        public double getCost(int v) {
            return cost[v];
        }

        /**
         *
         */
        public void printAllPaths() {
            System.out.println("All shortest paths from " +
                    vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) {
                printPath(i);
                System.out.println("(cost: " + cost[i] + ")");
            }
        }
    }

}
