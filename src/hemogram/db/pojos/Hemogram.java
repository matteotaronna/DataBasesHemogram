package hemogram.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Hemogram implements Serializable
{

	private static final long serialVersionUID = -6292926181397696502L;

	private Integer id;
	private Date dob;
	private String comments;
	private Patient patient;
	private Doctor doctor;
	private Analyzer analyzer;
	
	public Hemogram() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hemogram(Date dob, String comments, Patient patient, Doctor doctor, Analyzer analyzer) {
		super();
		this.dob = dob;
		this.comments = comments;
		this.patient = patient;
		this.doctor = doctor;
		this.analyzer = analyzer;
	}
	

	public Hemogram(Integer id, Date dob, String comments, Patient patient, Doctor doctor, Analyzer analyzer) {
		super();
		this.id = id;
		this.dob = dob;
		this.comments = comments;
		this.patient = patient;
		this.doctor = doctor;
		this.analyzer = analyzer;
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
		return "Hemogram [id=" + id + ", dob=" + dob + ", comments=" + comments + ", patient=" + patient + ", doctor="
				+ doctor + ", analyzer=" + analyzer + "]";
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
}
