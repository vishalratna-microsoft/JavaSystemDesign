package org.example.graphs;

import org.example.graphs.contracts.Graph;

import static java.lang.System.out;

public class GraphDriver {

    public static void main(String[] args) {
        Graph<Integer> graph = Graphs.create(Graphs.Type.DIRECTED);
//        graph.createConnection(2,1);
//        graph.createConnection(2,4);
//        graph.createConnection(3,2);
//        graph.createConnection(3,1);
//        graph.createConnection(4,3);
        graph.createConnection(0,1);
        graph.createConnection(0,2);
        graph.createConnection(2,3);
        graph.createConnection(2,0);
        graph.createConnection(1,2);
        graph.createConnection(3,3);

        out.println(graph.degree(0));
    }
}
