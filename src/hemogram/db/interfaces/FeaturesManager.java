package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Feature;

public interface FeaturesManager 
{
	public void insertFeatures(Feature features);
	public Feature getFeature (int featureId);
	public Feature getFeatureByName (String featureName);
	public List<Feature> listFeatures ();
}
