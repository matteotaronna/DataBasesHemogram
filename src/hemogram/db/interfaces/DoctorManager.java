package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Doctor;

public interface DoctorManager 
{
	public void admit (Doctor doctor);
	public Doctor getDoctor (int doctorId);
	public int getDoctorId (Doctor doctor);
	public List<Doctor> searchDoctor (String name, String work_user);
}
