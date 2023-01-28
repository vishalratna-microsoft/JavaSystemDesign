package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

import java.util.*;

public class KahnAlgorithm<T> implements Algorithm<Graph<T>, List<T>> {
    private final Map<T, Integer> mInDegree = new HashMap<>();
    private final Queue<T> mQueue = new ArrayDeque<>();
    private final List<T> mItems = new ArrayList<>();

    @Override
    public List<T> apply(Graph<T> input) {
        preprocess(input);
        while (!mQueue.isEmpty()) {
            T val = mQueue.poll();
            mItems.add(val);
            for (T connection : input.connections(val)) {
                boolean isZero = reduceInDegreeForNode(connection);
                if (isZero) {
                    mQueue.offer(connection);
                }
            }
        }
        return mItems;
    }

    private void preprocess(Graph<T> input) {
        for (T node : input.nodes()) {
            int degree = input.inDegree(node);
            if (degree == 0) {
                mQueue.offer(node);
                mInDegree.put(node, degree);
            } else {
                mInDegree.put(node, degree);
            }
        }
    }

    private boolean reduceInDegreeForNode(T node) {
        int current = mInDegree.get(node);
        mInDegree.put(node, --current);
        return current == 0;
    }
}
