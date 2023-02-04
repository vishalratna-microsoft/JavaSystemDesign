package org.example.graphs;

import org.example.graphs.base.Graph;
import org.example.graphs.problems.ProblemFindAllBridgesInUnDirectedGraph;

import java.util.Set;

import static org.example.graphs.GraphUtils.printSetOfEdges;

public class GraphDriver {

    public static void main(String[] args) {
        // ProblemGraphTraversal traversal = new ProblemGraphTraversal();
        ProblemFindAllBridgesInUnDirectedGraph findBridges = new ProblemFindAllBridgesInUnDirectedGraph();
        Set<Graph.Edge<Integer>> result = findBridges.execute();
        printSetOfEdges(result);

    }
}
