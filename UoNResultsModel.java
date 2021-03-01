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

	private String path="D:\\Temp\\";
	//private BufferedReader gatewayInFile;
	//private BufferedReader moduleInFile;
	private PrintWriter moduleOutFile;
	//private String gatewayTitle;
	private Gateway[] gateways = new Gateway[7];
	//private String[] moduleTitles = new String[17];
	private Module[] modules = new Module[28];
	
	//private String code;
	//private String title;
	//private int noOfAssessments;
	//private String assignment1;
	//private String assignment2;
	//private String grade;
	private int year = 1;
	
	public UoNResultsModel() {
		
		try {
			FileInputStream gwayinput = new FileInputStream(path + "gateways.dat");
			ObjectInputStream gwaystream = new ObjectInputStream(gwayinput);
			int count =0;
			Gateway obj = null;
			while((obj=(Gateway)gwaystream.readObject())!= null) {
				gateways[count] = obj;
				count++;
			}
			gwaystream.close();
		} catch (EOFException e) {
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
			FileInputStream modinput = new FileInputStream(path + "modules.dat");
			ObjectInputStream modstream = new ObjectInputStream(modinput);
			int count = 0;
			Module obj = null;
			while((obj=(Module)modstream.readObject())!= null) {
				modules[count] = obj;
				count++;
			}
			modstream.close();
		} catch (EOFException e) {
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
		
		/*try {
			gatewayInFile = new BufferedReader(new FileReader(path+"gateways.txt"));
			String line;
			for(int l=0;l<7;l++) {
				line = gatewayInFile.readLine();
				gatewayTitle = line;
				for (int j=0; j<17;j++) {
					line = gatewayInFile.readLine();
					moduleTitles[j] = line;
					//System.out.println(line);
				}
				gateways[l]=new Gateway(gatewayTitle, moduleTitles);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("Error in try");
		}
		try {
			moduleInFile = new BufferedReader(new FileReader(path+"modules.txt"));
			String line;
			for (int l=0; l<28;l++) {
				code = moduleInFile.readLine();
				title = moduleInFile.readLine();
				line = moduleInFile.readLine();
				noOfAssessments = Integer.parseInt(line);
				assignment1 = moduleInFile.readLine();
				assignment2 = moduleInFile.readLine();
				modules[l] = new Module(code, title, noOfAssessments, assignment1, assignment2);
				//System.out.println(code + ", " + title + ", " + noOfAssessments + ", " + assignment1 + ", " + assignment2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*for (int l =0; l<7;l++) {
			for (int j=0; j<17; j++) {
				
			}
		}*/
	}
	
	public Module getModule(String ModuleCode) {
		String ModCode = ModuleCode;
		int m=0;
		for (int l=0;l<28;l++) {
			if (ModCode.equals(modules[l].getCode())) {
				m = l;
			}
		}
		//System.out.println(modules[m].getCode() + ", " + modules[m].getTitle() + ", " + modules[m].getAssessment1() + ", " + modules[m].getAssessment2()); 
		return modules[m];
	}
	
	public String getGatewayTitles(int l) {
		return gateways[l].getTitle();
	}
	
	public String getGatewayModules(String gt, int l) {
		// working here
		int gatewayT =0;
		for(int j=0;j<7;j++) {
			String title = gateways[j].getTitle();
			if (gt.equals(title)) {
				gatewayT = j;
				//System.out.println(gt);
				//System.out.println(gateways[j].getTitle());
				//System.out.println(gatewayT);
				} 
			}
		String tempCode = gateways[gatewayT].getModuleCode(l);
		//System.out.println(gatewayT);
		//System.out.println(tempCode);
		//System.out.println(l);
		//String tempCode = moduleTitles[l];
		
		/*for (int loop=0;loop<7;loop++) {
			for (int j=0;j<17;j++) {
				System.out.println(gateways[loop].getModuleCode(j));
			}
		}*/
		return tempCode;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int y) {
		this.year = y;
		//System.out.println(year);
	}
	
	public void recalculate(String code, String assessment1, String assessment2) {
		String modCode = code;
		String modAssessment1 = assessment1;
		String modAssessment2 = assessment2;
		int p=-1;
		for (int i=0; i<28; i++) {
			if (modCode.equals(modules[i].getCode())) {
				p=i;
				//System.out.println(p);
			}
		}
		modules[p].setAssessment1(modAssessment1);
		modules[p].setAssessment2(modAssessment2);
		modules[p].calculateGrade();
	}
	
	public void saveModules() {
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
		
		
		
		/*try {
			moduleOutFile = new PrintWriter(new FileWriter(path+"modules.txt"), true);
			for (int l=0; l<28;l++) {
				moduleOutFile.println(modules[l].getCode());
				moduleOutFile.println(modules[l].getTitle());
				moduleOutFile.println(modules[l].getNoOfAssessments());
				moduleOutFile.println(modules[l].getAssessment1());
				moduleOutFile.println(modules[l].getAssessment2());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public String calculateFinalAward(String gatewayTitle) {
		double finalAve = 0;
		double temp = 0.0;
		double secondYearAve = 0.0;
		double thirdYearAve = 0.0;
		double dissertation = 0.0;
		double lowest = 25.0;
		
		for (int l = 6; l < 12; l++) {
			for (int j=0; j<28;j++) {
				if (getGatewayModules(gatewayTitle, l).equals(modules[j].getCode())){
					temp = modules[j].convertGrade(modules[j].getGrade()) * 2;
					if (temp<lowest) {
						lowest = temp;
					}
					secondYearAve += temp;
					//System.out.println(temp);
				}
			}
		}
		secondYearAve -= lowest;
		lowest = 25;
		for (int l = 12; l < 17; l++) {
			for (int j=0; j<28;j++) {
				if (getGatewayModules(gatewayTitle, l).equals(modules[j].getCode())){
					temp = modules[j].convertGrade(modules[j].getGrade()) * 2;
					//System.out.println(temp);
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
		thirdYearAve += dissertation;
		
		finalAve = (secondYearAve + (thirdYearAve * 2))/13;
		
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
