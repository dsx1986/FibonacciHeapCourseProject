package FHeap;

import java.util.List;

/**
 * @param <V>
 */
public class UnweightedGraph<V> extends AbstractGraph<V> {

    /**
     *
     */
    public UnweightedGraph() {
    }

    /**
     * @param edges
     * @param vertices
     */
    public UnweightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
    }

    /**
     * @param edges
     * @param vertices
     */
    public UnweightedGraph(List<Edge> edges, List<V> vertices) {
        super(edges, vertices);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /**
     * @param edges
     * @param numberOfVertices
     */
    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

}
