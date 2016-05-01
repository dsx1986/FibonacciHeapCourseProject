package FHeap;

import java.util.List;

/**
 * Interface Graph for implementation
 *
 * @param <V>
 */
public interface Graph<V> {

    /**
     * @return
     */
    public int getSize();

    /**
     * @return
     */
    public List<V> getVertices();

    /**
     * @param index
     * @return
     */
    public V getVertex(int index);

    /**
     * @param v
     * @return
     */
    public int getIndex(V v);

    /**
     * @param index
     * @return
     */
    public List<Integer> getNeighbors(int index);

    /**
     * @param v
     * @return
     */
    public int getDegree(int v);

    /**
     *
     */
    public void printEdges();

    /**
     *
     */
    public void clear();

    /**
     * @param vertex
     */
    public void addVertex(V vertex);

    /**
     * @param u
     * @param v
     */
    public void addEdge(int u, int v);

    /**
     * @param v
     * @return
     */
    public AbstractGraph<V>.Tree dfs(int v);

    /**
     * @param v
     * @return
     */
    public AbstractGraph<V>.Tree bfs(int v);

}
