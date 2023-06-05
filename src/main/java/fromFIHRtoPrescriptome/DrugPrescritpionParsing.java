/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.hl7.fhir.r4.model.CodeableConcept;

import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugPrescritpionParsing {

	/**
	 * @param PrescriptionID
	 * @param medicationRequest
	 * @return
	 */
	public static prescriptomeCore.Prescription getDrugPrescritpionFromFHIR(String PrescriptionID, org.hl7.fhir.r4.model.MedicationRequest medicationRequest){

		String encounterID = PrescriptionID;
		String providerID = medicationRequest.getPerformerTarget().getId();
		String patientID = medicationRequest.getSubjectTarget().getId();
		String facilityID = medicationRequest.getEncounterTarget().getLocationFirstRep().getId();
		String codeSourceDrug = medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codesystem = medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = null;
		String StayID =medicationRequest.getEncounterTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDate = medicationRequest.getEncounterTarget().getPeriod().getEnd();
		Date StartDate = medicationRequest.getEncounterTarget().getPeriod().getStart();
		Stay stayExposure = new Stay(StartDate, endDate, StayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		List<CodeableConcept> drugTherapeuticIndications = medicationRequest.getReasonCode();
		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();
		
		for(CodeableConcept codeTher : drugTherapeuticIndications) {
			String code  = codeTher.getCodingFirstRep().getCode();
			String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
			IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, systemss);
			drugIDTherapeuticIndications.add(informationSourceTypeCode);
		}


		String codeRoute  = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		String systemsRoute = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null;
		int orderedDose = medicationRequest.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getDoseQuantity().getValue().intValue();
		Date effectiveStartDate = medicationRequest.getDispenseRequest().getValidityPeriod().getStart();
		Date effectiveEndDate = medicationRequest.getDispenseRequest().getValidityPeriod().getEnd();
		Date orderedDate = medicationRequest.getAuthoredOn();
		int orderQuantity = medicationRequest.getDispenseRequest().getQuantity().getValue().intValue();
		int dosefrequenceQuantity= medicationRequest.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getRateQuantity().getValue().intValue();
		IRI encounterUnitOfPresentation =null;
		int refills = 1;
		IRI  doseFrequenceUnit = null;

		Prescription pres = new Prescription(
				encounterID, 
				providerID, 
				patientID, 
				facilityID, 
				validitytimeStay,
				createtimeStay, 
				modifytimeStay, 
				drugIDDataSource, 
				drugIDOCRx, 
				stayExposure, 
				drugIDTherapeuticIndications,
				routeOfAdministrationSource, 
				routeOfAdministrationOCRx, 
				encounterUnitOfPresentation, 
				refills, orderedDate,
				orderedDose,
				effectiveStartDate, 
				effectiveEndDate, 
				orderQuantity, 
				dosefrequenceQuantity, 
				doseFrequenceUnit);

		return pres;
	}


}
