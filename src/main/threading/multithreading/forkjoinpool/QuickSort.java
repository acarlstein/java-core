package threading.multithreading.forkjoinpool;


/**
 * This Quicksort use Hoarse partition
 */
public class QuickSort
{
	public QuickSort(int[] array, int low, int high){
		QuickSortTask(array, low, high - 1);
	}

	public void QuickSortTask(int[] array, int low, int high){
		if (low < high){
			int partition = hoarsePartition(array, low, high);
			QuickSortTask(array, low, partition );
			QuickSortTask(array, partition + 1, high);
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
