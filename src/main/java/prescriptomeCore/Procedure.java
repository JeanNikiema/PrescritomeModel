package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 *
 */
public class Procedure {
	private String procedureID;
	private Encounter Encounter;
	// procedure type code
	private IRI typeCode;
	private IRI originaleTypeCode;
	//procedure  date
	private Date procedureDate;
	// Quantity procedure
	private int quantity;
	//information source type code
	private IRI informationSourceTypeCode;
	// if it is a sequential procedure information on the steps
	private int priorityCode;
	private Date Validitytime;
	private Date Createtime;
	private Date Modifytime;
	private Date startDate;
	private Date endDate;
	private Device Device;






	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		return Device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		Device = device;
	}

	/**
	 * @param procedureID
	 * @param encounter
	 * @param typeCode
	 * @param originaleTypeCode
	 * @param procedureDate
	 * @param quantity
	 * @param informationSourceTypeCode
	 * @param priorityCode
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Procedure(String procedureID, prescriptomeCore.Encounter encounter, IRI typeCode, IRI originaleTypeCode,
			Date procedureDate, int quantity, IRI informationSourceTypeCode, int priorityCode, Date validitytime,
			Date createtime, Date modifytime, Date startDate, Date endDate, Device Device) {
		super();
		this.procedureID = procedureID;
		this.Encounter = encounter;
		this.typeCode = typeCode;
		this.originaleTypeCode = originaleTypeCode;
		this.procedureDate = procedureDate;
		this.quantity = quantity;
		this.informationSourceTypeCode = informationSourceTypeCode;
		this.priorityCode = priorityCode;
		this.Validitytime = validitytime;
		this.Createtime = createtime;
		this.Modifytime = modifytime;
		this.startDate = startDate;
		this.endDate= endDate;
		this.Device =Device;
	}

	/**
	 * @return the procedureID
	 */
	public String getProcedureID() {
		return procedureID;
	}
	/**
	 * @param procedureID the procedureID to set
	 */
	public void setProcedureID(String procedureID) {
		this.procedureID = procedureID;
	}
	/**
	 * @return the encounter
	 */
	public Encounter getEncounter() {
		return Encounter;
	}
	/**
	 * @param encounter the encounter to set
	 */
	public void setEncounter(Encounter encounter) {
		Encounter = encounter;
	}
	/**
	 * @return the typeCode
	 */
	public IRI getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(IRI typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the originaleTypeCode
	 */
	public IRI getOriginaleTypeCode() {
		return originaleTypeCode;
	}
	/**
	 * @param originaleTypeCode the originaleTypeCode to set
	 */
	public void setOriginaleTypeCode(IRI originaleTypeCode) {
		this.originaleTypeCode = originaleTypeCode;
	}
	/**
	 * @return the procedureDate
	 */
	public Date getProcedureDate() {
		return procedureDate;
	}
	/**
	 * @param procedureDate the procedureDate to set
	 */
	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the informationSourceTypeCode
	 */
	public IRI getInformationSourceTypeCode() {
		return informationSourceTypeCode;
	}
	/**
	 * @param informationSourceTypeCode the informationSourceTypeCode to set
	 */
	public void setInformationSourceTypeCode(IRI informationSourceTypeCode) {
		this.informationSourceTypeCode = informationSourceTypeCode;
	}
	/**
	 * @return the priorityCode
	 */
	public int getPriorityCode() {
		return priorityCode;
	}
	/**
	 * @param priorityCode the priorityCode to set
	 */
	public void setPriorityCode(int priorityCode) {
		this.priorityCode = priorityCode;
	}
	/**
	 * @return the validitytime
	 */
	public Date getValiditytime() {
		return Validitytime;
	}
	/**
	 * @param validitytime the validitytime to set
	 */
	public void setValiditytime(Date validitytime) {
		Validitytime = validitytime;
	}
	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return Createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		Createtime = createtime;
	}
	/**
	 * @return the modifytime
	 */
	public Date getModifytime() {
		return Modifytime;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Date modifytime) {
		Modifytime = modifytime;
	}


	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Procedure)) return false;
		Procedure other = (Procedure) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&



				((this.getProcedureID()==null && other.getProcedureID()==null) ||
						(this.getProcedureID()!=null &&
						this.getProcedureID().equals(other.getProcedureID())))  &&

				((this.getEncounter()==null && other.getEncounter()==null) ||
						(this.getEncounter()!=null &&
						this.getEncounter().equals(other.getEncounter())))  &&

				((this.getTypeCode()==null && other.getTypeCode()==null) ||
						(this.getTypeCode()!=null &&
						this.getTypeCode().equals(other.getTypeCode())))  &&

				((this.getOriginaleTypeCode()==null && other.getOriginaleTypeCode()==null) ||
						(this.getOriginaleTypeCode()!=null &&
						this.getOriginaleTypeCode().equals(other.getOriginaleTypeCode())))  &&

				((this.getProcedureDate()==null && other.getProcedureDate()==null) ||
						(this.getProcedureDate()!=null &&
						this.getProcedureDate().equals(other.getProcedureDate())))  &&

				(this.getQuantity()==other.getQuantity())  &&

				((this.getInformationSourceTypeCode()==null && other.getInformationSourceTypeCode()==null) ||
						(this.getInformationSourceTypeCode()!=null &&
						this.getInformationSourceTypeCode().equals(other.getInformationSourceTypeCode())))  &&

				(this.getPriorityCode()==other.getPriorityCode())  &&

				((this.getStartDate()==null && other.getStartDate()==null) ||
						(this.getStartDate()!=null &&
						this.getStartDate().equals(other.getStartDate())))  &&
				((this.getEndDate()==null && other.getEndDate()==null) ||
						(this.getEndDate()!=null &&
						this.getEndDate().equals(other.getEndDate())))  &&
				((this.getDevice()==null && other.getDevice()==null) ||
						(this.getDevice()!=null &&
						this.getDevice().equals(other.getDevice())))  &&


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
		if (getProcedureID() != null) {
			_hashCode += getProcedureID().hashCode();
		}

		if (getEncounter() != null) {
			_hashCode += getEncounter().hashCode();
		}
		if (getTypeCode() != null) {
			_hashCode += getTypeCode().hashCode();
		}


		if (getOriginaleTypeCode() != null) {
			_hashCode += getOriginaleTypeCode().hashCode();
		}

		if (getProcedureDate() != null) {
			_hashCode += getProcedureDate().hashCode();
		}
		_hashCode += getQuantity();
		if (getInformationSourceTypeCode() != null) {
			_hashCode += getInformationSourceTypeCode().hashCode();
		}
		_hashCode += getPriorityCode();

		if (getValiditytime() != null) {
			_hashCode += getValiditytime().hashCode();
		}
		if (getModifytime() != null) {
			_hashCode += getModifytime().hashCode();
		}
		if (getCreatetime() != null) {
			_hashCode += getCreatetime().hashCode();
		}

		if (getStartDate() != null) {
			_hashCode += getStartDate().hashCode();
		}
		if (getEndDate() != null) {
			_hashCode += getEndDate().hashCode();
		}
		if (getDevice() != null) {
			_hashCode += getDevice().hashCode();
		}


		__hashCodeCalc = false;
		return _hashCode;
	}





}
