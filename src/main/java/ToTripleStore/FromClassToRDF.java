package ToTripleStore;

import java.util.Date;
import java.util.Set;

import org.eclipse.rdf4j.model.BNode;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;

import prescriptomeCore.Adress;
import prescriptomeCore.CauseDeathInformation;
import prescriptomeCore.DeathInformation;
import prescriptomeCore.Device;
import prescriptomeCore.DrugAdministration;
import prescriptomeCore.Facility;
import prescriptomeCore.PatientGroup;
import prescriptomeCore.Prescription;
import prescriptomeCore.Stay;

public class FromClassToRDF {

	// eleven properties building
	//1
	private String  HasGroup = "CDMHPresc:has_group";


	//2
	private String  HasAdress = "CDMHPresc:has_adress";

	//3
	private String  HasFacility = "CDMHPresc:has_facility";


	//4
	private String  HasDeathInformation = "CDMHPresc:has_death_information";


	//5
	private String  CausedBy = "CDMHPresc:CausedBy";


	//6
	private String  HasProcedure = "CDMHPresc:has_procedure";


	//7
	private String  HasObservation = "CDMHPresc:has_observation";


	//8
	private String  HasDiagnosis = "CDMHPresc:has_diagnosis";


	//9
	private String  HasEncounter = "CDMHPresc:has_encounter";


	//10
	private String  PartOf = "CDMHPresc:part_of";


	//11
	private String  HasDevice = "CDMHPresc:has_device";



	private String HasBirthMonth= "CDMHPresc:has_birth_month";
	private String HasBirthYear= "CDMHPresc:has_birth_year";
	private String HasBirthDay= "CDMHPresc:has_birth_day";
	private String HasgenderCode= "CDMHPresc:has_gender_code";
	private String HasEthnicID= "CDMHPresc:has_ethnic_id";
	private String HasSexeCode= "CDMHPresc:has_sexe_code";
	private String HasName= "CDMHPresc:has_name";
	private String HasValiditytime= "CDMHPresc:has_validitytime";
	private String HasDeathIndicator= "CDMHPresc:has_death_indicator";
	private String HasCreatetime= "CDMHPresc:has_createtime";
	private String HasModifytime= "CDMHPresc:has_modifytime";
	private String Hasadress= "CDMHPresc:has_adress";

	private String Hasligne1= "CDMHPresc:has_ligne_1";
	private String Hasligne2= "CDMHPresc:has_ligne_2";
	private String HasCity= "CDMHPresc:has_city";
	private String HasState= "CDMHPresc:has_state";
	private String HasZipCode= "CDMHPresc:has_zip_code";
	private String HasCountry= "CDMHPresc:has_country";
	private String HasValidity= "CDMHPresc:has_validity";

	private String HasProviderRole= "CDMHPresc:has_provider_role";
	private String HasProviderFacility= "CDMHPresc:has_provider_facility";
	private String HasValiditytimeProvider= "CDMHPresc:has_validitytime_provider";
	private String HasCreatetimeProvider= "CDMHPresc:has_createtime_provider";
	private String HasModifytimeProvider= "CDMHPresc:has_modifytime_provider";


	private String HasdeathInformation= "CDMHPresc:has_death_Information";
	private String HascauseDeathInformation= "CDMHPresc:has_cause_Death_information";
	private String HasPatientGroup= "CDMHPresc:has_patient_group";
	private String HasValiditytimePatient= "CDMHPresc:has_validitytime_patient";
	private String HasCreatetimePatient= "CDMHPresc:has_createtime_Patient";
	private String HasModifytimePatient= "CDMHPresc:has_modify_time_Patient";

	private String HasDatabaseSource= "CDMHPresc:has_database_source";
	private String HasGroupID= "CDMHPresc:has_group_id";

	private String HasDeathDate = "CDMHPresc:has_death_date";
	private String CauseOfDeathICD = "CDMHPresc:has_cause_of_death";


	private String HasTypeCode= "CDMHPresc:has_type_code";

	private String HasProcedureDate= "CDMHPresc:has_procedure_date";

	private String HasQuantity= "CDMHPresc:has_quantity";
	private String HasInformationSourceTypeCode= "CDMHPresc:has_information_source_type_code";
	private String HasPriorityCode= "CDMHPresc:has_priority_Code";
	private String HasStartDate= "CDMHPresc:has_start_date";
	private String HasEndDate= "CDMHPresc:has_end_date";

	private String HasReportDate = "CDMHPresc:has_report_date";
	private String HasResults = "CDMHPresc:has_results";
	private String HasResultUnits = "CDMHPresc:has_result_units";

	private String HasOriginalDiagnosisCode= "CDMHPresc:has_original_diagnosis_code";
	private String HasdiagnosisCode= "CDMHPresc:has_diagnosis_code";
	private String HaspriorityAtDischarge= "CDMHPresc:has_priority_at_discharge";
	private String HaspresentAtEncounter= "CDMHPresc:has_present_at_encounter";

