package hemogram.db.pojos;

import java.io.Serializable;

public class Features implements Serializable
{
	private static final long serialVersionUID = 3698476214030959794L;
	
	private Integer id;
	private String name;
	private float maximum;
	private float minimum;
	
	public Features(String name, float maximum, float minimum) 
	{
		super();
		this.name = name;
		this.maximum = maximum;
		this.minimum = minimum;
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

	public float getMaximum() {
		return maximum;
	}
	public void setMaximum(float maximum) {
		this.maximum = maximum;
	}

	public float getMinimum() {
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
		Features other = (Features) obj;
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
