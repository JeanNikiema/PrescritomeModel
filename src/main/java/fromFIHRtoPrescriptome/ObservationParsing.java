/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

import prescriptomeCore.Encounter;

/**
 * @author coulidaou
 *
 */
public class ObservationParsing {
	
	/**
	 *
	 * @param Observation
	 * @return
	 */
	public static Set<prescriptomeCore.Observation>   getObservationFromFHIR(org.hl7.fhir.r4.model.Observation Observation){

		Set<prescriptomeCore.Observation> result = new HashSet<>();
		Set<prescriptomeCore.Encounter> encounterDescription = new HashSet<>();

		String observationID =Observation.getId();
		String providerID = Observation.getPerformerFirstRep().getId();
		String encounterID =Observation.getEncounterTarget().getId();
		String patientID = Observation.getSubjectTarget().getId();
		Date reportDate = Observation.getIssued();

		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date startDate = Observation.getEffectiveDateTimeType().getValue();
		Date endDate = Observation.getEffectiveDateTimeType().getValue();

		List<EncounterLocationComponent> encouterlocation = Observation.getEncounterTarget().getLocation();

		String codesystem = Observation.getCategoryFirstRep().getCodingFirstRep().getSystem()+"/version/"
				+Observation.getCategoryFirstRep().getCodingFirstRep().getVersion();

		String codeSource =Observation.getCategoryFirstRep().getCodingFirstRep().getCode();
		String results = Observation.getValue().toString();
		int resultsInt = Observation.getValueIntegerType().getValue();

		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(codeSource, codesystem);
		IRI informationSourceTypeCode=ResourceParsing.getIRIforCodeAndSystem("", codesystem);
		IRI typeCode = null;
		IRI resultUNITsource = null;
		IRI resultUnitUCUM = null;
		IRI resultsSNOMED = null;

		if(!Observation.getEffectivePeriod().isEmpty()) {
			startDate =Observation.getEffectivePeriod().getStart();
			endDate =Observation.getEffectivePeriod().getEnd();
		}

		if(!Observation.getEffectiveInstantType().isEmpty()) {
			startDate =Observation.getEffectiveInstantType().getValue();
			endDate =Observation.getEffectiveInstantType().getValue();
		}

		int v=1;
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			Encounter Encoun = new Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			encounterDescription.add(Encoun);
		}

		String facilityID =Observation.getEncounterTarget().getServiceProvider().getId() ;
		Encounter Encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		encounterDescription.add(Encoun);

		for (Encounter encounter : encounterDescription) {
			prescriptomeCore.Observation obs = new prescriptomeCore.Observation(observationID, reportDate, encounter, typeCode,
					originaleTypeCode, informationSourceTypeCode, results,
					resultsSNOMED, resultsInt, resultUNITsource, resultUnitUCUM, startDate, endDate, validitytimeStay, createtimeStay, modifytimeStay);
			result.add(obs);
		}

		return result;

	}

}
