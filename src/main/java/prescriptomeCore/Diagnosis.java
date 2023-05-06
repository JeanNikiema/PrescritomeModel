package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 *
 */
public class Diagnosis {



	/**
	 * @param diagnosisID
	 * @param originalDiagnosisCode
	 * @param diagnosisCode
	 * @param encounterID
	 * @param providerID
	 * @param patientID
	 * @param reportedDate
	 * @param priorityAtDischarge
	 * @param presentAtEncounter
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Diagnosis(String diagnosisID, IRI originalDiagnosisCode, IRI diagnosisCode, String encounterID,
			String providerID, String patientID, Date reportedDate, int priorityAtDischarge, boolean presentAtEncounter,
			Date validitytime, Date createtime, Date modifytime) {
		super();
		this.DiagnosisID = diagnosisID;
		this.originalDiagnosisCode = originalDiagnosisCode;
		this.diagnosisCode = diagnosisCode;
		this.EncounterID = encounterID;
		this.ProviderID = providerID;
		this.PatientID = patientID;
		this.reportedDate = reportedDate;
		this.priorityAtDischarge = priorityAtDischarge;
		this.presentAtEncounter = presentAtEncounter;
		this.Validitytime = validitytime;
		this.Createtime = createtime;
		this.Modifytime = modifytime;
	}
	
	
	

	/**
	 * 
	 */
	public Diagnosis() {
		super();
		// TODO Auto-generated constructor stub
	}




	public String getDiagnosisID() {
		return DiagnosisID;
	}
	public void setDiagnosisID(String diagnosisID) {
		DiagnosisID = diagnosisID;
	}
	public IRI getOriginalDiagnosisCode() {
		return originalDiagnosisCode;
	}
	public void setOriginalDiagnosisCode(IRI originalDiagnosisCode) {
		this.originalDiagnosisCode = originalDiagnosisCode;
	}
	public IRI getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(IRI diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
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
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	public int getPriorityAtDischarge() {
		return priorityAtDischarge;
	}
	public void setPriorityAtDischarge(int priorityAtDischarge) {
		this.priorityAtDischarge = priorityAtDischarge;
	}
	public boolean isPresentAtEncounter() {
		return presentAtEncounter;
	}
	public void setPresentAtEncounter(boolean presentAtEncounter) {
		this.presentAtEncounter = presentAtEncounter;
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
	private String DiagnosisID;
	private IRI originalDiagnosisCode;
	private IRI diagnosisCode;

	private Date reportedDate;
	private int priorityAtDischarge;
	//replace present at admission of CDMH
	private boolean presentAtEncounter;
	private String EncounterID;
	private String ProviderID;
	private String PatientID;
	private Date Validitytime;
	private Date Createtime;
	private Date Modifytime;

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Diagnosis)) return false;
		Diagnosis other = (Diagnosis) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&



				((this.getDiagnosisID()==null && other.getDiagnosisID()==null) ||
						(this.getDiagnosisID()!=null &&
						this.getDiagnosisID().equals(other.getDiagnosisID())))  &&

				((this.getOriginalDiagnosisCode()==null && other.getOriginalDiagnosisCode()==null) ||
						(this.getOriginalDiagnosisCode()!=null &&
						this.getOriginalDiagnosisCode().equals(other.getOriginalDiagnosisCode())))  &&


				((this.getDiagnosisCode()==null && other.getDiagnosisCode()==null) ||
						(this.getDiagnosisCode()!=null &&
						this.getDiagnosisCode().equals(other.getDiagnosisCode())))  &&


				((this.getReportedDate()==null && other.getReportedDate()==null) ||
						(this.getReportedDate()!=null &&
						this.getReportedDate().equals(other.getReportedDate())))  &&

				(this.getPriorityAtDischarge()== other.getPriorityAtDischarge())  &&

				(this.isPresentAtEncounter()==other.isPresentAtEncounter())  &&


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



		if (getDiagnosisID() != null) {
			_hashCode += getDiagnosisID().hashCode();
		}
		if (getOriginalDiagnosisCode() != null) {
			_hashCode += getOriginalDiagnosisCode().hashCode();
		}
		if (getDiagnosisCode() != null) {
			_hashCode += getDiagnosisCode().hashCode();
		}
		if (getReportedDate() != null) {
			_hashCode += getReportedDate().hashCode();
		}

		_hashCode += getPriorityAtDischarge();


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


}
