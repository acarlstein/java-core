package threading.multithreading.thread;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;
import threading.ThreadingUtil;

public class ThreadTest extends TestCase
{
	ThreadSleepyTimerManager threadSleepyTimerManager;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		threadSleepyTimerManager = new ThreadSleepyTimerManager();
	}

	@Test
	public void testSleepFor()
	throws InterruptedException {
		long startTime = System.currentTimeMillis();
		threadSleepyTimerManager.sleepFor(5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

}
