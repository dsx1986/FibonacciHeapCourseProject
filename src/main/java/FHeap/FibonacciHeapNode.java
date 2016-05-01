package FHeap;

public class FibonacciHeapNode<T> {

    FibonacciHeapNode<T> child, left, right, parent;
    T element;
    boolean mark;
    int key;//key put the element(WeightedEdge).weight, int in our case
    int degree;

    public FibonacciHeapNode(T element) {
        this.right = this;
        this.left = this;
        this.element = element;
    }

    public final int getKey() {
        return key;
    }

    public final T getData() {
        return element;
    }

    public String toString() {
        return Double.toString(key);
    }
}
