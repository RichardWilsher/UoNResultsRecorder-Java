import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UoNResultsController implements ActionListener{
	
	/**
	 * Program: 	UoNResults
	 * Filename:	UoNResultsController.java
	 * @author:		© Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */

	/*
	* Controller class of the MVC Model
	* used to deal with interactions with the GUI
	*/
	
	private boolean check = false;
	private UoNResultsModel model;
	private UoNResultsView view;
	private ResultRow[] resultsRow = new ResultRow[6];
	
	public UoNResultsController(UoNResultsModel model) {
		// controller constructor used to add the model class
		this.model = model;
	}
	public void addView(UoNResultsView view, ResultRow[] resultsRow) {
		// method to add the View class and associated resultrow class (probably unnecessary)
		this.view = view;
		this.resultsRow = resultsRow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// action listeners for the GUI controls
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="First") {
			// first year button
			check=false;
			model.setYear(1);
			view.addModules();
			check=true;
		}
		if (e.getActionCommand()=="Second") {
			// second year button
			check = false;
			model.setYear(2);
			view.addModules();
			check=true;
		}
		if (e.getActionCommand()=="Third") {
			// third year button
			check=false;
			model.setYear(3);
			view.addModules();
			check=true;
		}
		if (e.getActionCommand()=="Final") {
			// final award button
			JOptionPane.showMessageDialog(null,  "Note award calculation is an estimation, actual award can be different\n" + model.calculateFinalAward(view.getGateway()));
		}
		if (e.getActionCommand().equals("gateway")) {
			// gateway combobox
			check = false;
			view.setGateway(view.getGateway());
			view.addModules();
			check = true;
        }
		//assessment combo boxes first number is the assessment number (1 for 1, 2 for 2), second number is for the array position 0-5
		// need to check if these can be consolidated into a single one as they update all combo boxes and save all values to the file
		// check is used to avoid infinite loops when setting all the assessment values to the combo boxes
		if (e.getActionCommand().equals("Assessment10")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(0);
				String assessment2 = view.getAssessment2(0);
				String code = view.getCode(0);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment20")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(0);
				String assessment2 = view.getAssessment2(0);
				String code = view.getCode(0);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment11")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(1);
				String assessment2 = view.getAssessment2(1);
				String code = view.getCode(1);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment21")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(1);
				String assessment2 = view.getAssessment2(1);
				String code = view.getCode(1);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment12")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(2);
				String assessment2 = view.getAssessment2(2);
				String code = view.getCode(2);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment22")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(2);
				String assessment2 = view.getAssessment2(2);
				String code = view.getCode(2);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment13")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(3);
				String assessment2 = view.getAssessment2(3);
				String code = view.getCode(3);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment23")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(3);
				String assessment2 = view.getAssessment2(3);
				String code = view.getCode(3);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment14")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(4);
				String assessment2 = view.getAssessment2(4);
				String code = view.getCode(4);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment24")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(4);
				String assessment2 = view.getAssessment2(4);
				String code = view.getCode(4);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment15")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(5);
				String assessment2 = view.getAssessment2(5);
				String code = view.getCode(5);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
		if (e.getActionCommand().equals("Assessment25")) {
			if (check == true) {
				String assessment1 = view.getAssessment1(5);
				String assessment2 = view.getAssessment2(5);
				String code = view.getCode(5);
				model.recalculate(code, assessment1, assessment2);
				view.updateGrades();
				model.saveModules();
			}
		}
	}
}
