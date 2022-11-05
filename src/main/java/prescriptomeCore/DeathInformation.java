package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;

/**
 * @author Jean Nikiema
 * 
 */
public class DeathInformation {
	private Date Createtime;
	private Date Modifytime;
	private Date DeathDate ;



	public DeathInformation(Date createtime, Date modifytime, Date deathDate) {
		this.Createtime = createtime;
		this.Modifytime = modifytime;
		this.DeathDate = deathDate;
	}

	private java.lang.Object __equalsCalc = null;

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

				((this.DeathDate==null && other.getDeathDate()==null) || 
						(this.DeathDate!=null &&
						this.DeathDate.equals(other.getDeathDate())))  &&


				((this.Createtime==null && other.getCreatetime()==null) || 
						(this.Createtime!=null &&
						this.Createtime.equals(other.getCreatetime())))  &&

				((this.Modifytime==null && other.getModifytime()==null) || 
						(this.Modifytime!=null &&
						this.Modifytime.equals(other.getModifytime())));

		__equalsCalc = null;
		return _equals;
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
	public Date getDeathDate() {
		return DeathDate;
	}
	public void setDeathDate(Date deathDate) {
		DeathDate = deathDate;
	}
	public DeathInformation() {

	}

	private boolean __hashCodeCalc = false;
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
