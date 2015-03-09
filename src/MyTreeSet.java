
import java.util.Iterator;
import java.util.Comparator;
import java.util.Stack;


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
public class MyTreeSet<T> {

	private Node<T> root;
	private Node<T> head;
	private Node<T> tail;
	
	private int size = 0;
	private Comparator<T> comparator;
	
	public void add(T element){
		
	}
	
	
	
	public boolean contains(T element){
		if(element == null){
			throw new NullPointerException("Element can't be null.");
		}
		if(root == null){
			throw new NullPointerException("Tree is empty.");
		}
		if(comparator == null)
			return containsComparable(root, element);
		else
			return containsComparator(root, element);
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
	
	
	public void remove(T element){
		
	}
	
	public int size(){
		return size;
	}
	
	public Iterator<T> iterator(){
		
	}
} // MyTreeSet
