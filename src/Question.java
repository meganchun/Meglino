/*
 * Megan Chun
 * 
 * Wednesday, June 14, 2023
 * 
 * ICS3U1-05 | Mrs.Biswas
 * 
 * Computer Assisted Instruction (CAI) Program 
 * 
 * The Question class will allow the program to create Question object with the following attributes:
 * - type of question
 * - question
 * - answer 
 * - other options x 3
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
public class Question {
	
	int type; // 1 = multiple choice (1 right answer), 2 = fill in the blanks, 3 = true or false, 4 = multiple choice (multiple right answer)
	String question;
	String answer;
	String option1;
	String option2;
	String option3;
	
	//constructor for multiple choice questions
	public Question(int type, String question, String answer, String option1,
			String option2, String option3) {
		
		super();
		this.type = type;
		this.question = question;
		this.answer = answer;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
			
	}
	//GET AND SET METHODS
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	
	
}
