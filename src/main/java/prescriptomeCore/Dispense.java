package prescriptomeCore;

import java.util.Date;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 * 
 */
public class Dispense extends DrugEncounter{
	//Days of supply
	private int daysSupply;
	//dispense  date
	private Date dispenseDate;
	// Quantity supply by unit of presentation
	private int quantity;
	// Prescription leading to the dispense
	private Prescription DrugPrescription;

//	public Dispense(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
//			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
//			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
//			IRI encounterUnitOfPresentation) {
//		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime, drugIDDataSource,
//				drugIDOCRx, stayExposure, drugIDTherapeuticIndication, routeOfAdministrationSource, routeOfAdministrationOCRx,
//				encounterUnitOfPresentation);
//		// TODO Auto-generated constructor stub
//	}







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
	 * @param daySupply
	 * @param dispenseDate
	 * @param quantity
	 * @param drugPrescription
	 */
	public Dispense(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime, IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation, int daySupply, Date dispenseDate, int quantity,
			Prescription drugPrescription) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime, drugIDDataSource,
				drugIDOCRx, stayExposure, drugIDTherapeuticIndication, routeOfAdministrationSource,
				routeOfAdministrationOCRx, encounterUnitOfPresentation);
		this.daysSupply = daySupply;
		this.dispenseDate = dispenseDate;
		this.quantity = quantity;
		this.DrugPrescription = drugPrescription;
	}







	public int getDaysSupply() {
		return daysSupply;
	}
	public void setDaysSupply(int daySupply) {
		this.daysSupply = daySupply;
	}
	public Date getDispenseDate() {
		return dispenseDate;
	}
	public void setDispenseDate(Date dispenseDate) {
		this.dispenseDate = dispenseDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Prescription getDrugPrescription() {
		return DrugPrescription;
	}
	public void setDrugPrescription(Prescription drugPrescription) {
		this.DrugPrescription = drugPrescription;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Dispense)) return false;
		Dispense other = (Dispense) obj;
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

				(this.getDaysSupply()==other.getDaysSupply())  &&
				(this.getQuantity()==other.getQuantity())  &&

				((this.getDispenseDate()==null && other.getDispenseDate()==null) || 
						(this.getDispenseDate()!=null &&
						this.getDispenseDate().equals(other.getDispenseDate())))  &&

				((this.getDrugPrescription()==null && other.getDrugPrescription()==null) || 
						(this.getDrugPrescription()!=null &&
						this.getDrugPrescription().equals(other.getDrugPrescription())))  &&

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


		_hashCode += getDaysSupply();
		_hashCode += getQuantity();

		if (getDispenseDate() != null) {
			_hashCode += getDispenseDate().hashCode();
		}

		if (getDrugPrescription() != null) {
			_hashCode += getDrugPrescription().hashCode();
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

}
