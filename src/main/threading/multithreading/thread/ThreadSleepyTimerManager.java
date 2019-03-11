package threading.multithreading.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadSleepyTimerManager
{
	private static int ONE_SEC = 1000;

	public void sleepFor(int seconds)
	throws InterruptedException{
		List<ThreadSleepyTimer> list = new ArrayList<>();
		for (int i = 0; i < seconds; ++i){
			list.add(new ThreadSleepyTimer(ONE_SEC).start());
		}
		for(Thread thread : list){
			thread.join();
		}
	}

}
