package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Features;

public interface FeaturesManager 
{
	public void insertFeatures(Features features);
	public Features getFeature (int featureId);
	//public int getFeatureId (Features features);
	public List<Features> searchFeature (String name);
}
