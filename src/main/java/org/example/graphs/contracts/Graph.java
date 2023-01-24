package org.example.graphs.contracts;

import java.util.Objects;
import java.util.Set;

public interface Graph<T> {

    boolean isDirected();

    Set<T> nodes();

    Set<Edge<T>> edges();

    boolean createConnection(T a, T b);

    boolean edgeExists(T a, T b);

    Set<T> connections(T node);

    void addNode(T node);

    final class Edge<T> {
        private T a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return a.equals(edge.a) && b.equals(edge.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        private T b;

        public Edge(T a, T b) {
            this.a = a;
            this.b = b;
        }

        public T getA() {
            return a;
        }

        public void setA(T a) {
            this.a = a;
        }

        public T getB() {
            return b;
        }

        public void setB(T b) {
            this.b = b;
        }
    }
}
