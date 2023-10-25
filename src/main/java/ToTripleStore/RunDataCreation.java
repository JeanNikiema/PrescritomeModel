package ToTripleStore;

import java.io.IOException;
import java.net.URISyntaxException;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;
import com.complexible.stardog.api.Connection;

public class RunDataCreation {

	public static void main(String[] args) throws RDFHandlerException, UnsupportedRDFormatException, URISyntaxException, IOException {
		String graphname ="CDMH001";
		ConnectionStardogDataBase connec = new ConnectionStardogDataBase("db1");
		Connection conn = connec.getConnection();
//		dataModel modelFile = new dataModel(graphname);
//		conn.begin();
//		
//		conn.add().io().file(Paths.get("./CDMH.ttl"));
//		conn.commit();
//		conn.close();
}
}
