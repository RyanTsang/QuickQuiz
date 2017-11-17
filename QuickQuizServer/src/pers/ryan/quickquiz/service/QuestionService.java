package pers.ryan.quickquiz.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pers.ryan.quickquiz.dao.QuestionDao;
import pers.ryan.quickquiz.domain.Question;
import pers.ryan.quickquiz.domain.QuestionWithAns;
import pers.ryan.quickquiz.server.QuestionServer;
import pers.ryan.quickquiz.utils.binarytree.BinaryTree;
import pers.ryan.quickquiz.utils.dlist.DList;

/**
 * Service layer of the program
 * Contains all service methods here
 * @author Ryan Tsang
 *
 */
public class QuestionService {

	/***
	 * Declare field and setter of questionDao for spring to inject
	 */
	private QuestionDao questionDao;
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	/***
	 * Declare field and setter of questionServer for spring to inject
	 */
	QuestionServer questionServer;
	public void setQuestionServer(QuestionServer questionServer) {
		this.questionServer = questionServer;
	}

	//list contains questions displaying in JTable
	List<Question> displayingList = new ArrayList<Question>();
	
	DList<QuestionWithAns> dListOfQuestions = new DList<QuestionWithAns>();
	BinaryTree<Question> bTreeOfQuestions = new BinaryTree<Question>();


	public List<Question> getDisplayingList() {
		return displayingList;
	}

	public DList<QuestionWithAns> getDListOfQuestions() {
		return dListOfQuestions;
	}

	public BinaryTree<Question> getbTreeOfQuestions() {
		return bTreeOfQuestions;
	}

	public List<Question> findAll() {
		List<Question> res = questionDao.findAll();
		displayingList = res;
		return res;
	}

	public Question findById(String id) {
		return questionDao.findById(id);
	}

	/**
	 * Sort ArrayList using Bubble Sort
	 */
	public void sortByQnNum() {
		for (int j = 0; j < displayingList.size() - 1; j++) {
			for (int i = 0; i < displayingList.size() - j - 1; i++) {
				if (displayingList.get(i).getQnNum() > displayingList.get(i + 1).getQnNum()) {
					swapPosition(displayingList, i, i + 1);
				}
			}
		}
	}
	
	private void swapPosition(List targetList, int firstIndex, int secondIndex) {
		Object temp = targetList.get(firstIndex);
		targetList.set(firstIndex, targetList.get(secondIndex));
		targetList.set(secondIndex, temp);
	}

	/**
	 * Sort ArrayList using Insert Sort
	 */
	public void sortByTopic() {
		for (int i = 1; i < displayingList.size(); i++) {
			Question temp = displayingList.get(i);
			for (int j = i - 1; j >= 0; j--) {
				if (String.CASE_INSENSITIVE_ORDER.compare(temp.getTopic(), displayingList.get(j).getTopic()) < 0) {
					displayingList.set(j + 1, displayingList.get(j));
				} else {
					displayingList.set(j + 1, temp);
					break;
				}
				displayingList.set(j, temp);
			}
		}
	}

	/**
	 * Sort ArrayList using Merge Sort
	 */
	public void sortByAndser() {
		Question[][] question2DArray = new Question[displayingList.size()][];
		for (int i = 0; i < displayingList.size(); i++) {
			question2DArray[i] = new Question[1];
			question2DArray[i][0] = displayingList.get(i);
		}

		while (question2DArray.length != 1) {
			question2DArray = (Question[][]) merge2DArray(question2DArray);
		}
		displayingList.removeAll(displayingList);
		for (int i = 0; i < question2DArray[0].length; i++) {
			displayingList.add(question2DArray[0][i]);
		}
	}

