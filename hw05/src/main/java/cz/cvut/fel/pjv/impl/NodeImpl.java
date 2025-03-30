package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Node;

public class NodeImpl implements Node {

    private int Value;
    public NodeImpl left;
    public NodeImpl right;

    public NodeImpl(int value) {
        this.Value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public NodeImpl getLeft() {
        return this.left;
    }

    @Override
    public NodeImpl getRight() {
        return this.right;
    }

    @Override
    public int getValue() {
        return this.Value;
    }
}
