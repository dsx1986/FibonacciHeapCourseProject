package FHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Weighted graph using priority queue implemented by array for neighbor list
 *
 * @param <V>
 */
public class WeightedGraphArray<V> extends AbstractGraph<V> {

    //adjacent lists using priority queue
    private List<ArrayPriorityQueue<WeightedEdge>> queues = new ArrayList<ArrayPriorityQueue<WeightedEdge>>();

    /**
     *
     */
    public WeightedGraphArray() {
    }

    /**
     * @param edges
     * @param vertices
     */
    public WeightedGraphArray(int[][] edges, V[] vertices) {
        super(edges, vertices);
        createQueues(edges, vertices.length);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public void createQueues(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new ArrayPriorityQueue<WeightedEdge>());
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
     * @param numberOfVertices
     */
    public WeightedGraphArray(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public void createQueues(List<WeightedEdge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new ArrayPriorityQueue<WeightedEdge>());
        }

        for (WeightedEdge edge : edges) {
            queues.get(edge.u).offer(edge);
        }
    }

    /**
     *
     */
    public void printWeightedEdges() {
        for (int i = 0; i < queues.size(); i++) {
            System.out.print(getVertex(i) + " (" + i + "): ");
            WeightedEdge edge;
            for (int j = 0; j < queues.get(i).size(); j++) {
                edge = queues.get(i).get(j);
                System.out.print("(" + edge.u
                        + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * @return
     */
    public List<ArrayPriorityQueue<WeightedEdge>> getWeightedEdges() {
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
        queues.add(new ArrayPriorityQueue<WeightedEdge>());
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
    public WeightedGraphArray.ShortestPathTree getShortestPath(int sourceVertex) {

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

        List<ArrayPriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

        while (T.size() < numberOfVertices) {
            int v = -1;
            double smallestCost = Double.MAX_VALUE;
            for (int u : T) {
                while (!queues.get(u).isEmpty()
                        && T.contains(queues.get(u).peek().v)) {
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

        return new WeightedGraphArray.ShortestPathTree(sourceVertex, parent, T,
                cost);
    }

    //Dijkstra 

    private List<ArrayPriorityQueue<WeightedEdge>> deepClone(
            List<ArrayPriorityQueue<WeightedEdge>> queues) {
        List<ArrayPriorityQueue<WeightedEdge>> copiedQueues
                = new ArrayList<ArrayPriorityQueue<WeightedEdge>>();

        for (int i = 0; i < queues.size(); i++) {
            copiedQueues.add(new ArrayPriorityQueue<WeightedEdge>());
            WeightedEdge edge;
            for (int j = 0; j < queues.get(i).size(); j++) {
                edge = queues.get(i).get(j);
                copiedQueues.get(i).add(edge);
            }
        }

        return copiedQueues;
    }

    /**
     *
     */
    public class ShortestPathTree extends AbstractGraph.Tree {

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
//            System.out.println("All shortest paths from "
//                    + vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) {
//                printPath(i);
//                System.out.println("(cost: " + cost[i] + ")");
                System.out.println(cost[i]);
            }
        }
    }

}
