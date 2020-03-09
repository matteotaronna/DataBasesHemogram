package hemogram.db.patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLCreatePatient {
	
	public static void main(String args[])
	{
		try
		{
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Create tables
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE patients "
			+ "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
			+ " name     TEXT     NOT NULL, "
			+ " surname  TEXT	 NOT NULL,"
			+ " dob      DATE     CURRENT DATE,"
			+ " dni      TEXT     NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
