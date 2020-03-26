package hemogram.db.interfaces;

import hemogram.db.pojos.*;

public interface AnalizerManager 
{
	public void insertAnalizer(Analizer analizer);
	public Analizer getAnalizer (int analizerId);
	public Analizer logInAnalizer (String name, String work_user);
	public int getAnalizerId (Analizer analizer);
}
