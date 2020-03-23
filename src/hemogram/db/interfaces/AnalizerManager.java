package hemogram.db.interfaces;

import hemogram.db.pojos.*;

public interface AnalizerManager 
{
	public void insertAnalizer(Analizer analizer);
	public Analizer getAnalizer (int analizerId);
	public int getAnalizerId (Analizer analizer);
	public Analizer signUpAnalizer (String name, String work_user);
}
