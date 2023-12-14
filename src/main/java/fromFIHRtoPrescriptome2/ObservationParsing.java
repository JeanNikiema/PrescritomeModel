/**
 * 
 */
package fromFIHRtoPrescriptome2;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Encounter;

/**
 * @author coulidaou
 *
 */
public class ObservationParsing {
	
	public org.hl7.fhir.r4.model.Observation getObservationFromFhirServer(IGenericClient client) {
		org.hl7.fhir.r4.model.Observation obs = client.read()
				.resource(org.hl7.fhir.r4.model.Observation.class)
				.withId("10552521")
				.execute();
		
		return obs ;
	}
	

	public Set<prescriptomeCore.Observation>   getObservationFromFHIR(org.hl7.fhir.r4.model.Observation Observation){

		Set<prescriptomeCore.Observation> result = new HashSet<>();
		Set<prescriptomeCore.Encounter> encounterDescription = new HashSet<>();

		String observationID =Observation.getId();
		observationID="10552511";
		String providerID = Observation.getPerformerFirstRep().getId();
		providerID="1144";
//		String encounterID =Observation.getEncounterTarget().getId();
		String encounterID ="25314";
		String patientID = Observation.getSubject().getReference();
		patientID="24739";
//		Date reportDate = Observation.getIssued();
		Date reportDate = Observation.getMeta().getLastUpdated();

		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;

		Date startDate = date;
		Date endDate = date;
		if(Observation.getEffectiveDateTimeType().getValue() != null) {
			startDate = Observation.getEffectiveDateTimeType().getValue();
			endDate = Observation.getEffectiveDateTimeType().getValue();
		}

		List<EncounterLocationComponent> encouterlocation = Observation.getEncounterTarget().getLocation();
		String codesystem=null;
		if(Observation.getCategoryFirstRep().getCodingFirstRep().getVersion() != null) {
			codesystem = Observation.getCategoryFirstRep().getCodingFirstRep().getSystem()+"/version/"
					+Observation.getCategoryFirstRep().getCodingFirstRep().getVersion();
		}else {
			codesystem = Observation.getCategoryFirstRep().getCodingFirstRep().getSystem()+"/version/"
					+"undef";
		}


		String codeSource =Observation.getCategoryFirstRep().getCodingFirstRep().getCode();
		String results = Observation.getCode().getCodingFirstRep().getCode();
		String resultsStr = Observation.getCode().getCodingFirstRep().getDisplay();
		int resultsInt = Integer.parseInt(results);

		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(codeSource, codesystem);
		IRI informationSourceTypeCode=ResourceParsing.getIRIforCodeAndSystem("", codesystem);
		IRI typeCode = Values.iri("http://umontreal.ca", results);
		IRI resultUNITsource = Values.iri("http://umontreal.ca", "");
		IRI resultUnitUCUM = Values.iri("http://umontreal.ca", "");
		IRI resultsSNOMED = Values.iri("http://umontreal.ca", "");

		int v=1;
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			facilityID="185";
			Encounter Encoun = new Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			encounterDescription.add(Encoun);
		}

		String facilityID =Observation.getEncounterTarget().getServiceProvider().getId() ;
		if(facilityID == null) {
			facilityID = "185";
		}
		Encounter Encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		encounterDescription.add(Encoun);

		for (Encounter encounter : encounterDescription) {
			prescriptomeCore.Observation obs = new prescriptomeCore.Observation(observationID, reportDate, encounter, typeCode,
					originaleTypeCode, informationSourceTypeCode, resultsStr,
					resultsSNOMED, resultsInt, resultUNITsource, resultUnitUCUM, startDate, endDate, validitytimeStay, createtimeStay, modifytimeStay);
			result.add(obs);
		}

		return result;

	}

}
