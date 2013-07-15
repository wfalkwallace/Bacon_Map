/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class ShowMeTheBacon {

	private static DatabaseManager dbm;
	private static BaconGraph graph;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//DB Junk
		dbm = new DatabaseManager();
		dbm.configureDatabase();
		dbm.populateDatabase();
		//Graph Junk
		graph = new BaconGraph();


	}


	public void baconize() {
		//add the vertices
		for(String[] actor : dbm.getActorNames()){
			graph.addVertex(new BaconNode(actor));
			//add the edges
			for(String movie : dbm.getMoviesByActor(actor)){
				for(String[] otherActor : dbm.getActorsByMovie(movie, 0000)){
					graph.addEdge(new BaconNode(actor), new BaconNode(otherActor), movie);
				}
			}

		}
	}




}
