package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.disjointset.DisjointSet;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KruskalAlgorithm<T> implements Algorithm<AbstractWeightedGraph<T>, Set<AbstractWeightedGraph.WeightedEdge<T>>> {
    private PriorityQueue<AbstractWeightedGraph.WeightedEdge<T>> mQueue;

    public KruskalAlgorithm() {
    }

    @Override
    public Set<AbstractWeightedGraph.WeightedEdge<T>> apply(AbstractWeightedGraph<T> input) {
        Set<AbstractWeightedGraph.WeightedEdge<T>> mContributingEdges = new HashSet<>();
        mQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        mQueue.addAll(input.modelledEdges());
        applyKruskal(input, mContributingEdges);
        return mContributingEdges;
    }

    private void applyKruskal(AbstractWeightedGraph<T> graph, Set<AbstractWeightedGraph.WeightedEdge<T>> resultSet) {
        int sum = 0;
        DisjointSet<T> set = new DisjointSet<>(graph.nodes());
        while (!mQueue.isEmpty()) {
            AbstractWeightedGraph.WeightedEdge<T> item = mQueue.poll();
            if (!set.hasSameRepresentative(item.a, item.b)) {
                set.unionByRank(item.a, item.b);
                sum += item.weight;
                resultSet.add(item);
            }
        }

        System.out.println("Spanning tree weight: " + sum);
    }
}
