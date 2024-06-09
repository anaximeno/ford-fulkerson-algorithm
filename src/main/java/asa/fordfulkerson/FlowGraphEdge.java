package asa.fordfulkerson;

import org.jgrapht.graph.DefaultEdge;

public class FlowGraphEdge extends DefaultEdge {
    private int capacity;
    private int flow;

    public FlowGraphEdge(int capacity, int flow) {
        this.capacity = capacity;
        this.flow = flow;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return flow + "/" + capacity;
    }
}
