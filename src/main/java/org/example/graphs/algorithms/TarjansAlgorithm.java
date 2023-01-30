package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.*;

public class TarjansAlgorithm<T> implements Algorithm<Graph<T>, Set<List<T>>> {
    private int mIdCounter = 0;
    private final Map<T, Integer> discovery = new HashMap<>();
    private final Map<T, Integer> lowLinkValues = new HashMap<>();
    private final Stack<T> stack = new Stack<>();
    private final Set<List<T>> scc = new HashSet<>();

    @Override
    public Set<List<T>> apply(Graph<T> input) {
        return dispatchTarjans(input);
    }

    private Set<List<T>> dispatchTarjans(Graph<T> graph) {
        for (T node : graph.nodes()) {
            if (!discovery.containsKey(node)) {
                tarjan(node, graph);
            }
        }
        return scc;
    }

    private void tarjan(T node, Graph<T> graph) {
        // Book-keep information
        discovery.put(node, mIdCounter);
        lowLinkValues.put(node, mIdCounter);
        mIdCounter++;
        stack.push(node);

        for (T connection : graph.connections(node)) {
            if (!discovery.containsKey(connection)) {
                // It is a tree edge.
                tarjan(connection, graph);
                lowLinkValues.put(node, Math.min(lowLinkValues.get(node), lowLinkValues.get(connection)));
            } else {
                if (stack.contains(connection)) {
                    // It is a back edge.
                    lowLinkValues.put(node, Math.min(lowLinkValues.get(node), discovery.get(connection)));
                }
            }
        }
        // When the node's connections are full explored. Check if this is a start of SCC.
        // Pop till we encounter the head node of the SCC.
        if (Objects.equals(discovery.get(node), lowLinkValues.get(node))) {
            List<T> component = new ArrayList<>();
            T item;
            while ((item = stack.pop()) != node) {
                component.add(item);
            }
            component.add(item);
            scc.add(component);
        }
    }
}
