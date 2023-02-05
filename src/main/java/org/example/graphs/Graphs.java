package org.example.graphs;

import org.example.graphs.implementation.DirectedGraph;
import org.example.graphs.implementation.UnDirectedGraph;
import org.example.graphs.base.Graph;
import org.example.NoImplementationException;

public class Graphs {
    public static final int DIRECTED = 0;
    public static final int UNDIRECTED = 1;

    public static <T> Graph<T> create(Type type) {
        Graph<T> graph;
        switch (type) {
            case DIRECTED:
                graph = new DirectedGraph<>();
                break;
            case UNDIRECTED:
                graph = new UnDirectedGraph<>();
                break;
            default:
                throw new NoImplementationException();
        }
        return graph;
    }

    public enum Type {
        DIRECTED,
        UNDIRECTED
    }
}
