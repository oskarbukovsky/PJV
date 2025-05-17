package cz.cvut.fel.pjv.bukovja4;

// import java.io.IOException;
// import java.io.InputStream;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

import cz.cvut.fel.pjv.bukovja4.utils.logging.LOG;
// import cz.cvut.fel.pjv.bukovja4.utils.Utils;
// import cz.cvut.fel.pjv.bukovja4.utils.config.*;
import cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory;
import cz.cvut.fel.pjv.bukovja4.utils.audio.*;
// import cz.cvut.fel.pjv.bukovja4.utils.clocks.Clock;
import cz.cvut.fel.pjv.bukovja4.utils.engine.*;
import cz.cvut.fel.pjv.bukovja4.utils.engine.dim.*;

import cz.cvut.fel.pjv.bukovja4.engine.elements.specific.*;
// import cz.cvut.fel.pjv.bukovja4.engine.elements.*;
// import cz.cvut.fel.pjv.bukovja4.engine.logic.GameState;
// import cz.cvut.fel.pjv.bukovja4.engine.scenes.SceneFactory;
// import cz.cvut.fel.pjv.bukovja4.engine.scenes.SceneTypes;

import static cz.cvut.fel.pjv.bukovja4.engine.elements.ElementFactory.*;
import static cz.cvut.fel.pjv.bukovja4.engine.elements.ElementTypes.*;

public final class TestingThings {
    public static void TestElements() throws Throwable {
        ElementFactory<Dim2d> testFactory = new ElementFactory<Dim2d>();

        Button<Dim3d> testButton1 = Factory3d.create(BUTTON, new Pos<Dim3d>(1, 2, 3), new Pos<Dim3d>(4, 5, 6));
        LOG.info(testButton1.bounds.getCorners2d().toString());

        Button<Dim2d> testButton2 = testFactory.create(BUTTON, new Pos<Dim2d>(1, 2), new Pos<Dim2d>(4, 5));
        LOG.info(testButton2.bounds.getCorners3d().toString());

        Label<Dim3d> testLabel1 = Factory3d.create(LABEL, new Pos<Dim3d>(1, 2,
                3), new Pos<Dim3d>(4, 5, 6));
        LOG.info(testLabel1.bounds.getCorners3d().toString());
    }

    public static void TestAudio() throws InterruptedException {
        Playback playback = Audio.play("menus/main/background.wav", true);
        Thread.sleep(1500);
        Audio.play("menus/main/background.wav");
        Thread.sleep(3000);
        playback.stop();
    }

    public static void Test() throws Throwable {
    }
}