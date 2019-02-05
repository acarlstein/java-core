package threading.multithreading.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import threading.multithreading.runnable.SleepyTimerWithInterface;

public class ExecutorSleepyTimerManager
{
	private static int ONE_SEC = 1000;

	/**
	 * There may be a problem in this method... can you spot it?
	 */
	public void sleepSingleThreadFor(int seconds){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i = 0; i < seconds; ++i)
		{
			executorService.execute(new SleepyTimerWithInterface(ONE_SEC));
		}
		executorService.shutdown();
	}

	public void sleepWithFixedThreadsFor(int seconds, int numberOfThreads) {
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

		// Creating one thread per second
		for(int i = 0; i < seconds; ++i)
		{
			executorService.execute(new SleepyTimerWithInterface(ONE_SEC));
		}
		executorService.shutdown();
	}

	public void sleepWithThreadPoolFor(int seconds, int poolSize){
		ExecutorService executorService = Executors.newScheduledThreadPool(poolSize);

		// Creating one thread per second
		for(int i = 0; i < seconds; ++i)
		{
			executorService.execute(new SleepyTimerWithInterface(ONE_SEC));
		}
		executorService.shutdown();
	}

	public void sleepWithFutureFor(int seconds)
	throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Future> futures = new ArrayList<>();

		// Creating one thread per second
		for(int i = 0; i < seconds; ++i){
			futures.add(executorService.submit(new SleepyTimerWithInterface(ONE_SEC)));
		}
		for(Future future: futures){
			if (future.get() != null){
				throw new Exception("One of the tasks didn't finished correctly");
			}
		}
	}

}
