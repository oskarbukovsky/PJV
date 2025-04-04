package cz.cvut.fel.pjv.bukovja4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cz.cvut.fel.pjv.bukovja4.Main;
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

        InputStream sourceStream = Main.class.getResourceAsStream("/" + sourcePathString);
        if (sourceStream != null) {
            // System.out.println(sourceStream.toString());
            Files.copy(sourceStream, targetPath);
            LOG.info("Config file was successfully copied to " + targetPath);
        } else {
            LOG.error("Resource not found", new IOException(sourcePathString));
        }
    }
}
