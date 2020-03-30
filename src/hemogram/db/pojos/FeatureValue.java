package hemogram.db.pojos;

import java.io.Serializable;

public class FeatureValue implements Serializable
{
	private static final long serialVersionUID = 1407821407855057099L;
	
	private Integer id;
	private double value;
	private Feature feature;
	private Hemogram hemogram;
	private boolean healthy;
	
	
	
	
	public FeatureValue() 
	{
		super();
	}
	public FeatureValue(Integer id, double value, Feature feature, Hemogram hemogram)
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
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public Hemogram getHemogram() {
		return hemogram;
	}
	public void setHemogram(Hemogram hemogram) {
		this.hemogram = hemogram;
	}

	public boolean Healthy() {
		return healthy;
	}
	public void setHealthy(boolean healthy)
	{ 
		this.healthy=healthy;
	}
	
	

}
