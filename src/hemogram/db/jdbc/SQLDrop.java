package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLDrop {

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Drop tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "DROP TABLE patients";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			Statement stmt2 = c.createStatement();
			String sql2 = "DROP TABLE doctors";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "DROP TABLE analizers";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "DROP TABLE hemograms";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			Statement stmt5 = c.createStatement();
			String sql5 = "DROP TABLE features";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			Statement stmt6 = c.createStatement();
			String sql6 = "DROP TABLE featureValues";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			Statement stmt7 = c.createStatement();
			String sql7 = "DROP TABLE relations";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			System.out.println("Tables removed.");
			// Drop tables: end
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
