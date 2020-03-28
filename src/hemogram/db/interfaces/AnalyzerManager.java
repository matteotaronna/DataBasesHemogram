package hemogram.db.interfaces;

import hemogram.db.pojos.*;

public interface AnalyzerManager 
{
	public void insertAnalyzer(Analyzer analyzer);
	public Analyzer getAnalyzer (int analyzerId);
	public Analyzer logInAnalyzer (String name, String work_user);
	public int getAnalyzerId (Analyzer analyzer);
}
