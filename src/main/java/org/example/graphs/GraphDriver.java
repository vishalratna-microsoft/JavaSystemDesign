package org.example.graphs;

import org.example.graphs.problems.ProblemGraphTraversal;
import org.example.graphs.problems.ProblemTopologicalSort;

public class GraphDriver {

    public static void main(String[] args) {
        ProblemGraphTraversal traversal = new ProblemGraphTraversal();
        GraphUtils.printList(traversal.execute());
    }
}
