/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Claim.ProcedureComponent;

import prescriptomeCore.Device;
import prescriptomeCore.Encounter;
import prescriptomeCore.Procedure;

/**
 * @author coulidaou
 *
 */
public class ProcedureParsing {


	/**
	 * @param procedureID
	 * @param FHIRProcedure
	 * @return
	 */
	public static Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, org.hl7.fhir.r4.model.Procedure FHIRProcedure){

		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<>();

		String providerID = FHIRProcedure.getPerformerFirstRep().getId();

		String ProcedureID=procedureID;
		String patientID = FHIRProcedure.getSubjectTarget().getId();
		Date procedureDate = FHIRProcedure.getPerformedDateTimeType().getValue();

		Date validitytime = FHIRProcedure.getPerformedPeriod().getEnd();
		Date createtime = FHIRProcedure.getPerformedPeriod().getStart();
		Date modifytime= FHIRProcedure.getPerformedPeriod().getStart();
		
		IRI typeCode = null;
		String encounterID = "";
		
		String FacilityID = FHIRProcedure.getLocationTarget().getId();
		Encounter encounter = new Encounter(encounterID, providerID, patientID, FacilityID, validitytime, createtime, modifytime);

		String codeOr = FHIRProcedure.getCode().getId();
		String systemOr = FHIRProcedure.getCode().getCodingFirstRep().getSystem() +"/version/"+FHIRProcedure.getCode().getCodingFirstRep().getVersion();
		IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(codeOr, systemOr);

		String code = FHIRProcedure.getCategory().getId();
		String system = FHIRProcedure.getCategory().getCodingFirstRep().getSystem() +"/version/"+FHIRProcedure.getCategory().getCodingFirstRep().getVersion();
		
		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(code, system);
		int quantity= 1;
		
		List<CodeableConcept> resull = FHIRProcedure.getUsedCode();
		
		for (CodeableConcept co : resull) {
			Device Device= new Device("", co.getId(), validitytime, createtime, modifytime);
			int priorityCode = 1;
			Date startDate =FHIRProcedure.getPerformedPeriod().getStart();
			Date endDate = FHIRProcedure.getPerformedPeriod().getEnd();
			Procedure procd = new Procedure(ProcedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
					priorityCode, validitytime, createtime, modifytime, startDate, endDate, Device);
			setProcedure.add(procd);
		}

		return setProcedure;
	}
	
	

	/**
	 * @param procedureID
	 * @param FHIRclaim
	 * @return
	 */
	public static Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, Claim FHIRclaim){
		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<>();

		String providerID = FHIRclaim.getCareTeamFirstRep().getProviderTarget().getId();


		String patientID = FHIRclaim.getPatientTarget().getId();
		//Date reportedDate = FHIRclaim.getCreated();
		List<ProcedureComponent> getProcedureList  = FHIRclaim.getProcedure();
		Date validitytime = FHIRclaim.getBillablePeriod().getEnd();
		Date createtime = FHIRclaim.getBillablePeriod().getStart();
		Date modifytime= FHIRclaim.getBillablePeriod().getStart();

		String encounterID = "";
		String FacilityID = FHIRclaim.getFacilityTarget().getId();
		Encounter encounter = new Encounter(encounterID, providerID, patientID, FacilityID, validitytime, createtime, modifytime);

		for(ProcedureComponent a : getProcedureList) {

			String ProcedureID=procedureID+a.getSequence();
			String code = a.getTypeFirstRep().getCodingFirstRep().getCode();
			String system = a.getTypeFirstRep().getCodingFirstRep().getSystem()+"/version/"
					+a.getTypeFirstRep().getCodingFirstRep().getVersion();
			IRI typeCode = null;

			String codeOr = a.getProcedureCodeableConcept().getCodingFirstRep().getCode();
			String systemOr = a.getProcedureCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
					+a.getProcedureCodeableConcept().getCodingFirstRep().getVersion();
			IRI originaleTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, system);
			IRI informationSourceTypeCode=ResourceParsing.getIRIforCodeAndSystem(codeOr, systemOr);

			Date procedureDate = a.getDate();

			int quantity= 1;



			int priorityCode = a.getSequence();
			Date startDate =null;
			Date endDate = null;
			Device Device= null;
			
			Procedure procd = new Procedure(
					ProcedureID, 
					encounter, 
					typeCode, 
					originaleTypeCode, 
					procedureDate, 
					quantity, 
					informationSourceTypeCode,
					priorityCode, 
					validitytime, 
					createtime, 
					modifytime, 
					startDate, 
					endDate, 
					Device);
			
			setProcedure.add(procd);
		}

		return setProcedure;
	}



}
