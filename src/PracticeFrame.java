/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * The practice frame will display the user's progress as well as the module based on if the
 * user has completed the preivous levels prior. They can choose which module they would like to 
 * play while seeing their progress of the course
 * 
 *  Major Skills:
 * - arrays
 * - if statements
 * - Scanner input
 * 
 * Added Features:
 * - Saves and updates progress even after program is closed
 * - Locks certain levels if they user has not passed the previous levels
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources: see indivual classes for more info
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PracticeFrame extends JFrame implements ActionListener {
	
	//background image
	public JLabel background = new JLabel(new ImageIcon("images/practiceBackground.png"));
	
	//checked and uncheck image
	public JLabel checked;
	public JLabel unchecked;
	
	//play and locked image
	public JLabel play;
	public JLabel locked;
	
	//image for pie chart progress
	public JLabel overallProgress;
		
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);
	
	//images for the module buttons
	public ImageIcon module1 = new ImageIcon("images/module1.png");
	public ImageIcon module2 = new ImageIcon("images/module2.png");
	public ImageIcon module3 = new ImageIcon("images/module3.png");
	public ImageIcon module4 = new ImageIcon("images/module4.png");

	//buttons using the images above
	public JButton module1Btn = new JButton(module1);
	public JButton module2Btn = new JButton(module2);
	public JButton module3Btn = new JButton(module3);
	public JButton module4Btn = new JButton(module4);
	
	public static boolean[] moduleUnlocked;
	
	public PracticeFrame() {
		
		//intialize the moduleUnlocked array with size of 4
		moduleUnlocked = new boolean[4];
		
		//call the findProgress method to determine which levels are unlockedd
		findProgress();
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Practice"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//button for module one
		module1Btn.setBounds(380, 122, 783, 130);
		module1Btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		module1Btn.addActionListener(this); //add action listener
		background.add(module1Btn);
		
		//button for module two
		module2Btn.setBounds(381, 224, 783, 130);
		module2Btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		module2Btn.addActionListener(this); //add action listener
		background.add(module2Btn);
		
		//button for module three
		module3Btn.setBounds(381, 326, 783, 130);
		module3Btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		module3Btn.addActionListener(this); //add action listener
		background.add(module3Btn);
		
		//button for module four
		module4Btn.setBounds(381, 428, 783, 130);
		module4Btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		module4Btn.addActionListener(this); //add action listener
		background.add(module4Btn);
		
		//back button
		backBtn.setBounds(61, 60, 35, 35); //set location and size
		backBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		backBtn.addActionListener(this); //add action listener
		background.add(backBtn);
		
		//initlaize the counter for the index of the module
		int index = 0;
		//initalize variable to determine how many levels are completed/unlocked
		int numModuleUnlocked = 0;
		
		//iterate through each module to check if it is locked or unlocked and
		//display the progess components (pie chart, unlock/unlocked icon)
		for (int i = 222; i <= 502; i+=89) {
			
			//create a new image for each module
			checked = new JLabel(new ImageIcon("images/checked.png")); //if it is unlocked
			unchecked = new JLabel(new ImageIcon("images/unchecked.png")); //if it is locked
			
			locked = new JLabel(new ImageIcon("images/locked.png")); //if it is locked
			
			//if the index/module number is unlocked
			if (moduleUnlocked[index] == true) {
				
				//increment the number of modules unlocked
				numModuleUnlocked++;
				//set the bounds of the check mark on the side panel
				checked.setBounds(60, i, 35, 35);
				background.add(checked);
				
				play = new JLabel(new ImageIcon("images/play.png")); //if it is unlocked
				
				//module 1
				if (index == 0) {
					play.setBounds(100, 171, 44, 44);
					//add a play button
					module1Btn.add(play);
				}
				//module 2
				else if (index == 1) {
					play.setBounds(100, 25, 44, 44);
					//add a play button
					module2Btn.add(play);
				}
				//module 3
				else if (index == 2) {
					play.setBounds(100, 25, 44, 44);
					//add a play button
					module3Btn.add(play);
				}
				//module 4
				else {
					play.setBounds(100, 25, 44, 44);
					//add a play button
					module4Btn.add(play);
				}
				
				
			}
			//if the index/module number is locked
			else {
				//set the bounds and and the grey check mark on the side panel
				unchecked.setBounds(60, i, 35, 35);
				background.add(unchecked);
				
				//module 1
				if (index == 0) {
					locked.setBounds(100, 25, 44, 44);
					//add a locked button
					module1Btn.add(locked);
				}
				//module 2
				else if (index == 1) {
					locked.setBounds(100, 25, 44, 44);
					//add a locked button
					module2Btn.add(locked);
				}
				//module 3
				else if (index == 2) {
					locked.setBounds(100, 25, 44, 44);
					//add a locked button
					module3Btn.add(locked);
				}
				//module 4
				else {
					locked.setBounds(100, 25, 44, 44);
					//add a locked button
					module4Btn.add(locked);
				}
			}
			//if it has finished iterating through every module
			if (index == 3) {
				
				//if one module has been unlocked
				if (numModuleUnlocked == 1) {
					//quater circle
					overallProgress = new JLabel(new ImageIcon("images/quarter.png"));
					overallProgress.setBounds(58, 125, 38, 38);
					background.add(overallProgress);
				}
				//if two modules has been unlocked
				else if (numModuleUnlocked == 2) {
					//half a circle 
					overallProgress = new JLabel(new ImageIcon("images/half.png"));
					overallProgress.setBounds(58, 125, 38, 38);
					background.add(overallProgress);
				}
				//if three modules has been unlocked
				else if (numModuleUnlocked == 3) {
					//three quarters of a circle
					overallProgress = new JLabel(new ImageIcon("images/threeQuarters.png"));
					overallProgress.setBounds(58, 125, 38, 38);
					background.add(overallProgress);
					
				}
				//if all four modules has been unlocked
				else {
					//full circle
					overallProgress = new JLabel(new ImageIcon("images/full.png"));
					overallProgress.setBounds(58, 125, 38, 38);
					background.add(overallProgress);
				}
				
			}
			//increment the index of the module
			index++;
			
		}
		//set the window to visible
		setVisible(true);

	}
	//find the most recent progress of the user and store it into an array
	public void findProgress() {
		
		//initialize the number of lines
	    int lines = 0;
	    
		//check how many scores are in the highscores.txt file
		File file = new File("data/progress.txt");
		
		// https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/
		//create a new line number reader for the txt file
	    try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

	    	//while the line is not empty
	    	while (lnr.readLine() != null) ;

	    	//the number of lines becomes the line number
	    	lines = lnr.getLineNumber();

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    
		try {
			
			//use the scanner object to access the file
			Scanner inputFile = new Scanner(new File("data/progress.txt"));
			
			for (int i = 0; i < lines; i++) {
				//set a delimiter which will stop scanning the data for one row of progress
				inputFile.useDelimiter(",|\r\n");
				
				//create boolean variables for each level/module
				boolean module1 = inputFile.nextBoolean();
				boolean module2 = inputFile.nextBoolean();
				boolean module3 = inputFile.nextBoolean();
				boolean module4 = inputFile.nextBoolean();
				
				//add the latest data to the moduleUnlocked array
				moduleUnlocked[0] = module1;
				moduleUnlocked[1] = module2;
				moduleUnlocked[2] = module3;
				moduleUnlocked[3] = module4;
					
			}
			//close the scanner
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			//if the file is not found output an error message
			System.out.println("File error");
		}
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//if the user presses module 1 button and the module is unlocked
		if (e.getSource() == module1Btn && moduleUnlocked[0] == true) {
			//create a new instance of the ModuleOne class
			new ModuleOne();
			//set the window to not visible
			setVisible(false);
		}
		//if the user presses module 2 button and the module is unlocked
		if (e.getSource() == module2Btn && moduleUnlocked[1] == true) {
			//create a new instance of the ModuleTwo class
			new ModuleTwo();
			//set the window to not visible
			setVisible(false);
		}
		//if the user presses module 3 button and the module is unlocked
		if (e.getSource() == module3Btn && moduleUnlocked[2] == true) {
			//create a new instance of the ModuleThree class
			new ModuleThree();
			//set the window to not visible
			setVisible(false);
		}
		//if the user presses module 4 button and the module is unlocked
		if (e.getSource() == module4Btn && moduleUnlocked[3] == true) {
			//create a new instance of the ModuleFour class
			new ModuleFour();
			//set the window to not visible
			setVisible(false);
		}
		//if the user presses the back button
		if (e.getSource() == backBtn) {
			//create a new instance of the Homepage class
			new HomepageFrame(); 
			//set the window to not visible
			setVisible(false);
			
		}
	}
}
