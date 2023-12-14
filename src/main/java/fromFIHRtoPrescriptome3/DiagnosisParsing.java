/**
 * 
 */
package fromFIHRtoPrescriptome3;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import org.hl7.fhir.r4.model.Claim.DiagnosisComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterParticipantComponent;

import prescriptomeCore.Diagnosis;


public class DiagnosisParsing {
	
	public Set<prescriptomeCore.Diagnosis> getDiagnosisFromFHIR(String diagnosisID, HashMap<String, Resource> resources){
		Set<prescriptomeCore.Diagnosis>  result = new HashSet<>();
		Encounter encount = (Encounter) resources.get("Encounter");
		
		String encounterID = "22434606";
		String providerID = "P56AFM";
		String patientID = encount.getSubject().getReference();
		patientID = "18173897";

		boolean presentAtEncounter = false;
		Date date = new Date();
		Date reportedDate = encount.getPeriod().getEnd();
		Date validitytime = date;
		Date createtime = date;
		Date modifytime= date;
		
//		List<org.hl7.fhir.r4.model.Encounter.DiagnosisComponent> diagnos = encount.getDiagnosis();
//		
//		if(!diagnos.isEmpty()) {
//			for(org.hl7.fhir.r4.model.Encounter.DiagnosisComponent diag : diagnos) {
//				String codediag = diag.getUse().getCodingFirstRep().getCode();
//				IRI diagnosisCode=Values.iri("http://umontreal.ca/diagnosisCode/", "5723");
//				codediag ="5723";
//				int version =9;
//				String system  =  "http://snomed.info/ct/version/"+version;
//
//				IRI originalDiagnosisCode =ResourceParsing.getIRIforCodeAndSystem(codediag, system);
//				int priorityAtDischarge =diag.getRank();
//				Diagnosis diagd = new Diagnosis(diagnosisID, originalDiagnosisCode, diagnosisCode, encounterID, providerID, patientID, reportedDate,
//						priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
//				result.add(diagd);
//			}
//		}
		
		String codediag = "5723" ;
		String system  =  "http://umontreal.ca/system/version/v1";
		IRI diagnosisCode=Values.iri("http://umontreal.ca/diagnosisCode/", "5723");
		IRI originalDiagnosisCode =ResourceParsing.getIRIforCodeAndSystem(codediag, system);
		int priorityAtDischarge =60;
		
		Diagnosis diagd = new Diagnosis(
				diagnosisID, originalDiagnosisCode, diagnosisCode, encounterID, providerID, patientID, reportedDate,
				priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
		
		result.add(diagd);

		return result;

	}
}
