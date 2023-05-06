package prescriptomeCore;

import java.util.Date;
/**
 * @author Jean Nikiema
 *
 */
public class PatientGroup {
	private String DatabaseSource;
	private String GroupID;
	private Date Createtime;
	private Date Modifytime;

	public PatientGroup(String DatabaseSource,
			String GroupID,
			Date Createtime,
			Date Modifytime) {
		this.setDatabaseSource(DatabaseSource);
		this.setGroupID(GroupID);
		this.setCreatetime(Createtime);
		this.setModifytime(Modifytime);
	}


	/**
	 * 
	 */
	public PatientGroup() {
		super();
		// TODO Auto-generated constructor stub
	}


	private java.lang.Object __equalsCalc = null;

	@Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof PatientGroup)) return false;
		PatientGroup other = (PatientGroup) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true &&
				((this.DatabaseSource==null && other.getDatabaseSource()==null) ||
						(this.DatabaseSource!=null &&
						this.DatabaseSource.equals(other.getDatabaseSource())))  &&

				((this.GroupID==null && other.getGroupID()==null) ||
						(this.GroupID!=null &&
						this.GroupID.equals(other.getGroupID())))  &&


				((this.Createtime==null && other.getCreatetime()==null) ||
						(this.Createtime!=null &&
						this.Createtime.equals(other.getCreatetime())))  &&

				((this.Modifytime==null && other.getModifytime()==null) ||
						(this.Modifytime!=null &&
						this.Modifytime.equals(other.getModifytime())));

		__equalsCalc = null;
		return _equals;
	}

	public Date getModifytime() {
		return Modifytime;
	}

	public void setModifytime(Date modifytime) {
		Modifytime = modifytime;
	}
	public String getDatabaseSource() {
		return DatabaseSource;
	}

	public void setDatabaseSource(String databaseSource) {
		DatabaseSource = databaseSource;
	}

	public String getGroupID() {
		return GroupID;
	}

	public void setGroupID(String groupID) {
		GroupID = groupID;
	}

	public Date getCreatetime() {
		return Createtime;
	}

	public void setCreatetime(Date createtime) {
		Createtime = createtime;
	}


	private boolean __hashCodeCalc = false;
	@Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;

		if (getDatabaseSource() != null) {
			_hashCode += getDatabaseSource().hashCode();
		}
		if (getGroupID() != null) {
			_hashCode += getGroupID().hashCode();
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
