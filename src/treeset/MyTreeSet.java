
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
public class MyTreeSet<T> {

	private Node<T> root;
	private Node<T> head;
	private Node<T> tail;
	
	private int size = 0;
	private Comparator<T> comparator;
	
	public void add(T element){
		if (root == null){
			root = new Node<T>();
		}
	}
	
	public void contains(T element){
		
	}
	
	public void remove(T element){
		
	}
	
	public int size(){
		return size;
	}
	
	public Iterator<T> iterator(){
		
	}
} // MyTreeSet
