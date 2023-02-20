package org.example.graphs;


import org.example.experiment.ScenarioTracker;
import org.example.graphs.algorithms.PrimsAlgorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;
import org.example.graphs.problems.ProblemMinimumSpanningTree;

public class GraphDriver {

    public static void main(String[] args) {
        // ProblemGraphTraversal traversal = new ProblemGraphTraversal();
//        ProblemFindAllBridgesInUnDirectedGraph findBridges = new ProblemFindAllBridgesInUnDirectedGraph();
//        Set<Graph.Edge<Integer>> result = findBridges.execute();
//        printSetOfEdges(result);
//        ProblemFindStronglyConnectedComponents scc = new ProblemFindStronglyConnectedComponents();
//        printSCCs(scc.execute());
//        ProblemArticulationPoints points = new ProblemArticulationPoints();
//        printList(List.of(points.execute()));

        ProblemMinimumSpanningTree spanningTree = new ProblemMinimumSpanningTree();
        PrimsAlgorithm.PrimResult<Integer> result = spanningTree.execute();
        GraphUtils.printSetOfEdges(result.contributingEdges);
        System.out.println("Sum: " + result.sum);
    }
}
