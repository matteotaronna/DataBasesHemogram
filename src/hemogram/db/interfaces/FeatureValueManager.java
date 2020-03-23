package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.*;

public interface FeatureValueManager 
{
	public void insertFeatureValue(FeatureValue featureValue);
	public FeatureValue getFeatureValue (int featureValueId);
	public Feature getFeatureValueByName (String featureName);
	public List<Feature> listFeatures ();
}
