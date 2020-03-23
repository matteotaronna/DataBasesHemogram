package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Patient;

public interface PatientManager 
{
	public void insertPatient (Patient patient);
	public Patient getPatient (int patientId);
	public Patient searchPatient (String dni);
	public List<Patient> listPatients (int doctorId);
}
