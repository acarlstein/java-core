package threading.multithreading.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;
import threading.ThreadingUtil;
import threading.multithreading.runnable.SleepyTimerWithInterfaceManager;

public class ExecutorServiceTest extends TestCase
{
	ExecutorSleepyTimerManager executorSleepyTimerManager;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		executorSleepyTimerManager = new ExecutorSleepyTimerManager();
	}

	@Test
	public void testSleepForSingleThread(){
		long startTime = System.currentTimeMillis();
		executorSleepyTimerManager.sleepSingleThreadFor(5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

	@Test
	public void testSleepForFixedThreads(){
		long startTime = System.currentTimeMillis();
		executorSleepyTimerManager.sleepWithFixedThreadsFor(5, 5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

	@Test
	public void testSleepForThreadPool(){
		long startTime = System.currentTimeMillis();
		executorSleepyTimerManager.sleepWithThreadPoolFor(5, 5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

	@Test
	public void testSleepForFuture()
	throws Exception
	{
		long startTime = System.currentTimeMillis();
		executorSleepyTimerManager.sleepWithFutureFor(5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

}
