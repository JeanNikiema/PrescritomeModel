package ToTripleStore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.ConnectionPool;
import com.complexible.stardog.api.ConnectionPoolConfig;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.io.RDFFormats;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultWriters;

public class ConnectToStardog {
	
	private static final String url = "http://localhost:5820";
	private static final String username = "admin";
	private static final String password = "admin";
	private static final String to = "db1";

	
	/**
	*  Creates a connection to the DBMS itself so we can perform some administrative actions.
	*/
	public static void createAdminConnection() {
	    try (AdminConnection aConn = AdminConnectionConfiguration
	    		.toServer(url)
	            .credentials(username, password)
	            .connect()) {

	        // A look at what databases are currently in Stardog
	        aConn.list().forEach(item -> System.out.println(item));

	        // Checks to see if the 'testDB' is in Stardog. If it is, we are
	        // going to drop it so we are starting fresh
	        if (aConn.list().contains(to)) {aConn.drop(to);}
	        // Convenience function for creating a persistent
	        // database with all the default settings.
	        aConn.disk(to).create();
	    }
	}
	
	
	private static int maxPool = 200;
	private static int minPool = 10;
	private static boolean reasoningType = false;
	private static long blockCapacityTime = 900;
	private static TimeUnit blockCapacityTimeUnit = TimeUnit.SECONDS;
	private static long expirationTime = 300;
	private static TimeUnit expirationTimeUnit = TimeUnit.SECONDS;
	

	/**
	  *  Now we want to create the configuration for our pool.
	  * @param connectionConfig the configuration for the connection pool
	  * @return the newly created pool which we will use to get our Connections
	  */
	private static ConnectionPool createConnectionPool(ConnectionConfiguration connectionConfig) {
	    ConnectionPoolConfig poolConfig = ConnectionPoolConfig
	            .using(connectionConfig)
	            .minPool(minPool)
	            .maxPool(maxPool)
	            .expiration(expirationTime, expirationTimeUnit)
	            .blockAtCapacity(blockCapacityTime, blockCapacityTimeUnit);

	    return poolConfig.create();
	}

	
	/**
	  * Obtains the Stardog connection from the connection pool
	  * @param connectionPool the connection pool to get our connection
	  * @return Stardog Connection
	  */
	public static Connection getConnection(ConnectionPool connectionPool) {
	    return connectionPool.obtain();
	}

	
	/**
	  * Releases the Stardog connection from the connection pool
	  * @param connection Stardog Connection
	  */
	public static void releaseConnection(ConnectionPool connectionPool, Connection connection) {
	    try {
	        connectionPool.release(connection);
	    } catch (StardogException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public static void main(String[] args) {
		createAdminConnection();       
		ConnectionConfiguration connectionConfig = ConnectionConfiguration
		        .to(to)
		        .server(url)
		        .reasoning(reasoningType)
		        .credentials(username, password);

		// creates the Stardog connection pool
		ConnectionPool connectionPool = createConnectionPool(connectionConfig);

		try (Connection connection = getConnection(connectionPool)) {
		      // first start a transaction. This will generate the contents of
		      // the database from the N3 file.
		      connection.begin();

		      // declare the transaction
		      connection.add().io().format(RDFFormats.N3).stream(new FileInputStream("marvel.rdf"));

		      // and commit the change
		      connection.commit();

		      // Query the database to get our list of Marvel superheroes and print the results to the console
		      SelectQuery query = connection.select("PREFIX foaf:<http://xmlns.com/foaf/0.1/> select * { ?s rdf:type foaf:Person }");
		      SelectQueryResult tupleQueryResult = query.execute();
		      QueryResultWriters.write(tupleQueryResult, System.out, TextTableQueryResultWriter.FORMAT);
		      
		} catch (StardogException | IOException e) {
		    e.printStackTrace();
		} finally {
		    connectionPool.shutdown();
		}
	}
}
