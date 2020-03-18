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
	public void admit(Patient patient) 
	{
		try
		{
			// Insert new record
			String sql = "INSERT INTO patients (name, surname, dob, dni, doctor_id, analizer_id) "
						+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, patient.getName());
			prep.setString(2, patient.getSurname());
			prep.setDate(3, patient.getDob());
			prep.setString(4, patient.getDni());
			prep.setInt(5, patient.getDoctor_id());
			prep.setInt(6, patient.getAnalizer_id());
			System.out.println("Records inserted.");
			prep.executeUpdate();
			prep.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}

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
				int doctor_id = rs.getInt("doctor_id");
				int analizer_id = rs.getInt("analizer_id");
				newPatient = new Patient(id, name, surname, dob, dni,doctor_id,analizer_id);
				System.out.println(newPatient);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newPatient;
	}

	@Override
	public List<Patient> searchPatient(String name, String dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
