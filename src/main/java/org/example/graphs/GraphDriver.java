package org.example.graphs;

import org.example.graphs.problems.ProblemTopologicalSort;

public class GraphDriver {

    public static void main(String[] args) {
//        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
////        graph.createConnection(2,1);
////        graph.createConnection(2,4);
////        graph.createConnection(3,2);
////        graph.createConnection(3,1);
////        graph.createConnection(4,3);
//        graph.createConnection(5,0);
//        graph.createConnection(5,2);
//        graph.createConnection(4,0);
//        graph.createConnection(4,1);
//        graph.createConnection(2,3);
//        graph.createConnection(3,1);
//
//       GraphUtils.printList(GraphUtils.topSort(graph));
        ProblemTopologicalSort problemKahnAlgorithm = new ProblemTopologicalSort();
        GraphUtils.printList(problemKahnAlgorithm.execute());
    }
}
