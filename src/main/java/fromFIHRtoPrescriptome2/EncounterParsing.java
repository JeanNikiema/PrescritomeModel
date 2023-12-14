/**
 * 
 */
package fromFIHRtoPrescriptome2;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Encounter;

/**
 * @author coulidaou
 *
 */
public class EncounterParsing {
	public org.hl7.fhir.r4.model.Encounter getEncounterFromFhirServer(IGenericClient client) {
		org.hl7.fhir.r4.model.Encounter  enc = client.read()
				.resource(org.hl7.fhir.r4.model.Encounter .class)
				.withId("25314")
				.execute();
		
		return enc ;
	}
	
	/**
	 * @param Encounter
	 * @return
	 */
	public static Set<prescriptomeCore.Encounter>   getEncounterFromFHIR(org.hl7.fhir.r4.model.Encounter encounter){
		Set<prescriptomeCore.Encounter> result = new HashSet<>();
		
		String encounterID =encounter.getIdentifierFirstRep().getValue(); // 12345777
		encounterID = "25314";
		String providerID = encounter.getParticipantFirstRep().getIndividual().getReference(); //  "1472"
		String patientID = encounter.getSubject().getReference(); // 24739
		
	    providerID = providerID.split("/")[1];
	    patientID = patientID.split("/")[1];

		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		
		List<EncounterLocationComponent> encouterlocation= encounter.getLocation();
		int v=1;
		
		for(EncounterLocationComponent comp :  encouterlocation) {
			String locationID = comp.getLocation().getReference() ; // 25313
			locationID = locationID.split("/")[1];
			Encounter Encoun = new Encounter(encounterID+"_"+v, providerID, patientID, locationID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			result.add(Encoun);
		}
		
		String facilityID = encounter.getServiceProvider().getReference() ; // 185
	    facilityID = facilityID.split("/")[1];

		Encounter encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		result.add(encoun);
		return result;

	}
}
