package asa.fordfulkerson;

import java.util.List;

public class MaxFlowReport {
    final int maxFlow;
    final List<List<Integer>> paths;

    public MaxFlowReport(int maxFlow, List<List<Integer>> paths) {
        this.maxFlow = maxFlow;
        this.paths = paths;
    }
}
