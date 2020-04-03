package hemogram.db.interfaces;


import hemogram.db.pojos.*;

public interface FeatureValueManager 
{
	public void insertFeatureValue(FeatureValue featureValue) ;
	public FeatureValue getFeatureValue (int featureValueId);
	public Feature getFeatureValueByName (String featureName);
}