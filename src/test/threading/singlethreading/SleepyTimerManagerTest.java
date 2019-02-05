package threading.singlethreading;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;

public class SleepyTimerManagerTest extends TestCase
{
	SleepyTimerManager sleepyTimerManager;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		sleepyTimerManager = new SleepyTimerManager();
	}

	@Test
	public void testSleepyTimerManager(){
		long startTime = System.currentTimeMillis();
		sleepyTimerManager.sleepFor(5);
		long diff = System.currentTimeMillis() - startTime;
		System.out.println("Run for " + parseInterval(diff));
	}

	private String parseInterval(long interval){
		final long hr = TimeUnit.MILLISECONDS.toHours(interval);
		final long min = TimeUnit.MILLISECONDS.toMinutes(interval) % 60;
		final long sec = TimeUnit.MILLISECONDS.toSeconds(interval) % 60;
		final long ms = TimeUnit.MILLISECONDS.toMillis(interval) % 1000;
		return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
	}

}
