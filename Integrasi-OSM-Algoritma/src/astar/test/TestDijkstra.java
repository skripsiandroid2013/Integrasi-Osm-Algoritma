package astar.test;

import java.util.LinkedList;

import org.junit.Test;

import parsing.model.OSMNode;
import astar.engine.Dijkstra;
import astar.graph.builder.GraphBuilder;
import astar.model.Graph;
import astar.model.Key;
import astar.model.Vertex;
import astar.util.MapMatchingUtil;

public class TestDijkstra {

	@Test
	public void testAlgoritmaDijkstra() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya_new.osm");

		Graph graph = builder.getGraph();

		Dijkstra dijkstra = new Dijkstra(graph);

		// String location[] = Geocode.request();
		// System.out.println(location[0] + ", " + location[1]);

		// buat ngetes, biar < geocoding = gag adoh2 ;
		String unMatchLatLon[] = { "-7.3609237","112.7735955" }; // LatLon ini
																// tidak ada di
																// graph, cek
																// google maps
		OSMNode startNode = MapMatchingUtil.doMatching(graph.getVertexs(),
				unMatchLatLon[0], unMatchLatLon[1]);

	//	Vertex src = graph.fromVertex(new Key("-7.3609237","112.7735955"));
		Vertex src = graph.fromVertex(new Key(startNode.lat, startNode.lon));
		System.out.println("Src vertex = id : " + src + " latlon : "
				+ src.getNode().lat + " , " + src.getNode().lon);

		dijkstra.execute(src);

		Vertex dst = graph.toVertex(new Key("-7.2895664", "112.7805496"));
		System.out.println("Dst vertex = id : " + dst + " latlon : "
				+ dst.getNode().lat + " , " + dst.getNode().lon);

		LinkedList<Vertex> path = dijkstra.getPath(dst);
	//	System.out.println(dijkstra.getPredecessors());

		// fixed path not found
		if (path == null) {
			OSMNode goalNode = MapMatchingUtil.doMatching(
					dijkstra.getPredecessors(), dst.getNode().lat,
					dst.getNode().lon);
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
