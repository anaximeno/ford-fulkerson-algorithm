package asa.fordfulkerson;

import javax.swing.*;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

public class App extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App(640, 480, 100).setVisible(true));
    }

    public App(int width, int height, int graphRadius) {
        super("Ford Fulkerson");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // Create a graph
        ListenableGraph<String, DefaultEdge> graph = new DefaultListenableGraph<>(
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class));

        // Create a visualization component
        JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(graph);

        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        // Add visualization component to the frame
        getContentPane().add(component);

        // Add vertices and edges
        graph.addVertex("V1");
        graph.addVertex("V2");
        graph.addVertex("V3");
        graph.addEdge("V1", "V2");
        graph.addEdge("V2", "V3");

        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        layout.setX0((width / 2.0) - graphRadius);
        layout.setY0((height / 2.0) - graphRadius);
        layout.setRadius(graphRadius);
        layout.setMoveCircle(true);
        layout.execute(jgxAdapter.getDefaultParent());
    }
}
