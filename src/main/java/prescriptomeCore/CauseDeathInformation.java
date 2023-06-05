package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;
/**
 * @author Jean Nikiema
 *
 */
public class CauseDeathInformation {

	private Date createtime;
	private Date modifytime;
	private IRI causeOfDeathICD;

	public CauseDeathInformation( Date createtime, Date modifytime, String cause ){
		String ex = "http://prescriptomeDataModel.ca/";
		IRI causeICD = Values.iri(ex,cause);
		this.setCauseOfDeathICD(causeICD);
		this.setCreatetime(createtime);
		this.setModifytime(modifytime);
	}


	private java.lang.Object __equalsCalc = null;

	@Override
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

				((this.causeOfDeathICD==null && other.getCauseOfDeathICD()==null) ||
						(this.causeOfDeathICD!=null &&
						this.causeOfDeathICD.equals(other.getCauseOfDeathICD())))  &&


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

	
	public IRI getCauseOfDeathICD() {
		return causeOfDeathICD;
	}
	

	public void setCauseOfDeathICD(IRI causeOfDeathICD) {
		this.causeOfDeathICD = causeOfDeathICD;
	}

	
	private boolean __hashCodeCalc = false;
	@Override
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
