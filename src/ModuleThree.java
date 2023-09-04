/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * This class will display the third module about traversing arrays using for loops. In this program,
 * user's will be able to organize the fruit based on the prompt and array (randomized order) provided
 * 
 *  Major Skills:
 * - action listeners
 * - arrays
 * - if statements
 * 
 * Added Features:
 * - randomized array
 * - drag and drop feature
 * 
 * Areas of Concern:
 * - the display of icons does not display in a randomize order each time
 * 
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources:
 * - https://www.digitalocean.com/community/tutorials/shuffle-array-java (randomize array)
 * - https://zetcode.com/javaswing/draganddrop/ (icon drag and drop)
 */
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModuleThree extends JFrame implements ActionListener {
	
	//background image
	public JLabel background = new JLabel(new ImageIcon("images/moduleThreeBackground.png"));
	
	//image and button for back button
	public ImageIcon back = new ImageIcon("images/backBtn.png");
	public JButton backBtn = new JButton(back);
	
	//button to sumbit their answer
	public ImageIcon enter = new ImageIcon("images/enterBtn2.png");;
	public JButton enterBtn = new JButton(enter);
	
	//create butttons for each empty button areaa
	public JButton empty1;
	public JButton empty2;
	public JButton empty3;
	public JButton empty4;
	public JButton empty5;
	
	//array for the 5 diffeent food icons
	public Icon[] foodIcons = new Icon[5];
	
	//array of the different food options
	public String[] foodOptions = {"donut","bread","sandwich","chilli","burger"};
	
	//text area to displpay the array of food options
	public JTextArea foodArrayLabel;
 
    public ModuleThree() {

    	//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Module 3"); 
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

		//add the back button
		enterBtn.setBounds(813, 455, 98, 59);
		enterBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		enterBtn.addActionListener(this);
		background.add(enterBtn);
		
		//https://www.digitalocean.com/community/tutorials/shuffle-array-java
		//randomize the food options
		List<String> optionsList = Arrays.asList(foodOptions);

		Collections.shuffle(optionsList);

		optionsList.toArray(foodOptions);
		
		//add the label of the food array
		foodArrayLabel = new JTextArea("String[] food = {\""+foodOptions[0]+"\"\n,\""+foodOptions[1]+"\",\""+foodOptions[2]
				+"\n\",\""+foodOptions[3]+"\",\""+foodOptions[4]+"\"}");
		foodArrayLabel.setOpaque(false);
		foodArrayLabel.setFont(new Font("Courier", Font.PLAIN, 18)); //set font and size
		foodArrayLabel.setBounds(115, 403, 300, 90);
		foodArrayLabel.setForeground(Color.white);
		background.add(foodArrayLabel);
		
		//add icons to the icon array
		foodIcons[0] = new ImageIcon("images/"+foodOptions[0]+".png");
		foodIcons[1] = new ImageIcon("images/"+foodOptions[1]+".png");
		foodIcons[2] = new ImageIcon("images/"+foodOptions[2]+".png");
        foodIcons[3] = new ImageIcon("images/"+foodOptions[3]+".png");
        foodIcons[4] = new ImageIcon("images/"+foodOptions[4]+".png");
        
        Icon empty = new ImageIcon("images/empty.png");
        
        //create a listener to listen for dragging action
        DragMouseAdapter listener = new DragMouseAdapter();

        //image of food that can move
        JLabel food1 = new JLabel(foodIcons[2], JLabel.CENTER);
        food1.setBounds(610, 227, 87, 87);
        food1.addMouseListener(listener);
        background.add(food1);
        
        //image of food that can move
        JLabel food2 = new JLabel(foodIcons[3], JLabel.CENTER);
        food2.setBounds(715, 227, 87, 87);
        food2.addMouseListener(listener);
        background.add(food2);
        
        //image of food that can move
        JLabel food3 = new JLabel(foodIcons[0], JLabel.CENTER);
        food3.setBounds(819, 227, 87, 87);
        food3.addMouseListener(listener);
        background.add(food3);
        
        //image of food that can move
        JLabel food4 = new JLabel(foodIcons[4], JLabel.CENTER);
        food4.setBounds(923, 227, 87, 87);
        food4.addMouseListener(listener);
        background.add(food4);
        
        //image of food that can move
        JLabel food5 = new JLabel(foodIcons[1], JLabel.CENTER);
        food5.setBounds(1027, 227, 87, 87);
        food5.addMouseListener(listener);
        background.add(food5);

        //Add empty slot 1
        empty1 = new JButton(empty);
        empty1.setBounds(610, 344, 87, 87);
        background.add(empty1);
        
        //Add empty slot 2
        empty2 = new JButton(empty);
        empty2.setBounds(715, 344, 87, 87);
        background.add(empty2);
        
        //Add empty slot 3
        empty3 = new JButton(empty);
        empty3.setBounds(819, 344, 87, 87);
        background.add(empty3);
        
        //Add empty slot 4
        empty4 = new JButton(empty);
        empty4.setBounds(923, 344, 87, 87);
        background.add(empty4);
        
        //Add empty slot 5
        empty5 = new JButton(empty);
        empty5.setBounds(1027, 344, 87, 87);
        background.add(empty5);
       
        //https://zetcode.com/javaswing/draganddrop/ (icon drag and drop)
        //remove highlighted border from button
        empty1.setFocusable(false);
        empty2.setFocusable(false);
        empty3.setFocusable(false);
        empty4.setFocusable(false);
        empty5.setFocusable(false);
        
        //add a transfer handler to each food icon so they can be moved
        food1.setTransferHandler(new TransferHandler("icon"));
        food2.setTransferHandler(new TransferHandler("icon"));
        food3.setTransferHandler(new TransferHandler("icon"));
        food4.setTransferHandler(new TransferHandler("icon"));
        food5.setTransferHandler(new TransferHandler("icon"));
       
        //add a transfer handler to each empty sqaure so icons can be placed in it
        empty1.setTransferHandler(new TransferHandler("icon"));
        empty2.setTransferHandler(new TransferHandler("icon"));
        empty3.setTransferHandler(new TransferHandler("icon"));
        empty4.setTransferHandler(new TransferHandler("icon"));
        empty5.setTransferHandler(new TransferHandler("icon"));
       
        //set the window to visible
        setVisible(true);
        
    }
    //https://zetcode.com/javaswing/draganddrop/ (icon drag and drop)
    //if the mouse is dragged
    private class DragMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

        	//when a component is pressed assign it to a JComponent called c
        	JComponent c = (JComponent) e.getSource();
        	//create a transfer handler that called handler
            TransferHandler handler = c.getTransferHandler();
            //the handler will take in the component that was clicked and the mouse event
            handler.exportAsDrag(c, e, TransferHandler.COPY);
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
		//if the enter button is clicked
		if (e.getSource() == enterBtn) {
			//if the order of the icons in the buttons matches the organized array
			if (empty1.getIcon() == foodIcons[4] && empty2.getIcon() == foodIcons[3] && empty3.getIcon() == foodIcons[2]
					&& empty4.getIcon() == foodIcons[1] && empty5.getIcon() == foodIcons[0]) {
				//play music
				ModuleFour.playMusic("audio/correct.wav");
				//update progress
				ModuleOne.addProgress(2);
				//create a new instance of the ModuleThree class
				new ModuleThree();
				//set the current window to not visible
				setVisible(false);
			}
			//if the order is incorrect
			else {
				//play music
				ModuleFour.playMusic("audio/wrong.wav");
			}
		}
	}
}