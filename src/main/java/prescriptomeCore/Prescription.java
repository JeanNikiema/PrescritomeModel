package prescriptomeCore;

import java.util.Date;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 *
 */
public class Prescription extends DrugEncounter {


	public Prescription(
			String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation) {
		
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndication, routeOfAdministrationSource, routeOfAdministrationOCRx,
				encounterUnitOfPresentation);
	}
	
	// statut of the prescription if it is a refill
	private int refills;
	//order date
	private Date orderedDate;
	// number of unit of presentation as unique dosage
	private int orderedDose;
	// date for taking the drug
	private Date effectiveStartDate;
	// date for stop taking the drug
	private Date effectiveEndDate;
	// number of unit of presentation
	private int orderQuantity;


	// number odered dosage frequency
	private int dosefrequenceQuantity;
	// Unit of frequency
	private IRI doseFrequenceUnit;
//	// ENCOUNTER THAT LEADING TO THE PRESCRIPTION
//	PRIVATE ENCOUNTER ENCOUNTERPRESCRIPTION;

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
	 * @param refills
	 * @param orderedDate
	 * @param orderedDose
	 * @param effectiveStartDate
	 * @param effectiveEndDate
	 * @param orderQuantity
	 * @param dosefrequenceQuantity
	 * @param doseFrequenceUnit
	 */
	
	public Prescription(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation, int refills, Date orderedDate, int orderedDose, Date effectiveStartDate,
			Date effectiveEndDate, int orderQuantity, int dosefrequenceQuantity, IRI doseFrequenceUnit
			//,			Encounter encounterPrescription
			) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndication, routeOfAdministrationSource,
				routeOfAdministrationOCRx, encounterUnitOfPresentation);
		
		this.setRefills(refills);
		this.setOrderedDate(orderedDate);
		this.setOrderedDose(orderedDose);
		this.setEffectiveStartDate(effectiveStartDate);
		this.setEffectiveEndDate(effectiveEndDate);
		this.setOrderQuantity(orderQuantity);
		this.setDosefrequenceQuantity(dosefrequenceQuantity);
		this.setDoseFrequenceUnit(doseFrequenceUnit);
		//this.setEncounterPrescription(encounterPrescription);
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Prescription)) return false;
		Prescription other = (Prescription) obj;
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

//				((this.getEncounterPrescription()==null && other.getEncounterPrescription()==null) ||
//						(this.getEncounterPrescription()!=null &&
//						this.getEncounterPrescription().equals(other.getEncounterPrescription())))  &&

				((this.getDoseFrequenceUnit()==null && other.getDoseFrequenceUnit()==null) ||
						(this.getDoseFrequenceUnit()!=null &&
						this.getDoseFrequenceUnit().equals(other.getDoseFrequenceUnit())))  &&


				(this.getDosefrequenceQuantity()==other.getDosefrequenceQuantity())  &&


				((this.getRouteOfAdministrationSource()==null && other.getRouteOfAdministrationSource()==null) ||
						(this.getRouteOfAdministrationSource()!=null &&
						this.getRouteOfAdministrationSource().equals(other.getRouteOfAdministrationSource())))  &&

				((this.getRouteOfAdministrationOCRx()==null && other.getRouteOfAdministrationOCRx()==null) ||
						(this.getRouteOfAdministrationOCRx()!=null &&
						this.getRouteOfAdministrationOCRx().equals(other.getRouteOfAdministrationOCRx())))  &&

				((this.getEncounterUnitOfPresentation()==null && other.getEncounterUnitOfPresentation()==null) ||
						(this.getEncounterUnitOfPresentation()!=null &&
						this.getEncounterUnitOfPresentation().equals(other.getEncounterUnitOfPresentation())))  &&

				(this.getRefills()==other.getRefills())  &&

				((this.getOrderedDate()==null && other.getOrderedDate()==null) ||
						(this.getOrderedDate()!=null &&
						this.getOrderedDate().equals(other.getOrderedDate())))  &&

				(this.getOrderedDose()==other.getOrderedDose())  &&

				((this.getEffectiveStartDate()==null && other.getEffectiveStartDate()==null) ||
						(this.getEffectiveStartDate()!=null &&
						this.getEffectiveStartDate().equals(other.getEffectiveStartDate())))  &&

				((this.getEffectiveEndDate()==null && other.getEffectiveEndDate()==null) ||
						(this.getEffectiveEndDate()!=null &&
						this.getEffectiveEndDate().equals(other.getEffectiveEndDate())))  &&

				(this.getOrderQuantity()==other.getOrderQuantity())  &&

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

	@Override
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

		if (getDoseFrequenceUnit() != null) {
			_hashCode += getDoseFrequenceUnit().hashCode();
		}

//		if (getEncounterPrescription() != null) {
//			_hashCode += getEncounterPrescription().hashCode();
//		}



		_hashCode += getDosefrequenceQuantity();

		_hashCode += getRefills();


		if (getOrderedDate() != null) {
			_hashCode += getOrderedDate().hashCode();
		}


		_hashCode += getOrderedDose();


		if (getEffectiveStartDate() != null) {
			_hashCode += getEffectiveStartDate().hashCode();
		}

		if (getEffectiveEndDate() != null) {
			_hashCode += getEffectiveEndDate().hashCode();
		}


		_hashCode += getOrderQuantity();



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



























	public int getRefills() {
		return refills;
	}
	public void setRefills(int refills) {
		this.refills = refills;
	}
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public int getOrderedDose() {
		return orderedDose;
	}
	public void setOrderedDose(int orderedDose) {
		this.orderedDose = orderedDose;
	}
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getDosefrequenceQuantity() {
		return dosefrequenceQuantity;
	}
	public void setDosefrequenceQuantity(int dosefrequenceQuantity) {
		this.dosefrequenceQuantity = dosefrequenceQuantity;
	}
	public IRI getDoseFrequenceUnit() {
		return doseFrequenceUnit;
	}
	public void setDoseFrequenceUnit(IRI doseFrequenceUnit) {
		this.doseFrequenceUnit = doseFrequenceUnit;
	}
//	public Encounter getEncounterPrescription() {
//		return EncounterPrescription;
//	}
//	public void setEncounterPrescription(Encounter encounterPrescription) {
//		EncounterPrescription = encounterPrescription;
//	}



}
