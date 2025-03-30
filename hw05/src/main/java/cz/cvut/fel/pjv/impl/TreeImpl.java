package cz.cvut.fel.pjv.impl;

import java.util.Arrays;

import cz.cvut.fel.pjv.Node;
import cz.cvut.fel.pjv.Tree;

public class TreeImpl implements Tree {

    private NodeImpl root;

    public TreeImpl() {
        this.root = null;
    }

    @Override
    public void setTree(int[] values) {
        this.root = this.setTreeHelper(values, root);
    }

    private NodeImpl setTreeHelper(int[] values, NodeImpl tmpRoot) {
        if (values == null || values.length == 0) {
            return null;
        }
        int middle = values.length % 2 == 1 ? values.length / 2 : (values.length + 1) / 2;
        tmpRoot = new NodeImpl(values[middle]);
        tmpRoot.left = this.setTreeHelper(Arrays.copyOfRange(values, 0, middle), null);
        tmpRoot.right = this.setTreeHelper(Arrays.copyOfRange(values, middle + 1, values.length), null);
        return tmpRoot;
    }

    // @Override
    // public void setTree(int[] values) {
    // if (values == null || values.length == 0) {
    // return;
    // }
    // int middle = values.length % 2 == 1 ? values.length / 2 : (values.length + 1)
    // / 2;
    // if (this.root == null) {
    // this.root = new NodeImpl(values[middle]);
    // } else {
    // NodeImpl current = this.root;
    // NodeImpl parent = null;
    // while (current != null) {
    // parent = current;
    // if (values[middle] < current.getValue()) {
    // current = current.getLeft();
    // } else {
    // current = current.getRight();
    // }
    // }
    // if (values[middle] < parent.getValue()) {
    // parent.left = new NodeImpl(values[middle]);
    // } else {
    // parent.right = new NodeImpl(values[middle]);
    // }
    // }
    // this.setTree(Arrays.copyOfRange(values, 0, middle));
    // this.setTree(Arrays.copyOfRange(values, middle + 1, values.length));
    // }

    @Override
    public Node getRoot() {
        return this.root;
    }

    @Override
    public String toString() {
        return this.printNode(0, root);
    }

    String printNode(int depth, Node node) {
        if (node == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(" ".repeat(depth)).append("- ").append(node.getValue()).append("\n");
        result.append(this.printNode(depth + 1, node.getLeft()));
        result.append(this.printNode(depth + 1, node.getRight()));
        return result.toString();
    }

}
