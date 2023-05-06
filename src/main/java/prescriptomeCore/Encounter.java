package prescriptomeCore;

import java.util.Date;

/**
 * @author Jean Nikiema
 *
 */
public class Encounter {

	private String EncounterID;
	private String ProviderID;
	private String PatientID;
	private String FacilityID;
	private Date Validitytime;
	private Date Createtime;
	private Date Modifytime;




	/**
	 * @param encounterID
	 * @param providerID
	 * @param patientID
	 * @param facilityID
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Encounter(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime) {

		this.setEncounterID(encounterID);
		this.setProviderID(providerID);
		this.setPatientID(patientID);
		this.setFacilityID(facilityID);
		this.setValiditytime(validitytime);
		this.setCreatetime(createtime);
		this.setModifytime(modifytime);
	}
	
	// constructeur par defaut
	public Encounter() {
		super() ;
	}
	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Encounter)) return false;
		Encounter other = (Encounter) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&



				((this.FacilityID==null && other.getFacilityID()==null) ||
						(this.FacilityID!=null &&
						this.FacilityID.equals(other.getFacilityID())))  &&

				((this.EncounterID==null && other.getEncounterID()==null) ||
						(this.EncounterID!=null &&
						this.EncounterID.equals(other.getEncounterID())))  &&

				((this.ProviderID==null && other.getProviderID()==null) ||
						(this.ProviderID!=null &&
						this.ProviderID.equals(other.getProviderID())))  &&

				((this.PatientID==null && other.getPatientID()==null) ||
						(this.PatientID!=null &&
						this.PatientID.equals(other.getPatientID())))  &&

				((this.Validitytime==null && other.getValiditytime()==null) ||
						(this.Validitytime!=null &&
						this.Validitytime.equals(other.getValiditytime())))  &&

				((this.Modifytime==null && other.getModifytime()==null) ||
						(this.Modifytime!=null &&
						this.Modifytime.equals(other.getModifytime())))  &&

				((this.Createtime==null && other.getCreatetime()==null) ||
						(this.Createtime!=null &&
						this.Createtime.equals(other.getCreatetime())));

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
		if (getFacilityID() != null) {
			_hashCode += getFacilityID().hashCode();
		}

		if (getEncounterID() != null) {
			_hashCode += getEncounterID().hashCode();
		}
		if (getProviderID() != null) {
			_hashCode += getProviderID().hashCode();
		}


		if (getPatientID() != null) {
			_hashCode += getPatientID().hashCode();
		}
		if (getValiditytime() != null) {
			_hashCode += getValiditytime().hashCode();
		}
		if (getModifytime() != null) {
			_hashCode += getModifytime().hashCode();
		}
		if (getCreatetime() != null) {
			_hashCode += getCreatetime().hashCode();
		}


		__hashCodeCalc = false;
		return _hashCode;
	}


	public String getEncounterID() {
		return EncounterID;
	}
	public void setEncounterID(String encounterID) {
		EncounterID = encounterID;
	}
	public String getProviderID() {
		return ProviderID;
	}
	public void setProviderID(String providerID) {
		ProviderID = providerID;
	}
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
	public String getFacilityID() {
		return FacilityID;
	}
	public void setFacilityID(String facilityID) {
		FacilityID = facilityID;
	}
	public Date getValiditytime() {
		return Validitytime;
	}
	public void setValiditytime(Date validitytime) {
		Validitytime = validitytime;
	}
	public Date getCreatetime() {
		return Createtime;
	}
	public void setCreatetime(Date createtime) {
		Createtime = createtime;
	}
	public Date getModifytime() {
		return Modifytime;
	}
	public void setModifytime(Date modifytime) {
		Modifytime = modifytime;
	}



}
