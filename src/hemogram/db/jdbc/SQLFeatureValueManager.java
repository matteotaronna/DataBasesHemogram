package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import hemogram.db.interfaces.FeatureValueManager;
import hemogram.db.pojos.*;

public class SQLFeatureValueManager implements FeatureValueManager 
{
	private Connection c;

	public SQLFeatureValueManager(Connection c) 
	{
		this.c = c;
	}

	@Override
	public void insertFeatureValue(FeatureValue featureValue) 
	{
		try
		{
			// Insert new record
			String sql = "INSERT INTO featureValues (value, healthy, feature_id, hemogram_id) "
						+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDouble(1, featureValue.getValue());
			prep.setBoolean(2, featureValue.getHealthy());
			prep.setInt(3, featureValue.getFeature().getId());
			prep.setInt(4, featureValue.getHemogram().getId());
			prep.executeUpdate();
			prep.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public FeatureValue getFeatureValue(int featureValueId) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feature getFeatureValueByName(String featureName) {
		// TODO Auto-generated method stub
		return null;
	}
}
