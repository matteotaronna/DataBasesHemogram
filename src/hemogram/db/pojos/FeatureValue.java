package hemogram.db.pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "featureValue")
@XmlType(propOrder = { "value", "feature", "healthy" })
public class FeatureValue implements Serializable {
	private static final long serialVersionUID = 1407821407855057099L;

	@XmlTransient
	private Integer id;
	@XmlElement
	private double value;
	@XmlElement
	private Feature feature;
	@XmlTransient
	private Hemogram hemogram;
	@XmlElement
	private boolean healthy;

	public FeatureValue() {
		super();
	}

	public FeatureValue(Integer id, double value, Feature feature, Hemogram hemogram) {
		super();
		this.id = id;
		this.value = value;
		this.feature = feature;
		this.hemogram = hemogram;
	}

	public FeatureValue(Integer id, double value, Feature feature) {
		super();
		this.id = id;
		this.value = value;
		this.feature = feature;
	}

	public FeatureValue(double value, Feature feature, Hemogram hemogram, boolean healthy) {
		super();
		this.value = value;
		this.feature = feature;
		this.hemogram = hemogram;
		this.healthy = healthy;
	}

	public FeatureValue(Integer id, double value, Feature feature, Hemogram hemogram, boolean healthy) {
		super();
		this.id = id;
		this.value = value;
		this.feature = feature;
		this.hemogram = hemogram;
		this.healthy = healthy;
	}

	@Override
	public String toString() {
		return "FeatureValue [value=" + value + ", feature=" + feature + ", hemogram=" + hemogram + ", healthy="
				+ healthy + "]";
	}

	public FeatureValue(double value, boolean healthy) {
		super();
		this.value = value;
		this.healthy = healthy;
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

	public boolean getHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
}
