package dijkstra.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dijkstra.model.Edge;
import dijkstra.model.Graph;
import dijkstra.model.Vertex;



public class DijkstraAlgorithm {
	@SuppressWarnings("unused")
	private final List<Vertex> listNodes;
	private final List<Edge> listEdges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> MapPredecessors;
	private Map<Vertex, Integer> MapDistance;

	public DijkstraAlgorithm(Graph graph) {
		// Create a copy of the array so that we can operate on this array
		this.listNodes = new ArrayList<Vertex>(graph.getVertexes());
		this.listEdges = new ArrayList<Edge>(graph.getEdges());
		settledNodes = new HashSet<Vertex>();
		unSettledNodes = new HashSet<Vertex>();
		MapDistance = new HashMap<Vertex, Integer>();
		MapPredecessors = new HashMap<Vertex, Vertex>();
	}

	public void execute(Vertex awal) {
		MapDistance.put(awal, 0);
		unSettledNodes.add(awal);
		while (unSettledNodes.size() > 0) {
			//System.out.println("di dalam exec()="+unSettledNodes.size());
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > (getShortestDistance(node) + getDistance(node, target))) {
				MapDistance.put(target, getShortestDistance(node) + getDistance(node, target));
				MapPredecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}
	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : listEdges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : listEdges) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum))
				{
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = MapDistance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// Check if a path exists
		if (MapPredecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (MapPredecessors.get(step) != null) {
			step = MapPredecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

} 