package hemogram.db.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hemogram.db.interfaces.DoctorManager;
import hemogram.db.pojos.Analizer;
import hemogram.db.pojos.Doctor;

public class SQLDoctorManager implements DoctorManager 
{
	private Connection c;

	public SQLDoctorManager(Connection c) 
	{
		this.c = c;
	}

	@Override
	public void admit(Doctor doctor) 
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

	@Override
	public List<Doctor> searchDoctor(String name, String work_user) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
