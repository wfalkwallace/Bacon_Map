import java.util.HashMap;

/**
 * 
 */

/**
 * Actor-based node for bacon map graph
 * @author wgf2104
 *
 */
public class BaconNode {
	
	//name = [last name, first name]
	private String[] name = new String[2];
	//roles = [movie => role]
	//effectively: edgelist labels/key
	private HashMap<String, String> roles = new HashMap<String, String>();
	
	/**
	 * Initialize an actor node
	 * @param name actor name array [last, first]
	 */
	public BaconNode ( String[] name ) {
		this.name = name;
	}
	
	/**
	 * Get actor's name
	 * @return actor name array [last, first]
	 */
	public String[] getName ( ) {
		return name;
	}

	/**
	 * Get hashmap representing roles/films
	 * @return roles HashMap<String, String> maps movie title to role
	 */
	public HashMap<String, String> getRoles ( ) {
		return roles;
	}

	/**
	 * Add a role for this actor/node
	 * @param movie title of movie
	 * @param role part played in specified movie
	 */
	public void addRole ( String movie, String role ) {
		roles.put(movie, role);
	}
	
	
}
