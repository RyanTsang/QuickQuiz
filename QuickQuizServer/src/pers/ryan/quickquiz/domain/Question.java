package pers.ryan.quickquiz.domain;

import java.io.Serializable;

/***
 * Question
 * @author Ryan Tsang
 *
 */
public class Question implements Serializable{

	private String id;
	private Integer qnNum;
	private String topic;
	private String question;
	private String ansA;
	private String ansB;
	private String ansC;
	private String ansD;
	private String correctAns;
	
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return topic + ", " + qnNum;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getQnNum() {
		return qnNum;
	}
	public void setQnNum(Integer qnNum) {
		this.qnNum = qnNum;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnsA() {
		return ansA;
	}
	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}
	public String getAnsB() {
		return ansB;
	}
	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}
	public String getAnsC() {
		return ansC;
	}
	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}
	public String getAnsD() {
		return ansD;
	}
	public void setAnsD(String ansD) {
		this.ansD = ansD;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	/***
	 * compare this question to an object. return true if their ID are the same. 
	 */
	public boolean equals(Object obj) {
		"a".equals("a");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
