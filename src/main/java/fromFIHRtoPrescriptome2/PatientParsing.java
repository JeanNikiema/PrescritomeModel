/**
 * 
 */
package fromFIHRtoPrescriptome2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.PatientGroup;

/**
 * @author coulidaou
 *
 */
public class PatientParsing {

	public org.hl7.fhir.r4.model.Patient getPatientFromFhirServer(IGenericClient client) {
		Patient patient = client.read()
				.resource(Patient.class)
				.withId("24739")
				.execute();
		
		return patient ;
	}
	

	public prescriptomeCore.Patient getPatientFromFHIR(Patient FhirPatient, String patientID, String CauseDeath, PatientGroup Group , String  patientEthnicID) {
		Identifier identifier =FhirPatient.getIdentifierFirstRep();
		HumanName patientName = FhirPatient.getNameFirstRep();
		AdministrativeGender gender = FhirPatient.getGender();
		Date birthDate = FhirPatient.getBirthDate();
		DateTimeType diceased = FhirPatient.getDeceasedDateTimeType();

		GregorianCalendar birthDateCalendar = new GregorianCalendar();
		birthDateCalendar.setTime(birthDate) ;
		
		Date date = new Date();
		Date modifytimePatient = date; // Est nécessaire ici puisse que nous enrégistrons déjà les dates d'admission dans Encounter
		Date createtimePatient = date;
		Date validitytimePatient = date;
		
		Date validitytime=  date;
		Date createtime=  date;
		Date modifytime=  date;
		
		if(identifier.getPeriod().getStart() != null) {
			modifytimePatient = identifier.getPeriod().getStart();
			createtimePatient = identifier.getPeriod().getStart();
		}
		if (identifier.getPeriod().getEnd() != null) {
			validitytimePatient = identifier.getPeriod().getEnd();
		}
		DeathInformation deathInformation = null;
		CauseDeathInformation cause= null;
		boolean deathIndicator = false;
		if(diceased != null) {
			deathIndicator = true;
		}

		
//		if (diceased.booleanValue()) {
//			DateTimeType diceaseddate =  FhirPatient.getDeceasedDateTimeType();
			Date diceaseddate = diceased.getValue() ;
			deathInformation = new DeathInformation(createtimePatient, modifytimePatient, diceaseddate);
			cause = new CauseDeathInformation(createtimePatient, modifytimePatient, CauseDeath);
//		}

		int birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = birthDateCalendar.get(Calendar.MONTH) ;
		int birthYear = birthDateCalendar.get(Calendar.YEAR) ;
		
		String identifierSource = identifier.getValue();
		String dataBaseIdentifier= identifier.getValue();
		String genderCode = "";
		String sexeCode = ""  ;
		String ethnicID=patientEthnicID;
		
		if (gender.toString().equals("MALE") || gender.toString().equals("FEMALE")) {
			sexeCode = gender.toString() ;
		}
		else {
			genderCode = gender.toString() ;
		}

		String name = patientName.getNameAsSingleString();
		AdressParsing patientAdress = new AdressParsing();
		Adress adress = patientAdress.getAdress(FhirPatient); // Address of patient
		
		prescriptomeCore.Patient patient = new  prescriptomeCore.Patient();
		patient.setPatientID(patientID);
		patient.setBirthDay(birthDay);
		patient.setBirthMonth(birthMonth);
		patient.setBirthYear(birthYear);
		patient.setIdentifierSource(identifierSource);
		patient.setDataBaseIdentifier(dataBaseIdentifier);
		patient.setGenderCode(genderCode);
		patient.setEthnicID(ethnicID);
		patient.setAdress(adress);
		patient.setSexeCode(sexeCode);
		patient.setName(name);
		patient.setValiditytime(validitytime);
		patient.setDeathIndicator(deathIndicator);
		patient.setCreatetime(createtime);
		patient.setModifytime(modifytime);
		patient.setDeathInformation(deathInformation);
		patient.setCauseDeathInformation(cause);
		patient.setPatientGroup(Group);
		patient.setValiditytimePatient(validitytimePatient);
		patient.setCreatetimePatient(createtimePatient);
		patient.setModifytimePatient(modifytimePatient);

		return patient;
	}
}
