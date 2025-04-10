package cz.cvut.fel.pjv.bukovja4.utils;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

public class Closeable implements AutoCloseable {
    public Closeable() {
        LOG.warn("HERE");
    }
    @Override
    public void close() {
    }

}
