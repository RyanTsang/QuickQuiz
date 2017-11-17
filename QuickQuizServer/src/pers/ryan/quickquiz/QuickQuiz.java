package pers.ryan.quickquiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.ryan.quickquiz.frame.ServerFrame;
import pers.ryan.quickquiz.server.QuestionServer;


/***
 * This application is an assessment of Diploma Java subject.
 * Main feature of this program is that server can send question to client and client can response the question.
 * 
 * Version: 1.0.0 Release
 * @author Ryan Tsang
 *
 */

public class QuickQuiz {

	/***
	 * Entrance of the application
	 * Start the application by:
	 * 		1. Load spring configuration
	 * 		2. Get server UI from spring
	 * 		3. Get server from spring and run it
	 * 
	 * @param args parameters of main method
	 */
	public static void main(String[] args) {
		//1. Load spring configuration
		String xmlPath = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//2. Get server UI from spring
		ServerFrame serverFrame = (ServerFrame)applicationContext.getBean("serverFrame");
		serverFrame.setVisible(true);
		
		//3. Get server from spring and run it
		QuestionServer questionServer = (QuestionServer)applicationContext.getBean("questionServer");
		questionServer.start();
	}

}
