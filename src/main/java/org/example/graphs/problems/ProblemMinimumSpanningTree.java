package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.PrimsAlgorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

public class ProblemMinimumSpanningTree extends BaseWeightedGraphProblem<PrimsAlgorithm.PrimResult<Integer>> {

    @Override
    protected Algorithm<AbstractWeightedGraph<Integer>, PrimsAlgorithm.PrimResult<Integer>> algorithm() {
        return new PrimsAlgorithm<>();
    }

    @Override
    protected AbstractWeightedGraph<Integer> dataset() {
        AbstractWeightedGraph<Integer> graph = Graphs.createWeighted(Graphs.Type.UNDIRECTED);
        graph.createConnection(0, 1, 2);
        graph.createConnection(0, 2, 1);
        graph.createConnection(2, 1, 1);
        graph.createConnection(2, 4, 2);
        graph.createConnection(2, 3, 2);
        graph.createConnection(3, 4, 1);
        return graph;
    }
}