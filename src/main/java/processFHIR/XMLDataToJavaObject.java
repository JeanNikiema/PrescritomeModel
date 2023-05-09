package processFHIR;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Encounter;
import prescriptomeCore.Observation;
import prescriptomeCore.Patient;

public class XMLDataToJavaObject {
    SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    
	public Adress adress ;
	public CauseDeathInformation causeDeathInformation ;
	public DeathInformation deathInformation ;
	public Patient patient ;
	public Encounter encounter ;
	public Observation observation;
	public prescriptomeCore.Prescription medication ;

	public XMLDataToJavaObject () throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, ParseException {
		File file = new File("output/bundle7_1_1.xml");
		XMLParser parser = new XMLParser(file) ;
		
		NodeList patientList = parser.patientList ;
		NodeList organisationList = parser.organisationList ;
		NodeList observationList = parser.observationList ;
		NodeList conditionList = parser.conditionList ;
		NodeList locationList = parser.locationList ;
		NodeList encounterList = parser.encounterList ;
		NodeList medicationList = parser.medicationList ;
		NodeList medicationAdministratorList = parser.medicationAdministratorList ;
		
		String family = null ;
    	String identifiant = null;
    	String rue = null ;
    	String city = null  ;
    	String country = null ;
    	String gender = null ;
    	String birthDate =null ;
    	String postalCode = null;
    	String maritalStatus= null ;
    	String location = null ;
    	

    	for(int i=0; i<patientList.getLength(); i++) {
    		Node patientNode = (Node) patientList.item(i) ;
    		Element elementPat = (Element) patientNode ;
    		switch(i) {
    		case 6:
    			family = patientNode.getTextContent() ;
    		  case 11:
    			  identifiant = patientNode.getTextContent() ;
    		    break;
    		  case 15:
    			  rue = patientNode.getTextContent() ;
    			 break;
    		  case 17:
    			  city = patientNode.getTextContent() ;
    		    break;
    		  case 18:
    			  country = patientNode.getTextContent() ;
    			 break;
    		  case 37:
    			  gender = elementPat.getAttribute("value");
    		  case 38:
    			  birthDate = elementPat.getAttribute("value");
    		break;
    		  case 50:
    			  postalCode = elementPat.getAttribute("value") ;
    			  break ;
    		  case 56:
    			  maritalStatus = elementPat.getAttribute("value") ;
    			  break ;
    		  default:
    		    // code block
    		}
    	}
		
//    	System.out.println("\n********************* organisationList **********************") ;
    	String name = null ;
    	for(int i=0; i<organisationList.getLength(); i++) {
    		Node node = (Node) organisationList.item(i) ;
    		Element elementOrg = (Element) node ;
    		
    		switch (i) {
    			case 8:
    				name = elementOrg.getAttribute("value") ;
    				break ;
    				default :
    		}
    	}
    	
    	
    	
//    	System.out.println("\n********************* conditionList **********************") ;
    	for(int i=0; i<conditionList.getLength(); i++) {
    		Node condNnode = conditionList.item(i);
    		Element condElem = (Element) condNnode ;
    	}
    	
    	
//    	System.out.println("\n********************* locationList **********************") ;
    	for(int i=0; i<locationList.getLength(); i++) {
    		Node locNnode = (Node) locationList.item(i) ;
    		Element locElem = (Element) locNnode;
    		switch(i) {
    		case 2:
    			location = locElem.getAttribute("value") ;
    		}
    	}
        
//    	System.out.println("\n********************* encounterList **********************") ;
		String status = null;
		String cretime = null ;
		String modtime = null;
    	String encounterID=null;
    	String providerID=null;
    	String facilityID="0001";
    	Date validitytime=null;
    	
    	for(int i=0; i<encounterList.getLength(); i++) {
    		Node encounterNode = (Node) encounterList.item(i) ;
    		Element encountElem = (Element) encounterNode ;
    		
    		if(encounterNode.getNodeType()== Node.ELEMENT_NODE) {
    			
    			switch(i) {
    			case 0:
    				status = encountElem.getAttribute("value");
    				break;
    				case 28:
    					cretime = encountElem.getAttribute("value");
    				break;
    				case 29:
    					modtime = encountElem.getAttribute("value");
    				break ;
    				case 43:
    					providerID=encountElem.getAttribute("value");
    				case 78:
    					encounterID=encountElem.getAttribute("value");
    				default:
    			}
    		}
    	}
    	
    	encounter = new Encounter();
    	encounter.setPatientID(identifiant) ;
    	encounter.setProviderID(providerID) ;
		encounter.setEncounterID(encounterID) ;
		encounter.setFacilityID("002");
		Date createtime=simpleDateFormater.parse(cretime) ;
		encounter.setCreatetime(createtime) ;
		Date modifytime=simpleDateFormater.parse(modtime);
		encounter.setModifytime(modifytime) ;	
		validitytime = new Date();
		encounter.setValiditytime(validitytime);
    	
		
		// Observation
    	String observationID = null;
    	Date reportDate = null;
//    	prescriptomeCore.Encounter encounter;
    	IRI typeCode = null;
		IRI originaleTypeCode = null;
		IRI informationSourceTypeCode = null; 
		
		String results = "result1";
		IRI resultsSNOMED = Values.iri("http://terminology.hl7.org/CodeSystem/v2-0203", "resultsS");
		int resultsInt = 0;
		IRI resultUNITsource = Values.iri("http://terminology.hl7.org/CodeSystem/v2-0203", "resultUNITsource");
		IRI resultUnitUCUM = Values.iri("http://terminology.hl7.org/CodeSystem/v2-0203", "resultUnitUCUM");
		Date startDate = new Date();
		Date endDate = new Date();
		Date labValiditytime = new Date();
		Date labCreatetime = new Date();
		Date labModifytime = new Date();
    	for(int i=0; i<observationList.getLength(); i++) {
    		Node observationNode = (Node) observationList.item(i) ;
    		Element elementObservation = (Element) observationNode ;
    		
    		switch (i) {
    			case 10:
    				observationID = elementObservation.getAttribute("value") ;
    				break ;
    			case 20:
    				reportDate = simpleDateFormater.parse(elementObservation.getAttribute("value"));
    				break;
    			case 6:
    				String ex = elementObservation.getAttribute("value");
    				typeCode = Values.iri(ex, "observation");
    				originaleTypeCode = Values.iri(elementObservation.getAttribute("value"));
    				informationSourceTypeCode = Values.iri(elementObservation.getAttribute("value"));
    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
//    			case  :
//    				results = elementObservation.getAttribute("value") ;
//    				break;
    				default :
    		}
    		
    		System.out.println("("+i+") "+observationNode.getNodeName()) ;
    		System.out.println(elementObservation.getAttribute("value")) ;
    	}
    	observation = new Observation(
    			 observationID, 
    			 reportDate, 
    			 encounter, 
    			 typeCode,
    			 originaleTypeCode, 
    			 informationSourceTypeCode, 
    			 results, 
    			 resultsSNOMED, 
    			 resultsInt,
    			 resultUNITsource, 
    			 resultUnitUCUM, 
    			 startDate, 
    			 endDate, 
    			 labValiditytime, 
    			 labCreatetime,
    			 labModifytime
    		);
    	
    	
    	String medicAdminisIdentifier = null;
    	String medicAdminisStatus = null;
    	String medicAdminisSubject = null; // le patient ayant reçu la prescription
    	Date startDateAdminis = null ;
    	Date endDateAdminis = null ;
    	
//    	System.out.println("\n********************* medicationAdministratorList **********************") ;
    	for(int i=0; i<medicationAdministratorList.getLength(); i++) {
    		Node medicationAdministratorNode = medicationAdministratorList.item(i) ;
    		Element medicationAdministratorElem = (Element) medicationAdministratorNode ;
    		
    		switch(i) {
    		case 4:
    			medicAdminisIdentifier = medicationAdministratorElem.getAttribute("value") ;
    			break;
    		case 5:
    			medicAdminisStatus = medicationAdministratorElem.getAttribute("value") ;
    			break;
    		case 9:
    			medicAdminisSubject = medicationAdministratorElem.getAttribute("value") ;
    			break;
    		case 13:
    			
    			startDateAdminis = simpleDateFormater.parse(medicationAdministratorElem.getAttribute("value"));
    			break ;
    		case 14:
    			endDateAdminis = simpleDateFormater.parse(medicationAdministratorElem.getAttribute("value"));
    			default :
    		}
    	}
    	
//		****************** Fournir les données du fichier   **********************
//        SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date birthDate2 = simpleDateFormater.parse(birthDate);

    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTime(birthDate2) ;
    	Date deathDate = calendar.getTime() ; // Table patient
    	
    	int birthDay = calendar.get(Calendar.DAY_OF_MONTH) ;
    	int birthMonth = calendar.get(Calendar.MONTH) ;
    	int birthYear = calendar.get(Calendar.YEAR) ;
    	
    	
    	adress = new Adress(identifiant, rue, city, "", country, postalCode, country, validitytime, true, createtime, modifytime);
    	causeDeathInformation = new CauseDeathInformation(createtime, modifytime, "Paludisme" ) ;
    	deathInformation = new DeathInformation(createtime, modifytime, deathDate);
		
    	patient = new Patient() ;
		
		patient.setAdress(adress);
		patient.setBirthDay(birthDay) ;
		patient.setBirthMonth(birthMonth) ;
		patient.setBirthYear(birthYear);
		patient.setCauseDeathInformation(causeDeathInformation) ;
		patient.setCreatetime(createtime) ;
		patient.setCreatetimePatient(createtime) ;
		patient.setDataBaseIdentifier(identifiant) ;
		patient.setDeathIndicator(false) ;
		patient.setDeathIndicator(false);
		patient.setDeathInformation(deathInformation);
		patient.setEthnicID("005");
		patient.setGenderCode(gender);
		patient.setIdentifierSource(identifiant);
		patient.setModifytime(modifytime);
		patient.setModifytimePatient(modifytime);
		patient.setName(family);
		patient.setPatientGroup(null);
		patient.setPatientID(identifiant);
		patient.setSexeCode(gender) ;
		patient.setValiditytime(validitytime);
		patient.setValiditytimePatient(validitytime);
		
	}
	
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, ParseException {
		XMLDataToJavaObject xmlLoad = new XMLDataToJavaObject() ;
	}

}
