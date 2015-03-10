package treeset;

import static org.junit.Assert.*;

import org.junit.Test;

import treeset.MyTreeSet;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
	public void testContainsComparable(){
		Random rnd = new Random();
		TreeSet<Integer> oracle = new TreeSet<Integer>();
		MyTreeSet<Integer> myTreeSet = new MyTreeSet<Integer>();
		
		for(int i = 0; i < 1000; ++i){
			Integer randomInt = rnd.nextInt(1000);
			oracle.add(randomInt);
			myTreeSet.add(randomInt);
			
			//if(rnd.nextBoolean()){
				//assertEquals(oracle.size(), myTreeSet.size());				
			//}
		}
		for(Integer in : oracle){
			assertTrue(myTreeSet.contains(in));
		}
		//assertEquals(oracle.size(), myTreeSet.size());
	}

}
