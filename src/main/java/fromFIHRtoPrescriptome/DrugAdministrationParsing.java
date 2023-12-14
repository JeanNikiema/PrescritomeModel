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
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationAdministration;
import prescriptomeCore.DrugAdministration;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugAdministrationParsing {

	/**
	 * @param AdministrationID
	 * @param medicationAdministration
	 * @return
	 */
	public DrugAdministration   getDrugAdministrationFromFHIR(String AdministrationID, MedicationAdministration medicationAdministration){

		String encounterID = AdministrationID;
		String providerID = medicationAdministration.getPerformerFirstRep().getId();
		String patientID = medicationAdministration.getSubjectTarget().getId();
		String facilityID = null;
		String codeSourceDrug = medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codesystem = medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = null;
		
		String StayID =medicationAdministration.getContextTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDateStay = null;
		Date StartDateStay = null;
		Stay stayExposure = new Stay(StartDateStay, endDateStay, StayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		List<CodeableConcept> drugTherapeuticIndications = medicationAdministration.getReasonCode();
		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		for(CodeableConcept codeTher : drugTherapeuticIndications) {
			String code  = codeTher.getCodingFirstRep().getCode();
			String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
			IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, systemss);
			drugIDTherapeuticIndications.add(informationSourceTypeCode);
		}

		IRI encounterUnitOfPresentation =null;

		String codeRoute  = medicationAdministration.getDosage().getRoute().getCodingFirstRep().getCode();
		String systemsRoute = medicationAdministration.getDosage().getRoute().getCodingFirstRep().getSystem();
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null;

		Date endDate = medicationAdministration.getEffectivePeriod().getEnd();
		Date StartDate = medicationAdministration.getEffectivePeriod().getStart();

		String administrationInstructions = medicationAdministration.getDosage().getText();
		int administeredDose =medicationAdministration.getDosage().getDose().getValue().intValue();

		MedicationRequest prescriptionID = medicationAdministration.getRequestTarget();

		String ID = prescriptionID.getId();
		prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR(ID, prescriptionID);

		boolean Stop = false;
		IRI stopReason = null;
		IRI stopReasonSource = null;

		if(medicationAdministration.getStatus().toString().equals("stopped")) {
			Stop = true;
			String codeReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getCode();
			String systemsReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getSystem()+"/version/"+medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getVersion();
			stopReasonSource =ResourceParsing.getIRIforCodeAndSystem(codeReason, systemsReason);
		}

		DrugAdministration fDrugAdministration = new DrugAdministration(encounterID, providerID,
				patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndications, routeOfAdministrationSource, routeOfAdministrationOCRx,
				encounterUnitOfPresentation, StartDate, endDate, administrationInstructions, administeredDose, prescription,
				Stop, stopReasonSource, stopReason);

		return fDrugAdministration;
	}

	
}
