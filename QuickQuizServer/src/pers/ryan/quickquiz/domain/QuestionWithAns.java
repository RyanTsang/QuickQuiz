package pers.ryan.quickquiz.domain;

/**
 * This class contain question and student answer status.
 * @author Ryan Tsang
 *
 */
public class QuestionWithAns extends Question {
	
	private int incorrectStudentsCount = 0;
	
	private int sentCount = 0;
	
	private int receiveCount = 0;
	
	public int getSentCount() {
		return sentCount;
	}
	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}
	public int getReceiveCount() {
		return receiveCount;
	}
	public void setReceiveCount(int receiveCount) {
		this.receiveCount = receiveCount;
	}
	public int getIncorrectStudentsCount() {
		return incorrectStudentsCount;
	}
	public void setIncorrectStudentsCount(int incorrectStudentsCount) {
		this.incorrectStudentsCount = incorrectStudentsCount;
	}

	public QuestionWithAns(Question question) {
		super.setId(question.getId());
		super.setQnNum(question.getQnNum());
		super.setTopic(question.getTopic());
		super.setQuestion(question.getQuestion());
		super.setAnsA(question.getAnsA());
		super.setAnsB(question.getAnsB());
		super.setAnsC(question.getAnsC());
		super.setAnsD(question.getAnsD());
		super.setCorrectAns(question.getCorrectAns());
	}
	
	public boolean isAllResponsed() {
		return sentCount == receiveCount;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " + incorrectStudentsCount + " students";
	}

}
