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

public class TestDijkstra {

	@Test
	public void testAlgoritmaDijkstra() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");
		System.err.println("+ GraphBuilder.getGraph()");
		Graph graph = builder.getGraph();
	
		Dijkstra dijkstra = new Dijkstra(graph);
		
//		String location[] = Geocode.request();
//		System.out.println(location[0] + ", " + location[1]);
//		
//		OSMNode minNode = doMatching(graph.getVertexs(), 
//				Double.parseDouble(location[0]), Double.parseDouble(location[1]));
	
		Vertex src = graph.fromVertex(new Key("-7.3271793","112.7791673"));
		
		System.err.println("+ Graph.execute()");
		dijkstra.execute(src);
		
		System.out.println("Src vertex : "+src);
		
	//	dijkstra.execute(graph.fromVertex(new Key(minNode.lat, minNode.lon)));
		Vertex dst = graph.toVertex(new Key("-7.2607994","112.7668585"));
		System.out.println("Dst vertex : "+dst);
		
		LinkedList<Vertex> path = dijkstra.getPath(dst);
		
		    
		    for (Vertex vertex : path) {
		      System.out.print("path : "+vertex +" --> ");
		    }
//	
	
	
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
