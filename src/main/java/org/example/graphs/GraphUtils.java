package org.example.graphs;

import org.example.algorithms.Algorithm;
import org.example.algorithms.BreadthFirstTraversal;
import org.example.algorithms.DFSTopSort;
import org.example.algorithms.DepthFirstTraversal;
import org.example.graphs.contracts.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * General purpose graph utility that operates on graphs, works for both directed and undirected.
 */
public class GraphUtils {
    public static <T> boolean isCyclic(Graph<T> graph) {
        boolean isDirected = graph.isDirected();
        Set<T> all = graph.nodes();
        // Repeat the process for all the nodes.
        Set<T> visited = new HashSet<>();
        boolean isCyclic;
        if (!isDirected) {
            for (T node : all) {
                isCyclic = isCyclicInternalUndirected(graph, node, visited);
                if (isCyclic) return true;
            }
        } else {
            Set<T> recursion = new HashSet<>();
            for (T node : all) {
                isCyclic = isCyclicInternalDirected(graph, node, visited, recursion);
                if (isCyclic) return true;
            }
        }
        return false;
    }

    private static <T> boolean isCyclicInternalUndirected(Graph<T> graph, T node, Set<T> visited) {
        visited.add(node);

        for (T connection : graph.connections(node)) {
            if (visited.contains(connection)) return true;
            return isCyclicInternalUndirected(graph, connection, visited);
        }
        return false;
    }

    private static <T> boolean isCyclicInternalDirected(Graph<T> graph, T node, Set<T> visited, Set<T> recursion) {
        visited.add(node);
        recursion.add(node);

        // Explore all the connection for this node;
        for (T connection : graph.connections(node)) {
            // If visited and recursion contain the connection, we found a cycle.
            if ((visited.contains(connection) && recursion.contains(connection))) {
                return true;
            }
            if (isCyclicInternalDirected(graph, connection, visited, recursion)) {
                return true;
            }
        }
        recursion.remove(node);
        return false;
    }

    public static <T> Graph<T> transpose(Graph<T> input) {
        // Undirected graphs transpose() make no sense! return the same graph.
        if (!input.isDirected()) return input;

        Graph<T> output = Graphs.create(Graphs.Type.DIRECTED);

        // Iterate through all the nodes.
        for (T node : input.nodes()) {
            // If there is no connection for this node, just add the node in the transposed graph also.
            if (input.connections(node).size() == 0) {
                output.addNode(node);
                continue;
            }
            // If it has connections, then reverse the connection direction.
            for (T connection : input.connections(node)) {
                output.createConnection(connection, node);
            }
        }
        return output;
    }

    public static <T> List<T> dfs(Graph<T> input) {
        Algorithm<Graph<T>, List<T>> dfs = new DepthFirstTraversal<>();
        return dfs.apply(input);
    }

    public static <T> List<T> bfs(Graph<T> input) {
        Algorithm<Graph<T>, List<T>> bfs = new BreadthFirstTraversal<>();
        return bfs.apply(input);
    }

    public static <T> List<T> topSort(Graph<T> input) {
        Algorithm<Graph<T>, List<T>> topSort = new DFSTopSort<>();
        return topSort.apply(input);
    }
}
