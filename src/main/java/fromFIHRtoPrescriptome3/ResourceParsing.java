package fromFIHRtoPrescriptome3;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;

/**
 *
 * @author Jean Nikiema
 */

public class ResourceParsing {

	public static IRI getIRIforCodeAndSystem(String code, String terminology) {
		String ex = "http://prescriptomeDataModel.ca/";
		String codeAndTerminology = terminology+"/"+code.trim();
		IRI codeIRI = Values.iri(ex, codeAndTerminology);
		return codeIRI;
	}

}
