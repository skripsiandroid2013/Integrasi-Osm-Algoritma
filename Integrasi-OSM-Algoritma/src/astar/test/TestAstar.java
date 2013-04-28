package astar.test;

import geocoding.engine.Geocode;

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

		String [] location = Geocode.request();
		System.out.println(location[0]+", "+location[1]);
		
		
		//Match
		int i=0;
		OSMNode hasilAkjhir=null;
		for (Vertex vertex : graph.getVertexs()){
			OSMNode node = vertex.getNode();
			double jarak=0;
			if(i!=0){
				if(jarak<LatLongUtil.distance(Double.parseDouble(location[0]), Double.parseDouble(location[1]), 
						Double.parseDouble(node.lat), Double.parseDouble(node.lon))){
					hasilAkjhir=node;
					jarak=LatLongUtil.distance(Double.parseDouble(location[0]), Double.parseDouble(location[1]), 
							Double.parseDouble(node.lat), Double.parseDouble(node.lon));
				//	System.out.println("x"+jarak);
				}
			}else{
				jarak=LatLongUtil.distance(Double.parseDouble(location[0]), Double.parseDouble(location[1]), 
						Double.parseDouble(node.lat), Double.parseDouble(node.lon));
			//	System.out.println("a"+jarak);
			}
			i++;
		}
		
		//
		
		
		Vertex start = graph.fromVertex(new Key(hasilAkjhir.lat, hasilAkjhir.lon));
		
		Vertex goal = graph.toVertex(new Key("-7.3164693","112.7903005"));
		
		aStar.execute(start, goal);

		LinkedList<Vertex> path = aStar.getPath();
//		System.out.print("[");
//		for (Vertex vertex : path) {
//			System.out.print(vertex+ ", ");
//		}
//		System.out.print("]");
//		System.out.println();
		
		for (Vertex vertex : path) {
		System.out.println(vertex.getNode().lat+ ","+vertex.getNode().lon);
	}
	}

}
