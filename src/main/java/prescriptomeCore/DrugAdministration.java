package prescriptomeCore;

import java.util.Date;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 * 
 */
public class DrugAdministration extends DrugEncounter{


	public DrugAdministration(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndication, routeOfAdministrationSource, routeOfAdministrationOCRx,
				encounterUnitOfPresentation);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param encounterID
	 * @param providerID
	 * @param patientID
	 * @param facilityID
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 * @param drugIDDataSource
	 * @param drugIDOCRx
	 * @param stayExposure
	 * @param drugIDTherapeuticIndication
	 * @param routeOfAdministrationSource
	 * @param routeOfAdministrationOCRx
	 * @param encounterUnitOfPresentation
	 * @param startDate
	 * @param endDate
	 * @param administrationInstructions
	 * @param administeredDose
	 * @param prescription
	 */
	public DrugAdministration(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation, Date startDate, Date endDate, String administrationInstructions,
			int administeredDose, prescriptomeCore.Prescription prescription, boolean Stop, IRI stopReasonSource, IRI stopReason) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime,
				drugIDDataSource, drugIDOCRx, stayExposure, drugIDTherapeuticIndication,
				routeOfAdministrationSource, routeOfAdministrationOCRx, encounterUnitOfPresentation);
		this.startDate = startDate;
		this.endDate = endDate;
		this.administrationInstructions = administrationInstructions;
		this.administeredDose = administeredDose;
		this.Prescription = prescription;
		this.Stop= Stop;
		this.stopReasonSource=stopReasonSource;
		this.stopReason=stopReason;
	}


	public boolean isStop() {
		return Stop;
	}

	public void setStop(boolean stop) {
		Stop = stop;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAdministrationInstructions() {
		return administrationInstructions;
	}

	public void setAdministrationInstructions(String administrationInstructions) {
		this.administrationInstructions = administrationInstructions;
	}

	public int getAdministeredDose() {
		return administeredDose;
	}

	public void setAdministeredDose(int administeredDose) {
		this.administeredDose = administeredDose;
	}

	public Prescription getPrescription() {
		return Prescription;
	}

	public void setPrescription(Prescription prescription) {
		Prescription = prescription;
	}


	// date for starting the administration
	private Date startDate;
	// date for ending the administration
	private Date endDate;
	// instruction d'administration
	private String administrationInstructions;
	// number of unit of presentation administrated
	private int administeredDose;

	// Prescription that leading to the administration
	private Prescription Prescription;
	// drug administration stop
	private boolean Stop;
	// drug administration raison in original code
	private IRI stopReasonSource;
	// drug administration raison in MEDDRA
	private IRI stopReason;

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof DrugAdministration)) return false;
		DrugAdministration other = (DrugAdministration) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 
				((this.getStayExposure()==null && other.getStayExposure()==null) || 
						(this.getStayExposure()!=null &&
						this.getStayExposure().equals(other.getStayExposure())))  &&


				((this.getStopReasonSource()==null && other.getStopReasonSource()==null) || 
						(this.getStopReasonSource()!=null &&
						this.getStopReasonSource().equals(other.getStopReasonSource())))  &&

				((this.getStopReason()==null && other.getStopReason()==null) || 
						(this.getStopReason()!=null &&
						this.getStopReason().equals(other.getStopReason())))  &&

				(this.Stop==other.Stop)  &&

				((this.getStartDate()==null && other.getStartDate()==null) || 
						(this.getStartDate()!=null &&
						this.getStartDate().equals(other.getStartDate())))  &&

				((this.getEndDate()==null && other.getEndDate()==null) || 
						(this.getEndDate()!=null &&
						this.getEndDate().equals(other.getEndDate())))  &&

				((this.getAdministrationInstructions()==null && other.getAdministrationInstructions()==null) || 
						(this.getAdministrationInstructions()!=null &&
						this.getAdministrationInstructions().equals(other.getAdministrationInstructions())))  &&


				((this.getPrescription()==null && other.getPrescription()==null) || 
						(this.getPrescription()!=null &&
						this.getPrescription().equals(other.getPrescription())))  &&

				(this.getAdministeredDose()==other.getAdministeredDose())  &&


				((this.getRouteOfAdministrationSource()==null && other.getRouteOfAdministrationSource()==null) || 
						(this.getRouteOfAdministrationSource()!=null &&
						this.getRouteOfAdministrationSource().equals(other.getRouteOfAdministrationSource())))  &&

				((this.getRouteOfAdministrationOCRx()==null && other.getRouteOfAdministrationOCRx()==null) || 
						(this.getRouteOfAdministrationOCRx()!=null &&
						this.getRouteOfAdministrationOCRx().equals(other.getRouteOfAdministrationOCRx())))  &&

				((this.getEncounterUnitOfPresentation()==null && other.getEncounterUnitOfPresentation()==null) || 
						(this.getEncounterUnitOfPresentation()!=null &&
						this.getEncounterUnitOfPresentation().equals(other.getEncounterUnitOfPresentation())))  &&


				((this.getDrugIDDataSource()==null && other.getDrugIDDataSource()==null) || 
						(this.getDrugIDDataSource()!=null &&
						this.getDrugIDDataSource().equals(other.getDrugIDDataSource())))  &&

				((this.getDrugIDOCRx()==null && other.getDrugIDOCRx()==null) || 
						(this.getDrugIDOCRx()!=null &&
						this.getDrugIDOCRx().equals(other.getDrugIDOCRx())))  &&

				((this.getDrugIDTherapeuticIndication()==null && other.getDrugIDTherapeuticIndication()==null) || 
						(this.getDrugIDTherapeuticIndication()!=null &&
						this.getDrugIDTherapeuticIndication().equals(other.getDrugIDTherapeuticIndication())))  &&

				((this.getFacilityID()==null && other.getFacilityID()==null) || 
						(this.getFacilityID()!=null &&
						this.getFacilityID().equals(other.getFacilityID())))  &&

				((this.getEncounterID()==null && other.getEncounterID()==null) || 
						(this.getEncounterID()!=null &&
						this.getEncounterID().equals(other.getEncounterID())))  &&

				((this.getProviderID()==null && other.getProviderID()==null) || 
						(this.getProviderID()!=null &&
						this.getProviderID().equals(other.getProviderID())))  &&

				((this.getPatientID()==null && other.getPatientID()==null) || 
						(this.getPatientID()!=null &&
						this.getPatientID().equals(other.getPatientID())))  &&

				((this.getValiditytime()==null && other.getValiditytime()==null) || 
						(this.getValiditytime()!=null &&
						this.getValiditytime().equals(other.getValiditytime())))  &&

				((this.getModifytime()==null && other.getModifytime()==null) || 
						(this.getModifytime()!=null &&
						this.getModifytime().equals(other.getModifytime())))  &&

				((this.getCreatetime()==null && other.getCreatetime()==null) || 
						(this.getCreatetime()!=null &&
						this.getCreatetime().equals(other.getCreatetime())));

		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;


		if (getRouteOfAdministrationSource() != null) {
			_hashCode += getRouteOfAdministrationSource().hashCode();
		}

		if (getRouteOfAdministrationOCRx() != null) {
			_hashCode += getRouteOfAdministrationOCRx().hashCode();
		}

		if (getEncounterUnitOfPresentation() != null) {
			_hashCode += getEncounterUnitOfPresentation().hashCode();
		}




		if (getStopReasonSource() != null) {
			_hashCode += getStopReasonSource().hashCode();
		}
		if (getStopReason() != null) {
			_hashCode += getStopReason().hashCode();
		}
		if (getStopReason() != null) {
			_hashCode += getStopReason().hashCode();
		}

		_hashCode += getAdministeredDose();


		if (getPrescription() != null) {
			_hashCode += getPrescription().hashCode();
		}


		if (getStartDate() != null) {
			_hashCode += getStartDate().hashCode();
		}

		if (getEndDate() != null) {
			_hashCode += getEndDate().hashCode();
		}

		if (getAdministrationInstructions() != null) {
			_hashCode += getAdministrationInstructions().hashCode();
		}

		if (getDrugIDDataSource() != null) {
			_hashCode += getDrugIDDataSource().hashCode();
		}

		if (getDrugIDOCRx() != null) {
			_hashCode += getDrugIDOCRx().hashCode();
		}

		if (getDrugIDTherapeuticIndication() != null) {
			_hashCode += getDrugIDTherapeuticIndication().hashCode();
		}
		if (getStayExposure() != null) {
			_hashCode += getStayExposure().hashCode();
		}
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

	public IRI getStopReasonSource() {
		return stopReasonSource;
	}

	public void setStopReasonSource(IRI stopReasonSource) {
		this.stopReasonSource = stopReasonSource;
	}

	public IRI getStopReason() {
		return stopReason;
	}

	public void setStopReason(IRI stopReason) {
		this.stopReason = stopReason;
	}

}
