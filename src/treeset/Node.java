
package treeset;

/**
 * The nodes represent the nodes in the class MyTreeSet. It includes one value
 * of the generic type T and has two children. The class is also used as
 * node in the double-linked list spanning the tree. The attributes nextSmallest
 * and nextLargest are references to the previous node and the next node in the
 * linked list.
 * 
 * @param <T>
 * Generic type argument, represents the value to store in each node. 
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
class Node<T> {

	private T value;
	private Node<T> leftChild;
	private Node<T> rightChild;
	
	private Node<T> nextSmallest;
	private Node<T> nextLargest;
	
	
	Node(T value){
		this.value = value;
	}
	
	void setValue(T value){
		this.value = value;
	}
	
	T getValue(){
		return value;
	}
	
	Node<T> getLeftChild(){
		return leftChild;
	}
	
	void setLeftChild(Node<T> leftChild){
		this.leftChild = leftChild;
	}
	
	Node<T> getRightChild(){
		return rightChild;
	}
	
	void setRightChild(Node<T> rightChild){
		this.rightChild = rightChild;
	}
	
	/**
	 * Gets the previous node in the linked list, which also contains the next
	 * smallest value.
	 * @return
	 * - node containing the next smallest value
	 */
	Node<T> getNextSmallest(){
		return nextSmallest;
	}
	
	/**
	 * Sets the reference to the previous node in the linked list. The previous 
	 * node should contain the next smallest value. 
	 */
	void setNextSmallest(Node<T> nextSmallest){
		this.nextSmallest = nextSmallest;
	}
	
	/**
	 * Gets the next node in the linked list, which also contains the next
	 * largest value.
	 * @return
	 * - node containing the next largest value
	 */
	Node<T> getNextLargest(){
		return nextLargest;
	}
	
	/**
	 * Sets the reference to the next node in the linked list. The next 
	 * node should contain the next largest value. 
	 */
	void setNextLargest(Node<T> nextLargest){
		this.nextLargest = nextLargest;
	}
	
	/**
	 * Returns the string representation of the node's value.
	 * @return
	 * - a string representing the node's value.
	 */
	public String toString(){
		String retString = null;
		if (value != null){
			retString = value.toString();
		}
		return retString;
	}
	
} // Node




