import java.io.Serializable;

public class Gateway implements Serializable{
	
	/**
	 * Program: 	UoNResults
	 * Filename:	Gateway.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */

	private String title;
	private String[] modules = new String[17];
	
	public Gateway(String title, String[] modules) {
		this.title = title;
		for (int l=0;l<17;l++) {
			this.modules[l] = modules[l];
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getModules() {
		return modules;
	}

	public void setModules(String[] modules) {
		this.modules = modules;
	}
	
	public String getModuleCode(int t) {
		return modules[t];
	}
}
