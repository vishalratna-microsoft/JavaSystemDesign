package org.example.graphs;


import org.example.disjointset.DisjointSet;
import org.example.graphs.base.Graph;

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
//
//        ProblemMinimumSpanningTree spanningTree = new ProblemMinimumSpanningTree();
//        PrimsAlgorithm.PrimResult<Integer> result = spanningTree.execute();
//        GraphUtils.printSetOfEdges(result.contributingEdges);
//        System.out.println("Sum: " + result.sum);

        Graph<Integer> graph = Graphs.create(Graphs.Type.UNDIRECTED);
        for (int i = 1; i <= 7; i++) {
            graph.addNode(i);
        }
        DisjointSet<Integer> set = new DisjointSet<>(graph.nodes());
        set.hasSameRepresentative(1,2);
        set.unionByRank(1, 2);
        set.unionByRank(2, 3);
        set.unionByRank(4, 5);
        set.unionByRank(6, 7);
        set.unionByRank(5, 6);
        set.unionByRank(3, 7);
        set.hasSameRepresentative(1,6);
    }
}
