package FHeap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test whether the ArrayPriorityQueue is successful
 */
public class ArrayPriorityQueueTest {

    @Test
    public void testArrayPriorityQueue() {
        Patient patient1 = new Patient("Tom", 3);
        Patient patient2 = new Patient("Sam", 7);
        Patient patient3 = new Patient("John", 5);
        Patient patient4 = new Patient("Lily", 21);
        Patient patient5 = new Patient("Simon", 11);

        //Test enqueue
        ArrayPriorityQueue<Patient> priorityQueue = new ArrayPriorityQueue<Patient>();
        priorityQueue.enqueue(patient1);
        priorityQueue.printQueue();
        priorityQueue.enqueue(patient2);
        priorityQueue.printQueue();
        priorityQueue.enqueue(patient3);
        priorityQueue.printQueue();
        priorityQueue.enqueue(patient4);
        priorityQueue.printQueue();
        priorityQueue.enqueue(patient5);
        priorityQueue.printQueue();

        Assert.assertTrue(priorityQueue.size() == 5);

        //Test dequeue
        System.out.println("\nDequeue:\n");
        while (priorityQueue.getSize() > 0) {
            priorityQueue.dequeue();
            priorityQueue.printQueue();
        }

        Assert.assertTrue(priorityQueue.size()==0);

    }


    static class Patient implements Comparable<Patient> {
        private String name;
        private int priority;

        public Patient(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }


        @Override
        public String toString() {
            return name + "(priority:" + priority + ")";
        }

        @Override
        public int compareTo(Patient patient) {
            return this.priority - patient.priority;
        }
    }

}
