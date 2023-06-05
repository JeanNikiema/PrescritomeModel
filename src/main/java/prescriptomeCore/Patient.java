package prescriptomeCore;

import java.util.Date;
/**
 * A human being as patient
 * @author Jean Nikiema
 */
public class Patient extends Person{
	private DeathInformation deathInformation;
	private CauseDeathInformation causeDeathInformation;
	private PatientGroup PatientGroup;
	private String PatientID;
	private Date ValiditytimePatient;
	private Date CreatetimePatient;
	private Date ModifytimePatient;
	
	public Patient( String PatientID, Date ModifytimePatient,Date CreatetimePatient, Date ValiditytimePatient, DeathInformation deathInformation, CauseDeathInformation cause,  PatientGroup Group, int BirthMonth, int BirthYear, int BirthDay, String identifierSource, String DataBaseIdentifier,
			String genderCode, String EthnicID, String SexeCode, String Name, Date Validitytime, boolean DeathIndicator,
			Date Createtime, Date Modifytime,Adress adress) {
		
		super(BirthMonth, BirthYear, BirthDay, identifierSource, DataBaseIdentifier, genderCode, EthnicID, SexeCode, Name,
				Validitytime, DeathIndicator, Createtime, Modifytime,adress);
		
		this.setPatientID(PatientID);
		this.setDeathInformation(deathInformation);
		this.setCauseDeathInformation(cause);
		this.setPatientGroup(Group);
		this.setValiditytimePatient(ValiditytimePatient);
		this.setCreatetimePatient(CreatetimePatient);
		this.setModifytimePatient(ModifytimePatient);
	}


	/**
	 * @param BirthMonth
	 * @param BirthYear
	 * @param BirthDay
	 * @param identifierSource
	 * @param DataBaseIdentifier
	 * @param genderCode
	 * @param EthnicID
	 * @param SexeCode
	 * @param Name
	 * @param Validitytime
	 * @param DeathIndicator
	 * @param Createtime
	 * @param Modifytime
	 * @param deathInformation
	 * @param causeDeathInformation
	 * @param patientGroup
	 * @param patientID
	 * @param validitytimePatient
	 * @param createtimePatient
	 * @param modifytimePatient
	 */
	
	public Patient(int BirthMonth, int BirthYear, int BirthDay, String identifierSource, String DataBaseIdentifier,
			String genderCode, String EthnicID, String SexeCode, String Name, Date Validitytime, boolean DeathIndicator,
			Date Createtime, Date Modifytime, DeathInformation deathInformation,
			CauseDeathInformation causeDeathInformation, prescriptomeCore.PatientGroup patientGroup, String patientID,
			Date validitytimePatient, Date createtimePatient, Date modifytimePatient,Adress adress) {
		
		super(BirthMonth, BirthYear, BirthDay, identifierSource, DataBaseIdentifier, genderCode, EthnicID, SexeCode,
				Name, Validitytime, DeathIndicator, Createtime, Modifytime, adress);
		
		this.deathInformation = deathInformation;
		this.causeDeathInformation = causeDeathInformation;
		PatientGroup = patientGroup;
		PatientID = patientID;
		ValiditytimePatient = validitytimePatient;
		CreatetimePatient = createtimePatient;
		ModifytimePatient = modifytimePatient;

	}

	
	/**
	 * 
	 */
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DeathInformation getDeathInformation() {
		return deathInformation;
	}
	
	
	public void setDeathInformation(DeathInformation deathInformation) {
		this.deathInformation = deathInformation;
	}

	
	public PatientGroup getPatientGroup() {
		return PatientGroup;
	}
	
	
	public void setPatientGroup(PatientGroup patientGroup) {
		PatientGroup = patientGroup;
	}


	public CauseDeathInformation getCauseDeathInformation() {
		return causeDeathInformation;
	}


