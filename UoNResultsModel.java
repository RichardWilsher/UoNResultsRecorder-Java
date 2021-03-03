import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class UoNResultsModel {
	
	/**
	 * Program: 	UoNResults
	 * Filename:	UoNResultsModel.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */

	/*
	* Model part of the MVC Model
	* deals with storing and most of the interactions of the data
	*/

	private String path="D:\\Temp\\";
	private PrintWriter moduleOutFile;
	private Gateway[] gateways = new Gateway[7];
	private Module[] modules = new Module[28];
	private int year = 1;
	
	public UoNResultsModel() {
		/*
		* Model constructor, takes no parameters, but reads in the gateways.dat and modules.dat files
		 */
		
		try {
			// read in the Gateways file
			FileInputStream gwayinput = new FileInputStream(path + "gateways.dat");
			ObjectInputStream gwaystream = new ObjectInputStream(gwayinput);
			int count =0; // array index
			Gateway obj = null; //set temp variable to null before starting  to read in objects
			while((obj=(Gateway)gwaystream.readObject())!= null) { // read in object and cast to Gateway object
				gateways[count] = obj; // store in array location
				count++; //increment array location
			}
			gwaystream.close();
		} catch (EOFException e) {
			// ignore the End of File exception as it will trigger at the end of file, but the while loop ensures it is not used
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// read in the Modules file
			FileInputStream modinput = new FileInputStream(path + "modules.dat");
			ObjectInputStream modstream = new ObjectInputStream(modinput);
			int count = 0; // array index
			Module obj = null; // set temp variable to null before starting  to read in objects
			while((obj=(Module)modstream.readObject())!= null) { // read in object and cast to Module object
				modules[count] = obj; // store in array location
				count++; //increment array location
			}
			modstream.close();
		} catch (EOFException e) {
			// ignore the End of File exception as it will trigger at the end of file, but the while loop ensures it is not used
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Module getModule(String ModuleCode) {
		// method which takes a module code and returns the returns the whole module data
		String ModCode = ModuleCode;
		int m=0;
		for (int l=0;l<28;l++) { // search through the modules array for the code we are looking for and save the location
			if (ModCode.equals(modules[l].getCode())) {
				m = l;
			}
		}
		return modules[m];
	}
	
	public String getGatewayTitles(int l) {
		return gateways[l].getTitle();
	}
	
	public String getGatewayModules(String gt, int l) {
		//return the module code at position l from the selected gateway from the 7 stored gateways
		int gatewayT =0;
		for(int j=0;j<7;j++) { // search for the gateway and return the position
			String title = gateways[j].getTitle();
			if (gt.equals(title)) {
				gatewayT = j;
				} 
			}
		String tempCode = gateways[gatewayT].getModuleCode(l);
		return tempCode;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int y) {
		this.year = y;
	}
	
	public void recalculate(String code, String assessment1, String assessment2) {
		//recalculate the final grade when it is changed
		String modCode = code;
		String modAssessment1 = assessment1;
		String modAssessment2 = assessment2;
		int p=-1;
		for (int i=0; i<28; i++) {
			if (modCode.equals(modules[i].getCode())) {
				p=i;
			}
		}
		modules[p].setAssessment1(modAssessment1);
		modules[p].setAssessment2(modAssessment2);
		modules[p].calculateGrade();
	}
	
	public void saveModules() {
		// save the modules back to the modules file
		try {
			FileOutputStream modoutput = new FileOutputStream(path + "modules.dat");
			ObjectOutputStream modstream = new ObjectOutputStream(modoutput);
			for (int i = 0; i < 28; i++) {
				modstream.writeObject(modules[i]);
			}
			modstream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String calculateFinalAward(String gatewayTitle) {
		// method to estimate the final award of the degree by calculating the mean average of the 2nd and 3rd year results
		// minus the lowest grade from both years, the dissertation has to always be included however
		double finalAve = 0;
		double temp = 0.0;
		double secondYearAve = 0.0;
		double thirdYearAve = 0.0;
		double dissertation = 0.0;
		double lowest = 25.0;
		// calculate the total of the 2nd year modules, finally taking the lowest value away
		for (int l = 6; l < 12; l++) {
			for (int j=0; j<28;j++) {
				if (getGatewayModules(gatewayTitle, l).equals(modules[j].getCode())){
					temp = modules[j].convertGrade(modules[j].getGrade()) * 2; // grade is doubled as method call returns 1/2 the value
					if (temp<lowest) {
						lowest = temp;
					}
					secondYearAve += temp;
				}
			}
		}
		secondYearAve -= lowest;
		lowest = 25;
		// calculate the total of the 3rd year modules (excluding the dissertation at this time) then taking away the lowest value
		for (int l = 12; l < 17; l++) {
			for (int j=0; j<28;j++) {
				if (getGatewayModules(gatewayTitle, l).equals(modules[j].getCode())){
					temp = modules[j].convertGrade(modules[j].getGrade()) * 2; // grade is doubled as method call returns 1/2 the value
					if (getGatewayModules(gatewayTitle, l).equals("CSY4010")) {
						dissertation = temp;
					} else {
					if (temp<lowest) {
						lowest = temp;
					}
						thirdYearAve += temp;
					}
				}
			}
		}
		thirdYearAve -= lowest;
		thirdYearAve += dissertation; // then add the dissertation
		
		finalAve = (secondYearAve + (thirdYearAve * 2))/13; // double weight the third year results
		// finally return the award
	    if (finalAve >= 20.50) {
			return "First Class Degree";
		}else if (finalAve >= 17.50) {
	        return "Upper Second Class Degree";
		}else if (finalAve >= 14.50) {
			return "Lower Second Class Degree";
		}else if (finalAve >= 11.50) {
			return "Third Class Degree";
		}else {
			return "Fail";
		}
	}
}