	private String HasDrugIDDataSource= "CDMHPresc:has_drug_id_source";
	private String HasDrugIDOCRx= "CDMHPresc:has_drug_id_ocrx";
	private String HasStayExposure= "CDMHPresc:has_stay_exposure";
	private String HasDrugIDTherapeuticIndication= "CDMHPresc:has_therapeutic_indication";
	private String HasrouteOfAdministrationSource= "CDMHPresc:has_route_Of_administration_source";
	private String HasrouteOfAdministrationOCRx= "CDMHPresc:has_route_Of_administration_ocrx";
	private String HasEncounterUnitOfPresentation= "CDMHPresc:has_encounter_unit_of_presentation";

	private String HasDispenseDate= "CDMHPresc:dispense_date";
	private String HasDaySupply= "CDMHPresc:has_day_supply";
	private String HasPrescription= "CDMHPresc:has_drug_prescription";

	private String HasadministrationInstructions  = "CDMHPresc:has_administration_instructions";
	private String HasadministeredDose = "CDMHPresc:has_administered_dose";
	private String HasStop = "CDMHPresc:has_stop";
	private String HasstopReasonSource  = "CDMHPresc:has_stop_reason_source";
	private String HasstopReason = "CDMHPresc:has_stop_reason";
	private String HasDeviceCode="CDMHPresc:has_stop_reason";


	//1
	private String Person = "CDMHPresc:100000000";
	public ModelBuilder PersonClass(prescriptomeCore.Person pers, ModelBuilder builder){

		int BirthMonth=pers.getBirthMonth();
		int BirthYear=pers.getBirthYear();
		int BirthDay=pers.getBirthDay();
		String identifierSource=pers.getIdentifierSource();
		String DataBaseIdentifier=pers.getDataBaseIdentifier();
		String genderCode=pers.getGenderCode();
		String EthnicID=pers.getEthnicID();
		String SexeCode=pers.getSexeCode();
		String Name=pers.getName();
		Date Validitytime=pers.getValiditytime();
		boolean DeathIndicator=pers.isDeathIndicator();
		Date Createtime=pers.getCreatetime();
		Date Modifytime=pers.getModifytime();
		Adress adress=pers.getAdress();

		String AdressID =adress.getAdressID();
		String ligne1=adress.getLigne1();
		String ligne2=adress.getLigne2();
		String City=adress.getCity();
		String State=adress.getState();
		String ZipCode=adress.getZipCode();
		String Country=adress.getCountry();
		Date ValiditytimeAd=adress.getValiditytime();
		boolean Validity=adress.isValidity();
		Date CreatetimeAd=adress.getCreatetime();
		Date ModifytimeAd=adress.getModifytime();


		builder.subject("CDMHPresc:"+DataBaseIdentifier)
		.add(RDF.TYPE, Person)
		.add(HasBirthMonth, BirthMonth)
		.add(HasBirthYear, BirthYear)
		.add(HasBirthDay, BirthDay)
		.add(HasgenderCode, genderCode)
		.add(HasEthnicID, EthnicID)
		.add(HasSexeCode, SexeCode)
		.add(HasName, Name)
		.add(HasValiditytime, Validitytime)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime)
		.add(Hasadress, adress)
		.add(Hasadress, "CDMHPresc:"+adress.getAdressID())
		.add(HasDeathIndicator, DeathIndicator);

		builder.subject("CDMHPresc:"+AdressID)
		.add(RDF.TYPE, Adress)
		.add(Hasligne1, ligne1)
		.add(Hasligne2, ligne2)
		.add(HasCity, City)
		.add(HasState, State)
		.add(HasZipCode, ZipCode)
		.add(HasCountry, Country)
		.add(HasValiditytime, ValiditytimeAd)
		.add(HasCreatetime, CreatetimeAd)
		.add(HasModifytime, ModifytimeAd)
		.add(HasValidity, Validity);

