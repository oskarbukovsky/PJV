package pjv.hodina10;

public class MyStack implements Stack {
    private String[] stack;
    private int size = 0;
    private static final int MAX_SIZE = 10;

    @Override
    synchronized public void add(String word) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
        if (stack == null) {
            stack = new String[MAX_SIZE];
        }
        stack[size] = word;
        size++;
        notifyAll();
    }

    @Override
    synchronized public String pop() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        String word = stack[size - 1];
        stack[size - 1] = null;
        size--;
        notifyAll();
        return word;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

}
