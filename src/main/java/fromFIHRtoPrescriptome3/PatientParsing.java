package fromFIHRtoPrescriptome3;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.PatientGroup;

public class PatientParsing {
	public prescriptomeCore.Patient getPatientFromResourceFile(String patientID, HashMap<String, Resource> resources) throws ParseException{
		Patient fhirPat = (Patient) resources.get("Patient");
		Encounter fhirEnc = (Encounter) resources.get("Encounter");

		String patientName = fhirPat.getNameFirstRep().getFamily() ;
		AdministrativeGender patientGender = fhirPat.getGender();
		Date patientBirthDate = fhirPat.getBirthDate();
		Boolean is_deceded = fhirPat.getDeceasedBooleanType().getValue();
		
		GregorianCalendar birthDateCalendar = new GregorianCalendar();
		birthDateCalendar.setTime(patientBirthDate) ;
		String genderCode = "undefined";
		String SexeCode = "undefined"  ;
		String EthnicID="undefined";
		
		if (patientGender.toString().equals("MALE") || patientGender.toString().equals("FEMALE")) {
			SexeCode = patientGender.toString() ;
		}
		else {
			genderCode = patientGender.toString() ;
		}

		int birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH) ;
		int birthMonth = birthDateCalendar.get(Calendar.MONTH) ;
		int birthYear = birthDateCalendar.get(Calendar.YEAR) ;
		
		Date date = new Date();
		
		Date modifytimePatient = fhirEnc.getPeriod().getStart();
		Date createtimePatient = fhirEnc.getPeriod().getStart();
		Date validitytimePatient = fhirEnc.getPeriod().getEnd();
		

		
		DeathInformation deathInformation = null;
		CauseDeathInformation causeDeathInformation= null;
		String CauseDeath = "";
		
		Address address = fhirPat.getAddressFirstRep();
		List<StringType> line= address.getLine();
		String ligne1 = "";
		String ligne2 = "unknow";
		ligne1 = line.get(0).asStringValue();
		if(line.size()>1) {
			ligne2 = line.get(1).asStringValue();
		}

		String city = address.getCity();
		String state = address.getCountry();
		String country = address.getCountry();
		String zipCode = address.getPostalCode();

		Date createtimeDate = date; 
		Date modifytimeDate = date ;
		Date validitytimeDate = date ;
		if (address.getPeriod().getStart() != null) {
			createtimeDate = address.getPeriod().getStart(); 
			modifytimeDate = address.getPeriod().getStart();
		}
		
		if (address.getPeriod().getEnd() != null) {
			validitytimeDate = address.getPeriod().getEnd(); 
		}
		
		boolean validityDate = false;
		if(date.compareTo(validitytimeDate)<1) {
			validityDate = true;
		}
		else {
			validityDate = false;
		}
		
		prescriptomeCore.Adress adress = new prescriptomeCore.Adress(
				"P-adr-001", ligne1, ligne2, city, state, zipCode, country, validitytimeDate, 
				validityDate, createtimeDate, modifytimeDate
		);
//		
//		AdressParsing adressParsing = new AdressParsing();
//		prescriptomeCore.Adress adress = adressParsing.getAdress("P-adr-001", resources);

//		 if (is_deceded) {
//			Date diceasedDate =  fhirPat.getDeceasedDateTimeType().getValue();
			Date diceasedDate = date ;
			deathInformation = new DeathInformation(createtimePatient, modifytimePatient, diceasedDate);
			causeDeathInformation = new CauseDeathInformation(createtimePatient, modifytimePatient, CauseDeath);
//		 }
		
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date validitytime=  simpleDateFormat.parse("26/06/2022");
		Date createtime=  simpleDateFormat.parse("22/06/2022");
		Date modifytime=  simpleDateFormat.parse("26/06/2022");

		PatientGroupParsing patGroupPars = new PatientGroupParsing();
		PatientGroup patGroup = patGroupPars.getPatientGroup(resources);
		
		prescriptomeCore.Patient prescripPatient = new prescriptomeCore.Patient();
		prescripPatient.setAdress(adress);
		prescripPatient.setBirthDay(birthDay) ;
		prescripPatient.setBirthMonth(birthMonth) ;
		prescripPatient.setBirthYear(birthYear);
		prescripPatient.setCauseDeathInformation(causeDeathInformation) ;
		prescripPatient.setCreatetime(createtime) ;
		prescripPatient.setCreatetimePatient(createtimePatient) ;
		prescripPatient.setDataBaseIdentifier("MIMIC-IV-v2.2") ; // La provenance de la données
		prescripPatient.setDeathIndicator(is_deceded) ;
		prescripPatient.setDeathInformation(deathInformation) ;
		prescripPatient.setEthnicID(EthnicID);
		prescripPatient.setGenderCode(genderCode);
		prescripPatient.setIdentifierSource("MIMIC-IV-v2.2"+patientID); // id dans la base de données d'origine
		prescripPatient.setModifytime(modifytime);
		prescripPatient.setModifytimePatient(modifytimePatient);
		prescripPatient.setName(patientName);
		prescripPatient.setPatientGroup(patGroup);
		prescripPatient.setPatientID(patientID);
		prescripPatient.setSexeCode(SexeCode) ;
		prescripPatient.setValiditytime(validitytime);
		prescripPatient.setValiditytimePatient(validitytimePatient);
		
		return prescripPatient ;
	}
}
