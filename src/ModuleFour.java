/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * Module Four will allow user's to practice their knowledge of 2D array indexes but selecting the
 * food in the index provided
 * 
 *  Major Skills:
 * - for loops
 * - arrays
 * - if statements
 * 
 * Added Features: none
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources: 
 * - //https://www.youtube.com/watch?v=wJO_cq5XeSA (music)
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ModuleFour extends JFrame implements ActionListener {

	//background image
	public JLabel background = new JLabel(new ImageIcon("images/moduleFourBackground.png"));
	
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);	
	
	//create array of the icons for the buttons
	public Icon[][] foodIcons;
	
	//create array of JButtons
	public JButton[][] foodArray;
	
	//int array to hold index
	public int[] indexes;
	
	//hold to random values for [x,y] index
	public Random rand1;
	public Random rand2;
	
	//JLabel to display the index
	public JLabel indexLabel;
	
	public ModuleFour() {

		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Module 4"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//back button
		backBtn.setBounds(61, 60, 35, 35); //set location and size
		backBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		backBtn.addActionListener(this); //add action listener
		background.add(backBtn); //add to the window
		
		//create array to store indexes [x,y]
		indexes = new int[2];
		
		//intialize random number
		rand1 = new Random();
		rand2 = new Random();
		
		//set a random index 
		indexes[0] = rand1.nextInt(3); //from 0-2
		indexes[1] = rand1.nextInt(5); //from 0-4
		
		//add and display indexLabel to the windoww
		indexLabel = new JLabel("["+indexes[0]+","+indexes[1]+']');// indexes are the randomly generated index
		indexLabel.setFont(new Font("Helvetica", Font.PLAIN, 50)); //set font and size
		indexLabel.setForeground(Color.white); //change colour
		indexLabel.setBounds(162, 390, 110, 75);
		background.add(indexLabel);
		
		
		//initalize the foodIcons array to hold the rows and columns of the icons
		foodIcons = new Icon[3][5];
		
		int counter = 1; //initalize counter
		
		//fill the icons array with the icons
		//for loop to iterate through each row of icons
		for(int row = 0; row < 3; row++) {
			//for loop to iterate through each column of icons
			for (int col = 0; col < 5; col++) {
				//add the food based on iteration number
				  foodIcons[row][col] = new ImageIcon("images/food"+counter+".png");  
				  counter++; //increment counte
			}
		}
		
		//initlaize arraay to hold all the buttons of food
		foodArray = new JButton[3][5];
		
		//initalize x and y coordinate of buttons
		int y = 185;
		int x;
		
		//for loop to iterate through each row
		for(int row = 0; row < 3; row++) {
			//reset or set x value to 510
			x = 510;
			//for loop to iterate through each column
			for (int col = 0; col < 5; col++) {
				  foodArray[row][col] = new JButton(foodIcons[row][col]); //create a new button with the food icon
				  foodArray[row][col].addActionListener(this);
				  foodArray[row][col].setBounds(x, y, 70, 70);
				  foodArray[row][col].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
				  background.add(foodArray[row][col]); //add button to the window	  
				  //increment spacing between each button
				  x+=125;
			}
			y += 125;
		}
	
		//set the frame visible
		setVisible(true);
		
	}

	//playMusic method will play music based on the file path
	public static void playMusic(String filepath) {
		
		//https://www.youtube.com/watch?v=wJO_cq5XeSA
		try {
			//create a file
			File musicPath = new File(filepath);
			
			//if the file exists
			if (musicPath.exists()) {
				
				//create an audio input stream
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				//create a clip of the audio
				Clip clip = AudioSystem.getClip();
				//open the audio input stream
				clip.open(audioInput);
				
				//start to the audio
				clip.start();
			}
			//if the file is not found, print an error message
			else {
				System.out.println("can't find file");
			}
		}
		
		catch (Exception e) 
		{
			System.out.println("error");
		}
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
		
		//when a button is clicked on the screen, store the button into button
		 JButton button = (JButton) e.getSource();
		 
		 //if the icon in the index number displayed is the button at the same index in the foodArray
		 if (foodArray[indexes[0]][indexes[1]] == button) {
			 //play audioo
			 playMusic("audio/correct.wav");
			 //create a new instance of the ModuleFour class
			 new ModuleFour();
			 //destroy the current frame
			 dispose();
		 }
		 //if the button that was clicked was not the correct button at the correct index and is 
		 //not the back button
		 else if (foodArray[indexes[0]][indexes[1]] != button && e.getSource() != backBtn) {
			 //play music
			 playMusic("audio/wrong.wav");
		 }
	
	}
}
