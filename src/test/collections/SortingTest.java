package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

import junit.framework.TestCase;
import org.junit.Test;

public class SortingTest extends TestCase
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

	//(a) Inorder (Left, Root, Right) : 4 2 5 1 3
	//(b) Preorder (Root, Left, Right) : 1 2 4 5 3
	//(c) Postorder (Left, Right, Root) : 4 5 2 3 1

	@Test
	public void testTreeSet(){
		// It sorts the elements in ascending order by default
		TreeSet<ClassComparable> treeSet = new TreeSet<>();

		treeSet.add(new ClassComparable(-1, "A"));
		treeSet.add(new ClassComparable(-3, "B"));
		treeSet.add(new ClassComparable(0, "C"));
		treeSet.add(new ClassComparable(2, "D"));
		treeSet.add(new ClassComparable(5, "E"));

		// Duplicates are ignored
		treeSet.add(new ClassComparable(5, "E"));

		StringJoiner sj = new StringJoiner(", ");
		treeSet.forEach(obj -> sj.add(obj.toString()));
		assertEquals("[B:-3], [A:-1], [C:0], [D:2], [E:5]", sj.toString());

		assertEquals("[B:-3]", treeSet.first().toString());
		assertEquals("[E:5]", treeSet.last().toString());

	}

	public void testReverseTreeSet(){
		// It sorts the elements in descending order due Comparator.reverseOrder
		TreeSet<ClassComparable> treeSet = new TreeSet<ClassComparable>(Comparator.reverseOrder());

		ClassComparable first = new ClassComparable(-1, "A");
		ClassComparable middle = new ClassComparable(0, "C");

		treeSet.add(first);
		treeSet.add(new ClassComparable(-3, "B"));
		treeSet.add(middle);
		treeSet.add(new ClassComparable(2, "D"));
		treeSet.add(new ClassComparable(5, "E"));

		StringJoiner sj = new StringJoiner(", ");
		treeSet.forEach(obj -> sj.add(obj.toString()));
		assertEquals("[E:5], [D:2], [C:0], [A:-1], [B:-3]", sj.toString());

		// Read README.md for exercise
	}

	@Test
	public void testSetTreeWithClassComparator(){
		TreeSet<ClassComparable> treeSet = new TreeSet<ClassComparable>(new ClassComparator());

		treeSet.add(new ClassComparable(-1, "A"));
		treeSet.add(new ClassComparable(-3, "B"));
		treeSet.add(new ClassComparable(0, "C"));
		treeSet.add(new ClassComparable(2, "D"));
		treeSet.add(new ClassComparable(5, "E"));

		StringJoiner sj = new StringJoiner(", ");
		treeSet.forEach(obj -> sj.add(obj.toString()));
		assertEquals("[B:-3], [A:-1], [C:0], [D:2], [E:5]", sj.toString());
	}

	@Test
	public void testSetTreeWithClassReverseComparator(){
		TreeSet<ClassComparable> treeSet = new TreeSet<ClassComparable>(new ClassReverseComparator());

		treeSet.add(new ClassComparable(-1, "A"));
		treeSet.add(new ClassComparable(-3, "B"));
		treeSet.add(new ClassComparable(0, "C"));
		treeSet.add(new ClassComparable(2, "D"));
		treeSet.add(new ClassComparable(5, "E"));

		StringJoiner sj = new StringJoiner(", ");
		treeSet.forEach(obj -> sj.add(obj.toString()));
		assertEquals("[E:5], [D:2], [C:0], [A:-1], [B:-3]", sj.toString());
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

		// Clone content
		List<String> secondList = new ArrayList<>(firstList);

		// Sort using Java 8's method references and lambas
		firstList.sort(Comparator.comparing(string -> string.toString()));

		// Same as above using the double colon operator
		//firstList.sort(Comparator.comparing(String::toString()));

		// Java 7's Collections API
		Collections.sort(secondList);

		assertEquals(firstList, secondList);

		firstList.sort(Comparator.comparing(string -> string.toString()).reversed());
		Collections.sort(secondList, Collections.reverseOrder());

		assertEquals(firstList, secondList);
	}

}
