package threading.multithreading.callable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ParallelMatrixRotation
{

	int numberOfThreads;

	ExecutorService executorService;

	public ParallelMatrixRotation(int numberOfThreads){
		this.numberOfThreads = numberOfThreads;

	}

	public List<Integer[][]> rotateMatricesClockwise(List<Integer[][]> matrices){

		int[] numbers = new int[10];

		int[] newNumbers = new int[20];

		Collection<Callable<Integer[][]>> tasks = new ArrayList<Callable<Integer[][]>>(matrices.size());

		for(int i = 0; i < matrices.size(); ++i){
			tasks.add(new RotateMatrixClockwise(matrices.get(i)));
		}

		List<Integer[][]> valuesReturned = new ArrayList<Integer[][]>(matrices.size());

		List<Future<Integer[][]>> returnedResults = null;
		try
		{
			executorService = Executors.newFixedThreadPool(numberOfThreads);
			returnedResults = executorService.invokeAll(tasks, 2L, TimeUnit.SECONDS);
			for (final Future task : returnedResults) {
				if (!task.isCancelled())
				{
					valuesReturned.add((Integer[][]) task.get(0, TimeUnit.SECONDS));
				}
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
		}
		finally{
			// do this here otherwise you will block and wait for others
			executorService.shutdown();
		}

		return valuesReturned;
	}

	public List<Integer[][]> rotateMatricesCounterClockwise(List<Integer[][]> matrices){

		Collection<Callable<Integer[][]>> tasks = new ArrayList<Callable<Integer[][]>>(matrices.size());

		for(int i = 0; i < matrices.size(); ++i){
			tasks.add(new RotateMatrixCounterClockwise(matrices.get(i)));
		}

		List<Integer[][]> valuesReturned = new ArrayList<Integer[][]>(matrices.size());

		List<Future<Integer[][]>> returnedResults = null;
		try
		{
			executorService = Executors.newFixedThreadPool(numberOfThreads);
			returnedResults = executorService.invokeAll(tasks, 2L, TimeUnit.SECONDS);
			for (final Future task : returnedResults) {
				if (!task.isCancelled())
				{
					valuesReturned.add((Integer[][]) task.get(1L, TimeUnit.SECONDS));
				}
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
		}
		finally{
			// do this here otherwise you will block and wait for others
			executorService.shutdown();
		}


		return valuesReturned;
	}

}
