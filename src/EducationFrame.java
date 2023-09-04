/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * The education frame will display all the key lessons of array that will provided them
 * with basic knowledge will diving into some more advanced components
 * 
 * Major Skills:
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EducationFrame extends JFrame implements ActionListener{ 
	
	//background image
	JLabel background = new JLabel(new ImageIcon("images/educationBackground.png"));
	
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);	
	
	//scroll pane
	JScrollPane scrollPane;
	JPanel panel = new JPanel();
	
	//constuctor method
	public EducationFrame() {
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Learn"); 
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
		
		//create a new instance of a JScrollPane to the background
		scrollPane = new JScrollPane(background);
		scrollPane.setBounds(0, 0, 1200, 600);
		
		//set the panel bounds and layout manager
		panel.setBounds(0, 0, 1200, 600);
		panel.setLayout(null);
		
		//add the scroll pane to the panel
		//then add the panel to the frame
		panel.add(scrollPane);
		add(panel);
		
		//set the visibility of the window to true
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if the back button is pressed
		if (e.getSource() == backBtn) {
			//create a new instance of the homepage frame
			new HomepageFrame();
			//set the current window to not visible
			setVisible(false);
		}
	}

}
