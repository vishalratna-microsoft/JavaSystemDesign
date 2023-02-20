package org.example.graphs.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractWeightedGraph<T> extends AbstractGraph<T> {
    private final Map<Edge<T>, Integer> mWeights;

    public AbstractWeightedGraph() {
        super();
        this.mWeights = new HashMap<>();
    }

    @Override
    public boolean createConnection(T a, T b) {
        return this.createConnection(a, b, 0);
    }

    public boolean createConnection(T a, T b, int weight) {
        super.createConnection(a, b);
        Edge<T> edge = super.edge(a, b);
        mWeights.put(edge, weight);
        // If the graph is undirected, we need to add the reverse edge also.
        if (!isDirected()) {
            mWeights.put(super.edge(b, a), weight);
        }
        return true;
    }

    public Map<Edge<T>, Integer> weightedEdges() {
        return mWeights;
    }

    @Override
    public Set<Edge<T>> edges() {
        return super.edges();
    }

    public int weight(T a, T b) {
        if (!super.edgeExists(a, b)) return -1;

        return mWeights.get(super.edge(a, b));
    }
}
