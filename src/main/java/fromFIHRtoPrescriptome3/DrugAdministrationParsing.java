package fromFIHRtoPrescriptome3;

import java.text.ParseException;
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
import prescriptomeCore.DrugAdministration;
import prescriptomeCore.Stay;

public class DrugAdministrationParsing {
	public DrugAdministration  getMedicationAdministFromResource(String administrationID, HashMap<String, Resource> resources ) throws ParseException{
		MedicationAdministration drugAdminis = (MedicationAdministration) resources.get("MedicationAdministration") ;

		String providerID = drugAdminis.getPerformerFirstRep().getId();
		providerID="P56AFM";
		String patientID = drugAdminis.getSubject().getReference();
		String facilityID = null;
		facilityID="18173897-452";
//		String codeSourceDrug = drugAdminis.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codeSourceDrug="322254008";
		String codesystem = drugAdminis.getIdentifierFirstRep().getSystem();
		
		IRI drugIDDataSource =ResourceParsing.getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = Values.iri("http://umontreal.ca/drugIDOCRx/", "");
		
//		String stayID =drugAdminis.getContextTarget().getId();
		String stayID = "stay_001";
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDateStay = null;
		Date StartDateStay = null;
		
		Stay stayExposure = new Stay(
				StartDateStay, endDateStay, stayID, providerID, 
				patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		List<CodeableConcept> drugTherapeuticIndications = drugAdminis.getReasonCode();
		Set<IRI> drugIDTherapeuticIndications = new HashSet<>();

		for(CodeableConcept codeTher : drugTherapeuticIndications) {
			String code  = codeTher.getCodingFirstRep().getCode();
			String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
			IRI informationSourceTypeCode =ResourceParsing.getIRIforCodeAndSystem(code, systemss);
			drugIDTherapeuticIndications.add(informationSourceTypeCode);
		}

		IRI encounterUnitOfPresentation =null;

		String codeRoute  = drugAdminis.getDosage().getRoute().getCodingFirstRep().getCode();
		codeRoute = "34402009";
		String systemsRoute = drugAdminis.getDosage().getRoute().getCodingFirstRep().getSystem();
		systemsRoute="http://umontreal.ca/systemsRoute";
		IRI routeOfAdministrationSource =ResourceParsing.getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null;

		Date endDate = drugAdminis.getEffectivePeriod().getEnd();
		Date StartDate = drugAdminis.getEffectivePeriod().getStart();

		String administrationInstructions ="Rectum structure";
		int administeredDose=34402009;

		prescriptomeCore.Prescription prescription = DrugPrescritpionParsing.getDrugPrescritpionFromFHIR("961806", resources);

		boolean Stop = false;
		IRI stopReason = null;
		IRI stopReasonSource = null;

		if(drugAdminis.getStatus().toString().equals("stopped")) {
			Stop = true;
			String codeReason = drugAdminis.getStatusReasonFirstRep().getCodingFirstRep().getCode();
			String systemsReason = drugAdminis.getStatusReasonFirstRep().getCodingFirstRep().getSystem()+"/version/"+drugAdminis.getStatusReasonFirstRep().getCodingFirstRep().getVersion();
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