	public void setCauseDeathInformation(CauseDeathInformation causeDeathInformation) {
		this.causeDeathInformation = causeDeathInformation;
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Patient)) return false;
		Patient other = (Patient) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&

				((this.getPatientID()==null && other.getPatientID()==null) ||
						(this.getPatientID()!=null &&
						this.getPatientID().equals(other.getPatientID())))  &&

				((this.getDeathInformation()==null && other.getDeathInformation()==null) ||
						(this.getDeathInformation()!=null &&
						this.getDeathInformation().equals(other.getDeathInformation())))  &&


				((this.getCauseDeathInformation()==null && other.getCauseDeathInformation()==null) ||
						(this.getCauseDeathInformation()!=null &&
						this.getCauseDeathInformation().equals(other.getCauseDeathInformation())))  &&

				((this.getPatientGroup()==null && other.getPatientGroup()==null) ||
						(this.getPatientGroup()!=null &&
						this.getPatientGroup().equals(other.getPatientGroup())))  &&

				this.getBirthMonth()==other.getBirthMonth() &&

				this.getBirthYear()==other.getBirthYear() &&

				this.getBirthDay()==other.getBirthDay() &&



				((this.getIdentifierSource()==null && other.getIdentifierSource()==null) ||
						(this.getIdentifierSource()!=null &&
						this.getIdentifierSource().equals(other.getIdentifierSource())))  &&

				((this.getDataBaseIdentifier()==null && other.getDataBaseIdentifier()==null) ||
						(this.getDataBaseIdentifier()!=null &&
						this.getDataBaseIdentifier().equals(other.getDataBaseIdentifier())))  &&

				((this.getGenderCode()==null && other.getGenderCode()==null) ||
						(this.getGenderCode()!=null &&
						this.getGenderCode().equals(other.getGenderCode())))  &&

				((this.getEthnicID()==null && other.getEthnicID()==null) ||
						(this.getEthnicID()!=null &&
						this.getEthnicID().equals(other.getEthnicID())))  &&

				((this.getSexeCode()==null && other.getSexeCode()==null) ||
						(this.getSexeCode()!=null &&
						this.getSexeCode().equals(other.getSexeCode())))  &&

				((this.getName()==null && other.getName()==null) ||
						(this.getName()!=null &&
						this.getName().equals(other.getName())))  &&

				((this.getValiditytime()==null && other.getValiditytime()==null) ||
						(this.getValiditytime()!=null &&
						this.getValiditytime().equals(other.getValiditytime())))  &&

				((this.getValiditytimePatient()==null && other.getValiditytimePatient()==null) ||
						(this.getValiditytimePatient()!=null &&
						this.getValiditytimePatient().equals(other.getValiditytimePatient())))  &&

				((this.getCreatetimePatient()==null && other.getCreatetimePatient()==null) ||
						(this.getCreatetimePatient()!=null &&
						this.getCreatetimePatient().equals(other.getCreatetimePatient())))  &&

				((this.getModifytimePatient()==null && other.getModifytimePatient()==null) ||
						(this.getModifytimePatient()!=null &&
						this.getModifytimePatient().equals(other.getModifytimePatient())))  &&



				this.isDeathIndicator() ==other.isDeathIndicator()  &&

				((this.getCreatetime()==null && other.getCreatetime()==null) ||
						(this.getCreatetime()!=null &&
						this.getCreatetime().equals(other.getCreatetime())))  &&

				((this.getModifytime()==null && other.getModifytime()==null) ||
						(this.getModifytime()!=null &&
						this.getModifytime().equals(other.getModifytime())));

		__equalsCalc = null;
		return _equals;
	}



	private boolean __hashCodeCalc = false;
	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;


		_hashCode += this.getBirthMonth();
		_hashCode += this.getBirthYear();
		_hashCode += this.getBirthDay();

		if (getPatientID() != null) {
			_hashCode += getPatientID().hashCode();
		}

		if (getCauseDeathInformation() != null) {
			_hashCode += getCauseDeathInformation().hashCode();
		}
		if (getPatientGroup() != null) {
			_hashCode += getPatientGroup().hashCode();
		}

		if (getDeathInformation() != null) {
			_hashCode += getDeathInformation().hashCode();
		}

		if (this.getDataBaseIdentifier() != null) {
			_hashCode += this.getDataBaseIdentifier().hashCode();
		}
		if (this.getGenderCode() != null) {
			_hashCode += this.getGenderCode().hashCode();
		}
		if (this.getEthnicID() != null) {
			_hashCode += this.getEthnicID().hashCode();
		}
		if (this.getSexeCode() != null) {
			_hashCode += this.getSexeCode().hashCode();
		}
		if (this.getName() != null) {
			_hashCode += this.getName().hashCode();
		}
		if (this.getValiditytime() != null) {
			_hashCode += this.getValiditytime().hashCode();
		}


		if (this.getIdentifierSource() != null) {
			_hashCode += this.getIdentifierSource().hashCode();
		}

		if (this.getValiditytime() != null) {
			_hashCode += this.getValiditytime().hashCode();
		}
		if (this.getCreatetime() != null) {
			_hashCode += this.getCreatetime().hashCode();
		}
		if (this.getModifytime() != null) {
			_hashCode += this.getModifytime().hashCode();
		}

		if (this.getValiditytimePatient() != null) {
			_hashCode += this.getValiditytimePatient().hashCode();
		}
		if (this.getCreatetimePatient() != null) {
			_hashCode += this.getCreatetimePatient().hashCode();
		}
		if (this.getModifytimePatient() != null) {
			_hashCode += this.getModifytimePatient().hashCode();
		}

		__hashCodeCalc = false;
		return _hashCode;
	}


	public Date getValiditytimePatient() {
		return ValiditytimePatient;
	}


	public void setValiditytimePatient(Date validitytimePatient) {
		ValiditytimePatient = validitytimePatient;
	}


	public Date getCreatetimePatient() {
		return CreatetimePatient;
	}


	public void setCreatetimePatient(Date createtimePatient) {
		CreatetimePatient = createtimePatient;
	}


	public Date getModifytimePatient() {
		return ModifytimePatient;
	}


	public void setModifytimePatient(Date modifytimePatient) {
		ModifytimePatient = modifytimePatient;
	}


	public String getPatientID() {
		return PatientID;
	}


	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
}
