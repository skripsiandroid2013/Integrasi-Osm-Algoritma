package astar.model;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import parsing.model.OSMNode;


public class Graph {
	
	private HashMap<String, Vertex> vertices = new HashMap<>();
		
	public void addEdge(OSMNode fromNode, OSMNode toNode, double jarak, String edgeId)
	{
		Vertex fromVertex = vertices.get(fromNode.id);
		if (fromVertex == null) {
			fromVertex = new Vertex(fromNode);
			vertices.put(fromNode.id, fromVertex);
		}
		Vertex toVertex = vertices.get(toNode.id);
		if (toVertex == null) {
			toVertex = new Vertex(toNode);
			vertices.put(toNode.id, toVertex);
		}
		fromVertex.adjacencies.add(new Edge(fromVertex, toVertex, jarak, edgeId));
	}
	
	public int getSize()
	{
		return vertices.size();
	}
	
	public Collection<Vertex> getVertexs(){
		return vertices.values();
	}
	
	public Collection<Edge> getEdges() {
		List<Edge> edges = new LinkedList<>();
		for(Vertex vertex : vertices.values()){
			for (Edge edge : vertex.adjacencies){
				edges.add(edge);
			}
		}
		return edges;
	}

	public Vertex fromVertex(String id) {
		
		return vertices.get(id);
	}

	public Vertex toVertex(String id) {
		return vertices.get(id);
	}
	
	public Vertex toVertex(String lat, String lon) {
		String id ="";
		return vertices.get(id);
	}
}
