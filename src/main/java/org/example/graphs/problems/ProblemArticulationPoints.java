package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.Graphs;
import org.example.graphs.algorithms.FindArticulationPointUsingTarjan;
import org.example.graphs.base.Graph;

import java.util.Set;

public class ProblemArticulationPoints extends BaseGraphProblem<Set<Integer>> {

    @Override
    protected Algorithm<Graph<Integer>, Set<Integer>> algorithm() {
        return new FindArticulationPointUsingTarjan<>();
    }

    @Override
    protected Graph<Integer> dataset() {
        Graph<Integer> data = Graphs.create(Graphs.Type.UNDIRECTED);
        data.createConnection(0,1);
        data.createConnection(0,2);
        data.createConnection(0,3);
        data.createConnection(2,3);
        data.createConnection(2,4);
        data.createConnection(4,6);
        data.createConnection(6,5);
        data.createConnection(5,2);
        return data;
    }
}
