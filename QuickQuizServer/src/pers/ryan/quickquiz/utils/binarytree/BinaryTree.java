package pers.ryan.quickquiz.utils.binarytree;

import java.util.ArrayList;
import java.util.List;

/***
 * //Source: http://www.newthinktank.com/2013/03/binary-tree-in-java/ // New
 * Think Tank
 * 
 * @author Ryan Tsang
 */

public class BinaryTree<T> {

	Node<T> root;

	public void addNode(int key, T t) {
		// Create a new Node and initialize it
		Node<T> newNode = new Node<T>(key, t);
		// If there is no root this becomes root
		if (root == null) {
			root = newNode;
		} else {
			// Set root as the Node we will start
			// with as we traverse the tree
			Node<T> focusNode = root;
			// Future parent for our new Node
			Node<T> parent;
			while (true) {
				// root is the top parent so we start
				// there
				parent = focusNode;
				// Check if the new node should go on
				// the left side of the parent node
				if (key < focusNode.key) {
					// Switch focus to the left child
					focusNode = focusNode.leftChild;
					// If the left child has no children
					if (focusNode == null) {
						// then place the new node on the left of it
						parent.leftChild = newNode;
						return; // All Done
					}
				} else { // If we get here put the node on the right
					focusNode = focusNode.rightChild;
					// If the right child has no children
					if (focusNode == null) {
						// then place the new node on the right of it
						parent.rightChild = newNode;
						return; // All Done
					}
				}
			}
		}
	}

	// All nodes are visited in ascending order
	// Recursion is used to go to one node and
	// then go to its child nodes and so forth

	public String inOrderTraverseTree(Node<T> focusNode) {

		StringBuilder stringBuilder = new StringBuilder();

		if (focusNode != null) {

			// Traverse the left node

			stringBuilder.append(inOrderTraverseTree(focusNode.leftChild));

			// Visit the currently focused on node

			stringBuilder.append(focusNode + " -> ");

			// Traverse the right node

			stringBuilder.append(inOrderTraverseTree(focusNode.rightChild));

		}
		return stringBuilder.toString();
	}

	public String inOrderTraverseTree() {
		String s = inOrderTraverseTree(root);
		return "IN-ORDER: " + s.substring(0, s.length() - 3);
	}

	public String preOrderTraverseTree(Node<T> focusNode) {
		StringBuilder stringBuilder = new StringBuilder();
		if (focusNode != null) {
			stringBuilder.append(focusNode + " -> ");
			stringBuilder.append(preOrderTraverseTree(focusNode.leftChild));
			stringBuilder.append(preOrderTraverseTree(focusNode.rightChild));
		}
		return stringBuilder.toString();
	}

	public String preOrderTraverseTree() {
		String s = preOrderTraverseTree(this.root);
		return "PRE-ORDER: " + s.substring(0, s.length() - 3);
	}

	public String postOrderTraverseTree(Node<T> focusNode) {
		StringBuilder stringBuilder = new StringBuilder();
		if (focusNode != null) {
			stringBuilder.append(postOrderTraverseTree(focusNode.leftChild));
			stringBuilder.append(postOrderTraverseTree(focusNode.rightChild));
			stringBuilder.append(focusNode + " -> ");
		}
		return stringBuilder.toString();
	}

	public String postOrderTraverseTree() {
		String s = postOrderTraverseTree(this.root);
		return "POST-ORDER: " + s.substring(0, s.length() - 3);
	}

	public Node<T> findNode(int key) {
		// Start at the top of the tree
		Node<T> focusNode = root;
		// While we haven't found the Node
		// keep looking
		while (focusNode.key != key) {
			// If we should search to the left
			if (key < focusNode.key) {
				// Shift the focus Node to the left child
				focusNode = focusNode.leftChild;
			} else {
				// Shift the focus Node to the right child
				focusNode = focusNode.rightChild;
			}
			// The node wasn't found
			if (focusNode == null)
				return null;
		}
		return focusNode;
	}

	public List<T> toList(Node<T> focusNode) {
		List<T> list = new ArrayList<T>();
		
		if (focusNode != null) {
			// Traverse the left node
			list.addAll(toList(focusNode.leftChild));
			// Visit the currently focused on node
			list.add(focusNode.t);
			// Traverse the right node
			list.addAll(toList(focusNode.rightChild));
		}
		return list;
	}

	public T[] toArray() {
		List<T> list = toList(this.root);
		if(list != null) {
			return (T[]) list.toArray();
		}else {
			return null;
		}
	}
}

class Node<T> {
	int key;
	T t;	//entity

	Node<T> leftChild;
	Node<T> rightChild;
	Node(int key, T t) {
		this.key = key;
		this.t = t;
	}

	public String toString() {
		return t.toString();
	}
}
