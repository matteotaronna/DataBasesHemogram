package hemogram.db.interfaces;


import hemogram.db.pojos.*;

public interface FeatureValueManager 
{
	public void insertFeatureValue(double value, boolean healthy, int feature_id, int hemogram_id);
	public FeatureValue getFeatureValue (int featureValueId);
	public Feature getFeatureValueByName (String featureName);
}