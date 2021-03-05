import java.io.Serializable;

public class Module implements Serializable{
	
	/**
	 * Program: 	UoNResults
	 * Filename:	Module.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */

	/*
	* Class to store the Module information
	*/

	private String code;
	private String title;
	private int noOfAssessments;
	private String assessment1;
	private String assessment2;
	private String grade;
	
	public Module(String code, String title, int noOfAssessments, String assessment1, String assessment2) {
		//constructor taking all values except the grade as the grade is not stored but calculated
		this.code = code;
		this.title = title;
		this.noOfAssessments = noOfAssessments;
		this.assessment1 = assessment1;
		this.assessment2 = assessment2;
		calculateGrade();
	}
	
	public Module() {
		
	}
	
	public double convertGrade(String grade) {
		// method to take the letter grade and return a double value, halved as average of 2 grades
		// should remove the halving at this stage due to the whole value used in the Model
		double value = 0;
		
		switch(grade) {
		case "A+":
			value = (25.0) / 2;
		break;
		case "A":
			value = (23.0) / 2;
		break;
		case "A-":
			value = (21.0) / 2;
		break;
		case "B+":
			value = (20.0) / 2;
		break;
		case "B":
			value = (19.0) / 2;
		break;
		case "B-":
			value = (18.0) / 2;
		break;
		case "C+":
			value = (17.0) / 2;
		break;
		case "C":
			value = (16.0) / 2;
		break;
		case "C-":
			value = (15.0) / 2;
		break;
		case "D+":
			value = (14.0) / 2;
		break;
		case "D":
			value = (13.0) / 2;
		break;
		case "D-":
			value = (12.0) / 2;
		break;
		case "F+":
			value = (11.0) / 2;
		break;
		case "F":
			value = (8.0) / 2;
		break;
		case "F-":
			value = (2.0) / 2;
		break;
		case "G":
			value = 0;
		break;
		case "AG":
			value = 0;
		break;
		case "LG":
			value = 0;
		break;
		case "NG":
			value = 0;
		break;
		}
		
		return value;
	}


	public void calculateGrade() {
		// method to add 2 assessment grades and return the module letter grade
		double value1 = 0;
		double value2 = 0;

		if(noOfAssessments == 2) {
			value1 = convertGrade(assessment1);
			value2 = convertGrade(assessment2);
			double moduleValue = value1 + value2;
			
			if (moduleValue >= 24.0) {
				grade = "A+";
			} else if (moduleValue >= 22.0) {
				grade = "A";
			} else if (moduleValue >= 20.50) {
				grade = "A-";
			}else if (moduleValue >= 19.50) {
				grade = "B+";
			}else if (moduleValue >= 18.50) {
				grade = "B";
			}else if (moduleValue >= 17.50) {
				grade = "B-";
			}else if (moduleValue >= 16.50) {
				grade = "C+";
			}else if (moduleValue >= 15.50) {
				grade = "C";
			}else if (moduleValue >= 14.50) {
				grade = "C-";
			}else if (moduleValue >= 13.50) {
				grade = "D+";
			}else if (moduleValue >= 12.50) {
				grade = "D";
			}else if (moduleValue >= 11.50) {
				grade = "D-";
			}else if (moduleValue >= 9.50) {
				grade = "F+";
			}else if (moduleValue >= 6.00) {
				grade = "F";
			}else if (moduleValue >= 2.00) {
				grade = "F-";
			}else {
				grade = "G";
			}
		} else {
			grade = assessment1;
		}
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNoOfAssessments() {
		return noOfAssessments;
	}

	public void setNoOfAssessments(int noOfAssessments) {
		this.noOfAssessments = noOfAssessments;
	}

	public String getAssessment1() {
		return assessment1;
	}

	public void setAssessment1(String assessment1) {
		this.assessment1 = assessment1;
	}

	public String getAssessment2() {
		return assessment2;
	}

	public void setAssessment2(String assessment2) {
		this.assessment2 = assessment2;
	}

	public String getGrade() {
		calculateGrade();
		return grade;
	}
	
	
}
