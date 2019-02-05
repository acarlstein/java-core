package threading.multithreading.callable;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;
import threading.ThreadingUtil;

public class CallableTest extends TestCase
{
	int numberOfMatrices = 600000;
	List<Integer[][]> matrices;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		matrices = getListOfMatrices(numberOfMatrices);
	}

	private List<Integer[][]> getListOfMatrices(int numberOfMatrices){
		List<Integer[][]> matrices = new ArrayList<Integer[][]>();
		for(int i = 0; i < numberOfMatrices; ++i){
			matrices.add(new Integer[][] {
				{ 1, 2, 3, 4, 5 },
				{ 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15 }
			});
		}
		return matrices;
	}

	@Test
	public void testRotateMatricesLinearly(){
		long startTime = System.currentTimeMillis();
		List<Integer[][]> matricesRotatedClockwise = rotateMatricesClockwise(matrices);
		List<Integer[][]> matricesRotatedCounterClockwise = rotateMatricesCounterClockwise(matricesRotatedClockwise);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Linearly Run for " + ThreadingUtil.parseInterval(diff));
		for (int i = 0; i < numberOfMatrices; ++i){
			assertTrue(MatrixUtils.equal(matrices.get(i), matricesRotatedCounterClockwise.get(i)));
		}
	}

	private List<Integer[][]> rotateMatricesClockwise(List<Integer[][]> matrices){
		final List<Integer[][]> rotatedMatrices = new ArrayList<>();
		for(int i = 0; i < matrices.size(); ++i){
			rotatedMatrices.add(MatrixUtils.rotateClockwise(matrices.get(i)));
		}
		return rotatedMatrices;
	}

	private List<Integer[][]> rotateMatricesCounterClockwise(List<Integer[][]> matrices){
		final List<Integer[][]> rotatedMatrices = new ArrayList<>();
		for(int i = 0; i < matrices.size(); ++i){
			rotatedMatrices.add(MatrixUtils.rotateCounterClockwise(matrices.get(i)));
		}
		return rotatedMatrices;
	}

	@Test
	public void testRotateMatricesParallely(){
		ParallelMatrixRotation pmr = new ParallelMatrixRotation(50);
		long startTime = System.currentTimeMillis();
		List<Integer[][]> matricesRotatedClockwise = pmr.rotateMatricesClockwise(matrices);
		List<Integer[][]> matricesRotatedCounterClockwise = pmr.rotateMatricesCounterClockwise(matricesRotatedClockwise);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Parallely Run for " + ThreadingUtil.parseInterval(diff));
		for (int i = 0; i < numberOfMatrices; ++i){
			assertTrue(MatrixUtils.equal(matrices.get(i), matricesRotatedCounterClockwise.get(i)));
		}
	}

}
