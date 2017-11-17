package pers.ryan.quickquizclient.frame;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pers.ryan.quickquizclient.client.QuestionClientThread;

/**
 * Included UI and service in this class
 * @author Ryan Tsang
 *
 */
public class ClientFrame extends javax.swing.JFrame {

	private QuestionClientThread questionClientThread;
	private DataOutputStream streamOut = null;
	private Socket socket = null;
	private static String host;
	private static int port;

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	ObjectMapper mapper = new ObjectMapper();
	HashMap<String, String> questionHMap = new HashMap<String, String>();

	/**
	 * Creates new form ClientFrame
	 */
	public ClientFrame() {
		initComponents();
		//////// Customize components ///////////
		UIManager.put("TextField.inactiveBackground", Color.WHITE);
		nameTextfield.setEditable(true);
		topicTextField.setEditable(false);
		questionTextArea.setEditable(false);
		questionTextArea.setBorder(new JTextField().getBorder());
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		aTextField.setEditable(false);
		bTextField.setEditable(false);
		dTextField.setEditable(false);
		cTextField.setEditable(false);

		// load configurations and connect to server
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream("config/application.properties");
			Properties props = new Properties();
			props.load(in);
			host = props.getProperty("host");
			port = Integer.parseInt(props.getProperty("port"));
			socket = new Socket(host, port);
			questionClientThread = new QuestionClientThread(socket);
			questionClientThread.setClient(this);
			questionClientThread.start();
			open();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
			close();
		}

		//////// Customize components ///////////
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, "Please wait for receiving questions...", "Info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		nameTextfield = new javax.swing.JTextField();
		jPanel1 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		topicTextField = new javax.swing.JTextField();
		aTextField = new javax.swing.JTextField();
		bTextField = new javax.swing.JTextField();
		dTextField = new javax.swing.JTextField();
		cTextField = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		questionTextArea = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		aRadioButton = new javax.swing.JRadioButton();
		aRadioButton.setEnabled(false);
		bRadioButton = new javax.swing.JRadioButton();
		bRadioButton.setEnabled(false);
		cRadioButton = new javax.swing.JRadioButton();
		cRadioButton.setEnabled(false);
		dRadioButton = new javax.swing.JRadioButton();
		dRadioButton.setEnabled(false);
		submitButton = new java.awt.Button();
		submitButton.setEnabled(false);
		exitButton = new java.awt.Button();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Quick Quiz");
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("Your Name:");

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Question"));
		jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel3.setText("Topic:");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel4.setText("Question:");

		jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel5.setText("A:");

		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel6.setText("B:");

		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel7.setText("C:");

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel8.setText("D:");

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}
		});

		topicTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		aTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		bTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		dTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		cTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

		questionTextArea.setColumns(20);
		questionTextArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		questionTextArea.setRows(5);
		questionTextArea.setPreferredSize(null);
		jScrollPane1.setViewportView(questionTextArea);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3).addGap(26, 26, 26)
								.addComponent(topicTextField))
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel6).addGap(49, 49, 49)
								.addComponent(bTextField))
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel7).addGap(49, 49, 49)
								.addComponent(cTextField))
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel8).addGap(48, 48, 48)
								.addComponent(dTextField))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel5).addComponent(jLabel4))
								.addGap(7, 7, 7)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(aTextField).addComponent(jScrollPane1))))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(topicTextField,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel4).addGap(0, 0,
										Short.MAX_VALUE))
								.addComponent(jScrollPane1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel6).addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel7).addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel8).addComponent(dTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)));

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel2.setText("Answer:");

		aRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		aRadioButton.setText("A");

		bRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		bRadioButton.setText("B");

		cRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		cRadioButton.setText("C");

		dRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		dRadioButton.setText("D");

		submitButton.setLabel("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});

		exitButton.setLabel("Exit");
		exitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(nameTextfield))
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
												.addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(28, 28, 28).addComponent(exitButton,
														javax.swing.GroupLayout.PREFERRED_SIZE, 168,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup().addComponent(jLabel2)
												.addGap(56, 56, 56).addComponent(aRadioButton).addGap(18, 18, 18)
												.addComponent(bRadioButton).addGap(18, 18, 18)
												.addComponent(cRadioButton).addGap(18, 18, 18)
												.addComponent(dRadioButton)))
										.addGap(0, 6, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
						.addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
						.addComponent(aRadioButton).addComponent(bRadioButton).addComponent(cRadioButton)
						.addComponent(dRadioButton))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		pack();
	}// </editor-fold>

	private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		close();
	}

	private void formWindowClosed(java.awt.event.WindowEvent evt) {
		close();
	}

	/**
	 * if not name then ask user enter name
	 * else if not answer has been selected then ask user select answer
	 * else send out the answer by json 
	 * @param evt
	 */
	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (nameTextfield.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter your name.", "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			StringBuilder answer = new StringBuilder();
			if (aRadioButton.isSelected()) {
				answer.append("a");
			}
			if (bRadioButton.isSelected()) {
				answer.append("b");
			}
			if (cRadioButton.isSelected()) {
				answer.append("c");
			}
			if (dRadioButton.isSelected()) {
				answer.append("d");
			}
			String answerString = answer.toString();
			if (!answerString.equals("")) {
				HashMap<String, String> hMap = new HashMap<String, String>();
				String jsonString = "";
				hMap.put("studentName", nameTextfield.getText());
				nameTextfield.setEditable(false);
				hMap.put("ID", questionHMap.get("ID"));
				hMap.put("answer", answerString);
				
				try {
					jsonString = mapper.writeValueAsString(hMap);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				if (send(jsonString)) {
					resetControls();
				}
				String correctAns = questionHMap.get("correctAns");
				if (answerString.equalsIgnoreCase(correctAns)) {
					JOptionPane.showMessageDialog(null, "Your answer is correct.", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Your answer is wrong. Correct answer is " + correctAns + ".",
							"Info", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				// Message user ask him to select answer
				JOptionPane.showMessageDialog(null, "Please select at less an answer.", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/***
	 * reset text fields and radio buttons 
	 */
	private void resetControls() {
		topicTextField.setText("");
		aTextField.setText("");
		bTextField.setText("");
		dTextField.setText("");
		cTextField.setText("");
		questionTextArea.setText("");
		aRadioButton.setEnabled(false);
		bRadioButton.setEnabled(false);
		cRadioButton.setEnabled(false);
		dRadioButton.setEnabled(false);
		submitButton.setEnabled(false);
	}

	/***
	 * send message to server
	 * @param msg massage need to be sent
	 * @return
	 */
	private boolean send(String msg) {
		boolean res = false;
		try {
			streamOut.writeUTF(msg);
			streamOut.flush();
			res = true;
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Can't send data, please check if server still running.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			close();
		}
		return res;
	}

	/***
	 * handle massage receive from server
	 * @param input
	 */
	public void handle(String input) {
		if (input.equals(".bye")) {
			System.out.println("Good bye. Press EXIT button to exit ...");
			close();
		} else {
			// convert string to json object
			try {
				questionHMap = mapper.readValue(input, HashMap.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			showQuestion();
		}
	}

	public void open() {
		try {
			streamOut = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("Error opening output stream: " + ioe);
		}
	}

	public void close() {
		try {
			streamOut.writeUTF(".bye");
			streamOut.flush();
			System.out.println(".bye sent");
		} catch (Exception e) {

		}
		try {
			if (streamOut != null) {
				streamOut.close();
			}
			if (socket != null) {
				socket.close();
			}
			if (questionClientThread != null) {
				questionClientThread.close();
			}
			System.exit(0);
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
	}
	
	/**
	 * show question received from server
	 */
	private void showQuestion() {
		topicTextField.setText(questionHMap.get("topic"));
		questionTextArea.setText(questionHMap.get("question"));
		aTextField.setText(questionHMap.get("ansA"));
		bTextField.setText(questionHMap.get("ansB"));
		cTextField.setText(questionHMap.get("ansC"));
		dTextField.setText(questionHMap.get("ansD"));
		submitButton.setEnabled(true);
		aRadioButton.setEnabled(true);
		bRadioButton.setEnabled(true);
		cRadioButton.setEnabled(true);
		dRadioButton.setEnabled(true);
		aRadioButton.setSelected(false);
		bRadioButton.setSelected(false);
		cRadioButton.setSelected(false);
		dRadioButton.setSelected(false);
	}

	/**
	 * 
	 * Entrance of the program
	 * @param args
	 *     the command line arguments
	 */
	public static void main(String args[]) {
		ClientFrame clientFrame = new ClientFrame();
	}

	//Variables declaration - do not modify
	private javax.swing.JRadioButton aRadioButton;
	private javax.swing.JTextField aTextField;
	private javax.swing.JRadioButton bRadioButton;
	private javax.swing.JTextField bTextField;
	private javax.swing.JRadioButton cRadioButton;
	private javax.swing.JTextField cTextField;
	private javax.swing.JRadioButton dRadioButton;
	private javax.swing.JTextField dTextField;
	private java.awt.Button exitButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField nameTextfield;
	private javax.swing.JTextArea questionTextArea;
	private java.awt.Button submitButton;
	private javax.swing.JTextField topicTextField;
	// </editor-fold>
}
