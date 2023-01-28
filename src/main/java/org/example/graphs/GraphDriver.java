package org.example.graphs;

import org.example.graphs.problems.ProblemGraphTraversal;
import org.example.graphs.problems.ProblemTopologicalSort;
import org.example.graphs.problems.StronglyConnectedComponents;

import java.util.List;
import java.util.Set;

public class GraphDriver {

    public static void main(String[] args) {
        ProblemGraphTraversal traversal = new ProblemGraphTraversal();
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        Set<List<Integer>> result = scc.execute();
        for (List<Integer> eachScc : result) {
            for (Integer i : eachScc) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
