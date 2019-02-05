package threading.multithreading.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort
{
	private final ForkJoinPool pool;

	private static class QuickSortTask extends RecursiveAction {
		private final int[] array;
		private final int low;
		private final int high;

		/**
		 * Creates a {@code HoarseSortTask} containing the array and the bounds of the array
		 *
		 * @param array the array to sort
		 * @param low the lower element to start sorting at
		 * @param high the non-inclusive high element to sort to
		 */
		protected QuickSortTask(int[] array, int low, int high) {
			this.array = array;
			this.low = low;
			this.high = high;
		}

		@Override
		protected void compute() {
			if (low < high){
				int partition = hoarsePartition(array, low, high);
				QuickSortTask left = new QuickSortTask(array, low, partition);
				QuickSortTask right = new QuickSortTask(array, partition + 1, high);
				invokeAll(left , right);
			}
		}

		private int hoarsePartition(int[] array,  int low, int high){

			int pivot = array[low];

			int newLow = low - 1;

			int newHigh = high + 1;

			while (newLow < newHigh){

				for(newLow++; array[newLow] < pivot; newLow++);
				for(newHigh--; array[newHigh] > pivot; newHigh--);

				if (newLow < newHigh){
					int temp = array[newLow];
					array[newLow] = array[newHigh];
					array[newHigh] = temp;
				}
			}
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return newHigh;
		}
	}

	/**
	 * Creates a {@code ParallelQuickSort} containing a ForkJoinPool with the indicated parallelism level
	 * @param parallelism the parallelism level used
	 */
	public ParallelQuickSort(int parallelism) {
		pool = new ForkJoinPool(parallelism);
	}

	/**
	 * Sorts all the elements of the given array using the ForkJoin framework
	 * @param array the array to sort
	 */
	public void sort(int[] array) {
		ForkJoinTask<Void> job = pool.submit(new QuickSortTask(array, 0, array.length - 1));
		job.join();
	}
}