package prescriptomeCore;

import java.util.Date;

/**
 * @author Jean Nikiema
 *
 */
public class DeathInformation {
	private Date createtime;
	private Date modifytime;
	private Date deathDate ;



	public DeathInformation(Date createtime, Date modifytime, Date deathDate) {
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.deathDate = deathDate;
	}
	
	public DeathInformation() {
		super() ;
	}
	

	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof DeathInformation)) return false;
		DeathInformation other = (DeathInformation) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&

				((this.deathDate==null && other.getDeathDate()==null) ||
						(this.deathDate!=null &&
						this.deathDate.equals(other.getDeathDate())))  &&


				((this.createtime==null && other.getCreatetime()==null) ||
						(this.createtime!=null &&
						this.createtime.equals(other.getCreatetime())))  &&

				((this.modifytime==null && other.getModifytime()==null) ||
						(this.modifytime!=null &&
						this.modifytime.equals(other.getModifytime())));

		__equalsCalc = null;
		return _equals;
	}

	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	private boolean __hashCodeCalc = false;
	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;

		if (getDeathDate() != null) {
			_hashCode += getDeathDate().hashCode();
		}


		if (getCreatetime() != null) {
			_hashCode += getCreatetime().hashCode();
		}
		if (getModifytime() != null) {
			_hashCode += getModifytime().hashCode();
		}

		__hashCodeCalc = false;
		return _hashCode;
	}


}
