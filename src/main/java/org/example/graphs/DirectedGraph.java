package org.example.graphs;

import org.example.graphs.contracts.AbstractGraph;

public class DirectedGraph<T> extends AbstractGraph<T> {
    @Override
    public boolean isDirected() {
        return true;
    }
}
