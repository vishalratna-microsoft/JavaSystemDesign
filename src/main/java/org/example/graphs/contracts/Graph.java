package org.example.graphs.contracts;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a general purpose graph of type T. The implementation can choose to use adjacency list or matrix depending
 * on the requirements. It contains commonly used methods and operations. Each implementation of this class should
 * implement the behavior in case of directed and undirected graphs.
 * @param <T> Type of graph.
 */
public interface Graph<T> {

    /**
     * Tells whether the graph is directed or undirected.
     * @return true if directed, false otherwise.
     */
    boolean isDirected();

    /**
     * Returns a set containing all the nodes in this graph.
     * @return set of nodes.
     */
    Set<T> nodes();

    /**
     * Returns a set of all the edges in this graph.
     * @return set of edges
     */
    Set<Edge<T>> edges();

    /**
     * Provides a method to connect to nodes inside the graph.  It returns false if the connection cannot be created.
     * @param a Node A
     * @param b Node B
     * @return true if connection creation successful, false otherwise.
     */
    @SuppressWarnings("UnusedReturnValue")
    boolean createConnection(T a, T b);

    /**
     * Tells whether there is an edge between 2 nodes. In case of undirected graphs the order of the nodes in
     * important.
     * @param a Node A
     * @param b Node B
     * @return true if an edge exists, false otherwise.
     */
    boolean edgeExists(T a, T b);

    /**
     * This method tells the outgoing nodes from the given node.
     * @param node Node whose connections are queried.
     * @return set of outgoing nodes, empty set otherwise.
     */
    Set<T> connections(T node);

    /**
     * Provides a method to add a node inside the graph. This node would be a isolated node not connected to any other
     * nodes, unless and until connected later on using {@link Graph#createConnection(Object, Object)}
     * @param node node that needs to be added.
     */
    void addNode(T node);

    boolean isCyclic();

    /**
     * Class representing the edge of the graph.
     * @param <T> Type of the graph.
     */
    final class Edge<T> {
        private T a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return a.equals(edge.a) && b.equals(edge.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        private T b;

        public Edge(T a, T b) {
            this.a = a;
            this.b = b;
        }

        public T getA() {
            return a;
        }

        public void setA(T a) {
            this.a = a;
        }

        public T getB() {
            return b;
        }

        public void setB(T b) {
            this.b = b;
        }
    }
}