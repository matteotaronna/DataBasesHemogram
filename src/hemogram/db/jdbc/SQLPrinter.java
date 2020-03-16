package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hemogram.db.pojos.*;


public class SQLPrinter {
	
	public static Connection c;

	public static void printDoctors() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM doctors";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String work_user = rs.getString("work_user");
			String hospital = rs.getString("hospital");
			String speciality = rs.getString("speciality");
			Doctor doctor = new Doctor(id, name, surname, work_user, hospital, speciality);
			System.out.println(doctor);
		}
		rs.close();
		stmt.close();
	}
	
	public static void printAnalizers() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM analizers";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String work_user = rs.getString("work_user");
			String hospital = rs.getString("hospital");
			Analizer doctor = new Analizer(id, name, surname, work_user, hospital);
			System.out.println(doctor);
		}
		rs.close();
		stmt.close();
	}
}
