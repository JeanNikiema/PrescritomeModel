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
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

	public class DrugPrescritpionParsing {
		public org.hl7.fhir.r4.model.MedicationRequest getDrugPrescritpionFromFhirServer(IGenericClient client) {
			MedicationRequest medicationRequest = client.read()
					.resource(MedicationRequest.class)
					.withId("30076")
					.execute();
			
			return medicationRequest ;
		}
	

	public static prescriptomeCore.Prescription getDrugPrescritpionFromFHIR(String prescriptionID, org.hl7.fhir.r4.model.MedicationRequest medicationRequest){

//		String providerID = medicationRequest.getPerformerTarget().getId();
		String providerID = "1144";
//		String patientID = medicationRequest.getSubject().getReference();
		String patientID = "24739";
//		String facilityID = medicationRequest.getEncounterTarget().getLocationFirstRep().getId();
		String facilityID = "185";
//		String codeSourceDrug = medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codeSourceDrug = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		String codesystem = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/v1";
		
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = Values.iri("http://umontreal.ca/drugOcrx/", "undefined");
//		String StayID =medicationRequest.getEncounterTarget().getId();
		String StayID ="STAY_001";
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
//		Date endDate = medicationRequest.getEncounterTarget().getPeriod().getEnd();
//		Date StartDate = medicationRequest.getEncounterTarget().getPeriod().getStart();
		
		Date endDate = date;
		Date StartDate = date;
		
		Stay stayExposure = new Stay(StartDate, endDate, StayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		String code  = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		String systems = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem();
		IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, systems);
		drugIDTherapeuticIndications.add(informationSourceTypeCode);



		String codeRoute  = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
		String systemsRoute = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/v1";
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =Values.iri("http://umontreal.ca/adminOcrx/", "undefined");
		int orderedDose = medicationRequest.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getDoseRange().getLow().getValue().intValue();
		
//		Date effectiveStartDate = medicationRequest.getDispenseRequest().getValidityPeriod().getStart();
//		Date effectiveEndDate = medicationRequest.getDispenseRequest().getValidityPeriod().getEnd();
		Date effectiveStartDate = date;
		Date effectiveEndDate = date;
		Date orderedDate = medicationRequest.getAuthoredOn();
//		int orderQuantity = medicationRequest.getDispenseRequest().getQuantity().getValue().intValue();
		int orderQuantity = 1;
		int dosefrequenceQuantity= medicationRequest.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getDoseRange().getLow().getValue().intValue();
		IRI encounterUnitOfPresentation =Values.iri("http://umontreal.ca/unitPresentation/", "undefined");
		int refills = 1;
		IRI  doseFrequenceUnit = Values.iri("http://umontreal.ca/dosefeqUnit/", "undefined");

		Prescription pres = new Prescription(
				prescriptionID, 
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
