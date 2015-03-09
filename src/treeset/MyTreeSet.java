package treeset;

import java.util.Iterator;
import java.util.Comparator;


/**
 * 
 *
 *
 * @param <T>
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class MyTreeSet<T extends Comparable<T> > {

	private Node<T> root;
	private Node<T> head = new Node<T>(null);
	private Node<T> tail = new Node<T>(null);
	
	private int size = 0;
	private Comparator<T> comparator;
	
	/**
	 * Adds one element to the set.
	 * 
	 * @param element
	 * - the element to add to the set. 
	 * 
	 * @throws
	 * NullPointerException if element is null.
	 */
	public void add(T element){
		if (element == null){
			throw new NullPointerException("Element can't be null.");
		}
		
		if (root == null){
			root = new Node<T>(element);
			head.setNextLargest(root);
			root.setNextSmallest(head);
			
			root.setNextLargest(tail);
			tail.setNextSmallest(root);
			++size;
		} else {
			if ( add(element, root) ){
				++size;
			}
		}
	} // add
	
	private boolean add(T element, Node<T> currNode){
		boolean valueAdded = true;
		
		T currVal = root.getValue();
		if (currVal.compareTo(element) == 0){
			valueAdded = false;
			
		} else if (currVal.compareTo(element) > 0){
			Node<T> leftChild = currNode.getLeftChild();
			if (leftChild == null){
				leftChild = new Node<T>(element);
			} else {
				add(element, leftChild);
			}
			
		} else if (currVal.compareTo(element) < 0){
			Node<T> rightChild = currNode.getRightChild();
			if (rightChild == null){
				rightChild = new Node<T>(element);
			} else {
				add(element, rightChild);
			}
			
		} 
		return valueAdded;
	} // add
	
	public boolean contains(T element){
		return false;
	} // contains
	
	/**
	 * Adds the specified element from the set.
	 * 
	 * @param element
	 * - the element to remove from the set. 
	 * 
	 * @throws
	 * NullPointerException if element is null.
	 */
	public void remove(T element){
		if (element == null){
			throw new NullPointerException("Element can't be null.");
		}
		
		if ( remove(element, root) ){
			--size;
		}
	} // remove
	
	private boolean remove(T element, Node<T> currNode){
		boolean removedElement = false;
		
		T currVal = currNode.getValue();
		if (currVal.compareTo(element) == 0){
			--size;
			removedElement = true;
			//TODO REMOVE THE ELEMENT!!!
			
		} else if (currVal.compareTo(element) > 0){
			Node<T> leftChild = currNode.getLeftChild();
			if (leftChild == null){
				removedElement = false;
			} else {
				removedElement = remove(element, leftChild);
			}
			
		} else if (currVal.compareTo(element) < 0){
			Node<T> rightChild = currNode.getRightChild();
			if (rightChild == null){
				removedElement = false;
			} else {
				removedElement = remove(element, rightChild);
			}
			
		} 
		
		return removedElement;
	} // remove
	
	public int size(){
		return size;
	}
	
	public Iterator<T> iterator(){
		return null;
	}
} // MyTreeSet
