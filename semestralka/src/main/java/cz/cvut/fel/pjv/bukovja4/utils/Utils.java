package cz.cvut.fel.pjv.bukovja4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void copyFromResources(String targetPathString, String sourcePathString) throws Throwable {
        Path targetPath = Paths.get(targetPathString);

        // Try multiple methods to load the resource
        InputStream sourceFile = Utils.class.getResourceAsStream("/" + sourcePathString);
        if (sourceFile != null) {
            Files.copy(sourceFile, targetPath);
            LOG.info("Config file was successfully copied to " + targetPath);
        } else {
            LOG.error("Resource not found", new IOException(sourcePathString));
        }

    }

}
