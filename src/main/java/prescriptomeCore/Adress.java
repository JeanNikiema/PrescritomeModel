package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 *
 */
public class Adress {
	private String AdressID;
	private String ligne1;
	private String ligne2;
	private String City;
	private String State;
	private String ZipCode;
	private String Country;
	private Date Validitytime;
	private boolean Validity;
	private Date Createtime;
	private Date Modifytime;


	public Adress(
			String adressID,
			String ligne1,
			String ligne2,
			String City,
			String State,
			String ZipCode,
			String Country,
			Date Validitytime,
			boolean Validity,
			Date Createtime,
			Date Modifytime) {

		this.setAdressID(adressID);
		this.setLigne1(ligne1);
		this.setLigne2(ligne2);
		this.setCity(City);
		this.setState(State);
		this.setZipCode(ZipCode);
		this.setCountry(Country);
		this.setValiditytime(Validitytime);
		this.setValidity(Validity);
		this.setCreatetime(Createtime);
		this.setModifytime(Modifytime);
	}



	/**
	 * 
	 */
	public Adress() {
		super();
		// TODO Auto-generated constructor stub
	}



	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Adress)) return false;
		Adress other = (Adress) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&
				((this.ligne1==null && other.getLigne1()==null) ||
						(this.ligne1!=null &&
						this.ligne1.equals(other.getLigne1())))  &&

				((this.ligne2==null && other.getLigne2()==null) ||
						(this.ligne2!=null &&
						this.ligne2.equals(other.getLigne2())))  &&

				((this.City==null && other.getCity()==null) ||
						(this.City!=null &&
						this.City.equals(other.getCity())))  &&

				((this.State==null && other.getState()==null) ||
						(this.State!=null &&
						this.State.equals(other.getState())))  &&

				((this.ZipCode==null && other.getZipCode()==null) ||
						(this.ZipCode!=null &&
						this.ZipCode.equals(other.getZipCode())))  &&

				((this.Country==null && other.getCountry()==null) ||
						(this.Country!=null &&
						this.Country.equals(other.getCountry())))  &&

				((this.Validitytime==null && other.getValiditytime()==null) ||
						(this.Validitytime!=null &&
						this.Validitytime.equals(other.getValiditytime())))  &&


				this.Validity ==other.isValidity()  &&

				((this.Createtime==null && other.getCreatetime()==null) ||
						(this.Createtime!=null &&
						this.Createtime.equals(other.getCreatetime())))  &&

				((this.Modifytime==null && other.getModifytime()==null) ||
						(this.Modifytime!=null &&
						this.Modifytime.equals(other.getModifytime())));

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


		if (getLigne1() != null) {
			_hashCode += getLigne1().hashCode();
		}


		if (getLigne2() != null) {
			_hashCode += getLigne2().hashCode();
		}

		if (getCity() != null) {
			_hashCode += getCity().hashCode();
		}

		if (getState() != null) {
			_hashCode += getState().hashCode();
		}

		if (getZipCode() != null) {
			_hashCode += getZipCode().hashCode();
		}

		if (getCountry() != null) {
			_hashCode += getCountry().hashCode();
		}

		if (getValiditytime() != null) {
			_hashCode += getValiditytime().hashCode();
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




	public String getLigne1() {
		return ligne1;
	}



	public void setLigne1(String ligne1) {
		this.ligne1 = ligne1;
	}



	public String getLigne2() {
		return ligne2;
	}



	public void setLigne2(String ligne2) {
		this.ligne2 = ligne2;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	public String getState() {
		return State;
	}



	public void setState(String state) {
		State = state;
	}



	public String getZipCode() {
		return ZipCode;
	}



	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}



	public String getCountry() {
		return Country;
	}



	public void setCountry(String country) {
		Country = country;
	}



	public Date getValiditytime() {
		return Validitytime;
	}



	public void setValiditytime(Date validitytime) {
		Validitytime = validitytime;
	}



	public boolean isValidity() {
		return Validity;
	}



	public void setValidity(boolean validity) {
		Validity = validity;
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




	public String getAdressID() {
		return AdressID;
	}




	public void setAdressID(String adressID) {
		AdressID = adressID;
	}



}
