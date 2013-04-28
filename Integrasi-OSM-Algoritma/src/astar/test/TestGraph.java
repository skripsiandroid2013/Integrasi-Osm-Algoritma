package astar.test;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import parsing.model.OSMNode;
import parsing.util.LatLongUtil;

import astar.graph.builder.GraphBuilder;
import astar.model.Graph;
import astar.model.Vertex;


public class TestGraph {
	
	@Test
	public  void testGraphBuilder() throws Exception {
		GraphBuilder builder = new GraphBuilder("data/surabaya.osm");
		Graph graph = builder.getGraph();
		assertNotNull("graph loaded !", graph);
		
		double lat = -7.24917;
		double lon = 112.751;
		int i=0;
		OSMNode hasilAkjhir=null;
		for (Vertex vertex : graph.getVertexs()){
			OSMNode node = vertex.getNode();
			double jarak=0;
			if(i!=0){
				if(jarak<LatLongUtil.distance(lat, lon, 
						Double.parseDouble(node.lat), Double.parseDouble(node.lon))){
					hasilAkjhir=node;
					jarak=LatLongUtil.distance(lat, lon, 
							Double.parseDouble(node.lat), Double.parseDouble(node.lon));
					System.out.println("x"+jarak);
				}
			}else{
				jarak=LatLongUtil.distance(lat, lon, 
						Double.parseDouble(node.lat), Double.parseDouble(node.lon));
				System.out.println("a"+jarak);
			}
			i++;
		}
	
		System.out.println(hasilAkjhir.id);
		
		
		
//		for (Vertex vertex : graph.getVertexs()){
//			System.out.println("\""+vertex.getNode().lat +"\""+",\""+ vertex.getNode().lon+"\"");
//		}

	}
}
