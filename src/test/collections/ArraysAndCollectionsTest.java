package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;

public class ArraysAndCollectionsTest extends TestCase
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
	public void testGetArrayFromCollection(){
		List list = new ArrayList();
		list.add(new SimpleCollections(1));
		list.add(new SimpleCollections(2));
		list.add(new SimpleCollections(3));

		Object[] arrayOfObjects = list.toArray();

		SimpleCollections[] simpleCollectionsArray = new SimpleCollections[3];
		simpleCollectionsArray =  (SimpleCollections[]) list.toArray(simpleCollectionsArray);

		assertTrue(Arrays.equals(arrayOfObjects, simpleCollectionsArray));

		assertEquals(arrayOfObjects[0], simpleCollectionsArray[0]);
		assertEquals(arrayOfObjects[1], simpleCollectionsArray[1]);
		assertEquals(arrayOfObjects[2], simpleCollectionsArray[2]);
	}

	@Test
	public void testGetCollectionFromArray(){
		SimpleCollections[] simpleCollectionsArray = {
			new SimpleCollections(1),
			new SimpleCollections(2),
			new SimpleCollections(3)
		};

		List list = Arrays.asList(simpleCollectionsArray);

		assertTrue(Arrays.equals(simpleCollectionsArray, list.toArray()));

		assertEquals(simpleCollectionsArray[0], list.get(0));
		assertEquals(simpleCollectionsArray[1], list.get(1));
		assertEquals(simpleCollectionsArray[2], list.get(2));
	}

}
