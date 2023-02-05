package org.example.graphs;

import org.example.graphs.implementation.AbstractGraph;
import org.example.graphs.base.Graph;
import org.example.NoImplementationException;

public class Graphs {
    public static final int DIRECTED = 0;
    public static final int UNDIRECTED = 1;

    public static <T> Graph<T> create(Type type) {
        Graph<T> graph;
        switch (type) {
            case DIRECTED:
                graph = new AbstractGraph<>() {
                    @Override
                    public boolean isDirected() {
                        return true;
                    }
                };
                break;
            case UNDIRECTED:
                graph = new AbstractGraph<>() {
                    @Override
                    public boolean isDirected() {
                        return false;
                    }
                };
                break;
            default:
                throw new NoImplementationException("Graph type does not exist in the system, or is not configured.");
        }
        return graph;
    }

    public enum Type {
        DIRECTED,
        UNDIRECTED
    }
}
