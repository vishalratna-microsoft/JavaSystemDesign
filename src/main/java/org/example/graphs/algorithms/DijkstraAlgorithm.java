package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.*;

/**
 *         AbstractWeightedGraph<String> graph = Graphs.createWeighted(Graphs.Type.UNDIRECTED);
 *         graph.createConnection("A", "B", 6);
 *         graph.createConnection("A", "D", 1);
 *         graph.createConnection("D", "B", 2);
 *         graph.createConnection("B", "E", 2);
 *         graph.createConnection("D", "E", 1);
 *         graph.createConnection("B", "C", 5);
 *         graph.createConnection("E", "C", 5);
 *
 * @param <T>
 */
public class DijkstraAlgorithm<T> implements Algorithm<AbstractWeightedGraph<T>, Map<T, DijkstraAlgorithm.MetaInfo<T>>> {

    private final PriorityQueue<DijkstraEdge<T>> mQueue;
    private final T mStart;
    private final Map<T, MetaInfo<T>> mTracker;

    public DijkstraAlgorithm(T start) {
        this.mQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        this.mStart = start;
        this.mTracker = new HashMap<>();
    }

    private void init(AbstractWeightedGraph<T> input) {
        for (T node : input.nodes()) {
            if (node == mStart) {
                mTracker.put(node, new MetaInfo<>(0, null));
                continue;
            }
            mTracker.put(node, new MetaInfo<>(Integer.MAX_VALUE, null));
        }
    }

    @Override
    public Map<T, MetaInfo<T>> apply(AbstractWeightedGraph<T> input) {
        init(input);
        Set<T> visited = new HashSet<>();
        mQueue.add(new DijkstraEdge<>(0, mStart));
        applyDijkstra(input, visited);
        return mTracker;
    }

    private void applyDijkstra(AbstractWeightedGraph<T> input, Set<T> explored) {
        while (!mQueue.isEmpty()) {
            DijkstraEdge<T> item = mQueue.poll();

            if(!explored.contains(item.current)) {
                explored.add(item.current);
                // Explore connections
                for (T connection : input.connections(item.current)) {
                    if (!explored.contains(connection)) {
                        int weight = input.weight(item.current, connection);
                        // Check if weight to reach connection is better than what we know.
                        int i = mTracker.get(item.current).minDist + weight;
                        if ((i < mTracker.get(connection).minDist)) {
                            MetaInfo<T> t = mTracker.get(connection);
                            t.via = item.current;
                            t.minDist = i;
                            mTracker.put(connection, t);
                        }
                        DijkstraEdge<T> e = new DijkstraEdge<>(mTracker.get(connection).minDist, connection);
                        mQueue.add(e);
                    }
                }
            }
        }
    }

    private static class DijkstraEdge<T> {
        int distance;
        T current;

        DijkstraEdge(int w, T c) {
            this.distance = w;
            this.current = c;
        }
    }

    public static class MetaInfo<T> {
        public int minDist;
        public T via;

        public MetaInfo(int minDist, T via) {
            this.minDist = minDist;
            this.via = via;
        }
    }
}
