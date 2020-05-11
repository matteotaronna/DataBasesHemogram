package hemogram.db.pojos;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name  = "doctor")
@XmlType(propOrder = {"name", "surname", "work_user", "hospital","specialty"})
public class Doctor implements Serializable{

	private static final long serialVersionUID = -7594689062301279876L;
	
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	private String work_user;
	@XmlElement
	private String hospital;
	@XmlElement
	private String specialty;
	@XmlTransient
	private List<Patient> patients;
	
	public Doctor() {
		super();
	}

	public Doctor(Integer id, String name, String surname, String work_user, String hospital, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
		this.specialty = speciality;
	}
	

	public Doctor(String name, String surname, String work_user, String hospital, String speciality) {
		super();
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
		this.specialty = speciality;
	}

	public Doctor(Integer id, String name, String surname, String work_user, String hospital, String specialty,
			List<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
		this.specialty = specialty;
		this.patients = patients;
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctors [id=" + id + ", name=" + name + ", surname=" + surname + ", work_user=" + work_user
				+ ", hospital=" + hospital + ", speciality=" + specialty + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getWork_user() {
		return work_user;
	}

	public void setWork_user(String work_user) {
		this.work_user = work_user;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String speciality) {
		this.specialty = speciality;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}	

	
}
