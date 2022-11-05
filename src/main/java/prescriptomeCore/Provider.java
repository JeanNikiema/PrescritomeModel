package prescriptomeCore;

import java.util.Date;

/**
 * @author Jean Nikiema
 * 
 */
public class Provider extends Person{
	private String ProviderRole;
	private String ProviderID;
	private Facility ProviderFacility;
	private Date ValiditytimeProvider;
	private Date CreatetimeProvider;
	private Date ModifytimeProvider;


	/**
	 * @param ValiditytimeProvider
	 * @param CreatetimeProvider
	 * @param ModifytimeProvider
	 * @param ProviderRole
	 * @param ProviderID
	 * @param ProviderFacility
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
	public Provider( Date ValiditytimeProvider, Date CreatetimeProvider, Date ModifytimeProvider, String ProviderRole,String ProviderID,Facility ProviderFacility, int BirthMonth, int BirthYear, int BirthDay, String identifierSource, String DataBaseIdentifier,
			String genderCode, String EthnicID, String SexeCode, String Name, Date Validitytime, boolean DeathIndicator,
			Date Createtime, Date Modifytime, Adress adress) {
		super(BirthMonth, BirthYear, BirthDay, identifierSource, DataBaseIdentifier, genderCode, EthnicID, SexeCode, Name,
				Validitytime, DeathIndicator, Createtime, Modifytime,adress);
		// TODO Auto-generated constructor stub

		this.setProviderRole(ProviderRole);
		this.setProviderID(ProviderID); 
		this.setProviderFacility(ProviderFacility);
		this.setValiditytimeProvider(ValiditytimeProvider);
		this.setCreatetimeProvider(CreatetimeProvider);
		this.setModifytimeProvider(ModifytimeProvider);
		
	}


	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Provider)) return false;
		Provider other = (Provider) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 

				((this.getProviderRole()==null && other.getProviderRole()==null) || 
						(this.getProviderRole()!=null &&
						this.getProviderRole().equals(other.getProviderRole())))  &&


				((this.getProviderID()==null && other.getProviderID()==null) || 
						(this.getProviderID()!=null &&
						this.getProviderID().equals(other.getProviderID())))  &&

				((this.getProviderFacility()==null && other.getProviderFacility()==null) || 
						(this.getProviderFacility()!=null &&
						this.getProviderFacility().equals(other.getProviderFacility())))  &&

				this.getBirthMonth()==other.getBirthMonth() &&

				this.getBirthYear()==other.getBirthYear() &&

				this.getBirthDay()==other.getBirthDay() &&

						((this.getAdress()==null && other.getAdress()==null) || 
								(this.getAdress()!=null &&
								this.getAdress().equals(other.getAdress())))  &&

				((this.getIdentifierSource()==null && other.getIdentifierSource()==null) || 
						(this.getIdentifierSource()!=null &&
						this.getIdentifierSource().equals(other.getIdentifierSource())))  &&

				((this.getDataBaseIdentifier()==null && other.getDataBaseIdentifier()==null) || 
						(this.getDataBaseIdentifier()!=null &&
						this.getDataBaseIdentifier().equals(other.getDataBaseIdentifier())))  &&

				((this.getGenderCode()==null && other.getGenderCode()==null) || 
						(this.getGenderCode()!=null &&
						this.getGenderCode().equals(other.getGenderCode())))  &&

				((this.getEthnicID()==null && other.getEthnicID()==null) || 
						(this.getEthnicID()!=null &&
						this.getEthnicID().equals(other.getEthnicID())))  &&

				((this.getSexeCode()==null && other.getSexeCode()==null) || 
						(this.getSexeCode()!=null &&
						this.getSexeCode().equals(other.getSexeCode())))  &&

				((this.getName()==null && other.getName()==null) || 
						(this.getName()!=null &&
						this.getName().equals(other.getName())))  &&

				((this.getValiditytime()==null && other.getValiditytime()==null) || 
						(this.getValiditytime()!=null &&
						this.getValiditytime().equals(other.getValiditytime())))  &&

				((this.getValiditytimeProvider()==null && other.getValiditytimeProvider()==null) || 
						(this.getValiditytimeProvider()!=null &&
						this.getValiditytimeProvider().equals(other.getValiditytimeProvider())))  &&

				((this.getCreatetimeProvider()==null && other.getCreatetimeProvider()==null) || 
						(this.getCreatetimeProvider()!=null &&
						this.getCreatetimeProvider().equals(other.getCreatetimeProvider())))  &&

				((this.getModifytimeProvider()==null && other.getModifytimeProvider()==null) || 
						(this.getModifytimeProvider()!=null &&
						this.getModifytimeProvider().equals(other.getModifytimeProvider())))  &&



				this.isDeathIndicator() ==other.isDeathIndicator()  &&

				((this.getCreatetime()==null && other.getCreatetime()==null) || 
						(this.getCreatetime()!=null &&
						this.getCreatetime().equals(other.getCreatetime())))  &&

				((this.getModifytime()==null && other.getModifytime()==null) || 
						(this.getModifytime()!=null &&
						this.getModifytime().equals(other.getModifytime())));

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


		_hashCode += this.getBirthMonth();
		_hashCode += this.getBirthYear();
		_hashCode += this.getBirthDay();

		if (getProviderRole() != null) {
			_hashCode += getProviderRole().hashCode();
		}
		if (getProviderID() != null) {
			_hashCode += getProviderID().hashCode();
		}

		if (getProviderFacility() != null) {
			_hashCode += getProviderFacility().hashCode();
		}
		if (getAdress() != null) {
			_hashCode += getAdress().hashCode();
		}
		
		if (this.getDataBaseIdentifier() != null) {
			_hashCode += this.getDataBaseIdentifier().hashCode();
		}
		if (this.getGenderCode() != null) {
			_hashCode += this.getGenderCode().hashCode();
		}
		if (this.getEthnicID() != null) {
			_hashCode += this.getEthnicID().hashCode();
		}
		if (this.getSexeCode() != null) {
			_hashCode += this.getSexeCode().hashCode();
		}
		if (this.getName() != null) {
			_hashCode += this.getName().hashCode();
		}
		if (this.getValiditytime() != null) {
			_hashCode += this.getValiditytime().hashCode();
		}


		if (this.getIdentifierSource() != null) {
			_hashCode += this.getIdentifierSource().hashCode();
		}

		if (this.getValiditytime() != null) {
			_hashCode += this.getValiditytime().hashCode();
		}
		if (this.getCreatetime() != null) {
			_hashCode += this.getCreatetime().hashCode();
		}
		if (this.getModifytime() != null) {
			_hashCode += this.getModifytime().hashCode();
		}

		if (this.getValiditytimeProvider() != null) {
			_hashCode += this.getValiditytimeProvider().hashCode();
		}
		if (this.getCreatetimeProvider() != null) {
			_hashCode += this.getCreatetimeProvider().hashCode();
		}
		if (this.getModifytimeProvider() != null) {
			_hashCode += this.getModifytimeProvider().hashCode();
		}

		__hashCodeCalc = false;
		return _hashCode;
	}



	public Facility getProviderFacility() {
		return ProviderFacility;
	}


	public void setProviderFacility(Facility providerFacility) {
		ProviderFacility = providerFacility;
	}


	public String getProviderID() {
		return ProviderID;
	}


	public void setProviderID(String providerID) {
		ProviderID = providerID;
	}


	public String getProviderRole() {
		return ProviderRole;
	}


	public void setProviderRole(String providerRole) {
		ProviderRole = providerRole;
	}


	public Date getValiditytimeProvider() {
		return ValiditytimeProvider;
	}


	public void setValiditytimeProvider(Date validitytimeProvider) {
		ValiditytimeProvider = validitytimeProvider;
	}


	public Date getCreatetimeProvider() {
		return CreatetimeProvider;
	}


	public void setCreatetimeProvider(Date createtimeProvider) {
		CreatetimeProvider = createtimeProvider;
	}


	public Date getModifytimeProvider() {
		return ModifytimeProvider;
	}


	public void setModifytimeProvider(Date modifytimeProvider) {
		ModifytimeProvider = modifytimeProvider;
	}

}
