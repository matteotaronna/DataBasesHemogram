package hemogram.db.pojos;

import java.io.Serializable;

public class FeatureValue implements Serializable
{
	private static final long serialVersionUID = 1407821407855057099L;
	
	private Integer id;
	private double value;
	private Feature feature;
	private Hemogram hemogram;
	
	/*private boolean isHealthy(double max, double min, double value) {
		boolean ishealth;
	}*/
	
	public FeatureValue() 
	{
		super();
	}
	public FeatureValue(Integer id, double value, Feature feature, Hemogram hemogram) //change integers for objects
	{
		super();
		this.id = id;
		this.value = value;
		this.feature = feature;
		this.hemogram = hemogram;
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
	public Feature getFeature_id() {
		return feature;
	}
	public void setFeature_id(Integer feature_id) {
		this.feature = feature;
	}
	public Hemogram getHemogram_id() {
		return hemogram;
	}
	public void setHemogram_id(Integer hemogram_id) {
		this.hemogram = hemogram;
	}
	
	

}
