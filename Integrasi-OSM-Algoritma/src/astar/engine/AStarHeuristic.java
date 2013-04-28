package astar.engine;


import astar.model.Vertex;

public interface AStarHeuristic {

	public double getNilaiHeuristic(Vertex source, Vertex goal);
}
