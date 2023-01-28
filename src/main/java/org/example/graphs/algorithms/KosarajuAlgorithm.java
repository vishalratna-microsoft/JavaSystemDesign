package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.*;

public class KosarajuAlgorithm<T> implements Algorithm<Graph<T>, Set<List<T>>> {

    @Override
    public Set<List<T>> apply(Graph<T> input) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        // Get the stack ready first.
        for (T node : input.nodes()) {
            prepareOrderedStackRecursive(node, input, visited, stack);
        }
        // Get the transpose of the graph.
        Graph<T> transpose = input.transpose();
        visited.clear();

        Set<List<T>> scc = new HashSet<>();
        while (!stack.isEmpty()) {
            T item = stack.pop();
            // do the dfs call to find out components.
            if (!visited.contains(item)) {
                List<T> component = new ArrayList<>();
                figureStronglyConnected(item, transpose, visited, component);
                scc.add(component);
            }
        }
        return scc;
    }

    private void figureStronglyConnected(T item, Graph<T> input, Set<T> visited, List<T> component) {
        if (!visited.contains(item)) {
            visited.add(item);
            component.add(item);
            for (T connection : input.connections(item)) {
                figureStronglyConnected(connection, input, visited, component);
            }
        }
    }

    private void prepareOrderedStackRecursive(T node, Graph<T> input, Set<T> visited, Stack<T> stack) {
        if (!visited.contains(node)) {
            visited.add(node);
            for (T connection : input.connections(node)) {
                prepareOrderedStackRecursive(connection, input, visited, stack);
            }
            // When all nodes are explored or no nodes exist. Add it to the stack.
            stack.add(node);
        }
    }
}
