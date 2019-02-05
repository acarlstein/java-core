package threading.multithreading.runnable;

public class SleepyTimerWithInterfaceManager
{
	private static int ONE_SEC = 1000;

	public void sleepFor(int seconds) {
		for (int i = 0; i < seconds; ++i){
			new Thread(new SleepyTimerWithInterface(ONE_SEC)).start();
		}
	}

}
