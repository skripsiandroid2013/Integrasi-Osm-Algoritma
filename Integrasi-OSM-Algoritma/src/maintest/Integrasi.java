package maintest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dijkstra.engine.DijkstraAlgorithm;
import dijkstra.model.Edge;
import dijkstra.model.Graph;
import dijkstra.model.Vertex;

import parsing.engineparsing.OSMParser;
import parsing.model.OSM;
import parsing.model.OSMNode;
import parsing.model.Way;
import parsing.util.LatLongUtil;

public class Integrasi {

	private static ArrayList<Vertex> vertexs;
	private static ArrayList<Edge> edges;
	private Set<OSMNode> allTempat;
	private OSM osm;
	private Set<Way> allJalan;

	// fix graph
	// coba build graph'e pake GraphBuilder
	// file osm tak pindah nang folder data
	public void bentukGraph() throws Exception {
		
		osm = OSMParser.parse("data/surabaya.osm");
		allTempat=osm.getNodes();
		allJalan=osm.getWays();

		vertexs = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		int banyakTempat=0;
		for (OSMNode tempat : allTempat) {
			Vertex lokasi = new Vertex(tempat.getId(), Integer.toString(banyakTempat));
			vertexs.add(lokasi);
			banyakTempat++;
		}
		for (Way jalan : allJalan){
			prosesSatu(jalan.getNodes());
		}
		for (Vertex v : vertexs){
			System.out.println("ID : "+v.getId()+" Nama : "+v.getName());
		}
		for (Edge e : edges){
			System.out.println("ID : "+e.getId()+" Jarak : "+e.getWeight());
		}
		
		Graph graph = new Graph(vertexs, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(vertexs.get(41));
		//LinkedList<Vertex> path = dijkstra.getPath(vertexs.get(4)); //cari id di value
		//LinkedList<Vertex> path = dijkstra.getPath(vertexs.get(4)); //cari id di value
		LinkedList<Vertex> path = dijkstra.getPath(vertexs.get(23));   //cari id di value
		if(path==null){
			System.out.println("Lintasan tidak terhubung");
		}else{
			System.out.println("Hasil Lintasan Terpendek : ");
			for (Vertex vertex : path) {
				System.out.print("->"+vertex.getId()+"");
			}
		}
		

	}
	

	private void prosesSatu(List<OSMNode> nodesJalan) {
		// TODO Auto-generated method stub
		//System.out.println("Banyak node pada jalan : "+nodesJalan.size());
		for (int i=0;i<nodesJalan.size();i++){
			if((i+1)<nodesJalan.size()){
				OSMNode titik1 = nodesJalan.get(i);
				OSMNode titik2 = nodesJalan.get(i+1);
				Vertex a=sinkronisasi(titik1.getId());
				Vertex b=sinkronisasi(titik2.getId());
				if((a!=null)&&(b!=null)){
					addJarakAntarNode(a.getId()+"->"+b.getId(), 
							Integer.parseInt(a.getName()), 
							Integer.parseInt(b.getName()),
							kalkulasiPanjang(titik1,titik2));
				}

			}
		}
	}
	private int kalkulasiPanjang(OSMNode n1, OSMNode n2) {
		// TODO Auto-generated method stub
		double length = LatLongUtil.distance(Double.parseDouble(n1.lat), Double.parseDouble(n1.lon), 
				Double.parseDouble(n2.lat), Double.parseDouble(n2.lon));
		return (int) length;
	}

	private Vertex sinkronisasi(String id) {
		// TODO Auto-generated method stub
		for (Vertex v : vertexs){
			if(v.getId().equals(id)){
				return v;
			}
		}
		return null;
	}
	private void addJarakAntarNode(String laneId, int sourceLocNo, int destLocNo,int duration) {
		Edge lane = new Edge(laneId,vertexs.get(sourceLocNo), vertexs.get(destLocNo), duration);
		edges.add(lane);
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Integrasi().bentukGraph();
	}

}
