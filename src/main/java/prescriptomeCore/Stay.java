package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 * 
 */
public class Stay extends Encounter {
//	private Date ValiditytimeStay;
//	private Date CreatetimeStay;
//	private Date ModifytimeStay;
	private Date StartDate;
	private Date endDate;

	/**
	 * @param StartDate
	 * @param endDate
	 * @param encounterID
	 * @param providerID
	 * @param patientID
	 * @param facilityID
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Stay(
			//Date ValiditytimeStay,
			//Date CreatetimeStay, 
			//Date ModifytimeStay, 
			Date StartDate, 
			Date endDate,String encounterID, String providerID, String patientID, String facilityID, Date validitytime,
			Date createtime, Date modifytime) {
		super(encounterID, providerID, patientID, facilityID, validitytime, createtime, modifytime);
		// TODO Auto-generated constructor stub
//		this.setValiditytimeStay(ValiditytimeStay);
//		this.setCreatetimeStay(CreatetimeStay);
//		this.setModifytimeStay(ModifytimeStay);
		this.setStartDate(StartDate);
		this.setEndDate(endDate);
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Stay)) return false;
		Stay other = (Stay) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 
				((this.StartDate==null && other.getStartDate()==null) || 
						(this.StartDate!=null &&
						this.StartDate.equals(other.getStartDate())))  &&
				((this.endDate==null && other.getEndDate()==null) || 
						(this.endDate!=null &&
						this.endDate.equals(other.getEndDate())))  &&

				((this.getFacilityID()==null && other.getFacilityID()==null) || 
						(this.getFacilityID()!=null &&
						this.getFacilityID().equals(other.getFacilityID())))  &&

				((this.getEncounterID()==null && other.getEncounterID()==null) || 
						(this.getEncounterID()!=null &&
						this.getEncounterID().equals(other.getEncounterID())))  &&

				((this.getEncounterID()==null && other.getProviderID()==null) || 
						(this.getEncounterID()!=null &&
						this.getEncounterID().equals(other.getProviderID())))  &&

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
						this.getCreatetime().equals(other.getCreatetime())))  //&&

//				((this.getValiditytimeStay()==null && other.getValiditytimeStay()==null) || 
//						(this.getValiditytimeStay()!=null &&
//						this.getValiditytimeStay().equals(other.getValiditytimeStay())))  &&
//
//				((this.getModifytimeStay()==null && other.getModifytimeStay()==null) || 
//						(this.getModifytimeStay()!=null &&
//						this.getModifytimeStay().equals(other.getModifytimeStay())))  &&
//
//			((this.getCreatetimeStay()==null && other.getCreatetimeStay()==null) || 
//						(this.getCreatetimeStay()!=null &&
//						this.getCreatetimeStay().equals(other.getCreatetimeStay())));

;

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
//		if (getValiditytimeStay() != null) {
//			_hashCode += getValiditytimeStay().hashCode();
//		}
//		if (getCreatetimeStay() != null) {
//			_hashCode += getCreatetimeStay().hashCode();
//		}
//		if (getModifytimeStay() != null) {
//			_hashCode += getModifytimeStay().hashCode();
//		}
		if (getStartDate() != null) {
			_hashCode += getStartDate().hashCode();
		}
		if (getEndDate() != null) {
			_hashCode += getEndDate().hashCode();
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

//	public Date getValiditytimeStay() {
//		return ValiditytimeStay;
//	}
//	public void setValiditytimeStay(Date validitytimeStay) {
//		ValiditytimeStay = validitytimeStay;
//	}
//	public Date getCreatetimeStay() {
//		return CreatetimeStay;
//	}
//	public void setCreatetimeStay(Date createtimeStay) {
//		CreatetimeStay = createtimeStay;
//	}
//	public Date getModifytimeStay() {
//		return ModifytimeStay;
//	}
//	public void setModifytimeStay(Date modifytimeStay) {
//		ModifytimeStay = modifytimeStay;
//	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
