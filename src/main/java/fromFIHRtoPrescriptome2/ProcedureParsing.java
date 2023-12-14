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
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Practitioner;

import org.hl7.fhir.r4.model.Claim.ProcedureComponent;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Device;
import prescriptomeCore.Encounter;
import prescriptomeCore.Procedure;

/**
 * @author coulidaou
 *
 */
public class ProcedureParsing {
	public org.hl7.fhir.r4.model.Procedure getProcedureFromFhirServer(IGenericClient client ) {
		org.hl7.fhir.r4.model.Procedure procedure = client.read()
			.resource(org.hl7.fhir.r4.model.Procedure.class)
			.withId("39555")
			.execute();
	
		return procedure ;
	}
	
	
	public Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, org.hl7.fhir.r4.model.Procedure FHIRProcedure){

		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<>();

//		String providerID = FHIRProcedure.getPerformerFirstRep().getId();
//		String patientID = FHIRProcedure.getSubjectTarget().getId();
		String providerID = "1144";
		String patientID = "24739";
		Date procedureDate = FHIRProcedure.getPerformedDateTimeType().getValue();
		
		Date date = new Date();
		
//		Date validitytime = FHIRProcedure.getPerformedPeriod().getEnd();
		Date validitytime= date ;

//		Date createtime = FHIRProcedure.getPerformedPeriod().getStart();
		Date createtime= date;

//		Date modifytime= FHIRProcedure.getPerformedPeriod().getStart();
		Date modifytime= date;
		
		IRI typeCode = Values.iri("http://umontreal.ca/", "undefined");
		String encounterID = "25314";
		
		String facilityID = FHIRProcedure.getLocationTarget().getId();
		facilityID="185";
		Encounter encounter = new Encounter(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime);

		String codeOr = FHIRProcedure.getCode().getCodingFirstRep().getCode();
		String systemOr = FHIRProcedure.getCode().getCodingFirstRep().getSystem() +"/version/v1";
		IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(codeOr, systemOr);

//		String code = FHIRProcedure.getCategory().getId();
//		String system = FHIRProcedure.getCategory().getCodingFirstRep().getSystem() +"/version/";
		
		String code = FHIRProcedure.getCode().getCodingFirstRep().getCode();
		String system = FHIRProcedure.getCode().getCodingFirstRep().getSystem() +"/version/v1";
		
		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(code, system);
		int quantity= 1;
		
		List<CodeableConcept> resull = FHIRProcedure.getUsedCode();
		
		if(!resull.isEmpty()) {
			for (CodeableConcept co : resull) {
				String deviceID = co.getId();
				deviceID = "171";
				String deviceCode = "";

				Device Device= new Device(deviceID, deviceCode, validitytime, createtime, modifytime);
				int priorityCode = 1;
				Date startDate =FHIRProcedure.getPerformedPeriod().getStart();
				Date endDate = FHIRProcedure.getPerformedPeriod().getEnd();
				Procedure procd = new Procedure(procedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
						priorityCode, validitytime, createtime, modifytime, startDate, endDate, Device);
				setProcedure.add(procd);
			}
		}
		
		String deviceID = "171";
		String deviceCode = "";
		Device device= new Device(deviceID, deviceCode, validitytime, createtime, modifytime);
		int priorityCode = 1;
//		Date startDate =FHIRProcedure.getPerformedPeriod().getStart();
//		Date endDate = FHIRProcedure.getPerformedPeriod().getEnd();
		Date startDate =date;
		Date endDate = date;
		Procedure procd = new Procedure(procedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
				priorityCode, validitytime, createtime, modifytime, startDate, endDate, device);
		setProcedure.add(procd);
		
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
