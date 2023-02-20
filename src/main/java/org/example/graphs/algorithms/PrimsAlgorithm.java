package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimsAlgorithm<T> implements Algorithm<AbstractWeightedGraph<T>, PrimsAlgorithm.PrimResult<T>> {
    private final PriorityQueue<PrimsBlock<T>> mQueue;
    private int sum = 0;

    public PrimsAlgorithm() {
        this.mQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    }

    @Override
    public PrimsAlgorithm.PrimResult<T> apply(AbstractWeightedGraph<T> input) {
        T node = input.nodes().iterator().next();
        Set<Graph.Edge<T>> result = new HashSet<>();
        Set<T> visited = new HashSet<>();
        // Prepare the initial configuration
        PrimsBlock<T> block = new PrimsBlock<>(0, node, null);
        mQueue.add(block);
        applyPrims(input, visited, result);
        return new PrimResult<>(result, sum);
    }

    private void applyPrims(AbstractWeightedGraph<T> graph, Set<T> visited, Set<Graph.Edge<T>> result) {
        while (!mQueue.isEmpty()) {
            PrimsBlock<T> block = mQueue.poll();
            if (visited.contains(block.node)) continue;

            visited.add(block.node);
            if (block.parent != null) {
                // Add the edge to the spanning tree!
                sum += block.weight;
                result.add(graph.edge(block.node, block.parent));
            }
            // Look for the connections and add the edges to the queue.
            for (T connection : graph.connections(block.node)) {
                if (!visited.contains(connection)) {
                    PrimsBlock<T> b = new PrimsBlock<>(graph.weight(block.node, connection), connection, block.node);
                    mQueue.add(b);
                }
            }
        }
    }

    // Represents the entity that is held in the priority queue, having all the data points to create an edge in a
    // spanning tree.
    private static class PrimsBlock<T> {
        int weight;
        T node;
        T parent;

        PrimsBlock(int weight, T node, T parent) {
            this.weight = weight;
            this.node = node;
            this.parent = parent;
        }
    }

    // Just a pair of values to represent spanning tree edges and minimum sum.
    public static final class PrimResult<T> {
        PrimResult(Set<Graph.Edge<T>> contributingEdges, int sum) {
            this.contributingEdges = contributingEdges;
            this.sum = sum;
        }

        public Set<Graph.Edge<T>> contributingEdges;
        public int sum;
    }
}
