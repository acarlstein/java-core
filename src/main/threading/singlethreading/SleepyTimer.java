package threading.singlethreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SleepyTimer
{
	private long sleepMilliseconds;

	public SleepyTimer(long sleepMilliseconds){
		this.sleepMilliseconds = sleepMilliseconds;
	}

	public void sleep()
	throws InterruptedException {
		sleepUsingLockSupport();
	}

	// While TimeUnit.sleep() calls internally Thread.sleep(), it is more human readable since you can
	// indicate HOURS, MINUTES, SECONDS, etc.
	// For example: TimeUnit.MINUTE.sleep(3) == Thread.sleep(180000) where 180000 are in milliseconds.
	public void sleepUsingTimeUnit()
	throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(sleepMilliseconds);
	}

	public void sleepUsingThread()
	throws InterruptedException {
		Thread.sleep(sleepMilliseconds);
	}

	public void sleepUsingBlockingQueue()
	throws InterruptedException{
		new ArrayBlockingQueue(1).poll(sleepMilliseconds, TimeUnit.MILLISECONDS);
	}

	public void sleepUsingLockSupport(){
		LockSupport.parkNanos(1_000_000 * sleepMilliseconds);
	}

}
