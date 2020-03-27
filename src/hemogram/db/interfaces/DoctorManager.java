package hemogram.db.interfaces;

import java.util.List;
import hemogram.db.pojos.*;

import hemogram.db.pojos.Analizer;
import hemogram.db.pojos.Doctor;

public interface DoctorManager 
{
	public void insertDoctor (Doctor doctor);
	public Doctor getDoctor (int doctorId);
	public int getDoctorId (Doctor doctor);
	public Doctor signUpDoctor (String name, String work_user);
	public List<Doctor> searchDoctor (String name, String work_user);
}
