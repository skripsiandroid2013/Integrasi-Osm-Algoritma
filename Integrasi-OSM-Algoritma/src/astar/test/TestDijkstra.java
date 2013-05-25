package astar.test;

import static org.junit.Assert.*;
import geocoding.engine.Geocode;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import parsing.model.OSMNode;
import parsing.util.LatLongUtil;
import astar.engine.Dijkstra;
import astar.graph.builder.GraphBuilder;
import astar.model.Graph;
import astar.model.Key;
import astar.model.Vertex;
import astar.util.MapMatchingUtil;

public class TestDijkstra {

	@Test
	public void testAlgoritmaDijkstra() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");

		Graph graph = builder.getGraph();

		Dijkstra dijkstra = new Dijkstra(graph);

		String location[] = Geocode.request();
		System.out.println(location[0] + ", " + location[1]);

		// OSMNode startNode = MapMatchingUtil.doMatching(graph.getVertexs(),
		// location[0], location[1]);
		//
		// Vertex src = graph.fromVertex(new Key("-7.3271793","112.7791673"));
		Vertex src = graph.fromVertex(new Key("-7.3298113", "112.8042761"));
		// Vertex src = graph.fromVertex(new Key(startNode.lat,startNode.lon));

		dijkstra.execute(src);

		System.out.println("Src vertex : " + src);

		Vertex dst = graph.toVertex(new Key("-7.3315294", "112.8033978"));
		System.out.println("Dst vertex : " + dst);

		LinkedList<Vertex> path = dijkstra.getPath(dst);
		System.out.println(dijkstra.getPredecessors());
		if (path == null) {
			OSMNode goalNode = MapMatchingUtil.doMatching(dijkstra.getPredecessors(),
					dst.getNode().lat, dst.getNode().lon);
			dst = graph.toVertex(new Key(goalNode.lat, goalNode.lon));
			path = dijkstra.getPath(dst);
		}
		
		System.out.print("path : ");
		for (Vertex vertex : path) {
			System.out.print(vertex + " --> ");
		}
		System.out.println();

	}

}
