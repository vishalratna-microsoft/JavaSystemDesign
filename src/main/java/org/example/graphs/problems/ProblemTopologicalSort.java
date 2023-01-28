package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.algorithms.KahnAlgorithm;
import org.example.graphs.Graphs;
import org.example.graphs.base.Graph;

import java.util.List;

public class ProblemTopologicalSort extends BaseGraphProblem<List<Integer>> {

    @Override
    public Algorithm<Graph<Integer>, List<Integer>> algorithm() {
        return new KahnAlgorithm<>();
    }

    @Override
    public Graph<Integer> dataset() {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
        graph.createConnection(5, 0);
        graph.createConnection(5, 2);
        graph.createConnection(4, 0);
        graph.createConnection(4, 1);
        graph.createConnection(2, 3);
        graph.createConnection(3, 1);
        return graph;
    }
}
