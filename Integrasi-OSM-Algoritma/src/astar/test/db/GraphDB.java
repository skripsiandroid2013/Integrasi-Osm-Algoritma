package astar.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collection;

import parsing.model.OSMNode;

import astar.graph.builder.GraphBuilder;
import astar.model.Edge;
import astar.model.Graph;
import astar.model.Vertex;

public class GraphDB {

	public static void main(String[] args) throws Exception {
		Connection c = null;
		Statement stmt = null;

		GraphBuilder builder = new GraphBuilder("data/surabaya_new.osm");
		Graph graph = builder.getGraph();


		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:data/graph.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			// INSERT VERTEX
			int n = 0;
			for (Vertex v : graph.getVertexs()) {
				OSMNode node = v.getNode();

				String sql = "INSERT INTO VERTEX (id, osm_id, lat, lon) "
						+ "VALUES (" + ++n + ", " + node.id + ", " + node.lat
						+ ", " + node.lon + ");";
				stmt.executeUpdate(sql);

			}
			
			// INSERT EDGE
			int m =0;
			for(Edge e : graph.getEdges()){
				String sql = "INSERT INTO EDGE (ID, WAY_ID, FROM_VERTEX, TO_VERTEX, JARAK)" +
						" VALUES (" + ++m + 
						"," + e.getId() + 
						"," + e.getFromVertex().getNode().id + 
						"," + e.getToVertex().getNode().id + 
						"," + e.getJarak() +" );";
				stmt.executeUpdate(sql);
				
			}
			
			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		 System.out.println("Records created successfully");
		 
	}

}
