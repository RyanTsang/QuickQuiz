package pers.ryan.quickquiz.frame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import pers.ryan.quickquiz.domain.Question;
import pers.ryan.quickquiz.service.QuestionService;

/***
 * UI of server
 * @author Ryan Tsang
 *
 */
public class ServerFrame extends JFrame {

	/***
	 * Declare field and setter of questionService for spring to inject
	 */
	private QuestionService questionService;

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	/**
	 * Declare and initial component
	 */
	static String applicationName = "Quick Quiz";

	JLabel statusLabel = new JLabel();

	JPanel questionsPanel = new JPanel();
	// questionsLabel
	JLabel questionsLabel = new JLabel("Quick Quiz Questions");
	DefaultTableModel model = new DefaultTableModel();
	JTable questionsTable = new JTable();
	JLabel questionsSortByLabel = new JLabel("Sort By:");
	JButton questionsQnNubButton = new JButton("Qn#");
	JButton questionsTopicButton = new JButton("Topic");
	JButton questionsAnsButton = new JButton("Anser");

	// questionDetailPanel
	JPanel questionDetailPanel = new JPanel();
	JLabel questionDetailLabel = new JLabel("Question Detail");
	JLabel questionDetailTopicLabel = new JLabel("Topic:");
	JTextField questionDetailTopicText = new JTextField();
	JLabel questionDetailQnNumLabel = new JLabel("Qn:");
	JLabel questionDetailQnNumIntLabel = new JLabel("");
	JTextArea questionDetailQnTextArea = new JTextArea();
	JLabel questionDetailAnsALabel = new JLabel("A:");
	JTextField questionDetailAnsAText = new JTextField();
	JLabel questionDetailAnsBLabel = new JLabel("B:");
	JTextField questionDetailAnsBText = new JTextField();
	JLabel questionDetailAnsCLabel = new JLabel("C:");
	JTextField questionDetailAnsCText = new JTextField();
	JLabel questionDetailAnsDLabel = new JLabel("D:");
	JTextField questionDetailAnsDText = new JTextField();

	JLabel ansLabel = new JLabel("Correct Ans: ");
	JTextField ansTextField = new JTextField();
	JButton sendButton = new JButton("Send");

	JLabel linkedListLabel = new JLabel("Linked List:");
	JTextArea linkedListTextArea = new JTextArea();

	JLabel binaryTreeLabel = new JLabel("Binary Tree:");
	JTextArea binaryTreeTextArea = new JTextArea();

	JLabel preOrderLabel = new JLabel("Pre-Order");
	JButton preOrderDisplayButton = new JButton("Display");
	JButton preOrderSaveButton = new JButton("Save");

	JLabel inOrderLabel = new JLabel("In-Order");
	JButton inOrderDisplayButton = new JButton("Display");
	JButton inOrderSaveButton = new JButton("Save");

	JLabel postOrderLabel = new JLabel("Post-Order");
	JButton postOrderDisplayButton = new JButton("Display");
	JButton postOrderSaveButton = new JButton("Save");

	JButton exitButton = new JButton("Exit");
	////////////// Not add to frame yet

	///////////////// Set components size /////////////////

	private static int frameWidth = 800;
	private static int frameHeight = 600;
	private static int gap = 10;

	private static int statusLabelWidth = frameWidth - 2 * gap;
	private static int statusLabelHeight = 40;
	private static Rectangle statusLabelHeightRectangle = new Rectangle(gap, gap, statusLabelWidth, statusLabelHeight);

	// questionsPanel
	private static int questionsPanelWidth = (int) ((frameWidth - 3 * gap) * 0.55);
	private static int questionsPanelHeight = (int) ((frameHeight - statusLabelHeight - gap * 6) / 2);
	private static Rectangle questionsPanelRectangle = new Rectangle(gap, gap * 2 + statusLabelHeight,
			questionsPanelWidth, questionsPanelHeight);

	private static int questionsLabelWidth = questionsPanelWidth;
	private static int questionsLabelHeight = 25;
	private static Rectangle questionsLabelRectangle = new Rectangle(0, 0, questionsLabelWidth, questionsLabelHeight);

