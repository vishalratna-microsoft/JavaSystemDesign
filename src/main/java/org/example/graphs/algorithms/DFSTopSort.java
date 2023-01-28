package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.*;

public class DFSTopSort<T> implements Algorithm<Graph<T>, List<T>> {
    @Override
    public List<T> apply(Graph<T> input) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        input.nodes().forEach(t -> topSortRecursive(t, input, visited, stack));
        List<T> result = new ArrayList<>();
        while (stack.size() > 0) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topSortRecursive(T t, Graph<T> input, Set<T> visited, Stack<T> stack) {
        if (!visited.contains(t)) {
            visited.add(t);

            for (T connection : input.connections(t)) {
                topSortRecursive(connection, input, visited, stack);
            }
            // Add the element in the stack once done exploring all the nodes.
            stack.push(t);
        }
    }
}
