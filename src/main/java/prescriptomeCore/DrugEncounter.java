package prescriptomeCore;

import java.util.Date;
import java.util.Set;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 * 
 */
public class DrugEncounter extends Encounter{


	//	public Date getValiditytimeDrugEncounter() {
	//		return ValiditytimeDrugEncounter;
	//	}
	//	public void setValiditytimeDrugEncounter(Date validitytime) {
	//		ValiditytimeDrugEncounter= validitytime;
	//	}
	//	public Date getCreatetimeDrugEncounter() {
	//		return CreatetimeDrugEncounter;
	//	}
	//	public void setCreatetimeDrugEncounter(Date createtime) {
	//		CreatetimeDrugEncounter = createtime;
	//	}
	//	public Date getModifytimeDrugEncounter() {
	//		return ModifytimeDrugEncounter;
	//	}
	//	public void setModifytimeDrugEncounter(Date modifytime) {
	//		ModifytimeDrugEncounter = modifytime;
	//	}
	public IRI getDrugIDDataSource() {
		return DrugIDDataSource;
	}
	public void setDrugIDDataSource(IRI drugIDDataSource) {
		DrugIDDataSource = drugIDDataSource;
	}
	public IRI getDrugIDOCRx() {
		return DrugIDOCRx;
	}
	public void setDrugIDOCRx(IRI drugIDOCRx) {
		DrugIDOCRx = drugIDOCRx;
	}
	public Set<IRI> getDrugIDTherapeuticIndication() {
		return DrugIDTherapeuticIndication;
	}
	public void setDrugIDTherapeuticIndication(Set<IRI> drugIDTherapeuticIndication) {
		DrugIDTherapeuticIndication = drugIDTherapeuticIndication;
	}
	//	private Date ValiditytimeDrugEncounter;
	//	private Date CreatetimeDrugEncounter;
	//	private Date ModifytimeDrugEncounter;
	private IRI DrugIDDataSource;
	private IRI DrugIDOCRx;
	private Stay StayExposure;
	private Set<IRI> DrugIDTherapeuticIndication;
	private IRI routeOfAdministrationSource;
	private IRI routeOfAdministrationOCRx;
	private IRI EncounterUnitOfPresentation;
	//	/**
	//	 * @param encounterID
	//	 * @param providerID
	//	 * @param patientID
	//	 * @param StayExposure
	//	 * @param facilityID
	//	 * @param validitytime
	//	 * @param createtime
	//	 * @param modifytime
	//	 * @param validitytime2
	//	 * @param createtime2
	//	 * @param modifytime2
	//	 * @param drugIDDataSource
	//	 * @param drugIDOCRx
	//	 * @param drugIDTherapeuticIndication
	//	 * @param routeOfAdministrationSource
	//	 * @param routeOfAdministrationOCRx
	//	 * @param EncounterUnitOfPresentation
	//	 */
	//	public DrugEncounter(IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx, IRI EncounterUnitOfPresentation,
	//			String encounterID, String providerID, String patientID, Stay StayExposure, String facilityID, Date validitytime,
	//			Date createtime, Date modifytime, Date validitytime2, Date createtime2, Date modifytime2,
	//			IRI drugIDDataSource, IRI drugIDOCRx, Set<IRI> drugIDTherapeuticIndication) {
	//		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime);
	//		//this.setValiditytimeDrugEncounter(validitytime2);
	//		//this.setCreatetimeDrugEncounter(createtime2);
	//		this.setStayExposure(StayExposure);
	//		//this.setModifytimeDrugEncounter(modifytime2);
	//		this.setDrugIDDataSource(drugIDDataSource);
	//		this.setDrugIDOCRx(drugIDOCRx);
	//		this.setDrugIDTherapeuticIndication(drugIDTherapeuticIndication);
	//	}

