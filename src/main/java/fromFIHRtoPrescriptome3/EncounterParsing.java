package fromFIHRtoPrescriptome3;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

public class EncounterParsing {
	public Set<prescriptomeCore.Encounter> getEncounterFromResourceFile(String encounterID, HashMap<String, Resource> resources){
		Encounter fhirEnc = (Encounter) resources.get("Encounter");
		
		Set<prescriptomeCore.Encounter> result = new HashSet<>();
		String providerID = "P56AFM";
		String patientID = "18173897";
		
		Date createtimeStay = fhirEnc.getPeriod().getStart();
		Date modifytimeStay = fhirEnc.getPeriod().getEnd();
		Date validitytimeStay = fhirEnc.getPeriod().getEnd();
		
		String facilityID =fhirEnc.getServiceProvider().getId() ;
		facilityID = "18173897-452";
		
		prescriptomeCore.Encounter Encoun = new prescriptomeCore.Encounter(
				encounterID, 
				providerID, 
				patientID, 
				facilityID, 
				validitytimeStay, 
				createtimeStay, 
				modifytimeStay
		);
		
		result.add(Encoun);
		return result;
	}
}
