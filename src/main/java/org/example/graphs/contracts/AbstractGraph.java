package org.example.graphs.contracts;

import org.example.graphs.GraphUtils;

import java.util.*;

/**
 * Abstract graph implementation that implements the basic set of functionality in directed as well as undirected
 * graphs. Uses adjacency list to maintain the graph structure.
 *
 * @param <T> Type of graph.
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    private final HashMap<T, Set<T>> mGraph;
    private final Set<Edge<T>> mEdges;
    private final Set<T> mNodes;

    public AbstractGraph() {
        this.mGraph = new HashMap<>();
        this.mEdges = new HashSet<>();
        this.mNodes = new HashSet<>();
    }

    @Override
    public Set<T> nodes() {
        return mNodes;
    }

    @Override
    public Set<Edge<T>> edges() {
        return mEdges;
    }

    @Override
    public boolean createConnection(T a, T b) {
        validateNodes(a, b);
        trackNodes(a, b);
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

    private void trackNodes(T a, T b) {
        trackNode(a);
        trackNode(b);
    }

    private void trackNode(T node) {
        mNodes.add(node);
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
                trackNode(node);
            }
        }
    }

    @Override
    public boolean isCyclic() {
        return GraphUtils.isCyclic(this);
    }

    public abstract boolean isDirected();

    private void validateNodes(T a, T b) {
        if (a == null || b == null) throw new IllegalArgumentException("Graph nodes initialised with null value");
    }
}
