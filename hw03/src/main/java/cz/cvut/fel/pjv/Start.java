package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.impl.CircularArrayQueue;

public class Start {

    public static void main(String[] args) {
        Queue queue = new CircularArrayQueue(10);

        queue.enqueue("Starkiller");
        queue.enqueue("C-3PO");
        queue.enqueue("Jabba the Hutt");
        queue.enqueue("HK-47");
        queue.enqueue("Darth Nihilus");
        queue.enqueue("Count Dooku");
        queue.enqueue(null);
        System.out.println("size: " + queue.size());
        System.out.println("Value dequeued from CircularArrayQueue: " + queue.dequeue());
        System.out.println("printing all elements: ");
        queue.printAllElements();
        queue.enqueue("Jango Fett");
        System.out.println("size: " + queue.size());

        for (String item : queue.getElements()) {
            System.out.println(item);
        }
    }
}
