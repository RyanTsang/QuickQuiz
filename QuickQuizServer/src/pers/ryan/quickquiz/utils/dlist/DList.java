package pers.ryan.quickquiz.utils.dlist;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * Modified version of Toshimi Minoura DList
 * 
 * @author Ryan Tsang
 *
 */
public class DList<T> {

	Node<T> head;

	public DList() {
		head = new Node<T>();
	}

	/**
	 * find item equals to t in the list 
	 * @param _t entity to be found
	 * @return if found, return entity in the list. if not, return null. 
	 */
	public T find(T _t) {
		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			if (current.t.equals(_t)) {
				return current.t;
			}
		}
		return null;
	}

	/**
	 * find note contains item equals to t in the list 
	 * @param _t entity to be found
	 * @return if found, return node contains entity in the list. if not, return null. 
	 */
	public Node<T> findNode(T _t) {
		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			if (current.t.equals(_t)) {
				return current;
			}
		}
		return null;
	}

	/***
	 * get item by index
	 * @param i index(start from 0, index 0 = head)
	 * @return entity
	 */
	public T get(int i) {
		Node<T> current = getNode(i);
		return current.t;
	}

	/***
	 * get note by index
	 * @param i index(start from 0, index 0 = head)
	 * @return node
	 */
	public Node<T> getNode(int i) {
		Node<T> current = this.head;
		if (i < 0 || current == null) {
			throw new ArrayIndexOutOfBoundsException();
		}
		while (i > 0) {
			i--;
			current = current.next;
			if (current == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		return current;
	}

	/**
	 * append a new note contain t at the end of the list
	 * @param t entity to be appended
	 */
	public void append(T t) {
		Node<T> currentNode = new Node<T>(t);
		Node<T> lastNode = getNode(size());
		lastNode.next = currentNode;
		currentNode.prev = lastNode;
	}

	/***
	 * remove first note contains t
	 * @param t entity to be removed
	 */
	public void remove(T t) {
		findNode(t).remove();
	}

	/***
	 * remove all notes in the list
	 */
	public void removeAll() {
		head = new Node<T>();
	}

	/**
	 * convert list to an ArrayList
	 * @return
	 */
	public List<T> toList() {
		List<T> res = new ArrayList<T>();
		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			res.add(current.t);
		}
		return res;
	}

	/**
	 * save all items in a provided list to DoubleLinkedList
	 * @param list
	 */
	public void saveList(List<T> list) {
		for (T t : list) {
			head.append(new Node<T>(t));
		}
	}

	public boolean isEmpty() {
		if (head.next == null) {
			return true;
		}
		return false;
	}

	public int size() {
		int res = 0;
		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			res++;
		}
		return res;
	}
	
	/**
	 * get last entity
	 * @return
	 */
	public T getLast() {
		T res = null;
		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			res = current.t;
		}
		return res;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	@Override
	public String toString() {
		String res = "HEAD";
		String connector = " <-> ";
		res += connector;

		for (Node<T> current = head.next; current != null && current.t != null; current = current.next) {
			res += current.t;
			res += connector;
		}
		return res + "TAIL";
	}

}

class Node<T> {
	Node<T> prev;
	Node<T> next;
	T t;	//entity

	Node() {
		prev = this;
		next = this;
	}

	Node(T _t) {
		prev = null;
		next = null;
		this.t = _t;
	}

	public void append(Node<T> newNode) {
		newNode.prev = this;
		newNode.next = next;
		if (next != null) {
			next.prev = newNode;
		}
		next = newNode;
	}

	public void insert(Node<T> newNode) {
		newNode.prev = prev;
		newNode.next = this;
		prev.next = newNode;
		prev = newNode;
	}

	public void remove() {
		next.prev = prev;
		prev.next = next;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
