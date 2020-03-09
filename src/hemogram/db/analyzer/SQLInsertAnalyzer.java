package hemogram.db.analyzer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertAnalyzer {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the employee info from the command prompt
			System.out.println("Please, input the patient's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Surname: ");
			String surname = reader.readLine();
			//System.out.print("Date of Birth: ");
			//Date dob = reader.readLine();
			System.out.print("DNI: ");
			String dni = reader.readLine();
			

			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO patients (name, surname, dni) "
					+ "VALUES ('" + name + "', '" + surname	+ "', '" + dni + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Department info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
