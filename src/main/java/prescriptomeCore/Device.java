package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 *
 */
public class Device {

	private String DeviceID;
	private String DeviceCode;
	private Date Validitytime;
	private Date Createtime;
	private Date Modifytime;
	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return DeviceID;
	}
	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}
	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return DeviceCode;
	}
	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		DeviceCode = deviceCode;
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
	/**
	 * @param deviceID
	 * @param deviceCode
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Device(String deviceID, String deviceCode, Date validitytime, Date createtime, Date modifytime) {
		super();
		this.DeviceID = deviceID;
		this.DeviceCode = deviceCode;
		this.Validitytime = validitytime;
		this.Createtime = createtime;
		this.Modifytime = modifytime;
	}

	
	

	/**
	 * 
	 */
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}




	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Device)) return false;
		Device other = (Device) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&



				((this.DeviceID==null && other.getDeviceID()==null) ||
						(this.DeviceID!=null &&
						this.DeviceID.equals(other.getDeviceID())))  &&

				((this.DeviceCode==null && other.getDeviceCode()==null) ||
						(this.DeviceCode!=null &&
						this.DeviceCode.equals(other.getDeviceCode())))  &&


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
		if (getDeviceID() != null) {
			_hashCode += getDeviceID().hashCode();
		}

		if (getDeviceCode() != null) {
			_hashCode += getDeviceCode().hashCode();
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
