package org.example.graphs;

import org.example.graphs.problems.ProblemGraphTraversal;
import org.example.graphs.problems.ProblemTopologicalSort;
import org.example.graphs.problems.StronglyConnectedComponents;

import java.util.List;
import java.util.Set;

import static org.example.graphs.GraphUtils.printSCCs;

public class GraphDriver {

    public static void main(String[] args) {
       // ProblemGraphTraversal traversal = new ProblemGraphTraversal();
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        Set<List<Integer>> result = scc.execute();
        printSCCs(result);
    }
}
