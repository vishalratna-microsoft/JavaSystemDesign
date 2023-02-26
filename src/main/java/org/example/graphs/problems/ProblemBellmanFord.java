package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.BellmanFordAlgorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.Map;

public class ProblemBellmanFord extends BaseWeightedGraphProblem<Map<Integer, Integer>> {

    @Override
    protected Algorithm<AbstractWeightedGraph<Integer>, Map<Integer, Integer>> algorithm() {
        return new BellmanFordAlgorithm<>(0);
    }

    @Override
    protected AbstractWeightedGraph<Integer> dataset() {
        AbstractWeightedGraph<Integer> graph = Graphs.createWeighted(Graphs.Type.DIRECTED);
        graph.createConnection(0, 1, 5);
        graph.createConnection(1, 2, -2);
        graph.createConnection(2, 4, 3);
        graph.createConnection(1, 5, -3);
        graph.createConnection(5, 3, 1);
        graph.createConnection(3, 4, -2);
        graph.createConnection(3, 2, 6);
        return graph;
    }
}
