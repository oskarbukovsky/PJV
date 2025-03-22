package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Queue;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Collection;
// import java.util.List;
// import java.util.Objects;
// import java.util.stream.Collectors;

/**
 * Implementation of the {@link Queue} backed by fixed size array.
 */
public class CircularArrayQueue implements Queue {

    private String[] queue;
    private int size;
    private int capacity;
    private int start;
    private int end;

    /**
     * Creates the queue with capacity set to the value of 5.
     */
    public CircularArrayQueue() {
        this(5);
    }

    /**
     * Creates the queue with given {@code capacity}. The capacity represents
     * maximal number of elements that the
     * queue is able to store.
     * 
     * @param capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.queue = new String[capacity];
        this.start = 0;
        this.end = 0;
        // System.out.printf("size: %d\n", this.capacity);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isFull() {
        if (this.size >= this.capacity) {
            return true;
        }
        return false;
    }

    @Override
    public boolean enqueue(String obj) {
        if (this.isFull() || obj == null) {
            return false;
        }
        this.size++;
        System.out.printf("value enqueued to CircularArrayQueue: %s\n", obj);
        // System.out.println("size: " + this.size);
        this.queue[end] = obj;
        // System.out.println("end: " + this.end);
        this.end = (this.end + 1) % this.capacity;
        // System.out.println("end: " + this.end);
        // System.out.println("this.queue[end]: " + this.queue[end]);
        return true;
    }

    @Override
    public String dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        String result = this.queue[this.start];
        System.out.printf("value dequeued from CircularArrayQueue: %s\n", result);
        this.size--;
        // System.out.println("size: " + this.size);
        this.queue[start] = null; // ☆*: .｡. o(≧▽≦)o .｡.:*☆ =.=
        // System.out.println("start: " + this.start);
        this.start = (this.start + 1) % this.capacity;
        // System.out.println("start: " + this.start);
        // System.out.println("this.queue[start]: " + this.queue[start]);
        return result;
    }

    @Override
    public Collection<String> getElements() {
        ArrayList<String> nonNullQueue = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            nonNullQueue.add(this.queue[(this.start + i) % this.capacity]);
        }
        // K čemu K* je dobrý to List.of ? :/ Tady se člověk zblázní
        return nonNullQueue;
        // tady mi rupnou nervy asi ㄟ(≧◇≦)ㄏ !!!!!!!!
    }

    @Override
    public void printAllElements() {
        // System.out.println("printing all elements: ");
        for (String element : this.queue) {
            if (element == null) {
                continue;
            }
            System.out.println(element);
        }
        // System.out.println("Size: " + this.size);
    }
}
