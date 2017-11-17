package pers.ryan.quickquiz.server;

import java.io.IOException;

//Reference Source:
//Creating a simple Chat Client/Server Solution 
//http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.ryan.quickquiz.domain.Question;
import pers.ryan.quickquiz.domain.QuestionWithAns;
import pers.ryan.quickquiz.service.QuestionService;

/***
 * 
 * @author Ryan Tsang
 *
 */

public class QuestionServer {

	private List<QuestionServerThread> clients = new ArrayList<QuestionServerThread>();
	
	/***
	 * Declare fields and create setters for spring to inject
	 */
	private QuestionService questionService;
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	private ServerSocket server = null;

	public QuestionServer(int port) {
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
		} catch (Exception ioe) {
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
		}
	}

	/**
	 * start waiting for clients
	 */
	public void start() {
		while (true) {
			try {
				System.out.println("Waiting for a client ...");
				//wait for client to connect
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
			}
		}
	}

	/**
	 * handle massage send from client
	 * @param currentQuestionServerThread 
	 * 		Thread contain client
	 * @param input 
	 * 		massage
	 */
	public synchronized void handle(QuestionServerThread currentQuestionServerThread, String input) {
		//if massage equals to '.bye', close the thread
		if (input.equals(".bye")) {
			try {
				currentQuestionServerThread.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			clients.remove(currentQuestionServerThread);
		//if massage not equals to '.bye' then
		//	1. Change massage to json object
		//	2. ReceiveCount++
		//	3. if answer not correct, IncorrectStudentsCount ++
		} else {
			// Analyze input into json, store it into dtree.
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> hMap = new HashMap<String, String>();
			try {
				hMap = mapper.readValue(input, HashMap.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			QuestionWithAns questionWithAns = questionService.getDListOfQuestions().getLast();
			questionWithAns.setReceiveCount(questionWithAns.getReceiveCount() + 1);
			if (!questionWithAns.getCorrectAns().equalsIgnoreCase(hMap.get("answer"))) {
				questionWithAns.setIncorrectStudentsCount(questionWithAns.getIncorrectStudentsCount() + 1);
			}
		}
	}

	public synchronized void remove(QuestionServerThread questionServerThread) {
		clients.remove(questionServerThread);
	}

	/**
	 * 1. create a server thread using the socket provided
	 * 2. add the thread to a clents list
	 * 3. start the thread to listen to the client 
	 * @param socket
	 */
	private void addThread(Socket socket) {
		System.out.println("Client accepted: " + socket);
		QuestionServerThread currentQuestionServerThread = new QuestionServerThread(this, socket);
		clients.add(currentQuestionServerThread);
		try {
			currentQuestionServerThread.open();
			currentQuestionServerThread.start();
		} catch (IOException ioe) {
			System.out.println("Error opening thread: " + ioe);
		}
	}

	/**
	 * Convert java object to json and send to client
	 * @param question
	 * @return received client number
	 */
	public int sendQuestion(Question question) {
		int res = 0;
		// convert questions to Json Object
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("ID", question.getId());
		hMap.put("topic", question.getTopic());
		hMap.put("question", question.getQuestion());
		hMap.put("ansA", question.getAnsA());
		hMap.put("ansB", question.getAnsB());
		hMap.put("ansC", question.getAnsC());
		hMap.put("ansD", question.getAnsD());
		hMap.put("correctAns", question.getCorrectAns());
		
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(hMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		for (QuestionServerThread client : clients) {
			client.send(jsonString.toString());
			res++;
		}
		return res;
	}

	/**
	 * 
	 * @return connected client number
	 */
	public int getActivedClientCount() {
		return clients.size();
	}
}
