import javax.swing.*;
import java.awt.*;

public class UoNResultsView extends JFrame {
	
	/**
	 * Program: 	UoNResults
	 * Filename:	UoNResultsView.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */
	
	
	/*
	 * View part of the MVC model
	 * deals with the GUI, setting it up and reading/changing values   
	 */
	private JFrame window;
	private JPanel mainPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton firstYearButton = new JButton("First Year");
	private JButton secondYearButton = new JButton("Second Year");
	private JButton thirdYearButton = new JButton("Third Year");
	private JButton finalGradeButton = new JButton("Final Grade");
	private JComboBox gateway;
	private JLabel codeLabel = new JLabel("Module Code");
	private JLabel nameLabel = new JLabel("Module Name");
	private JLabel firstGradeLabel = new JLabel("1st Grade");
	private JLabel secondGradeLabel = new JLabel("2nd Grade");
	private JLabel finalGradeLabel = new JLabel("Final Grade");
	
	private UoNResultsModel model;
	private UoNResultsController controller;
	private Module module;
	private ResultRow[] resultRow = new ResultRow[6];
	
	private String[] moduleCodes = new String[6];
	private String gatewayTitle;

	public UoNResultsView(UoNResultsController controller, UoNResultsModel model, JFrame window) {
		/*
		 * Constructor for UoNResultsView class
		 * accepts the links to the model, controller and the window created to display the GUI
		 */
		this.window = window;
		this.controller = controller;
		this.model = model;
		controller.addView(this, resultRow); //pass a link to the this class to the controller 
		// create 6 instances of the ResultRow class, 1 for each module taken in a year
		for (int l=0; l<6; l++) {
			resultRow[l] = new ResultRow(model, controller, l);
		}
		window.setLayout(new BorderLayout() );// border layout allows simple placement of 3 panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout()); // flow layout due to only 1 element on this panel
		final DefaultComboBoxModel gatewayTitles = new DefaultComboBoxModel();
		for (int l=0;l<7;l++) {
			gatewayTitles.addElement(model.getGatewayTitles(l)); // add the 7 tracked gateways to the combobox
		}
		gateway = new JComboBox(gatewayTitles); //create the combo box
		gateway.addActionListener(controller); //add the actionlistener to the controller class
		gateway.setActionCommand("gateway"); //add command to identify this control in the controller class
		topPanel.add(gateway);// add combobox to panel
		window.add(topPanel, BorderLayout.NORTH); //add the first panel to the top of window
		mainPanel.setLayout(new FlowLayout());// Flow layout to allow for different sized controls
		// I had to remove the ability to resize the window so the controls remain consistent
		// add labels as column headers
		codeLabel.setPreferredSize(new Dimension (80,22));
		mainPanel.add(codeLabel);
		nameLabel.setPreferredSize(new Dimension (300,22));
		mainPanel.add(nameLabel);
		firstGradeLabel.setPreferredSize(new Dimension (80,22));
		mainPanel.add(firstGradeLabel);
		secondGradeLabel.setPreferredSize(new Dimension (80,22));
		mainPanel.add(secondGradeLabel);
		finalGradeLabel.setPreferredSize(new Dimension (80,22));
		mainPanel.add(finalGradeLabel);
		// add controls to display module information, stored in a separate class to allow iterative control over them
		for (int l=0; l<6;l++) {
			resultRow[l].addCodeText(mainPanel);
			resultRow[l].addNameText(mainPanel);
			resultRow[l].addAssessment1Text(mainPanel);
			resultRow[l].addAssessment2Text(mainPanel);
			resultRow[l].addButton(mainPanel);
			resultRow[l].addGradeText(mainPanel);
		}
		window.add(mainPanel, BorderLayout.CENTER); // add the main panel to the center of the window
		bottomPanel.setLayout(new FlowLayout()); // flow layout due to 4 buttons being on the form
		bottomPanel.add(firstYearButton);
		firstYearButton.addActionListener(controller);
		firstYearButton.setActionCommand("First");
		bottomPanel.add(secondYearButton);
		secondYearButton.addActionListener(controller);
		secondYearButton.setActionCommand("Second");
		bottomPanel.add(thirdYearButton);
		thirdYearButton.addActionListener(controller);
		thirdYearButton.setActionCommand("Third");
		bottomPanel.add(finalGradeButton);
		finalGradeButton.addActionListener(controller);
		finalGradeButton.setActionCommand("Final");
		window.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public String getGateway() {
		// method to return the selected element of the gateway combobox
		if (gateway.getSelectedIndex() != -1) {
			String selection = (String) gateway.getItemAt(gateway.getSelectedIndex());
			return selection;
		} else 
			return "NULL";
	}
	
	public void addModules() {
		// method to populate controls with module data
		int max;
		int year = model.getYear();
		int offset=0; // offset to filter data to correct year
		Module tempModule;
		if (year == 3) {
			max = 5; // clause for 5 modules in 3rd year
			resultRow[5].hideRow();
		} else {
			max = 6; // otherwise show all 6
			resultRow[5].showRow();
		}
		for (int l=0; l<max; l++) {
			offset = (year -1) * 6;// offset is 0 for 1st year, 6 for second year, 12 for 3rd year
			int pos = l + offset;// calculate the position in the array using the offset
			String temp = model.getGatewayModules(gatewayTitle, pos); // get the module code for the module at the offset array position for the appropriate gateway
			resultRow[l].setModuleCodeText(temp); //set the module code text box in resultrow
			tempModule = model.getModule(temp); //get the module from the model
			resultRow[l].setNameText(tempModule.getTitle()); //set the name text box in resultrow
			resultRow[l].setAssessment1Text(tempModule.getAssessment1()); //set the assessment1 combobox in resultrow
			resultRow[l].setAssessment2Text(tempModule.getAssessment2()); //set the assessment2 combobox in resultrow
			resultRow[l].setFinalGradeText(tempModule.getGrade()); //calculate and store the module result in the final grade text
			if (tempModule.getNoOfAssessments() == 1) {
				resultRow[l].hideAssessment2Text(); // hide the 2nd assessment combobox if only 1 assessment for course
			} else {
				resultRow[l].showAssessment2Text(); // otherwise show it
			}
		}
	}
	
	public void setGateway(String gatewayTitle) {
		// set the gateway to the selected gateway
		this.gatewayTitle = gatewayTitle;
	}
	
	public String getAssessment1(int i) {
		// get the value of assessment1Text from resultrow class
		return resultRow[i].getAssessment1Value();
	}
	
	public String getAssessment2(int i) {
		// get the value of assessment2Text from resultrow class
		return resultRow[i].getAssessment2Value();
	}
	
	public String getCode(int i) {
		// get the value of moduleCodeText from resultrow class
		return resultRow[i].getCode();
	}
	
	public void updateGrades() {
		/*
		 * Method to update the grades, primarily the final grade when the assessment combo boxes are changed
		 * based off of addModules(), should look into how the 2 methods can be integrated/concatenated
		 */
		int max;
		int year = model.getYear();
		int offset=0; // offset to filter correct year
		Module tempModule;
		if (year == 3) {
			max = 5; // clause for 3rd year having 5 modules
		} else {
			max = 6; // Otherwise 6 for the 1st and 2nd years
		}
		for (int l=0; l<max; l++) {
			offset = (year -1) * 6;// offset is 0 for 1st year, 6 for second year, 12 for 3rd year
			int pos = l + offset;// calculate the position in the array using the offset
			String temp = model.getGatewayModules(gatewayTitle, pos);
			resultRow[l].setModuleCodeText(temp);//set the module code text box in resultrow
			tempModule = model.getModule(temp);//get the module from the model
			resultRow[l].setFinalGradeText(tempModule.getGrade());//calculate and store the module result in the final grade text
		}
	}
}