	private Question[][] merge2DArray(Question[][] objArray) {
		Question[][] res = new Question[objArray.length / 2][];
		for (int i = 0, j = 0; i < objArray.length; i++, j++) {
			if (res.length == 1) {
				if (j == 0) {
					res[j] = mergeObjArray(objArray[i], objArray[++i]);
				}
				if (j > 0) {
					res[j - 1] = mergeObjArray(res[j - 1], objArray[i]);
				}
			} else {
				if (i != objArray.length - 1) {
					res[j] = mergeObjArray(objArray[i], objArray[++i]);
				} else {
					res[j - 1] = mergeObjArray(res[j - 1], objArray[i]);
				}
			}
		}
		return res;
	}

	private Question[] mergeObjArray(Question[] aArray, Question[] bArray) {
		Question[] res = new Question[aArray.length + bArray.length];
		Iterator<Question> aArrayIter = Arrays.asList(aArray).iterator();
		Iterator<Question> bArrayIter = Arrays.asList(bArray).iterator();
		int resIndex = 0;
		Question a = null;
		Question b = null;
		while (aArrayIter.hasNext() || bArrayIter.hasNext() || a != null || b != null) {
			if (!bArrayIter.hasNext() && b == null) {
				res[resIndex++] = (a == null ? aArrayIter.next() : a);
				a = null;
			} else if (!aArrayIter.hasNext() && a == null) {
				res[resIndex++] = (b == null ? bArrayIter.next() : b);
				b = null;
			} else {
				if (a == null)
					a = aArrayIter.next();
				if (b == null)
					b = bArrayIter.next();

				if (QuestionAnsComparator.compare(a, b) > 0) {
					res[resIndex++] = b;
					b = null;
				} else {
					res[resIndex++] = a;
					a = null;
				}
			}
		}

		return res;
	}
	
	public static Comparator<Question> QuestionAnsComparator = new Comparator<Question>() {
		public int compare(Question question1, Question question2) {
			String ans1 = question1.getCorrectAns().toUpperCase();
			String ans2 = question2.getCorrectAns().toUpperCase();

			return ans1.compareTo(ans2);
		}

	};

	/**
	 * save sent question to 3 collections
	 * @param question question to be saved
	 */
	private void insertToSentList(Question question) {
		dListOfQuestions.append(new QuestionWithAns(question));
		bTreeOfQuestions.addNode(question.getQnNum(), question);
	}

	public int sendQuestionById(String questionId) {
		Question question = findById(questionId);
		int res = questionServer.sendQuestion(question);
		insertToSentList(question);
		dListOfQuestions.getLast().setSentCount(res);
		return res;
	}

	public int getActivedClientCount() {
		return questionServer.getActivedClientCount();
	}

	/***
	 * Save bTreeOfQuestions to a txt file
	 * 		1. convert bTreeOfQuestions to array
	 * 		2. store items in a HashMap
	 * 		3. Iterate HashMap and write every item to a txt file
	 * @param fileName save data to the file with this file name
	 * @return how many items have been saved. -1 means fail to save it.
	 * @throws IOException 
	 */
	public int saveToFile(String fileName) throws IOException {
		Object[] arrayOfQuestions = bTreeOfQuestions.toArray();
		
		//just for meeting assessment mark criteria
		//	--'Apply a hashing algorithm to the output of each of these files.'
		Map<Integer, String> hm = new HashMap<Integer, String>();
		for (Object o : arrayOfQuestions) {
			Question q = (Question) o;
			hm.put(q.getQnNum(), q.getTopic());
		}

		int count = -1;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(fileName), false);

			StringBuilder sb = new StringBuilder();
			for (Entry<Integer, String> entry : hm.entrySet()) {
				sb.append(entry.getKey() + "-" + entry.getValue());
				sb.append(", ");
			}
			fileWriter.write(sb.substring(0, sb.length() - 2).toString());
		} catch (IOException ex) {
			System.out.println("Error while writing file.");
			ex.printStackTrace();
			throw ex;
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Error while flushing/closing fileWriter.");
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public boolean isAllResponsed() {
		return dListOfQuestions.getLast().isAllResponsed();
	}
}
