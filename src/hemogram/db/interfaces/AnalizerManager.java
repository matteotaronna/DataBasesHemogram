package hemogram.db.interfaces;

import hemogram.db.pojos.*;

public interface AnalizerManager 
{
	public void insertAnalizer(Analizer analizer);
	public Analizer getAnalizer (int analizerId);
	public Analizer signUpAnalizer (String name, String work_user);
}
