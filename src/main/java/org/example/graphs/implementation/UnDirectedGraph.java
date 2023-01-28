package org.example.graphs.implementation;

import org.example.graphs.base.AbstractGraph;

public class UnDirectedGraph<T> extends AbstractGraph<T> {
    @Override
    public boolean isDirected() {
        return false;
    }
}
