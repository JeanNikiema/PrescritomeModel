/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

import prescriptomeCore.Encounter;

/**
 * @author coulidaou
 *
 */
public class EncounterParsing {

	/**
	 * @param Encounter
	 * @return
	 */
	public static Set<prescriptomeCore.Encounter>   getEncounterFromFHIR(org.hl7.fhir.r4.model.Encounter Encounter){
		Set<prescriptomeCore.Encounter> result = new HashSet<>();
		
		String encounterID =Encounter.getId();
		String providerID = Encounter.getParticipantFirstRep().getId();
		String patientID = Encounter.getSubjectTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		
		List<EncounterLocationComponent> encouterlocation= Encounter.getLocation();
		int v=1;
		
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			Encounter Encoun = new Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			result.add(Encoun);
		}
		
		String facilityID =Encounter.getServiceProvider().getId() ;
		Encounter Encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		result.add(Encoun);
		return result;

	}
}
