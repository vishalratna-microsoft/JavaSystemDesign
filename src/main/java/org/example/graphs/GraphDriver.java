package org.example.graphs;

import org.example.graphs.contracts.Graph;
import org.example.graphs.contracts.Graphs;

public class GraphDriver {

    public static void main(String[] args) {
        Graph<Integer> graph = Graphs.create(Graphs.DIRECTED);
        graph.createConnection(1, 2);
        graph.createConnection(2, 3);
        graph.createConnection(3, 1);
        Graphs.printEdges(graph);
        Graphs.printNodes(graph);
    }
}
