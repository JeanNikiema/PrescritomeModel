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
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.PractitionerRole;

import ca.uhn.fhir.rest.client.api.IGenericClient;

import org.hl7.fhir.r4.model.MedicationAdministration;
import prescriptomeCore.DrugAdministration;
import prescriptomeCore.Stay;

/**
 * @author coulidaou
 *
 */
public class DrugAdministrationParsing {

	public MedicationAdministration getDrugAdministrFromFhirServer(IGenericClient client) {
		Bundle results = client
		   .search().forResource(MedicationAdministration.class)
		   .returnBundle(Bundle.class)
		   .execute();
		MedicationAdministration drugAdmin = (MedicationAdministration) results.getEntryFirstRep().getResource();
		
		return drugAdmin ;
	}
	
	
	public DrugAdministration   getDrugAdministrationFromFHIR(String administrationID, MedicationAdministration medicationAdministration, IGenericClient client){
		String encounterID = "25314";
		String providerID = medicationAdministration.getPerformerFirstRep().getActor().getReference();
//		providerID=providerID.split("/")[1];
		providerID="1144";
		
		String patientID = medicationAdministration.getSubject().getReference();
//		patientID=patientID.split("/")[1];
		patientID="24739";
		
		String facilityID = "185"; // Est que ça ne peut pas rester juste au niveau de patient ?
		String codeSourceDrug = medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codesystem = medicationAdministration.getMeta().getVersionId();

		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = Values.iri("http://umontreal.ca/drugocrx/", "undefined");

//		String StayID =medicationAdministration.getContextTarget().getId();
		String StayID ="STAY_001"; // Est que ça ne peut pas rester juste au niveau de patient ?
		
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

		IRI encounterUnitOfPresentation =Values.iri("http://umontreal.ca/unitofpresentation/", "undefined");

		String codeRoute  = medicationAdministration.getDosage().getSite().getCodingFirstRep().getCode();
		String systemsRoute = medicationAdministration.getDosage().getSite().getCodingFirstRep().getSystem();

		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =Values.iri("http://umontreal.ca/adminocrx/", "undefined");

		Date endDate = medicationAdministration.getEffectivePeriod().getEnd();
		Date StartDate = medicationAdministration.getEffectivePeriod().getStart();

		String administrationInstructions = medicationAdministration.getDosage().getText();
//		int administeredDose = medicationAdministration.getDosage().getDose().getValue().intValue();
		int administeredDose = 240;
		MedicationRequest prescriptionID = medicationAdministration.getRequestTarget();

//		String ID = prescriptionID.getId();
		String ID = "30076";
		
		MedicationRequest presciption = client.read().resource(MedicationRequest.class).withId("30076").execute();
		prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR(ID, presciption);
		
		boolean Stop = false;
		IRI stopReason = Values.iri("http://umontreal.ca/stopResason/", "undefined");
		IRI stopReasonSource = Values.iri("http://umontreal.ca/stopReasonSource/", "undefined");

		if(medicationAdministration.getStatus().toString().equals("stopped")) {
			Stop = true;
			String codeReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getCode();
			String systemsReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getSystem()+"/version/"+medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getVersion();
			stopReasonSource =ResourceParsing.getIRIforCodeAndSystem(codeReason, systemsReason);
		}

		DrugAdministration fDrugAdministration = new DrugAdministration(administrationID, providerID,
				patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndications, routeOfAdministrationSource, routeOfAdministrationOCRx,
				encounterUnitOfPresentation, StartDate, endDate, administrationInstructions, administeredDose, prescription,
				Stop, stopReasonSource, stopReason);
		
		return fDrugAdministration;
	}
	
}
