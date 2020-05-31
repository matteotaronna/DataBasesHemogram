package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hemogram.db.interfaces.DoctorManager;
import hemogram.db.pojos.Doctor;

public class SQLDoctorManager implements DoctorManager {
	private Connection c;

	public SQLDoctorManager(Connection c) {
		this.c = c;
	}

	// INSERT
	@Override
	public void insertDoctor(Doctor doctor) {
		try {
			String sql = "INSERT INTO doctors (name, surname, work_user, hospital, specialty) " + "VALUES (?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, doctor.getName());
			prep.setString(2, doctor.getSurname());
			prep.setString(3, doctor.getWork_user());
			prep.setString(4, doctor.getHospital());
			prep.setString(5, doctor.getSpecialty());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SELECT
	@Override
	public Doctor getDoctor(int doctorId) {
		Doctor newDoctor = null;
		try {
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
			String specialty = rs.getString("specialty");
			newDoctor = new Doctor(id, name, surname, work_user, hospital, specialty);
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDoctor;
	}

	@Override
	public List<Doctor> listDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String work_user = rs.getString("work_user");
				String hospital = rs.getString("hospital");
				String specialty = rs.getString("specialty");
				Doctor doctor = new Doctor(id, name, surname, work_user, hospital, specialty);
				doctors.add(doctor);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public Doctor logInDoctor(String name, String work_user) {
		Doctor newDoctor = null;
		try {
			String sql = "SELECT * FROM doctors WHERE name = ? AND work_user = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, work_user);
			ResultSet rs = s.executeQuery();
			while (rs.next() == true) {
				int id = rs.getInt("id");
				String Dname = rs.getString("name");
				String surname = rs.getString("surname");
				String Dwork_user = rs.getString("work_user");
				String hospital = rs.getString("hospital");
				String specialty = rs.getString("specialty");
				newDoctor = new Doctor(id, Dname, surname, Dwork_user, hospital, specialty);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDoctor;
	}
}
