package cz.cvut.fel.pjv.bukovja4.utils;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

/**
 * Test implementation of AutoCloseable interface.
 * This class is for testing purposes only and should not be used in production
 * code.
 */
public class Closeable implements AutoCloseable {

    public Closeable() {
        LOG.warn("HERE");
    }

    @Override
    public void close() {
    }

}
