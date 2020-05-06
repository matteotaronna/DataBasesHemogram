package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import hemogram.db.interfaces.PatientManager;
import hemogram.db.pojos.Patient;

public class SQLPatientManager implements PatientManager 
{
	private Connection c;
	
	

	public SQLPatientManager(Connection c) 
	{
		this.c = c;
	}
	
	//INSERT
	@Override
	public void insertPatient (Patient patient) 
	{
		try
		{
			// Insert new record
			String sql = "INSERT INTO patients (name, surname, dob, dni) "
						+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, patient.getName());
			prep.setString(2, patient.getSurname());
			prep.setDate(3, patient.getDob());
			prep.setString(4, patient.getDni());
			System.out.println("Records inserted.");
			prep.executeUpdate();
			prep.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//method to sign up patient without inserting doctor id
	@Override
	public void signUpPatient (Patient patient) 
	{
		try
		{
			// Insert new record
			String sql = "INSERT INTO patients (name, surname, dob, dni) "
						+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, patient.getName());
			prep.setString(2, patient.getSurname());
			prep.setDate(3, patient.getDob());
			prep.setString(4, patient.getDni());
			System.out.println("Records inserted.");
			prep.executeUpdate();
			prep.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Patient logInPatient(String name, String dni) 
	{
		Patient newPatient = null;
		try 
		{
			String sql = "SELECT * FROM patients WHERE name = ? AND dni = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, dni);
			ResultSet rs = s.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String Aname = rs.getString("name");
			String surname = rs.getString("surname");
			Date Adob = rs.getDate("dob");
			String Adni = rs.getString("dni");
			newPatient = new Patient(id, Aname, surname, Adob, Adni);

		} catch (Exception e) {
			if (e.getMessage().contains("ResultSet closed")) {
				newPatient = null;
			} else {
				e.printStackTrace();
			}
		}
		return newPatient;
	}

	//SELECT
	@Override
	public Patient getPatient(int patientId) 
	{
		Patient newPatient = null;
		try 
		{
			String sql = "SELECT * FROM patients WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, patientId);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String dni = rs.getString("dni");
				newPatient = new Patient(id, name, surname, dob, dni);
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newPatient;
	}
	
	@Override
	public Patient searchPatient(String dni) 
	{
		Patient newPatient = null;
		try 
		{
			String sql = "SELECT * FROM patients WHERE dni = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, dni);
			ResultSet rs = s.executeQuery();
			rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date dob = rs.getDate("dob");
				String newDni = rs.getString("dni");
				newPatient = new Patient(id, name, surname, dob, newDni);
			
		} catch (Exception e) 
		{
			if(e.getMessage().contains("ResultSet closed"))
			{
				newPatient = null;
			}
			else
			{
				e.printStackTrace();
			}
		}
		return newPatient;
	}
	
	@Override
	public int getPatientId (Patient patient)
	{
		int patientId = 0;
		try 
		{
			String sql = "SELECT * FROM patients WHERE name = ? AND surname = ? AND dob = ? AND dni = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, patient.getName());
			s.setString(2, patient.getSurname());
			s.setDate(3, patient.getDob());
			s.setString(4, patient.getDni());
			ResultSet rs = s.executeQuery();
			rs.next();
				 patientId = rs.getInt("id");
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return patientId;
	}

	@Override
	public List<Patient> listPatients(int doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
