package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.KruskalAlgorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.Set;

public class ProblemSpanningTreeWithKruskal extends BaseWeightedGraphProblem<Set<AbstractWeightedGraph.WeightedEdge<Integer>>> {
    @Override
    protected Algorithm<AbstractWeightedGraph<Integer>, Set<AbstractWeightedGraph.WeightedEdge<Integer>>> algorithm() {
        return new KruskalAlgorithm<>();
    }

    @Override
    protected AbstractWeightedGraph<Integer> dataset() {
        AbstractWeightedGraph<Integer> graph = Graphs.createWeighted(Graphs.Type.UNDIRECTED);
        graph.createConnection(1, 2, 2);
        graph.createConnection(2, 3, 3);
        graph.createConnection(3, 4, 5);
        graph.createConnection(4, 1, 1);

        graph.createConnection(4, 5, 9);
        graph.createConnection(5, 1, 4);

        graph.createConnection(2, 4, 3);
        graph.createConnection(3, 6, 8);
        graph.createConnection(2, 6, 7);

        return graph;
    }
}
