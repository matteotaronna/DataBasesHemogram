package hemogram.db.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SQLInsertBasic 
{
	
	private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY-MM-DD");
	
	public static void insertPatient()
	{
		try
		{
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
			System.out.print("Date of Birth (YYYY-MM-DD): ");
			String dob = reader.readLine();
			LocalDate dobDate = LocalDate.parse(dob, formatter);
			System.out.print("DNI: ");
			String dni = reader.readLine();

			// Insert new record
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO patients (name, surname, dni, dobDate) "
						+ "VALUES ('" + name + "', '" + surname	+ "', '" + dni + "', '" + dobDate + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Records inserted.");
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void insertDoctor()
	{
		try
		{
			//Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the employee info from the command prompt
			System.out.println("Please, input the doctor's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Surname: ");
			String surname = reader.readLine();
			System.out.print("Hospital: ");
			String hospital = reader.readLine();
			System.out.print("Speciality: ");
			String speciality = reader.readLine();
	

			// Insert new record
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO patients (name, surname, hospital, speciality) "
						+ "VALUES ('" + name + "', '" + surname	+ "', '" + hospital + "', '" + speciality + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Records inserted.");
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
