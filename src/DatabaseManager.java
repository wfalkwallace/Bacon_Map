import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 */

/**
 * DatabaseManager manages a connection to a SQLite database (here for the Bacon Map database)
 * @author wgf2104
 * 
 */
public class DatabaseManager {

	private final int QUERY_TIMEOUT = 10;
	private final String DB_DRIVER_NAME = "org.sqlite.JDBC";
	private final String DB_CONNECTION_PREFIX = "jdbc:sqlite:";
	private final String DB_FILENAME = "BaconMap.db";


	private Connection connection;
	private Statement statement;

	/**
	 * Loads SQLite JDBC; performs initial setup for db interaction.
	 */
	public DatabaseManager ( ) {


		try {

			// load the sqlite-JDBC driver using the current class loader
			Class.forName ( DB_DRIVER_NAME );

			// create a database connection
			connection = DriverManager.getConnection ( DB_CONNECTION_PREFIX + DB_FILENAME );
			//prepare the shared statement for use later
			statement = connection.createStatement ( );
			statement.setQueryTimeout ( QUERY_TIMEOUT );  // set timeout to 10 sec.

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit(1);
		} catch ( ClassNotFoundException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
	}

	/**
	 * Initialize database and create tables. Format of db specified in readme
	 */
	public void configureDatabase ( ) {

		try {

			// If the table "movies" already exists in the data base
			statement.executeUpdate ( "DROP TABLE IF EXISTS movies" );

			// Define the fields for the table "movies"
			statement.executeUpdate ( "create table movies (id INTEGER PRIMARY KEY, title TEXT, year DATE)" );

			// If the table "actors" already exists in the data base
			statement.executeUpdate ( "DROP TABLE IF EXISTS actors" );

			// Define the fields for the table "actors"
			statement.executeUpdate( "create table actors (id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT)" );

			// If the table "roles" already exists in the data base
			statement.executeUpdate( "DROP TABLE IF EXISTS roles" );

			// Define the fields for the table "roles"
			statement.executeUpdate( "create table roles (id INTEGER PRIMARY KEY, movie INTEGER FOREIGN KEY, " +
					"actor INTEGER FOREIGN KEY, role TEXT)" );

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
	}

	/**
	 * Populates database and tables with IMDB Data from stored files
	 */
	public void populateDatabase ( ) {
		//TODO: plaintext parsing





	}

	/**
	 * Add an actor to the database
	 * @param lastname actor's last name
	 * @param firstname actor's first name
	 */
	public void addActor ( String lastname, String firstname ) {

		try {

			// Add the actor to the SQLite database
			statement.executeUpdate ( "INSERT INTO actors VALUES( NULL," + firstname + "," + lastname + ")" );

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
	}

	/**
	 * Add a movie to database
	 * @param title title of movie
	 * @param year production year of movie
	 */
	public void addMovie ( String title, int year ) {

		try {

			// Add the movie to the SQLite database
			statement.executeUpdate ( "INSERT INTO movies VALUES( NULL," + title + "," + year + ")" );

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
	}

	/**
	 * Add a role for an actor in a movie
	 * @param movie ID integer key for movie in which role was portrayed
	 * @param actor ID integer key for actor who portrayed role
	 * @param role title of character portrayed
	 */
	public void addRole ( int movie, int actor, String role ) {

		try {

			// Add the movie to the SQLite database
			statement.executeUpdate ( "INSERT INTO movies VALUES( NULL," + movie + "," + actor + "," + role + ")" );

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
	}

	/**
	 * Get movie table ID by movie title and year
	 * @param title movie title
	 * @return unique movie database ID
	 */
	public int getMovieID ( String title, int year ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT id FROM movies WHERE title = " + title + " AND year =" + year);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Get actor table ID by actor name
	 * @param name actor's name as string array [last, first]
	 * @return unique actor database ID
	 */
	public int getActorID ( String[] name ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT id FROM actors WHERE last_name = " + name[0] + " AND first_name =" + name[1]);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}	

	/**
	 * Get role table ID by role name
	 * @param role role title
	 * @return unique role database ID
	 */
	public int getRoleID ( String role ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT id FROM roles WHERE role = " + role);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Get movie name by movie table ID
	 * @param id movie database ID
	 * @return movie name string
	 */
	public String getMovieName ( int id ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT title FROM movies WHERE id = " + id);
			return rs.getString(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return "NULL";
		}
	}

	/**
	 * Get actor name by actor table ID
	 * @param id actor database ID
	 * @return actor name string array [last, first]
	 */
	public String[] getActorName ( int id ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT last_name, first_name FROM actors WHERE id = " + id);
			return new String[]{rs.getString(1), rs.getString(2)};

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return new String[]{"NULL","NULL"};
		}
	}

	/**
	 * Get role name by role table ID
	 * @param id role database ID
	 * @return role name string
	 */
	public String getRoleName ( int id ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT role FROM roles WHERE id = " + id);
			return rs.getString(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return "NULL";
		}
	}

	/**
	 * Get movie ID associated with given role ID
	 * @param id role database ID
	 * @return movie ID
	 */
	public int getMovieID ( int id ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT movie FROM roles WHERE id = " + id);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Get actor ID associated with given role ID
	 * @param id role database ID
	 * @return actor ID
	 */
	public int getActorID ( int id ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT actor FROM roles WHERE id = " + id);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Get movie ID associated with given role title
	 * @param role role title
	 * @return movie ID
	 */
	public int getMovieIDByRole ( String role ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT movie FROM roles WHERE role = " + role);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Get actor ID associated with given role title
	 * @param role role title
	 * @return actor ID
	 */
	public int getActorIDByRole ( String role ) {
		try {

			ResultSet rs = statement.executeQuery("SELECT actor FROM roles WHERE role = " + role);
			return rs.getInt(1);

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
			//never gets here; silly java
			return -1;
		}
	}

	/**
	 * Close the database after initialization
	 */
	public void closeDatabase ( ) {

		try {

			if( connection != null )
				connection.close ( );
			if( statement != null )
				statement.close ( );

		} catch ( SQLException e ) {
			// connection close failed.
			System.err.println ( e );
			System.exit ( 1 );
		}
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String[]> getActorNames ( ) {
		//result set
		ArrayList<String[]> actors = new ArrayList<String[]>();
		
		try {

			//get the actors
			ResultSet rs = statement.executeQuery("SELECT last_name, first_name FROM actors");
			while(rs.next())
				actors.add(new String[]{rs.getString(1), rs.getString(2)});
			
		} catch ( SQLException e ) {
			// connection close failed.
			System.err.println ( e );
			System.exit ( 1 );
		}
		
		return actors;
	}
	
	public ArrayList<String> getMoviesByActor ( String[] actor ) {
		
		
	}


}