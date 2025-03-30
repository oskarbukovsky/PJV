package cz.cvut.fel.pjv.publictests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import cz.cvut.fel.pjv.Tree;
import cz.cvut.fel.pjv.impl.TreeImpl;
import cz.cvut.fel.pjv.Node;
// import cz.cvut.fel.pjv.impl.NodeImpl;

public class MyTest {
    @Test
    void Test_0() {
        Tree tree = new TreeImpl();
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_1() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] {});
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_2() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 5 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_3() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 5, 4 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_3a() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 4, 5 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_4() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 5, 6 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_4a() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 6, 5 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_5() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 6, 5, 4 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_5a() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 4, 6, 5 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_5b() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 6, 4, 5 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_5c() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 5, 4, 6 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_5d() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 5, 6, 4 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_6() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 6, 5, 4, 3, 2, 1 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_6a() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 1, 2, 3, 4, 5, 6 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_7() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 6, 5, 4, 3, 2, 1, 0 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_7a() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 0, 1, 2, 3, 4, 5, 6 });
        Assertions.assertEquals(" ", tree.toString());
    }

    @Test
    void Test_8() {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 0, 10, 1, 200, 2, 30, 3, 4000, 4, 50, 5, 600, 6, 70, 8 });
        Assertions.assertEquals(" ", tree.toString());
    }
}
