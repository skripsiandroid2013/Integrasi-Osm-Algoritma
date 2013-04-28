package maintest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import org.junit.Test;

import dijkstra.engine.*;
import dijkstra.model.*;

//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

	private List<Vertex> nodes;
	private List<Edge> edges;

	
	private void addJarakAntarNode(String laneId, int sourceLocNo, int destLocNo,int duration) {
		Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		edges.add(lane);
	}
	public void testExcute() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 13; i++) {
			Vertex location = new Vertex("Node_" + i, "Node_" + i);
			nodes.add(location);
		}

		addJarakAntarNode("Edge_0", 0, 1, 85);
		addJarakAntarNode("Edge_1", 0, 2, 217);
		addJarakAntarNode("Edge_2", 0, 4, 173);
		addJarakAntarNode("Edge_3", 2, 6, 186);
		addJarakAntarNode("Edge_4", 2, 7, 103);
		addJarakAntarNode("Edge_5", 3, 7, 183);
		addJarakAntarNode("Edge_6", 5, 8, 250);
		addJarakAntarNode("Edge_7", 8, 9, 84);
		addJarakAntarNode("Edge_8", 7, 9, 167);
		addJarakAntarNode("Edge_9", 4, 9, 502);
		addJarakAntarNode("Edge_10", 9, 10, 40);
		addJarakAntarNode("Edge_11", 1, 10, 20);
		addJarakAntarNode("Edge_12", 10, 1, 600);
		addJarakAntarNode("Edge_13", 1, 0, 40);

		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(10));
		LinkedList<Vertex> path = dijkstra.getPath(nodes.get(4));

		//assertNotNull(path);
		//assertTrue(path.size() > 0);

		for (Vertex vertex : path) {
			System.out.println(vertex);
		}

	}


	public static void main(String []args){
		new TestDijkstraAlgorithm().testExcute();
	}
} 