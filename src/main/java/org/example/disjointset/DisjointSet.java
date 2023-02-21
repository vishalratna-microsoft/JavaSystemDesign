package org.example.disjointset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * General purpose Disjoint set that excepts set of nodes from a graph(Can be general nodes that does not belong to graph).
 * Stores rank and parent information in the hashmap.
 *
 * @param <T> Type of the disjoint set.
 */
public class DisjointSet<T> {
    private final Map<T, T> mParents;
    private final Map<T, Integer> mRanks;

    private boolean enableLogging = false;

    public DisjointSet(Set<T> nodes) {
        mParents = new HashMap<>(nodes.size());
        mRanks = new HashMap<>(nodes.size());
        init(nodes);
    }

    public void unionByRank(T u, T v) {
        T pu = findRepresentative(u);
        T pv = findRepresentative(v);
        if (pu == pv) return;

        if (mRanks.get(pu) > mRanks.get(pv)) {
            // attach v to u
            mParents.put(pv, pu);
        } else if (mRanks.get(pv) > mRanks.get(pu)) {
            mParents.put(pu, pv);
        } else {
            mParents.put(pv, pu);
            mRanks.put(pu, mRanks.get(pu) + 1);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean hasSameRepresentative(T u, T v) {
        if (findRepresentative(u) == findRepresentative(v)) {
            if (enableLogging) {
                System.out.println(u + " and " + v + " has same representative.");
            }
            return true;
        } else {
            if (enableLogging) {
                System.out.println(u + " and " + v + " does not have the same representative.");
            }
            return false;
        }
    }

    public void setEnableLogging(boolean val) {
        this.enableLogging = val;
    }

    public T findRepresentative(T u) {
        if (mParents.get(u) == u) {
            return u;
        }
        T representative = findRepresentative(mParents.get(u));
        // Path compression.
        mParents.put(u, representative);
        return representative;
    }

    // Iterative approach without path compression.
//    private T findRepresentative(T u) {
//        T tmp = u;
//        while (mParents.get(tmp) != tmp) {
//            tmp = mParents.get(tmp);
//        }
//        return tmp;
//    }

    private void init(Set<T> nodes) {
        for (T entry : nodes) {
            mParents.put(entry, entry);
            mRanks.put(entry, 0);
        }
    }
}
