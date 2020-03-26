package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hemogram.db.interfaces.AnalizerManager;
import hemogram.db.pojos.Analizer;

public class SQLAnalizerManager implements AnalizerManager 
{
	private Connection c;

	public SQLAnalizerManager(Connection c) 
	{
		this.c = c;
	}

	//INSERT
	@Override
	public void insertAnalizer (Analizer analizer) 
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

	//SELECT
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
	public Analizer logInAnalizer (String name, String work_user)
	{
		Analizer newAnalizer = null;
		try 
		{
			String sql = "SELECT * FROM analizers WHERE name = ? AND work_user = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, work_user);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String Aname = rs.getString("name");
				String surname = rs.getString("surname");
				String Awork_user = rs.getString("work_user");
				String hospital = rs.getString("hospital");
				newAnalizer = new Analizer(id, Aname, surname, Awork_user, hospital);
				System.out.println(newAnalizer);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newAnalizer;
	}
	
	@Override
	public int getAnalizerId (Analizer analizer)
	{
		int analizerId = 0;
		try 
		{
			String sql = "SELECT * FROM analizers WHERE name = ? AND surname = ? AND work_user = ? AND hospital = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, analizer.getName());
			s.setString(2, analizer.getSurname());
			s.setString(3, analizer.getWork_user());
			s.setString(4, analizer.getHospital());
			ResultSet rs = s.executeQuery();
			rs.next();
				 analizerId = rs.getInt("id");
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return analizerId;
	}
}
