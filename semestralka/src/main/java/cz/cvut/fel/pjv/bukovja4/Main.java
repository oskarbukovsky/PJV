package cz.cvut.fel.pjv.bukovja4;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cz.cvut.fel.pjv.bukovja4.utils.Utils;
// import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.*;

public final class Main {
    public static void copyFromResources(String targetPathString, String sourcePathString) throws Throwable {
        Path targetPath = Paths.get(targetPathString);

        InputStream sourceStream = new Main().getClass().getResourceAsStream("/" + sourcePathString);
        if (sourceStream != null) {
            System.out.println(sourceStream.toString());
            Files.copy(sourceStream, targetPath);
            LOG.info("Config file was successfully copied to " + targetPath);
        } else {
            LOG.error("Resource not found", new IOException(sourcePathString));
        }
    }

    public static void main(String[] args) {
        try {
            Thread.currentThread().setName("MainThread");

            LOG.info("Starting game...");
            LOG.debug("Game DIR: " + System.getProperty("user.dir"));

            Config config = new Config();
            config.Load();

            // Clock clock = new Clock(Const.DESIRED_TPS);
            // clock.start();

            GameLoop game = new GameLoop(config);
            game.start();
            try {
                game.join();
            } catch (InterruptedException e) {
                LOG.error("Game interrupted", e);
            }
        } catch (Throwable e) {
        } finally {
            Utils.CloseGame();
        }
    }
}