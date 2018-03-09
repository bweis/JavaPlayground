package SumSquareProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Creates a <tt>Graph</tt>
 *
 * @author Benjamin Weis
 */
public class Graph<T> implements Iterable<T> {
    private Map<T, Set<T>> vertexMap;
    private int numEdges;

    public Graph() {
        vertexMap = new HashMap<>();
    }

    public int getNumVertices() {
        return vertexMap.size();
    }

    public int getNumEdges() {
        return numEdges;
    }


    private void validateVertex(T v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v.toString() + " is not a vertex");
    }

    public int degree(T v) {
        validateVertex(v);
        return vertexMap.get(v).size();
    }

    public void addEdge(T v, T w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) numEdges++;
        vertexMap.get(v).add(w);
        vertexMap.get(w).add(v);
    }

    public void addVertex(T v) {
        if (!hasVertex(v)) vertexMap.put(v, new HashSet<T>());
    }

    public boolean hasEdge(T v, T w) {
        validateVertex(v);
        validateVertex(w);
        return vertexMap.get(v).contains(w);
    }

    public boolean hasVertex(T v) {
        return vertexMap.containsKey(v);
    }

    @Override
    public Iterator<T> iterator() {
        return vertexMap.keySet().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T v : vertexMap.keySet()) {
            sb.append(v.toString()).append(": ");
            for (T w : vertexMap.get(v)) {
                sb.append(w.toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("D", "G");
        graph.addEdge("E", "G");
        graph.addVertex("H");

        System.out.println(graph);

        System.out.println("Vertices: " + graph.getNumVertices());
        System.out.println("Edges: " + graph.getNumEdges());
    }
}