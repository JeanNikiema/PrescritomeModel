package ToTripleStore;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;
import org.xml.sax.SAXException;

import com.complexible.stardog.api.Connection;

public class RunDataCreation {

	public static void main(String[] args) throws RDFHandlerException, UnsupportedRDFormatException, URISyntaxException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
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
