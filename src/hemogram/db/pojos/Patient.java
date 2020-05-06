package hemogram.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import hemogram.db.xml.utils.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name  = "patient")
@XmlType(propOrder = {"name", "surname", "dob", "dni", "doctor"})
public class Patient implements Serializable
{
	// <element attribute="value">text or other elements</element>
	private static final long serialVersionUID = -3478632845146919461L;
	
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private String surname;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private String dni;
	// <patient>
	//   <doctors> <-- Wrapper
	//      <doctor></doctor>
	//      <doctor></doctor>
	//   </doctors>
	// </patient>
	@XmlElement(name = "doctor")
	@XmlElementWrapper(name = "doctors")
	private List<Doctor> doctor;
	
	public Patient() {
		super();
	}
	
	public Patient(Integer id, String name, String surname, Date dob, String dni) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.dni = dni;
	}
	
	public Patient(String name, String surname, Date dob, String dni) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.dni = dni;
	}
	
	

	public Patient(Integer id, String name, String surname, Date dob, String dni, List<Doctor> doctor) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.dni = dni;
		this.doctor = doctor;
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
	
	public List<Doctor> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
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
