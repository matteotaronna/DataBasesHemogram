package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import hemogram.db.interfaces.*;
import hemogram.db.pojos.*;


public class SQLManager implements DBManager 
{
	private Connection c;
	private AnalizerManager analizer;
	private PatientManager patient;
	private DoctorManager doctor;
	private FeaturesManager features;
	private FeatureValueManager featureValue;
	private HemogramManager hemogram;
	//hemogram ...
	
	public SQLManager()
	{
		super();
	}

	@Override
	public void connect() 
	{
		try 
		{
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/HemogramDB.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			
			//Create the atributes managers.
			analizer = new SQLAnalizerManager(c);
			patient = new SQLPatientManager(c);
			features = new SQLFeaturesManager(c);
			doctor = new SQLDoctorManager(c);
			featureValue = new SQLFeatureValueManager(c);
			hemogram = new SQLHemogramManager(c);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() 
	{
		try 
		{
			c.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createTables() 
	{
		try {
			// Create tables
			
			//DOCTORS
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE doctors "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT     NOT NULL, "
					   + " surname    TEXT     NOT NULL,"
					   + " work_user  TEXT	   NOT NULL  UNIQUE, "
					   + " hospital   TEXT     NOT NULL, "
					   + " speciality TEXT     NOT NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			
			//ANALIZERS
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE analizers "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT     NOT NULL, "
					   + " surname    TEXT     NOT NULL, "
					   + " work_user  TEXT     NOT NULL UNIQUE,"
					   + " hospital   TEXT     NOT NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			
			//PATIENTS
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE patients "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " surname  TEXT	 NOT NULL,"
					   + " dob      DATE     CURRENT DATE,"
					   + " dni      TEXT     NOT NULL"
					   + " doctor_id INTEGER REFERENCES doctors(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			
			//HEMOGRAMS
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE hemograms "
					   + "(id             INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " date_hemogram  TEXT    NOT NULL, "
					   + " comments       TEXT     NOT NULL, "
					   + " doctor_id INTEGER REFERENCES doctors(id) ON UPDATE CASCADE ON DELETE SET NULL, "
					   + " patient_id INTEGER REFERENCES patients(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					   + " analizer_id INTEGER REFERENCES analizers(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			
			//FEATURES
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE features "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name       TEXT    NOT NULL, "
					   + " minimum    REAL     NOT NULL, "
					   + " maximum    REAL     NOT NULL)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			
			//FEATUREVALUES
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE featureValues "
					   + "(id         INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " value      REAL    NOT NULL, "
					   + " feature_id INTEGER REFERENCES features(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					   + " hemogram_id INTEGER REFERENCES hemograms(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			
			//SET FEATURES
			Feature leukocytes = new Feature ("leukocytes",4.8,10.8);
			features.insertFeatures(leukocytes);
			Feature erythrocytes = new Feature ("erythrocytes",4.20,5.40);
			features.insertFeatures(erythrocytes);
			Feature hemoglobin = new Feature ("hemoglobin",12,16);
			features.insertFeatures(hemoglobin);
			Feature hematocrit = new Feature ("hematocrit",37,47);
			features.insertFeatures(hematocrit);
			Feature platelets = new Feature ("platelets",130,400);
			features.insertFeatures(platelets);
			Feature cholesterol = new Feature ("cholesterol",0,200);
			features.insertFeatures(cholesterol);
			Feature cholesterolHDL = new Feature ("cholesterolHDL",40,60);
			features.insertFeatures(cholesterolHDL);
			Feature triglycerides = new Feature ("triglycerides",10,200);
			features.insertFeatures(triglycerides);
			Feature cholesterolLDL = new Feature ("cholesterolLDL",0,100);
			features.insertFeatures(cholesterolLDL);
			Feature glycemia = new Feature ("glycemia",70,110);
			features.insertFeatures(glycemia);
			
			System.out.println("Tables created.");
			// Create table: end

		} catch (SQLException e) 
		{
			if(e.getMessage().contains("already exists"));
			else
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public AnalizerManager getAnalizerManager()
	{
		return analizer;
	}
	@Override
	public PatientManager getPatientManager()
	{
		return patient;
	}
	@Override
	public DoctorManager getDoctorManager()
	{
		return doctor;
	}
	@Override
	public FeaturesManager getFeaturesManager()
	{
		return features;
	}
	@Override
	public FeatureValueManager getFeatureValueManager()
	{
		return featureValue;
	}
	@Override
	public HemogramManager getHemogramManager()
	{
		return hemogram;
	}
}
