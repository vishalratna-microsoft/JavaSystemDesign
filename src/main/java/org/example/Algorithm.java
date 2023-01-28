package org.example;

/**
 * Represents a general purpose algorithms that applies on a data structure of type I.
 * Returns an output of type O
 *
 * @param <I> Type of DS on which algorithms has to run.
 * @param <O> Output of the algorithms.
 */
public interface Algorithm<I, O> {
    /**
     * Method that runs the algorithm on the input structure of type I
     *
     * @param input input input
     * @return output input, or null.
     */
    O apply(I input);
}
