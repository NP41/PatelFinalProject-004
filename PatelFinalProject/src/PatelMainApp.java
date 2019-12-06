/**
 * This main class launches the program by creating and showing the web-scrapper form. 
 * Based on the form, user can collect data from a website and store it into JSON or text file format.
 * @author NP
 *
 */

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.net.URL;
import java.util.Scanner;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

public class PatelMainApp extends JFrame {
	
	public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");       
        JMenuItem miExit = new JMenuItem("Exit");
        JMenu mnuHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        
        miAbout.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"Author: Neel Patel\nCaMS Student,Lewis University\nClass: Object Oriented Programming");
        	 }
        });
        
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mnuFile.add(miExit);
        mbar.add(mnuFile);
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
        setJMenuBar(mbar);
    }

public PatelMainApp()  // constructor
{
	setTitle("Web Scrapper");
	setBounds(100,50,600,800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	Container ct = getContentPane();
	
	BorderLayout bl = new BorderLayout();
	ct.setLayout(bl);
	
	JPanel panSouth = new JPanel();
	JPanel panNorth = new JPanel();
	
	panNorth.setLayout(new FlowLayout());
    panSouth.setLayout(new FlowLayout());
	
	JLabel label = new JLabel("Enter Website:");
    JTextField webURL = new JTextField(40); // 40 lower-case m's
    
	
    panNorth.add(label);
    panNorth.add(webURL);
	ct.add(panNorth, BorderLayout.NORTH);
    
	JButton saveT = new JButton("Save to Text");
	saveT.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("ho ho ho!");
		}
	});
	panSouth.add(saveT);
	ct.add(panSouth, BorderLayout.SOUTH);
	
	JButton saveJSON = new JButton("Save to JSON");
	panSouth.add(saveJSON);
	ct.add(panSouth,BorderLayout.SOUTH);
	
	JTextArea txt = new JTextArea();
     Font f = new Font("Monospaced",Font.BOLD,24);
     txt.setFont(f);
	ct.add(txt, BorderLayout.CENTER);
	
	 JButton fetch = new JButton("Fetch");
	    fetch.addActionListener(new ActionListener() {
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
	                 usc.close();
	             } catch (Exception ex) {
	                 ex.printStackTrace();
	             }
	        }
	    });
	    panNorth.add(fetch);
	    panNorth.add(fetch);

	setupMenu();
	
}

/**
 * The main function will launch the program by showing the window to access data from web.
 * @param args
 */
public static void main (String[] args) {
	
	PatelMainApp papp = new PatelMainApp();   // PatelMaiApp object that will call its constructor. 
	papp.setVisible(true);   // this will show the user interface
	
}

}
