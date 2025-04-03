package cz.cvut.fel.pjv.bukovja4.utils;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public final class Utils {
    public static void CloseGame() {
        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive() && !thread.getName().equals("MainThread")) {
                thread.interrupt();
                LOG.debug("Interrupted thread: " + thread.getName());
            }
        }
        LOG.info("Game is closing...");
    }
}
