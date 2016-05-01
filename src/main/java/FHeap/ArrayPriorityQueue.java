package FHeap;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @param <Ele>
 */
public class ArrayPriorityQueue<Ele extends Comparable<Ele>> {

    private int sizeArrayList = 0; //size of arrayList, actual element in it,not the capacity

    private ArrayList<Ele> arrayList = new ArrayList<Ele>();

    /**
     * @return
     */
    public Ele dequeue() {
        int minElePosition = getMinElePos(arrayList);
        Ele minEle = arrayList.get(minElePosition);
        arrayList.remove(minElePosition);//remove the first one, also the smallest
        sizeArrayList--;
        return minEle;
    }

    /**
     * @param tempArrayList
     * @return
     */
    public int getMinElePos(ArrayList<Ele> tempArrayList) {
        int minElePosition = 0;
        Ele tempEle = arrayList.get(0);
        for (int i = 0; i < sizeArrayList; i++) {
            if (arrayList.get(i).compareTo(tempEle) < 0) {
                minElePosition = i;
                tempEle = arrayList.get(i);
            }
        }
        return minElePosition;
    }

    /**
     * @param ele
     */
    public void add(Ele ele) {
        enqueue(ele);
    }

    /**
     * @param ele
     */
    public void enqueue(Ele ele) {
        arrayList.add(ele);
        sizeArrayList++;
    }

    /**
     * @param index
     * @return
     */
    public Ele get(int index) {
        return arrayList.get(index);
    }

    /**
     * @return
     */
    public Ele remove() {
        Ele x = poll();
        if (x != null) {
            return x;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * @return
     */
    public Ele poll() {
        if (sizeArrayList == 0) {
            return null;
        } else {
            Ele tempEle = arrayList.get(getMinElePos(arrayList));
            arrayList.remove(getMinElePos(arrayList));
            sizeArrayList--;
            return tempEle;
        }
    }

    /**
     *
     */
    public void printQueue() {
        System.out.println("The priority queue is:\n");
        for (int i = 0; i < sizeArrayList; i++) {
            System.out.print(arrayList.get(i) + "   ");
        }
        System.out.println("\n");
    }

    /**
     * @return
     */
    public int size() {
        return getSize();
    }

    /**
     * @return
     */
    public int getSize() {
        return sizeArrayList;
    }

    /**
     * @param e
     * @return
     */
    public boolean offer(Ele e) {
        for (int i = 0; i < sizeArrayList; i++) {
            if (arrayList.get(i).equals(e)) {
                return false; //already exist
            }
        }
        arrayList.add(e);
        sizeArrayList++;
        return true;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        if (sizeArrayList == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @return
     */
    public Ele peek() {
        if (sizeArrayList == 0) {
            return null;
        } else {
            return arrayList.get(getMinElePos(arrayList));
        }
    }

}
