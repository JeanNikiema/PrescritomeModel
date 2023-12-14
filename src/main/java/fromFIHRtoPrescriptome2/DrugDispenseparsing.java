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
import org.hl7.fhir.r4.model.MedicationDispense;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Dispense;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugDispenseparsing {
	
	public org.hl7.fhir.r4.model.MedicationDispense getMedicationDispenseFromFhirServer(IGenericClient client) {
		MedicationDispense medicationDispense = client.read()
				.resource(MedicationDispense.class)
				.withId("30077")
				.execute();
		
		return medicationDispense ;
	}

	public Set<prescriptomeCore.Dispense>   getDrugDispenseFromFHIR(String dispenseID, org.hl7.fhir.r4.model.MedicationDispense medicationDispense, IGenericClient client){
		String encounterID = dispenseID;
		encounterID = "25314";
		String providerID = medicationDispense.getPerformerFirstRep().getId();
		providerID = "1144";
//		String patientID = medicationDispense.getSubjectTarget().getId();
		String patientID="24739";
//		String facilityID = medicationDispense.getLocationTarget().getId();
		String facilityID="185";
		String codeSourceDrug = medicationDispense.getType().getCodingFirstRep().getCode();
//		String version = medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		String version = "v1";
		String codesystem = medicationDispense.getType().getCodingFirstRep().getSystem()+"/version/"+version;
		
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = Values.iri("http://umontreal.ca/drugOrcx/", "");
		
//		String stayID =medicationDispense.getContextTarget().getId();
		String stayID = "STAY_001";
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDate = null;
		Date StartDate = null;
		Stay stayExposure = new Stay(StartDate, endDate, stayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		IRI encounterUnitOfPresentation =null;

		String codeRoute  = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		version = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
		String systemsRoute = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+version;
		
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null;
		
		Date dispenseDate = medicationDispense.getWhenHandedOver();
		int daySupply =medicationDispense.getDaysSupply().getValue().intValue();
		int quantity = medicationDispense.getQuantity().getValue().intValue();

		String prescriptionID = medicationDispense.getAuthorizingPrescriptionFirstRep().getReference();
		prescriptionID=prescriptionID.split("/")[1];
		MedicationRequest presciption = client.read().resource(MedicationRequest.class).withId(prescriptionID).execute();

		Set<prescriptomeCore.Prescription> listOfPrescription = new HashSet<>();

		String ID = prescriptionID;
		prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR(ID, presciption);
		listOfPrescription.add(prescription);

		Set<prescriptomeCore.Dispense> listOfDispense = new HashSet<>();
		for (Prescription ad : listOfPrescription) {
			Dispense disp = new Dispense(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource, drugIDOCRx, stayExposure, drugIDTherapeuticIndications,
					routeOfAdministrationSource, routeOfAdministrationOCRx, encounterUnitOfPresentation, daySupply, dispenseDate, quantity, ad);
			listOfDispense.add(disp);
		}

		return listOfDispense;
	}

}
