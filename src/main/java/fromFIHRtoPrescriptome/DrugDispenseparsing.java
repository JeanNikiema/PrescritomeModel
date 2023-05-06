/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.hl7.fhir.r4.model.MedicationRequest;

import prescriptomeCore.Dispense;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugDispenseparsing {

	/**
	 * @param DispenseID
	 * @param medicationDispense
	 * @return
	 */
	public static Set<prescriptomeCore.Dispense>   getDrugDispenseFromFHIR(String DispenseID, org.hl7.fhir.r4.model.MedicationDispense medicationDispense){
		String encounterID = DispenseID;
		String providerID = medicationDispense.getPerformerFirstRep().getId();
		String patientID = medicationDispense.getSubjectTarget().getId();
		String facilityID = medicationDispense.getLocationTarget().getId();
		String codeSourceDrug = medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codesystem = medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = null;
		
		String StayID =medicationDispense.getContextTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDate = null;
		Date StartDate = null;
		Stay stayExposure = new Stay(StartDate, endDate, StayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		IRI encounterUnitOfPresentation =null;

		String codeRoute  = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		String systemsRoute = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
		
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null;
		
		Date dispenseDate = medicationDispense.getWhenHandedOver();
		int daySupply =medicationDispense.getDaysSupply().getValue().intValue();
		int quantity = medicationDispense.getQuantity().getValue().intValue();

		@SuppressWarnings("deprecation")
		List<MedicationRequest> prescriptionID = medicationDispense.getAuthorizingPrescriptionTarget();
		Set<prescriptomeCore.Prescription> listOfPrescription = new HashSet<>();

		for (MedicationRequest request : prescriptionID) {
			String ID = request.getId();
			prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR(ID, request);
			listOfPrescription.add(prescription);
		}
		Set<prescriptomeCore.Dispense> listOfDispense = new HashSet<>();
		for (Prescription ad : listOfPrescription) {
			Dispense disp = new Dispense(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource, drugIDOCRx, stayExposure, drugIDTherapeuticIndications,
					routeOfAdministrationSource, routeOfAdministrationOCRx, encounterUnitOfPresentation, daySupply, dispenseDate, quantity, ad);
			listOfDispense.add(disp);
		}

		return listOfDispense;
	}

}
