package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import hemogram.db.interfaces.FeatureValueManager;
import hemogram.db.interfaces.FeaturesManager;
import hemogram.db.interfaces.HemogramManager;
import hemogram.db.pojos.*;

public class SQLFeatureValueManager implements FeatureValueManager {
	private Connection c;
	private FeaturesManager featuresManager;
	private HemogramManager hemogramManager;

	public SQLFeatureValueManager(Connection c, FeaturesManager feature, HemogramManager hemogram) {
		this.c = c;
		this.featuresManager = feature;
		this.hemogramManager = hemogram;
	}

	@Override
	public void insertFeatureValue(FeatureValue featureValue) {
		try {
			// Insert new record
			String sql = "INSERT INTO featureValues (value, healthy, feature_id, hemogram_id) " + "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDouble(1, featureValue.getValue());
			prep.setBoolean(2, featureValue.getHealthy());
			prep.setInt(3, featureValue.getFeature().getId());
			prep.setInt(4, featureValue.getHemogram().getId());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FeatureValue> getFeatureValuesByHemogram(int hemogramId) {
		List<FeatureValue> featuresValues = new ArrayList<FeatureValue>();
		try {
			// Insert new record
			String sql = "SELECT * FROM featureValues WHERE hemogram_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, hemogramId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				double value = rs.getDouble("value");
				boolean healthy = rs.getBoolean("healthy");
				int featureId = rs.getInt("feature_id");
				Feature feature = featuresManager.getFeature(featureId);
				int hemogramID = rs.getInt("hemogram_id");
				Hemogram hemogram = hemogramManager.getHemogram(hemogramID);
				FeatureValue newFeatureValue = new FeatureValue(id, value, feature, hemogram, healthy);
				featuresValues.add(newFeatureValue);
			}
			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return featuresValues;

	}
}