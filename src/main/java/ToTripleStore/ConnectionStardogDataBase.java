package ToTripleStore;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;

public class ConnectionStardogDataBase {

	private Connection ConnectionStardog;
	
	/**
	 * @param database
	 */
	public ConnectionStardogDataBase(String database){
	
	Connection aConn = ConnectionConfiguration
			.to(database)                      // the name of the db to connect to
			.server("http://localhost:5820")
			.credentials("admin", "admin")        // credentials to use while connecting
			.connect();
	this.ConnectionStardog=aConn;
	}
	
	public void createdatabase(String database) {
		
	}
	
	public Connection getConnection() {
		return ConnectionStardog;

}
}