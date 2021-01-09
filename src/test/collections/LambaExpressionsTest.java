package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Lambas expressions are introduced in Java 8
 */
public class LambaExpressionsTest extends TestCase
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
	public void testLambaForEach(){
		List<SimpleCollections> list = new ArrayList<>();
		list.add(new SimpleCollections(0));
		list.add(new SimpleCollections(1));
		list.add(new SimpleCollections(2));
		list.add(new SimpleCollections(3));

		list.forEach(obj -> assertTrue(obj.getIdentifier() > -1));

		AtomicInteger counter = new AtomicInteger(0);
		list.forEach(obj -> {
			if (obj.getIdentifier() > 0) counter.getAndIncrement();
		} );
		assertTrue(counter.get() == 3);
	}

	@Test
	public void testRemoveIf(){
		List<SimpleCollections> list = new ArrayList<>();
		list.add(new SimpleCollections(1));
		list.add(new SimpleCollections(2));
		list.add(new SimpleCollections(3));

		list.removeIf(obj -> obj.getIdentifier() == 2);
		assertTrue(list.size() == 2);
		assertTrue(list.contains(new SimpleCollections(1)));
		assertTrue(list.contains(new SimpleCollections(3)));
		assertFalse(list.contains(new SimpleCollections(2)));
	}

	@Test
	public void testSortMethodReference(){
		List<String> firstList = new ArrayList(){{
			add("A");
			add("D");
			add("E");
			add("B");
			add("F");
			add("C");
		}};

		List<String> secondList = new ArrayList<>(firstList);

		// Java 8's Method Reference
		firstList.replaceAll(str -> str.toLowerCase());

		// Java 8's using Method Reference with double colon operator
		secondList.replaceAll(String::toLowerCase);

		assertEquals(firstList, secondList);
	}

}
