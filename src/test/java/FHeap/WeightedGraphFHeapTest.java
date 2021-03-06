package FHeap;

import org.junit.Before;
import org.junit.Test;

/**
 * Test whether the WeightedGraph using Fibonacci Heap as priority queue is successful
 */
public class WeightedGraphFHeapTest {

    String[] vertices;

    @Before
    public void setUp() throws Exception {

        vertices = new String[]{"Node 1", "Node 2", "Node 3", "Node 4", "Node 5", "Node 6", "Node 7", "Node 8", "Node 9", "Node 10", "Node 11", "Node 12"};

    }

    @Test
    public void testWeightedGraphFHeapin() {

        int[][] edges = {
                {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
                {6, 5, 983}, {6, 7, 214},
                {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
                {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
                {9, 8, 661}, {9, 11, 1187},
                {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
                {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };

        WeightedGraphFHeap<String> graph1 = new WeightedGraphFHeap<String>(edges, vertices);

        System.out.println(
                "The number of vertices in graph1: " + graph1.getSize());
        System.out.println("The vertex with index 1 is " + graph1.getVertex(1));
        System.out.println("The index for Node 10 is " + graph1.getIndex(
                "Node 10"));

        System.out.println("The edges for graph1:");
        graph1.printWeightedEdges();

        WeightedGraphFHeap<String>.ShortestPathTree tree1
                = graph1.getShortestPath(graph1.getIndex("Node 5"));
        tree1.printAllPaths();

        edges = new int[][]{
                {0, 1, 2}, {0, 3, 8},
                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
                {4, 2, 5}, {4, 3, 6}
        };

        WeightedGraphFHeap<Integer> graph2 = new WeightedGraphFHeap<Integer>(edges, 5);

        System.out.println("\nThe edges for graph2:");
        graph2.printWeightedEdges();

        WeightedGraphFHeap<Integer>.ShortestPathTree tree2
                = graph2.getShortestPath(3);
        tree2.printAllPaths();
    }

}
