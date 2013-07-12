import java.util.ArrayList;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class BaconEdge {

	private BaconNode v1;
	private BaconNode v2;
	private ArrayList<String> movies;
	
	public BaconEdge ( BaconNode v1, BaconNode v2, String movie ) {
		movies = new ArrayList<String>();
				
		this.v1 = v1;
		this.v2 = v2;
		movies.add(movie);
	}

	public BaconNode getV1 ( ) {
		return v1;
	}

	public BaconNode getV2 ( ) {
		return v2;
	}

	public String getMovie ( int i ) {
		return movies.get(i);
	}
	
	public ArrayList<String> getMovies ( ) {
		return movies;
	}
	
	public int getWeight ( ) {
		return movies.size();
	}
	
	
	
	
	
}
