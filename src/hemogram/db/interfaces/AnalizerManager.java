package hemogram.db.interfaces;

import java.util.List;

import hemogram.db.pojos.*;

public interface AnalizerManager 
{
	public void insertAnalizer(Analizer analizer);
	public Analizer getAnalizer (int analizerId);
	public int getAnalizerId (Analizer analizer);
	public Analizer signUpAnalizer (String name, String work_user);
	public List<Analizer> searchAnalizer (String name, String work_user);
}