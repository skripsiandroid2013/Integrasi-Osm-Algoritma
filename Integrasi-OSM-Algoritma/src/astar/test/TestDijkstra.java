package astar.test;
import java.util.LinkedList;


import org.junit.Test;
import astar.engine.Dijkstra;
import astar.graph.builder.GraphBuilder;
import astar.model.Graph;
import astar.model.Key;
import astar.model.Vertex;

public class TestDijkstra {

	@Test
	public void testAlgoritmaDijkstra() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");
		Graph graph = builder.getGraph();
	
		Dijkstra dijkstra = new Dijkstra(graph);
		
		dijkstra.execute(graph.fromVertex(new Key("-7.3161762","112.7918078")));
		
		LinkedList<Vertex> path = dijkstra.getPath(graph.toVertex(new Key("-7.3164693","112.7903005")));
		
		System.out.print("[");
		for (Vertex vertex : path) {
			System.out.print(vertex + ", ");
		}
		System.out.print("]");
		System.out.println();
	}

}
