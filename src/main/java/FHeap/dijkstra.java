package FHeap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class dijkstra {

    static int[] vertices;
    static int numberOfVertices;
    static int[][] edges;
    static int source; //source
    static List<long[]> timeCompare = new ArrayList<long[]>();

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

//        args = new String[]{"-r", "10", "50", "5"}; //for my own test
//        args = new String[]{"-f", "input.txt"}; //for my own test
//        args = new String[]{"-s", "input.txt"}; //for my own test
//        selectInputMode(args);
        //Test performance
        double[] densityTest = new double[]{20.0, 50.0, 75.0, 100.0};
        int[] nodeTest = new int[]{100};
        testPerformance(nodeTest, densityTest);
    }

    private static void testPerformance(int[] nodeNum, double[] density) throws IOException {
        String[] args;
        for (int k = 0; k < density.length; k = k + 1) {
            for (int i = 0; i < nodeNum.length; i = i + 1) {

                args = new String[]{"-r", String.format("%d", nodeNum[i]), String.format("%f", density[k]), "5"}; //for my own test
                System.out.println("\nTotal node number:" + nodeNum[i] + " density: " + density[k] + "%");
                selectInputMode(args);
            }
            printTime();
        }

    }

    private static void printTime() {
        System.out.println("All the time spend in milliseconds:");
        for (int j = 0; j < timeCompare.size(); j++) {
            for (int m = 0; m < timeCompare.get(j).length; m++) {
                System.out.print(String.format("%d", timeCompare.get(j)[m]) + " ");
            }
            System.out.print("\n");
        }
    }

    private static void simpleSchemeInputMode(String[] args) throws IOException {
        readGraphFile(args);
        simpleSchemeGraph();
    }

    private static void fibonacciSchemeInputMode(String[] args) throws IOException {
        readGraphFile(args);
        fibonacciSchemeGraph();
    }

    private static void randomMode(String[] args) {
        int n = Integer.parseInt(args[1]);// number of vertices
        setVertices(n);
        double d = Double.parseDouble(args[2]); // density d%
        source = Integer.parseInt(args[3]); // source node number

        //keep generating random edges until all vertices are connected
        do {
            generateRandomEdges(n, d);
        } while (!testConnectivity()); //verify connectivity

        //then do both priorityQueue, simple scheme and F-scheme
//        long time1 = priorityQueueSchemeGraph();// for comparison
        long time2 = simpleSchemeGraph();
        long time3 = fibonacciSchemeGraph();
        timeCompare.add(new long[]{time2, time3});
    }

    private static void generateRandomEdges(int n, double d) {
        //generate edges
        //edges for a n node graph is n*(n-1)/2
        int numEdges = (int) (n * (n - 1) / 2 * d / 100);
        edges = new int[numEdges * 2][3];
        ArrayList<ArrayList<Integer>> edgesList = new ArrayList<ArrayList<Integer>>();//put all list
        for (int m = 0; m < numEdges; m++) {
            ArrayList<Integer> tempEdgePair = new ArrayList<Integer>();
            do {
                int i = (int) (Math.random() * n);
                int j = (int) (Math.random() * n);
                while (i >= j) {// we need a effective edge pair like (2,5), not (3,3) or (3,2)
                    i = (int) (Math.random() * n);
                    j = (int) (Math.random() * n);
                }

                tempEdgePair.clear();
                tempEdgePair.add(i);
                tempEdgePair.add(j);
            } while (edgesList.contains(tempEdgePair));
            edgesList.add(tempEdgePair);
        }

        for (int m2 = 0; m2 < numEdges; m2++) {
            edges[m2][0] = edgesList.get(m2).get(0);
            edges[m2][1] = edgesList.get(m2).get(1);
            edges[m2][2] = (int) (Math.random() * 1000) + 1;
//            System.out.println("Edge " + m2 + " is:( " + edges[m2][0] + ", " + edges[m2][1] + ", " + edges[m2][2] + " );");
            // add (j,i) edge
            edges[m2 + numEdges][0] = edgesList.get(m2).get(1);
            edges[m2 + numEdges][1] = edgesList.get(m2).get(0);
            edges[m2 + numEdges][2] = edges[m2][2];
//            System.out.println("Edge " + (m2 + numEdges) + " is:( " + edges[m2 + numEdges][0] + ", " + edges[m2 + numEdges][1] + ", " + edges[m2 + numEdges][2] + " );");
        }

//        System.out.println("\nEdges generated.");
    }

    private static void readGraphFile(String[] args) throws FileNotFoundException, IOException {

        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        String filename = currentDir + "/" + args[1];
        System.out.println(filename);
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            int x, n, m;
            source = input.nextInt(); //source node num
            n = input.nextInt();//number of vertices
            setVertices(n);
            m = input.nextInt(); //number of edges
            edges = new int[m * 2][3];
            int v1, v2, cost;
            for (int j = 0; j < m; j++) {
                v1 = input.nextInt();
                v2 = input.nextInt();
                cost = input.nextInt();
                edges[j][0] = v1;
                edges[j][1] = v2;
                edges[j][2] = cost;
                // add (j,i) edge
                edges[j + m][0] = v2;
                edges[j + m][1] = v1;
                edges[j + m][2] = cost;
            }
        }

    }

    private static void setVertices(int n) {
        numberOfVertices = n;
        vertices = new int[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = i; //initialize vertices
        }
    }

    /**
     * Where to go? according to input arguments
     */
    private static void selectInputMode(String[] args) throws IOException {
        //
        switch (args[0]) {
            case "-r":
                if (args.length == 4) {
                    randomMode(args);
                }
                break;
            case "-s":
                simpleSchemeInputMode(args);
                break;
            case "-f":
                fibonacciSchemeInputMode(args);
                break;
            default:
                System.out.println("Input illegal!\n");
                System.exit(1);
        }

    }

    private static long priorityQueueSchemeGraph() {
        WeightedGraph<Integer> graph1 = new WeightedGraph<Integer>(
                edges, numberOfVertices);
        System.out.println("\nWeighted Graph using Priority Queue:");
//        System.out.println("\nThe edges for graph:");
//        graph1.printWeightedEdges();
        long start = 0, stop = 0, time = 0;
        start = System.currentTimeMillis();
        WeightedGraph<Integer>.ShortestPathTree tree1 = graph1.getShortestPath(source);
        stop = System.currentTimeMillis();
        time = stop - start;
        System.out.println("The time used is:" + String.format("%1.10G", time / 1000000.0) + " seconds.\n");
//        tree1.printAllPaths();
        return time;
    }

    private static long simpleSchemeGraph() {
        WeightedGraphArray<Integer> graph1 = new WeightedGraphArray<Integer>(
                edges, numberOfVertices);
//        System.out.println("\nWeighted Graph using array implemented Priority Queue:");
//        System.out.println("\nThe edges for graph:");
//        graph1.printWeightedEdges();

        long start = 0, stop = 0, time = 0;
        start = System.currentTimeMillis();
        WeightedGraphArray<Integer>.ShortestPathTree tree1 = graph1.getShortestPath(source);
        stop = System.currentTimeMillis();
        time = stop - start;
//        System.out.println("The time used is:" + String.format("%1.10G", time / 1000000.0) + " seconds.\n");
//        tree1.printAllPaths();
        return time;
    }

    private static long fibonacciSchemeGraph() {
        WeightedGraphFHeap<Integer> graph1 = new WeightedGraphFHeap<Integer>(
                edges, numberOfVertices);
//        System.out.println("\nWeighted Graph using Fibonacci Heap implemented Priority Queue:");
//        System.out.println("\nThe edges for graph:");
//        graph1.printWeightedEdges();

        long start = 0, stop = 0, time = 0;
        start = System.currentTimeMillis();
        WeightedGraphFHeap<Integer>.ShortestPathTree tree1 = graph1.getShortestPath(source);
        stop = System.currentTimeMillis();
        time = stop - start;
//        System.out.println("The time used is:" + String.format("%1.10G", time / 1000000.0) + " seconds.\n");
//        tree1.printAllPaths();
        return time;
    }

    /**
     * Test connectivity of the graph by randomly generated edges, stop until
     * connected
     */
    private static boolean testConnectivity() {

        Graph<Integer> graph
                = new WeightedGraph<Integer>(edges, numberOfVertices);
        AbstractGraph<Integer>.Tree dfs
                = graph.dfs(source);

//        List<Integer> searchOrders = dfs.getSearchOrder();
//        System.out.println(dfs.getNumberOfVerticesFound()
//                + " vertices are searched in this order:");
//        for (int i = 0; i < searchOrders.size(); i++) {
//            System.out.println(graph.getVertex(searchOrders.get(i)));
//        }
//
//        for (int i = 0; i < searchOrders.size(); i++) {
//            if (dfs.getParent(i) != -1) {
//                System.out.println("parent of " + graph.getVertex(i)
//                        + " is " + graph.getVertex(dfs.getParent(i)));
//            }
//        }
        if (dfs.getNumberOfVerticesFound() < numberOfVertices) {
            System.out.println("\nFound " + dfs.getNumberOfVerticesFound() + "connected vertices.");
            return false;
        } else {
            return true;
        }
    }

}
