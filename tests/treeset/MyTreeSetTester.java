package treeset;

import static org.junit.Assert.*;

import org.junit.Test;


public class MyTreeSetTester {

	MyTreeSet<Integer> myTree = new MyTreeSet<Integer>();
	
	@Test
	public void testAddElement() {
		assertFalse(myTree.contains(5));
		assertEquals(0, myTree.size());
		myTree.add(5);
		assertTrue(myTree.contains(5));
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

}
