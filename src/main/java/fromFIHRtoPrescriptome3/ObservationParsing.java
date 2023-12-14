package fromFIHRtoPrescriptome3;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

import fromFIHRtoPrescriptome2.ResourceParsing;

public class ObservationParsing {
	public Set<prescriptomeCore.Observation> getObservationFromResource(HashMap<String, Resource> resources) {
		Observation ob = (Observation) resources.get("Observation");
		Set<prescriptomeCore.Observation> result = new HashSet<>();
		Set<prescriptomeCore.Encounter> encounterDescription = new HashSet<>();

		String observationID =ob.getIdentifierFirstRep().getValue();
		String providerID = ob.getPerformerFirstRep().getId();
		providerID="P56AFM";
//		String encounterID =Observation.getEncounterTarget().getId();
		String encounterID ="22434606";
		String patientID = ob.getSubject().getReference();
		
		Date date = new Date();
		
//		Date reportDate = ob.getMeta().getLastUpdated();
		Date reportDate = date;

		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;

		Date startDate = date;
		Date endDate = date;
		if(ob.getEffectiveDateTimeType().getValue() != null) {
			startDate = ob.getEffectiveDateTimeType().getValue();
			endDate = ob.getEffectiveDateTimeType().getValue();
		}

		List<EncounterLocationComponent> encouterlocation = ob.getEncounterTarget().getLocation();
		String codesystem = ob.getIdentifierFirstRep().getSystem();
		
		String codeSource =ob.getIdentifierFirstRep().getType().getCodingFirstRep().getCode();
		String results = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getCode();
		String resultsStr = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getDisplay();
		int resultsInt = 0;

		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(codeSource, codesystem);
		IRI informationSourceTypeCode=ResourceParsing.getIRIforCodeAndSystem("", codesystem);
		IRI typeCode = Values.iri("http://umontreal.ca/typeCode/", results);
		IRI resultUNITsource = Values.iri("http://umontreal.ca/resultUNITsource/", "");
		IRI resultUnitUCUM = Values.iri("http://umontreal.ca/resultUnitUCUM/", "");
		IRI resultsSNOMED = Values.iri("http://umontreal.ca/resultsSNOMED/", "");

		int v=1;
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			facilityID="18173897-452";
			prescriptomeCore.Encounter Encoun = new prescriptomeCore.Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			encounterDescription.add(Encoun);
		}

		String facilityID =ob.getEncounterTarget().getServiceProvider().getId() ;
		if(facilityID == null) {
			facilityID = "18173897-452";
		}
		prescriptomeCore.Encounter encoun = new prescriptomeCore.Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		encounterDescription.add(encoun);

		for (prescriptomeCore.Encounter encounter : encounterDescription) {
			prescriptomeCore.Observation observation = new prescriptomeCore.Observation(
					observationID, reportDate, encounter, typeCode,
					originaleTypeCode, informationSourceTypeCode, resultsStr,
					resultsSNOMED, resultsInt, resultUNITsource, resultUnitUCUM, 
					startDate, endDate, validitytimeStay, createtimeStay, modifytimeStay
			);
			
			result.add(observation);
		}

		return result;

	}
}
