package org.example.graphs.contracts;

import org.example.graphs.DirectedGraph;
import org.example.graphs.UnDirectedGraph;
import org.example.ratelimiter.NoImplementationException;

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

    public static <T> void printEdges(Graph<T> graph) {
        for (Graph.Edge<T> edge : graph.edges()) {
            System.out.println(edge.getA().toString() + " -> " + edge.getB().toString());
        }
    }

    public static <T> void printNodes(Graph<T> graph) {
        for (T node : graph.nodes()) {
            System.out.println(node.toString());
        }
    }

    public enum Type {
        DIRECTED,
        UNDIRECTED
    }
}