	/**
	 * @param encounterID
	 * @param providerID
	 * @param patientID
	 * @param facilityID
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 * @param validitytimeDrugEncounter
	 * @param createtimeDrugEncounter
	 * @param modifytimeDrugEncounter
	 * @param drugIDDataSource
	 * @param drugIDOCRx
	 * @param stayExposure
	 * @param drugIDTherapeuticIndication
	 * @param routeOfAdministrationSource
	 * @param routeOfAdministrationOCRx
	 * @param encounterUnitOfPresentation
	 */
	public DrugEncounter(String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime,
			//Date validitytimeDrugEncounter, 
			//Date createtimeDrugEncounter,
			//Date modifytimeDrugEncounter, 
			IRI drugIDDataSource, IRI drugIDOCRx, Stay stayExposure,
			Set<IRI> drugIDTherapeuticIndication, IRI routeOfAdministrationSource, IRI routeOfAdministrationOCRx,
			IRI encounterUnitOfPresentation) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime);
		//this.setValiditytimeDrugEncounter(validitytimeDrugEncounter);
		//this.setCreatetimeDrugEncounter(createtimeDrugEncounter);
		this.setStayExposure(stayExposure);
		//this.setModifytimeDrugEncounter(modifytimeDrugEncounter);
		this.setDrugIDDataSource(drugIDDataSource);
		this.setDrugIDOCRx(drugIDOCRx);
		this.setDrugIDTherapeuticIndication(drugIDTherapeuticIndication);
		this.setRouteOfAdministrationSource(routeOfAdministrationSource);
		this.setRouteOfAdministrationOCRx(routeOfAdministrationOCRx);
		this.setEncounterUnitOfPresentation(encounterUnitOfPresentation);
	}
	public IRI getRouteOfAdministrationSource() {
		return routeOfAdministrationSource;
	}
	public void setRouteOfAdministrationSource(IRI routeOfAdministrationSource) {
		this.routeOfAdministrationSource = routeOfAdministrationSource;
	}
	public IRI getRouteOfAdministrationOCRx() {
		return routeOfAdministrationOCRx;
	}
	public void setRouteOfAdministrationOCRx(IRI routeOfAdministrationOCRx) {
		this.routeOfAdministrationOCRx = routeOfAdministrationOCRx;
	}
	public IRI getEncounterUnitOfPresentation() {
		return EncounterUnitOfPresentation;
	}
	public void setEncounterUnitOfPresentation(IRI encounterUnitOfPresentation) {
		EncounterUnitOfPresentation = encounterUnitOfPresentation;
	}
	public java.lang.Object get__equalsCalc() {
		return __equalsCalc;
	}
	public void set__equalsCalc(java.lang.Object __equalsCalc) {
		this.__equalsCalc = __equalsCalc;
	}
	public boolean is__hashCodeCalc() {
		return __hashCodeCalc;
	}
	public void set__hashCodeCalc(boolean __hashCodeCalc) {
		this.__hashCodeCalc = __hashCodeCalc;
	}
	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof DrugEncounter)) return false;
		DrugEncounter other = (DrugEncounter) obj;
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


				((this.getRouteOfAdministrationSource()==null && other.getRouteOfAdministrationSource()==null) || 
						(this.getRouteOfAdministrationSource()!=null &&
						this.getRouteOfAdministrationSource().equals(other.getRouteOfAdministrationSource())))  &&

				((this.getRouteOfAdministrationOCRx()==null && other.getRouteOfAdministrationOCRx()==null) || 
						(this.getRouteOfAdministrationOCRx()!=null &&
						this.getRouteOfAdministrationOCRx().equals(other.getRouteOfAdministrationOCRx())))  &&

				((this.getEncounterUnitOfPresentation()==null && other.getEncounterUnitOfPresentation()==null) || 
						(this.getEncounterUnitOfPresentation()!=null &&
						this.getEncounterUnitOfPresentation().equals(other.getEncounterUnitOfPresentation())))  &&


				//				((this.getValiditytimeDrugEncounter()==null && other.getValiditytimeDrugEncounter()==null) || 
				//						(this.getValiditytimeDrugEncounter()!=null &&
				//						this.getValiditytimeDrugEncounter().equals(other.getValiditytimeDrugEncounter())))  &&
				//
				//				((this.getCreatetimeDrugEncounter()==null && other.getCreatetimeDrugEncounter()==null) || 
				//						(this.getCreatetimeDrugEncounter()!=null &&
				//						this.getCreatetimeDrugEncounter().equals(other.getCreatetimeDrugEncounter())))  &&
				//
				//				((this.getModifytimeDrugEncounter()==null && other.getModifytimeDrugEncounter()==null) || 
				//						(this.getModifytimeDrugEncounter()!=null &&
				//						this.getModifytimeDrugEncounter().equals(other.getModifytimeDrugEncounter())))  &&

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


		//		if (getValiditytimeDrugEncounter() != null) {
		//			_hashCode += getValiditytimeDrugEncounter().hashCode();
		//		}
		//
		//		if (getCreatetimeDrugEncounter() != null) {
		//			_hashCode += getCreatetimeDrugEncounter().hashCode();
		//		}
		//
		//		if (getModifytimeDrugEncounter() != null) {
		//			_hashCode += getModifytimeDrugEncounter().hashCode();
		//		}

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
	public Stay getStayExposure() {
		return StayExposure;
	}
	public void setStayExposure(Stay stayExposure) {
		StayExposure = stayExposure;
	}



}
