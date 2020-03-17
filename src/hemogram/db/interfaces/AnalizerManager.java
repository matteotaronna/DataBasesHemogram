package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.*;

public interface AnalizerManager 
{
	public void admit(Analizer analizer);
	public Analizer getAnalizer (int analizerId);
	public int getAnalizerId (Analizer analizer);
	public List<Analizer> searchAnalizer (String name, String work_user);
}
