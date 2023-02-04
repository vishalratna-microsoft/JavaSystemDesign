package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Algorithm that tries to use tarjan's way of dfs to find bridges in the undirected graph.
 * @param <T> type of graph.
 */
public class FindBridgeUsingTarjan<T> implements Algorithm<Graph<T>, Set<Graph.Edge<T>>> {

    private final HashMap<T, Integer> mDiscovery;
    private final HashMap<T, Integer> mLowLink;
    private final Set<Graph.Edge<T>> mBridge;
    private int mTimer = 0;

    public FindBridgeUsingTarjan() {
        this.mDiscovery = new HashMap<>();
        this.mLowLink = new HashMap<>();
        this.mBridge = new HashSet<>();
    }

    @Override
    public Set<Graph.Edge<T>> apply(Graph<T> input) {
        return dispatchTarjanBridgeFinder(input);
    }

    private Set<Graph.Edge<T>> dispatchTarjanBridgeFinder(Graph<T> graph) {
        for (T node : graph.nodes()) {
            tarjanBridgeFinder(node, null, graph);
        }
        return mBridge;
    }

    private void tarjanBridgeFinder(T node, T parent, Graph<T> graph) {
        mDiscovery.put(node, mTimer);
        mLowLink.put(node, mTimer);
        mTimer++;

        for (T connection : graph.connections(node)) {
            if (connection == parent) continue;
            if (!mDiscovery.containsKey(connection)) {
                tarjanBridgeFinder(connection, node, graph);
                mLowLink.put(node, Math.min(mLowLink.get(node), mLowLink.get(connection)));
                if (mLowLink.get(connection) > mDiscovery.get(node)) {
                    mBridge.add(new Graph.Edge<>(node, connection));
                }
            } else {
                mLowLink.put(node, Math.min(mLowLink.get(node), mLowLink.get(connection)));
            }
        }
    }
}
