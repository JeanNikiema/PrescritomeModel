/**
 * 
 */
package fromFIHRtoPrescriptome3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.MedicationAdministration;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Resource;
import fromFIHRtoPrescriptome.ResourceParsing;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

	public class DrugPrescritpionParsing {

		public static prescriptomeCore.Prescription getDrugPrescritpionFromFHIR(String prescriptionID, HashMap<String, Resource> resources) throws ParseException{
//			MedicationRequest prescription = (MedicationRequest) resources.get("Medication") ;
			MedicationAdministration drugAdmin = (MedicationAdministration) resources.get("MedicationAdministration") ;
			String providerID = "P56AFM";
			String patientID="18173897";
			String facilityID = "18173897-452";
			String codeSourceDrug = "00169750111";
			String codesystem = "http://terminology.hl7.org/CodeSystem/";
			String ex ="http://umontreal.ca/";
			IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
			IRI drugIDOCRx = Values.iri(ex + "drugIDOCRx/", "");
			String StayID ="stay_001";
			
			Date date = new Date();
			Date validitytimeStay = date;
			Date createtimeStay = date;
			Date modifytimeStay = date;

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddd hh:mm:ss");
			
			Date startDate = format.parse("2143-10-18 12:00:00") ;
			Date endDate = format.parse("2143-10-18 12:00:00") ;

			Stay stayExposure = new Stay(
					startDate, endDate, StayID, providerID, patientID, facilityID, 
					validitytimeStay, createtimeStay, modifytimeStay
			);
			
			List<CodeableConcept> drugTherapeuticIndications = drugAdmin.getReasonCode();
			Set<IRI> drugIDTherapeuticIndications = new HashSet<>();
			
			for(CodeableConcept codeTher : drugTherapeuticIndications) {
				String code  = codeTher.getCodingFirstRep().getCode();
				String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
				IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, systemss);
				drugIDTherapeuticIndications.add(informationSourceTypeCode);
			}


//			String codeRoute  = prescription.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();
			String codeRoute  = "SC";
//			String systemsRoute = prescription.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+prescription.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
			String systemsRoute = "";
			IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
			IRI routeOfAdministrationOCRx =Values.iri("http://umontreal.ca/routeOfAdministrationOCRx/", "");
//			int orderedDose = prescription.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getDoseQuantity().getValue().intValue();
			int orderedDose = 3;
			Date effectiveStartDate = startDate;
			Date effectiveEndDate = endDate;
			Date orderedDate = startDate;
//			int orderQuantity = prescription.getDispenseRequest().getQuantity().getValue().intValue();
			int orderQuantity = 3;
//			int dosefrequenceQuantity= prescription.getDosageInstructionFirstRep().getDoseAndRateFirstRep().getRateQuantity().getValue().intValue();
			int dosefrequenceQuantity= 3;
			IRI encounterUnitOfPresentation =Values.iri(ex+"encounterUnitOfPresentation/", "");
			int refills = 1;
			IRI  doseFrequenceUnit = Values.iri(ex+"doseFrequenceUnit/", "");

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
					refills, 
					orderedDate,
					orderedDose,
					effectiveStartDate, 
					effectiveEndDate, 
					orderQuantity, 
					dosefrequenceQuantity, 
					doseFrequenceUnit);
			
			return pres;
		}

}
