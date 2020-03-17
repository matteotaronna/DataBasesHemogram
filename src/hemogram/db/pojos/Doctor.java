package hemogram.db.pojos;

import java.io.Serializable;

public class Doctor implements Serializable{

	private static final long serialVersionUID = -7594689062301279876L;
	
	private Integer id;
	private String name;
	private String surname;
	private String work_user;
	private String hospital;
	private String speciality;
	
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
		this.speciality = speciality;
	}
	

	public Doctor(String name, String surname, String work_user, String hospital, String speciality) {
		super();
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
		this.speciality = speciality;
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
				+ ", hospital=" + hospital + ", speciality=" + speciality + "]";
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}	

}
