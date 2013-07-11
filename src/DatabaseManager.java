/**
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
			statement.executeUpdate ( "create table swimmer (id INTEGER PRIMARY KEY, title TEXT, year DATE)" );

			// If the table "actors" already exists in the data base
			statement.executeUpdate ( "DROP TABLE IF EXISTS actors" );

			// Define the fields for the table "actors"
			statement.executeUpdate( "create table swimmer (id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT)" );

			// If the table "roles" already exists in the data base
			statement.executeUpdate( "DROP TABLE IF EXISTS roles" );

			// Define the fields for the table "roles"
			statement.executeUpdate( "create table swimmer (id INTEGER PRIMARY KEY, movie INTEGER FOREIGN KEY, " +
					"actor INTEGER FOREIGN KEY, role TEXT)" );

		} catch ( SQLException e ) {
			System.err.println ( e.getMessage ( ) );
			System.exit ( 1 );
		}
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
	
	public int getMovieID ( String title ) {
		
	}
	
	public int getActorID ( String lastname, String firstname ) {
		
	}	
	
	public int getRoleID ( String role ) {
		
	}
	
	public String getMovieName ( int id ) {
		
	}
	
	public String[] getActorName ( int id ) {
		
	}
	
	public String getRoleName ( int id ) {
		
	}
	
	public int getMovieID ( int role ) {
		
	}
	
	public int getActorID ( int role ) {
		
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


}