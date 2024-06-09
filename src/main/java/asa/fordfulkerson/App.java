package asa.fordfulkerson;

import javax.swing.*;
import java.awt.event.*;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

public class App extends JFrame {
    private mxCircleLayout layout;
    private JGraphXAdapter<String, FlowGraphEdge> jgxAdapter;

    public static void main(String[] args) {
        final App app = new App(640, 480, 100);
        SwingUtilities.invokeLater(() -> app.setVisible(true));
    }

    public App(int width, int height, int graphRadius) {
        super("Ford Fulkerson");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // Create a graph
        ListenableGraph<String, FlowGraphEdge> graph = new DefaultListenableGraph<>(
                new DefaultDirectedGraph<String, FlowGraphEdge>(FlowGraphEdge.class));

        // Create a visualization component
        jgxAdapter = new JGraphXAdapter<>(graph);

        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        // Add visualization component to the frame
        getContentPane().add(component);

        // Add vertices and edges
        graph.addVertex("V1");
        graph.addVertex("V2");
        graph.addVertex("V3");
        graph.addEdge("V1", "V2", new FlowGraphEdge(10, 0));
        graph.addEdge("V2", "V3", new FlowGraphEdge(5, 0));

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
        layout.execute(jgxAdapter.getDefaultParent());
    }
}
