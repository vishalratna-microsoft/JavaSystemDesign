package org.example.graphs;

import org.example.graphs.contracts.AbstractGraph;

public class UnDirectedGraph<T> extends AbstractGraph<T> {
    @Override
    public boolean isDirected() {
        return false;
    }
}
