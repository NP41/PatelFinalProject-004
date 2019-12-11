/**
 * This main class launches the program by  showing the web-scrapper form. 
 * Based on the form, user can collect data from a website and store it into JSON or text file format.
 * In this project I will extract data form a URL containing list of presidents of the U.S until now, their term begin date and end date. 
 * @author NP
 *
 */

public class PatelMainApp {

/**
 * The main function will launch the program by showing the window to access data from web.
 * @param args
 */
public static void main (String[] args) {
	
	UserInterface ui = new UserInterface();   // UserInterface object that will call its constructor. 
	ui.setVisible(true);   // this will show and implement the user interface
	
}

}
