package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Hemogram;

public interface HemogramManager {
	public void insertHemogram(Hemogram hemogram);

	public Hemogram getHemogram(int hemogramId);

	public List<Hemogram> listHemogramDoctor(int patient_id, int doctor_id);

	public List<Hemogram> listHemogramPatient(int patientId);

	public void updatecomment(int hemogramid, String comments);
}
