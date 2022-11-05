package ToTripleStore;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;

import com.complexible.stardog.api.Connection;

public class RunDataCreation {

	public static void main(String[] args) throws RDFHandlerException, UnsupportedRDFormatException, FileNotFoundException, URISyntaxException {
		// TODO Auto-generated method stub
		
		String graphname ="CDMH001";
		ConnectionStardogDataBase connec = new ConnectionStardogDataBase("RxINdatabase");
		Connection conn = connec.getConnection();
		dataModel modelFile = new dataModel(graphname);
		conn.begin();
		
		conn.add().io().file(Paths.get("./CDMH.ttl"));
		conn.commit();
		conn.close();

	}

}
