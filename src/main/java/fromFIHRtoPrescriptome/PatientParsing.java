/**
 * 
 */
package fromFIHRtoPrescriptome;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.PatientGroup;

/**
 * @author coulidaou
 *
 */
public class PatientParsing {


	/**
	 * return FHIR patient into prescriptome Patient
	 * @param FhirPatient
	 * @param patientID
	 * @param CauseDeath
	 * @param Group
	 * @param ethnicID
	 * @return
	 */
	public prescriptomeCore.Patient getPatientFromFHIR(Patient FhirPatient, String patientID, String CauseDeath, PatientGroup Group , String  ethnicID) {

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
		
		if (gender.toString().equals("Male") || gender.toString().equals("Female")) {
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
