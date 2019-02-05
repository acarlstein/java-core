package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import junit.framework.TestCase;
import org.junit.Test;

public class SimpleCollectionsTest extends TestCase
{

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testArrayList(){
		// ArrayList implements the List runnable
		ArrayList arrayList = new ArrayList();

		arrayList.add("Alejandro");
		arrayList.add("Carlstein");

		assertTrue(arrayList.contains("Alejandro"));
		assertTrue(arrayList.contains("Carlstein"));
		assertFalse(arrayList.contains("Pepe"));
	}

	@Test
	public void testArrayListToString(){

		// Casting to String
		List list = new ArrayList();
		list.add("La");
		list.add("gilada");

		StringJoiner sj = new StringJoiner(" ");
		for(Object obj: list){
			sj.add(obj.toString());
		}

		assertEquals("La gilada", sj.toString());
	}

	@Test
	public void testArrayListGet(){
		// Strongly typed
		List<String> list = new ArrayList<String>();
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");

		// This will not work since it isn't a string
		//list.add(1);

		assertEquals("One", list.get(0));
		assertEquals("Two", list.get(1));
		assertEquals("Three", list.get(2));
		assertEquals("Four", list.get(3).toString());
	}

	@Test
	public void testArrayListDifferentObjects(){
		List list = new ArrayList();
		list.add(1);
		list.add("One");
		list.add(new SimpleCollections());

		assertTrue(list.get(0) instanceof Integer);
		assertTrue(list.get(1) instanceof String);
		assertTrue(list.get(2) instanceof SimpleCollections);

		assertEquals("1", list.get(0).toString());
		assertEquals("One", list.get(1));
		assertEquals(SimpleCollections.TAG, list.get(2).toString());
	}

	@Test
	public void testLinkedList(){
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add("Item1");
		linkedList.add("Item2");

		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.addAll(linkedList);

		assertEquals(linkedList.get(0), arrayList.get(0));
		assertEquals(linkedList.get(1), arrayList.get(1));
	}

	@Test
	public void testCustomEquals(){
		List firstList = new ArrayList();
		firstList.add(new SimpleCollections(1));
		firstList.add(new SimpleCollections(2));
		firstList.add(new SimpleCollections(3));

		List secondList = new LinkedList();
		secondList.add(new SimpleCollections(2));

		firstList.retainAll(secondList);

		// Remember, we are using our custom equals() method which use the value in the variable identifer
		// Retain 2 and remove 1 and 3
		assertEquals(1, firstList.size());
		assertTrue(firstList.get(0).equals(new SimpleCollections(2)));

		// Remove 2
		firstList.remove(new SimpleCollections(2));
		assertEquals(0, firstList.size());
	}


}
