package asa.fordfulkerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.ListenableGraph;

public class FordFulkerson {
    final int[][] matrix;
    final ListenableGraph<String, FlowGraphEdge> visualGraph;
    final int size;
    final VertexNamer vertexNamer;

    public FordFulkerson(int[][] matrix, ListenableGraph<String, FlowGraphEdge> visualGraph, VertexNamer vertexNamer) {
        this.matrix = matrix;
        this.size = matrix.length;
        this.visualGraph = visualGraph;
        this.vertexNamer = vertexNamer;
    }

    private void resetVisualGraphFlow() {
        for (FlowGraphEdge edge : visualGraph.edgeSet())
            edge.setFlow(0);
    }

    private void displayPathFlow(List<Integer> path, int pathFlow) {
        System.out.println("PathFlow: " + path.toString() + " => " + pathFlow);

        FlowGraphEdge edge;
        String source, target;

        for (int i = 0; i < path.size() - 1; ++i) {
            source = vertexNamer.nameVertex(path.get(i));
            target = vertexNamer.nameVertex(path.get(i + 1));

            if ((edge = visualGraph.getEdge(source, target)) != null)
                edge.setFlow(pathFlow);
        }
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
                rGraph[u][v] = matrix[u][v];

        int parent[] = new int[size];

        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            List<Integer> path = new ArrayList<>();

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
                path.add(v);
            }

            path.add(s);
            Collections.reverse(path);
            displayPathFlow(path, pathFlow);

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
