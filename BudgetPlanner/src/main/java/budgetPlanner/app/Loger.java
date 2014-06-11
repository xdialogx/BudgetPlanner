package budgetPlanner.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Loger {
	
private boolean errorFlag = false;
	
	public void LogExceptions(Exception e) {
		try {
			FileWriter fstream=new FileWriter("exception.log");
			BufferedWriter out=new BufferedWriter(fstream);
			out.write(e.toString());
			out.close();
		} catch (IOException e1) {
			setErrorFlag(true);
		}      
	}

	public boolean getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

}
