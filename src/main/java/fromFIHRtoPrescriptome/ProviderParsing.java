/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import prescriptomeCore.Adress;
import prescriptomeCore.Facility;
import prescriptomeCore.Provider;

/**
 * @author coulidaou
 *
 */
public class ProviderParsing {

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

}
