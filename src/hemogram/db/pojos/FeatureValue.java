package hemogram.db.pojos;

import java.io.Serializable;

public class FeatureValue implements Serializable
{
	private static final long serialVersionUID = 1407821407855057099L;
	
	private Integer id;
	private double value;
	private Integer feature_id;
	private Integer hemogram_id;
	
	
	
	public FeatureValue() 
	{
		super();
	}
	public FeatureValue(Integer id, double value, Integer feature_id, Integer hemogram_id) 
	{
		super();
		this.id = id;
		this.value = value;
		this.feature_id = feature_id;
		this.hemogram_id = hemogram_id;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Integer getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(Integer feature_id) {
		this.feature_id = feature_id;
	}
	public Integer getHemogram_id() {
		return hemogram_id;
	}
	public void setHemogram_id(Integer hemogram_id) {
		this.hemogram_id = hemogram_id;
	}
	
	

}
