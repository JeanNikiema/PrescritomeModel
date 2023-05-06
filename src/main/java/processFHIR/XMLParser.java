package processFHIR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParser {
	public NodeList patientList ;
	public NodeList organisationList ;
	public NodeList conditionList ;
	public NodeList locationList ;
	public NodeList encounterList ;
	public NodeList medicationList ;
	public NodeList medicationAdministratorList ;
	

	private File file;
	
    public File getFile() {
		return file;
	}

    public XMLParser(File file) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.file = file;
        
        FileInputStream fileIS = new FileInputStream(getFile());
    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = builderFactory.newDocumentBuilder();
    	Document xmlDocument = builder.parse(fileIS);
    	XPath xPath = XPathFactory.newInstance().newXPath();
    	
    	String patientXPATH = "//Patient/descendant::*";
    	patientList = (NodeList) xPath.compile(patientXPATH).evaluate(xmlDocument, XPathConstants.NODESET);

    	String organisationXPATH = "//Organization/descendant::*";
    	organisationList = (NodeList) xPath.compile(organisationXPATH).evaluate(xmlDocument, XPathConstants.NODESET);
  	
    	String conditionXPATH = "//Condition/descendant::*" ;
    	conditionList = (NodeList) xPath.compile(conditionXPATH).evaluate(xmlDocument, XPathConstants.NODESET);
    	
    	String locationXPATH = "//Location/descendant::*";
    	locationList = (NodeList) xPath.compile(locationXPATH).evaluate(xmlDocument, XPathConstants.NODESET);

    	String encounterXPATH = "//Encounter/descendant::*";
    	encounterList = (NodeList) xPath.compile(encounterXPATH).evaluate(xmlDocument, XPathConstants.NODESET);
    	
    	String medicationXPATH = "//Medication/descendant::*" ;
    	medicationList = (NodeList) xPath.compile(medicationXPATH).evaluate(xmlDocument, XPathConstants.NODESET);

    	String medicationAdministratorXPATH = "//MedicationAdministration/descendant::*" ;
    	medicationAdministratorList = (NodeList) xPath.compile(medicationAdministratorXPATH).evaluate(xmlDocument, XPathConstants.NODESET);

    }
}
