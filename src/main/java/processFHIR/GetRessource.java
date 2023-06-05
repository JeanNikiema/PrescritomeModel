package processFHIR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationAdministration;
import org.hl7.fhir.r4.model.MedicationAdministration.MedicationAdministrationStatus;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ResourceType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

public class GetRessource {
	public Bundle getFile(String fileName) throws FileNotFoundException {
		FhirContext ctxR4 = FhirContext.forR4();
//		String fileName ="output/bundle7_1_1.xml";
		InputStream inputStream = new FileInputStream(fileName);
		IParser parser = ctxR4.newXmlParser().setPrettyPrint(true);
		Bundle bundle = parser.parseResource(Bundle.class, inputStream);
		return bundle ;
	}
	
	public void getOrganizationFromResource(Bundle bundle) {
		ResourceType organization = bundle.getEntry().get(0).getResource().getResourceType().Organization;
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(organization)) {
				Organization org = (Organization) bundle.getEntry().get(i).getResource();
				
				String orgIdentifier = org.getIdentifier().get(0).getValue();
				String orgType = org.getType().get(0).getCoding().get(0).getDisplay();
				String orgName = org.getName();
			}
		}
	}

	public void getConditionFromResource(Bundle bundle) {
		ResourceType condition = bundle.getEntry().get(0).getResource().getResourceType().Condition;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(condition)) {
				System.out.println("\n************** Condition **************************") ;
				Condition cond = (Condition) bundle.getEntry().get(i).getResource();
				
				String condIdentifier = cond.getIdentifier().get(0).getValue();
				String condPatient = cond.getSubject().getReference();
				Date recordDate = cond.getRecordedDate();
				
				System.out.println(condIdentifier);
				System.out.println(condPatient);
				System.out.println(recordDate);
			}
		}
	}
	
	public void getLocationFromResource(Bundle bundle) {
		ResourceType location = bundle.getEntry().get(0).getResource().getResourceType().Location;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(location)) {
				System.out.println("\n************** Location **************************") ;
				Location loc = (Location) bundle.getEntry().get(i).getResource();
				
				String locIdentifier = loc.getIdentifier().get(0).getValue();
				String locName = loc.getName();
				String locType = loc.getType().get(0).getCoding().get(0).getDisplay();
				String locPhysicalType = loc.getPhysicalType().getCoding().get(0).getDisplay();
				String orgRef = loc.getManagingOrganization().getReference();
				
				System.out.println(locIdentifier);
				System.out.println(locName);
				System.out.println(locType);
				System.out.println(locPhysicalType);
				System.out.println(orgRef);
			}
		}
	}
	
	
	
	public void getMedicationFromResource(Bundle bundle) {
		ResourceType medication = bundle.getEntry().get(0).getResource().getResourceType().Medication;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(medication)) {
				Medication med = (Medication) bundle.getEntry().get(i).getResource();
				NarrativeStatus medStatus = med.getText().getStatus();
				
				System.out.println(medStatus);
			}
		}
	}

	public prescriptomeCore.Observation getObservationFromResource(Bundle bundle) {
		ResourceType observation = bundle.getEntry().get(0).getResource().getResourceType().Observation ;
		prescriptomeCore.Observation obs = null ;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(observation)) {
				Observation ob = (Observation) bundle.getEntry().get(i).getResource();
				
				List<CanonicalType> profile=ob.getMeta().getProfile();
				String identifierSys = ob.getIdentifierFirstRep().getSystem();
				String identifierVal = ob.getIdentifierFirstRep().getValue();
				String codingSys = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getSystem();
				String codingCode = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getCode();
				String codingDisplay = ob.getIdentifierFirstRep().getType().getCodingFirstRep().getDisplay();
				String assignerRef = ob.getIdentifierFirstRep().getAssigner().getReference();
				String partOf = ob.getPartOfFirstRep().getReference();
				ObservationStatus status = ob.getStatus();
				String obCode = ob.getCode().getText();
				String obSubject = ob.getSubject().getReference();
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
				
				prescriptomeCore.Encounter encount = new prescriptomeCore.Encounter();
				
				obs = new prescriptomeCore.Observation(identifierVal, createtime, encount, typeCode, originaleTypeCode, informationSourceTypeCode, results, resultsSNOMED, resultsInt, resultUNITsource, resultUnitUCUM, effectiveDateTime, createtime, validitytime, createtime, modifytime);
			}
		}
		
		return obs;
	}
	
	Date validitytime;
	Boolean validity =true;
	Date createtime;
	Date modifytime;
	String encIdentifier;
	
	public prescriptomeCore.Encounter getEncounterFromResourceFile(Bundle bundle){
		ResourceType encounter = bundle.getEntry().get(0).getResource().getResourceType().Encounter;
		prescriptomeCore.Encounter prescriptEnc = new prescriptomeCore.Encounter();
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(encounter)) {
				Encounter enc = (Encounter) bundle.getEntry().get(i).getResource();
				
				String encIdentifier = enc.getIdentifierFirstRep().getValue();
				EncounterStatus status = enc.getStatus();
				createtime = enc.getPeriod().getStart(); 
				modifytime = enc.getPeriod().getStart(); 
				validitytime = enc.getPeriod().getEnd(); 
				String encLocationRef = enc.getLocationFirstRep().getLocation().getReference();
				EncounterLocationStatus encLocationStatus = enc.getLocationFirstRep().getStatus();
				String encLocationPhysicalType = enc.getLocationFirstRep().getPhysicalType().getCodingFirstRep().getDisplay();
				String encServiceProvider = enc.getServiceProvider().getReference();
				String encPartOf = enc.getPartOf().getReference();
				String encClassCode = enc.getClass_().getCode();
				String encClassSystem = enc.getClass_().getSystem();
				String encClassDisplay = enc.getClass_().getDisplay();
				List<DiagnosisComponent> encDisgnostics = enc.getDiagnosis();
				String encHospitalizationSystem = enc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getSystem() ;
				String encHospitalizationCode = enc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getCode() ;
				String encHospitalizationDisplay = enc.getHospitalization().getDischargeDisposition().getCodingFirstRep().getDisplay() ;
				
				Patient pat = (Patient) bundle.getEntry().get(0).getResource();
				String identifier = pat.getIdentifierFirstRep().getValue();
				
				prescriptEnc.setPatientID(identifier) ;
				prescriptEnc.setProviderID(encLocationRef) ;
				prescriptEnc.setEncounterID(encIdentifier) ;
				prescriptEnc.setFacilityID("0001FACIL");
				prescriptEnc.setCreatetime(createtime) ;
				prescriptEnc.setModifytime(modifytime) ;	
				validitytime = new Date();
				prescriptEnc.setValiditytime(validitytime);
			}
		}
		return prescriptEnc ;
	}
	
	public prescriptomeCore.Patient getPatientFromResourceFile(Bundle bundle){
		prescriptomeCore.Patient prescripPatient = new prescriptomeCore.Patient();
		ResourceType fhirPatient = bundle.getEntry().get(0).getResource().getResourceType().Patient ;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(fhirPatient)) {
				Patient pat = (Patient) bundle.getEntry().get(i).getResource();
				
				String identifier = pat.getIdentifierFirstRep().getValue();
				String name = pat.getNameFirstRep().getFamily() ;
				AdministrativeGender gender = pat.getGender();
				Date birthDate = pat.getBirthDate();
				Boolean deceded = pat.getDeceasedBooleanType().getValue();
				String address = (String) pat.getAddressFirstRep().getLine().get(0).toString();
				String city = pat.getAddressFirstRep().getCity();
				String country = pat.getAddressFirstRep().getCountry();
				String postalCode = pat.getAddressFirstRep().getPostalCode();
				String maritalStatus = pat.getMaritalStatus().getCodingFirstRep().getDisplay();
				
				DeathInformation deathInformation = null;
				CauseDeathInformation causeDeathInformation= null;
				
		    	GregorianCalendar calendar = new GregorianCalendar();
		    	calendar.setTime(birthDate) ;
		    	Date deathDate = calendar.getTime() ; // Table patient
		    	
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
		    	
		    	prescriptomeCore.Adress adress = new prescriptomeCore.Adress("000AD1", address, city,"", country, postalCode, country, validitytime, true, createtime, modifytime);
		    	
		    	if (deceded) {
					DateTimeType Diceaseddate =  pat.getDeceasedDateTimeType();
					deathInformation = new DeathInformation(createtime, modifytime, Diceaseddate.getValue());
					causeDeathInformation = new CauseDeathInformation(createtime, modifytime, "Unknow");
				}
	
				prescripPatient.setAdress(adress);
				prescripPatient.setBirthDay(birthDay) ;
				prescripPatient.setBirthMonth(birthMonth) ;
				prescripPatient.setBirthYear(birthYear);
				prescripPatient.setCauseDeathInformation(causeDeathInformation) ;
				prescripPatient.setCreatetime(createtime) ;
				prescripPatient.setCreatetimePatient(createtime) ;
				prescripPatient.setDataBaseIdentifier(identifier) ;
				prescripPatient.setDeathIndicator(false) ;
				prescripPatient.setDeathIndicator(false);
				prescripPatient.setDeathInformation(deathInformation);
				prescripPatient.setEthnicID(EthnicID);
				prescripPatient.setGenderCode(genderCode);
				prescripPatient.setIdentifierSource(identifier);
				prescripPatient.setModifytime(modifytime);
				prescripPatient.setModifytimePatient(modifytime);
				prescripPatient.setName(name);
				prescripPatient.setPatientGroup(null);
				prescripPatient.setPatientID(identifier);
				prescripPatient.setSexeCode(SexeCode) ;
				prescripPatient.setValiditytime(validitytime);
				prescripPatient.setValiditytimePatient(validitytime);
			}
		}
		
		return prescripPatient ;
	}
	
	
	public prescriptomeCore.Prescription  getMedicationAdministFromResource(Bundle bundle){
		prescriptomeCore.Prescription prescription = null ;
		ResourceType medicationAdministration = bundle.getEntry().get(0).getResource().getResourceType().MedicationAdministration;
		
		for(int i =0; i<bundle.getEntry().size(); i++) {
					
			if( bundle.getEntry().get(i).getResource().getResourceType().equals(medicationAdministration)) {
				MedicationAdministration medAdminis = (MedicationAdministration) bundle.getEntry().get(i).getResource();
				
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
		    	
		    	Stay stay = new Stay(effectivePeriodStart, effectivePeriodEnd, context,  "PROV001",  patientID,  "FACIL001",  validitytime,
		    			 createtime,  modifytime);
		    	
		    	Stay stayExposure  =  stay;
		    	Set<IRI> drugIDTherapeuticIndication = new HashSet<IRI>();
		    	drugIDTherapeuticIndication.add(Values.iri(ex2, "http://umontreal.ca")) ;
		    	IRI routeOfAdministrationSource  = Values.iri(ex2, "routeOfAdministrationSource");
		    	IRI routeOfAdministrationOCRx  = Values.iri(ex2, "routeOfAdministrationOCRx") ;
		    	IRI encounterUnitOfPresentation  = Values.iri(ex2, "encounterUnitOfPresentation") ;
				
		    	prescription = new prescriptomeCore.Prescription(
		    			encIdentifier, "PROV001", subject, "FACIL001",  validitytime,
		    			effectivePeriodStart,  effectivePeriodEnd,  drugIDDataSource,  drugIDOCRx,  stayExposure,
		    			drugIDTherapeuticIndication,  routeOfAdministrationSource, routeOfAdministrationOCRx,
		    			 encounterUnitOfPresentation);
			}
		}
		return prescription ;
	}

	
//	public static void main(String[] args) throws FileNotFoundException {
//		GetRessource readRes = new GetRessource();
//		Bundle bundle = readRes.getFile("output/bundle7_1_1.xml");
//		
//		readRes.getPatientFromResourceFile(bundle) ;
//		readRes.getOrganizationFromResource(bundle);
//		readRes.getConditionFromResource(bundle);
//		readRes.getLocationFromResource(bundle);
//		readRes.getEncounterFromResourceFile(bundle);
//		readRes.getMedicationFromResource(bundle);
//		readRes.getMedicationAdministFromResource(bundle);
//		readRes.getObservationFromResource(bundle);
//	}
}