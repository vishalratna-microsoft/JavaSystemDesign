package org.example.graphs.algorithms;

import org.example.Algorithm;
import org.example.graphs.implementation.AbstractWeightedGraph;

import java.util.*;

/**
 *         AbstractWeightedGraph<String> graph = Graphs.createWeighted(Graphs.Type.DIRECTED);
 *         graph.createConnection("0", "1", 5);
 *         graph.createConnection("1", "2", 5);
 *         graph.createConnection("0", "3", 2);
 *         graph.createConnection("3", "1", 2);
 *         graph.createConnection("1", "4", 1);
 *         graph.createConnection("4", "2", 1);
 *
 *         graph.createConnection("0", "1", 100);
 *         graph.createConnection("1", "3", 600);
 *         graph.createConnection("1", "2", 100);
 *         graph.createConnection("2", "0", 100);
 *         graph.createConnection("2", "3", 200);
 */
public class CheapestFlightsWithKStops implements Algorithm<AbstractWeightedGraph<String>, Integer> {

    private final String mStart;
    private final String mEnd;
    private final int mMaxStops;

    private Map<String, DijkstraAlgorithm.MetaInfo<String>> mInfo;
    private Queue<QueueEntry> mQueue;


    public CheapestFlightsWithKStops(String start, String end, int k) {
        this.mStart = start;
        this.mEnd = end;
        this.mMaxStops = k;
    }

    @Override
    public Integer apply(AbstractWeightedGraph<String> input) {
        mQueue = new ArrayDeque<>();
        init(input);
        mQueue.offer(new QueueEntry(mStart, null, 0,0));
        return applyAlgo(input);
    }

    private int applyAlgo(AbstractWeightedGraph<String> input) {
        while (!mQueue.isEmpty()) {
            QueueEntry e = mQueue.poll();
            if (e.stops > mMaxStops) continue;

            // Explore connections.
            for (String connection : input.connections(e.current)) {
                int costToReachCurrent = e.weight;
                int costToConnection = input.weight(e.current, connection);
                if ((costToReachCurrent + costToConnection) < mInfo.get(connection).minDist && e.stops <= mMaxStops) {
                    DijkstraAlgorithm.MetaInfo<String> tmp = mInfo.get(connection);
                    tmp.minDist = costToConnection + costToReachCurrent;
                    mInfo.put(connection, tmp);
                    QueueEntry next = new QueueEntry(connection, e.current, e.stops + 1, tmp.minDist);
                    mQueue.offer(next);
                }
            }
        }
        // Prepare the result set.
        if (mInfo.get(mEnd).minDist == Integer.MAX_VALUE) {
            return -1; // No flights possible.
        } else {
           return mInfo.get(mEnd).minDist;
        }
    }

    private void init(AbstractWeightedGraph<String> input) {
        mInfo = new HashMap<>();
        for (String s : input.nodes()) {
            if (s.equalsIgnoreCase(mStart)) {
                mInfo.put(s, new DijkstraAlgorithm.MetaInfo<>(0, null));
                continue;
            }
            mInfo.put(s, new DijkstraAlgorithm.MetaInfo<>(Integer.MAX_VALUE, null));
        }
    }

    private static class QueueEntry {
        String current;
        String parent;
        int stops;
        int weight;
        QueueEntry(String curr, String parent, int stops, int weight) {
            this.current = curr;
            this.parent = parent;
            this.stops = stops;
            this.weight = weight;
        }
    }
}
