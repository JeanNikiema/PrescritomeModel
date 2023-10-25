package processFHIR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Encounter.DiagnosisComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationStatus;
import org.hl7.fhir.r4.model.Encounter.EncounterStatus;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationAdministration;
import org.hl7.fhir.r4.model.MedicationAdministration.MedicationAdministrationStatus;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Type;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Facility;
import prescriptomeCore.PatientGroup;
import prescriptomeCore.Stay;
import prescriptomeCore.Provider;

public class GetRessource {
	
	ArrayList<prescriptomeCore.Prescription> listPrescriptions ; 	//		Liste des admissions
	ArrayList<prescriptomeCore.Encounter> listEncounters = new ArrayList<prescriptomeCore.Encounter>(); 	// Liste des patients
	ArrayList<prescriptomeCore.Person> listPersons ;
	ArrayList<prescriptomeCore.Patient> listPatients ;
	ArrayList<prescriptomeCore.Observation> listObservations ;
	ArrayList<prescriptomeCore.Observation> listConditions ;
	ArrayList<prescriptomeCore.Observation> listOrganisations ;
	
	
	//	 *************** Read the fhir resource file 
	public Bundle getFile(String fileName) throws FileNotFoundException {
		FhirContext ctxR4 = FhirContext.forR4();
		InputStream inputStream = new FileInputStream(fileName);
		IParser parser = ctxR4.newXmlParser().setPrettyPrint(true);
		Bundle bundle = parser.parseResource(Bundle.class, inputStream);
		return bundle ;
	}
	
	
	public HashMap<String, Resource> mapResource(Bundle bundle) {
		HashMap<String, Resource> resources = new HashMap<String, Resource>();
		for(int i=0; i<bundle.getEntry().size(); i++) {
			//			List<Resource> resourcesList = null; // Liste 
			Resource resource = bundle.getEntry().get(i).getResource();
			String resourceName = bundle.getEntry().get(i).getResource().getClass().getSimpleName() ;
			resources.put(resourceName, resource);
		}
		return resources;
	}
	

	//	 ***************************** Function to get the PatientGroup ********************************
	public prescriptomeCore.PatientGroup getPatientGroup(){
		prescriptomeCore.PatientGroup patientGroup = new prescriptomeCore.PatientGroup() ;
		GregorianCalendar patGroupCreatetime = new GregorianCalendar(2022,06, 12);
		GregorianCalendar patGroupModifytime = new GregorianCalendar(2023,01, 05);

		patientGroup.setDatabaseSource( "MIMIC-IV-v2.2");
		patientGroup.setGroupID("2023-MIT-LCP");
		patientGroup.setCreatetime(patGroupCreatetime.getTime());
		patientGroup.setModifytime(patGroupModifytime.getTime());
		return patientGroup ;
	}
	

