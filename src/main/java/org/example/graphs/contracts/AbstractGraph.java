package org.example.graphs.contracts;

import java.util.*;

public abstract class AbstractGraph<T> implements Graph<T> {

    private final HashMap<T, Set<T>> mGraph;
    private final Set<Edge<T>> mEdges;

    public AbstractGraph() {
        this.mGraph = new HashMap<>();
        this.mEdges = new HashSet<>();
    }

    @Override
    public Set<T> nodes() {
        return mGraph.keySet();
    }

    @Override
    public Set<Edge<T>> edges() {
        return mEdges;
    }

    @Override
    public boolean createConnection(T a, T b) {
        validateNodes(a, b);
        // Make entry for node A
        if (isDirected()) {
            createConnectionInternal(a, b);
        } else {
            // Undirected graphs have to make entries in both directions
            createConnectionInternal(a, b);
            createConnectionInternal(b, a);
        }
        return true;
    }

    private void createConnectionInternal(T a, T b) {
        if (!mGraph.containsKey(a)) {
            mGraph.put(a, new HashSet<>());
        }
        Set<T> connections = mGraph.get(a);
        connections.add(b);
        mGraph.put(a, connections);
        createEdge(a, b);
    }

    private void createEdge(T a, T b) {
        Edge<T> edge = new Edge<>(a, b);
        mEdges.add(edge);
    }

    @Override
    public boolean edgeExists(T a, T b) {
        if (isDirected()) {
            return mEdges.contains(new Edge<>(a, b));
        }
        // For undirected check for both ways.
        boolean exists = mEdges.contains(new Edge<>(a, b));
        if (mEdges.contains(new Edge<>(b, a))) {
            exists = true;
        }
        return exists;
    }

    @Override
    public Set<T> connections(T node) {
        if (!mGraph.containsKey(node)) {
            return new HashSet<>();
        }
        return mGraph.get(node);
    }

    @Override
    public void addNode(T node) {
        if (node != null) {
            // See if that node exists already
            if (!mGraph.containsKey(node)) {
                mGraph.put(node, new HashSet<>());
            }
        }
    }

    public abstract boolean isDirected();

    private void validateNodes(T a, T b) {
        if (a == null || b == null) throw new IllegalArgumentException("Graph nodes initialised with null value");
    }
}
