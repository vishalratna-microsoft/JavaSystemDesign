package org.example.graphs;

import org.example.Algorithm;
import org.example.graphs.algorithms.*;
import org.example.graphs.base.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * General purpose graph utility that operates on graphs, works for both directed and undirected.
 */
public class GraphUtils {

    public static <T> void printEdges(Graph<T> graph) {
        for (Graph.Edge<T> edge : graph.edges()) {
            System.out.println(edge.getA().toString() + " -> " + edge.getB().toString());
        }
    }

    public static <T> void printNodes(Graph<T> graph) {
        for (T node : graph.nodes()) {
            System.out.println(node.toString());
        }
    }

    public static <T> void printList(List<T> items) {
        for (T node : items) {
            System.out.print(node.toString() + " ");
        }
    }

    public static void printSCCs(Set<List<Integer>> result) {
        for (List<Integer> eachScc : result) {
            for (Integer i : eachScc) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void printSetOfEdges(Set<Graph.Edge<Integer>> result) {
        for (Graph.Edge<Integer> edge : result) {
            System.out.println(edge.toString());
        }
    }

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
        Algorithm<Graph<T>, Graph<T>> transpose = new Transpose<>();
        return transpose.apply(input);
    }

    public static <T> boolean isDisconnected(Graph<T> input) {
        Algorithm<Graph<T>, Boolean> isDisconnected = new IsGraphDisconnected<>();
        return isDisconnected.apply(input);
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
        Algorithm<Graph<T>, List<T>> topSort = new KahnAlgorithm<>();
        return topSort.apply(input);
    }
}
