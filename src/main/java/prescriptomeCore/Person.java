package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 *
 */
/**
 * @author Jean Nikiema
 *
 */
public class Person {

	private int BirthMonth;
	private int BirthYear;
	private int BirthDay; // 
	private String identifierSource;
	private String DataBaseIdentifier;
	private String genderCode;
	private String EthnicID; //
	private String SexeCode;
	private String Name;
	private Date Validitytime;
	private boolean DeathIndicator;
	private Date Createtime;
	private Date Modifytime;
	private Adress adress;

	/**
	 * @param BirthMonth
	 * @param BirthYear
	 * @param BirthDay
	 * @param identifierSource
	 * @param DataBaseIdentifier
	 * @param genderCode
	 * @param EthnicID
	 * @param SexeCode
	 * @param Name
	 * @param Validitytime
	 * @param DeathIndicator
	 * @param Createtime
	 * @param Modifytime
	 * @param adress
	 */

	
	public Person(
			int BirthMonth,
			int BirthYear,
			int BirthDay,
			String identifierSource,
			String DataBaseIdentifier,
			String genderCode,
			String EthnicID,
			String SexeCode,
			String Name,
			Date Validitytime,
			boolean DeathIndicator,
			Date Createtime,
			Date Modifytime,Adress adress) {

		this.setBirthMonth(BirthMonth);
		this.setBirthYear(BirthYear);
		this.setBirthDay(BirthDay);
		this.setIdentifierSource(identifierSource);
		this.setDataBaseIdentifier(DataBaseIdentifier);
		this.setGenderCode(genderCode);
		this.setEthnicID(EthnicID);
		this.setSexeCode(SexeCode);
		this.setName(Name);
		this.setValiditytime(Validitytime);
		this.setDeathIndicator(DeathIndicator);
		this.setCreatetime(Createtime);
		this.setModifytime(Modifytime);
		this.setAdress(adress);
	}


	/**
	 * 
	 */
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}


	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Person)) return false;
		Person other = (Person) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&
				this.BirthMonth==other.getBirthMonth() &&

				this.BirthYear==other.getBirthYear() &&

				this.BirthDay==other.getBirthDay() &&

						((this.adress==null && other.getAdress()==null) ||
								(this.adress!=null &&
								this.adress.equals(other.getAdress())))  &&

				((this.identifierSource==null && other.getIdentifierSource()==null) ||
						(this.identifierSource!=null &&
						this.identifierSource.equals(other.getIdentifierSource())))  &&

				((this.DataBaseIdentifier==null && other.getDataBaseIdentifier()==null) ||
						(this.DataBaseIdentifier!=null &&
						this.DataBaseIdentifier.equals(other.getDataBaseIdentifier())))  &&

				((this.genderCode==null && other.getGenderCode()==null) ||
						(this.genderCode!=null &&
						this.genderCode.equals(other.getGenderCode())))  &&

				((this.EthnicID==null && other.getEthnicID()==null) ||
						(this.EthnicID!=null &&
						this.EthnicID.equals(other.getEthnicID())))  &&

				((this.SexeCode==null && other.getSexeCode()==null) ||
						(this.SexeCode!=null &&
						this.SexeCode.equals(other.getSexeCode())))  &&

				((this.Name==null && other.getName()==null) ||
						(this.Name!=null &&
						this.Name.equals(other.getName())))  &&

				((this.Validitytime==null && other.getValiditytime()==null) ||
						(this.Validitytime!=null &&
						this.Validitytime.equals(other.getValiditytime())))  &&


				this.DeathIndicator ==other.isDeathIndicator()  &&

				((this.Createtime==null && other.getCreatetime()==null) ||
						(this.Createtime!=null &&
						this.Createtime.equals(other.getCreatetime())))  &&

				((this.Modifytime==null && other.getModifytime()==null) ||
						(this.Modifytime!=null &&
						this.Modifytime.equals(other.getModifytime())));

		__equalsCalc = null;
		return _equals;
	}

	public int getBirthMonth() {
		return BirthMonth;
	}



	public void setBirthMonth(int birthMonth) {
		BirthMonth = birthMonth;
	}



	public int getBirthYear() {
		return BirthYear;
	}



	public void setBirthYear(int birthYear) {
		BirthYear = birthYear;
	}



	public int getBirthDay() {
		return BirthDay;
	}



	public void setBirthDay(int birthDay) {
		BirthDay = birthDay;
	}



	public String getIdentifierSource() {
		return identifierSource;
	}



	public void setIdentifierSource(String identifierSource) {
		this.identifierSource = identifierSource;
	}



	public String getDataBaseIdentifier() {
		return DataBaseIdentifier;
	}



	public void setDataBaseIdentifier(String dataBaseIdentifier) {
		DataBaseIdentifier = dataBaseIdentifier;
	}



	public String getGenderCode() {
		return genderCode;
	}



	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}



	public String getEthnicID() {
		return EthnicID;
	}



	public void setEthnicID(String ethnicID) {
		EthnicID = ethnicID;
	}



	public String getSexeCode() {
		return SexeCode;
	}



	public void setSexeCode(String sexeCode) {
		SexeCode = sexeCode;
	}



	public Date getValiditytime() {
		return Validitytime;
	}



	public void setValiditytime(Date validitytime) {
		Validitytime = validitytime;
	}



	public boolean isDeathIndicator() {
		return DeathIndicator;
	}



	public void setDeathIndicator(boolean deathIndicator) {
		DeathIndicator = deathIndicator;
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



	public Adress getAdress() {
		return adress;
	}



	public void setAdress(Adress adress) {
		this.adress = adress;
	}


	private boolean __hashCodeCalc = false;
	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;


		_hashCode += BirthMonth;
		_hashCode += BirthYear;
		_hashCode += BirthDay;

		if (getDataBaseIdentifier() != null) {
			_hashCode += getDataBaseIdentifier().hashCode();
		}
		if (getAdress() != null) {
			_hashCode += getAdress().hashCode();
		}

		if (getGenderCode() != null) {
			_hashCode += getGenderCode().hashCode();
		}
		if (getEthnicID() != null) {
			_hashCode += getEthnicID().hashCode();
		}
		if (getSexeCode() != null) {
			_hashCode += getSexeCode().hashCode();
		}
		if (getName() != null) {
			_hashCode += getName().hashCode();
		}
		if (getValiditytime() != null) {
			_hashCode += getValiditytime().hashCode();
		}


		if (getIdentifierSource() != null) {
			_hashCode += getIdentifierSource().hashCode();
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



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}
}




