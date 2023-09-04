/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * This frame will display the user's score based on their performance on the quiz
 * 
 *  Major Skills:
 * - JLabel
 * 
 * Added Features: 
 * - check marks indicating which question they got right or wrong
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources: none
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ResultsFrame extends JFrame implements ActionListener {
	
	//background image
	public JLabel background = new JLabel(new ImageIcon("images/resultsBackground.png"));
	
	//image and button for home button
	public ImageIcon home = new ImageIcon("images/homeBtn.png");
	public JButton homeBtn = new JButton(home);
	
	//image and button for again button
	public ImageIcon again = new ImageIcon("images/againBtn.png");
	public JButton againBtn = new JButton(again);
	
	//images for the correct and incorrect checkbox
	public JLabel checkbox;
	public JLabel wrongbox;
	
	//Jlabel of the score
	public JLabel scoreLabel;
	
	//Jlabel of the achievement
	public JLabel achievementLabel;
	
	//String to represent their achievement
	public String achievement;


	public ResultsFrame(int score) {
		
		//if the user scored more than 50%
		if (score > 5) {
			achievement = "Congratulations!";
		}
		//if the user score less than 50%
		else {
			achievement = "Nice Try";
		}
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle(achievement); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//home button
		homeBtn.setBounds(349, 440, 224, 68);
		homeBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		homeBtn.addActionListener(this);
		background.add(homeBtn);
		
		//again button
		againBtn.setBounds(623, 440, 224, 68);
		againBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		againBtn.addActionListener(this);
		background.add(againBtn);
	
		//score label of the user's percentage score
		scoreLabel = new JLabel((Integer.toString(score*10))+"%",SwingConstants.CENTER);
		scoreLabel.setBounds(542, 132, 115, 53);
		scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 35)); //set font and size
		scoreLabel.setForeground(Color.white);
		background.add(scoreLabel);
		
		//level of achievement label
		achievementLabel = new JLabel(achievement,SwingConstants.CENTER);
		achievementLabel.setBounds(0, 282, 1200, 60);
		achievementLabel.setFont(new Font("Helvetica", Font.BOLD, 40)); //set font and size
		background.add(achievementLabel);
		
		//create counter for the question number
		int questionNum = 0;
		
		for (int x = 296; x <= 854; x += 62) {
			if (QuizFrame.questionsResult[questionNum] == true) {
				checkbox = new JLabel(new ImageIcon("images/checkbox.png"));
				checkbox.setBounds(x, 365, 50, 50);
				background.add(checkbox);
			}
			else {
				wrongbox = new JLabel(new ImageIcon("images/wrongbox.png"));
				wrongbox.setBounds(x, 365, 50, 50);
				background.add(wrongbox);
			}
			questionNum++;
			
		}
		
		//set the window to visible
		setVisible(true);
	}
	public static void main(String[] args) {
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//if the home button is clicked
		if (e.getSource() == homeBtn) {
			//create a new instance of the HomepageFrame
			new HomepageFrame();
			//set the window to not visible
			setVisible(false);
		}
		//if the again button is clicked
		if (e.getSource() == againBtn) {
			//create a new instance of the QuizFrame
			new QuizFrame();
			//set the window to not visible
			setVisible(false);
		}
	}

}
