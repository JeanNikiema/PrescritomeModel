package fromFIHRtoPrescriptome3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.hl7.fhir.r4.model.Resource;
import prescriptomeCore.Adress;
import prescriptomeCore.Facility;
import prescriptomeCore.Provider;

public class ProviderParsing {
	public prescriptomeCore.Provider getProvider(String providerID, HashMap<String, Resource> resources) throws ParseException{
		org.hl7.fhir.r4.model.Practitioner FHIRProvider = (org.hl7.fhir.r4.model.Practitioner) resources.get("Provider");
		String name = "Titi";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date BirthDate = formatter.parse("2022-09-27 13:00");
		
//		Address adress = FHIRProvider.getAddressFirstRep();
		String special ="Doctor";
		Date date = new Date();

		String ligne1AdressP  = "Musterstrasse 2";
		String ligne2AdressP  = "unknow";


		String AdressID = "prov-adr-001";
		String CityAdressP ="Montréal";
		String StateAdressP  ="Ca";
		String ZipCodeAdressP ="15043";
		String CountryAdressP  ="Ca";

		Date ValiditytimeAdressP = date;
		
		Date CreatetimeAdressP  = date;
		Date ModifytimeAdressP  = date;
		
		boolean ValidityAdressP  = false;
		if(date.compareTo(CreatetimeAdressP)<1) {
			ValidityAdressP  = true;
		}
		else {
			ValidityAdressP  = false;
		}

		Adress ProviderAdress = new Adress(AdressID,ligne1AdressP , ligne2AdressP , CityAdressP , StateAdressP , ZipCodeAdressP , CountryAdressP , ValiditytimeAdressP , ValidityAdressP , CreatetimeAdressP , ModifytimeAdressP );

		@SuppressWarnings("deprecation")
		int BirthMonth = BirthDate.getMonth();
		@SuppressWarnings("deprecation")
		int BirthYear = BirthDate.getYear();
		@SuppressWarnings("deprecation")
		int BirthDay = BirthDate.getDay();
		String identifierSource = "mimiciv";
		String DataBaseIdentifier= "P56AFM";
		String genderCode = "FEMALE";
		String SexeCode = ""  ;

		Date Validitytime=  date;
		Date Createtime1=  date;
		Date Modifytime=  date;

		Date ModifytimeProvider = date;
		Date CreatetimeProvider =date;
		Date ValiditytimeProvider = date;
		String ProviderRole = "Doctor";
		String ProviderID ="P56AFM" ;

		String facilityID = "Fac-18173897-452";
		String nameFacility = "IMI-Mimic Hospital";
		Date validitytimeFacility = date;
		Date createtimeFacility = date;
		Date modifytimeFacility = date;


		String ligne1 = "Musterstrasse 3";
		String ligne2 = "unknow";


		String AdressIDS = "fac-adrs-001";
		String City="Paris";
		String State ="FR";
		String ZipCode="14043";
		String Country ="FR";

		Date ValiditytimeDate = date;
		boolean ValidityDate = false;
		if(date.compareTo(ValiditytimeDate)<1) {
			ValidityDate = true;
		}
		else {
			ValidityDate = false;
		}

		Date CreatetimeDate = date;
		Date ModifytimeDate = date;
		Adress facilityAdress = new Adress(AdressIDS,ligne1, ligne2, City, State, ZipCode, Country, ValiditytimeDate, ValidityDate, CreatetimeDate, ModifytimeDate);
		Facility ProviderFacility = new Facility(facilityID, nameFacility, validitytimeFacility, createtimeFacility, modifytimeFacility, facilityAdress);
		
		Provider provider = new Provider(ValiditytimeProvider, CreatetimeProvider, ModifytimeProvider, ProviderRole, ProviderID, ProviderFacility, BirthMonth, BirthYear, BirthDay,
				identifierSource, DataBaseIdentifier, genderCode, "", SexeCode, name, Validitytime, true, Createtime1, Modifytime, ProviderAdress);
		
		return provider;
	}
}
