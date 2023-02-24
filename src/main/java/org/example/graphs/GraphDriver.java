package org.example.graphs;


import org.example.graphs.algorithms.DijkstraAlgorithm;
import org.example.graphs.base.Graph;
import org.example.graphs.implementation.AbstractWeightedGraph;
import org.example.graphs.problems.ProblemSpanningTreeWithKruskal;

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

//        Graph<Integer> graph = Graphs.create(Graphs.Type.UNDIRECTED);
//        for (int i = 1; i <= 7; i++) {
//            graph.addNode(i);
//        }
//        DisjointSet<Integer> set = new DisjointSet<>(graph.nodes());
//        set.hasSameRepresentative(1,2);
//        set.unionByRank(1, 2);
//        set.unionByRank(2, 3);
//        set.unionByRank(4, 5);
//        set.unionByRank(6, 7);
//        set.unionByRank(5, 6);
//        set.unionByRank(3, 7);
//        set.hasSameRepresentative(1,6);

//        AbstractWeightedGraph<Integer> graph = Graphs.createWeighted(Graphs.Type.UNDIRECTED);
//        graph.createConnection(1,2,2);
//        graph.createConnection(2,3,4);
//        graph.createConnection(3,4,3);
//        graph.createConnection(4,1,1);
//        graph.createConnection(2,5,5);
//        graph.createConnection(5,3,1);
//
//        DijkstraAlgorithm<Integer> algorithm = new DijkstraAlgorithm<>(1);
//        algorithm.apply(graph);
//        System.out.println();

        AbstractWeightedGraph<String> graph = Graphs.createWeighted(Graphs.Type.UNDIRECTED);
        graph.createConnection("A", "B", 6);
        graph.createConnection("A", "D", 1);
        graph.createConnection("D", "B", 2);
        graph.createConnection("B", "E", 2);
        graph.createConnection("D", "E", 1);
        graph.createConnection("B", "C", 5);
        graph.createConnection("E", "C", 5);

        DijkstraAlgorithm<String> algorithm = new DijkstraAlgorithm<>("A");
        algorithm.apply(graph);
        System.out.println();


    }
}
