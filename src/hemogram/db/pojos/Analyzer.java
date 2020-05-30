package hemogram.db.pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "analyzer")
@XmlType(propOrder = { "name", "surname", "work_user", "hospital" })
public class Analyzer implements Serializable {

	private static final long serialVersionUID = 2533734397872268521L;

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

	public Analyzer() {
		super();
	}

	public Analyzer(Integer id, String name, String surname, String work_user, String hospital) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
	}

	public Analyzer(String name, String surname, String work_user, String hospital) {
		super();
		this.name = name;
		this.surname = surname;
		this.work_user = work_user;
		this.hospital = hospital;
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
		Analyzer other = (Analyzer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Analyzer [id=" + id + ", name=" + name + ", surname=" + surname + ", work_user=" + work_user
				+ ", hospital=" + hospital + "]";
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

}
