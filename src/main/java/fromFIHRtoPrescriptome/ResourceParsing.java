package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.Claim.DiagnosisComponent;
import org.hl7.fhir.r4.model.Claim.ProcedureComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Device;
import prescriptomeCore.Diagnosis;
import prescriptomeCore.Dispense;
import prescriptomeCore.DrugAdministration;
import prescriptomeCore.Encounter;
import prescriptomeCore.Facility;
import prescriptomeCore.PatientGroup;
import prescriptomeCore.Prescription;
import prescriptomeCore.Procedure;
import prescriptomeCore.Provider;
import prescriptomeCore.Stay;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;

/**
 * 
 * @author Jean Nikiema
 *List of methods to convert FHIR data elements to CDMH-RDF
 */

public class ResourceParsing {

	/**
	 * 
	 * @param Encounter
	 * @return
	 */
	public static Set<prescriptomeCore.Observation>   getObservationFromFHIR(org.hl7.fhir.r4.model.Observation Observation){
		

		Set<prescriptomeCore.Observation> result = new HashSet<prescriptomeCore.Observation>();
		Set<prescriptomeCore.Encounter> encounterDescription = new HashSet<prescriptomeCore.Encounter>();
		String observationID =Observation.getId();
		String providerID = Observation.getPerformerFirstRep().getId();
		String encounterID =Observation.getEncounterTarget().getId();
		String patientID = Observation.getSubjectTarget().getId();
		Date reportDate = Observation.getIssued();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		List<EncounterLocationComponent> encouterlocation= Observation.getEncounterTarget().getLocation();
		int v=1;
		String codesystem = Observation.getCategoryFirstRep().getCodingFirstRep().getSystem()+"/version/"
				+Observation.getCategoryFirstRep().getCodingFirstRep().getVersion();
		
	
		String codeSource =Observation.getCategoryFirstRep().getCodingFirstRep().getCode();
		IRI originaleTypeCode=getIRIforCodeAndSystem(codeSource, codesystem);
		IRI informationSourceTypeCode=getIRIforCodeAndSystem("", codesystem);
		IRI typeCode = null;
		IRI resultUNITsource = null;
		IRI resultUnitUCUM = null;
		String results = Observation.getValue().toString();
		int resultsInt = Observation.getValueIntegerType().getValue();
		IRI resultsSNOMED = null;
		Date startDate = Observation.getEffectiveDateTimeType().getValue();
		Date endDate = Observation.getEffectiveDateTimeType().getValue();
		if(!Observation.getEffectivePeriod().isEmpty()) {
			startDate =Observation.getEffectivePeriod().getStart();
			endDate =Observation.getEffectivePeriod().getEnd();
		}
		if(!Observation.getEffectiveInstantType().isEmpty()) {
			startDate =Observation.getEffectiveInstantType().getValue();
			endDate =Observation.getEffectiveInstantType().getValue();
		}
		
				
			
		
		
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			Encounter Encoun = new Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			encounterDescription.add(Encoun);
		}
		String facilityID =Observation.getEncounterTarget().getServiceProvider().getId() ;
		Encounter Encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		encounterDescription.add(Encoun);
		
