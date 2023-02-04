package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.FindBridgeUsingTarjan;
import org.example.graphs.base.Graph;

import java.util.Set;

public class ProblemFindAllBridgesInUnDirectedGraph extends BaseGraphProblem<Set<Graph.Edge<Integer>>> {

    @Override
    protected Algorithm<Graph<Integer>, Set<Graph.Edge<Integer>>> algorithm() {
        return new FindBridgeUsingTarjan<>();
    }

    @Override
    protected Graph<Integer> dataset() {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
//        graph.createConnection(0,1);
//        graph.createConnection(1,2);
//        graph.createConnection(2,0);
//        graph.createConnection(2,3);
//        graph.createConnection(3,4);
//        graph.createConnection(4,5);
//        graph.createConnection(5,6);
//        graph.createConnection(6,7);
//        graph.createConnection(4,7);
//        graph.createConnection(6,4);

//        graph.createConnection(0,1);
//        graph.createConnection(1,2);
//        graph.createConnection(2,3);

        graph.createConnection(1, 2);
        graph.createConnection(2, 3);
        graph.createConnection(3, 1);
        graph.createConnection(3, 5);
        graph.createConnection(5, 6);
        graph.createConnection(6, 4);
        graph.createConnection(4, 5);
        graph.createConnection(7, 6);

//        graph.createConnection(1,2);
//        graph.createConnection(2,3);
//        graph.createConnection(3,4);
//        graph.createConnection(4,1);
//        graph.createConnection(4,5);
//        graph.createConnection(5,6);
//        graph.createConnection(6,7);
//        graph.createConnection(7,8);
//        graph.createConnection(8,9);
//        graph.createConnection(9,6);
//        graph.createConnection(8,10);
//        graph.createConnection(10,11);
//        graph.createConnection(11,12);
//        graph.createConnection(12,10);

        return graph;
    }
}
