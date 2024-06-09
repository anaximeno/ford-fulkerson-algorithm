package asa.fordfulkerson;

public class VertexNamer {
    final String prefix;

    private int source, sink;

    public VertexNamer(String prefix, int sourceVertex, int sinkVertex) {
        this.prefix = prefix;
        this.source = sourceVertex;
        this.sink = sinkVertex;
    }

    public void setSourceVertex(int sourceVertex) {
        source = sourceVertex;
    }

    public int getSourceVertex() {
        return source;
    }

    public void setSinkVertex(int sinkVertex) {
        sink = sinkVertex;
    }

    public int getSinkVertex() {
        return sink;
    }

    public String nameVertex(int vertex) {
        String suffix = vertex == source ? " (Source)" : vertex == sink ? " (Sink)" : "";
        return prefix + vertex + suffix;
    }
}
