package org.example.graphs;


import org.example.experiment.ScenarioTracker;

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
        ScenarioTracker tracker = new ScenarioTracker();
        tracker.register("vishal","ratna","delta_sync_complete");
    }
}
