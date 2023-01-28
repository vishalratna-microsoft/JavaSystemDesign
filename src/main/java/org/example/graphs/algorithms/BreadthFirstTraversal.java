package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.*;

public class BreadthFirstTraversal<T> implements Algorithm<Graph<T>, List<T>> {
    @Override
    public List<T> apply(Graph<T> input) {
        return dispatchBfs(input);
    }

    private List<T> dispatchBfs(Graph<T> input) {
        Queue<T> queue = new ArrayDeque<>();
        List<T> bfsOutput = new ArrayList<>();
        Set<T> visited = new HashSet<>();

        for (T node : input.nodes()) {
            bfs(node, input, queue, bfsOutput, visited);
        }
        return bfsOutput;
    }

    private void bfs(T node, Graph<T> input, Queue<T> queue, List<T> bfsOutput, Set<T> visited) {
        queue.offer(node);

        while (!queue.isEmpty()) {
            T item = queue.poll();
            // Ideally we do not require this check.
            if (visited.add(item)) {
                bfsOutput.add(item);
            }
            for (T connection : input.connections(item)) {
                if (!visited.contains(connection)) {
                    queue.offer(connection);
                }
            }
        }
    }
}
