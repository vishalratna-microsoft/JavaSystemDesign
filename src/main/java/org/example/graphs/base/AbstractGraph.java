package org.example.graphs.base;

import org.example.graphs.GraphUtils;

import java.util.*;

/**
 * Abstract graph implementation that implements the basic set of functionality in directed as well as undirected
 * graphs. Uses adjacency list to maintain the graph structure. The list is maintained as a hashset. So, queries on the
 * node doesn't return the adjacency list in any pre-determined order.
 * This implementation will not honour the case where multiple edges exists between the same set of nodes. It
 * compresses all such requests to one edge.
 *
 * @param <T> Type of graph.
 */

// Needs to be split when we implement weighted graphs, in that case, multiple edges will exist between the same nodes
// with different weights.
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
        maybeCreateNodes(a, b);
        Set<T> connections = mGraph.get(a);
        connections.add(b);
        mGraph.put(a, connections);
        createEdge(a, b);
    }

    private void maybeCreateNodes(T a, T b) {
        if (!mGraph.containsKey(a)) {
            mGraph.put(a, new HashSet<>());
        }
        // Additionally create a adjacency list for b also if in case.
        if (!mGraph.containsKey(b)) {
            mGraph.put(b, new HashSet<>());
        }
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

    @Override
    public boolean isCyclic() {
        return GraphUtils.isCyclic(this);
    }

    @Override
    public Graph<T> transpose() {
        return GraphUtils.transpose(this);
    }

    @Override
    public int degree(T node) {
        // Degree is not defined in case of directed graphs.
        if (isDirected())
            throw new IllegalStateException("Directed graphs have in/out degrees, degree() is not defined");

        // If node is not the part of graph return -1
        if (!mGraph.containsKey(node)) return -1;

        return mGraph.get(node).size();
    }

    @Override
    public int inDegree(T node) {
        if (!isDirected())
            throw new IllegalStateException("Un-Directed graphs do not have in degrees, try calling degree()");

        if (!mGraph.containsKey(node)) return -1;

        int inDegree = 0;
        for (Map.Entry<T, Set<T>> entry : mGraph.entrySet()) {
            if (entry.getValue().contains(node)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    @Override
    public int outDegree(T node) {
        if (!isDirected())
            throw new IllegalStateException("Un-Directed graphs do not have out degrees, try calling degree()");

        // If node is not the part of graph return -1
        if (!mGraph.containsKey(node)) return -1;

        return mGraph.get(node).size();
    }

    public abstract boolean isDirected();

    private void validateNodes(T a, T b) {
        if (a == null || b == null) throw new IllegalArgumentException("Graph nodes initialised with null value");
    }
}
