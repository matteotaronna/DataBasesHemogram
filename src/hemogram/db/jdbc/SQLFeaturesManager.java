package hemogram.db.jdbc;

import java.sql.Connection;
import java.util.List;

import hemogram.db.interfaces.FeaturesManager;
import hemogram.db.pojos.Features;

public class SQLFeaturesManager implements FeaturesManager 
{
	private Connection c;
	
	

	public SQLFeaturesManager(Connection c) {
		this.c = c;
	}

	@Override
	public void admit(Features features) {
		// TODO Auto-generated method stub

	}

	@Override
	public Features getFeature(int featureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Features> searchFeature(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
