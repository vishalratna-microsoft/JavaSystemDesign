package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * We start doing the DFS and count the number of unique nodes visited. If the number is different from the number of nodes
 * in the graph we say that the graph is disconnected.
 *
 * For directed graphs, we pick a random node and do the DFS, then we reverse the direction of edges and again do the dfs.
 * If a node is not present in any of the visited arrays( for each direction of traversal ) then the graph is disconnected.
 *
 * @param <T> Type of graph.
 */
public class IsGraphDisconnected<T> implements Algorithm<Graph<T>, Boolean> {

    private final Set<T> mVisited = new HashSet<>();
    private int mCount = 0;

    @Override
    public Boolean apply(Graph<T> input) {
        if (input.isDirected()) {
            return isGraphDisconnectedDirected(input);
        } else {
            return isGraphDisconnectedUnDirected(input);
        }
    }

    private boolean isGraphDisconnectedDirected(Graph<T> input) {
        boolean val = true;
        T random;
        for (T node : input.nodes()) {
            random = node;
            Set<T> visited1 = new HashSet<>();
            dfs(random, input, visited1);
            Graph<T> transpose = input.transpose();
            Set<T> visited2 = new HashSet<>();
            dfs(random, input, visited2);
            val = examineDisconnected(visited1, visited2, transpose);
            break;
        }
        return val;
    }

    private boolean examineDisconnected(Set<T> set1, Set<T> set2, Graph<T> graph) {
        for (T node : graph.nodes()) {
            if (!set1.contains(node) && !set2.contains(node)) {
                // Graph is disconnected.
                return true;
            }
        }
        return false;
    }

    private void dfs(T node, Graph<T> graph, Set<T> visited) {
        visited.add(node);

        for (T connection : graph.connections(node)) {
            if (!visited.contains(connection)) {
                dfs(node, graph, visited);
            }
        }
    }

    private Boolean isGraphDisconnectedUnDirected(Graph<T> graph) {
        boolean val = true;
        for (T node : graph.nodes()) {
            mCount++;
            isGraphDisconnectedUndirectedInternal(node, graph);
            if (mCount == graph.nodes().size()) {
                val = false;
            }
            break;
        }
        return val;
    }

    private void isGraphDisconnectedUndirectedInternal(T node, Graph<T> graph) {
        mVisited.add(node);

        for (T connection : graph.connections(node)) {
            if (!mVisited.contains(connection)) {
                mCount++;
                isGraphDisconnectedUndirectedInternal(connection, graph);
            }
        }
    }
}
