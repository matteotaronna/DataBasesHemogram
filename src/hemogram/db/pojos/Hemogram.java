package hemogram.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Hemogram implements Serializable
{

	private static final long serialVersionUID = -6292926181397696502L;

	private Integer id;
	private Date dob;
	private String comments;
	private Integer patient_id;
	private Integer doctor_id;
	private Integer analyzer_id;
	
	public Hemogram() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hemogram(Integer id, Date dob, String comments, Integer patient_id, Integer doctor_id, Integer analyzer_id) {
		super();
		this.id = id;
		this.dob = dob;
		this.comments = comments;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.analyzer_id = analyzer_id;
	}

	public Hemogram(Date dob, String comments, Integer patient_id, Integer doctor_id, Integer analyzer_id) {
		super();
		this.dob = dob;
		this.comments = comments;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.analyzer_id = analyzer_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hemogram other = (Hemogram) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hemogram [id=" + id + ", dob=" + dob + ", comments=" + comments + ", patient_id=" + patient_id
				+ ", doctor_id=" + doctor_id + ", analizer_id=" + analyzer_id + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getAnalyzer_id() {
		return analyzer_id;
	}

	public void setAnalizer_id(Integer analizer_id) {
		this.analyzer_id = analizer_id;
	}
}
