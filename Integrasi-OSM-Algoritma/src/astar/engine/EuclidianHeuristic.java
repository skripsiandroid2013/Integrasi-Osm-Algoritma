package astar.engine;

import parsing.model.OSMNode;
import astar.model.Vertex;


public class EuclidianHeuristic implements AStarHeuristic {

	@Override
	public double getNilaiHeuristic(Vertex source, Vertex goal) {
		OSMNode start = source.getNode();
		OSMNode finish = source.getNode();
	
		double x = start.getPoint().getX() - finish.getPoint().getX();
		double y = start.getPoint().getY() - finish.getPoint().getY();
		
		return Math.sqrt(x*x + y*y);
	}

}
