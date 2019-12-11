/**
 * UserInterface class creates the user interactive web form with, menu options, exit option, getting URL pane, fetching the data from the URl, saving to text and JSON file. 
 * Along with those buttons, it creates the action listener that listens to each click and performs actions accordingly.
 * 
 * @author NP
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UserInterface extends JFrame {

// calling the ArrayList of Prezs to use in further steps.
ArrayList<President> prezs = new ArrayList<President>();
/**
 * 	setupMenu function will setup the menu bar on the web scraper form.
 */
	public void setupMenu() {
	
        JMenuBar mbar = new JMenuBar();    // create menu bar
        JMenu mnuFile = new JMenu("File");  // create menu named file    
        JMenuItem miExit = new JMenuItem("Exit"); // create menu item named exit
        JMenu mnuHelp = new JMenu("Help");    // create menu named help
        JMenuItem miAbout = new JMenuItem("About");   // create menu item named about in help menu.
       
        // add action listener to about menu item.
        miAbout.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		 // show the message box with author details in about menu option
        JOptionPane.showMessageDialog(null,"Author: Neel Patel\nCaMS Student,Lewis University\nClass: Object Oriented Programming");
        	 }
        });
        // is user chooses exit menu item form file menu, then quit. 
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mnuFile.add(miExit);   // add menu item exit to menu name file
        mbar.add(mnuFile);     // add file menu to menu bar
        mnuHelp.add(miAbout);  // add About menu item to help menu
        mbar.add(mnuHelp);     // add help menu to menu bar
        setJMenuBar(mbar);     // add menu bar to itself
    }
/**
 * public contructor that will create the userinterface form and help interact with it.
 */
public UserInterface()  // constructor
{
	setTitle("Web Scrapper"); // title of the form 
	setBounds(100,50,600,800); // dimensions
	setDefaultCloseOperation(EXIT_ON_CLOSE); // exit on close button 
	
	Container ct = getContentPane(); // create container 
	
	BorderLayout bl = new BorderLayout();  // add border layout
	ct.setLayout(bl);  // add border layout to the container 
	
	JPanel panSouth = new JPanel(); // create north and south panels 
	JPanel panNorth = new JPanel();
	
	panNorth.setLayout(new FlowLayout()); // add layout to the panels
    panSouth.setLayout(new FlowLayout());
	
	JLabel label = new JLabel("Enter Website:"); // label that will aske user to enter URL 
    JTextField webURL = new JTextField(40); // 40 lower-case m's
    
	
    panNorth.add(label);  // add label to north panel
    panNorth.add(webURL); // add web URL field to north panel
	ct.add(panNorth, BorderLayout.NORTH);
    
	JButton saveT = new JButton("Save to Text"); // create button named save to text
	
	// add action listener to the button 
	saveT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		ArrayList<President> presidentList = prezs;
			
		// if the ArrayList is empty, print show the message box with error else prompt the user to save the data to any directory when they click the button.  
	            if (prezs.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Oops! No data found!");
	            } 
	            else 
	            {
	                JFileChooser fileChooser = new JFileChooser();   // create file chooser object
	                int finalData = fileChooser.showOpenDialog(null);
	                if (finalData == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = fileChooser.getSelectedFile();
	                    Writer.saveToText(prezs, selectedFile);     // write the data from prezs ArrayList to selected file 
	                    JOptionPane.showMessageDialog(null, "File saved Successfully!");  // show the success message when done. 
		}
	}
		}
	});
	panSouth.add(saveT);  // ads the save to text button to south panel 
	ct.add(panSouth, BorderLayout.SOUTH); //add the south panel 
	
	JButton saveJSON = new JButton("Save to JSON");  // create button named save to JSON 
	saveJSON.addActionListener(new ActionListener() // add action listener to the button 
	{
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<President> prezList = prezs;
			
			if (prezs.isEmpty()) // if arrayList is empty, print messagebox saying no nata found, else allow the user to save the data from ArrayList to selected file
			{
                JOptionPane.showMessageDialog(null, "Oops! No data found!");
            } else {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Writer.saveToJSON(selectedFile, prezs);
                    JOptionPane.showMessageDialog(null, "File saved Successfully!");
			}	 
		}
	}
	});
	panSouth.add(saveJSON); // add save to JSON button to south panel 
	ct.add(panSouth,BorderLayout.SOUTH); // add south panel 
	
	JTextArea txt = new JTextArea(); // add the text area at the center of the web scrapper form. 
     Font f = new Font("Monospaced",Font.BOLD,24);  // set font type and and size 
     txt.setFont(f); 
     txt.setEditable(false);  // disable text editing
	ct.add(txt, BorderLayout.CENTER);  // add textarea to the center of the container 
	
	// create the scroll pane vertically and horizontally as required.
	JScrollPane pane = new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    ct.add(pane); // add the pane to container 
	
	 JButton fetch = new JButton("Fetch"); // add fetch button to the north panel.
	    fetch.addActionListener(new ActionListener() // add action listener to fetch button to fetch data from the URL 
	    {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	String data = webURL.getText(); 
	        	String str;
	            Scanner sc = new Scanner(data);
	            String addr = sc.nextLine();
	             try {
	                 URL link = new URL(addr);
	                 Scanner usc = new Scanner(link.openStream());
	                 
	                 while (usc.hasNextLine()) {
	                    str = usc.nextLine();
	                     txt.setText(txt.getText() + str + "\n");
	                 }
	                 String totalHtml = txt.getText().replace("\n",""); // storing the fetched html into text
	                 
	                 prezs = DataParser.convertTotalHtmlToPrez(totalHtml); // add the fetched data that is formated, into the text area
	                 txt.setText("");
	                 
	                 boolean firstPrez = true; // while the ArrayList contains data
	                 
	                 for (President prez: prezs) // iterate through the ArrayList and print the data 
	                 {
	                	 if(firstPrez) 
	                	 {
	                		 String header = "PRESIDENT				  TERM BEGAN		    TERM ENDED";
	    	                 System.out.println(header);
	    	                 txt.setText(txt.getText() + header + "\n");
	                	 }
	                	 firstPrez = false;          
	                	 
	                 System.out.println(prez);       // print the data on screen 
	                 txt.setText(txt.getText() + prez + "\n");
	                 }
	                 usc.close();
	             } catch (Exception ex) {
	                 System.out.println("Something went wrong!");;
	             }
	        }
	    });
	    panNorth.add(fetch); // add fetch button to the north panel
	   
	setupMenu();  // call the setup menu function 
	 
}
}
