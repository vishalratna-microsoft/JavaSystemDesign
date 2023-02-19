package org.example.graphs.implementation;

import org.example.graphs.base.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class WeightedGraph<T> implements Graph<T> {
    private final Graph<T> mBase;
    private final Map<Edge<T>, Integer> mEdges;

    public WeightedGraph(@NotNull Graph<T> graph) {
        this.mBase = graph;
        this.mEdges = new HashMap<>();
    }

    @Override
    public boolean createConnection(T a, T b) {
        return this.createConnection(a, b, 0);
    }

    public boolean createConnection(T a, T b, int weight) {
        mBase.createConnection(a, b);
        Edge<T> edge = mBase.edge(a, b);
        mEdges.put(edge, weight);
        // If the graph is undirected, we need to add the reverse edge also.
        if (!isDirected()) {
            mEdges.put(mBase.edge(b, a), weight);
        }
        return true;
    }

    public Map<Edge<T>, Integer> weightedEdges() {
        return mEdges;
    }

    @Override
    public Set<? extends Edge<T>> edges() {
        return mBase.edges();
    }

    int weight(T a, T b) {
        if (!mBase.edgeExists(a, b)) return -1;

        return mEdges.get(mBase.edge(a, b));
    }
}
