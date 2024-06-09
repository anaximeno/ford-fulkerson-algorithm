package asa.fordfulkerson;

import java.util.Arrays;
import java.util.LinkedList;

import org.jgrapht.ListenableGraph;

public class FordFulkerson {
    final int[][] adjGraph;
    final ListenableGraph<String, FlowGraphEdge> visualGraph;
    final int size;

    public FordFulkerson(int[][] adjGraph, ListenableGraph<String, FlowGraphEdge> visualGraph) {
        this.adjGraph = adjGraph;
        this.size = adjGraph.length;
        this.visualGraph = visualGraph;
    }

    private void resetVisualGraphFlow() {
        for (FlowGraphEdge edge : visualGraph.edgeSet())
            edge.setFlow(0);
    }

    private boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[size];

        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < size; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false;
    }

    public int maxFlow(int s, int t) {
        resetVisualGraphFlow();

        int u, v;

        int rGraph[][] = new int[size][size];
        for (u = 0; u < size; u++)
            for (v = 0; v < size; v++)
                rGraph[u][v] = adjGraph[u][v];

        int parent[] = new int[size];

        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        System.out.println(rGraph);

        return maxFlow;
    }
}
