/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * Module One will allow user's to practice their creating and declaring arrays using the 
 * information provided in a prompt
 * 
 *  Major Skills:
 * - if statements
 * - JTextFields
 * - Scanner input
 * 
 * Added Features: none
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources:
 * - //https://www.digitalocean.com/community/tutorials/java-append-to-file (append a file)
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModuleOne extends JFrame implements ActionListener {

	public JLabel background = new JLabel(new ImageIcon("images/moduleOneBackground.png"));
	
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);
	
	//create a list of arrays for the different name and data type options
	public String[] nameOptions = {"biswas","samantha","kelvin","megan"};
	public String[] dataTypeOptions = {"int","boolean","long","double","String"};
 	
	public String dataType;
	public int size;
	public String name;
	
	public JTextArea prompt;
	public JTextField textField;
	
	public ImageIcon enter = new ImageIcon("images/enterBtn2.png");;
	public JButton enterBtn = new JButton(enter);
	
	//constructor method
	public ModuleOne() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Module 1"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//back button
		backBtn.setBounds(61, 60, 35, 35); //set location and size
		backBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		backBtn.addActionListener(this);
		background.add(backBtn);
		
		//create a random object
		Random rand = new Random();
		
		//create two random number
		int nameRand = rand.nextInt(4); //random number for which name is to be displayed
		int dataRand = rand.nextInt(5); //random number for which data type is to be displayed
		
		//set the variables to a random index
		name = nameOptions[nameRand];
		dataType = dataTypeOptions[dataRand];
		size = rand.nextInt(101); //create a random number from 0-100
		
		//display the prompt that will tell the user what array to create
		prompt = new JTextArea("Declare and create a(n) "
				+ dataType +" array \n\nwith a size of "+ size +" called "+ name);
		prompt.setFont(new Font("Helvetica", Font.PLAIN, 25)); //set font and size
		prompt.setForeground(new Color(114,196,58));
		prompt.setBounds(595, 200, 447, 76);
		background.add(prompt);
		
		//text field that will allow user's to type their answer
		textField = new JTextField();
		textField.setBounds(595, 320, 393, 59);
		textField.addActionListener(this);
		textField.setEditable(true); //allow the user to edit the field
		textField.setForeground(new Color(114,196,58));
		textField.setFont(new Font("Courier", Font.PLAIN, 20)); 
		background.add(textField);
		
		//add the back button
		enterBtn.setBounds(1016, 320, 98, 59);
		enterBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn.addActionListener(this);
		background.add(enterBtn);
		
		//set the window to visible
		setVisible(true);
				
	}
	public static void addProgress(int moduleNum) {
		
		////https://www.digitalocean.com/community/tutorials/java-append-to-file (append a file)
		//set the next module to unlocked
		PracticeFrame.moduleUnlocked[moduleNum+1] = true;
		
		//create a string of boolean elements in the moduleUnlocked array that is comma seperated 
		String unlocked = PracticeFrame.moduleUnlocked[0]+","+PracticeFrame.moduleUnlocked[1]+
				","+PracticeFrame.moduleUnlocked[2]+","+PracticeFrame.moduleUnlocked[3];
		
		//https://www.digitalocean.com/community/tutorials/java-append-to-file
		//add the players score to the high score file
		FileWriter fr = null;
		try {
			fr = new FileWriter("data/progress.txt", true);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedWriter br = new BufferedWriter(fr);
		try {
			br.write("\r\n"+unlocked);
			br.close();
			fr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//String of the exact answer
		String answer = dataType+"[] "+name+" = new "+dataType+"["+size+"]";
		
		//if the user presses the enter button
		if (e.getSource() == enterBtn) {
			//if the text in the text field is equal to the answer String
			if (textField.getText().equals(answer)) {
				//play music
				ModuleFour.playMusic("audio/correct.wav");
				//call the addProgress method to update their progress
				addProgress(0);
				//create a new instance of the ModuleOne class
				new ModuleOne();
			}
			else {
				//play music
				ModuleFour.playMusic("audio/wrong.wav");
			}
		}
		//if the back button is pressed
		if (e.getSource() == backBtn) {
			//create a new instance of the homepage frame
			new HomepageFrame();
			//set the current window to not visible
			setVisible(false);
		}
	}
	

}
