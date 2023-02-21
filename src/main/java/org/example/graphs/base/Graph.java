package org.example.graphs.base;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a general purpose graph of type T. The implementation can choose to use adjacency list or matrix depending
 * on the requirements. It contains commonly used methods and operations. Each implementation of this class should
 * implement the behavior in case of directed and undirected graphs.
 *
 * @param <T> Type of graph.
 */
public interface Graph<T> {

    /**
     * Tells whether the graph is directed or undirected.
     *
     * @return true if directed, false otherwise.
     */
    boolean isDirected();

    /**
     * Returns a set containing all the nodes in this graph.
     *
     * @return set of nodes.
     */
    Set<T> nodes();

    /**
     * Returns a set of all the edges in this graph.
     *
     * @return set of edges
     */
    Set<Edge<T>> edges();

    /**
     * Provides a method to connect to nodes inside the graph.  It returns false if the connection cannot be created.
     *
     * @param a Node A
     * @param b Node B
     * @return true if connection creation successful, false otherwise.
     */
    @SuppressWarnings("UnusedReturnValue")
    boolean createConnection(T a, T b);

    /**
     * Tells whether there is an edge between 2 nodes. In case of undirected graphs the order of the nodes in
     * important.
     *
     * @param a Node A
     * @param b Node B
     * @return true if an edge exists, false otherwise.
     */
    boolean edgeExists(T a, T b);

    /**
     * This method tells the outgoing nodes from the given node.
     *
     * @param node Node whose connections are queried.
     * @return set of outgoing nodes, empty set otherwise.
     */
    Set<T> connections(T node);

    /**
     * Provides a method to add a node inside the graph. This node would be a isolated node not connected to any other
     * nodes, unless and until connected later on using {@link Graph#createConnection(Object, Object)}
     *
     * @param node node that needs to be added.
     */
    void addNode(T node);

    /**
     * Method to check if the graph has cycles or not.
     *
     * @return true if cyclic, false otherwise.
     */
    boolean isCyclic();

    /**
     * Returns the transpose for this graph.
     *
     * @return transposed output.
     */
    Graph<T> transpose();

    /**
     * Degree is defined in undirected graphs, in simple words it is the number of edges the node is part of.
     *
     * @param node the node to be queried
     * @return degree of the node.
     */
    int degree(T node);

    /**
     * InDegree is not defined in un-directed graphs. In directed graphs, it is the number of incoming edges on the node.
     *
     * @param node node of be queried.
     * @return in-degree of the node.
     */
    int inDegree(T node);

    /**
     * OutDegree is not defined in un-directed graphs. In directed graphs, it is the number of outgoing edges on the node.
     *
     * @param node node of be queried.
     * @return out-degree of the node.
     */
    int outDegree(T node);

    /**
     * Returns the {@link Edge} representing the 2 nodes, a and b.
     * @param a Node A
     * @param b Node B
     * @return edge object connecting 2 nodes.
     */
    Edge<T> edge(T a, T b);

    /**
     * Class representing the edge of the graph.
     *
     * @param <T> Type of the graph.
     */
    class Edge<T> {
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

        @Override
        public String toString() {
            return "[" + a.toString() + " -> " + b.toString() + "]";
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