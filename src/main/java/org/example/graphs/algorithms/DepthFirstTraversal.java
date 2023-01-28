package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirstTraversal<T> implements Algorithm<Graph<T>, List<T>> {

    @Override
    public List<T> apply(Graph<T> input) {
        return dispatchDfs(input);
    }

    private static <T> List<T> dispatchDfs(Graph<T> input) {
        Set<T> visited = new HashSet<>();
        List<T> dfsOutput = new ArrayList<>();
        for (T node : input.nodes()) {
            recursionDfs(node, input, visited, dfsOutput);
        }
        return dfsOutput;
    }

    private static <T> void recursionDfs(T node, Graph<T> graph, Set<T> visited, List<T> dfsOutput) {
        if (!visited.contains(node)) {
            visited.add(node);
            dfsOutput.add(node);

            for (T connection : graph.connections(node)) {
                recursionDfs(connection, graph, visited, dfsOutput);
            }
        }
    }
}
