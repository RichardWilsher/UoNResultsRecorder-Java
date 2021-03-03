import java.awt.Dimension;
import javax.swing.*;

public class ResultRow{
	
	/**
	 * Program: 	UoNResults
	 * Filename:	ResultsRow.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */

	/*
	* Class to allow iterative control over the (5 or)6 modules displayed on the screen
	*/

	private JTextField moduleCodeText;
	private JTextField moduleNameText;
	private JComboBox assessment1Text;
	private JComboBox assessment2Text;
	private JButton button;
	private JTextField finalGradeText;
	private String[] selections = { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F+", "F", "F-", "G", };

	private UoNResultsModel model;
	private UoNResultsController controller;
	
	public ResultRow(UoNResultsModel model, UoNResultsController controller, int l) {
		/*
		* Constructor for the Result Row Class
		* accepts the model and controller as well as an integer identifying which array location is being accessed
		*/
		String assess1;
		String assess2;
		this.model = model; //attach model to retrieve data
		this.controller = controller; //attach controller to add actionlisteners
		this.moduleCodeText = new JTextField();
		moduleCodeText.setPreferredSize(new Dimension(80, 22));
		moduleCodeText.setEditable(false);
		this.moduleNameText = new JTextField();
		moduleNameText.setPreferredSize(new Dimension(300, 22));
		moduleNameText.setEditable(false);
		this.assessment1Text = new JComboBox(selections);
		assessment1Text.addActionListener(this.controller);
		assess1 = "Assessment1"+l;//variable to attach actionlistener command to, to identify this control
		assessment1Text.setActionCommand(assess1);
		assessment1Text.setPreferredSize(new Dimension(80, 22));
		assessment1Text.setEditable(false);
		this.assessment2Text = new JComboBox(selections);
		assessment2Text.addActionListener(this.controller);
		assess2 = "Assessment2"+l;//variable to attach actionlistener command to, to identify this control
		assessment2Text.setActionCommand(assess2);
		assessment2Text.setPreferredSize(new Dimension(80, 22));
		assessment2Text.setEditable(false);
		button = new JButton(" ");//button usually hidden, used to plug the gap when a module with only 1 assessment is displayed
		button.setPreferredSize(new Dimension(80, 22));
		button.setVisible(false);
		button.setOpaque(false); 
		button.setContentAreaFilled(false); 
		button.setBorderPainted(false); 
		button.setFocusable(false);
		this.finalGradeText = new JTextField();
		finalGradeText.setPreferredSize(new Dimension(80, 22));
		finalGradeText.setEditable(false);
	}
	
	public void addCodeText(JPanel mainPanel) {
		mainPanel.add(moduleCodeText);
	}

	public void addNameText(JPanel mainPanel) {
		mainPanel.add(moduleNameText);
	}
	
	public void addAssessment1Text(JPanel mainPanel) {
		mainPanel.add(assessment1Text);
	}
	
	public void addAssessment2Text(JPanel mainPanel) {
		mainPanel.add(assessment2Text);
	}
	
	public void addGradeText(JPanel mainPanel) {
		mainPanel.add(finalGradeText);
	}
	
	public void addButton(JPanel mainPanel) {
		mainPanel.add(button);
	}
	
	public void setModuleCodeText(String text) {
		moduleCodeText.setText(text);
	}
	
	public void setNameText(String text) {
		moduleNameText.setText(text);
	}
	
	public void setAssessment1Text(String text) {
		switch(text) {
		case "A+":
			assessment1Text.setSelectedIndex(0);
		break;
		case "A":
			assessment1Text.setSelectedIndex(1);
		break;
		case "A-":
			assessment1Text.setSelectedIndex(2);
		break;
		case "B+":
			assessment1Text.setSelectedIndex(3);
		break;
		case "B":
			assessment1Text.setSelectedIndex(4);
		break;
		case "B-":
			assessment1Text.setSelectedIndex(5);
		break;
		case "C+":
			assessment1Text.setSelectedIndex(6);
		break;
		case "C":
			assessment1Text.setSelectedIndex(7);
		break;
		case "C-":
			assessment1Text.setSelectedIndex(8);
		break;
		case "D+":
			assessment1Text.setSelectedIndex(9);
		break;
		case "D":
			assessment1Text.setSelectedIndex(10);
		break;
		case "D-":
			assessment1Text.setSelectedIndex(11);
		break;
		case "F+":
			assessment1Text.setSelectedIndex(12);
		break;
		case "F":
			assessment1Text.setSelectedIndex(13);
		break;
		case "F-":
			assessment1Text.setSelectedIndex(14);
		break;
		case "G":
			assessment1Text.setSelectedIndex(15);
		break;
		}
	}
	
	public void setAssessment2Text(String text) {
		switch(text) {
		case "A+":
			assessment2Text.setSelectedIndex(0);
		break;
		case "A":
			assessment2Text.setSelectedIndex(1);
		break;
		case "A-":
			assessment2Text.setSelectedIndex(2);
		break;
		case "B+":
			assessment2Text.setSelectedIndex(3);
		break;
		case "B":
			assessment2Text.setSelectedIndex(4);
		break;
		case "B-":
			assessment2Text.setSelectedIndex(5);
		break;
		case "C+":
			assessment2Text.setSelectedIndex(6);
		break;
		case "C":
			assessment2Text.setSelectedIndex(7);
		break;
		case "C-":
			assessment2Text.setSelectedIndex(8);
		break;
		case "D+":
			assessment2Text.setSelectedIndex(9);
		break;
		case "D":
			assessment2Text.setSelectedIndex(10);
		break;
		case "D-":
			assessment2Text.setSelectedIndex(11);
		break;
		case "F+":
			assessment2Text.setSelectedIndex(12);
		break;
		case "F":
			assessment2Text.setSelectedIndex(13);
		break;
		case "F-":
			assessment2Text.setSelectedIndex(14);
		break;
		case "G":
			assessment2Text.setSelectedIndex(15);
		break;
		}
	}
	
	public void setFinalGradeText(String text) {
		finalGradeText.setText(text);
	}
	
	public void hideAssessment2Text() {
		assessment2Text.setVisible(false);
		button.setVisible(true);
	}
	
	public void showAssessment2Text() {
		assessment2Text.setVisible(true);
		button.setVisible(false);
	}
	
	public String getAssessment1Value() {
		return (String) assessment1Text.getSelectedItem();
	}
	
	public String getAssessment2Value() {
		return (String) assessment2Text.getSelectedItem();
	}
	
	public String getCode() {
		return moduleCodeText.getText();
	}
	
	public void hideRow() {
		moduleCodeText.setVisible(false);
		moduleNameText.setVisible(false);
		assessment1Text.setVisible(false);
		assessment2Text.setVisible(false);
		finalGradeText.setVisible(false);
	}
	
	public void showRow() {
		moduleCodeText.setVisible(true);
		moduleNameText.setVisible(true);
		assessment1Text.setVisible(true);
		assessment2Text.setVisible(true);
		finalGradeText.setVisible(true);
	}
	
}
