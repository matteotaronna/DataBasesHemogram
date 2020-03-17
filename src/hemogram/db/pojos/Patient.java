package hemogram.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Patient implements Serializable
{
	private static final long serialVersionUID = -3478632845146919461L;
	
	private Integer id;
	private String name;
	private String surname;
	private Date dob;
	private String dni;
	private Integer doctor_id;
	private Integer analizer_id;
	
	public Patient() {
		super();
	}
	
	public Patient(Integer id, String name, String surname, Date dob, String dni, Integer doctor_id, Integer analizer_id) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.dni = dni;
		this.doctor_id = doctor_id;
		this.analizer_id = analizer_id;
	}
	public Patient(String name, String surname, Date dob, String dni, Integer doctor_id, Integer analizer_id) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.dni = dni;
		this.doctor_id = doctor_id;
		this.analizer_id = analizer_id;
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

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	

	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getAnalizer_id() {
		return analizer_id;
	}
	public void setAnalizer_id(Integer analizer_id) {
		this.analizer_id = analizer_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Patient other = (Patient) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", dni=" + dni + "]";
	}
	
}
