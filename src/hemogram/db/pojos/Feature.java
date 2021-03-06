package hemogram.db.pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "feature")
@XmlType(propOrder = { "name" })
public class Feature implements Serializable {
	private static final long serialVersionUID = 3698476214030959794L;

	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlTransient
	private double minimum;
	@XmlTransient
	private double maximum;

	public Feature() {
		super();
	}

	public Feature(String name, double minimum, double maximum) {
		super();
		this.name = name;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Feature(Integer id, String name, double minimum, double maximum) {
		super();
		this.id = id;
		this.name = name;
		this.minimum = minimum;
		this.maximum = maximum;
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

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(float maximum) {
		this.maximum = maximum;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(float minimum) {
		this.minimum = minimum;
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
		Feature other = (Feature) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Features [id=" + id + ", name=" + name + ", maximum=" + maximum + ", minimum=" + minimum + "]";
	}

}
