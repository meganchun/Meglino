/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * The homepage frame will provided user's with the option to read and learn, practice their skills, or take a quiz
 * 
 *  Major Skills:
 * - JFrame
 * 
 * Added Features: none
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources: none
 * 
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomepageFrame extends JFrame implements ActionListener {
	
	public JLabel background = new JLabel(new ImageIcon("images/homepageBackground.png"));
	
	//images for button
	public ImageIcon enter = new ImageIcon("images/enterBtn.png");
	public ImageIcon getStarted = new ImageIcon("images/getStartedBtn.png");
	
	//buttons using the images above
	public JButton enterBtn1 = new JButton(enter);
	public JButton enterBtn2 = new JButton(enter);
	public JButton enterBtn3 = new JButton(enter);
	public JButton getStartedBtn = new JButton(getStarted);
	
	//create an instance of the quizFrame 
	public static QuizFrame quizFrame;
	
	
	public HomepageFrame() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Welcome to Meglino"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//button to the education page
		enterBtn1.setBounds(90, 450, 188, 104);
		enterBtn1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn1.addActionListener(this); //add action listener
		background.add(enterBtn1); //add to the window
		
		//button to the practice page
		enterBtn2.setBounds(415, 380, 188, 104);
		enterBtn2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn2.addActionListener(this); //add action listener
		background.add(enterBtn2); //add to the window
		
		//button to the quiz page
		enterBtn3.setBounds(1005, 430, 188, 104);
		enterBtn3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn3.addActionListener(this); //add action listener
		background.add(enterBtn3); //add to the window
		
		//button to the practice page
		getStartedBtn.setBounds(400, 200, 412, 142);
		getStartedBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		getStartedBtn.addActionListener(this); //add action listener
		background.add(getStartedBtn); //add to the window
		
		//set the window to visible
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the learn button is clicked
		if (e.getSource() == enterBtn1) {
			//create a new instance of the EducationFrame
			new EducationFrame();
			//set current window to not visible
			setVisible(false);
			
		}
		//if the practice button is clicked
		if (e.getSource() == enterBtn2) {
			//create a new instance of the PracticeFrame
			new PracticeFrame();
			//set current window to not visible
			setVisible(false);
			
		}
		//if the get started button is clicked
		if (e.getSource() == getStartedBtn) {
			//create a new instance of the PracticeFrame
			new PracticeFrame();
			//set current window to not visible
			setVisible(false);
			
		}
		//if the quiz button is clicked
		if (e.getSource() == enterBtn3) {
			//create a new instance of the QuizFrame
			quizFrame = new QuizFrame();
			//set current window to not visible
			setVisible(false);
		}
	}
	

}