	private static int questionsTableWidth = questionsPanelWidth - gap;
	private static int questionsTableHeight = questionsPanelHeight - 2 * questionsLabelHeight;
	private static Rectangle questionsTableRectangle = new Rectangle(gap / 2, questionsLabelHeight, questionsTableWidth,
			questionsTableHeight);

	private static int questionsSortByLabelWidth = questionsPanelWidth / 5;
	private static int questionsSortByLabelHeight = questionsLabelHeight;
	private static Rectangle questionsSortByLabelRectangle = new Rectangle(questionsSortByLabelWidth,
			questionsTableHeight + questionsLabelHeight, questionsSortByLabelWidth, questionsSortByLabelHeight);

	private static int questionsQnNubButtonWidth = questionsPanelWidth / 5;
	private static int questionsQnNubButtonHeight = questionsLabelHeight;
	private static Rectangle questionsQnNubButtonRectangle = new Rectangle(questionsSortByLabelWidth * 2,
			questionsTableHeight + questionsLabelHeight, questionsQnNubButtonWidth, questionsQnNubButtonHeight);

	private static int questionsTopicButtonWidth = questionsPanelWidth / 5;
	private static int questionsTopicButtonHeight = questionsLabelHeight;
	private static Rectangle questionsTopicButtonRectangle = new Rectangle(questionsSortByLabelWidth * 3,
			questionsTableHeight + questionsLabelHeight, questionsTopicButtonWidth, questionsTopicButtonHeight);

	private static int questionsAnsButtonWidth = questionsPanelWidth / 5;
	private static int questionsAnsButtonHeight = questionsLabelHeight;
	private static Rectangle questionsAnsButtonRectangle = new Rectangle(questionsSortByLabelWidth * 4,
			questionsTableHeight + questionsLabelHeight, questionsAnsButtonWidth, questionsAnsButtonHeight);

	// questionDetailPanel
	private static int questionDetailPanelWidth = frameWidth - 3 * gap - questionsPanelWidth;
	private static int questionDetailPanelHeight = questionsPanelHeight;
	private static Rectangle questionDetailPanelRectangle = new Rectangle((gap * 2 + questionsPanelWidth),
			gap * 2 + statusLabelHeight, questionDetailPanelWidth, questionDetailPanelHeight);

	private static int questionDetailLabelWidth = questionDetailPanelWidth;
	private static int questionDetailLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailLabelRectangle = new Rectangle(0, 0, questionDetailLabelWidth,
			questionDetailLabelHeight);

	private static int questionDetailTopicLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailTopicLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailTopicLabelRectangle = new Rectangle(0, questionsLabelHeight,
			questionDetailTopicLabelWidth, questionDetailTopicLabelHeight);

