package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hemogram.db.interfaces.AnalyzerManager;
import hemogram.db.pojos.Analyzer;

public class SQLAnalyzerManager implements AnalyzerManager {
	private Connection c;

	public SQLAnalyzerManager(Connection c) {
		this.c = c;
	}

	// INSERT
	@Override
	public void insertAnalyzer(Analyzer analyzer) {
		try {
			String sql = "INSERT INTO analyzers (name, surname, work_user, hospital) " + "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, analyzer.getName());
			prep.setString(2, analyzer.getSurname());
			prep.setString(3, analyzer.getWork_user());
			prep.setString(4, analyzer.getHospital());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SELECT
	@Override
	public Analyzer getAnalyzer(int analyzerId) {
		Analyzer newAnalyzer = null;
		try {
			String sql = "SELECT * FROM analyzers WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, analyzerId);
			ResultSet rs = s.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String work_user = rs.getString("work_user");
			String hospital = rs.getString("hospital");
			newAnalyzer = new Analyzer(id, name, surname, work_user, hospital);
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newAnalyzer;
	}

	@Override
	public Analyzer logInAnalyzer(String name, String work_user) {
		Analyzer newAnalyzer = null;
		try {
			String sql = "SELECT * FROM analyzers WHERE name = ? AND work_user = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, work_user);
			ResultSet rs = s.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			String Aname = rs.getString("name");
			String surname = rs.getString("surname");
			String Awork_user = rs.getString("work_user");
			String hospital = rs.getString("hospital");
			newAnalyzer = new Analyzer(id, Aname, surname, Awork_user, hospital);
			rs.close();
			s.close();

		} catch (Exception e) {
			if (e.getMessage().contains("ResultSet closed")) {
				newAnalyzer = null;
			} else {
				e.printStackTrace();
			}
		}
		return newAnalyzer;
	}

	@Override
	public int getAnalyzerId(Analyzer analyzer) {
		int analyzerId = 0;
		try {
			String sql = "SELECT * FROM analyzers WHERE name = ? AND surname = ? AND work_user = ? AND hospital = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, analyzer.getName());
			s.setString(2, analyzer.getSurname());
			s.setString(3, analyzer.getWork_user());
			s.setString(4, analyzer.getHospital());
			ResultSet rs = s.executeQuery();
			rs.next();
			analyzerId = rs.getInt("id");
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return analyzerId;
	}

	@Override
	public void linkPatientDoctor(int patientId, int doctorId) {
		try {

			String sql1 = "SELECT * FROM patientsDoctors WHERE patientId = ?";
			PreparedStatement prep1 = c.prepareStatement(sql1);
			prep1.setInt(1, patientId);
			ResultSet rs = prep1.executeQuery();
			int doctorIdSearched;
			boolean allreadyLinked = false;
			while (rs.next()) {
				doctorIdSearched = rs.getInt("doctorId");
				if (doctorId == doctorIdSearched) {
					allreadyLinked = true;
					break;
				}
			}

			if (allreadyLinked == false) {
				String sql = "INSERT INTO patientsDoctors (patientId, doctorId) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, patientId);
				prep.setInt(2, doctorId);
				prep.executeUpdate();
				prep.close();
			}
			rs.close();
			prep1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}