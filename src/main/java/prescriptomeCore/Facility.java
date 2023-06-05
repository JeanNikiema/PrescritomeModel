package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 *
 */
public class Facility {

	private String FacilityID;
	private String name;
	private Date ValiditytimeFacility;
	private Date CreatetimeFacility;
	private Date ModifytimeFacility;
	private Adress FacilityAdress;


	/**
	 * @param facilityID
	 * @param name
	 * @param validitytimeFacility
	 * @param createtimeFacility
	 * @param modifytimeFacility
	 * @param facilityAdress
	 */
	public Facility(String facilityID, String name, Date validitytimeFacility,
			Date createtimeFacility, Date modifytimeFacility, Adress facilityAdress) {

		this.setFacilityID(facilityID);
		this.setName(name);
		this.setValiditytimeFacility (validitytimeFacility);
		this.setCreatetimeFacility (createtimeFacility);
		this.setModifytimeFacility (modifytimeFacility);
		this.setFacilityAdress (facilityAdress);
	}

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Facility)) return false;
		Facility other = (Facility) obj;
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

				((this.name==null && other.getName()==null) ||
						(this.name!=null &&
						this.name.equals(other.getName())))  &&

				((this.ValiditytimeFacility==null && other.getValiditytimeFacility()==null) ||
						(this.ValiditytimeFacility!=null &&
						this.ValiditytimeFacility.equals(other.getValiditytimeFacility())))  &&

				((this.CreatetimeFacility==null && other.getCreatetimeFacility()==null) ||
						(this.CreatetimeFacility!=null &&
						this.CreatetimeFacility.equals(other.getCreatetimeFacility())))  &&

				((this.FacilityAdress==null && other.getFacilityAdress()==null) ||
						(this.FacilityAdress!=null &&
						this.FacilityAdress.equals(other.getFacilityAdress())))  &&

				((this.ModifytimeFacility==null && other.getModifytimeFacility()==null) ||
						(this.ModifytimeFacility!=null &&
						this.ModifytimeFacility.equals(other.getModifytimeFacility())));

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

		if (getName() != null) {
			_hashCode += getName().hashCode();
		}
		if (getFacilityAdress() != null) {
			_hashCode += getFacilityAdress().hashCode();
		}


		if (getCreatetimeFacility() != null) {
			_hashCode += getCreatetimeFacility().hashCode();
		}
		if (getModifytimeFacility() != null) {
			_hashCode += getModifytimeFacility().hashCode();
		}
		if (getValiditytimeFacility() != null) {
			_hashCode += getValiditytimeFacility().hashCode();
		}


		__hashCodeCalc = false;
		return _hashCode;
	}



	public String getFacilityID() {
		return FacilityID;
	}
	public void setFacilityID(String facilityID) {
		FacilityID = facilityID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getValiditytimeFacility() {
		return ValiditytimeFacility;
	}
	public void setValiditytimeFacility(Date validitytimeFacility) {
		ValiditytimeFacility = validitytimeFacility;
	}
	public Date getCreatetimeFacility() {
		return CreatetimeFacility;
	}
	public void setCreatetimeFacility(Date createtimeFacility) {
		CreatetimeFacility = createtimeFacility;
	}
	public Date getModifytimeFacility() {
		return ModifytimeFacility;
	}
	public void setModifytimeFacility(Date modifytimeFacility) {
		ModifytimeFacility = modifytimeFacility;
	}
	public Adress getFacilityAdress() {
		return FacilityAdress;
	}
	public void setFacilityAdress(Adress facilityAdress) {
		FacilityAdress = facilityAdress;
	}

}
