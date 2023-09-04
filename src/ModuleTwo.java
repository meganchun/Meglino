/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * This class will display the second module about indexes, where the user's will have to match the fruit with its
 * index number by clicking the fruit followed by the index number
 * 
 * Major Skills:
 * - for loops
 * - arrays
 * - if statements
 * 
 * Added Features:
 * - randomized array
 * 
 * Areas of Concern:
 * 
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources: none
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ModuleTwo extends JFrame implements ActionListener {

	//background image
	public JLabel background = new JLabel(new ImageIcon("images/moduleTwoBackground.png"));
	
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);	
	
	//create array of Icons for the food and numbers
	public Icon[] foodIcons;
	public Icon[] numIcons;
	
	//create array of JButtons for the food and numbers
	public JButton[] foodButtons;
	public JButton[] numButtons;
	
	//image for number button
	
	//Array for the foods
	public String[] foodArray;
	//JLabel for the foodArray
	public JLabel foodArrayLabel;
	
	//String to check which fruit was pressed
	public String fruit;
	
	//score variable 
	public int score;
	
	//keep track of how many moves were completed
	public int numCompleted;
	
	//constructor method
	public ModuleTwo() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Module 2"); 
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
		
		//initalize food array with fruit elements
		foodArray = new String[5];
		foodArray[0] = "orange";
		foodArray[1] = "pear";
		foodArray[2] = "avocado";
		foodArray[3] = "lemon";
		foodArray[4] = "watermelon";
		
		//create an array of food icons
		foodIcons = new Icon[5];
		
		//create an array of number icons
		numIcons = new Icon[5];
		
		//fill the icons array with the icons
		for (int i = 0; i < 5; i++) {
			//add food icon to the foodIcon array
			foodIcons[i] = new ImageIcon("images/"+foodArray[i]+".png");
			//add num icon to the numIcons array
			numIcons[i] = new ImageIcon("images/num"+i+".png");
		}
		
		//randomize and shuffle the list of fruits in the foodArray
		List<String> shuffleFood = Arrays.asList(foodArray);

		Collections.shuffle(shuffleFood);

		shuffleFood.toArray(foodArray);
		
		//JLabel to display the shuffled foodArray
		foodArrayLabel = new JLabel("{"+foodArray[0]+","+foodArray[1]+","+
				foodArray[2]+","+foodArray[3]+","+foodArray[4]+"}");
		foodArrayLabel.setBounds(625, 198, 491, 38);
		foodArrayLabel.setFont(new Font("Helvetica", Font.PLAIN, 25)); //set font and size
		foodArrayLabel.setForeground(new Color(114,196,58));
		background.add(foodArrayLabel);
	
		//initlaize array to hold all the buttons of food
		foodButtons = new JButton[5];
		
		int counter = 0; //initalize counter
		
		//initalize x and y coordinate of buttons
		for (int x = 611; x <= 1027; x += 104) {
			//create a button with a food icon of index of the counter
			foodButtons[counter] = new JButton(foodIcons[counter]);
			foodButtons[counter].addActionListener(this);
			foodButtons[counter].setBounds(x, 265, 87, 87);
			foodButtons[counter].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
			background.add(foodButtons[counter]);
			counter++; //increment counter
		}
		
		//reset the counter for the number buttons
		counter = 0;
		
		//initalize array of buttons for the number buttons
		numButtons = new JButton[5];
		
		//use a for loop to iterate through each x value of the buttons
		for (int x = 611; x <= 1027; x += 104) {
			//create a button with a number icon of index of the counter
			numButtons[counter] = new JButton(numIcons[counter]);
			numButtons[counter].addActionListener(this);
			numButtons[counter].setBounds(x, 380, 87, 87);
			numButtons[counter].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
			background.add(numButtons[counter]);
			counter++; //increment counter
		}
		
		//set the window to visible
		setVisible(true);
		
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the back button is pressed
		if (e.getSource() == backBtn) {
			//create a new instance of the homepage frame
			new HomepageFrame();
			//set the current window to not visible
			setVisible(false);
		}
		
		 JButton button = (JButton) e.getSource();
		 
		 //if the orange button was clicked
		 if (button == foodButtons[0]) {	
			 //set the fruit variable to orange
			 fruit = "orange";
		 }
		//if the pear button was clicked
		 else if (button == foodButtons[1]) {	
			 //set the fruit variable to pear
			 fruit = "pear";
		 }
		 //if the avocado button was clicked
		 else if (button == foodButtons[2]) {	
			 //set the fruit variable to avocado
			 fruit = "avocado";
		 }
		 //if the lemon button was clicked
		 else if (button == foodButtons[3]) {	
			 //set the fruit variable to lemon
			 fruit = "lemon";
		 }
		 //if the watermelon button was clicked
		 else if (button == foodButtons[4]) {	
			 //set the fruit variable to watermelon
			 fruit = "watermelon";
		 }
		 
		 //checks if the right index button was clicked after the corrresponding food was clicked
		 if (button == numButtons[Arrays.asList(foodArray).indexOf("orange")]) {
			 
			 //if the fruit is the correct fruit
			 if (fruit == "orange") {
				 //set the number button of the fruit to not visible
				 numButtons[Arrays.asList(foodArray).indexOf("orange")].setVisible(false);
				 score++; //increment score
				//set the food button of the fruit to not visible
				 foodButtons[0].setVisible(false);
				 //play music
				 ModuleFour.playMusic("audio/correct.wav");
				 
				 //if the user has finished matching all the fruits
				 if (score == 5) {
					 //update the user's progress
					 ModuleOne.addProgress(2);
					 //set the window to not visible
					 setVisible(false);
					 //create a new instance of the ModuleTwo Frame
					 new ModuleTwo();
				 }
			 }
			 //if the user clicks the wrong correpsonding button
			 else {
				 //play music
				 ModuleFour.playMusic("audio/wrong.wav");
				 //restart the module
				 setVisible(false);
				 new ModuleTwo();
			 }
		 }
		 //checks if the right index button was clicked after the corrresponding food was clicked
		 if (button == numButtons[Arrays.asList(foodArray).indexOf("pear")]) {
		 
			//if the fruit is the correct fruit
			 if (fruit == "pear") {
				//set the number button of the fruit to not visible
				 numButtons[Arrays.asList(foodArray).indexOf("pear")].setVisible(false);
				 score++; //increment score
				 //set the food button of the fruit to not visible
				 foodButtons[1].setVisible(false);
				 //play music
				 ModuleFour.playMusic("audio/correct.wav");
				 
				 //if the user has finished matching all the fruits
				 if (score == 5) {
					 //update the user's progress
					 ModuleOne.addProgress(1);
					//set the window to not visible
					 setVisible(false);
					 //create a new instance of the ModuleTwo Frame
					 new ModuleTwo();
				 }
			}
			 //if the user clicks the wrong correpsonding button
			 else {
				 ModuleFour.playMusic("audio/wrong.wav");
				 setVisible(false);
				 new ModuleTwo();
			 }
		 }
		 //checks if the right index button was clicked after the corrresponding food was clicked
		 if (button == numButtons[Arrays.asList(foodArray).indexOf("avocado")]) {
		 
			 //if the fruit is the correct fruit
			 if (fruit == "avocado") {
				 //set the number button of the fruit to not visible
				 numButtons[Arrays.asList(foodArray).indexOf("avocado")].setVisible(false);
				 score++; //increment score
				 //set the food button of the fruit to not visible
				 foodButtons[2].setVisible(false);
				 //play music
				 ModuleFour.playMusic("audio/correct.wav");
				 
				 if (score == 5) {
					 //update the user's progress
					 ModuleOne.addProgress(1);
					 //set the window to not visible
					 setVisible(false);
					 //create a new instance of the ModuleTwo Frame
					 new ModuleTwo();
				 }
			 }
			 //if the user clicks the wrong correpsonding button
			 else {
				//play music
				 ModuleFour.playMusic("audio/wrong.wav");
				 //set the window to not visible
				 setVisible(false);
				 //create a new instance of the ModuleTwo Frame
				 new ModuleTwo();
			 }
		 }
		 //checks if the right index button was clicked after the corrresponding food was clicked
		 if (button == numButtons[Arrays.asList(foodArray).indexOf("lemon")]) {
		 
			 //if the fruit is the correct fruit
			 if (fruit == "lemon") {
				 //set the number button of the fruit to not visible
				 numButtons[Arrays.asList(foodArray).indexOf("lemon")].setVisible(false);
				 score++; //increment score
				 //set the food button of the fruit to not visible
				 foodButtons[3].setVisible(false);
				 //play music
				 ModuleFour.playMusic("audio/correct.wav");
					 
				 if (score == 5) {
					 //update the user's progress
					 ModuleOne.addProgress(1);
					 //set the window to not visible
					 setVisible(false);
					 //create a new instance of the ModuleTwo Frame
					 new ModuleTwo();
				 }
			 }
			 //if the user clicks the wrong correpsonding button
			 else {
				 //play music
				 ModuleFour.playMusic("audio/wrong.wav");
				 //set the window to not visible
				 setVisible(false);
				 //create a new instance of the ModuleTwo Frame
				 new ModuleTwo();
			 }
			 
			
		 }
		 //checks if the right index button was clicked after the corrresponding food was clicked
		 if (button == numButtons[Arrays.asList(foodArray).indexOf("watermelon")]) {
		 
			 //if the fruit is the correct fruit
			 if (fruit == "watermelon") {
				 //set the number button of the fruit to not visible
				 numButtons[Arrays.asList(foodArray).indexOf("watermelon")].setVisible(false);
				 score++; //increment score
				 //set the food button of the fruit to not visible
				 foodButtons[4].setVisible(false);
				 //play music
				 ModuleFour.playMusic("audio/correct.wav");
				 
				 if (score == 5) {
					 //update the user's progress
					 ModuleOne.addProgress(1);
					 //set the window to not visible
					 setVisible(false);
					 //create a new instance of the ModuleTwo Frame
					 new ModuleTwo();
				 }
			 }
			 //if the user clicks the wrong correpsonding button
			 else  {
				 //play music
				 ModuleFour.playMusic("audio/wrong.wav");
				 //set the window to not visible
				 setVisible(false);
				 //create a new instance of the ModuleTwo Frame
				 new ModuleTwo();
			 }
		 }
	}

}
