package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
/**
 * @author Jean Nikiema
 * 
 */
public class CauseDeathInformation {

	private Date Createtime;
	private Date Modifytime;
	private IRI CauseOfDeathICD;

	public CauseDeathInformation( Date Createtime,
			Date Modifytime,
			String Cause ){
		String ex = "http://prescriptomeDataModel.ca/";
		IRI causeICD = Values.iri(ex,Cause);
		this.setCauseOfDeathICD(causeICD);
		this.setCreatetime(Createtime);
		this.setModifytime(Modifytime);
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof CauseDeathInformation)) return false;
		CauseDeathInformation other = (CauseDeathInformation) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 

				((this.CauseOfDeathICD==null && other.getCauseOfDeathICD()==null) || 
						(this.CauseOfDeathICD!=null &&
						this.CauseOfDeathICD.equals(other.getCauseOfDeathICD())))  &&


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

	public IRI getCauseOfDeathICD() {
		return CauseOfDeathICD;
	}

	public void setCauseOfDeathICD(IRI causeOfDeathICD) {
		CauseOfDeathICD = causeOfDeathICD;
	}

	private boolean __hashCodeCalc = false;
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;

		if (getCauseOfDeathICD() != null) {
			_hashCode += getCauseOfDeathICD().hashCode();
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
