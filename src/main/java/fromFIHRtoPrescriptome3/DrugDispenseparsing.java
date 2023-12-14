/**
 * 
 */
package fromFIHRtoPrescriptome3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Resource;
import prescriptomeCore.Dispense;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugDispenseparsing {
	public Set<prescriptomeCore.Dispense>   getDrugDispenseFromFHIR(String dispenseID, HashMap<String, Resource> resources) throws ParseException{
		Encounter encount = (Encounter) resources.get("Encounter");
		String encounterID = dispenseID;
		encounterID = "22434606";
		String providerID = "P56AFM";
		String patientID="18173897";
		String facilityID="18173897-452";
		String codeSourceDrug = "FF";
		String version = "v1";
		String codesystem = "http://terminology.hl7.org/CodeSystem/v3-ActCode";
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = Values.iri("http://umontreal.ca/drugOrcx/", "");
		
		String stayID = "stay_001";
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDate = null;
		Date StartDate = null;
		
		Stay stayExposure = new Stay(StartDate, endDate, stayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		IRI encounterUnitOfPresentation =null;
		String codeRoute  = "26643006";

		String systemsRoute = "http://umontreal.ca/systemsRoute/";
		
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =Values.iri("http://umontreal.ca/routeOfAdministrationOCRx", "");
		
//		Date dispenseDate = medicationDispense.getWhenHandedOver();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date dispenseDate =  formater.parse("2015-01-15 20:00");
//		int daySupply =medicationDispense.getDaysSupply().getValue().intValue();
//		int quantity = medicationDispense.getQuantity().getValue().intValue();
		
		int daySupply =30;
		int quantity =100 ;

//		String prescriptionID = medicationDispense.getAuthorizingPrescriptionFirstRep().getReference();
		String prescriptionID = "961806";

//		MedicationRequest presciption = client.read().resource(MedicationRequest.class).withId(prescriptionID).execute();

		Set<prescriptomeCore.Prescription> listOfPrescription = new HashSet<>();

		String ID = prescriptionID;
		
		prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR("961806", resources);
		
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
