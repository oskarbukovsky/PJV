import org.junit.jupiter.api.*;

import cz.cvut.fel.pjv.impl.*;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class Testing {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test_1() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_1.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
    }

    @Test
    public void test_2() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_2.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
    }

    @Test
    public void test_3() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_3.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
    }

    @Test
    public void test_4() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_4.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
        assertEquals(" ", errContent.toString());
    }

    @Test
    public void test_5() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_5.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
        assertEquals(" ", errContent.toString());
    }

    @Test
    public void test_6() throws IOException {
        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public_6.txt"));
        new Lab02().main(new String[] {});

        assertEquals(" ", outContent.toString());
        assertEquals(" ", errContent.toString());
    }
}
