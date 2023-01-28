package org.example.graphs.problems;

import org.example.Algorithm;
import org.example.graphs.base.Graph;

/**
 * This is an abstraction for any graph problem that we solve. It is created to encapsulate, the
 * algorithm required to solve the problem and the data set. Every graph problem has a unique data that it
 * operates on. Keeping all the data is tricky in a single class and makes things cluttery.
 * Our agenda is simple, that is exhibited in execute() method, take the algorithms and apply the dataset on it.
 *
 * @param <O> Result of the graph problem.
 */
public abstract class BaseGraphProblem<O> {

    protected abstract Algorithm<Graph<Integer>, O> algorithm();

    protected abstract Graph<Integer> dataset();

    public final O execute() {
        return algorithm().apply(dataset());
    }
}
