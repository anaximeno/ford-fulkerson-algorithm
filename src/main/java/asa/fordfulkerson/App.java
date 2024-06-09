package asa.fordfulkerson;

import javax.swing.*;
import java.awt.event.*;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import asa.fordfulkerson.examples.*;

public class App extends JFrame {
    private ListenableGraph<String, FlowGraphEdge> graph;
    private JGraphXAdapter<String, FlowGraphEdge> jgxAdapter;
    private mxCircleLayout layout;

    public static void main(String[] args) {
        final App app = new App(640, 480, 100);
        SwingUtilities.invokeLater(() -> app.setVisible(true));
    }

    public App(int width, int height, int graphRadius) {
        super("ASA - Ford Fulkerson Algorithm");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // Create a graph
        graph = new DefaultListenableGraph<>(new DefaultDirectedGraph<String, FlowGraphEdge>(FlowGraphEdge.class));

        // Create a visualization component
        jgxAdapter = new JGraphXAdapter<>(graph);

        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);

        // Add visualization component to the frame
        getContentPane().add(component);

        Example example = Example.fromType(ExampleType.EXTREME);
        VertexNamer vertexNamer = new VertexNamer(example.source, example.sink);

        initializeGraph(example.matrix, vertexNamer);

        FordFulkerson f = new FordFulkerson(example.matrix, graph);
        int maxFlow = f.maxFlow(example.source, example.sink);

        System.out.println("Max Flow: " + maxFlow);

        layout = new mxCircleLayout(jgxAdapter);
        adjustLayout(width, height, graphRadius);

        component.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                final int width = e.getComponent().getWidth();
                final int height = e.getComponent().getHeight();
                adjustLayout(width, height, layout.getRadius());
            }
        });
    }

    private void adjustLayout(int width, int height, double graphRadius) {
        layout.setX0((width / 2.0) - graphRadius);
        layout.setY0((height / 2.0) - graphRadius);
        layout.setRadius(graphRadius);
        layout.setMoveCircle(true);
        layout.setUseBoundingBox(true);
        layout.execute(jgxAdapter.getDefaultParent());
    }

    private void initializeGraph(int[][] matrix, VertexNamer namer) {
        // we suppose it's a square matrix
        final int size = matrix.length;

        for (int i = 0; i < size; ++i)
            graph.addVertex(namer.nameVertex(i));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j] > 0) {
                    String source = namer.nameVertex(i);
                    String target = namer.nameVertex(j);
                    FlowGraphEdge edge = FlowGraphEdge.fromCapacity(matrix[i][j]);
                    graph.addEdge(source, target, edge);
                }
            }
        }
    }
}
