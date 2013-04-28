package maintest;

import java.util.Set;

import parsing.engineparsing.*;
import parsing.model.*;

public class ParserTest {
	// fix graph
	// coba build graph'e pake GraphBuilder
	// file osm tak pindah nang folder data
	public static void main(String[] args) throws Exception {
		// String left = "16.35428";
		// String bottom = "48.20161";
		// String right = "16.36806";
		// String top = "48.20747";
		// String url = " http://api.openstreetmap.org/api/0.6/map?bbox=" + left
		// + "," + bottom + "," + right
		// + "," + top;
		// System.out.println(url);
		// OSM osm = OSMParser.parse(url);

		OSM osm = OSMParser.parse("data/surabaya.osm");
		Set<OSMNode> allTempat = osm.getNodes();
		Set<Way> allJalan = osm.getWays();
		Set<Relation> allRelasi = osm.getRelations();
		System.out.println("Jumlah tempat = " + allTempat.size());
		System.out.println("Jumlah jalan = " + allJalan.size());
		System.out.println("Jumlah relasi = " + allRelasi.size());
		for (OSMNode tempat : allTempat) {
			System.out.println("Lokasi : " + tempat.id);
			System.out.println(tempat.getPoint());
		}

		for (Way jalan : allJalan) {
			if (jalan.getName().equals("Jl.SatuArah")) {
				System.out.println("Nama Jalan : " + jalan.getName());
				System.out.println("Apa jalan Besar : " + jalan.isHighway());

				// fix jalan satu arah
				System.out.println("Apa satu arah : "
						+ jalan.getOnewayDirection());
				System.out.println("panjang : " + jalan.getLineString());
				System.out.println("panjang : " + jalan.getWayLength());
				for (OSMNode titik : jalan.getNodes()) {
					System.out.println(titik.lat + " dan " + titik.lon);
				}
			}
		}

		// for (Relation relasi : allRelasi){
		// System.out.println(relasi.getName());
		// }
		//

		// for (Way way : osm.getWays()) {
		// //System.out.println(way.getLineString());
		// System.out.println("Nama Jalan : "+way.getName());
		// System.out.println("Panjang Jalan : "+way.getWayLength());
		// System.out.println(way.getName());
		// }
		//
		// System.out.println(osm.getWays().size());
		// for (OSMNode tempat : osm.getNodes()){
		//
		// System.out.println(tempat.getLocation());
		//
		// }
		// String asal = "1379499958";
		// int i=0;
		// for (OSMNode node : osm.getNodes()) {
		// System.out.print("<"+i+">   ");
		// System.out.println(node.getLocation());
		// System.out.println(node.getPoint());
		// System.out.println(node.lat+" dan "+node.lon);
		//
		// i++;
		// }
	}
}
