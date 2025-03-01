package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Queue;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of the {@link Queue} backed by fixed size array.
 */
public class CircularArrayQueue implements Queue {

    // TODO: Implement the homework here

    /**
     * Creates the queue with capacity set to the value of 5.
     */
    public CircularArrayQueue() {

    }

    /**
     * Creates the queue with given {@code capacity}. The capacity represents maximal number of elements that the
     * queue is able to store.
     * @param capacity of the queue
     */
    public CircularArrayQueue(int capacity) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean enqueue(String obj) {
        return false;
    }

    @Override
    public String dequeue() {
        return "";
    }

    @Override
    public Collection<String> getElements() {
        return List.of();
    }

    @Override
    public void printAllElements() {

    }
}
