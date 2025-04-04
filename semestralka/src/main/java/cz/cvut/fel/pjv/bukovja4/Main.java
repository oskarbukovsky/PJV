package cz.cvut.fel.pjv.bukovja4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.Button;
import cz.cvut.fel.pjv.bukovja4.utils.Utils;
// import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.config.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

import static cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory.*;
import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
import cz.cvut.fel.pjv.bukovja4.engine.elements.*;

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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            Thread.currentThread().setName("MainThread");

            LOG.info("Starting game...");
            LOG.debug("Game DIR: " + System.getProperty("user.dir"));

            Config config = new Config();
            config.Load();

            // Clock clock = new Clock(Const.DESIRED_TPS);
            // clock.start();

            // GameLoop game = new GameLoop(config);
            // game.start();
            // try {
            // game.join();
            // } catch (InterruptedException e) {
            // LOG.error("Game interrupted", e);
            // }

            // Creating factories
            // ElementFactory<Dim2d> factory2d = ElementFactory.createFactory2d();
            // ElementFactory<Dim3d> factory3d = ElementFactory.createFactory3d();

            ElementFactory<Dim2d> testFactory = new ElementFactory<Dim2d>();

            Button<Dim3d> testButton1 = Factory3d.create(Button.class, new Pos<Dim3d>(1,2, 3), new Pos<Dim3d>(4,5,6));
            Button<Dim2d> testButton2 = testFactory.create(Button.class, new Pos<Dim2d>(1,2), new Pos<Dim2d>(4,5));
            Label<Dim3d> testLabel1 = Factory3d.create(Label.class, new Pos<Dim3d>(1,2, 3), new Pos<Dim3d>(4,5,6));

            LOG.info(testButton2.bounds.getCorners3d().toString());
            LOG.info(testButton1.bounds.getCorners2d().toString());
            LOG.info(testLabel1.bounds.getCorners3d().toString());

            BaseElement<Dim3d> bad = Factory3d.create(BaseElement.class, new Pos<Dim3d>(1,2, 3), new Pos<Dim3d>(4,5,6));

            // Creating elements with the factories
            // Button<Dim2d> button2d = factory2d.createButton(new Pos2d(10, 20), new Pos2d(30, 40));
            // Button<Dim3d> button3d = factory3d.createButton(new Pos3d(10, 20, 30), new Pos3d(40, 50, 60));

            // Using the generic create method
            // Label<Dim2d> label2d = factory2d.create(Label.class, new Pos2d(10, 20));

            // Creating elements with dimension-specific factories
            // Button<Dim2d> button2D = factory2d.create<Button<Dim2d>>(new Pos2d(10, 20),
            // new Pos2d(30, 40));
            // Button<Dim3d> button3D = factory3d.create<Button<Dim3d>>(new Pos3d(10, 20,
            // 30), new Pos3d(40, 50, 60),
            // new Pos3d(70, 80, 90));

        } catch (Throwable e) {
            // e.printStackTrace();
        } finally {
            Utils.CloseGame();
        }
    }
}