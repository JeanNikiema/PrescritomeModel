package fromFIHRtoPrescriptome2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.DateTimeType;
import prescriptomeCore.PatientGroup;


public class PersonParsing {
	
	public org.hl7.fhir.r4.model.Patient getPersonFromFhirServer(IGenericClient client) {
		Patient patient = client.read()
				.resource(Patient.class)
				.withId("24739")
				.execute();
		
		return patient ;
	}
	
	
	public prescriptomeCore.Person getPersonFromFHIR(Patient FhirPatient, String personID, String CauseDeath, PatientGroup Group , String  patientEthnicID) {
		HumanName personName = FhirPatient.getNameFirstRep();
		AdministrativeGender gender = FhirPatient.getGender();
		Date birthDate = FhirPatient.getBirthDate();
		DateTimeType diceased = FhirPatient.getDeceasedDateTimeType();

		GregorianCalendar birthDateCalendar = new GregorianCalendar();
		birthDateCalendar.setTime(birthDate) ;
		
		Date date = new Date();
		boolean deathIndicator=false;
		if(diceased != null) {
			deathIndicator =true;
		}

		int birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = birthDateCalendar.get(Calendar.MONTH) ;
		int birthYear = birthDateCalendar.get(Calendar.YEAR) ;
		
		String genderCode = "";
		String sexeCode = ""  ;
		String ethnicID=patientEthnicID;
		
		if (gender.toString().equals("MALE") || gender.toString().equals("FEMALE")) {
			sexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		String name = personName.getNameAsSingleString();
		Date validitytime=  date;
		Date createtime=  date;
		Date modifytime=  date;

		AdressParsing adressParsing = new AdressParsing();
		prescriptomeCore.Adress adress = adressParsing.getAdress(FhirPatient);
		String identifierSource=personID;
		String dataBaseIdentifier="MIMIC-IV-v2.2-PERS_"+personID;
		
		prescriptomeCore.Person prescripPers = new prescriptomeCore.Person(
				birthMonth,birthYear,birthDay,identifierSource,dataBaseIdentifier,genderCode,ethnicID,
				 sexeCode,name,validitytime,deathIndicator,createtime,modifytime, adress
		);
		
		return prescripPers ;

	}
}
