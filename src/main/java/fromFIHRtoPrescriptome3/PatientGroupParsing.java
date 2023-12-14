package fromFIHRtoPrescriptome3;

import java.util.GregorianCalendar;
import java.util.HashMap;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

public class PatientGroupParsing {
	public prescriptomeCore.PatientGroup getPatientGroup(HashMap<String, Resource> resources){
		
		prescriptomeCore.PatientGroup patientGroup = new prescriptomeCore.PatientGroup() ;
		GregorianCalendar patGroupCreatetime = new GregorianCalendar(2022,06, 12);
		GregorianCalendar patGroupModifytime = new GregorianCalendar(2023,01, 05);
		Patient pat = (Patient) resources.get("Patient");
		String patientID = pat.getIdentifierFirstRep().getValue();
		
		patientGroup.setDatabaseSource( "MIMIC-IV-v2.2");
		patientGroup.setGroupID("2023-MIT-LCP"); 
		patientGroup.setCreatetime(patGroupCreatetime.getTime());
		patientGroup.setModifytime(patGroupModifytime.getTime());
		
		return patientGroup ;
	}
}
