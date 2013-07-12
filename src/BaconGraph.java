import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class BaconGraph {
	
	//hashmap from movie title to array of actors
	private Hashtable<BaconNode, ArrayList<BaconEdge>> graph;
	
	/**
	 * Initalize a Bacon Degrees Graph with a given depth
	 * @param degrees depth of representation
	 */
	public BaconGraph ( ) {
		graph = new Hashtable<BaconNode, ArrayList<BaconEdge>>();
	}
	
	/**
	 * Add an actor node to the graph
	 * @param v vertex to be added
	 */
	public void addVertex ( BaconNode v ) {
		graph.put(v, new ArrayList<BaconEdge>());
	}
	
	/**
	 * Add edge between two vertices on the graph
	 * @param v1 first vertex of edge
	 * @param v2 second vertex of edge
	 * @param movie edge label
	 */
	public void addEdge ( BaconNode v1, BaconNode v2, String movie) {
		if(!graph.containsKey(v1))
			addVertex(v1);
		if(!graph.containsKey(v2))
			addVertex(v2);
		graph.get(v1).add(new BaconEdge(v1, v2, movie));
		graph.get(v2).add(new BaconEdge(v1, v2, movie));
	}
	
	/**
	 * Get Edges for a given vertex
	 * @param v vertex to analyze
	 * @return arraylist of associated edges
	 */
	public ArrayList<BaconEdge> getEdges ( BaconNode v ) {
		return graph.get(v);
	}
	
	/**
	 * Get size of map
	 * @return number of vertices
	 */
	public int getSize ( ) {
		return graph.size();
	}
	
	/**
	 * Get local edge density - since graph is symmetric, incount == outcount.
	 * @param v vertex to analyze
	 * @return size of edgelist
	 */
	public int getOutCount ( BaconNode v ) {
		return graph.get(v).size();
	}
	
	/**
	 * Get graph node by actor name array
	 * @param actor String array [last, first]
	 * @return bacon node representing actor
	 */
	public BaconNode getNode ( String[] actor ) {
		for(BaconNode v:graph.keySet())
			if(v.getName()[0] == actor[0] && v.getName()[1] == actor[1])
				return v;
		return null;
	}
	
	
}
