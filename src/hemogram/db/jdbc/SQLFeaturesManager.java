package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hemogram.db.interfaces.FeaturesManager;
import hemogram.db.pojos.*;

public class SQLFeaturesManager implements FeaturesManager 
{
	private Connection c;

	public SQLFeaturesManager(Connection c) {
		this.c = c;
	}

	@Override
	public void insertFeatures(Feature features) 
	{
		try
		{
			// Insert new record
			String sql = "INSERT INTO features (name, minimum, maximum) "
						+ "VALUES (?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, features.getName());
			prep.setDouble(2, features.getMinimum());
			prep.setDouble(3, features.getMaximum());
			prep.executeUpdate();
			prep.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public Feature getFeature(int featureId) 
	{
		Feature newFeature = null;
		try 
		{
			String sql = "SELECT * FROM features WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, featureId);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float minimum = rs.getFloat("minimum");
				float maximum = rs.getFloat("maximum");
				newFeature = new Feature(id, name, minimum, maximum);
			rs.close();
			s.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newFeature;
	}
	
	@Override
	public Feature getFeatureByName(String featureName) 
	{
		Feature newFeature = null;
		try 
		{
			String sql = "SELECT * FROM features WHERE name LIKE ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, featureName);
			ResultSet rs = s.executeQuery();
			rs.next(); 
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float minimum = rs.getFloat("minimum");
				float maximum = rs.getFloat("maximum");
				newFeature = new Feature(id, name, minimum, maximum);
			rs.close();
			s.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newFeature;
	}

	@Override
	public List<Feature> listFeatures() 
	{
		//create an empty list of dogs
				List<Feature> featuresList = new ArrayList<Feature>();
				//search for all the dogs that fit the name
				try 
				{
					Statement stmt = c.createStatement();
					String sql = "SELECT * FROM features";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) 
					{
						int id = rs.getInt("id");
						String name = rs.getString("name");
						double minimum = rs.getDouble("minimum");
						double maximum = rs.getDouble("maximum");
						Feature newFeature = new Feature(id,name,minimum,maximum);
						//add dog to the list
						featuresList.add(newFeature);
					}
					rs.close();
					stmt.close();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				//return the dogs list
				return featuresList;
	}

}
