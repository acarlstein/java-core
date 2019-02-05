package collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

import junit.framework.TestCase;
import org.junit.Test;

public class DictionariesTest extends TestCase
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
	public void testCommonMapMethods(){
		Map map = new HashMap<String, Integer>();

		map.put("One", 1);
		map.put("Two", 2);

		assertEquals(map.get("One"), 1);
		assertEquals(map.get("Two"), 2);

		Collection collection = map.values();
		assertTrue(collection.contains(1));
		assertTrue(collection.contains(2));

		Set<String> keys = map.keySet();
		assertTrue(keys.contains("One"));
		assertTrue(keys.contains("Two"));


	}

	@Test
	public void testMoreAdvanceMapMethods(){
		Map<String, Integer> map = new HashMap<>();

		// Java 8
		map.putIfAbsent("One", 1);
		map.putIfAbsent("Two", 2);

		// This would give you ambiguous because we obtain an Integer object but the number 1 is a primitive type.
		//assertEquals(map.get("One"), 1);

		assertEquals(map.get("One"), Integer.valueOf(1));
		assertEquals(map.get("Two"), Integer.valueOf(2));

		assertEquals(map.getOrDefault("Three", 3), Integer.valueOf(3));

		StringJoiner sj = new StringJoiner(", ");
		map.forEach((key, value) -> sj.add("['".concat(key).concat("':").concat(String.valueOf(value)).concat("]")));
		assertEquals("['One':1], ['Two':2]", sj.toString());
	}

	@Test
	public void testReplaceAll(){
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "ONE");
		map.put(2, "TWO");

		map.replaceAll((key, value) -> value.toLowerCase());

		StringJoiner sj = new StringJoiner(", ");
		map.forEach((key, value) -> sj.add("[".concat(String.valueOf(key)).concat(":'").concat(value).concat("']")));
		assertEquals("[1:'one'], [2:'two']", sj.toString());

	}

	@Test
	public void testReplaceAllUpperFirstLetter(){
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "oNe");
		map.put(2, "TwO");

		map.replaceAll((key, value) -> {
			String newValue = value.substring(0,1).toUpperCase() + value.substring(1).toLowerCase();
			return newValue;
		});

		StringJoiner sj2 = new StringJoiner(", ");
		map.forEach((key, value) -> sj2.add("[".concat(String.valueOf(key)).concat(":'").concat(value).concat("']")));
		assertEquals("[1:'One'], [2:'Two']", sj2.toString());
	}

	@Test
	public void testCommonSortedMapMethods(){
		SortedMap<Integer, String> sortedMap = new TreeMap();

		sortedMap.put(1, "One");
		sortedMap.put(2, "Two");
		sortedMap.put(3, "Three");
		sortedMap.put(4, "Four");
		sortedMap.put(5, "Five");

		assertEquals(sortedMap.firstKey(), Integer.valueOf(1));
		assertEquals(sortedMap.lastKey(), Integer.valueOf(5));

		SortedMap headMapExpected = sortedMap.subMap(1, 3);
		assertEquals("{1=One, 2=Two}", headMapExpected.toString());

		// headMap returns all keys less than specified key.
		SortedMap headMapResult = sortedMap.headMap(3);
		assertEquals("{1=One, 2=Two}", headMapResult.toString());

		assertEquals(headMapExpected, headMapResult);


		SortedMap tailMapExpected = sortedMap.subMap(3, 6);
		assertEquals("{3=Three, 4=Four, 5=Five}", tailMapExpected.toString());

		// tailMap returns all keys greater or equals to specified key (aka. it's inclusive)
		SortedMap tailMapResult = sortedMap.tailMap(3);
		assertEquals("{3=Three, 4=Four, 5=Five}", tailMapResult.toString());

		assertEquals(tailMapExpected, tailMapResult);
	}

}

