package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.NoImplementationException;
import org.example.graphs.base.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * We start doing the DFS and count the number of unique nodes visited. If the number is different from the number of nodes
 * in the graph we say that the graph is disconnected.
 *
 * @param <T> Type of graph.
 */
public class IsGraphDisconnected<T> implements Algorithm<Graph<T>, Boolean> {

    private final Set<T> mVisited = new HashSet<>();
    private int mCount = 0;

    @Override
    public Boolean apply(Graph<T> input) {
        if (input.isDirected()) throw new NoImplementationException("Algorithm does not work on directed graphs!");

        return isGraphDisconnected(input);
    }

    private Boolean isGraphDisconnected(Graph<T> graph) {
        boolean val = true;
        for (T node : graph.nodes()) {
            mCount++;
            isGraphDisconnectedInternal(node, graph);
            if (mCount == graph.nodes().size()) {
                val = false;
            }
            break;
        }
        return val;
    }

    private void isGraphDisconnectedInternal(T node, Graph<T> graph) {
        mVisited.add(node);

        for (T connection : graph.connections(node)) {
            if (!mVisited.contains(connection)) {
                mCount++;
                isGraphDisconnectedInternal(connection, graph);
            }
        }
    }
}