	private static int questionDetailTopicTextWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailTopicTextHeight = questionsLabelHeight;
	private static Rectangle questionDetailTopicTextRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight, questionDetailTopicTextWidth, questionDetailTopicTextHeight);

	private static int questionDetailQnNumLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailQnNumLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailQnNumLabelRectangle = new Rectangle(0, questionsLabelHeight * 2,
			questionDetailQnNumLabelWidth, questionDetailQnNumLabelHeight);

	private static int questionDetailQnTextAreaWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailQnTextAreaHeight = questionsLabelHeight * 3;
	private static Rectangle questionDetailQnTextAreaRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight * 2, questionDetailQnTextAreaWidth, questionDetailQnTextAreaHeight);

	private static int questionDetailQnNumIntLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailQnNumIntLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailQnNumIntLabelRectangle = new Rectangle(0, questionsLabelHeight * 3,
			questionDetailQnNumIntLabelWidth, questionDetailQnNumIntLabelHeight);

	private static int questionDetailAnsALabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailAnsALabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsALabelRectangle = new Rectangle(0, questionsLabelHeight * 5,
			questionDetailAnsALabelWidth, questionDetailAnsALabelHeight);

	private static int questionDetailAnsATextWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailAnsATextHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsATextRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight * 5, questionDetailAnsATextWidth, questionDetailAnsATextHeight);

	private static int questionDetailAnsBLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailAnsBLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsBLabelRectangle = new Rectangle(0, questionsLabelHeight * 6,
			questionDetailAnsBLabelWidth, questionDetailAnsBLabelHeight);

	private static int questionDetailAnsBTextWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailAnsBTextHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsBTextRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight * 6, questionDetailAnsBTextWidth, questionDetailAnsBTextHeight);

	private static int questionDetailAnsCLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailAnsCLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsCLabelRectangle = new Rectangle(0, questionsLabelHeight * 7,
			questionDetailAnsCLabelWidth, questionDetailAnsCLabelHeight);

	private static int questionDetailAnsCTextWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailAnsCTextHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsCTextRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight * 7, questionDetailAnsCTextWidth, questionDetailAnsCTextHeight);

	private static int questionDetailAnsDLabelWidth = (int) (questionDetailPanelWidth * 0.2);
	private static int questionDetailAnsDLabelHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsDLabelRectangle = new Rectangle(0, questionsLabelHeight * 8,
			questionDetailAnsDLabelWidth, questionDetailAnsDLabelHeight);

	private static int questionDetailAnsDTextWidth = (int) (questionDetailPanelWidth * 0.8);
	private static int questionDetailAnsDTextHeight = questionsLabelHeight;
	private static Rectangle questionDetailAnsDTextRectangle = new Rectangle(questionDetailTopicLabelWidth,
			questionsLabelHeight * 8, questionDetailAnsDTextWidth, questionDetailAnsDTextHeight);

	private static int sendButtonWidth = 190;
	private static int sendButtonHeight = 25;
	private static Rectangle sendButtonRectangle = new Rectangle(frameWidth - gap - sendButtonWidth,
			statusLabelHeight + (int) (2.5 * gap) + questionsPanelHeight, sendButtonWidth, sendButtonHeight);

	private static int ansTextWidth = 75;
	private static int ansTextHeight = sendButtonHeight;
	private static Rectangle ansTextRectangle = new Rectangle(
			frameWidth - gap - sendButtonWidth - (int) (0.5 * gap) - ansTextWidth,
			statusLabelHeight + (int) (2.5 * gap) + questionsPanelHeight, ansTextWidth, ansTextHeight);

	private static int ansLabelWidth = 75;
	private static int ansLabelHeight = sendButtonHeight;
	private static Rectangle ansLabelRectangle = new Rectangle((gap * 2 + questionsPanelWidth),
			statusLabelHeight + (int) (2.5 * gap) + questionsPanelHeight, ansLabelWidth, ansLabelHeight);

	private static int linkedListLabelWidth = frameWidth - 2 * gap;
	private static int linkedListLabelHeight = sendButtonHeight;
	private static Rectangle linkedListLabelRectangle = new Rectangle(gap,
			statusLabelHeight + (int) (3.5 * gap) + questionsPanelHeight + sendButtonHeight, linkedListLabelWidth,
			linkedListLabelHeight);

	private static int linkedListTextAreaWidth = frameWidth - 2 * gap;
	private static int linkedListTextAreaHeight = 50;
	private static Rectangle linkedListTextAreaRectangle = new Rectangle(gap,
			statusLabelHeight + (int) (3.5 * gap) + questionsPanelHeight + 2 * sendButtonHeight,
			linkedListTextAreaWidth, linkedListTextAreaHeight);

	private static int binaryTreeLabelWidth = frameWidth - 2 * gap;
	private static int binaryTreeLabelHeight = sendButtonHeight;
	private static Rectangle binaryTreeLabelRectangle = new Rectangle(gap, statusLabelHeight + (int) (4.5 * gap)
			+ questionsPanelHeight + 2 * sendButtonHeight + linkedListTextAreaHeight, binaryTreeLabelWidth,
			binaryTreeLabelHeight);

	private static int binaryTreeTextAreaWidth = frameWidth - 2 * gap;
	private static int binaryTreeTextAreaHeight = linkedListTextAreaHeight;
	private static Rectangle binaryTreeTextAreaRectangle = new Rectangle(gap, statusLabelHeight + (int) (4.5 * gap)
			+ questionsPanelHeight + 3 * sendButtonHeight + linkedListTextAreaHeight, binaryTreeTextAreaWidth,
			binaryTreeTextAreaHeight);

	private static int preOrderLabelWidth = (int) ((frameWidth - 5 * gap) / 4);
	private static int preOrderLabelHeight = (frameHeight - 5 - (statusLabelHeight + (int) (6.5 * gap)
			+ questionsPanelHeight + 3 * sendButtonHeight + 2 * linkedListTextAreaHeight)) / 2;
	private static Rectangle preOrderLabelRectangle = new Rectangle(gap, statusLabelHeight + (int) (5.5 * gap)
			+ questionsPanelHeight + 3 * sendButtonHeight + 2 * linkedListTextAreaHeight, preOrderLabelWidth,
			preOrderLabelHeight);

	private static int preOrderDisplayButtonWidth = preOrderLabelWidth / 2;
	private static int preOrderDisplayButtonHeight = preOrderLabelHeight;
	private static Rectangle preOrderDisplayButtonRectangle = new Rectangle(gap,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			preOrderDisplayButtonWidth, preOrderDisplayButtonHeight);

	private static int preOrderSaveButtonWidth = preOrderDisplayButtonWidth;
	private static int preOrderSaveButtonHeight = preOrderLabelHeight;
	private static Rectangle preOrderSaveButtonRectangle = new Rectangle(gap + preOrderDisplayButtonWidth,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			preOrderSaveButtonWidth, preOrderSaveButtonHeight);

	private static int inOrderLabelWidth = preOrderLabelWidth;
	private static int inOrderLabelHeight = preOrderLabelHeight;
	private static Rectangle inOrderLabelRectangle = new Rectangle(2 * gap + preOrderLabelWidth, statusLabelHeight
			+ (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight + 2 * linkedListTextAreaHeight,
			inOrderLabelWidth, inOrderLabelHeight);

	private static int inOrderDisplayButtonWidth = preOrderLabelWidth / 2;
	private static int inOrderDisplayButtonHeight = preOrderLabelHeight;
	private static Rectangle inOrderDisplayButtonRectangle = new Rectangle(2 * gap + preOrderLabelWidth,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			inOrderDisplayButtonWidth, inOrderDisplayButtonHeight);

	private static int inOrderSaveButtonWidth = preOrderLabelWidth / 2;
	private static int inOrderSaveButtonHeight = preOrderLabelHeight;
	private static Rectangle inOrderSaveButtonRectangle = new Rectangle(
			2 * gap + preOrderLabelWidth + preOrderDisplayButtonWidth,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			inOrderSaveButtonWidth, inOrderSaveButtonHeight);

	private static int postOrderLabelWidth = preOrderLabelWidth;
	private static int postOrderLabelHeight = preOrderLabelHeight;
	private static Rectangle postOrderLabelRectangle = new Rectangle(3 * gap + 2 * preOrderLabelWidth, statusLabelHeight
			+ (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight + 2 * linkedListTextAreaHeight,
			postOrderLabelWidth, postOrderLabelHeight);

	private static int postOrderDisplayButtonWidth = preOrderLabelWidth / 2;
	private static int postOrderDisplayButtonHeight = preOrderLabelHeight;
	private static Rectangle postOrderDisplayButtonRectangle = new Rectangle(3 * gap + 2 * preOrderLabelWidth,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			postOrderDisplayButtonWidth, postOrderDisplayButtonHeight);

	private static int postOrderSaveButtonWidth = preOrderLabelWidth / 2;
	private static int postOrderSaveButtonHeight = preOrderLabelHeight;
	private static Rectangle postOrderSaveButtonRectangle = new Rectangle(
			3 * gap + 2 * preOrderLabelWidth + preOrderDisplayButtonWidth,
			statusLabelHeight + (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight
					+ 2 * linkedListTextAreaHeight + preOrderLabelHeight,
			postOrderSaveButtonWidth, postOrderSaveButtonHeight);

	private static int exitButtonWidth = preOrderLabelWidth;
	private static int exitButtonHeight = preOrderLabelHeight * 2;
	private static Rectangle exitButtonRectangle = new Rectangle(4 * gap + 3 * preOrderLabelWidth, statusLabelHeight
			+ (int) (5.5 * gap) + questionsPanelHeight + 3 * sendButtonHeight + 2 * linkedListTextAreaHeight,
			exitButtonWidth, exitButtonHeight);

	///////////////// Each component size /////////////////

	public ServerFrame() {
		super(applicationName);
		this.setResizable(false);
		// set size of the frame
		this.setSize(frameWidth + 16, frameHeight + 32);
		// set close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// SpringLayout myLayout = new SpringLayout();
		this.setLayout(null);

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// refresh linkedListTextArea and connected students number every second.
				linkedListTextArea.setText(questionService.getDListOfQuestions().toString());
				statusLabel.setText("Connected student(s): " + questionService.getActivedClientCount());

				// enable sendButton if all students have reponsed
				if (!sendButton.isEnabled() && questionService.isAllResponsed()) {
					sendButton.setEnabled(true);
					sendButton.setToolTipText(null);
				}
			}
		});
		timer.start();

		////////////////// Add Control /////////////////

		questionsTable.setShowHorizontalLines(true);
		questionsTable.setRowSelectionAllowed(true);
		questionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		questionsPanel.setBorder(new EtchedBorder());
		placeComponents(questionsPanel, questionsPanelRectangle);
		questionsLabel.setBounds(questionsLabelRectangle);
		questionsPanel.add(questionsLabel);

		JScrollPane scrPane = new JScrollPane(questionsTable);
		scrPane.setBounds(questionsTableRectangle);
		questionsTable.setFillsViewportHeight(true);
		questionsPanel.add(scrPane);

		questionsSortByLabel.setBounds(questionsSortByLabelRectangle);
		questionsPanel.add(questionsSortByLabel);
		questionsQnNubButton.setBounds(questionsQnNubButtonRectangle);
		questionsPanel.add(questionsQnNubButton);
		questionsTopicButton.setBounds(questionsTopicButtonRectangle);
		questionsPanel.add(questionsTopicButton);
		questionsAnsButton.setBounds(questionsAnsButtonRectangle);
		questionsPanel.add(questionsAnsButton);

		questionDetailPanel.setBorder(new EtchedBorder());
		placeComponents(questionDetailPanel, questionDetailPanelRectangle);
		questionDetailLabel.setBounds(questionDetailLabelRectangle);
		questionDetailPanel.add(questionDetailLabel);
		questionDetailTopicLabel.setBounds(questionDetailTopicLabelRectangle);
		questionDetailPanel.add(questionDetailTopicLabel);
		questionDetailTopicText.setBounds(questionDetailTopicTextRectangle);
		questionDetailPanel.add(questionDetailTopicText);
		questionDetailQnNumLabel.setBounds(questionDetailQnNumLabelRectangle);
		questionDetailPanel.add(questionDetailQnNumLabel);
		questionDetailQnTextArea.setLineWrap(true);
		questionDetailQnTextArea.setWrapStyleWord(true);
		questionDetailQnTextArea.setBounds(questionDetailQnTextAreaRectangle);
		questionDetailQnTextArea.setBorder(new EtchedBorder());

		questionDetailPanel.add(questionDetailQnTextArea);
		questionDetailQnNumIntLabel.setBounds(questionDetailQnNumIntLabelRectangle);
		questionDetailPanel.add(questionDetailQnNumIntLabel);
		questionDetailAnsALabel.setBounds(questionDetailAnsALabelRectangle);
		questionDetailPanel.add(questionDetailAnsALabel);
		questionDetailAnsAText.setBounds(questionDetailAnsATextRectangle);
		questionDetailPanel.add(questionDetailAnsAText);
		questionDetailAnsBLabel.setBounds(questionDetailAnsBLabelRectangle);
		questionDetailPanel.add(questionDetailAnsBLabel);
		questionDetailAnsBText.setBounds(questionDetailAnsBTextRectangle);
		questionDetailPanel.add(questionDetailAnsBText);
		questionDetailAnsCLabel.setBounds(questionDetailAnsCLabelRectangle);
		questionDetailPanel.add(questionDetailAnsCLabel);
		questionDetailAnsCText.setBounds(questionDetailAnsCTextRectangle);
		questionDetailPanel.add(questionDetailAnsCText);
		questionDetailAnsDLabel.setBounds(questionDetailAnsDLabelRectangle);
		questionDetailPanel.add(questionDetailAnsDLabel);
		questionDetailAnsDText.setBounds(questionDetailAnsDTextRectangle);
		questionDetailPanel.add(questionDetailAnsDText);

		placeComponents(statusLabel, statusLabelHeightRectangle);
		placeComponents(sendButton, sendButtonRectangle);
		placeComponents(ansTextField, ansTextRectangle);
		placeComponents(ansLabel, ansLabelRectangle);
		placeComponents(linkedListLabel, linkedListLabelRectangle);
		linkedListTextArea.setLineWrap(true);
		linkedListTextArea.setEditable(false);
		placeComponents(linkedListTextArea, linkedListTextAreaRectangle);
		placeComponents(binaryTreeLabel, binaryTreeLabelRectangle);
		binaryTreeTextArea.setLineWrap(true);
		binaryTreeTextArea.setEditable(false);
		placeComponents(binaryTreeTextArea, binaryTreeTextAreaRectangle);
		placeComponents(preOrderLabel, preOrderLabelRectangle);
		placeComponents(preOrderDisplayButton, preOrderDisplayButtonRectangle);
		placeComponents(preOrderSaveButton, preOrderSaveButtonRectangle);
		placeComponents(inOrderLabel, inOrderLabelRectangle);
		placeComponents(inOrderDisplayButton, inOrderDisplayButtonRectangle);
		placeComponents(inOrderSaveButton, inOrderSaveButtonRectangle);
		placeComponents(postOrderLabel, postOrderLabelRectangle);
		placeComponents(postOrderDisplayButton, postOrderDisplayButtonRectangle);
		placeComponents(postOrderSaveButton, postOrderSaveButtonRectangle);
		placeComponents(exitButton, exitButtonRectangle);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent evt) {
				formWindowOpened(evt);
			}
		});

		questionsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				questionsTableValueChanged(event);
			}
		});

		questionsQnNubButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				questionsQnNubButtonMouseClicked(evt);
			}
		});

		questionsTopicButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				questionsTopicButtonMouseClicked(evt);
			}
		});

		questionsAnsButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				questionsAnsButtonMouseClicked(evt);
			}
		});

		inOrderDisplayButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				inOrderDisplayButtonMouseClicked(evt);
			}
		});

		preOrderDisplayButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				preOrderDisplayButtonMouseClicked(evt);
			}
		});

		postOrderDisplayButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				postOrderDisplayButtonMouseClicked(evt);
			}
		});

		exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				exitButtonMouseClicked(evt);
			}
		});

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendButtonPerformed(e);
			}
		});

		preOrderSaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saveButtonMouseClicked(evt);
			}
		});

		inOrderSaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saveButtonMouseClicked(evt);
			}
		});

		postOrderSaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				saveButtonMouseClicked(evt);
			}
		});
	}

	private void placeComponents(Component component, Rectangle rectangle) {

		if (component instanceof Container) {
			Container container = (Container) component;
			container.setLayout(null);
		}
		component.setBounds(rectangle);
		this.add(component);
	}

	/***
	 * Show questions in JTable
	 * 
	 * @param questionList
	 *            questions to be shown
	 */
	private void showList(List<Question> questionList) {
		Object[] identifiers = { "#", "Topic", "Question", "Id" };
		Object[][] data = new Object[questionList.size()][];
		for (int i = 0; i < questionList.size(); i++) {
			Question record = questionList.get(i);
			data[i] = new Object[] { record.getQnNum(), record.getTopic(), record.getQuestion(), record.getId() };
		}
		model.setDataVector(data, identifiers);
		questionsTable.setModel(model);
		questionsTable.removeColumn(questionsTable.getColumnModel().getColumn(3));
		// Set Column Width
		questionsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		questionsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		questionsTable.getColumnModel().getColumn(2).setPreferredWidth(300);
	}

	/***
	 * Show one question details
	 * 
	 * @param record
	 *            question to be shown
	 */
	private void showQuestion(Question record) {
		questionDetailTopicText.setText(record.getTopic());
		questionDetailQnTextArea.setText(record.getQuestion());
		questionDetailAnsAText.setText(record.getAnsA());
		questionDetailAnsBText.setText(record.getAnsB());
		questionDetailAnsCText.setText(record.getAnsC());
		questionDetailAnsDText.setText(record.getAnsD());
		ansTextField.setText(record.getCorrectAns());
	}

	/***
	 * Clean question details which are showing
	 */
	private void cleanQuestion() {
		questionDetailTopicText.setText(StringUtils.EMPTY);
		questionDetailQnTextArea.setText(StringUtils.EMPTY);
		questionDetailAnsAText.setText(StringUtils.EMPTY);
		questionDetailAnsBText.setText(StringUtils.EMPTY);
		questionDetailAnsCText.setText(StringUtils.EMPTY);
		questionDetailAnsDText.setText(StringUtils.EMPTY);
		ansTextField.setText(StringUtils.EMPTY);
	}

	private void formWindowOpened(java.awt.event.WindowEvent evt) {
		// Find all question and show them when window opened
		showList(questionService.findAll());
	}

	/***
	 * When click on one of the question, show its deatils
	 * 
	 * @param event
	 */
	private void questionsTableValueChanged(ListSelectionEvent event) {
		// prevent this function being call repeatedly
		if (!event.getValueIsAdjusting()) {
			// if no row being selected, clean question details
			if (questionsTable.getSelectedRow() == -1) {
				cleanQuestion();
			} else {
				Object objId = questionsTable.getModel().getValueAt(questionsTable.getSelectedRow(), 3);
				String selectedId = objId.toString();
				Question question = questionService.findById(selectedId);
				showQuestion(question);
			}
		}
	}

	private void questionsQnNubButtonMouseClicked(java.awt.event.MouseEvent evt) {
		questionService.sortByQnNum();
		showList(questionService.getDisplayingList());
	}

	private void questionsTopicButtonMouseClicked(java.awt.event.MouseEvent evt) {
		questionService.sortByTopic();
		showList(questionService.getDisplayingList());
	}

	private void questionsAnsButtonMouseClicked(java.awt.event.MouseEvent evt) {
		questionService.sortByAndser();
		showList(questionService.getDisplayingList());
	}

	private void inOrderDisplayButtonMouseClicked(java.awt.event.MouseEvent evt) {
		binaryTreeTextArea.setText(questionService.getbTreeOfQuestions().inOrderTraverseTree());
	}

	private void preOrderDisplayButtonMouseClicked(java.awt.event.MouseEvent evt) {
		binaryTreeTextArea.setText(questionService.getbTreeOfQuestions().preOrderTraverseTree());
	}

	private void postOrderDisplayButtonMouseClicked(java.awt.event.MouseEvent evt) {
		binaryTreeTextArea.setText(questionService.getbTreeOfQuestions().postOrderTraverseTree());
	}

	private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {
		System.exit(0);
	}

	private void sendButtonPerformed(ActionEvent e) {
		int selectRowNum = questionsTable.getSelectedRow();
		//If there is a question being selected:
		//	1. get question id
		//	2. send question
		//	3. disable send button and add tool tip
		if (selectRowNum != -1) {
			Object objId = questionsTable.getModel().getValueAt(selectRowNum, 3);
			if (objId != null) {
				String selectedId = objId.toString();
				int count = questionService.sendQuestionById(selectedId);
				JOptionPane.showMessageDialog(null, "Questions have been sent to " + count + " student(s).", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				sendButton.setEnabled(false);
				sendButton.setToolTipText("Waiting for all students response.");
			}
			//if there is no question being selected
			//	display error message
		} else {
			JOptionPane.showMessageDialog(null, "Please select question you want to send.", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/***
	 * 1. pop up file chooser
	 * 2. if save button is clicked
	 * 3. add extension to the path and save file to the path
	 * @param evt
	 */
	private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
		fc.addChoosableFileFilter(new FileNameExtensionFilter("TXT file", "txt"));

		if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String filename = file.getAbsolutePath();
			try {
				if (!FilenameUtils.getExtension(filename).equals("txt")) {
					filename = FilenameUtils.removeExtension(filename);
					filename += ".txt";
				}
				questionService.saveToFile(filename);
				JOptionPane.showMessageDialog(null, "Save to " + filename + " successfully!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error while writing file.\nError Message:\n" + e.getMessage(),
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
