package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.Doctor;

public interface DoctorManager 
{
	public void insertDoctor (Doctor doctor);
	public Doctor getDoctor (int doctorId);
	public Doctor logInDoctor(String name, String work_user);
	public List<Doctor> listDoctors ();
}
