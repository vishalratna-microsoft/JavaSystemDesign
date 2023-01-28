package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.KosarajuAlgorithm;
import org.example.graphs.base.Graph;

import java.util.List;
import java.util.Set;

public class StronglyConnectedComponents extends BaseGraphProblem<Set<List<Integer>>> {

    @Override
    protected Algorithm<Graph<Integer>, Set<List<Integer>>> algorithm() {
        return new KosarajuAlgorithm<>();
    }

    @Override
    protected Graph<Integer> dataset() {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);

        // TestCase 1:
//        graph.createConnection(1,0);
//        graph.createConnection(0,3);
//        graph.createConnection(3,4);
//        graph.createConnection(2,1);
//        graph.createConnection(0,2);

        // TestCase 2:
//        graph.createConnection(1,2);
//        graph.createConnection(2,3);
//        graph.createConnection(3,1);
//        graph.createConnection(3,5);
//        graph.createConnection(5,6);
//        graph.createConnection(6,4);
//        graph.createConnection(4,5);
//        graph.createConnection(7,6);

        // TestCase 3:
        graph.createConnection(0,1);
        graph.createConnection(1,2);
        graph.createConnection(2,0);
        graph.createConnection(2,3);
        graph.createConnection(3,4);
        graph.createConnection(4,5);
        graph.createConnection(5,6);
        graph.createConnection(6,7);
        graph.createConnection(4,7);
        graph.createConnection(6,4);

        return graph;
    }
}
