package threading.multithreading.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import threading.ThreadingUtil;

public class ForkJoinPoolTest extends TestCase
{
	// Around 2 minutes
	static final int NUMBER_OF_INTEGERS = 600;

	int[] array;
	int[] expectedArray;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		array = getArrayOfIntegers(NUMBER_OF_INTEGERS);
		expectedArray = getArrayOfIntegers(NUMBER_OF_INTEGERS);
	}

	@Test
	public void testQuickSort(){

		long startTime = System.currentTimeMillis();
		new QuickSort(array, 0, array.length);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Linearly Run for " + ThreadingUtil.parseInterval(diff));

		for (int i = 0; i < array.length; i++){
			assertTrue(array[i] == expectedArray[i]);
		}
	}

	@Test
	public void testParallelQuickSort(){

		ParallelQuickSort parallelQuickSort = new ParallelQuickSort(Runtime.getRuntime().availableProcessors());
		long startTime = System.currentTimeMillis();
		parallelQuickSort.sort(array);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Parallely Run for " + ThreadingUtil.parseInterval(diff));

		for (int i = 0; i < array.length; i++){
			assertTrue(array[i] == expectedArray[i]);
		}
	}

	private int[] getArrayOfIntegers(int size){
		int[] array = new int[size];
		for(int i = 0; i < size; ++i){
			array[i] = i;
		}
		return array;
	}

}
