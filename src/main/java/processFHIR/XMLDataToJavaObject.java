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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Encounter;
import prescriptomeCore.Patient;

public class XMLDataToJavaObject {
    SimpleDateFormat simpleDateFormter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    
	public Adress adress ;
	public CauseDeathInformation causeDeathInformation ;
	public DeathInformation deathInformation ;
	public Patient patient ;
	public Encounter encounter ;
	public prescriptomeCore.Prescription medication ;

	public XMLDataToJavaObject () throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, ParseException {
		File file = new File("output/bundle7_1_1.xml");
		XMLParser parser = new XMLParser(file) ;
		
		NodeList patientList = parser.patientList ;
		NodeList organisationList = parser.organisationList ;
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
    		Node node = (Node) patientList.item(i) ;
    		Element elementPat = (Element) node ;
    		switch(i) {
    		case 6:
    			family = node.getTextContent() ;
    		  case 11:
    			  identifiant = node.getTextContent() ;
    		    break;
    		  case 15:
    			  rue = node.getTextContent() ;
    			 break;
    		  case 17:
    			  city = node.getTextContent() ;
    		    break;
    		  case 18:
    			  country = node.getTextContent() ;
    			 break;
    		  case 37:
    			  gender = elementPat.getAttribute("value");
    		  case 40:
    			  birthDate = patientList.item(40).getTextContent();
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
    		
//    		System.out.println("("+i+") "+organisationList.item(i).getNodeName()) ;
//    		System.out.println(elementOrg.getAttribute("value")) ;
    	}
    	

    	
//    	System.out.println("\n********************* conditionList **********************") ;
    	for(int i=0; i<conditionList.getLength(); i++) {
    		Node node = conditionList.item(i);
    		Element condElem = (Element) node ;

//    		System.out.println("("+i+") "+node.getNodeName()) ;
//    		System.out.println(condElem.getAttribute("value"));
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
        
    	
//    	encounter = new Encounter();
//    	encounter.setPatientID(identifiant) ;
//    	encounter.setProviderID("0001") ;
//		encounter.setEncounterID("004") ;
//		encounter.setFacilityID("002");
//		Date validitytime2 = new Date();
//		encounter.setValiditytime(validitytime2);
//    	
//    	for(int i=0; i<encounterList.getLength(); i++) {
//    		Node node = (Node) encounterList.item(i) ;
//    		Element encountElem = (Element) node ;
//    		
//    		if(node.getNodeType()== Node.ELEMENT_NODE) {
//    			
//    			switch(i) {
//    				case 6:
//    					encounter.setCreatetime(simpleDateFormter.parse(encountElem.getAttribute("value"))) ;
//    				break;
//    				case 7:
//    					encounter.setModifytime(simpleDateFormter.parse(encountElem.getAttribute("value"))) ;	
//    				break ;
//    				default:
//    			}
//    		}
//    	}
    	
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
    			
    			startDateAdminis = simpleDateFormter.parse(medicationAdministratorElem.getAttribute("value"));
    			break ;
    		case 14:
    			endDateAdminis = simpleDateFormter.parse(medicationAdministratorElem.getAttribute("value"));
    			default :
    		}
    	}
    	
//		****************** Fournir les données du fichier   **********************

    	GregorianCalendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
    	
    	Date validitytime = calendar.getTime();
    	Date createtime = calendar.getTime();
    	Date modifytime = calendar.getTime();
    	Date deathDate = calendar.getTime() ;
    	
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
		patient.setGenderCode("M");
		patient.setIdentifierSource(identifiant);
		patient.setModifytime(modifytime);
		patient.setModifytimePatient(modifytime);
		patient.setName("Daouda");
		patient.setPatientGroup(null);
		patient.setPatientID("001");
		patient.setSexeCode("M") ;
		patient.setValiditytime(validitytime);
		patient.setValiditytimePatient(validitytime);
		
	}
	
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, ParseException {
		XMLDataToJavaObject xmlLoad = new XMLDataToJavaObject() ;
	}

}
