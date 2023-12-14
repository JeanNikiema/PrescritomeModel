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
import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Resource;

import org.hl7.fhir.r4.model.Claim.DiagnosisComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterParticipantComponent;

import prescriptomeCore.Diagnosis;


public class DiagnosisParsing {
	
	public static Set<prescriptomeCore.Diagnosis> getDiagnosisFromFHIR(String diagnosisID, Claim FHIRclaim){
		Set<prescriptomeCore.Diagnosis> setDiag = new HashSet<>();
		String encounterID = FHIRclaim.getFacilityTarget().getId();
		String providerID = FHIRclaim.getCareTeamFirstRep().getProviderTarget().getId();


		String patientID = FHIRclaim.getPatientTarget().getId();
		Date reportedDate = FHIRclaim.getCreated();
		List<DiagnosisComponent> getDiagnosisList  = FHIRclaim.getDiagnosis();

		for(DiagnosisComponent a : getDiagnosisList) {
			int priorityAtDischarge = a.getSequence();
			String DiagnosisID=diagnosisID+a.getSequence();
			String code = a.getDiagnosisCodeableConcept().getCodingFirstRep().getCode();
			String system = a.getDiagnosisCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
					+a.getDiagnosisCodeableConcept().getCodingFirstRep().getVersion();
			IRI originalDiagnosisCode=ResourceParsing.getIRIforCodeAndSystem(code, system);
			boolean presentAtEncounter= false;
			if (a.getOnAdmission().equals("y")) {
				presentAtEncounter= true;
			}
			Date validitytime = FHIRclaim.getBillablePeriod().getEnd();
			Date createtime = FHIRclaim.getBillablePeriod().getStart();
			Date modifytime= FHIRclaim.getBillablePeriod().getStart();
			Diagnosis diag = new Diagnosis(DiagnosisID, originalDiagnosisCode, null, encounterID, providerID, patientID, reportedDate,
					priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
			setDiag.add(diag);
		}

		return setDiag;

	}


	/**
	 * @param diagnosisID
	 * @param encount
	 * @return
	 */
	public Set<prescriptomeCore.Diagnosis> getDiagnosisFromFHIR(String diagnosisID, org.hl7.fhir.r4.model.Encounter encount){
		Set<prescriptomeCore.Diagnosis>  result = new HashSet<>();
		String DiagnosisID=diagnosisID;
		String encounterID = encount.getId();
		encounterID = "25314";
		String providerID = "1144";
		String patientID = encount.getSubject().getReference();
//		patientID = patientID.split("/")[1];
		patientID = "24739";
//		for (EncounterParticipantComponent a : encount.getParticipant()) {
//			providerID = a.getId();
//		}
		boolean presentAtEncounter = false;
		Date date = new Date();
		Date reportedDate = encount.getPeriod().getEnd();
		Date validitytime = date;
		Date createtime = date;
		Date modifytime= date;
		
		List<org.hl7.fhir.r4.model.Encounter.DiagnosisComponent> diagnos = encount.getDiagnosis();
		
		if(!diagnos.isEmpty()) {
			for(org.hl7.fhir.r4.model.Encounter.DiagnosisComponent diag : diagnos) {
				String codediag = diag.getUse().getCodingFirstRep().getCode();
				String system  =  diag.getUse().getCodingFirstRep().getSystem()+"/version/"+diag.getUse().getCodingFirstRep().getVersion();

				IRI originalDiagnosisCode =ResourceParsing.getIRIforCodeAndSystem(codediag, system);
				int priorityAtDischarge =diag.getRank();
				Diagnosis diagd = new Diagnosis(DiagnosisID, originalDiagnosisCode, null, encounterID, providerID, patientID, reportedDate,
						priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
				result.add(diagd);
			}
		}
		
		String codediag = "73211009" ;
		String system  =  "http://snomed.info/ct/version/v1";
		IRI diagnosisCode=Values.iri("http://umontreal.ca/", "42027");
		IRI originalDiagnosisCode =ResourceParsing.getIRIforCodeAndSystem(codediag, system);
		int priorityAtDischarge =60;
		
		Diagnosis diagd = new Diagnosis(DiagnosisID, originalDiagnosisCode, diagnosisCode, encounterID, providerID, patientID, reportedDate,
				priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
		result.add(diagd);
		


		return result;

	}

	/**
	 * @param	1 diagnosisID
	 * @param FHIRdiagnosis
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static prescriptomeCore.Diagnosis getDiagnosisFromFHIR(String diagnosisID, DiagnosticReport FHIRdiagnosis){
		String DiagnosisID=diagnosisID;
		IRI originalDiagnosisCode=ResourceParsing.getIRIforCodeAndSystem(FHIRdiagnosis.getCode().getCodingFirstRep().getCode(), FHIRdiagnosis.getCategoryFirstRep().getCodingFirstRep().getSystem()+"+"
				+FHIRdiagnosis.getCategoryFirstRep().getCodingFirstRep().getVersion());
		//IRI diagnosisCode =
		String encounterID = FHIRdiagnosis.getEncounterTarget().getId();
		String providerID = "";
		for (Resource a : FHIRdiagnosis.getPerformerTarget()) {
			providerID =a.getId();
		}

		String patientID = FHIRdiagnosis.getSubjectTarget().getId();
		Date reportedDate = FHIRdiagnosis.getIssued();
		int priorityAtDischarge =0;
		boolean presentAtEncounter = false;

		Date validitytime = FHIRdiagnosis.getEffectivePeriod().getEnd();
		Date createtime = FHIRdiagnosis.getEffectiveDateTimeType().getValue();
		Date modifytime= FHIRdiagnosis.getEffectivePeriod().getStart();
		Diagnosis diag = new Diagnosis(
			DiagnosisID, 
			originalDiagnosisCode, 
			null, 
			encounterID, 
			providerID, 
			patientID, 
			reportedDate,
			priorityAtDischarge, 
			presentAtEncounter, 
			validitytime, 
			createtime, 
			modifytime
		);
		
		return diag;

	}


}
