package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.DepthFirstTraversal;
import org.example.graphs.base.Graph;

import java.util.List;

public class ProblemGraphTraversal extends BaseGraphProblem<List<Integer>> {

    @Override
    protected Algorithm<Graph<Integer>, List<Integer>> algorithm() {
        return new DepthFirstTraversal<>();
    }

    @Override
    protected Graph<Integer> dataset() {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
        graph.createConnection(2,1);
        graph.createConnection(2,4);
        graph.createConnection(3,2);
        graph.createConnection(3,1);
        graph.createConnection(4,3);
        return graph;
    }
}
