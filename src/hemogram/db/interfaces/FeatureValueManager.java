package hemogram.db.interfaces;


import java.util.List;

import hemogram.db.pojos.*;

public interface FeatureValueManager 
{
	public void insertFeatureValue(FeatureValue featureValue) ;
	public List<FeatureValue> getFeatureValuesByHemogram (int hemogramId);
}