package prescriptomeCore;

import java.util.Date;

import org.eclipse.rdf4j.model.IRI;
/**
 * @author Jean Nikiema
 *
 */
public class Observation {

	private String ObservationID;
	private Date reportDate;
	private Encounter Encounter;
	// type of observation code in LOINC or SNOMED
	private IRI typeCode;
	private IRI originaleTypeCode;
	//information source type code
	private IRI informationSourceTypeCode;
	// observation results
	private String results;
	private IRI resultsSNOMED;
	private int resultsInt;
	private IRI resultUNITsource;
	private IRI resultUnitUCUM;
	private Date startDate;
	private Date endDate;
	private Date Validitytime;
	private Date Createtime;
	private Date Modifytime;
	/**
	 * @return the observationID
	 */
	public String getObservationID() {
		return ObservationID;
	}
	/**
	 * @param observationID the observationID to set
	 */
	public void setObservationID(String observationID) {
		ObservationID = observationID;
	}
	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
	 * @return the results
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(String results) {
		this.results = results;
	}
	/**
	 * @return the resultsSNOMED
	 */
	public IRI getResultsSNOMED() {
		return resultsSNOMED;
	}
	/**
	 * @param resultsSNOMED the resultsSNOMED to set
	 */
	public void setResultsSNOMED(IRI resultsSNOMED) {
		this.resultsSNOMED = resultsSNOMED;
	}
	/**
	 * @return the resultsInt
	 */
	public int getResultsInt() {
		return resultsInt;
	}
	/**
	 * @param resultsInt the resultsInt to set
	 */
	public void setResultsInt(int resultsInt) {
		this.resultsInt = resultsInt;
	}
	/**
	 * @return the resultUNITsource
	 */
	public IRI getResultUNITsource() {
		return resultUNITsource;
	}
	/**
	 * @param resultUNITsource the resultUNITsource to set
	 */
	public void setResultUNITsource(IRI resultUNITsource) {
		this.resultUNITsource = resultUNITsource;
	}
	/**
	 * @return the resultUnitUCUM
	 */
	public IRI getResultUnitUCUM() {
		return resultUnitUCUM;
	}
	/**
	 * @param resultUnitUCUM the resultUnitUCUM to set
	 */
	public void setResultUnitUCUM(IRI resultUnitUCUM) {
		this.resultUnitUCUM = resultUnitUCUM;
	}
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
	 * @param observationID
	 * @param reportDate
	 * @param encounter
	 * @param typeCode
	 * @param originaleTypeCode
	 * @param informationSourceTypeCode
	 * @param results
	 * @param resultsSNOMED
	 * @param resultsInt
	 * @param resultUNITsource
	 * @param resultUnitUCUM
	 * @param startDate
	 * @param endDate
	 * @param validitytime
	 * @param createtime
	 * @param modifytime
	 */
	public Observation(String observationID, Date reportDate, prescriptomeCore.Encounter encounter, IRI typeCode,
			IRI originaleTypeCode, IRI informationSourceTypeCode, String results, IRI resultsSNOMED, int resultsInt,
			IRI resultUNITsource, IRI resultUnitUCUM, Date startDate, Date endDate, Date validitytime, Date createtime,
			Date modifytime) {
		super();
		this.ObservationID = observationID;
		this.reportDate = reportDate;
		this.Encounter = encounter;
		this.typeCode = typeCode;
		this.originaleTypeCode = originaleTypeCode;
		this.informationSourceTypeCode = informationSourceTypeCode;
		this.results = results;
		this.resultsSNOMED = resultsSNOMED;
		this.resultsInt = resultsInt;
		this.resultUNITsource = resultUNITsource;
		this.resultUnitUCUM = resultUnitUCUM;
		this.startDate = startDate;
		this.endDate = endDate;
		this.Validitytime = validitytime;
		this.Createtime = createtime;
		this.Modifytime = modifytime;
	}


	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Observation)) return false;
		Observation other = (Observation) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&



				((this.ObservationID==null && other.getObservationID()==null) ||
						(this.ObservationID!=null &&
						this.ObservationID.equals(other.getObservationID())))  &&

				((this.reportDate==null && other.getReportDate()==null) ||
						(this.reportDate!=null &&
						this.reportDate.equals(other.getReportDate())))  &&

				((this.Encounter==null && other.getEncounter()==null) ||
						(this.Encounter!=null &&
						this.Encounter.equals(other.getEncounter())))  &&

				((this.typeCode==null && other.getTypeCode()==null) ||
						(this.typeCode!=null &&
						this.typeCode.equals(other.getTypeCode())))  &&

				((this.originaleTypeCode==null && other.getOriginaleTypeCode()==null) ||
						(this.originaleTypeCode!=null &&
						this.originaleTypeCode.equals(other.getOriginaleTypeCode())))  &&

				((this.informationSourceTypeCode==null && other.getInformationSourceTypeCode()==null) ||
						(this.informationSourceTypeCode!=null &&
						this.informationSourceTypeCode.equals(other.getInformationSourceTypeCode())))  &&

				((this.getResults()==null && other.getResults()==null) ||
						(this.getResults()!=null &&
						this.getResults().equals(other.getResults())))  &&

				((this.resultsSNOMED==null && other.getResultsSNOMED()==null) ||
						(this.resultsSNOMED!=null &&
						this.resultsSNOMED.equals(other.getResultsSNOMED())))  &&

				((this.startDate==null && other.getStartDate()==null) ||
						(this.startDate!=null &&
						this.startDate.equals(other.getStartDate())))  &&

				(this.resultsInt==other.getResultsInt())  &&

				((this.resultUNITsource==null && other.getResultUNITsource()==null) ||
						(this.resultUNITsource!=null &&
						this.resultUNITsource.equals(other.getResultUNITsource())))  &&

				((this.resultUnitUCUM==null && other.getResultUnitUCUM()==null) ||
						(this.resultUnitUCUM!=null &&
						this.resultUnitUCUM.equals(other.getResultUnitUCUM())))  &&

				((this.endDate==null && other.getEndDate()==null) ||
						(this.endDate!=null &&
						this.endDate.equals(other.getEndDate())))  &&


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
		if (getObservationID() != null) {
			_hashCode += getObservationID().hashCode();
		}

		if (getReportDate() != null) {
			_hashCode += getReportDate().hashCode();
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

		if (getInformationSourceTypeCode() != null) {
			_hashCode += getInformationSourceTypeCode().hashCode();
		}
		if (getResults() != null) {
			_hashCode += getResults().hashCode();
		}


		if (getResultsSNOMED() != null) {
			_hashCode += getResultsSNOMED().hashCode();
		}


		_hashCode += getResultsInt();

		if (getResultUNITsource() != null) {
			_hashCode += getResultUNITsource().hashCode();
		}
		if (getResultUnitUCUM() != null) {
			_hashCode += getResultUnitUCUM().hashCode();
		}
		if (getStartDate() != null) {
			_hashCode += getStartDate().hashCode();
		}
		if (getEndDate() != null) {
			_hashCode += getEndDate().hashCode();
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
