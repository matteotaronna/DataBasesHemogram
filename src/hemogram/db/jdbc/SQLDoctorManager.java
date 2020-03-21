package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hemogram.db.interfaces.DoctorManager;
import hemogram.db.pojos.Doctor;

public class SQLDoctorManager implements DoctorManager 
{
	private Connection c;

	public SQLDoctorManager(Connection c) 
	{
		this.c = c;
	}

	//INSERT
	@Override
	public void insertDoctor (Doctor doctor) 
	{
		try
		{
			String sql = "INSERT INTO doctors (name, surname, work_user, hospital, speciality) "
						+ "VALUES (?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, doctor.getName());
			prep.setString(2, doctor.getSurname());
			prep.setString(3, doctor.getWork_user());
			prep.setString(4, doctor.getHospital());
			prep.setString(5, doctor.getSpeciality());
			prep.executeUpdate();
			prep.close();
			System.out.println("Records inserted.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//SELECT
	@Override
	public Doctor getDoctor(int doctorId) 
	{
		Doctor newDoctor = null;
		try 
		{
			String sql = "SELECT * FROM doctors WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, doctorId);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String work_user = rs.getString("work_user");
				String hospital = rs.getString("hospital");
				String speciality = rs.getString("speciality");
				newDoctor = new Doctor(id, name, surname, work_user, hospital,speciality);
				System.out.println(newDoctor);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newDoctor;
	}

	public int getDoctorId (Doctor doctor)
	{
		
		int doctorID=0;
		try 
		{
			String sql = "SELECT * FROM doctors WHERE name = ? AND surname = ? AND work_user = ? AND hospital = ? AND speciality";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, doctor.getName());
			s.setString(2, doctor.getSurname());
			s.setString(3, doctor.getWork_user());
			s.setString(4, doctor.getHospital());
			ResultSet rs = s.executeQuery();
			rs.next();
			doctorID = rs.getInt("id");
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return doctorID;
	}

	//
	@Override
	public List<Doctor> searchDoctor(String name, String work_user) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
