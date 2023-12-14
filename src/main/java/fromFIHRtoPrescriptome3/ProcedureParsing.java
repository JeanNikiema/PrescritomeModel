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
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Resource;
import prescriptomeCore.Device;
import prescriptomeCore.Encounter;
import prescriptomeCore.Procedure;

/**
 * @author coulidaou
 *
 */
public class ProcedureParsing {
	public Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, HashMap<String, Resource> resources){
		org.hl7.fhir.r4.model.Procedure FHIRProcedure = (org.hl7.fhir.r4.model.Procedure) resources.get("Procedure");
		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<>();

		String providerID = "P56AFM";
		String patientID = "18173897";
		
		Date date = new Date();
		
		Date procedureDate = date;

		Date validitytime= date ;

		Date createtime= date;

		Date modifytime= date;
		
		IRI typeCode = Values.iri("http://umontreal.ca/", "undefined");
		String encounterID = "22434606";
		
		String facilityID = FHIRProcedure.getLocationTarget().getId();
		facilityID="18173897-452";
		Encounter encounter = new Encounter(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime);

		String codeOr ="8922154";
		String systemOr =  "http://snomed.info/sct";
		IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(codeOr, systemOr);

		
		String code = "8922154";
		String system =  "http://snomed.info/sct";
		
		IRI originaleTypeCode=ResourceParsing.getIRIforCodeAndSystem(code, system);
		int quantity= 1;
		
		List<CodeableConcept> resull = FHIRProcedure.getUsedCode();
		
		if(!resull.isEmpty()) {
			for (CodeableConcept co : resull) {
				String deviceID = co.getId();
				deviceID = "171";
				String deviceCode = "undefined";

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
		String deviceCode = "undefined";
		Device device= new Device(deviceID, deviceCode, validitytime, createtime, modifytime);
		int priorityCode = 1;
		Date startDate =date;
		Date endDate = date;
		
		Procedure procd = new Procedure(procedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
				priorityCode, validitytime, createtime, modifytime, startDate, endDate, device);
		
		setProcedure.add(procd);
		
		return setProcedure;
	}
	
}
