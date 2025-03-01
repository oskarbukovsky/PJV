import org.junit.jupiter.api.Test;

import cz.cvut.fel.pjv.impl.*;
import java.io.IOException;
import java.io.FileInputStream;

public class test {
    @Test
    public void my_test() throws IOException {


        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public1.txt"));
        new Lab01Impl().homework();

        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public2.txt"));
        new Lab01Impl().homework();

        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public3.txt"));
        new Lab01Impl().homework();

        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public4.txt"));
        new Lab01Impl().homework();

        System.setIn(new FileInputStream("src/test/resources/cz/cvut/fel/pjv/_public5.txt"));
        new Lab01Impl().homework();
    }
}
