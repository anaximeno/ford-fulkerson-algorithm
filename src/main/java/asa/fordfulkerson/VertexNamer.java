package asa.fordfulkerson;

public class VertexNamer {
    static final String prefix = "Ponto ";

    int source, sink;

    public VertexNamer(int sourceVertex, int sinkVertex) {
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
