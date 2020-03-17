package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import hemogram.db.interfaces.AnalizerManager;
import hemogram.db.pojos.Analizer;

public class SQLAnalizerManager implements AnalizerManager 
{
	private Connection c;

	public SQLAnalizerManager(Connection c) 
	{
		this.c = c;
	}

	@Override
	public void admit(Analizer analizer) 
	{
		try 
		{	
			String sql = "INSERT INTO analizers (name, surname, work_user, hospital) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, analizer.getName());
			prep.setString(2, analizer.getSurname());
			prep.setString(3, analizer.getWork_user());
			prep.setString(4, analizer.getHospital());
			prep.executeUpdate();
			prep.close();
			
			System.out.println("Records inserted.");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Analizer getAnalizer(int analizerId) 
	{
		Analizer newAnalizer = null;
		try 
		{
			String sql = "SELECT * FROM analizers WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, analizerId);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String work_user = rs.getString("work_user");
				String hospital = rs.getString("hospital");
				newAnalizer = new Analizer(id, name, surname, work_user, hospital);
				System.out.println(newAnalizer);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newAnalizer;
	}

	@Override
	public List<Analizer> searchAnalizer(String name, String work_user) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
