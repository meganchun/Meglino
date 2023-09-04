/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * The quiz frame will display the quiz intro screen and the 10 questions in the quiz 
 * Link to answers: https://docs.google.com/spreadsheets/d/1H6Ksqnd-Sm5oLdFqBA9DxSTyDhgEtO1enSn6hBzozVE/edit?usp=sharing
 * 
 *  Major Skills:
 * - for loops
 * - arrays
 * - if statements
 * 
 * Added Features: 
 * - new frame for each question
 * 
 * Areas of Concern: none
 *
 * Contribution: All code was coded by Megan unless otherwise stated
 * 
 * External Sources:
 * - //https://www.digitalocean.com/community/tutorials/shuffle-array-java (shuffle array)
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class QuizFrame extends JFrame implements ActionListener {
	
	//Question array to hold the different questions
	public Question[] questionArray = new Question[10];
	
	//initalize the score to user
	public static int score = 0;
	
	//ELEMENTS FOR QUIZ HOMEPAGE ------------------------------
	public JLabel background;

	//button and image to start the quiz
	public ImageIcon start = new ImageIcon("images/startBtn.png");
	public JButton startBtn = new JButton(start);
	
	//declare and initalize the starting question number
	public int questionNum = 1;
	
	//create a JFrame for the question
	public static JFrame questionFrame;
	
	//Array to determine which indexes are the correct answers
	public int[] correctAnswerIndexes;

	//ELEMENTS FOR INDIVIDUAL QUESTION FRAME ------------------------------
	//headers
	public JLabel questionNumLabel;
	public JLabel questionLabel;
	
	//answer and reponse components
	public JTextField textField; //fill in the blank
	public JToggleButton option1; //mulitple choice or true/false
	public JToggleButton option2; //mulitple choice or true/false
	public JToggleButton option3; //mulitple choice or true/false
	public JToggleButton option4; //mulitple choice or true/false
	
	//image and button for next buttonn
	public ImageIcon next = new ImageIcon("images/nextBtn.png");
	public JButton nextBtn;
	
	//int to hold the correct and incorrect answer index
	int correctAnswerIndex;
	int incorrectIndex;
	
	//String for the user's text answer
	String userAnswer;
	
	//array to hold which question they got correct
	public static boolean[] questionsResult = new boolean[10];
	
	public QuizFrame() {
		
		//fill the questions in the question array
		fillQuestionArray();
		//call the QuizHomepage method to display the intro
		QuizHomepage();
		
	}
	//QuizHomepage method will display the opening screen to the quiz
	public void QuizHomepage() {
		
		//set the background image
		background = new JLabel(new ImageIcon("images/quizBackground.png"));
		
		//set the window functions (size, title, resizable)
		setLocationRelativeTo(null); 
		setSize(1200,600); //size of window
		setTitle("Quiz"); 
		setLocationRelativeTo(null); //open window in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		//add the background to the window
		add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//start button
		startBtn.setBounds(488, 356, 224, 68);
		startBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		startBtn.addActionListener(this);
		background.add(startBtn);
		
		//set the window visible to true
		setVisible(true);
		
	}
	//fill the question array with data from an external file
	public void fillQuestionArray() {
		
		try {
			//use the scanner object to access the file
			Scanner inputFile = new Scanner(new File("data/quizQuestions.txt"));
			
			//set a delimiter which will stop scanning the data for one question
			inputFile.useDelimiter(",|\r\n");
			
			//for loop that will iterate through all the indexes in the question array
			for(int index = 0; index < questionArray.length; index++) {
				
				//create variables of each data needed for the question
				int type = inputFile.nextInt();
				String question = inputFile.next();
				String answer = inputFile.next();
				String option1 = inputFile.next();
				String option2 = inputFile.next();
				String option3 = inputFile.next();
			
				//add the data to the corresponding question
				questionArray[index] = new Question(type, question, answer, option1, option2, option3);

			}
			//close the scanner
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			//if the file is not found output an error message
			System.out.println("File error");
		}
		
	}
	
	//Question method will display a window for each question
	public void Question(int questionNumber) {
		
		//create a new JFrame for the question
		questionFrame = new JFrame();
		
		//set the background
		background = new JLabel(new ImageIcon("images/questionBackground.png"));
		
		//set the window functions (size, title, resizable)
		questionFrame.setLocationRelativeTo(null); 
		questionFrame.setSize(1200,600); //size of window
		questionFrame.setTitle("Question "+questionNum); 
		questionFrame.setLocationRelativeTo(null); //open window in the center of the screen
		questionFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		questionFrame.setResizable(false);
				
		//add the background to the window
		questionFrame.add(background);
		background.setBounds(0, 0, 1200, 600);
		
		//label for the question number the user is currently on
		questionNumLabel = new JLabel("Question "+questionNum+" of 10",SwingConstants.CENTER);
		questionNumLabel.setFont(new Font("Helvetica", Font.PLAIN, 20)); //set font and size
		questionNumLabel.setForeground(new Color(177,177,177));
		questionNumLabel.setBounds(0, 151, 1200, 30);
		background.add(questionNumLabel);
		
		//label for the question
		questionLabel = new JLabel(questionArray[questionNum-1].getQuestion(), SwingConstants.CENTER);
		questionLabel.setFont(new Font("Helvetica", Font.PLAIN, 20)); //set font and size
		questionLabel.setBounds(0, 232, 1200, 30);
		background.add(questionLabel);
		
		//next button
		nextBtn = new JButton(next);
		nextBtn.setBounds(1031, 300, 44, 44);
		nextBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //remove border from button
		nextBtn.addActionListener(this);
		background.add(nextBtn);
		
		
		//if the question is a multiple choice
		if (questionArray[questionNum-1].getType() == 1 || questionArray[questionNum-1].getType() == 4) {
			
			//https://www.digitalocean.com/community/tutorials/shuffle-array-java (shuffle array)
			//randomize the incorrect options and answers
			String[] questionOptions = new String[4];
			questionOptions[0] = questionArray[questionNum-1].getAnswer();
			questionOptions[1] = questionArray[questionNum-1].getOption1();
			questionOptions[2] = questionArray[questionNum-1].getOption2();
			questionOptions[3] = questionArray[questionNum-1].getOption3();
			
			List<String> optionsList = Arrays.asList(questionOptions);

			Collections.shuffle(optionsList);

			optionsList.toArray(questionOptions);
			
			//if the question is select one correct answer
			if (questionArray[questionNum-1].getType() == 1) {
				
				//iterate through each answer option
				for (int i = 0; i < 4; i++) {
			
					//check if the option is equal to the question's answer
					if(questionOptions[i].equalsIgnoreCase(questionArray[questionNum-1].getAnswer())) {
						//set the correct index of the answer to the iteration number
						correctAnswerIndex = i;
						//break the loop
						break;
					}
				}
			}
			//if the question is select multiple correct answers
			else {
				
				//create an array to store the correct indexes
				incorrectIndex = 0;
				
				//checks each question in a randomized order if it one of the correct answers
				for (int i = 0; i < 4; i++) {
					
					//check if the option is the last option (incorrect one)
					if(questionOptions[i].equalsIgnoreCase(questionArray[questionNum-1].getOption3())) {
						incorrectIndex = i;
					}
				}
			}
		
			
			//create a panel for each option
			//option 1
			JPanel optionPanel1 = new JPanel();
			optionPanel1.setBounds(420, 274, 360, 40);
			background.add(optionPanel1);
			
			//option 2
			JPanel optionPanel2 = new JPanel();
			optionPanel2.setBounds(420, 337, 360, 40);
			background.add(optionPanel2);
			
			//option 3
			JPanel optionPanel3 = new JPanel();
			optionPanel3.setBounds(420, 400, 360, 40);
			background.add(optionPanel3);
			
			//option 4
			JPanel optionPanel4 = new JPanel();
			optionPanel4.setBounds(420, 463, 360, 40);
			background.add(optionPanel4);
			
			//create option #1
			//set the text of the button
			option1 = new JToggleButton(questionOptions[0]);
			option1.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option1.setFocusable(false); //removes highlighted box
			option1.setPreferredSize(new Dimension(360,45));
			//set the colour of the toggle when clicked to green
			option1.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the option to the panel
			optionPanel1.add(option1);
			
			//create option #2
			//set the text of the button
			option2 = new JToggleButton(questionOptions[1]);
			option2.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option2.setFocusable(false); //removes highlighted box
			option2.setPreferredSize(new Dimension(359,45));
			//set the colour of the toggle when clicked to green
			option2.setUI(new MetalToggleButtonUI() {
				//set the colour of the toggle when clicked to green
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the option to the panel
			optionPanel2.add(option2);
			
			//create option #3
			//set the text of the button
			option3 = new JToggleButton(questionOptions[2]);
			option3.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option3.setFocusable(false); //removes highlighted box
			option3.setPreferredSize(new Dimension(359,45));
			//set the colour of the toggle when clicked to green
			option3.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the option to the panel
			optionPanel3.add(option3);
			
			
			//create option #4
			//set the text of the button
			option4 = new JToggleButton(questionOptions[3]);
			option4.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option4.setFocusable(false); //removes highlighted box
			option4.setPreferredSize(new Dimension(359,45));
			//set the colour of the toggle when clicked to green
			option4.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the option to the panel
			optionPanel4.add(option4);
			
		}
		
		
		//if the question is a true or false question
		if (questionArray[questionNum-1].getType() == 3) {
			
			//create a panel for each option
			//true option
			JPanel truePanel = new JPanel();
			truePanel.setBounds(420, 274, 360, 40);
			background.add(truePanel);
			
			//false option
			JPanel falsePanel = new JPanel();
			falsePanel.setBounds(420, 337, 360, 40);
			background.add(falsePanel);
			
				
			//create true option
			option1 = new JToggleButton("True");
			option1.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option1.setFocusable(false); //removes highlighted box
			option1.setPreferredSize(new Dimension(360,45));
			//set the colour of the toggle when clicked to green
			option1.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the button to the panel
			truePanel.add(option1);
			
			//create false option
			option2 = new JToggleButton("False");
			option2.setFont(new Font("Helvetica", Font.PLAIN, 15));
			option2.setFocusable(false); //removes highlighted box
			option2.setPreferredSize(new Dimension(359,45));
			//set the colour of the toggle when clicked to green
			option2.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getSelectColor() {
			        return new Color(114,196,58);
			    }
			});
			//add the button to the panel
			falsePanel.add(option2);
		}
		
		
		
		//if it is a fill in the blank question
		if (questionArray[questionNum-1].getType() == 2){
			textField = new JTextField("Enter answer here");
			//text area to enter their names
			textField.setBounds(254, 300, 691, 40);
			textField.addActionListener(this);
			textField.setEditable(true); //allow the user to enter text
			textField.setForeground(new Color(114,196,58)); //change the colour
			//set the font of the text
			textField.setFont(new Font("Courier", Font.PLAIN, 20));
			background.add(textField);
		}
	
		//set the question frame to visible
		questionFrame.setVisible(true);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		//if the start button is clicked
		if (e.getSource() == startBtn) {
			//set the homepage window to not visible
			HomepageFrame.quizFrame.setVisible(false);
			//call the Question method to create the next question
			Question(questionNum);
		}
		
		//if the next button is clicked
		if (e.getSource() == nextBtn) {
		
			//close the previous question's frame
			questionFrame.setVisible(false);
			
			//if it is a multiple choice question (only one correct option)
			if (questionArray[questionNum-1].getType() == 1) {
				//if the first option is selected and the correct answer is the first option
				if (option1.isSelected() == true && correctAnswerIndex == 0) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the second option is selected and the correct answer is the second option
				else if (option2.isSelected() == true && correctAnswerIndex == 1) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the third option is selected and the correct answer is the third option
				else if (option3.isSelected() == true && correctAnswerIndex == 2) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the fourth option is selected and the correct answer is the fourth option
				else if (option4.isSelected() == true && correctAnswerIndex == 3) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
			}
			//if the question was a fill in the blank question
			else if (questionArray[questionNum-1].getType() == 2) {
				
				//get the text in the text field
				userAnswer = textField.getText();
				
				//if the user's answer they type is the same as the questions answer
				if (userAnswer.equalsIgnoreCase(questionArray[questionNum-1].getAnswer())) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}

			}
			//if the question is a true or false question
			else if (questionArray[questionNum-1].getType() == 3) {
				//if the user selected true and the answer is true
				if (option1.isSelected() == true && questionArray[questionNum-1].getAnswer().equalsIgnoreCase("t")) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the user selected false and the answer is false
				else if (option2.isSelected() == true && questionArray[questionNum-1].getAnswer().equalsIgnoreCase("f")) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
			}
			
			//else if it is a fill in the choice question
			else if (questionArray[questionNum-1].getType() == 4) {
			
				//if the first option is the only incorrect option
				if (option1.isSelected() == false && incorrectIndex == 0 && option2.isSelected() == true 
						&& option3.isSelected() == true && option4.isSelected() == true) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the second option is the only incorrect option
				else if (option2.isSelected() == false && incorrectIndex == 1 && option1.isSelected() == true 
						&& option3.isSelected() == true && option4.isSelected() == true) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the second option is the only incorrect option
				else if (option3.isSelected() == false && incorrectIndex == 2 && option2.isSelected() == true 
						&& option1.isSelected() == true && option4.isSelected() == true) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				//if the second option is the only incorrect option
				else if (option4.isSelected() == false && incorrectIndex == 3 && option1.isSelected() == true 
						&& option3.isSelected() == true && option2.isSelected() == true) {
					score++; //increment score
					questionsResult[questionNum-1] = true; //set the question to correct
				}
				

			}
			//if the user is not on the last question
			if (questionNum < 10) {
				//increment the question number
				questionNum++;
				//open a new question 
				Question(questionNum);
					
			}
			//if the user is on the last question
			else {
				//create a new instance of the ResultsFrame
				new ResultsFrame(score);
				setVisible(false);
			}
			
		}
		
		
	}
}
