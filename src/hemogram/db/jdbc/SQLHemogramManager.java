package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hemogram.db.interfaces.HemogramManager;
import hemogram.db.pojos.Hemogram;

public class SQLHemogramManager implements HemogramManager{

	private Connection c;
	private List<Hemogram> hemogramList;

	public SQLHemogramManager(Connection c) 
	{
		this.c = c;
	}

	//INSERT
	@Override
	public void insertHemogram (Hemogram hemogram) 
	{
		try 
		{	
			String sql = "INSERT INTO hemogram (date_hemogram, comments, doctor_id, patient_id, analyzer_id) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, hemogram.getDob());
			prep.setString(2, hemogram.getComments());
			prep.setInt(3, hemogram.getDoctor_id());
			prep.setInt(4, hemogram.getPatient_id());
			prep.setInt(5, hemogram.getAnalyzer_id());
			prep.executeUpdate();
			prep.close();
			
			System.out.println("Records inserted.");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	    //SELECT (SEARCH)
		@Override
		public Hemogram getHemogram(int hemogramId) 
		{
			Hemogram newHemogram = null;
			try 
			{
				String sql = "SELECT * FROM hemograms WHERE id = ?";
				PreparedStatement s = c.prepareStatement(sql);
				s.setInt(1, hemogramId);
				ResultSet rs = s.executeQuery();
				rs.next();
					int id = rs.getInt("id");
					Date dob = rs.getDate("date_hemogram");
					String comments = rs.getString("comments");
					int doctor_id = rs.getInt("doctor_id");
					int patient_id = rs.getInt("patient_id");
					int analyzer_id = rs.getInt("analyzer_id");
					newHemogram = new Hemogram(id, dob, comments, doctor_id, patient_id, analyzer_id);
					System.out.println(newHemogram);
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			return newHemogram;
		}
		
		public List<Hemogram> listHemogram(int patientId, int doctorId){
			
			hemogramList = null;
			try 
			{
				String sql = "SELECT * FROM hemograms WHERE patient_id = ? AND doctor_id = ?";
				PreparedStatement s = c.prepareStatement(sql);
				s.setInt(1, patientId);
				s.setInt(2, doctorId);
				ResultSet rs = s.executeQuery();
				while(rs.next()==true)
				{
					int id = rs.getInt("id");
					Date dob = rs.getDate("date_hemogram");
					String comments = rs.getString("comments");
					int doctor_id = rs.getInt("doctor_id");
					int patient_id = rs.getInt("patient_id");
					int analyzer_id = rs.getInt("analyzer_id");
					Hemogram newHemogram = new Hemogram(id, dob, comments, doctor_id, patient_id, analyzer_id);
					hemogramList.add(newHemogram);
				}
				
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			return hemogramList;
		}
		}

