package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;
import org.example.NoImplementationException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindArticulationPointUsingTarjan<T> implements Algorithm<Graph<T>, Set<T>> {

    private int mTimer = 0;
    private final Map<T, Integer> mDiscovery;
    private final Map<T, Integer> mLowLink;
    private T mStartNode = null;

    public FindArticulationPointUsingTarjan() {
        this.mDiscovery = new HashMap<>();
        this.mLowLink = new HashMap<>();
    }

    @Override
    public Set<T> apply(Graph<T> input) {
        if (input.isDirected()) throw new NoImplementationException("Algorithm does not work for directed graphs");

        Set<T> articulationPts = new HashSet<>();
        for (T node : input.nodes()) {
            mStartNode = node;
            findArticulation(node, input, null, articulationPts);
            // Return after the dfs from the first node. The graph needs to be connected.
            break;
        }
        return articulationPts;
    }

    private void findArticulation(T node, Graph<T> graph, T parent, Set<T> articulationPts) {
        mDiscovery.put(node, mTimer);
        mLowLink.put(node, mTimer);
        mTimer++;
        int count = 0;

        for (T connection : graph.connections(node)) {
            if (connection == parent) continue;

            if (!mDiscovery.containsKey(connection)) {
                count++;
                findArticulation(connection, graph, node, articulationPts);
                mLowLink.put(node, Math.min(mLowLink.get(node), mLowLink.get(connection)));
                // If low link of connection is able to reach before node, then it is not a articulation point.
                // If it is able to reach to or below me in discovery times, then it is a articulation point.
                if (node != mStartNode && mLowLink.get(connection) >= mDiscovery.get(node)) {
                    articulationPts.add(node);
                }
            } else {
                mLowLink.put(node, Math.min(mLowLink.get(node), mDiscovery.get(connection)));
            }
        }

        if (node == mStartNode && count > 1) {
            articulationPts.add(node);
        }
    }
}
