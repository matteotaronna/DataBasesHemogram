package hemogram.db.interfaces;

import java.sql.Date;
import java.util.List;

import hemogram.db.pojos.Hemogram;

public interface HemogramManager {
	public void insertHemogram (Date hemogramDate, int patientId, int analyzerId, int doctorId) ;
	public Hemogram getHemogram(int hemogramId);
	public List<Hemogram> listHemogramDoctor(int patient_id, int doctor_id);
	public List<Hemogram> listHemogramPatient (int patientId);
}
