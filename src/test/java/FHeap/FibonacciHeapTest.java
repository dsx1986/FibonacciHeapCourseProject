package FHeap;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

import org.junit.*;


/**
 * @author shuangxingdai
 */
public class FibonacciHeapTest {

    public FibonacciHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void FibonacciHeapTest() {
//arrange

        FibonacciHeap FHeap = new FibonacciHeap();
        FibonacciHeapNode<Integer> x;

        int i;
        i = (int) Math.round(Math.random() * 100);
        x = new FibonacciHeapNode<Integer>(i);

        FHeap.insert(x, i);

        Assert.assertTrue(FHeap.size() == 1);


        i = (int) Math.round(Math.random() * 100);
        x = new FibonacciHeapNode<Integer>(i);

        FHeap.insert(x, i);

        Assert.assertTrue(FHeap.size() == 2);

        i = (int) Math.round(Math.random() * 100);
        x = new FibonacciHeapNode<Integer>(i);

        FHeap.insert(x, i);

        Assert.assertTrue(FHeap.size() == 3);

        FHeap.removeMin();

        Assert.assertTrue(FHeap.size() == 2);

        FHeap.clear();

        Assert.assertTrue(FHeap.size() == 0);

    }
}
