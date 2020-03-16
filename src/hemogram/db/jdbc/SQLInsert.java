package hemogram.db.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SQLInsert 
{
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
	
	public static void insertDoctor()
	{
		try
		{
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the doctor info from the command prompt
			System.out.println("Please, input the doctor's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Surname: ");
			String surname = reader.readLine();
			System.out.print("UserID: ");
			String userID = reader.readLine();
			System.out.print("Hospital: ");
			String hospital = reader.readLine();
			System.out.print("Speciality: ");
			String speciality = reader.readLine();

			// Insert new record
			String sql = "INSERT INTO doctors (name, surname, work_user, hospital, speciality) "
						+ "VALUES (?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			prep.setString(2, surname);
			prep.setString(3, userID);
			prep.setString(4, hospital);
			prep.setString(5, speciality);
			System.out.println("Records inserted.");
			// Insert ended
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void insertAnalizer()
	{
		try
		{
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the analizer info from the command prompt
			System.out.println("Please, input the analizer's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Surname: ");
			String surname = reader.readLine();
			System.out.print("UserID: ");
			String userID = reader.readLine();
			System.out.print("Hospital: ");
			String hospital = reader.readLine();

			// Insert new record
			String sql = "INSERT INTO analizers (name, surname, work_user, hospital) "
						+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			prep.setString(2, surname);
			prep.setString(3, userID);
			prep.setString(4, hospital);
			System.out.println("Records inserted.");
			// Insert ended
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void insertPatient()
	{
		try
		{
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the doctor info from the command prompt
			System.out.println("Please, input the doctor's info:");
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
			System.out.print("Choose the patient's doctor, type its ID: ");
			//printDoctors();
			int doctor_id = Integer.parseInt(reader.readLine());
			System.out.print("Choose the patient's analizer, type its ID: ");
			//printAnalizers();
			int analizer_id = Integer.parseInt(reader.readLine());

			// Insert new record
			String sql = "INSERT INTO patients (name, surname, work_user, hospital, speciality) "
						+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			prep.setString(2, surname);
			prep.setDate(3, Date.valueOf(dobDate));
			prep.setString(4, dni);
			prep.setInt(5, doctor_id);
			prep.setInt(6, analizer_id);
			System.out.println("Records inserted.");
			// Insert ended
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
