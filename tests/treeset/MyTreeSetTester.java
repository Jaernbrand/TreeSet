package treeset;

import static org.junit.Assert.*;

import org.junit.Test;

import treeset.MyTreeSet;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class MyTreeSetTester {

	MyTreeSet<Integer> myTree = new MyTreeSet<Integer>();
	
	@Test(expected=NullPointerException.class)
	public void testAddNull() {
		myTree.add(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveNull() {
		myTree.add(5);
		myTree.remove(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsNull() {
		myTree.contains(null);
	}
	
	@Test
	public void testAddElement() {
		assertFalse(myTree.contains(5));
		assertEquals(0, myTree.size());
		myTree.add(5);
		assertTrue(myTree.contains(5));
		assertEquals(1, myTree.size());
	}
	
	@Test
	public void testAddDuplicate() {
		assertFalse(myTree.contains(5));
		assertEquals(0, myTree.size());
		myTree.add(5);
		assertTrue(myTree.contains(5));
		assertEquals(1, myTree.size());
		
		myTree.add(5);
		assertEquals(1, myTree.size());
	}
	
	@Test
	public void testRemoveElement() {
		assertFalse(myTree.contains(5));
		assertEquals(0, myTree.size());
		myTree.add(5);
		assertTrue(myTree.contains(5));
		assertEquals(1, myTree.size());
		myTree.remove(5);
		assertFalse(myTree.contains(5));
		assertEquals(0, myTree.size());
	}
	
	@Test
	public void testAddAndRemoveMultipleElements(){
		Integer[] input = {5, 6, 4, 7, 3, 8, 2, 9, 1, 10};
		Set<Integer> oracle = new TreeSet<Integer>();
		
		assertEquals(0, myTree.size());
		
		for (int i=0; i<input.length; ++i){
			myTree.add(input[i]);
			oracle.add(input[i]);
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
		
		for (int i=0; i<input.length; ++i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());
		}
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	@Test
	public void testAddAndRemoveMultipleRandomElements(){
		Random rnd = new Random();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		assertEquals(0, myTree.size());
		assertEquals(0, oracle.size());
		
		for (int i=0; i < 1000; ++i){
			Integer newVal = rnd.nextInt(1000);
			myTree.add(newVal);
			oracle.add(newVal);
			
			assertTrue( myTree.contains(newVal) );
			assertTrue( oracle.contains(newVal) );
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());
			
			Iterator<Integer> oracleIter = oracle.iterator();
			while (myTree.size() > 0 && rnd.nextBoolean() ){
				Integer tmpVal = oracleIter.next();
				myTree.remove(tmpVal);
				oracleIter.remove();
				assertFalse(myTree.contains(tmpVal) );
				assertFalse(oracle.contains(tmpVal) );
			}
			
			assertEquals(oracle.size(), myTree.size());
			
			for (Integer oracleVal : oracle){
				assertTrue(myTree.contains(oracleVal) );
			}
			
			assertEquals(oracle.toString(), myTree.toString());
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	
	private void addInputArrayToTree(Integer[] input, MyTreeSet<Integer> tree, TreeSet<Integer> oracle){
		for (int i=0; i < input.length; ++i){
			tree.add(input[i]);
			oracle.add(input[i]);
			assertTrue(tree.contains(input[i]));
			assertTrue(oracle.contains(input[i]));
			
			assertEquals(oracle.size(), tree.size());
			assertEquals(oracle.toString(), tree.toString());	
		}
	}
	
	@Test
	public void testAddAndRemoveRightHeavyTree(){
		Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	@Test
	public void testAddAndRemoveLeftHeavyTree(){
		Integer[] input = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	@Test
	public void testAddAndRemoveRootUntilEmptyTree(){
		Integer[] input = {100, 50, 150, 120, 200, 220, 250, 300, 320, 350};
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
		
		addInputArrayToTree(input, myTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			myTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(myTree.contains(input[i]));
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());	
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	@Test
	public void testAddAndRemoveMultipleElementsComparator(){
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator());
		Integer[] input = {5, 6, 4, 7, 3, 8, 2, 9, 1, 10};
		Set<Integer> oracle = new TreeSet<Integer>(new IntegerComparator());
		
		assertEquals(0, myTree.size());
		
		for (int i=0; i<input.length; ++i){
			compTree.add(input[i]);
			oracle.add(input[i]);
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
		
		for (int i=0; i<input.length; ++i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());
		}
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testAddAndRemoveRightHeavyTreeComparator(){
		Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator());
		TreeSet<Integer> oracle = new TreeSet<Integer>(new IntegerComparator());
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testAddAndRemoveLeftHeavyTreeComparator(){
		Integer[] input = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator());
		TreeSet<Integer> oracle = new TreeSet<Integer>(new IntegerComparator() );
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testAddAndRemoveMultipleRandomElementsAndWithComparator(){
		Random rnd = new Random();
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator());
		TreeSet<Integer> oracle = new TreeSet<Integer>( new IntegerComparator());
		
		ArrayList<Integer> addHistory = new ArrayList<Integer>();
		
		assertEquals(0, compTree.size());
		assertEquals(0, oracle.size());
		
		for (int i=0; i < 1000; ++i){
			Integer newVal = rnd.nextInt(1000);
			compTree.add(newVal);
			oracle.add(newVal);
			
			addHistory.add(newVal);
			
			assertTrue( compTree.contains(newVal) );
			assertTrue( oracle.contains(newVal) );
			
			if (oracle.size() != compTree.size()){
				int forDebuggingPurpuses = -1;
			}
			assertEquals(oracle.toString(), compTree.toString());
			assertEquals(oracle.size(), compTree.size());
			
			
			Iterator<Integer> oracleIter = oracle.iterator();
			while (compTree.size() > 0 && rnd.nextBoolean() ){
				Integer tmpVal = oracleIter.next();
				compTree.remove(tmpVal);
				oracleIter.remove();
				assertFalse(compTree.contains(tmpVal) );
				assertFalse(oracle.contains(tmpVal) );
			}
			
			assertEquals(oracle.size(), compTree.size());
			
			for (Integer oracleVal : oracle){
				assertTrue(compTree.contains(oracleVal) );
			}
			
			assertEquals(oracle.toString(), compTree.toString());
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testAddDuplicateComparator(){
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		assertEquals(0, compTree.size());
		assertEquals(0, oracle.size());
		assertEquals(oracle.toString(), compTree.toString());
		
		compTree.add(5);
		oracle.add(5);
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
		assertTrue(compTree.contains(5));
		assertTrue(oracle.contains(5));
		
		compTree.add(5);
		oracle.add(5);
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testRecreatingFailAddAndRemoveRandomComparator(){
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator() );
		Integer[] input = {59, 271, 11, 369, 169, 7, 392, 301, 645, 864, 392};
		
		for (int i=0; i < input.length; ++i){
			compTree.add(input[i]);
			assertTrue(compTree.contains(input[i]));
		}
		
		assertEquals(input.length-1, compTree.size());
	}
	
	@Test
	public void testAddAndRemoveRootUntilEmptyTreeComparator(){
		Integer[] input = {100, 50, 150, 120, 200, 220, 250, 300, 320, 350};
		MyTreeSet<Integer> compTree = new MyTreeSet<Integer>(new IntegerComparator());
		TreeSet<Integer> oracle = new TreeSet<Integer>(new IntegerComparator() );
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=0; i < input.length; ++i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
		
		addInputArrayToTree(input, compTree, oracle);
		
		for (int i=input.length-1; i >= 0; --i){
			compTree.remove(input[i]);
			oracle.remove(input[i]);
			assertFalse(compTree.contains(input[i]));
			
			assertEquals(oracle.size(), compTree.size());
			assertEquals(oracle.toString(), compTree.toString());	
		}
		
		assertEquals(oracle.size(), compTree.size());
		assertEquals(oracle.toString(), compTree.toString());
	}
	
	@Test
	public void testIteratorWithRandomIntegers(){
		Random rnd = new Random();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		assertEquals(0, myTree.size());
		assertEquals(0, oracle.size());
		
		for (int i=0; i < 500; ++i){
			Integer newVal = rnd.nextInt(1000);
			myTree.add(newVal);
			oracle.add(newVal);
			
			assertTrue( myTree.contains(newVal) );
			assertTrue( oracle.contains(newVal) );
			
			assertEquals(oracle.size(), myTree.size());
			assertEquals(oracle.toString(), myTree.toString());
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
		
		Iterator<Integer> myIter = myTree.iterator();
		for (Integer e : oracle){ // Fungerar bara om oraklets iterator går från minsta till största.
			Integer tmp = myIter.next();
			assertEquals(e, tmp);
		}
		
		assertEquals(oracle.size(), myTree.size());
		assertEquals(oracle.toString(), myTree.toString());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testIteratorRemoveWithoutCallingNext(){
		myTree.add(5);
		assertEquals(1, myTree.size());
		Iterator<Integer> myIter = myTree.iterator();
		myIter.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testOneToManyNextOnIterator(){
		myTree.add(5);
		assertEquals(1, myTree.size());
		Iterator<Integer> myIter = myTree.iterator();
		for (int i=0; i <= myTree.size(); ++i){
			myIter.next();
		}
	}
	
	@Test
	public void testEmptyIterator(){
		Iterator<Integer> myIter = myTree.iterator();
		assertFalse(myIter.hasNext());
	}
	
	@Test
	public void testContainsComparable(){
		Random rnd = new Random();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		MyTreeSet<Integer> myTreeSet = new MyTreeSet<Integer>();
		
		for(int i = 0; i < 1000; ++i){
			Integer randomInt = rnd.nextInt(1000);
			oracle.add(randomInt);
			myTreeSet.add(randomInt);
			
			if(rnd.nextBoolean()){
				assertEquals(oracle.size(), myTreeSet.size());
				for(Integer in : oracle){
					assertTrue(myTreeSet.contains(in));
				}
			}
		}
		assertEquals(oracle.size(), myTreeSet.size());
	}
	
	
	private MyTreeSet<Integer> get1000randomIntegers(){
		Random rnd = new Random();
		MyTreeSet<Integer> myTreeSet = new MyTreeSet<Integer>();
		for(int i = 0; i < 1000; ++i){
			Integer randomInt = rnd.nextInt(1000);
			myTreeSet.add(randomInt);
		}
		return myTreeSet;
	}
	
	private MyTreeSet<Integer> add10Ints(MyTreeSet<Integer> treeSet){
		for(int i = 0; i < 10; ++i){
			treeSet.add(i);
		}
		return treeSet;
	}
	
	private TreeSet<Integer> add10Ints(TreeSet<Integer> treeSet){
		for(int i = 0; i < 10; ++i){
			treeSet.add(i);
		}
		return treeSet;
	}
	
	
	@Test
	public void testIterator(){
		MyTreeSet<Integer> treeSet = new MyTreeSet<Integer>();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		
		Iterator<Integer> iter = treeSet.iterator();
		Iterator<Integer> oracleIter = oracle.iterator();
		
		assertFalse(iter.hasNext()); 		//empty
		assertFalse(oracleIter.hasNext());  //empty

		oracle = add10Ints(oracle);
		treeSet = add10Ints(treeSet);
		assertEquals(oracle.size(), treeSet.size());
		
		oracleIter = oracle.iterator();
		iter = treeSet.iterator();
		
		//emptying treeSet
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
		//emptying oracle
		while(oracleIter.hasNext()){
			oracleIter.next();
			oracleIter.remove();
		}
		assertEquals(oracle.size(), treeSet.size());	
	}
	
	@Test (expected=ConcurrentModificationException.class)
	public void testIteratorConcurrentException(){
		
		MyTreeSet<Integer> treeSet = new MyTreeSet<Integer>();
		Iterator<Integer> iter = treeSet.iterator();
		treeSet = add10Ints(treeSet);
		iter.hasNext();
		iter.next();
	}
	
	@Test (expected=ConcurrentModificationException.class)
	public void testIteratorConcurrentExceptionAtRemove(){
		
		MyTreeSet<Integer> treeSet = new MyTreeSet<Integer>();
		Iterator<Integer> iter = treeSet.iterator();
		treeSet = add10Ints(treeSet);
		iter.next();
		iter.remove();
	}
	
	@Test (expected=NoSuchElementException.class)
	public void testIteratorNoSuchElementException(){
		
		MyTreeSet<Integer> treeSet = new MyTreeSet<Integer>();
		Iterator<Integer> iter = treeSet.iterator();
		iter.hasNext();
		iter.next();
	}
	
	
	@Test (expected=IllegalStateException.class)
	public void testIteratorIllegalStateException(){
		
		MyTreeSet<Integer> treeSet = new MyTreeSet<Integer>();
		Iterator<Integer> iter = treeSet.iterator();
		iter.hasNext();
		iter.remove();
	}
	
		
}


