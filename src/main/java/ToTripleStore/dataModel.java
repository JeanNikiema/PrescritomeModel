package ToTripleStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;
import org.xml.sax.SAXException;

import prescriptomeCore.Encounter;
import prescriptomeCore.Observation;
import prescriptomeCore.Patient;
import processFHIR.XMLDataToJavaObject;



public class dataModel {

	// eleven properties building
	//1
	private String  HasGroup = "CDMHPresc:has_group";
//	private Literal HasGroupNameEN =  Values.literal("Has Group","en");
//	private Literal HasGroupNameFR =  Values.literal("a pour group","fr");

	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasGroup(ModelBuilder builder){
		builder.subject(HasGroup)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("Has Group","en"))
		.add(RDFS.LABEL, Values.literal("a pour group","fr"));
		return builder;
	}

	//2
	private String  HasAdress = "CDMHPresc:has_adress";
//	private Literal HasAdressNameEN =  Values.literal("has adress","en");
//	private Literal HasAdressNameFR =  Values.literal("a pour adresse","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasAdress(ModelBuilder builder){
		builder.subject(HasAdress)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has adress","en"))
		.add(RDFS.LABEL, Values.literal("a pour adresse","fr"));
		return builder;
	}

	//3
	//	private String  HasFacility = "CDMHPresc:fas_facility";
	private String  HasFacility = "CDMHPresc:has_facility";
//	private Literal HasFacilityNameEN =  Values.literal("has facility","en");
//	private Literal HasFacilityNameFR =  Values.literal("a pour compagnie","fr");

	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasFacility(ModelBuilder builder){
		builder.subject("CDMHPresc:has_facility")
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has facility","en"))
		.add(RDFS.LABEL, Values.literal("a pour compagnie","fr"));
		return builder;
	}

	//4
	private String  HasDeathInformation = "CDMHPresc:has_death_information";
//	private Literal HasDeathInformationNameEN =  Values.literal("has adress","en");
//	private Literal HasDeathInformationNameFR =  Values.literal("a pour information sur le décès","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasDeathInformation(ModelBuilder builder){
		builder.subject("CDMHPresc:has_death_information")
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has adress","en"))
		.add(RDFS.LABEL, Values.literal("a pour information sur le décès","fr"));
		return builder;
	}

	//5
	private String  CausedBy = "CDMHPresc:CausedBy";
//	private Literal CausedByNameEN =  Values.literal("caused by","en");
//	private Literal CausedByNameFR =  Values.literal("est causé par","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyCausedBy(ModelBuilder builder){
		builder.subject(CausedBy)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("caused by","en"))
		.add(RDFS.LABEL, Values.literal("est causé par","fr"));
		return builder;
	}

	//6
	private String  HasProcedure = "CDMHPresc:has_procedure";
//	private Literal HasProcedureNameEN =  Values.literal("has procedure","en");
//	private Literal HasProcedureNameFR =  Values.literal("a pour procedure","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasProcedure(ModelBuilder builder){
		builder.subject(HasProcedure)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has procedure","en"))
		.add(RDFS.LABEL, Values.literal("a pour procedure","fr"));
		return builder;
	}

	//7
	private String  HasObservation = "CDMHPresc:has_observation";
//	private Literal HasObservationNameEN =  Values.literal("has observation","en");
//	private Literal HasObservationNameFR =  Values.literal("a pour observation","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasObservation(ModelBuilder builder){
		builder.subject(HasObservation)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has observation","en"))
		.add(RDFS.LABEL, Values.literal("a pour observation","fr"));
		return builder;
	}

	//8
	private String  HasDiagnosis = "CDMHPresc:has_diagnosis";
//	private Literal HasDiagnosisNameEN =  Values.literal("has diagnosis","en");
//	private Literal HasDiagnosisNameFR =  Values.literal("a pour diagnostic","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasDiagnosis(ModelBuilder builder){
		builder.subject(HasDiagnosis)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has diagnosis","en"))
		.add(RDFS.LABEL, Values.literal("a pour diagnostic","fr"));
		return builder;
	}

	//9
	private String  HasEncounter = "CDMHPresc:has_encounter";
