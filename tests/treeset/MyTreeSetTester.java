package treeset;

import static org.junit.Assert.*;

import org.junit.Test;

import treeset.MyTreeSet;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

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
			
			assertEquals(myTree.size(), myTree.getListSize());
			assertEquals(oracle.size(), myTree.getListSize());
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
	public void testContainsComparable(){
		Random rnd = new Random();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		MyTreeSet<Integer> myTreeSet = new MyTreeSet<Integer>();
		
		for(int i = 0; i < 1000; ++i){
			Integer randomInt = rnd.nextInt(1000);
			oracle.add(randomInt);
			myTreeSet.add(randomInt);
			
			if(rnd.nextBoolean()){
				//assertEquals(oracle.size(), myTreeSet.size());
				for(Integer in : oracle){
					assertTrue(myTreeSet.contains(in));
				}
			}
		}
		
		//assertEquals(oracle.size(), myTreeSet.size());
	}

}
