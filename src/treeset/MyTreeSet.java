package treeset;

import java.util.Iterator;
import java.util.Comparator;


/**
 * Set based on a binary search tree. No duplicates.
 *
 *
 * @param <T>
 * - the datatype of the elements contained in the set
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class MyTreeSet<T extends Comparable<T> > implements Iterable<T>{

	private Node<T> root;
	private Node<T> head;
	private Node<T> tail;
	
	private int size;
	private Comparator<T> comparator;
	private int modCount = 0;
	
	/**
	 * Creates an empty MyTreeSet object.
	 */
	public MyTreeSet(){
		head = new Node<T>(null);
		tail = new Node<T>(null);
		
		head.setNextLargest(tail);
		tail.setNextSmallest(head);
		
		size = 0;
	}
	
	/**
	 * Adds one element to the set. Does nothing if the set already contains 
	 * the element.
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
			addNewNodeToList(head, root);
			addNewNodeToList(root, tail);
			
			++size;
			
		} else {
			if (comparator != null && addWithComperator(element, root) ){
				++size;
				++modCount;
				
			} else if ( add(element, root) ){
				++size;
				++modCount;
			}
		}
	} // add
	
	/**
	 * Auxiliary method to add. Finds and adds the element to the correct 
	 * location in the supplied subtree. Doesn't add the element if it already
	 * exist in the set. This method is called if no comparator has been set in 
	 * the object. Traversal through the tree is based on compareTo.
	 *  
	 * @param element
	 * - the element to add to the set.
	 * @param subroot
	 * - the root of the subtree in which to add the element.
	 * @return
	 * true if the element was added to the set.
	 */
	private boolean add(T element, Node<T> subroot){
		
		T currVal = subroot.getValue();
		if (currVal.compareTo(element) == 0){
			return false;
			
		} else if (currVal.compareTo(element) > 0){
			Node<T> leftChild = subroot.getLeftChild();
			if (leftChild == null){
				addNewLeftChild(subroot, element);
				return true;
				
			} else {
				return add(element, leftChild);
			}
			
		} else if (currVal.compareTo(element) < 0){
			Node<T> rightChild = subroot.getRightChild();
			if (rightChild == null){
				addNewRightChild(subroot, element);
				return true;
				
			} else {
				return add(element, rightChild);
			}
			
		} 
		return false;
	} // add
	
	/**
	 * Auxiliary method to add. Finds and adds the element to the correct 
	 * location in the supplied subtree. Doesn't add the element if it already
	 * exist in the set. This method is called in favor of 
	 * <i>add(T element, Node<T> subroot)</i> if a comparator has been set in 
	 * MyTreeSet-object. Traversal through the tree is based on the comparator attribute.
	 *  
	 * @param element
	 * - the element to add to the set.
	 * @param subroot
	 * - the root of the subtree in which to add the element.
	 * @return
	 * true if the element was added to the set.
	 */
	private boolean addWithComperator(T element, Node<T> subroot){
		
		T currVal = subroot.getValue();
		if (comparator.compare(currVal, element) == 0){
			return false;
			
		} else if (comparator.compare(currVal, element) > 0){
			Node<T> leftChild = subroot.getLeftChild();
			if (leftChild == null){
				addNewLeftChild(subroot, element);
				return true;
				
			} else {
				return addWithComperator(element, leftChild);
			}
			
		} else if (comparator.compare(currVal, element) < 0){
			Node<T> rightChild = subroot.getRightChild();
			if (rightChild == null){
				addNewRightChild(subroot, element);
				return true;
				
			} else {
				return addWithComperator(element, rightChild);
			}
			
		} 
		return false;	
	} //addWithComperator
	
	/**
	 * Creates a new node containing the supplied element and adds it to the 
	 * specified node as left child. Any existing left child will be lost.
	 * 
	 * @param currNode
	 * - the node for which to set left child. 
	 * @param element
	 * - the element to add in the new node.
	 */
	private void addNewLeftChild(Node<T> currNode, T element){
		Node<T> leftChild = new Node<T>(element);
		currNode.setLeftChild( leftChild );
		addNewNodeToList(leftChild, currNode);
	}
	
	/**
	 * Creates a new node containing the supplied element and adds it to the 
	 * specified node as right child. Any existing right child will be lost.
	 * 
	 * @param currNode
	 * - the node for which to set right child. 
	 * @param element
	 * - the element to add in the new node.
	 */
	private void addNewRightChild(Node<T> currNode, T element){
		Node<T> rightChild = new Node<T>(element);
		currNode.setRightChild( rightChild );
		addNewNodeToList(currNode, rightChild);
	}
	
	/**
	 * Adds one new node to the linked list spanning the tree. One of the nodes 
	 * is assumed to be smaller than the other node. It's also assumed that one
	 * node is new and is NOT in the list. The new node's nextLargest and 
	 * nextSmallest references have to be null, for the method to work properly.
	 * 
	 * @param smaller
	 * - the smaller of the two nodes
	 * @param larger
	 * - the larger of the two nodes
	 */
	private void addNewNodeToList(Node<T> smaller, Node<T> larger){
		if (smaller.getNextLargest() != null){
			Node<T> tmpNode = smaller.getNextLargest();
			
			larger.setNextLargest( tmpNode );
			tmpNode.setNextSmallest( larger );
		} else if (larger.getNextSmallest() != null){
			Node<T> tmpNode = larger.getNextSmallest();
			
			smaller.setNextSmallest( tmpNode );
			tmpNode.setNextLargest( smaller );
		}
		
		smaller.setNextLargest( larger );
		larger.setNextSmallest( smaller );
	}
	
	public boolean contains(T element){
		if(element == null){
			throw new NullPointerException("Element can't be null.");
		}
		
		if(root != null && comparator == null)
			return containsComparable(root, element);
		else if (root != null)
			return containsComparator(root, element);
		else
			return false;
	}//contains
	
	private boolean containsComparable(Node<T> toSearch, T element){
		if(toSearch == null){
			return false;
		}
		if(toSearch.getValue().compareTo(element) == 0){
			return true;
		}
		if(toSearch.getValue().compareTo(element) < 0){
			return containsComparable(toSearch.getRightChild(), element);
		}else{ // > 0
			return containsComparable(toSearch.getLeftChild(), element);
		}
	}//containsComparable
	
	
	
	private boolean containsComparator(Node<T> toSearch, T element){
		if(toSearch == null){
			return false;
		}
		if(comparator.compare(toSearch.getValue(), element) == 0){
			return true;
		}
		if(comparator.compare(toSearch.getValue(), element) < 0){
			return containsComparator(toSearch.getRightChild(), element);
		}else{ // > 0
			return containsComparator(toSearch.getLeftChild(), element);
		}
	}//containsComparator
	
	/**
	 * Removes the specified element from the set. Does nothing if the set doesn't
	 * contain the element.
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
		
		if (comparator != null && root != null){
			root = removeWithComparator(element, root);
		} else if ( root != null){
			root = remove(element, root);
		}
	} // remove
	
	/**
	 * Finds and removes the node containing the specified element via recursion. 
	 * An updated subtree is returned. The search is based on compareTo.
	 * 
	 * @param element
	 * - the element to remove.
	 * @param currNode
	 * - the subroot to search for the element.
	 * @return
	 * - subroot to the modified tree
	 */
	private Node<T> remove(T element, Node<T> currNode){
		Node<T> newSubroot = currNode;
		
		T currVal = currNode.getValue();
		if (currVal.compareTo(element) == 0){
			newSubroot = removeCurrentNode(currNode);
			
		} else if (currVal.compareTo(element) > 0){
			Node<T> leftChild = currNode.getLeftChild();
			if (leftChild != null){
				currNode.setLeftChild( remove(element, leftChild) );
			}
			
		} else if (currVal.compareTo(element) < 0){
			Node<T> rightChild = currNode.getRightChild();
			if (rightChild != null){
				currNode.setRightChild( remove(element, rightChild) );
			} 
		} 
		
		return newSubroot;
	} // remove
	
	/**
	 * Finds and removes the node containing the specified element via recursion. 
	 * An updated subtree is returned. The search is based on the set comparator.
	 * 
	 * @param element
	 * - the element to remove.
	 * @param currNode
	 * - the subroot to search for the element.
	 * @return
	 * - subroot to the modified tree
	 */
	private Node<T> removeWithComparator(T element, Node<T> currNode){
		Node<T> newSubroot = currNode;
		
		T currVal = currNode.getValue();
		if (comparator.compare(currVal, element) == 0){
			newSubroot = removeCurrentNode(currNode);
			
		} else if (comparator.compare(currVal, element) > 0){
			Node<T> leftChild = currNode.getLeftChild();
			if (leftChild != null){
				currNode.setLeftChild( removeWithComparator(element, leftChild) );
			}
			
		} else if (comparator.compare(currVal, element) < 0){
			Node<T> rightChild = currNode.getRightChild();
			if (rightChild != null){
				currNode.setRightChild( removeWithComparator(element, rightChild) );
			} 
		} 
		
		return newSubroot;
	} // removeWithComparator
	
	/**
	 * Removes the supplied node from the tree.
	 * 
	 * @param currNode
	 * - the node to remove from the tree.
	 * @return
	 * - the new subtree with currNode removed.
	 */
	private Node<T> removeCurrentNode(Node<T> currNode){
		Node<T> retVal;
		
		Node<T> leftChild = currNode.getLeftChild();
		Node<T> rightChild = currNode.getRightChild();
		
		if (leftChild != null && rightChild != null){
			T newVal = removeSmallestValue( rightChild );
			currNode.setValue( newVal );
			retVal = currNode;

		} else if (leftChild != null){
			retVal = leftChild;
			removeFromList( currNode );
			
		} else if (rightChild != null){
			retVal = rightChild;
			removeFromList( currNode );
			
		} else {
			retVal = null;
			removeFromList( currNode );
		}
		--size;
		--modCount;
		
		return retVal;
	}
	
	/**
	 * Auxiliary method for remove. Finds the smallest value in the specified 
	 * subtree and returns it. The node containing the value is removed. 
	 * 
	 * @param subroot
	 * - the root from which to search for the smallest value
	 * @return
	 * the value of the smallest value in the subtree
	 */
	private T removeSmallestValue(Node<T> subroot){
		Node<T> parentNode = subroot;
		Node<T> nextNode = subroot;
		
		while ( nextNode.getLeftChild() != null){
			parentNode = nextNode;
			nextNode = nextNode.getLeftChild();
		}
		
		parentNode.setLeftChild( nextNode.getRightChild() );
		
		removeFromList( nextNode );
		
		return nextNode.getValue();
	} // removeSmallestValue
	
	/**
	 * Removes the specified node from the linked list spanning through the tree.
	 * @param toRemove
	 * - the node to remove from the list.
	 */
	private void removeFromList(Node<T> toRemove){
		Node<T> smaller = toRemove.getNextSmallest();
		Node<T> larger = toRemove.getNextLargest();
		
		smaller.setNextLargest( larger );
		larger.setNextSmallest( smaller );
	}
	
	/**
	 * Returns the size of the set, i.e. the number of elements in the set.
	 * @return
	 * the size of the set.
	 */
	public int size(){
		return size;
	} // size
	
	/**
	 * Returns a string representation of the set.
	 * @return
	 * - a string representing the set
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		Node<T> tmpNode = head.getNextLargest();
		while (tmpNode != tail){
			if (builder.length() != 0){
				builder.append( ", " );
			}
			
			builder.append( tmpNode.toString() );
			tmpNode = tmpNode.getNextLargest();
		}
		
		return "[" + builder.toString() + "]";
	}

	
	/**
	 * Returns an iterator belonging to MyTreeSet.
	 */
	public Iterator<T> iterator(){
		return new MyTreeSetIterator();
	} // iterator
	
	

	/**
	 * Used to iterate over the elements in a MyTreeSet object. 
	 * MyTreeSetIterator implements the iterator interface. 
	 * 
	 */
	private class MyTreeSetIterator implements Iterator<T>{

		private Node<T> currentNode = head;
		private int expectedCount = modCount;
		private boolean removalValid = false;
		
		
		/**
		 * Checks that the currentNode isn't the last one. If it is, returns false.
		 * Also returns false if the set is empty. 
		 */
		public boolean hasNext() {
			if(size == 0)
				return false;
			else
				return currentNode.getNextLargest() != null;			
		}


		
		/**
		 * Allows the next element to be retrieved if the index is valid and no 
		 * modifications has been made other than those made by the iterator.
		 * Sets removalValid to true before returning the element. 
		 */
		public T next() {
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			if(expectedCount != modCount)
				throw new java.util.ConcurrentModificationException();
			removalValid = true;
			return currentNode.getNextLargest().getValue();
		}

		
		/**
		 * If the next element has been returned by next() and no modifications has been
		 * done other than changes made by the iterator, the element is removed. 
		 * After setting removalValid to false, forcing a call to next() before removing 
		 * another element, expectedCount is incremented as is modCount in the list.  
		 */
		public void remove() {
			if(!removalValid)
				throw new IllegalStateException();
			if(expectedCount != modCount)
				throw new java.util.ConcurrentModificationException();
			removalValid = false;
			++expectedCount;
			MyTreeSet.this.remove(currentNode.getValue());
		}
		
	} // MyTreeSetIterator
	
} // MyTreeSet