		for (Encounter encounter : encounterDescription) {
			prescriptomeCore.Observation obbs = new prescriptomeCore.Observation(observationID, reportDate, encounter, typeCode, 
					originaleTypeCode, informationSourceTypeCode, results, 
					resultsSNOMED, resultsInt, resultUNITsource, resultUnitUCUM, startDate, endDate, validitytimeStay, createtimeStay, modifytimeStay);
			result.add(obbs);
		}
		return result;

	}

	/**
	 * @param Encounter
	 * @return
	 */
	public static Set<prescriptomeCore.Encounter>   getEncounterFromFHIR(org.hl7.fhir.r4.model.Encounter Encounter){

		Set<prescriptomeCore.Encounter> result = new HashSet<Encounter>();
		String encounterID =Encounter.getId();
		String providerID = Encounter.getParticipantFirstRep().getId();
		String patientID = Encounter.getSubjectTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		List<EncounterLocationComponent> encouterlocation= Encounter.getLocation();
		int v=1;
		for(EncounterLocationComponent comp :  encouterlocation) {
			String facilityID = comp.getId();
			Encounter Encoun = new Encounter(encounterID+"+"+v, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
			v++;
			result.add(Encoun);
		}
		String facilityID =Encounter.getServiceProvider().getId() ;
		Encounter Encoun = new Encounter(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);
		result.add(Encoun);
		return result;

	}




	/**
	 * @param AdministrationID
	 * @param medicationAdministration
	 * @return
	 */
	public static prescriptomeCore.DrugAdministration   getDrugAdministrationFromFHIR(String AdministrationID, org.hl7.fhir.r4.model.MedicationAdministration medicationAdministration){

		String encounterID = AdministrationID;
		String providerID = medicationAdministration.getPerformerFirstRep().getId();
		String patientID = medicationAdministration.getSubjectTarget().getId();
		String facilityID = null;
		String codeSourceDrug = medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getCode();
		String codesystem = medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationAdministration.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		IRI drugIDDataSource =getIRIforCodeAndSystem(codeSourceDrug, codesystem);
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
		Set<IRI> drugIDTherapeuticIndications = new HashSet<IRI>();
		for(CodeableConcept codeTher : drugTherapeuticIndications) {
			String code  = codeTher.getCodingFirstRep().getCode();;
			String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
			IRI informationSourceTypeCode =getIRIforCodeAndSystem(code, systemss);
			drugIDTherapeuticIndications.add(informationSourceTypeCode);
		}

		IRI encounterUnitOfPresentation =null;	

		String codeRoute  = medicationAdministration.getDosage().getRoute().getCodingFirstRep().getCode();;
		String systemsRoute = medicationAdministration.getDosage().getRoute().getCodingFirstRep().getSystem();
		IRI routeOfAdministrationSource =getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null; 

		Date endDate = medicationAdministration.getEffectivePeriod().getEnd();
		Date StartDate = medicationAdministration.getEffectivePeriod().getStart();

		String administrationInstructions = medicationAdministration.getDosage().getText();
		int administeredDose =medicationAdministration.getDosage().getDose().getValue().intValue();


		MedicationRequest prescriptionID = medicationAdministration.getRequestTarget();

		String ID = prescriptionID.getId();
		prescriptomeCore.Prescription prescription = getDrugPrescritpionFromFHIR(ID, prescriptionID);

		boolean Stop = false;
		IRI stopReason = null;
		IRI stopReasonSource = null;

		if(medicationAdministration.getStatus().toString().equals("stopped")) {
			Stop = true;
			String codeReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getCode();
			String systemsReason = medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getSystem()+"/version/"+medicationAdministration.getStatusReasonFirstRep().getCodingFirstRep().getVersion();
			stopReasonSource =getIRIforCodeAndSystem(codeReason, systemsReason);
		}

		DrugAdministration fDrugAdministration = new DrugAdministration(encounterID, providerID, 
				patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndications, routeOfAdministrationSource, routeOfAdministrationOCRx, 
				encounterUnitOfPresentation, StartDate, endDate, administrationInstructions, administeredDose, prescription, 
				Stop, stopReasonSource, stopReason);



		return fDrugAdministration;
	}

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
		String codeSourceDrug = medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getCode();;
		String codesystem = medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationDispense.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		IRI drugIDDataSource =getIRIforCodeAndSystem(codeSourceDrug, codesystem);
		IRI drugIDOCRx = null;
		String StayID =medicationDispense.getContextTarget().getId();
		Date date = new Date();
		Date validitytimeStay = date;
		Date createtimeStay = date;
		Date modifytimeStay = date;
		Date endDate = null;
		Date StartDate = null;
		Stay stayExposure = new Stay(StartDate, endDate, StayID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay);

		Set<IRI> drugIDTherapeuticIndications = new HashSet<IRI>();

		IRI encounterUnitOfPresentation =null;	

		String codeRoute  = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();;
		String systemsRoute = medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+medicationDispense.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
		IRI routeOfAdministrationSource =getIRIforCodeAndSystem(codeRoute, systemsRoute);
		IRI routeOfAdministrationOCRx =null; 
		Date dispenseDate = medicationDispense.getWhenHandedOver();
		int daySupply =medicationDispense.getDaysSupply().getValue().intValue();
		int quantity = medicationDispense.getQuantity().getValue().intValue();

		@SuppressWarnings("deprecation")
		List<MedicationRequest> prescriptionID = medicationDispense.getAuthorizingPrescriptionTarget();
		Set<prescriptomeCore.Prescription> listOfPrescription = new HashSet<Prescription>();
		for (MedicationRequest request : prescriptionID) {
			String ID = request.getId();
			prescriptomeCore.Prescription prescription = getDrugPrescritpionFromFHIR(ID, request);
			listOfPrescription.add(prescription);
		}
		Set<prescriptomeCore.Dispense> listOfDispense = new HashSet<Dispense>();
		for (Prescription ad : listOfPrescription) {
			Dispense disp = new Dispense(encounterID, providerID, patientID, facilityID, validitytimeStay, createtimeStay, modifytimeStay, drugIDDataSource, drugIDOCRx, stayExposure, drugIDTherapeuticIndications,
					routeOfAdministrationSource, routeOfAdministrationOCRx, encounterUnitOfPresentation, daySupply, dispenseDate, quantity, ad);
			listOfDispense.add(disp);
		}

		return listOfDispense;
	}


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
		String codeSourceDrug = medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getCode();;
		String codesystem = medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
				+medicationRequest.getMedicationCodeableConcept().getCodingFirstRep().getVersion();
		IRI drugIDDataSource =getIRIforCodeAndSystem(codeSourceDrug, codesystem);
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
		Set<IRI> drugIDTherapeuticIndications = new HashSet<IRI>();
		for(CodeableConcept codeTher : drugTherapeuticIndications) {
			String code  = codeTher.getCodingFirstRep().getCode();;
			String systemss = codeTher.getCodingFirstRep().getSystem()+"/version/"+codeTher.getCodingFirstRep().getVersion();
			IRI informationSourceTypeCode =getIRIforCodeAndSystem(code, systemss);
			drugIDTherapeuticIndications.add(informationSourceTypeCode);
		}


		String codeRoute  = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getCode();;
		String systemsRoute = medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getSystem()+"/version/"+medicationRequest.getDosageInstructionFirstRep().getRoute().getCodingFirstRep().getVersion();
		IRI routeOfAdministrationSource =getIRIforCodeAndSystem(codeRoute, systemsRoute);
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

		Prescription pres = new Prescription(encounterID, providerID, patientID, facilityID, validitytimeStay,
				createtimeStay, modifytimeStay, drugIDDataSource, drugIDOCRx, stayExposure, drugIDTherapeuticIndications, 
				routeOfAdministrationSource, routeOfAdministrationOCRx, encounterUnitOfPresentation, refills, orderedDate, 
				orderedDose, effectiveStartDate, effectiveEndDate, orderQuantity, dosefrequenceQuantity, doseFrequenceUnit);

		return pres;
	}






	/**
	 * @param procedureID
	 * @param FHIRProcedure
	 * @return
	 */
	public static Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, org.hl7.fhir.r4.model.Procedure FHIRProcedure){

		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<Procedure>();

		String providerID = FHIRProcedure.getPerformerFirstRep().getId();

		String ProcedureID=procedureID;
		String patientID = FHIRProcedure.getSubjectTarget().getId(); 
		Date procedureDate = FHIRProcedure.getPerformedDateTimeType().getValue();

		Date validitytime = FHIRProcedure.getPerformedPeriod().getEnd(); 
		Date createtime = FHIRProcedure.getPerformedPeriod().getStart(); 
		Date modifytime= FHIRProcedure.getPerformedPeriod().getStart(); 
		IRI typeCode = null;
		String encounterID = "";
		String FacilityID = FHIRProcedure.getLocationTarget().getId();
		Encounter encounter = new Encounter(encounterID, providerID, patientID, FacilityID, validitytime, createtime, modifytime);

		String codeOr = FHIRProcedure.getCode().getId();
		String systemOr = FHIRProcedure.getCode().getCodingFirstRep().getSystem() +"/version/"
				+FHIRProcedure.getCode().getCodingFirstRep().getVersion();
		IRI informationSourceTypeCode =getIRIforCodeAndSystem(codeOr, systemOr);

		String code = FHIRProcedure.getCategory().getId();
		String system = FHIRProcedure.getCategory().getCodingFirstRep().getSystem() +"/version/"
				+FHIRProcedure.getCategory().getCodingFirstRep().getVersion();
		IRI originaleTypeCode=getIRIforCodeAndSystem(code, system);
		int quantity= 1;
		List<CodeableConcept> resull = FHIRProcedure.getUsedCode();
		for (CodeableConcept co : resull) {
			Device Device= new Device("", co.getId(), validitytime, createtime, modifytime);
			int priorityCode = 1;
			Date startDate =FHIRProcedure.getPerformedPeriod().getStart();
			Date endDate = FHIRProcedure.getPerformedPeriod().getEnd();
			Procedure procd = new Procedure(ProcedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
					priorityCode, validitytime, createtime, modifytime, startDate, endDate, Device);
			setProcedure.add(procd);
		}


		return setProcedure;
	}


	/**
	 * @param procedureID
	 * @param FHIRclaim
	 * @return
	 */
	public static Set<prescriptomeCore.Procedure> getProcedureFromFHIR(String procedureID, Claim FHIRclaim){
		Set<prescriptomeCore.Procedure> setProcedure = new HashSet<Procedure>();

		String providerID = FHIRclaim.getCareTeamFirstRep().getProviderTarget().getId();


		String patientID = FHIRclaim.getPatientTarget().getId(); 
		//Date reportedDate = FHIRclaim.getCreated();
		List<ProcedureComponent> getProcedureList  = FHIRclaim.getProcedure();
		Date validitytime = FHIRclaim.getBillablePeriod().getEnd(); 
		Date createtime = FHIRclaim.getBillablePeriod().getStart(); 
		Date modifytime= FHIRclaim.getBillablePeriod().getStart(); 

		String encounterID = "";
		String FacilityID = FHIRclaim.getFacilityTarget().getId();
		Encounter encounter = new Encounter(encounterID, providerID, patientID, FacilityID, validitytime, createtime, modifytime);

		for(ProcedureComponent a : getProcedureList) {

			String ProcedureID=procedureID+a.getSequence();
			String code = a.getTypeFirstRep().getCodingFirstRep().getCode();;
			String system = a.getTypeFirstRep().getCodingFirstRep().getSystem()+"/version/"
					+a.getTypeFirstRep().getCodingFirstRep().getVersion();
			IRI typeCode = null;

			String codeOr = a.getProcedureCodeableConcept().getCodingFirstRep().getCode();;
			String systemOr = a.getProcedureCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
					+a.getProcedureCodeableConcept().getCodingFirstRep().getVersion();
			IRI originaleTypeCode =getIRIforCodeAndSystem(code, system);
			IRI informationSourceTypeCode=getIRIforCodeAndSystem(codeOr, systemOr);

			Date procedureDate = a.getDate();

			int quantity= 1;



			int priorityCode = a.getSequence();
			Date startDate =null;
			Date endDate = null;
			Device Device= null;
			Procedure procd = new Procedure(ProcedureID, encounter, typeCode, originaleTypeCode, procedureDate, quantity, informationSourceTypeCode,
					priorityCode, validitytime, createtime, modifytime, startDate, endDate, Device);
			setProcedure.add(procd);
		}

		return setProcedure;
	}









	/**
	 * @param diagnosisID
	 * @param FHIRclaim
	 * @return
	 */
	public static Set<prescriptomeCore.Diagnosis> getDiagnosisFromFHIR(String diagnosisID, Claim FHIRclaim){
		Set<prescriptomeCore.Diagnosis> setDiag = new HashSet<Diagnosis>();
		String encounterID = FHIRclaim.getFacilityTarget().getId();
		String providerID = FHIRclaim.getCareTeamFirstRep().getProviderTarget().getId();


		String patientID = FHIRclaim.getPatientTarget().getId(); 
		Date reportedDate = FHIRclaim.getCreated();
		List<DiagnosisComponent> getDiagnosisList  = FHIRclaim.getDiagnosis();
		for(DiagnosisComponent a : getDiagnosisList) {
			int priorityAtDischarge = a.getSequence();
			String DiagnosisID=diagnosisID+a.getSequence();
			String code = a.getDiagnosisCodeableConcept().getCodingFirstRep().getCode();
			String system = a.getDiagnosisCodeableConcept().getCodingFirstRep().getSystem()+"/version/"
					+a.getDiagnosisCodeableConcept().getCodingFirstRep().getVersion();
			IRI originalDiagnosisCode=getIRIforCodeAndSystem(code, system);
			boolean presentAtEncounter= false;
			if (a.getOnAdmission().equals("y")) {
				presentAtEncounter= true;
			}
			Date validitytime = FHIRclaim.getBillablePeriod().getEnd(); 
			Date createtime = FHIRclaim.getBillablePeriod().getStart(); 
			Date modifytime= FHIRclaim.getBillablePeriod().getStart(); 
			Diagnosis diag = new Diagnosis(DiagnosisID, originalDiagnosisCode, null, encounterID, providerID, patientID, reportedDate,
					priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
			setDiag.add(diag);
		}


		return setDiag;

	}


	/**
	 * @param diagnosisID
	 * @param encount
	 * @return
	 */
	public static Set<prescriptomeCore.Diagnosis> getDiagnosisFromFHIR(String diagnosisID, org.hl7.fhir.r4.model.Encounter encount){
		Set<prescriptomeCore.Diagnosis>  result = new HashSet<Diagnosis>();
		String DiagnosisID=diagnosisID;
		String encounterID = encount.getId();
		String providerID = "";
		String patientID = encount.getSubjectTarget().getId();
		for (EncounterParticipantComponent a : encount.getParticipant()) {
			providerID =a.getId();
		}
		boolean presentAtEncounter = false;
		Date reportedDate = encount.getPeriod().getEnd(); 
		Date validitytime = null; 
		Date createtime = null; 
		Date modifytime= null; 
		List<org.hl7.fhir.r4.model.Encounter.DiagnosisComponent> diagnos = encount.getDiagnosis();
		for(org.hl7.fhir.r4.model.Encounter.DiagnosisComponent diag : diagnos) {
			String codediag = diag.getUse().getCodingFirstRep().getCode();
			String system  =  diag.getUse().getCodingFirstRep().getSystem()+"/version/"+diag.getUse().getCodingFirstRep().getVersion();

			IRI originalDiagnosisCode =getIRIforCodeAndSystem(codediag, system);
			int priorityAtDischarge =diag.getRank();
			Diagnosis diagd = new Diagnosis(DiagnosisID, originalDiagnosisCode, null, encounterID, providerID, patientID, reportedDate,
					priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
			result.add(diagd);

		}


		return result;

	}

	/**
	 * @param diagnosisID
	 * @param FHIRdiagnosis
	 * @return
	 */
	public static prescriptomeCore.Diagnosis getDiagnosisFromFHIR(String diagnosisID, DiagnosticReport FHIRdiagnosis){
		String DiagnosisID=diagnosisID;
		IRI originalDiagnosisCode=getIRIforCodeAndSystem(FHIRdiagnosis.getCode().getCodingFirstRep().getCode(), FHIRdiagnosis.getCategoryFirstRep().getCodingFirstRep().getSystem()+"+"
				+FHIRdiagnosis.getCategoryFirstRep().getCodingFirstRep().getVersion());
		//IRI diagnosisCode = 
		String encounterID = FHIRdiagnosis.getEncounterTarget().getId();
		String providerID = "";
		for (Resource a : FHIRdiagnosis.getPerformerTarget()) {
			providerID =a.getId();
		}

		String patientID = FHIRdiagnosis.getSubjectTarget().getId();
		Date reportedDate = FHIRdiagnosis.getIssued(); 
		int priorityAtDischarge =0;
		boolean presentAtEncounter = false;

		Date validitytime = FHIRdiagnosis.getEffectivePeriod().getEnd(); 
		Date createtime = FHIRdiagnosis.getEffectiveDateTimeType().getValue(); 
		Date modifytime= FHIRdiagnosis.getEffectivePeriod().getStart(); 
		Diagnosis diag = new Diagnosis(DiagnosisID, originalDiagnosisCode, null, encounterID, providerID, patientID, reportedDate,
				priorityAtDischarge, presentAtEncounter, validitytime, createtime, modifytime);
		return diag;

	}

	/**
	 * @param code
	 * @param terminology
	 * @return
	 */
	public static IRI getIRIforCodeAndSystem(String code, String terminology) {
		String ex = "http://prescriptomeDataModel.ca/";
		String codeAndTerminology = terminology+"/"+code.trim();
		IRI codeIRI = Values.iri(ex,codeAndTerminology);
		return codeIRI;
	}
	/**
	 * @param providerID
	 * @param FHIRPractitioner
	 * @param FHIRPractitionerRole
	 * @return
	 */
	public static prescriptomeCore.Provider getProviderFromFHIR(String providerID, Practitioner FHIRPractitioner, PractitionerRole FHIRPractitionerRole){

		Identifier identifier =FHIRPractitioner.getIdentifierFirstRep();
		HumanName name = FHIRPractitioner.getNameFirstRep();
		AdministrativeGender gender = FHIRPractitioner.getGender();
		Date BirthDate = FHIRPractitioner.getBirthDate();
		Address adress = FHIRPractitioner.getAddressFirstRep();
		CodeableConcept special =FHIRPractitionerRole.getSpecialtyFirstRep();
		Date date = new Date();

		List<StringType> ligneAdressP  =adress.getLine();
		String ligne1AdressP  = "";
		String ligne2AdressP  = "";
		int u =1;
		for(StringType lab : ligneAdressP ) {
			if(u==1) {
				ligne1AdressP  = lab.asStringValue();
			}
			else if(u==2) {
				ligne2AdressP  = lab.asStringValue();
			}
		}

		String AdressID = adress.getId();
		String CityAdressP =adress.getCity();
		String StateAdressP  =adress.getState();
		String ZipCodeAdressP =adress.getPostalCode();
		String CountryAdressP  =adress.getCountry();

		Date ValiditytimeAdressP = adress.getPeriod().getEnd();
		boolean ValidityAdressP  = false;
		if(date.compareTo(adress.getPeriod().getEnd())<1) {
			ValidityAdressP  = true;
		}
		else {
			ValidityAdressP  = false;
		}

		Date CreatetimeAdressP  = adress.getPeriod().getStart();
		Date ModifytimeAdressP  = adress.getPeriod().getStart();


		Adress ProviderAdress = new Adress(AdressID,ligne1AdressP , ligne2AdressP , CityAdressP , StateAdressP , ZipCodeAdressP , CountryAdressP , ValiditytimeAdressP , ValidityAdressP , CreatetimeAdressP , ModifytimeAdressP );

		@SuppressWarnings("deprecation")
		int BirthMonth = BirthDate.getMonth();		 
		@SuppressWarnings("deprecation")
		int BirthYear = BirthDate.getYear();	
		@SuppressWarnings("deprecation")
		int BirthDay = BirthDate.getDay(); 
		String identifierSource = identifier.getId();
		String DataBaseIdentifier= identifier.getValue();
		String genderCode = ""; 
		String SexeCode = ""  ;
		if (gender.toString().equals("Male")||gender.toString().equals("Female")) {
			SexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		String Name = name.getNameAsSingleString();
		Date Validitytime=  date;
		Date Createtime1=  date;
		Date Modifytime=  date;





		Date ModifytimeProvider = identifier.getPeriod().getStart(); 
		Date CreatetimeProvider = identifier.getPeriod().getStart(); 
		Date ValiditytimeProvider = identifier.getPeriod().getEnd(); 
		String ProviderRole = special.getText();
		String ProviderID = providerID;


		String facilityID = FHIRPractitionerRole.getOrganizationTarget().getId();
		String nameFacility = FHIRPractitionerRole.getOrganizationTarget().getName();
		Date validitytimeFacility = FHIRPractitionerRole.getPeriod().getEnd();
		Date	 createtimeFacility = FHIRPractitionerRole.getPeriod().getStart();
		Date	 modifytimeFacility = FHIRPractitionerRole.getPeriod().getStart();

		List<StringType> ligne =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getLine();
		String ligne1 = "";
		String ligne2 = "";
		int i =1;
		for(StringType lab : ligne) {
			if(i==1) {
				ligne1 = lab.asStringValue();
			}
			else if(i==2) {
				ligne2 = lab.asStringValue();
			}
		}

		String AdressIDS = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getId();
		String City=FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getCity();
		String State =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getState();
		String ZipCode=FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPostalCode();
		String Country =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getCountry();

		Date ValiditytimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getEnd();
		boolean ValidityDate = false;
		if(date.compareTo(FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getEnd())<1) {
			ValidityDate = true;
		}
		else {
			ValidityDate = false;
		}

		Date CreatetimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getStart();
		Date ModifytimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getStart();	
		Adress facilityAdress = new Adress(AdressIDS,ligne1, ligne2, City, State, ZipCode, Country, ValiditytimeDate, ValidityDate, CreatetimeDate, ModifytimeDate);
		Facility ProviderFacility = new Facility(facilityID, nameFacility, validitytimeFacility, createtimeFacility, modifytimeFacility, facilityAdress);
		Provider provider = new Provider(ValiditytimeProvider, CreatetimeProvider, ModifytimeProvider, ProviderRole, ProviderID, ProviderFacility, BirthMonth, BirthYear, BirthDay, 
				identifierSource, DataBaseIdentifier, genderCode, "", SexeCode, Name, Validitytime, true, Createtime1, Modifytime, ProviderAdress);
		return provider;

	}



	/**
	 * return FHIR patient into prescriptome Patient
	 * @param FhirPatient
	 * @param patientID
	 * @param CauseDeath
	 * @param Group
	 * @param ethnicID
	 * @return
	 */
	public static prescriptomeCore.Patient getPatientFromFHIR(Patient FhirPatient, String patientID, String CauseDeath, PatientGroup Group ,
			String  ethnicID) {

		Identifier identifier =FhirPatient.getIdentifierFirstRep();
		HumanName name = FhirPatient.getNameFirstRep();
		AdministrativeGender gender = FhirPatient.getGender();
		Date BirthDate = FhirPatient.getBirthDate();
		BooleanType Diceased = FhirPatient.getDeceasedBooleanType();


		Date date = new Date();
		Address adress = FhirPatient.getAddressFirstRep();
		String PatientID = patientID ;
		Date ModifytimePatient = identifier.getPeriod().getStart(); 
		Date CreatetimePatient = identifier.getPeriod().getStart(); 
		Date ValiditytimePatient = identifier.getPeriod().getEnd(); 
		DeathInformation deathInformation = null;
		CauseDeathInformation cause= null;
		boolean DeathIndicator = Diceased.booleanValue();		
		if (Diceased.booleanValue()) {
			DateTimeType Diceaseddate =  FhirPatient.getDeceasedDateTimeType();
			deathInformation = new DeathInformation(CreatetimePatient, ModifytimePatient, Diceaseddate.getValue());
			cause = new CauseDeathInformation(CreatetimePatient, ModifytimePatient, CauseDeath);
		}


		@SuppressWarnings("deprecation")
		int BirthMonth = BirthDate.getMonth();		 
		@SuppressWarnings("deprecation")
		int BirthYear = BirthDate.getYear();	
		@SuppressWarnings("deprecation")
		int BirthDay = BirthDate.getDay(); 
		String identifierSource = identifier.getId();
		String DataBaseIdentifier= identifier.getValue();
		String genderCode = ""; 
		String SexeCode = ""  ;
		String EthnicID=ethnicID; 
		if (gender.toString().equals("Male")||gender.toString().equals("Female")) {
			SexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		String Name = name.getNameAsSingleString();
		Date Validitytime=  date;
		Date Createtime1=  date;
		Date Modifytime=  date;
		List<StringType> ligne =adress.getLine();
		String ligne1 = "";
		String ligne2 = "";
		int i =1;
		for(StringType lab : ligne) {
			if(i==1) {
				ligne1 = lab.asStringValue();
			}
			else if(i==2) {
				ligne2 = lab.asStringValue();
			}
		}


		String City=adress.getCity();
		String State =adress.getState();
		String ZipCode=adress.getPostalCode();
		String Country =adress.getCountry();

		Date ValiditytimeDate = adress.getPeriod().getEnd();
		boolean ValidityDate = false;
		if(date.compareTo(adress.getPeriod().getEnd())<1) {
			ValidityDate = true;
		}
		else {
			ValidityDate = false;
		}

		Date CreatetimeDate = adress.getPeriod().getStart();
		Date ModifytimeDate = adress.getPeriod().getStart();
		String AdressIDS = adress.getId();

		Adress patientAdress = new Adress(AdressIDS,ligne1, ligne2, City, State, ZipCode, Country, ValiditytimeDate, ValidityDate, CreatetimeDate, ModifytimeDate);
		prescriptomeCore.Patient patient = new  prescriptomeCore.Patient(PatientID, ModifytimePatient, CreatetimePatient, ValiditytimePatient, 
				deathInformation, cause, Group, BirthMonth, BirthYear, BirthDay, identifierSource, DataBaseIdentifier, genderCode, 
				EthnicID, SexeCode, Name, Validitytime, DeathIndicator, CreatetimeDate, Modifytime,patientAdress);
		patient.setPatientID(PatientID);
		patient.setBirthDay(BirthDay);
		patient.setBirthMonth(BirthMonth);
		patient.setBirthYear(BirthYear);
		patient.setIdentifierSource(identifierSource);
		patient.setDataBaseIdentifier(DataBaseIdentifier);
		patient.setGenderCode(genderCode);
		patient.setEthnicID(EthnicID);
		patient.setAdress(patientAdress);
		patient.setSexeCode(SexeCode);
		patient.setName(Name);
		patient.setValiditytime(Validitytime);
		patient.setDeathIndicator(DeathIndicator);
		patient.setCreatetime(Createtime1);
		patient.setModifytime(Modifytime);
		patient.setDeathInformation(deathInformation);
		patient.setCauseDeathInformation(cause);
		patient.setPatientGroup(Group);
		patient.setValiditytimePatient(ValiditytimePatient);
		patient.setCreatetimePatient(CreatetimePatient);
		patient.setModifytimePatient(ModifytimePatient);


		return patient;

	}
}
