package cz.cvut.fel.pjv;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class BruteForceAttackerTryFast extends Thief {
    class FastBreaker implements Runnable {
        private char[] prefix;
        private int sizeOfPassword;
        private CountDownLatch latch;

        public FastBreaker(char[] prefix, int sizeOfPassword, CountDownLatch latch) {
            this.prefix = prefix;
            this.sizeOfPassword = sizeOfPassword;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                breakPassword(prefix, sizeOfPassword);
            } finally {
                latch.countDown();
            }
        }
    }

    public void breakPassword(int sizeOfPassword) {
        char[] characters = getCharacters();
        int numThreads = characters.length;
        CountDownLatch latch = new CountDownLatch(numThreads);
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            char[] prefix = new char[] { characters[i] };
            threads[i] = new Thread(new FastBreaker(prefix, sizeOfPassword, latch));
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean breakPassword(char[] prefix, int sizeOfPassword) {
        if (prefix.length < sizeOfPassword) {
            prefix = Arrays.copyOf(prefix, prefix.length + 1);
            for (char character : getCharacters()) {
                prefix[prefix.length - 1] = character;
                if (breakPassword(prefix, sizeOfPassword)) {
                    return true;
                }
            }
        } else {
            return tryOpen(prefix);
        }
        return false;
    }
}
