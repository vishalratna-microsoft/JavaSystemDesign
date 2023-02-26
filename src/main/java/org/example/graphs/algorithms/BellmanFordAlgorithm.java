package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFordAlgorithm<T> implements Algorithm<AbstractWeightedGraph<T>, Map<T, Integer>> {

    private final T mStart;
    private Map<T, Integer> mMinDist;

    public BellmanFordAlgorithm(T start) {
        this.mStart = start;
    }

    @Override
    public Map<T, Integer> apply(AbstractWeightedGraph<T> input) {
        if (!input.isDirected())
            throw new IllegalArgumentException("Graph should be directed for Bellman ford to work");

        int passes = input.nodes().size() - 1;
        init(input);
        // Get the list of edges.
        List<AbstractWeightedGraph.WeightedEdge<T>> edges = input.modelledEdges();
        for (int i = 0; i < passes; i++) {
            for (AbstractWeightedGraph.WeightedEdge<T> edge : edges) {
                int costToReachCurrent = mMinDist.get(edge.a);
                int costForConnection = edge.weight;
                if (costToReachCurrent != Integer.MAX_VALUE && (costForConnection + costToReachCurrent) < mMinDist.get(edge.b)) {
                    mMinDist.put(edge.b, (costForConnection + costToReachCurrent));
                }
            }
        }
        return mMinDist;
    }

    private void init(AbstractWeightedGraph<T> input) {
        mMinDist = new HashMap<>();
        for (T node : input.nodes()) {
            if (node == mStart) {
                mMinDist.put(node, 0);
                continue;
            }
            mMinDist.put(node, Integer.MAX_VALUE);
        }
    }
}
