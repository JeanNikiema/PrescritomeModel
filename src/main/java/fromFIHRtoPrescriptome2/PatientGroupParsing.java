package fromFIHRtoPrescriptome2;
import java.util.GregorianCalendar;
import org.hl7.fhir.r4.model.Patient;


public class PatientGroupParsing {
	public prescriptomeCore.PatientGroup getPatientGroup(String patientID ){
		prescriptomeCore.PatientGroup patientGroup = new prescriptomeCore.PatientGroup() ;
		GregorianCalendar patGroupCreatetime = new GregorianCalendar(2022,06, 12);
		GregorianCalendar patGroupModifytime = new GregorianCalendar(2023,01, 05);

		patientGroup.setDatabaseSource( "MIMIC-IV-v2.2");
		patientGroup.setGroupID("2023-MIT-LCP-PATGRP_"+patientID); // Identifiant du patient dans la base données
		patientGroup.setCreatetime(patGroupCreatetime.getTime());
		patientGroup.setModifytime(patGroupModifytime.getTime());
		return patientGroup ;
	}
}
