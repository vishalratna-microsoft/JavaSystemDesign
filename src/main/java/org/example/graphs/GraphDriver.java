package org.example.graphs;

import org.example.graphs.base.Graph;
import org.example.graphs.problems.ProblemArticulationPoints;
import org.example.graphs.problems.ProblemFindAllBridgesInUnDirectedGraph;
import org.example.graphs.problems.ProblemFindStronglyConnectedComponents;

import java.util.List;
import java.util.Set;

import static org.example.graphs.GraphUtils.*;

public class GraphDriver {

    public static void main(String[] args) {
        // ProblemGraphTraversal traversal = new ProblemGraphTraversal();
//        ProblemFindAllBridgesInUnDirectedGraph findBridges = new ProblemFindAllBridgesInUnDirectedGraph();
//        Set<Graph.Edge<Integer>> result = findBridges.execute();
//        printSetOfEdges(result);
//        ProblemFindStronglyConnectedComponents scc = new ProblemFindStronglyConnectedComponents();
//        printSCCs(scc.execute());
        ProblemArticulationPoints points = new ProblemArticulationPoints();
        printList(List.of(points.execute()));

    }
}
