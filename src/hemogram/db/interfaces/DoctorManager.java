package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Doctor;
import hemogram.db.pojos.Patient;

public interface DoctorManager 
{
	public void admit (Doctor doctor);
	public Doctor getDoctor (int doctorId);
	public List<Doctor> searchDoctor (String name, String work_user);
}
