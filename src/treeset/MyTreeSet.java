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
public class MyTreeSet<T extends Comparable<T> > implements Iterable<T>{

	private Node<T> root;
	private Node<T> head = new Node<T>(null);
	private Node<T> tail = new Node<T>(null);
	
	private int size = 0;
	private Comparator<T> comparator;
	private int modCount = 0;
	
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
			/*head.setNextLargest(root);
			root.setNextSmallest(head);*/ //TODO CLEAN!!
			
			addNewNodeToList(root, tail);
			/*root.setNextLargest(tail);
			tail.setNextSmallest(root);*/
			++size;
		} else {
			// TODO add compartor
			if ( add(element, root) ){
				++size;
				++modCount;
			}
		}
	} // add
	
	/**
	 * Auxiliary method to add. Finds and adds the element to the correct 
	 * location in the supplied subtree. Doesn't add the element if it already
	 * exist in the set.
	 *  
	 * @param element
	 * - the element to add to the set.
	 * @param subroot
	 * - the root of the subtree in which to add the element.
	 * @return
	 * true if the element was added to the set.
	 */
	private boolean add(T element, Node<T> subroot){
		boolean valueAdded = true;
		
		T currVal = subroot.getValue();
		if (currVal.compareTo(element) == 0){
			valueAdded = false;
			
		} else if (currVal.compareTo(element) > 0){
			Node<T> leftChild = subroot.getLeftChild();
			if (leftChild == null){
				leftChild = new Node<T>(element);
				subroot.setLeftChild( leftChild );
				addNewNodeToList(leftChild, subroot);
				
			} else {
				add(element, leftChild);
				/*Node<T> nextSmaller = findLargest(leftChild);
				addNewToList(nextSmaller, subroot);*/ //TODO clean
			}
			
		} else if (currVal.compareTo(element) < 0){
			Node<T> rightChild = subroot.getRightChild();
			if (rightChild == null){
				rightChild = new Node<T>(element);
				subroot.setRightChild( rightChild );
				addNewNodeToList(subroot, rightChild);
				
			} else {
				add(element, rightChild);
				/*Node<T> nextLarger = findSmallest(rightChild);
				addNewToList(subroot, nextLarger);*/ //TODO clean
			}
			
		} 
		return valueAdded;
	} // add
	
	/*private Node<T> findSmallest(Node<T> subroot){
		Node<T> smallest = subroot;
		
		Node<T> leftChild = subroot.getLeftChild();
		if (leftChild != null){
			smallest = findSmallest(leftChild);
		}
		
		return smallest;
	} // findSmallest*/
	
	/*private Node<T> findLargest(Node<T> subroot){
		Node<T> largest = subroot;
		
		Node<T> rightChild = subroot.getRightChild();
		if (rightChild != null){
			largest = findSmallest(rightChild); // GRANSKA DENNA BIT!
		}
		
		return largest;
	} // findLargest*/
	
	private void addNewNodeToList(Node<T> smaller, Node<T> larger){
		if (smaller.getNextLargest() != null){
			Node<T> tmpNode = smaller.getNextLargest();
			
			larger.setNextLargest( tmpNode );
			tmpNode.setNextSmallest( larger );
		}
		if (larger.getNextSmallest() != null){
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
		
		if ( root != null){
			//TODO remove comparator
			root = remove(element, root);
		}
	} // remove
	
	/**
	 * Finds and removes the node containing the specified element via recursion. 
	 * An updated subtree is returned.
	 * 
	 * @param element
	 * - the element to remove.
	 * @param currNode
	 * - the subroot to search for the element.
	 * @return
	 * - subroot to the modified tree
	 */
	private Node<T> remove(T element, Node<T> currNode){
		Node<T> subroot = currNode;
		
		T currVal = currNode.getValue();
		if (currVal.compareTo(element) == 0){
	
			Node<T> leftChild = currNode.getLeftChild();
			Node<T> rightChild = currNode.getRightChild();
			
			if (leftChild != null && rightChild != null){
				T newVal = removeSmallestValue( rightChild );
				currNode.setValue( newVal );

			} else if (leftChild != null){
				subroot = leftChild;
				removeFromList( currNode );
				
			} else if (rightChild != null){
				subroot = rightChild;
				removeFromList( currNode );
				
			} else {
				subroot = null;
				removeFromList( currNode );
			}
			--size;
			--modCount;
			
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
		
		return subroot;
	} // remove
	
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
		Node<T> currNode = subroot;
		Node<T> nextNode = subroot;
		
		while ( nextNode.getLeftChild() != null){
			currNode = nextNode;
			nextNode = nextNode.getLeftChild();
		}
		
		if (nextNode.getRightChild() != null){
			currNode.setLeftChild( nextNode.getRightChild() );
		} else {
			currNode.setLeftChild(null);
		}
		removeFromList( nextNode );
		
		return nextNode.getValue();
	} // removeSmallestValue
	
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
	@Override
	public Iterator<T> iterator(){
		return new MyTreeSetIterator();
	} // iterator
	
	

	/**
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
		@Override
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
		@Override
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
		@Override
		public void remove() {
			if(!removalValid)
				throw new IllegalStateException();
			if(expectedCount != modCount)
				throw new java.util.ConcurrentModificationException();
			removalValid = false;
			++expectedCount;
			MyTreeSet.this.remove(currentNode.getValue());
		}
		
	}
	
} // MyTreeSet
