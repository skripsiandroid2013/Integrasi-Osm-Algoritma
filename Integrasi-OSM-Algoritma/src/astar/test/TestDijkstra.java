package astar.test;
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

public class TestDijkstra {

	@Test
	public void testAlgoritmaDijkstra() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");
		Graph graph = builder.getGraph();
	
		Dijkstra dijkstra = new Dijkstra(graph);
		
		String location[] = Geocode.request();
		System.out.println(location[0] + ", " + location[1]);
		
		OSMNode minNode = doMatching(graph.getVertexs(), 
				Double.parseDouble(location[0]), Double.parseDouble(location[1]));
	
		dijkstra.execute(graph.fromVertex(new Key("-7.315756","112.790314")));
		
	//	dijkstra.execute(graph.fromVertex(new Key(minNode.lat, minNode.lon)));
		
		LinkedList<Vertex> path = dijkstra.getPath(graph.toVertex(new Key("-7.3164693","112.7903005")));
		
		System.out.print("[");
		for (Vertex vertex : path) {
			System.out.print(vertex + ", ");
		}
		System.out.print("]");
		System.out.println();
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
