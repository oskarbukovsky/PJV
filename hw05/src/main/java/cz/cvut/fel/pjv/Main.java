package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.impl.TreeImpl;

public class Main {
    public static void main(String[] args) {
        Tree tree = new TreeImpl();
        tree.setTree(new int[] { 0, 10, 1, 200, 2, 30, 3, 4000, 4, 50, 5, 600, 6, 70, 8 });
        System.out.println(tree.toString());
    }
}
