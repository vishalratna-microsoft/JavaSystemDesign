package org.example.graphs.implementation;

import org.example.graphs.base.AbstractGraph;

public class DirectedGraph<T> extends AbstractGraph<T> {
    @Override
    public boolean isDirected() {
        return true;
    }
}
