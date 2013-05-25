package astar.test;

import geocoding.engine.Geocode;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Test;

import parsing.model.OSMNode;
import parsing.util.LatLongUtil;
import astar.engine.AStar;
import astar.engine.AStarHeuristic;
import astar.engine.EuclidianHeuristic;
import astar.graph.builder.GraphBuilder;
import astar.model.Graph;
import astar.model.Key;
import astar.model.Vertex;

public class TestAstar {

	@Test
	public void testAlgoritmaAstar() throws Exception {

		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");

		Graph graph = builder.getGraph();

		AStarHeuristic heuristic = new EuclidianHeuristic();
		AStar aStar = new AStar(graph, heuristic);

		String location[] = Geocode.request();
		System.out.println(location[0] + ", " + location[1]);

		/*
		 * MATCH 1
		 */

		// int i = 0;
		// OSMNode hasilAkjhir = null;
		// for (Vertex vertex : graph.getVertexs()) {
		// OSMNode node = vertex.getNode();
		// double jarak = 0;
		// if (i != 0) {
		// if (jarak < LatLongUtil.distance(lat, lon,
		// Double.parseDouble(node.lat),
		// Double.parseDouble(node.lon))) {
		// hasilAkjhir = node;
		// jarak = LatLongUtil.distance(lat, lon,
		// Double.parseDouble(node.lat),
		// Double.parseDouble(node.lon));
		// System.out.println("x" + jarak);
		// }
		// } else {
		// jarak = LatLongUtil.distance(lat, lon,
		// Double.parseDouble(node.lat),
		// Double.parseDouble(node.lon));
		// System.out.println("a" + jarak);
		// }
		// i++;
		// }
		//
		// System.out.println(hasilAkjhir.id);

		OSMNode minNode = doMatching(graph.getVertexs(),
				Double.parseDouble(location[0]),
				Double.parseDouble(location[1]));

		Vertex start = graph.fromVertex(new Key(minNode.lat, minNode.lon));
		System.out.println(start.getNode());

		Vertex goal = graph.toVertex(new Key("-7.2517932", "112.6502037"));

		aStar.execute(start, goal);

		LinkedList<Vertex> path = aStar.getPath();

		if (path != null) {

			System.out.print("[");
			for (Vertex vertex : path) {
				System.out.print(vertex + ", ");
			}
			System.out.print("]");
			System.out.println();
		}

	}

	/*
	 * MATCH 2
	 */

	private OSMNode doMatching(Collection<Vertex> vertexs, double gpsLat,
			double gpsLon) {
		OSMNode minNode = null;

		double minJarak = Double.MAX_VALUE;
		for (Vertex vertex : vertexs) {

			OSMNode node2 = vertex.getNode();

			double jarak = LatLongUtil.distance(gpsLat, gpsLon,
					Double.parseDouble(node2.lat),
					Double.parseDouble(node2.lon));

			// System.out.println(jarak);

			if (jarak < minJarak) {
				minJarak = jarak;
				minNode = node2;
			}

		}

		// TEST
		System.out.println("======================================");

		System.out.println(" minJarak : " + minJarak);

		System.out.println(" minNode id : " + minNode.id);

		System.out.println(" minNode jarak : "
				+ LatLongUtil.distance(gpsLat, gpsLon,
						Double.parseDouble(minNode.lat),
						Double.parseDouble(minNode.lon)));

		return minNode;
	}

}
