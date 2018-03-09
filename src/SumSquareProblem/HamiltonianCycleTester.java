package SumSquareProblem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HamiltonianCycleTester {
    static List<Integer> path;
    final static int MAX_NODES = 16;

    public static void main(String[] args) {
        Graph graph = getAddendGraph();
        findHamiltonianPath(graph);
        System.out.println(graph);

    }

    private static Graph getAddendGraph() {
        Graph g = new Graph();
        for (int i = 1; i <= MAX_NODES; i++) {
            for(Integer addend : getPerfectSquareAddends(i)) {
                g.addEdge(i, addend);
            }
        }
        return g;
    }

    private static ArrayList<Integer> getPerfectSquareAddends(int sum) {
        ArrayList<Integer> perfectSquareAddends = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(MAX_NODES); i++) {
            if (i * i > sum) {
                perfectSquareAddends.add(i * i - sum);
            }
        }
        return perfectSquareAddends;
    }



    private static void findHamiltonianPath(Graph graph) {
        path = new LinkedList<>();
        if (!hamPathUtil(graph)) {
            System.out.println("\nSolution does not exist");
        } else {
            System.out.println("\nCycle Exists");
            System.out.println(path);
        }
    }


    private static boolean hamPathUtil(Graph graph)
    {
        // base case: If all vertices are included in Hamiltonian Path
        // and if there is an edge from the last included vertex to the first vertex
        if (path.size() == MAX_NODES) {
            return (graph.hasEdge(path.get(0), path.get(path.size() - 1)));
        }

        // Try different vertices as a next candidate in Hamiltonian Path. We don't try for 0 as we included 0 as starting point in in hamPath()
        Iterator iterator = graph.iterator();
        while (iterator.hasNext()) {
            Integer vertex = (Integer) iterator.next();
            if (!path.contains(vertex)) {
                path.add(vertex);
                if (hamPathUtil(graph)) {
                    return true;
                } else {
                    path.remove(path.size() - 1);
                }
            }
        }
        return false;
    }

}
