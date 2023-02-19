package org.example.graphs.implementation;

import org.example.graphs.base.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractWeightedGraph<T> extends AbstractGraph<T> {
    private final Map<Edge<T>, Integer> mEdges;

    public AbstractWeightedGraph() {
        super();
        this.mEdges = new HashMap<>();
    }

    @Override
    public boolean createConnection(T a, T b) {
        return this.createConnection(a, b, 0);
    }

    public boolean createConnection(T a, T b, int weight) {
        super.createConnection(a, b);
        Edge<T> edge = super.edge(a, b);
        mEdges.put(edge, weight);
        // If the graph is undirected, we need to add the reverse edge also.
        if (!isDirected()) {
            mEdges.put(super.edge(b, a), weight);
        }
        return true;
    }

    @Override
    public void addNode(T node) {
        super.addNode(node);
        createConnection(node,node,0);
    }

    public Map<Edge<T>, Integer> weightedEdges() {
        return mEdges;
    }

    @Override
    public Set<Edge<T>> edges() {
        return super.edges();
    }

    int weight(T a, T b) {
        if (!super.edgeExists(a, b)) return -1;

        return mEdges.get(super.edge(a, b));
    }
}
