package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class GameLoop extends Thread {

    private String threadName;

    public GameLoop(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(threadName);
        LOG.debug("GameLoop started");
    }

}
