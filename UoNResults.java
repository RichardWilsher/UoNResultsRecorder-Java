import java.awt.*;

import javax.swing.*;

public class UoNResults extends JFrame {

	/**
	 * Program: 	UoNResults
	 * Filename:	UoNResults.java
	 * @author:		� Richard Wilsher (2021)
	 * Course:		CSY2030 System Design & Development
	 * Tutor:		Apkar Salatian
	 * @version:	1.0 final
	 * Date:		28/02/2021
	 */
	
	public static void main(String[] args) {

		/*
		 * Program to record individual assessments for University of Northampton Computing pathway Modules
		 * Employs Model View Controller (MVC), OOP principles, serialization of files.
		 * Main method setups the window and MVC classes
		 */
		// setup window
		JFrame window = new JFrame(); // set up main window
		window.setLayout(new FlowLayout()); // lay out of window is left to right
		// create model, View and controller
		UoNResultsModel model = new UoNResultsModel();
		UoNResultsController controller = new UoNResultsController(model);
		UoNResultsView view = new UoNResultsView(controller, model, window);
		//configure window
		window.setLocationRelativeTo(null);
		window.setTitle("University of Northampton - Results Recorder");
		window.setResizable(false);
		window.setSize(700, 300); 
		window.setVisible(true); 
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
