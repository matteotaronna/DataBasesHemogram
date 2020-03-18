package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Features;

public interface FeaturesManager 
{
	public void insertFeatures(Features features);
	public Features getFeature (int featureId);
	public Features getFeatureByName (String featureName);
	public List<Features> listFeatures ();
}