//	private Literal HasEncounterNameEN =  Values.literal("has encounter","en");
//	private Literal HasEncounterNameFR =  Values.literal("a pour contact médical","fr");

	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasEncounter(ModelBuilder builder){
		builder.subject(HasEncounter)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("has encounter","en"))
		.add(RDFS.LABEL, Values.literal("a pour contact médical","fr"));
		return builder;
	}

	//10
	private String  PartOf = "CDMHPresc:part_of";
//	private Literal PartOfNameEN =  Values.literal("Part Of","en");
//	private Literal PartOfNameFR =  Values.literal("est partie de","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyPartOf(ModelBuilder builder){
		builder.subject(PartOf)
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("Part Of","en"))
		.add(RDFS.LABEL, Values.literal("est partie de","fr"));
		return builder;
	}

	//11
//	private String  HasDevice = "CDMHPresc:has_device";
//	private Literal HasDeviceNameEN =  Values.literal("Has Device","en");
//	private Literal HasDeviceNameFR =  Values.literal("A comme outils","fr");
	/**
	 * @param builder
	 * @return
	 */
	public ModelBuilder PropertyHasDevice(ModelBuilder builder){
		builder.subject("CDMHPresc:has_device")
		.add(RDF.TYPE, RDF.PROPERTY)
		.add(RDFS.LABEL, Values.literal("Has Device","en"))
		.add(RDFS.LABEL, Values.literal("A comme outils","fr"));
		return builder;
	}
	
	
	
	// seventeen main concepts building

	//1
	private String Person = "CDMHPresc:100000000";
	private Literal PersonNameEN =  Values.literal("Person","en");
	private Literal PersonNameFR =  Values.literal("Personne","fr");

	public ModelBuilder PersonClass(ModelBuilder builder){
		builder.subject(Person)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, PersonNameFR)
		.add(RDFS.LABEL, PersonNameEN);
		return builder;
	}

	//2
	private String Provider = "CDMHPresc:200000000";
	private Literal ProviderNameEN =  Values.literal("Provider","en");
	private Literal ProviderNameFR =  Values.literal("Praticien","fr");

	public ModelBuilder ProviderClass(ModelBuilder builder){
		builder.subject(Provider)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(HasFacility, Facility)
		.add(HasEncounter, Encounter)
		.add(RDFS.SUBCLASSOF, Person)
		.add(RDFS.LABEL, ProviderNameFR)
		.add(RDFS.LABEL, ProviderNameEN);
		return builder;
	}

	//3
	private String Patient = "CDMHPresc:300000000";
	private Literal PatientNameEN =  Values.literal("Patient","en");
	private Literal PatientNameFR =  Values.literal("Patient","fr");

	public ModelBuilder PatientClass(ModelBuilder builder){
		builder.subject(Patient)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, Person)
		.add(HasGroup, PatientGroup)
		.add(HasAdress, Adress)
		.add(CausedBy, CauseOfDeath)
		.add(HasEncounter, Encounter)
		.add(HasDeathInformation, DeathInformation)
		.add(RDFS.LABEL, PatientNameFR)
		.add(RDFS.LABEL, PatientNameEN);
		return builder;
	}
	//4
	private String PatientGroup = "CDMHPresc:400000000";
	private Literal PatientGroupNameEN =  Values.literal("Patient Group","en");
	private Literal PatientGroupNameFR =  Values.literal("Groupe de Patient","fr");

	public ModelBuilder PatientGroupClass(ModelBuilder builder){
		builder.subject(PatientGroup)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, PatientGroupNameFR)
		.add(RDFS.LABEL, PatientGroupNameEN);
		return builder;
	}

	//5
	private String Facility = "CDMHPresc:500000000";
	private Literal FacilityNameEN =  Values.literal("Facility","en");
	private Literal FacilityNameFR =  Values.literal("Structure de soins","fr");

	public ModelBuilder FacilityClass(ModelBuilder builder){
		builder.subject(Facility)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(HasAdress, Adress)
		.add(RDFS.LABEL, FacilityNameFR)
		.add(RDFS.LABEL, FacilityNameEN);
		return builder;
	}

	//6
	private String Adress = "CDMHPresc:600000000";
	private Literal AdressNameEN =  Values.literal("Adress","en");
	private Literal AdressNameFR =  Values.literal("Adresse","fr");

	public ModelBuilder AdressClass(ModelBuilder builder){
		builder.subject(Adress)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, AdressNameFR)
		.add(RDFS.LABEL, AdressNameEN);
		return builder;
	}

	//7
	private String DeathInformation = "CDMHPresc:700000000";
	private Literal DeathInformationNameEN =  Values.literal("Death Information","en");
	private Literal DeathInformationNameFR =  Values.literal("Information sur le décès","fr");

	public ModelBuilder DeathInformationClass(ModelBuilder builder){
		builder.subject(DeathInformation)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, DeathInformationNameFR)
		.add(RDFS.LABEL, DeathInformationNameEN);
		return builder;
	}

	//8
	private String CauseOfDeath = "CDMHPresc:800000000";
	private Literal CauseOfDeathNameEN =  Values.literal("Cause Of Death","en");
	private Literal CauseOfDeathNameFR =  Values.literal("Cause de décès","fr");

	public ModelBuilder CauseOfDeathClass(ModelBuilder builder){
		builder.subject(CauseOfDeath)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, CauseOfDeathNameFR)
		.add(RDFS.LABEL, CauseOfDeathNameEN);
		return builder;
	}
	
	
	//9
	private String Encounter = "CDMHPresc:900000000";
	private Literal EncounterNameEN =  Values.literal("Encounter","en");
	private Literal EncounterNameFR =  Values.literal("Contact médical","fr");

	public ModelBuilder EncounterClass(ModelBuilder builder){
		builder.subject(Encounter)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(PartOf, Encounter)
		.add(HasProcedure, Procedure)
		.add(HasObservation, Observation)
		.add(HasDiagnosis, Diagnosis)
		.add(RDFS.LABEL, EncounterNameFR)
		.add(RDFS.LABEL, EncounterNameEN);
		return builder;
	}
	
	
	//10
	private String Procedure = "CDMHPresc:1000000000";
	private Literal ProcedureNameEN =  Values.literal("Procedure","en");
	private Literal ProcedureNameFR =  Values.literal("Procédure","fr");

	public ModelBuilder ProcedureClass(ModelBuilder builder){
		builder.subject(Procedure)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, ProcedureNameFR)
		.add(RDFS.LABEL, ProcedureNameEN);
		return builder;
	}
	
	
	//11
	private String Observation = "CDMHPresc:1100000000";
	private Literal ObservationNameEN =  Values.literal("Observation","en");
	private Literal ObservationNameFR =  Values.literal("Observation","fr");

	public ModelBuilder ObservationClass(ModelBuilder builder){
		builder.subject(Observation)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(PartOf, Observation)
		.add(RDFS.LABEL, ObservationNameFR)
		.add(RDFS.LABEL, ObservationNameEN);
		return builder;
	}
	
	
	//12
	private String Diagnosis = "CDMHPresc:1200000000";
	private Literal DiagnosisNameEN =  Values.literal("Diagnosis","en");
	private Literal DiagnosisNameFR =  Values.literal("Diagnostic","fr");

	public ModelBuilder DiagnosisClass(ModelBuilder builder){
		builder.subject(Diagnosis)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL, DiagnosisNameFR)
		.add(RDFS.LABEL, DiagnosisNameEN);
		return builder;
	}
	
	
	//13
	private String Stay = "CDMHPresc:1300000000";
	private Literal StayNameEN =  Values.literal("Stay","en");
	private Literal StayNameFR =  Values.literal("Séjour","fr");

	public ModelBuilder StayClass(ModelBuilder builder){
		builder.subject(Stay)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, Encounter)
		.add(RDFS.LABEL,StayNameFR)
		.add(RDFS.LABEL, StayNameEN);
		return builder;
	}
	
	
	//14
	private String DrugEncounter = "CDMHPresc:1400000000";
	private Literal DrugEncounterNameEN =  Values.literal("Drug Encounter","en");
	private Literal DrugEncounterNameFR =  Values.literal("Contact médicamenteux","fr");

	public ModelBuilder DrugEncounterClass(ModelBuilder builder){
		builder.subject(DrugEncounter)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, Encounter)
		.add(PartOf, Stay)
		.add(RDFS.LABEL, DrugEncounterNameFR)
		.add(RDFS.LABEL, DrugEncounterNameEN);
		return builder;
	}
	
	
	//15
	private String Dispense = "CDMHPresc:1500000000";
	private Literal DispenseNameEN =  Values.literal("Dispense","en");
	private Literal DispenseNameFR =  Values.literal("Distribution","fr");

	public ModelBuilder DispenseClass(ModelBuilder builder){
		builder.subject(Dispense)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, DrugEncounter)
		.add(RDFS.LABEL, DispenseNameFR)
		.add(RDFS.LABEL, DispenseNameEN);
		return builder;
	}
	
	
	//16
	private String Prescription = "CDMHPresc:1600000000";
	private Literal PrescriptionNameEN =  Values.literal("Prescription","en");
	private Literal PrescriptionNameFR =  Values.literal("Prescription","fr");

	public ModelBuilder PrescriptionClass(ModelBuilder builder){
		builder.subject(Prescription)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, DrugEncounter)
		.add(RDFS.LABEL, PrescriptionNameFR)
		.add(RDFS.LABEL, PrescriptionNameEN);
		return builder;
	}
	
	
	//17
	private String DrugAdministration = "CDMHPresc:1700000000";
	private Literal DrugAdministrationNameEN =  Values.literal("Drug Administration","en");
	private Literal DrugAdministrationNameFR =  Values.literal("Administration du médicament","fr");

	public ModelBuilder DrugAdministrationClass(ModelBuilder builder){
		builder.subject(DrugAdministration)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.SUBCLASSOF, DrugEncounter)
		.add(RDFS.LABEL, DrugAdministrationNameFR)
		.add(RDFS.LABEL, DrugAdministrationNameEN);
		return builder;
	}

	
	//18
	private String Device = "CDMHPresc:1800000000";
	private Literal DeviceNameEN =  Values.literal("Device","en");
	private Literal DeviceNameFR =  Values.literal("Appareil","fr");

	public ModelBuilder DeviceClass(ModelBuilder builder){
		builder.subject(Device)
		.add(RDF.TYPE, RDFS.CLASS)
		.add(RDFS.LABEL,DeviceNameFR)
		.add(RDFS.LABEL, DeviceNameEN);
		return builder;
	}

	
	//
	public ModelBuilder MainconceptBuilt(ModelBuilder builder) {
		//1
		builder=PersonClass( builder);
		//2
		builder=ProviderClass( builder);

		//3
		builder=PatientClass( builder);
		//4
		builder=PatientGroupClass( builder);
		//5
		builder=FacilityClass( builder);
		//6
		builder=AdressClass( builder);
		//7
		builder=DeathInformationClass( builder);
		//8
		builder=CauseOfDeathClass( builder);
		//9
		builder=EncounterClass( builder);
		//10
		builder=ProcedureClass( builder);
		//11
		builder=ObservationClass( builder);
		//12
		builder=DiagnosisClass( builder);
		//13
		builder=StayClass( builder);
		//14
		builder=DrugEncounterClass( builder);
		//15
		builder=DispenseClass( builder);
		//16
		builder=PrescriptionClass( builder);
		//17
		builder=DrugAdministrationClass( builder);
		//18
		builder=DeviceClass( builder);
		return builder ;
	}

	public ModelBuilder PropertyBuilt(ModelBuilder builder) {

		//1
		builder=PropertyHasGroup( builder);

		//2
		builder=PropertyHasAdress( builder);
		//3
		builder=PropertyHasFacility( builder);

		//4
		builder=PropertyHasDeathInformation( builder);

		//5
		builder=PropertyCausedBy( builder);

		//6
		builder=PropertyHasProcedure( builder);

		//7
		builder=PropertyHasObservation( builder);

		//8
		builder=PropertyHasDiagnosis( builder);

		//9
		builder=PropertyHasEncounter( builder);

		//10
		builder=PropertyPartOf( builder);
		//11
		builder=PropertyHasDevice( builder);
		return builder ;
	}

	
	public ModelBuilder ClassBuilt(ModelBuilder builder) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, ParseException {
		FromClassToRDF fromClassToRDF = new FromClassToRDF() ;
		
		XMLDataToJavaObject xlmData = new XMLDataToJavaObject() ;

		//1
		// PersonClass
		Patient patient = xlmData.patient ;
		builder=fromClassToRDF.PersonClass(patient, builder);

//		//2
//		prescriptomeCore.Provider provider = new prescriptomeCore.Provider() ;
//		builder=fromClassToRDF.ProviderClass( prescriptomeCore.Provider provider,  builder);
//		//3
//		builder=fromClassToRDF.PatientClass( prescriptomeCore.Patient patient,  builder);
//
//		//4
//		builder=fromClassToRDF.PatientGroupClass( prescriptomeCore.PatientGroup patientGroup,  builder);
//
//		//5
//		builder=fromClassToRDF.FacilityClass(prescriptomeCore.Facility fac,  builder);
//
//		//6
//		builder=fromClassToRDF.AdressClass( prescriptomeCore.Adress adress,  builder);
//
//		//7
//		builder=fromClassToRDF.DeathInformationClass(prescriptomeCore.DeathInformation deahIn, builder);
//
//		//8
//		builder=fromClassToRDF.CauseOfDeathClass(prescriptomeCore.CauseDeathInformation cause,  builder);
//
		//9
		Encounter encounter = xlmData.encounter ;
		builder=fromClassToRDF.EncounterClass(encounter,  builder);
//
//		//10
//		builder=fromClassToRDF.ProcedureClass(prescriptomeCore.Procedure proc,  builder);
		//11
		Observation observation = xlmData.observation;
		builder=fromClassToRDF.ObservationClass( observation, builder);
//		
//		builder=fromClassToRDF.DiagnosisClass( prescriptomeCore.Diagnosis diag,  builder);
//		
//		builder=fromClassToRDF.StayClass( prescriptomeCore.Stay stay,   builder);
//		builder=fromClassToRDF.DrugEncounterClass( prescriptomeCore.DrugEncounter drugEncount,  builder);
//		
//		builder=fromClassToRDF.DispenseClass(prescriptomeCore.Dispense disp,   builder);
//		
//		builder=fromClassToRDF.PrescriptionClass(prescriptomeCore.Prescription drugPrescription,   builder);
//		
//		builder=fromClassToRDF.DrugAdministrationClass( DrugAdministration DrugAdmin,   builder);
//		
//		builder=fromClassToRDF.DeviceClass( prescriptomeCore.DrugAdministration DrugAdmin,   builder);
//		
//		builder=fromClassToRDF.DrugAdministrationClass(prescriptomeCore.Device device,   builder);
		
		return builder ;
	}

	
	
	// constructeur
	public dataModel(String PrescriptomeModelVersion) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, RDFHandlerException, UnsupportedRDFormatException, URISyntaxException, ParseException {
		String prefix = "http://prescriptomeDataModel.ca/";
		ModelBuilder builder = new ModelBuilder();
		builder.setNamespace("CDMHPresc",prefix).namedGraph(prefix+PrescriptomeModelVersion);
		
		builder = MainconceptBuilt(builder);
		builder = PropertyBuilt(builder);
		
		// FromClassToRDF
		builder = ClassBuilt(builder) ;
		
		Model model = builder.build();
		Rio.write(model, new FileOutputStream("./Prescriptome.ttl"), "", RDFFormat.TURTLE);
//		Rio.write(model, System.out, RDFFormat.TURTLE);
	}
	
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, URISyntaxException, ParseException {
		try {
			dataModel model = new dataModel("v1");
		} catch (RDFHandlerException | UnsupportedRDFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
