package org.example.graphs;

import org.example.graphs.contracts.Graph;

import static java.lang.System.out;

public class GraphDriver {

    public static void main(String[] args) {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
        graph.createConnection(1, 2);
        graph.createConnection(2, 3);
        graph.createConnection(3, 4);
        graph.createConnection(1, 4);
        graph.addNode(10);
        Graphs.printEdges(graph);
        out.println("****");
        Graphs.printEdges(graph.transpose());
    }
}
