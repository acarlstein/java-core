package threading.multithreading.runnable;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;
import threading.ThreadingUtil;

public class RunnableInterfaceTest extends TestCase
{
	SleepyTimerWithInterfaceManager sleepyTimerWithInterfaceManager;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		sleepyTimerWithInterfaceManager = new SleepyTimerWithInterfaceManager();
	}

	@Test
	public void testSleepFor(){
		long startTime = System.currentTimeMillis();
		sleepyTimerWithInterfaceManager.sleepFor(5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + ThreadingUtil.parseInterval(diff));
	}

}
