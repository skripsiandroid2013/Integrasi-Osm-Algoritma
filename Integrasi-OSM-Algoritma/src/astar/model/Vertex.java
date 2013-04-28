package astar.model;

import java.util.LinkedList;

import parsing.model.OSMNode;


//TODO add latlon
public class Vertex implements Comparable<Vertex> {
	
	public LinkedList<Edge> adjacencies = new LinkedList<Edge>();
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	private OSMNode node;

	
	public Vertex(OSMNode node) {
		this.setNode(node);
	}
	
	
	@Override
	public String toString() {
		return node.id;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public OSMNode getNode(String id) {
		if (this.node.id.equals(id)){
			return this.node;
		}
		return null;
	}
	
	public OSMNode getNode() {
		return node;
	}

	public void setNode(OSMNode node) {
		this.node = node;
	}

}
