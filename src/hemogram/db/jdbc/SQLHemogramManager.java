package hemogram.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hemogram.db.interfaces.AnalyzerManager;
import hemogram.db.interfaces.DoctorManager;
import hemogram.db.interfaces.HemogramManager;
import hemogram.db.interfaces.PatientManager;
import hemogram.db.pojos.Analyzer;
import hemogram.db.pojos.Doctor;
import hemogram.db.pojos.Hemogram;
import hemogram.db.pojos.Patient;

public class SQLHemogramManager implements HemogramManager {

	private Connection c;
	private DoctorManager doctorM;
	private PatientManager patientM;
	private AnalyzerManager analyzerM;

	public SQLHemogramManager(Connection c, DoctorManager doctor, PatientManager patient, AnalyzerManager analyzer) {
		this.c = c;
		this.doctorM = doctor;
		this.patientM = patient;
		this.analyzerM = analyzer;
	}

	// INSERT
	@Override
	public void insertHemogram(Hemogram hemogram) {
		try {
			// Insert new record
			String sql = "INSERT INTO hemograms (date_hemogram, doctor_id, patient_id, analyzer_id) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, hemogram.getDob());
			prep.setInt(2, hemogram.getDoctor().getId());
			prep.setInt(3, hemogram.getPatient().getId());
			prep.setInt(4, hemogram.getAnalyzer().getId());

			System.out.println("Records inserted.");
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// SELECT (SEARCH)
	@Override
	public Hemogram getHemogram(int hemogramId) {
		Hemogram newHemogram = null;
		try {
			String sql = "SELECT * FROM hemograms WHERE id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, hemogramId);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Date dob = rs.getDate("date_hemogram");
				String comments = rs.getString("comments");
				int doctor_id = rs.getInt("doctor_id");
				Doctor doctor = doctorM.getDoctor(doctor_id);
				int patient_id = rs.getInt("patient_id");
				Patient patient = patientM.getPatient(patient_id);
				int analyzer_id = rs.getInt("analyzer_id");
				Analyzer analyzer = analyzerM.getAnalyzer(analyzer_id);
				newHemogram = new Hemogram(id, dob, comments, patient, doctor, analyzer);
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newHemogram;
	}

	@Override
	public List<Hemogram> listHemogramPatient(int patientId) {

		List<Hemogram> hemogramList = new ArrayList<Hemogram>();
		try {
			String sql = "SELECT * FROM hemograms WHERE patient_id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, patientId);
			ResultSet rs = s.executeQuery();
			while (rs.next() == true) {
				int id = rs.getInt("id");
				Date dob = rs.getDate("date_hemogram");
				String comments = rs.getString("comments");
				int doctor_id = rs.getInt("doctor_id");
				Doctor doctor = doctorM.getDoctor(doctor_id);
				int patient_id = rs.getInt("patient_id");
				Patient patient = patientM.getPatient(patient_id);
				int analyzer_id = rs.getInt("analyzer_id");
				Analyzer analyzer = analyzerM.getAnalyzer(analyzer_id);
				Hemogram newHemogram = new Hemogram(id, dob, comments, patient, doctor, analyzer);
				hemogramList.add(newHemogram);
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hemogramList;
	}

	@Override
	public List<Hemogram> listHemogramDoctor(int patientId, int doctorId) {
		List<Hemogram> hemogramList = new ArrayList<Hemogram>();
		try {
			String sql = "SELECT * FROM hemograms WHERE patient_id = ? AND doctor_id = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, patientId);
			s.setInt(2, doctorId);
			ResultSet rs = s.executeQuery();
			while (rs.next() == true) {
				int id = rs.getInt("id");
				Date dob = rs.getDate("date_hemogram");
				String comments = rs.getString("comments");
				int doctor_id = rs.getInt("doctor_id");
				Doctor doctor = doctorM.getDoctor(doctor_id);
				int patient_id = rs.getInt("patient_id");
				Patient patient = patientM.getPatient(patient_id);
				int analyzer_id = rs.getInt("analyzer_id");
				Analyzer analyzer = analyzerM.getAnalyzer(analyzer_id);
				Hemogram newHemogram = new Hemogram(id, dob, comments, patient, doctor, analyzer);
				hemogramList.add(newHemogram);
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hemogramList;
	}

	@Override
	public void updatecomment(int hemogramid, String comments) {
		try {
			String sql = "UPDATE hemograms SET comments=? WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, comments);
			s.setInt(2, hemogramid);
			s.executeUpdate();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}