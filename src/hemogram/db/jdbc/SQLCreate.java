package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLCreate {
	
	public static void main(String args[]) {
		try {
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
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE doctors "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT     NOT NULL, "
					   + " surname    TEXT     NOT NULL,"
					   + " work_user  DATE	   NOT NULL  UNIQUE, "
					   + " hospital   TEXT     NOT NULL, "
					   + " speciality TEXT     NOT NULL,"
					   + " patient_id	INTEGER REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE analizers "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT     NOT NULL, "
					   + " surname    TEXT     NOT NULL, "
					   + " work_user  TEXT     NOT NULL UNIQUE,"
					   + " hospital   TEXT     NOT NULL,"
					   + " patient_id INTEGER REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE hemograms "
					   + "(id             INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " date_hemogram  TEXT    NOT NULL, "
					   + " comments       TEXT     NOT NULL, "
					   + " doctor_id INTEGER REFERENCES doctors(id) ON UPDATE CASCADE ON DELETE SET NULL, "
					   + " patient_id INTEGER REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE features "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT    NOT NULL, "
					   + " minimum    REAL     NOT NULL, "
					   + " maximum    REAL     NOT NULL)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE featureValues "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " value      REAL    NOT NULL, "
					   + " feature_id INTEGER REFERENCES features(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					   + " hemogram_id INTEGER REFERENCES hemograms(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE relations "
					   + "(analizer_id     INTEGER  REFERENCES analizer(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					   + " hemogram_id   INTEGER  REFERENCES hemogram(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					   + " PRIMARY KEY (analizer_id,hemogram_id))";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			System.out.println("Tables created.");
			// Create table: end
			
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