		builder.subject("CDMHPresc:"+identifierSource)
		.add(RDF.TYPE, Person)
		.add(RDFS.SUBCLASSOF, DataBaseIdentifier);
		return builder;
	}

	//2
	private String Provider = "CDMHPresc:200000000";

	public ModelBuilder ProviderClass(prescriptomeCore.Provider prov, ModelBuilder builder){

		Date ValiditytimeProvider= prov.getValiditytimeProvider();
		Date CreatetimeProvider= prov.getCreatetimeProvider();
		Date ModifytimeProvider= prov.getValiditytimeProvider();
		String ProviderRole= prov.getProviderRole();
		String ProviderID= prov.getProviderID();
		Facility ProviderFacility= prov.getProviderFacility();
		int BirthMonth = prov.getBirthMonth();
		int BirthYear= prov.getBirthYear();
		int BirthDay= prov.getBirthDay();
		String identifierSource= prov.getIdentifierSource();
		String DataBaseIdentifier= prov.getDataBaseIdentifier();
		String genderCode= prov.getGenderCode();
		String EthnicID= prov.getEthnicID();
		String SexeCode= prov.getSexeCode();
		String Name= prov.getName();
		Date Validitytime= prov.getValiditytime();
		boolean DeathIndicator= prov.isDeathIndicator();
		Date Createtime= prov.getCreatetime();
		Date Modifytime= prov.getModifytime();
		Adress adress= prov.getAdress();

		builder = FacilityClass(ProviderFacility, builder);

		builder.subject("CDMHPresc:"+ProviderID)
		.add(RDF.TYPE, Provider)
		.add(RDFS.SUBCLASSOF, "CDMHPresc:"+identifierSource)
		.add(HasBirthMonth, BirthMonth)
		.add(HasBirthYear, BirthYear)
		.add(HasBirthDay, BirthDay)
		.add(HasgenderCode, genderCode)
		.add(HasEthnicID, EthnicID)
		.add(HasSexeCode, SexeCode)
		.add(HasName, Name)
		.add(HasValiditytime, Validitytime)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime)
		.add(HasValiditytimeProvider, ValiditytimeProvider)
		.add(HasCreatetimeProvider, CreatetimeProvider)
		.add(HasModifytimeProvider, ModifytimeProvider)
		.add(Hasadress, adress)
		.add(Hasadress, adress.getAdressID())
		.add(HasProviderRole, ProviderRole)
		.add(HasProviderFacility, ProviderFacility)
		.add(HasDeathIndicator, DeathIndicator);


		builder.subject("CDMHPresc:"+DataBaseIdentifier)
		.add(RDF.TYPE, Person)
		.add(HasBirthMonth, BirthMonth)
		.add(HasBirthYear, BirthYear)
		.add(HasBirthDay, BirthDay)
		.add(HasgenderCode, genderCode)
		.add(HasEthnicID, EthnicID)
		.add(HasSexeCode, SexeCode)
		.add(HasName, Name)
		.add(HasValiditytime, Validitytime)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime)
		.add(Hasadress, adress)
		.add(Hasadress, adress.getAdressID())
		.add(HasDeathIndicator, DeathIndicator);

		String AdressID =adress.getAdressID();
		String ligne1=adress.getLigne1();
		String ligne2=adress.getLigne2();
		String City=adress.getCity();
		String State=adress.getState();
		String ZipCode=adress.getZipCode();
		String Country=adress.getCountry();
		Date ValiditytimeAd=adress.getValiditytime();
		boolean Validity=adress.isValidity();
		Date CreatetimeAd=adress.getCreatetime();
		Date ModifytimeAd=adress.getModifytime();

		builder.subject("CDMHPresc:"+AdressID)
		.add(RDF.TYPE, Adress)
		.add(Hasligne1, ligne1)
		.add(Hasligne2, ligne2)
		.add(HasCity, City)
		.add(HasState, State)
		.add(HasZipCode, ZipCode)
		.add(HasCountry, Country)
		.add(HasValiditytime, ValiditytimeAd)
		.add(HasCreatetime, CreatetimeAd)
		.add(HasModifytime, ModifytimeAd)
		.add(HasValidity, Validity);

		builder.subject("CDMHPresc:"+identifierSource)
		.add(RDF.TYPE, Person)
		.add(RDFS.SUBCLASSOF, DataBaseIdentifier);

		return builder;
	}

	
	//3
	private String Patient = "CDMHPresc:300000000";
	public ModelBuilder PatientClass(prescriptomeCore.Patient patient, ModelBuilder builder){
		String PatientID =patient.getPatientID();
		Date ModifytimePatient =patient.getModifytimePatient();
		Date CreatetimePatient =patient.getCreatetimePatient();
		Date ValiditytimePatient =patient.getValiditytimePatient();

		DeathInformation deathInformation =patient.getDeathInformation();
		builder = DeathInformationClass(deathInformation, builder);
		CauseDeathInformation cause =patient.getCauseDeathInformation();
		builder = CauseOfDeathClass(cause, builder);
		PatientGroup Group =patient.getPatientGroup();
		builder = PatientGroupClass(Group, builder);

		int BirthMonth =patient.getBirthMonth();
		int BirthYear =patient.getBirthYear();
		int BirthDay =patient.getBirthDay();
		String identifierSource =patient.getIdentifierSource();
		String DataBaseIdentifier =patient.getDataBaseIdentifier();
		String genderCode =patient.getGenderCode();
		String EthnicID =patient.getEthnicID();
		String SexeCode =patient.getSexeCode();
		String Name =patient.getName();
		Date Validitytime =patient.getValiditytime();
		boolean DeathIndicator =patient.isDeathIndicator();
		Date Createtime =patient.getCreatetime();
		Date Modifytime =patient.getModifytime();
		Adress adress =patient.getAdress();

		builder.subject("CDMHPresc:"+PatientID)
		.add(RDF.TYPE, Patient)
		.add(HasBirthMonth, BirthMonth)
		.add(HasBirthYear, BirthYear)
		.add(HasBirthDay, BirthDay)
		.add(HasgenderCode, genderCode)
		.add(HasEthnicID, EthnicID)
		.add(HasSexeCode, SexeCode)
		.add(HasName, Name)
		.add(HasValiditytime, Validitytime)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime)
		.add(HasValiditytimePatient, ValiditytimePatient)
		.add(HasCreatetimePatient, CreatetimePatient)
		.add(HasModifytimePatient, ModifytimePatient)
		.add(HasdeathInformation, deathInformation)
		.add(HascauseDeathInformation, cause)
		.add(HasPatientGroup, Group)
		.add(HasModifytimePatient, ModifytimePatient)
		.add(Hasadress, adress)
		.add(Hasadress, adress.getAdressID())
		.add(HasDeathIndicator, DeathIndicator);

		builder.subject("CDMHPresc:"+DataBaseIdentifier)
		.add(RDF.TYPE, Person)
		.add(HasBirthMonth, BirthMonth)
		.add(HasBirthYear, BirthYear)
		.add(HasBirthDay, BirthDay)
		.add(HasgenderCode, genderCode)
		.add(HasEthnicID, EthnicID)
		.add(HasSexeCode, SexeCode)
		.add(HasName, Name)
		.add(HasValiditytime, Validitytime)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime)
		.add(Hasadress, adress)
		.add(Hasadress, "CDMHPresc:"+adress.getAdressID())
		.add(HasDeathIndicator, DeathIndicator);

		String AdressID =adress.getAdressID();
		String ligne1=adress.getLigne1();
		String ligne2=adress.getLigne2();
		String City=adress.getCity();
		String State=adress.getState();
		String ZipCode=adress.getZipCode();
		String Country=adress.getCountry();
		Date ValiditytimeAd=adress.getValiditytime();
		boolean Validity=adress.isValidity();
		Date CreatetimeAd=adress.getCreatetime();
		Date ModifytimeAd=adress.getModifytime();

		builder.subject("CDMHPresc:"+AdressID)
		.add(RDF.TYPE, Adress)
		.add(Hasligne1, ligne1)
		.add(Hasligne2, ligne2)
		.add(HasCity, City)
		.add(HasState, State)
		.add(HasZipCode, ZipCode)
		.add(HasCountry, Country)
		.add(HasValiditytime, ValiditytimeAd)
		.add(HasCreatetime, CreatetimeAd)
		.add(HasModifytime, ModifytimeAd)
		.add(HasValidity, Validity);

		builder.subject("CDMHPresc:"+identifierSource)
		.add(RDF.TYPE, Person)
		.add(RDFS.SUBCLASSOF, DataBaseIdentifier);
		return builder;
	}


	//4
	private String PatientGroup = "CDMHPresc:400000000";
	public ModelBuilder PatientGroupClass(PatientGroup group, ModelBuilder builder){
		String DatabaseSource =group.getDatabaseSource();
		String GroupID=group.getGroupID();
		Date Createtime=group.getCreatetime();
		Date Modifytime=group.getModifytime();
		builder.subject("CDMHPresc:"+GroupID)
		.add(RDF.TYPE, PatientGroup)
		.add(HasDatabaseSource, DatabaseSource)
		.add(HasGroupID, GroupID)
		.add(HasCreatetime, Createtime)
		.add(HasModifytime, Modifytime);
		return builder;
	}
	
	//5
	private String Facility = "CDMHPresc:500000000";
	public ModelBuilder FacilityClass(Facility fac, ModelBuilder builder){

		String facilityID = fac.getFacilityID();
		String name =fac.getName();
		Date validitytimeFacility=fac.getValiditytimeFacility();
		Date createtimeFacility=fac.getCreatetimeFacility();
		Date modifytimeFacility = fac.getModifytimeFacility();
		Adress facilityAdress =fac.getFacilityAdress();

		builder=AdressClass(facilityAdress, builder);

		builder.subject("CDMHPresc:"+facilityID)
		.add(RDF.TYPE, Facility)
		.add(HasAdress, Adress)
		.add(HasName, name)
		.add(HasValiditytime, validitytimeFacility)
		.add(HasCreatetime, createtimeFacility)
		.add(HasModifytime, modifytimeFacility);
		return builder;
	}

	//6
	private String Adress = "CDMHPresc:600000000";

	public ModelBuilder AdressClass(Adress adress, ModelBuilder builder){
		String AdressID =adress.getAdressID();
		String ligne1=adress.getLigne1();
		String ligne2=adress.getLigne2();
		String City=adress.getCity();
		String State=adress.getState();
		String ZipCode=adress.getZipCode();
		String Country=adress.getCountry();
		Date ValiditytimeAd=adress.getValiditytime();
		boolean Validity=adress.isValidity();
		Date CreatetimeAd=adress.getCreatetime();
		Date ModifytimeAd=adress.getModifytime();

		builder.subject("CDMHPresc:"+AdressID)
		.add(RDF.TYPE, Adress)
		.add(Hasligne1, ligne1)
		.add(Hasligne2, ligne2)
		.add(HasCity, City)
		.add(HasState, State)
		.add(HasZipCode, ZipCode)
		.add(HasCountry, Country)
		.add(HasValiditytime, ValiditytimeAd)
		.add(HasCreatetime, CreatetimeAd)
		.add(HasModifytime, ModifytimeAd)
		.add(HasValidity, Validity);
		return builder;
	}

	//7
	private String DeathInformation = "CDMHPresc:700000000";

	public ModelBuilder DeathInformationClass(DeathInformation deahIn, ModelBuilder builder){
		Date createtime =deahIn.getCreatetime();
		Date modifytime =deahIn.getModifytime();
		Date deathDate  =deahIn.getDeathDate();
		BNode deahtin = Values.bnode();
		builder.subject(deahtin)
		.add(RDF.TYPE, DeathInformation)
		.add(HasDeathDate, deathDate)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}

	//8
	private String CauseOfDeath = "CDMHPresc:800000000";

	public ModelBuilder CauseOfDeathClass(CauseDeathInformation cause, ModelBuilder builder){
		Date createtime=cause.getCreatetime();
		Date modifytime=cause.getModifytime();
		IRI Cause =cause.getCauseOfDeathICD();
		BNode deathcau = Values.bnode();
		builder.subject(deathcau )
		.add(RDF.TYPE, CauseOfDeath)
		.add(CauseOfDeathICD, Cause)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}


	//9
	private String Encounter = "CDMHPresc:900000000";

	public ModelBuilder EncounterClass(prescriptomeCore.Encounter encou, ModelBuilder builder){
		String encounterID = encou.getEncounterID();
		String providerID = encou.getProviderID();
		String patientID= encou.getPatientID();
		String facilityID = encou.getFacilityID();
		Date validitytime = encou.getValiditytime();
		Date createtime = encou.getCreatetime();
		Date modifytime = encou.getModifytime();


		builder.subject("CDMHPresc:"+encounterID )
		.add(RDF.TYPE, Encounter)
		.add(HasFacility, facilityID)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		return builder;
	}
	
	
	//10
	private String Procedure = "CDMHPresc:1000000000";

	public ModelBuilder ProcedureClass( prescriptomeCore.Procedure proc, ModelBuilder builder){

		String procedureID = proc.getProcedureID();
		prescriptomeCore.Encounter encounter = proc.getEncounter();
		builder = EncounterClass(encounter, builder);
		IRI typeCode = proc.getTypeCode();
		IRI originaleTypeCode=proc.getOriginaleTypeCode();
		Date procedureDate=proc.getProcedureDate();
		int quantity =proc.getQuantity();
		IRI informationSourceTypeCode =proc.getInformationSourceTypeCode();
		int priorityCode = proc.getPriorityCode();
		Date validitytime =proc.getValiditytime();
		Date createtime = proc.getCreatetime();
		Date modifytime = proc.getModifytime();
		Date startDate = proc.getStartDate();
		Date endDate = proc.getEndDate();
		Device Device = proc.getDevice();
		builder = DeviceClass(Device, builder);


		builder.subject( "CDMHPresc:"+ procedureID )
		.add(RDF.TYPE, Procedure)
		.add(HasEncounter, encounter)
		.add(HasTypeCode, typeCode)
		.add(HasTypeCode, originaleTypeCode)
		.add(HasProcedureDate, procedureDate)
		.add(HasQuantity, quantity)
		.add(HasInformationSourceTypeCode, informationSourceTypeCode)
		.add(HasPriorityCode, priorityCode)
		.add(HasStartDate, startDate)
		.add(HasEndDate, endDate)
		.add(HasDevice, Device)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}

	//11
	private String Observation = "CDMHPresc:1100000000";

	public ModelBuilder ObservationClass(prescriptomeCore.Observation obs, ModelBuilder builder){
		String observationID =obs.getObservationID();
		Date reportDate =obs.getReportDate();
		prescriptomeCore.Encounter encounter =obs.getEncounter();
		IRI typeCode = obs.getTypeCode();
		IRI originaleTypeCode = obs.getOriginaleTypeCode();
		IRI informationSourceTypeCode=obs.getInformationSourceTypeCode();
		String results = obs.getResults();
		IRI resultsSNOMED = obs.getResultsSNOMED();
		int resultsInt =obs.getResultsInt();
		IRI resultUNITsource= obs.getResultUNITsource();
		IRI resultUnitUCUM = obs.getResultUnitUCUM();
		Date startDate = obs.getStartDate();
		Date endDate = obs.getEndDate();
		Date validitytime = obs.getValiditytime();
		Date createtime = obs.getCreatetime();
		Date modifytime= obs.getModifytime();

		builder.subject(  "CDMHPresc:"+ observationID)
		.add(RDF.TYPE, Observation)
		.add(HasEncounter, encounter)
		.add(HasTypeCode, typeCode)
		.add(HasTypeCode, originaleTypeCode)
		.add(HasInformationSourceTypeCode, informationSourceTypeCode)
		.add(HasResults, results)
		.add(HasResults, resultsSNOMED)
		.add(HasResults, resultsInt)
		.add(HasResultUnits, resultUNITsource)
		.add(HasResultUnits, resultUnitUCUM)
		.add(HasReportDate, reportDate)
		.add(HasStartDate, startDate)
		.add(HasEndDate, endDate)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}

	//12
	private String Diagnosis = "CDMHPresc:1200000000";
	public ModelBuilder DiagnosisClass(prescriptomeCore.Diagnosis diag, ModelBuilder builder){
		String diagnosisID = diag.getDiagnosisID();
		IRI originalDiagnosisCode = diag.getOriginalDiagnosisCode();
		IRI diagnosisCode = diag.getDiagnosisCode();
		String encounterID = diag.getEncounterID();
		String providerID = diag.getProviderID();
		String patientID = diag.getPatientID();
		Date reportedDate =diag.getReportedDate();
		int priorityAtDischarge = diag.getPriorityAtDischarge();
		boolean presentAtEncounter =diag.isPresentAtEncounter();
		Date validitytime =diag.getValiditytime();
		Date createtime =diag.getCreatetime();
		Date modifytime = diag.getModifytime();


		builder.subject(  "CDMHPresc:"+ diagnosisID)
		.add(RDF.TYPE,Diagnosis)
		.add(HasReportDate, reportedDate)
		.add(HasOriginalDiagnosisCode, originalDiagnosisCode)
		.add(HasdiagnosisCode, diagnosisCode)
		.add(HaspriorityAtDischarge, priorityAtDischarge)
		.add(HaspresentAtEncounter, presentAtEncounter)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);



		builder.subject("CDMHPresc:"+encounterID )
		.add(RDF.TYPE, Encounter)
		.add(HasDiagnosis, "CDMHPresc:"+diagnosisID)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		return builder;
	}

	//13
	private String Stay = "CDMHPresc:1300000000";

	public ModelBuilder StayClass(prescriptomeCore.Stay stay,  ModelBuilder builder){

		Date startDate =stay.getStartDate();
		Date endDate =stay.getEndDate();
		String encounterID =stay.getEncounterID();
		String providerID = stay.getProviderID();
		String patientID = stay.getPatientID();
		String facilityID = stay.getFacilityID();
		Date validitytime =stay.getValiditytime();
		Date createtime = stay.getCreatetime();
		Date modifytime =stay.getModifytime();

		builder.subject( "CDMHPresc:"+encounterID  )
		.add(RDF.TYPE, Stay)
		.add(HasStartDate, startDate)
		.add(HasEndDate, endDate)
		.add(HasFacility, facilityID)
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);


		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		return builder;
	}



	//14
	private String DrugEncounter = "CDMHPresc:1400000000";

	public ModelBuilder DrugEncounterClass(prescriptomeCore.DrugEncounter drugEncount, ModelBuilder builder){

		String encounterID=drugEncount.getEncounterID();
		String providerID = drugEncount.getProviderID();
		String patientID =drugEncount.getPatientID();
		String facilityID = drugEncount.getFacilityID();
		Date validitytime = drugEncount.getValiditytime();
		Date createtime = drugEncount.getCreatetime();
		Date modifytime = drugEncount.getModifytime();
		IRI drugIDDataSource = drugEncount.getDrugIDDataSource();
		IRI drugIDOCRx =drugEncount.getDrugIDOCRx();
		Stay stayExposure = drugEncount.getStayExposure();
		Set<IRI> drugIDTherapeuticIndications = drugEncount.getDrugIDTherapeuticIndication();
		IRI routeOfAdministrationSource = drugEncount.getRouteOfAdministrationSource();
		IRI routeOfAdministrationOCRx = drugEncount.getRouteOfAdministrationOCRx();
		IRI encounterUnitOfPresentation = drugEncount.getEncounterUnitOfPresentation();

		for (IRI drugIDTherapeuticIndication:drugIDTherapeuticIndications ) {
			builder.subject( "CDMHPresc:"+encounterID  )
			.add(RDF.TYPE, DrugEncounter)
			.add(HasDrugIDDataSource, drugIDDataSource)
			.add(HasDrugIDOCRx, drugIDOCRx)
			.add(PartOf, stayExposure)
			.add(HasDrugIDTherapeuticIndication, drugIDTherapeuticIndication)
			.add(HasrouteOfAdministrationSource, routeOfAdministrationSource)
			.add(HasrouteOfAdministrationOCRx, routeOfAdministrationOCRx)
			.add(HasEncounterUnitOfPresentation, encounterUnitOfPresentation)
			.add(HasFacility, facilityID)
			.add(HasValiditytime, validitytime)
			.add(HasCreatetime, createtime)
			.add(HasModifytime, modifytime);
		}

		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		return builder;
	}
	
	//15
	private String Dispense = "CDMHPresc:1500000000";
	public ModelBuilder DispenseClass(prescriptomeCore.Dispense disp, ModelBuilder builder){
		String encounterID =disp.getEncounterID();
		String providerID = disp.getProviderID();
		String patientID = disp.getPatientID();
		String facilityID = disp.getFacilityID();
		Date validitytime = disp.getValiditytime();
		Date createtime =disp.getCreatetime();
		Date modifytime =disp.getModifytime();
		IRI drugIDDataSource = disp.getDrugIDDataSource();
		IRI drugIDOCRx = disp.getDrugIDOCRx();
		Stay stayExposure = disp.getStayExposure();
		Set<IRI> drugIDTherapeuticIndications = disp.getDrugIDTherapeuticIndication();
		IRI routeOfAdministrationSource =disp.getRouteOfAdministrationSource();
		IRI routeOfAdministrationOCRx = disp.getRouteOfAdministrationOCRx();
		IRI encounterUnitOfPresentation = disp.getEncounterUnitOfPresentation();
		int daySupply = disp.getDaysSupply();
		Date dispenseDate = disp.getDispenseDate();
		int quantity = disp.getQuantity();
		Prescription drugPrescription =disp.getDrugPrescription();
		builder = PrescriptionClass(drugPrescription, builder);


		for (IRI drugIDTherapeuticIndication:drugIDTherapeuticIndications ) {
			builder.subject( "CDMHPresc:"+encounterID  )
			.add(RDF.TYPE, Dispense)
			.add(HasDrugIDDataSource, drugIDDataSource)
			.add(HasDrugIDOCRx, drugIDOCRx)
			.add(HasStayExposure, stayExposure)
			.add(HasDrugIDTherapeuticIndication, drugIDTherapeuticIndication)
			.add(HasrouteOfAdministrationSource, routeOfAdministrationSource)
			.add(HasrouteOfAdministrationOCRx, routeOfAdministrationOCRx)
			.add(HasEncounterUnitOfPresentation, encounterUnitOfPresentation)

			.add(HasFacility, facilityID)
			.add(HasDispenseDate, dispenseDate)
			.add(HasDaySupply,daySupply)
			.add(HasPrescription, drugPrescription)
			.add(HasQuantity,quantity)
			.add(HasValiditytime, validitytime)
			.add(HasCreatetime, createtime)
			.add(HasModifytime, modifytime);
		}

		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		
		return builder;
	}

	//16
	private String Prescription = "CDMHPresc:1600000000";
	public ModelBuilder PrescriptionClass(Prescription drugPrescription, ModelBuilder builder){
		String encounterID =drugPrescription.getEncounterID();
		String providerID = drugPrescription.getProviderID();
		String patientID = drugPrescription.getPatientID();
		String facilityID = drugPrescription.getFacilityID();
		Date validitytime = drugPrescription.getValiditytime();
		Date createtime = drugPrescription.getCreatetime();
		Date modifytime = drugPrescription.getModifytime();
		IRI drugIDDataSource = drugPrescription.getDrugIDDataSource();
		IRI drugIDOCRx = drugPrescription.getDrugIDOCRx();
		Stay stayExposure = drugPrescription.getStayExposure();
		Set<IRI> drugIDTherapeuticIndications = drugPrescription.getDrugIDTherapeuticIndication();
		IRI routeOfAdministrationSource = drugPrescription.getRouteOfAdministrationSource();
		IRI routeOfAdministrationOCRx = drugPrescription.getRouteOfAdministrationOCRx();
		IRI encounterUnitOfPresentation = drugPrescription.getEncounterUnitOfPresentation();

		for (IRI drugIDTherapeuticIndication:drugIDTherapeuticIndications ) {
			builder.subject( "CDMHPresc:"+encounterID  )
			.add(RDF.TYPE, Prescription)
			.add(HasDrugIDDataSource, drugIDDataSource)
			.add(HasDrugIDOCRx, drugIDOCRx)
			.add(HasStayExposure, stayExposure)
			.add(HasDrugIDTherapeuticIndication, drugIDTherapeuticIndication)
			.add(HasrouteOfAdministrationSource, routeOfAdministrationSource)
			.add(HasrouteOfAdministrationOCRx, routeOfAdministrationOCRx)
			.add(HasEncounterUnitOfPresentation, encounterUnitOfPresentation)
			.add(HasFacility, facilityID)
			.add(HasPrescription, drugPrescription)
			.add(HasValiditytime, validitytime)
			.add(HasCreatetime, createtime)
			.add(HasModifytime, modifytime);
		}



		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID)
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}
	
	//17
	private String DrugAdministration = "CDMHPresc:1700000000";

	public ModelBuilder DrugAdministrationClass(DrugAdministration DrugAdmin, ModelBuilder builder){
		String encounterID = DrugAdmin.getEncounterID();
		String providerID = DrugAdmin.getProviderID();
		String patientID = DrugAdmin.getPatientID();
		String facilityID = DrugAdmin.getFacilityID();
		Date validitytime = DrugAdmin.getValiditytime();
		Date createtime = DrugAdmin.getCreatetime();
		Date modifytime = DrugAdmin.getModifytime();
		IRI drugIDDataSource = DrugAdmin.getDrugIDDataSource();
		IRI drugIDOCRx = DrugAdmin.getDrugIDOCRx();
		Stay stayExposure = DrugAdmin.getStayExposure();
		Set<IRI> drugIDTherapeuticIndications = DrugAdmin.getDrugIDTherapeuticIndication();
		IRI routeOfAdministrationSource = DrugAdmin.getRouteOfAdministrationSource();
		IRI routeOfAdministrationOCRx = DrugAdmin.getRouteOfAdministrationOCRx();
		IRI encounterUnitOfPresentation = DrugAdmin.getEncounterUnitOfPresentation();

		Date startDate  = DrugAdmin.getStartDate();
		Date endDate = DrugAdmin.getEndDate();
		String administrationInstructions  = DrugAdmin.getAdministrationInstructions();
		int administeredDose  = DrugAdmin.getAdministeredDose();
		prescriptomeCore.Prescription prescription  = DrugAdmin.getPrescription();
		boolean Stop  = DrugAdmin.isStop();
		IRI stopReasonSource  = DrugAdmin.getStopReason();
		IRI stopReason =   DrugAdmin.getStopReason();

		for (IRI drugIDTherapeuticIndication:drugIDTherapeuticIndications ) {
			builder.subject( "CDMHPresc:"+encounterID  )
			.add(RDF.TYPE, DrugAdministration)
			.add(HasDrugIDDataSource, drugIDDataSource)
			.add(HasDrugIDOCRx, drugIDOCRx)
			.add(HasStayExposure, stayExposure)
			.add(HasDrugIDTherapeuticIndication, drugIDTherapeuticIndication)
			.add(HasrouteOfAdministrationSource, routeOfAdministrationSource)
			.add(HasrouteOfAdministrationOCRx, routeOfAdministrationOCRx)
			.add(HasEncounterUnitOfPresentation, encounterUnitOfPresentation)
			.add(HasStartDate, startDate)
			.add(HasEndDate, endDate)
			.add(HasPrescription, prescription)
			.add(HasadministrationInstructions, administrationInstructions)
			.add(HasadministeredDose, administeredDose)
			.add(HasStop, Stop)
			.add(HasstopReasonSource, stopReasonSource)
			.add(HasstopReason, stopReason)


			.add(HasFacility, facilityID)
			.add(HasValiditytime, validitytime)
			.add(HasCreatetime, createtime)
			.add(HasModifytime, modifytime);
		}

		builder.subject("CDMHPresc:"+providerID )
		.add(RDF.TYPE, Provider)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);

		builder.subject("CDMHPresc:"+patientID )
		.add(RDF.TYPE, Patient)
		.add(HasEncounter, "CDMHPresc:"+encounterID )
		.add(HasValiditytime, validitytime)
		.add(HasCreatetime, createtime)
		.add(HasModifytime, modifytime);
		return builder;
	}

	//18
	private String Device = "CDMHPresc:1800000000";

	public ModelBuilder DeviceClass(Device device, ModelBuilder builder){
//		 String DeviceID = device.getDeviceID();
//		 String DeviceCode = device.getDeviceCode();
//		 Date validitytime = device.getValiditytime();
//		 Date createtime = device.getCreatetime();
//		 Date modifytime = device.getModifytime();
		builder.subject("CDMHPresc:"+device.getDeviceID() )
		.add(HasDeviceCode, device.getDeviceCode())
		.add(HasValiditytime, device.getValiditytime())
		.add(HasCreatetime, device.getCreatetime())
		.add(HasModifytime, device.getModifytime());

		return builder;
	}

}