	//	 ************* The function to get the person resource **************************
	public prescriptomeCore.Person getPerson(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		Patient pat = (Patient) resources.get("Patient");
		listPersons = new ArrayList<prescriptomeCore.Person>();
		String identifier = pat.getIdentifierFirstRep().getValue();
		String name = pat.getNameFirstRep().getFamily() ;
		AdministrativeGender gender = pat.getGender();
		Date birthDate = pat.getBirthDate();
		Boolean is_deceded = pat.getDeceasedBooleanType().getValue();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(birthDate) ;
		String genderCode = "";
		String SexeCode = ""  ;
		String EthnicID="Unknow";
		
		if (gender.toString().equals("Male") || gender.toString().equals("Female")) {
			SexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}
		
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = calendar.get(Calendar.MONTH) ;
		int birthYear = calendar.get(Calendar.YEAR) ;
		
		// Les admissions des patients en soin 
		Encounter fhirEnc = (Encounter) resources.get("Encounter");
		Date createtime = fhirEnc.getPeriod().getStart(); 
		Date modifytime = fhirEnc.getPeriod().getStart(); 
		Date validitytime = fhirEnc.getPeriod().getEnd(); 
		
		prescriptomeCore.Adress adress = getAdress(bundle);
		prescriptomeCore.Person prescripPers = new prescriptomeCore.Person();

		prescripPers.setBirthMonth(birthMonth);
		prescripPers.setBirthYear(birthYear);
		prescripPers.setBirthDay(birthDay);
		prescripPers.setIdentifierSource("MIMIC-IV-v2.2");
		prescripPers.setDataBaseIdentifier("2023-MIT-LCP");
		prescripPers.setGenderCode(genderCode);
		prescripPers.setEthnicID(EthnicID);
		prescripPers.setSexeCode(SexeCode);
		prescripPers.setName(name);
		prescripPers.setValiditytime(validitytime);
		prescripPers.setDeathIndicator(is_deceded);
		prescripPers.setCreatetime(createtime);
		prescripPers.setModifytime(modifytime);
		prescripPers.setAdress(adress);
		
		listPersons.add(prescripPers);
		return prescripPers ;
	}
	
	
	//	 ************* The function to get the patient resource **************************
	public prescriptomeCore.Patient getPatientFromResourceFile(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		Patient fhirPat = (Patient) resources.get("Patient");
		
		listPatients = new ArrayList<prescriptomeCore.Patient>();
		String identifier = fhirPat.getIdentifierFirstRep().getValue();
		String name = fhirPat.getNameFirstRep().getFamily() ;
		AdministrativeGender gender = fhirPat.getGender();
		Date birthDate = fhirPat.getBirthDate();
		Boolean is_deceded = fhirPat.getDeceasedBooleanType().getValue();
		GregorianCalendar birthDateCalendar = new GregorianCalendar();
		birthDateCalendar.setTime(birthDate) ;
		Date deathDate = birthDateCalendar.getTime() ; // Table patient
		String genderCode = "";
		String SexeCode = "Undefined"  ;
		String EthnicID="Unknow";
		
		if (gender.toString().equals("Male") || gender.toString().equals("Female")) {
			SexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		int birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = birthDateCalendar.get(Calendar.MONTH) ;
		int birthYear = birthDateCalendar.get(Calendar.YEAR) ;

		//   	Les admissions des patients en soin 
		Encounter enc = (Encounter) resources.get("Encounter");
		Date createtime = enc.getPeriod().getStart(); 
		Date modifytime = enc.getPeriod().getStart(); 
		Date validitytime = enc.getPeriod().getEnd(); 
		
		prescriptomeCore.Adress adress = getAdress(bundle);

		// if (is_deceded) {
		// 	// DateTimeType diceasedDate =  pat.getDeceasedDateTimeType();
			Date diceasedDate =  validitytime;
		// 	// deathInformation = new DeathInformation(createtime, modifytime, diceasedDate.getValue());
			DeathInformation deathInformation = new DeathInformation(createtime, modifytime, diceasedDate);
			CauseDeathInformation causeDeathInformation = new CauseDeathInformation(createtime, modifytime, "Unknow");
		// }

		PatientGroup patGroup = getPatientGroup();
		prescriptomeCore.Patient prescripPatient = new prescriptomeCore.Patient();
		prescripPatient.setAdress(adress);
		prescripPatient.setBirthDay(birthDay) ;
		prescripPatient.setBirthMonth(birthMonth) ;
		prescripPatient.setBirthYear(birthYear);
		prescripPatient.setCauseDeathInformation(causeDeathInformation) ;
		prescripPatient.setCreatetime(createtime) ;
		prescripPatient.setCreatetimePatient(createtime) ;
		prescripPatient.setDataBaseIdentifier(identifier) ;
		prescripPatient.setDeathIndicator(is_deceded) ;
		prescripPatient.setDeathInformation(deathInformation);
		prescripPatient.setEthnicID(EthnicID);
		prescripPatient.setGenderCode(genderCode);
		prescripPatient.setIdentifierSource(identifier);
		prescripPatient.setModifytime(modifytime);
		prescripPatient.setModifytimePatient(modifytime);
		prescripPatient.setName(name);
		prescripPatient.setPatientGroup(patGroup);
		prescripPatient.setPatientID(identifier);
		prescripPatient.setSexeCode(SexeCode) ;
		prescripPatient.setValiditytime(validitytime);
		prescripPatient.setValiditytimePatient(validitytime);
		
		listPatients.add(prescripPatient);
		return prescripPatient ;
	}
	
	
	//	 ***************** Function to get encounter resource ******************************
	public ArrayList<prescriptomeCore.Encounter> getEncounterFromResourceFile(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		Encounter fhirEnc = (Encounter) resources.get("Encounter");

		IdType idElement = fhirEnc.getIdElement();
		String encIdentifier = fhirEnc.getIdentifierFirstRep().getValue();
		EncounterStatus status = fhirEnc.getStatus();
		Date createtime = fhirEnc.getPeriod().getStart(); 
		Date modifytime = fhirEnc.getPeriod().getStart(); 
		Date validitytime = fhirEnc.getPeriod().getEnd(); 
		String encLocationRef = fhirEnc.getLocationFirstRep().getLocation().getReference();
		EncounterLocationStatus encLocationStatus = fhirEnc.getLocationFirstRep().getStatus();
		String encLocationPhysicalType = fhirEnc.getLocationFirstRep().getPhysicalType().getCodingFirstRep().getDisplay();
		prescriptomeCore.Provider prov = getProvider(bundle);
		String encServiceProvider = prov.getProviderID();
		String encPartOf = fhirEnc.getPartOf().getReference();
		String encClassCode = fhirEnc.getClass_().getCode();
		String encClassSystem = fhirEnc.getClass_().getSystem();
		String encClassDisplay = fhirEnc.getClass_().getDisplay();
		List<DiagnosisComponent> encDisgnostics = fhirEnc.getDiagnosis();
		String encHospitalizationSystem = fhirEnc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getSystem() ;
		String encHospitalizationCode = fhirEnc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getCode() ;
		String encHospitalizationDisplay = fhirEnc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getDisplay() ;
		
		Patient fhirPat = (Patient) resources.get("Patient");
		String patientID = fhirPat.getIdentifierFirstRep().getValue();
		prescriptomeCore.Encounter prescriptEnc = new prescriptomeCore.Encounter();
		prescriptEnc.setPatientID(patientID) ;
		prescriptEnc.setProviderID(encServiceProvider) ;
		prescriptEnc.setEncounterID(encIdentifier) ;
		
		Location loc = (Location) resources.get("Location");
		String facilityID = loc.getIdentifierFirstRep().getValue();
		prescriptEnc.setFacilityID(facilityID);
		prescriptEnc.setCreatetime(createtime) ;
		prescriptEnc.setModifytime(modifytime) ;	
		validitytime = new Date();
		prescriptEnc.setValiditytime(validitytime);

		listEncounters.add(prescriptEnc); //		Liste des admissions
		
		return listEncounters;
	}
	
	
	//	 ********************** Function to get Organization resource ***************************
	public void getOrganizationFromResource(Bundle bundle) {
		HashMap<String, Resource> resources = mapResource(bundle);
		Organization org = (Organization) resources.get("Organization");
		
		String orgIdentifier = org.getIdentifierFirstRep().getValue();
		String orgType = org.getTypeFirstRep().getCodingFirstRep().getDisplay();
		String orgName = org.getName();
	}
	
	
	// Function to get Condition resource 
	public void getConditionFromResource(Bundle bundle) {
		HashMap<String, Resource> resources = mapResource(bundle);
		Condition cond = (Condition) resources.get("Condition");
		
		String condIdentifier = cond.getIdentifierFirstRep().getValue();
		String condPatient = cond.getSubject().getReference();
		Date recordDate = cond.getRecordedDate();
	}
	
	
	public void getLocationFromResource(Bundle bundle) {
		HashMap<String, Resource> resources = mapResource(bundle);
		Location loc = (Location) resources.get("Location");
		String locIdentifier = loc.getIdentifierFirstRep().getValue();
		String locName = loc.getName();
		String locType = loc.getTypeFirstRep().getCodingFirstRep().getDisplay();
		String locPhysicalType = loc.getPhysicalType().getCodingFirstRep().getDisplay();
		String orgRef = loc.getManagingOrganization().getReference();
	}
	
	
	public void getMedicationFromResource(Bundle bundle) {
		HashMap<String, Resource> resources = mapResource(bundle);
		Medication med = (Medication) resources.get("Medication");
		NarrativeStatus medStatus = med.getText().getStatus();
	}
	
	
	public prescriptomeCore.Observation getObservationFromResource(Bundle bundle) {
		HashMap<String, Resource> resources = mapResource(bundle);
		Observation ob = (Observation) resources.get("Observation");
		List<CanonicalType> profile=ob.getMeta().getProfile();
		String identifierSys = ob.getIdentifierFirstRep().getSystem();
		String observationID = ob.getIdentifierFirstRep().getValue();
		String codingSys = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getSystem();
		String codingCode = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getCode();
		String codingDisplay = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getDisplay();
		String assignerRef = ob.getIdentifierFirstRep().getAssigner().getReference();
		String partOf = ob.getPartOfFirstRep().getReference();
		ObservationStatus status = ob.getStatus();
		String obCode = ob.getCode().getText();
		String patientID = ob.getSubject().getReference();
		Date effectiveDateTime = ob.getEffectiveDateTimeType().getValue();
		BigDecimal valueQuantity = ob.getValueQuantity().getValue();
		String valueQuantityUnit = ob.getValueQuantity().getUnit();
		
		IRI typeCode = Values.iri(identifierSys, "observation");
		IRI originaleTypeCode = Values.iri(identifierSys, "observation");
		IRI informationSourceTypeCode = Values.iri(identifierSys, "observation");
		
		String results = "result1";
		String ex = "http://umontreal.ca";
		IRI resultsSNOMED = Values.iri(ex, "resultsSNOMED");
		int resultsInt = 0;
		IRI resultUNITsource = Values.iri(ex, "resultUNITsource");
		IRI resultUnitUCUM = Values.iri(ex, "resultUnitUCUM");
		
		prescriptomeCore.Encounter prescEnc= listEncounters.get(0);
		Encounter fhirEnc = (Encounter) resources.get("Encounter");
		
		Date createtime = fhirEnc.getPeriod().getStart(); 
		Date modifytime = fhirEnc.getPeriod().getStart(); 
		Date validitytime = fhirEnc.getPeriod().getEnd(); 
		
		prescriptomeCore.Observation  obs = new prescriptomeCore.Observation(observationID, createtime, prescEnc, typeCode, 
				originaleTypeCode, informationSourceTypeCode, results, resultsSNOMED, 
				resultsInt, resultUNITsource, resultUnitUCUM, effectiveDateTime, 
				createtime, validitytime, createtime, modifytime);
		
		//	listObservations.add(obs);
		return obs;
	}
	
	
	public prescriptomeCore.Prescription  getMedicationAdministFromResource(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		
		//		Liste des prescriptions de médicaments
		//		listPrescriptions = new ArrayList<prescriptomeCore.Prescription>();
		
		MedicationAdministration medAdminis = (MedicationAdministration) resources.get("MedicationAdministration");
		
		List<CanonicalType> metaProfil = medAdminis.getMeta().getProfile();
		String idenSystem = medAdminis.getIdentifierFirstRep().getSystem();
		String idenCode = medAdminis.getIdentifierFirstRep().getValue();
		MedicationAdministrationStatus status = medAdminis.getStatus();
		String medicationReference= medAdminis.getMedicationReference().getReference();
		String subject = medAdminis.getSubject().getReference();
		String context=medAdminis.getContext().getReference();
		Date effectivePeriodStart = medAdminis.getEffectivePeriod().getStart();
		Date effectivePeriodEnd = medAdminis.getEffectivePeriod().getEnd();
		String ex2 = "http://umontreal.ca" ;
		IRI drugIDDataSource = Values.iri(ex2, "drugIDDataSource") ;
		IRI drugIDOCRx  = Values.iri(ex2, "drugIDOCRx");
		Patient pat = (Patient) bundle.getEntry().get(0).getResource();
		String patientID = pat.getIdentifierFirstRep().getValue();
		
		prescriptomeCore.Encounter encounter = listEncounters.get(0);
		String encounterID = encounter.getEncounterID();
		String providerID = encounter.getProviderID();
		String fcilityID = encounter.getFacilityID();
		
		Encounter enc = (Encounter) resources.get("Encounter");
		Date createtime = enc.getPeriod().getStart(); 
		Date modifytime = enc.getPeriod().getStart(); 
		Date validitytime = enc.getPeriod().getEnd(); 
		
		Facility facility = getFacility(bundle);
		String facilityID = facility.getFacilityID();
		
		//    	Sejour du patient
		Stay stay = new Stay(
			effectivePeriodStart, effectivePeriodEnd, context,  providerID,  patientID,  facilityID,  validitytime,
			createtime,  modifytime
		);
		Stay stayExposure  =  stay;
		Set<IRI> drugIDTherapeuticIndication = new HashSet<IRI>();
		drugIDTherapeuticIndication.add(Values.iri(ex2, "http://umontreal.ca")) ;
		IRI routeOfAdministrationSource  = Values.iri(ex2, "routeOfAdministrationSource");
		IRI routeOfAdministrationOCRx  = Values.iri(ex2, "routeOfAdministrationOCRx") ;
		IRI encounterUnitOfPresentation  = Values.iri(ex2, "encounterUnitOfPresentation") ;
		
		prescriptomeCore.Prescription  prescription = new prescriptomeCore.Prescription(
				encounterID, providerID, subject, fcilityID,  validitytime,
				effectivePeriodStart,  effectivePeriodEnd,  drugIDDataSource,  drugIDOCRx,  stayExposure,
				drugIDTherapeuticIndication,  routeOfAdministrationSource, routeOfAdministrationOCRx,
					encounterUnitOfPresentation
		);
		// Ajout de la prescription à la liste des prescriptions
		//    	listPrescriptions.add(prescription);
		return prescription ;
	}

	
	// Function to get Adress
	public prescriptomeCore.Adress getAdress(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		Patient pat = (Patient) resources.get("Patient");
		String address = (String) pat.getAddressFirstRep().getLine().get(0).toString();
		Type streetName= pat.getAddressFirstRep().getLine().get(0).getExtension().get(1).getValue();
		Type houseNumber= pat.getAddressFirstRep().getLine().get(0).getExtension().get(1).getValue();
		String city = pat.getAddressFirstRep().getCity();
		String country = pat.getAddressFirstRep().getCountry();
		String postalCode = pat.getAddressFirstRep().getPostalCode();

		//   	Les admissions des patients en soin 
		Encounter enc = (Encounter) resources.get("Encounter");
		Date createtime = enc.getPeriod().getStart(); 
		Date modifytime = enc.getPeriod().getStart(); 
		Date validitytime = enc.getPeriod().getEnd(); 
		
		prescriptomeCore.Adress adress = new prescriptomeCore.Adress(
				postalCode, address, city,"", country, postalCode, country, validitytime, true, createtime, modifytime
		);
		return adress ;
	}
	
	
	//	 ================== Function to get provider/Praticien =====================
	public prescriptomeCore.Provider getProvider(Bundle bundle){
		HashMap<String, Resource> resources = mapResource(bundle);
		Patient pat = (Patient) resources.get("Patient");
		Encounter enc = (Encounter) resources.get("Encounter");
		Date birthDate = pat.getBirthDate();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(birthDate) ;

		Location loc = (Location) resources.get("Location");
		Adress adress = getAdress(bundle);
		//    	Structure de soins Facility ----> Location
		Facility fac = getFacility(bundle) ;

		Date validitytimeProvider = enc.getPeriod().getEnd();
		Date createtimeProvider = enc.getPeriod().getStart(); 
		Date modifytimeProvider = enc.getPeriod().getStart(); 
		String providerRole = "Soignant";
		String providerID = "Prov001";
		Facility providerFacility = fac;
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = calendar.get(Calendar.MONTH) ;
		int birthYear = calendar.get(Calendar.YEAR) ;
		String identifierSource = "2023-MIT-LCP";
		String dataBaseIdentifier = "MIMIC-IV-v2.2";
		String genderCode = "MALE";
		String ethnicID = "Unknow";
		String sexeCode = "M";
		String providerName = loc.getName(); // le nom du praticien
		String name = providerName;
		Date validitytime = enc.getPeriod().getEnd(); ;
		boolean deathIndicator = false;
		Date createtime = enc.getPeriod().getEnd();
		Date modifytime = enc.getPeriod().getEnd();
		
		prescriptomeCore.Provider provider = new prescriptomeCore.Provider(
			validitytimeProvider,createtimeProvider, modifytimeProvider, providerRole,providerID,providerFacility,
			birthMonth, birthYear,birthDay, identifierSource,dataBaseIdentifier,genderCode,ethnicID,sexeCode,
			name,validitytime,deathIndicator, createtime,modifytime,adress
		);
		return provider ;
	}
	
	public prescriptomeCore.Facility getFacility(Bundle bundle){
//    	Structure de soins Facility ----> Location
		HashMap<String, Resource> resources = mapResource(bundle);
		Encounter enc = (Encounter) resources.get("Encounter");
		
		Location loc = (Location) resources.get("Location");
		String facilityID = loc.getIdentifierFirstRep().getValue();
		Adress adress = getAdress(bundle);
		String facilityName = loc.getName();
		Date facilityValiditytimeFacility = enc.getPeriod().getEnd();
		Date facilityCreatetimeFacility = enc.getPeriod().getStart(); 
		Date facilityModifytimeFacility = enc.getPeriod().getStart(); 
		Adress facilityAdress = adress;
		
		Facility fac = new Facility( facilityID,  facilityName,  facilityValiditytimeFacility,
				facilityCreatetimeFacility,  facilityModifytimeFacility,  facilityAdress) ;
		
		return fac ;
	}
	
	
	public prescriptomeCore.Stay getStay(Bundle bundle){
		
		return null ;
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		GetRessource readRes = new GetRessource();
//		Bundle bundle = readRes.getFile("output/bundle7_1_1.xml");
//		
//		readRes.getProvider(bundle) ;
//	}
}
