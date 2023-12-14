/**
 * 
 */
package fromFIHRtoPrescriptome2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Adress;
import prescriptomeCore.Facility;
import prescriptomeCore.Provider;

/**
 * @author coulidaou
 *
 */
public class ProviderParsing {

	public Practitioner getPratitionnerFromFhirServer(IGenericClient client ) {
		Practitioner pratitionner = client.read()
				.resource(Practitioner.class)
				.withId("1472")
				.execute();
		
		return pratitionner ;
	}
	
	
	public PractitionerRole getPratitionnerRoleFromFhirServer(IGenericClient client) {
		PractitionerRole pratitionnerRole = client.read()
				.resource(PractitionerRole.class)
				.withId("1135")
				.execute();
		
		return pratitionnerRole ;
	}
	
	
	public  prescriptomeCore.Provider getProviderFromFHIR(String providerID, Practitioner FHIRPractitioner, PractitionerRole FHIRPractitionerRole){
		Identifier identifier =FHIRPractitioner.getIdentifierFirstRep();
		HumanName prividerName = FHIRPractitioner.getNameFirstRep();
		AdministrativeGender gender = FHIRPractitioner.getGender();
		Date birthDate = FHIRPractitioner.getBirthDate();
		Address adress = FHIRPractitioner.getAddressFirstRep();
		//		CodeableConcept special =FHIRPractitionerRole.getSpecialtyFirstRep();
		String special = FHIRPractitionerRole.getCodeFirstRep().getCodingFirstRep().getDisplay();
		Date date = new Date();

		List<StringType> ligneAdressP  = adress.getLine();
		String ligne1AdressP  = ligneAdressP.get(0).getValueAsString();
		String ligne2AdressP  = ligneAdressP.get(1).getValueAsString();

		String adressID = adress.getPostalCode();
		if(adressID==null) {
			adressID="PROV-ADRS_001";
		}
		String cityAdressP =adress.getCity();
		String stateAdressP  =adress.getState();
		if(stateAdressP == null) {
			stateAdressP = "Unknow";
		}
		String zipCodeAdressP =adress.getPostalCode();
		if(zipCodeAdressP==null) {
			zipCodeAdressP = "Unknow";
		}
		String countryAdressP  =adress.getCountry();
		
		Date validitytimeAdressP = adress.getPeriod().getEnd();
		if(validitytimeAdressP==null) {
			validitytimeAdressP=date;
		}
		boolean validityAdressP  = false;
		if(date.compareTo(validitytimeAdressP)<1) {
			validityAdressP  = true;
		}
		else {
			validityAdressP  = false;
		}
		


		Date createtimeAdressP  = adress.getPeriod().getStart();
		if(createtimeAdressP==null) {
			createtimeAdressP=date;
		}
		Date modifytimeAdressP  = adress.getPeriod().getStart();
		if(modifytimeAdressP==null) {
			modifytimeAdressP=date;
		}

		GregorianCalendar birthDateCalendar = new GregorianCalendar();
		
		if(birthDate==null) {
			birthDate = date ;
		}
		
		birthDateCalendar.setTime(birthDate) ;
		
		int birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = birthDateCalendar.get(Calendar.MONTH) ;
		int birthYear = birthDateCalendar.get(Calendar.YEAR) ;
		
		String identifierSource = identifier.getId();
		if(identifierSource==null) {
			identifierSource="1472";
		}
		String dataBaseIdentifier= identifier.getValue();
		if(dataBaseIdentifier==null) {
			dataBaseIdentifier="1472";
		}
		String genderCode = "";
		String sexeCode = ""  ;
		
		if (gender.toString().equals("male")||gender.toString().equals("female")) {
			sexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		String name = prividerName.getText();
		if(name==null) {
			name="Unknow";
		}
		
		Date validitytime=  date;
		Date createtime=  date;
		Date modifytime=  date;

		Date modifytimeProvider = identifier.getPeriod().getStart();
		if(modifytimeProvider==null) {
			modifytimeProvider=date;
		}
		Date createtimeProvider = identifier.getPeriod().getStart();
		if(createtimeProvider==null) {
			createtimeProvider=date;
		}
		Date validitytimeProvider = identifier.getPeriod().getEnd();
		if(validitytimeProvider==null) {
			validitytimeProvider=date;
		}
	//		String providerRole = special.getText();
		String providerRole = special;

		String facilityID = FHIRPractitionerRole.getOrganization().getReference();
		facilityID = facilityID.split("/")[1];

		String nameFacility = FHIRPractitionerRole.getOrganizationTarget().getName();
		if(nameFacility==null) {
			nameFacility="Samson Skincare Ltd";
		}
		Date validitytimeFacility = FHIRPractitionerRole.getPeriod().getEnd();
		if(validitytimeFacility==null) {
			validitytimeFacility=date;
		}
		Date	 createtimeFacility = FHIRPractitionerRole.getPeriod().getStart();
		if(createtimeFacility==null) {
			createtimeFacility=date;
		}
		Date	 modifytimeFacility = FHIRPractitionerRole.getPeriod().getStart();
		if(modifytimeFacility==null) {
			modifytimeFacility=date;
		}

		List<StringType> ligne =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getLine();
		String ligne1 = "R-10 CALLE ESMERALDA";
		String ligne2 = "CARDEMAR SHOPPING CENTER";
		int i =1;
		for(StringType lab : ligne) {
			if(i==1) {
				ligne1 = lab.asStringValue();
			}
			else if(i==2) {
				ligne2 = lab.asStringValue();
			}
		}

		String adressIDS = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPostalCode();
		if(adressIDS==null) {
			adressIDS="7372079";
		}
		String city=FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getCity();
		if(city == null) {
			city="CAYEY";
		}
		String state =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getState();
		if(state == null) {
			state="PR";
		}
		String zipCode=FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPostalCode();
		if(zipCode==null) {
			zipCode="7372079";
		}
		String country =FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getCountry();
		if(country==null) {
			country="US";
		}

		Date validitytimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getEnd();
		if(validitytimeDate==null) {
			validitytimeDate=date;
		}
		boolean validityDate = false;
		if(date.compareTo(validitytimeDate)<1) {
			validityDate = true;
		}
		else {
			validityDate = false;
		}

		Date createtimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getStart();
		if(createtimeDate==null) {
			createtimeDate=date;
		}
		Date modifytimeDate = FHIRPractitionerRole.getOrganizationTarget().getAddressFirstRep().getPeriod().getStart();
		if(modifytimeDate==null) {
			modifytimeDate=date;
		}
		
		Adress providerAdress = new Adress(adressID,ligne1AdressP , ligne2AdressP , cityAdressP , stateAdressP , zipCodeAdressP , countryAdressP , validitytimeAdressP , validityAdressP , createtimeAdressP , modifytimeAdressP );
		Adress facilityAdress = new Adress(adressIDS,ligne1, ligne2, city, state, zipCode, country, validitytimeDate, validityDate, createtimeDate, modifytimeDate);
		Facility ProviderFacility = new Facility(facilityID, nameFacility, validitytimeFacility, createtimeFacility, modifytimeFacility, facilityAdress);
		Provider provider = new Provider(validitytimeProvider, createtimeProvider, modifytimeProvider, providerRole, providerID, ProviderFacility, birthMonth, birthYear, birthDay,
				identifierSource, dataBaseIdentifier, genderCode, "", sexeCode, name, validitytime, true, createtime, modifytime, providerAdress);
		
		return provider;

	}

}
