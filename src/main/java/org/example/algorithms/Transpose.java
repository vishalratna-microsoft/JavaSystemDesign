package org.example.algorithms;

import org.example.graphs.Graphs;
import org.example.graphs.contracts.Graph;

public class Transpose<T> implements Algorithm<Graph<T>, Graph<T>> {
    @Override
    public Graph<T> apply(Graph<T> input) {
        // Undirected graphs transpose() make no sense! return the same graph.
        if (!input.isDirected()) return input;

        Graph<T> output = Graphs.create(Graphs.Type.DIRECTED);

        // Iterate through all the nodes.
        for (T node : input.nodes()) {
            // If there is no connection for this node, just add the node in the transposed graph also.
            if (input.connections(node).size() == 0) {
                output.addNode(node);
                continue;
            }
            // If it has connections, then reverse the connection direction.
            for (T connection : input.connections(node)) {
                output.createConnection(connection, node);
            }
        }
        return output;
    }
}
