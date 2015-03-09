
public class Node<T> {

	T value;
	private Node<T> leftChild;
	private Node<T> rightChild;
	
	private Node<T> nextSmallest;
	private Node<T> nextLargest;
	
	
	Node(T value){
		this.value = value;
	}
	
	Node(T value, Node<T> nextSmallest, Node<T> nextLargest){
		this.value = value;
		this.nextSmallest = nextSmallest;
		this.nextLargest = nextLargest;
		
		leftChild = null;
		rightChild = null;
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
	
	Node<T> getNextSmallest(){
		return nextSmallest;
	}
	
	void setNextSmallest(Node<T> nextSmallest){
		this.nextSmallest = nextSmallest;
	}
	
	Node<T> getNextLargest(){
		return nextLargest;
	}
	
	void setNextLargest(Node<T> nextLargest){
		this.nextLargest = nextLargest;
	}
	
	
}




