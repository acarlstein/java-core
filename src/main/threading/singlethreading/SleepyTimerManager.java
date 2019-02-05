package threading.singlethreading;

public class SleepyTimerManager
{
	public void sleepFor(int seconds){
		for (int i = 0; i < seconds; ++i){
			try
			{
				new SleepyTimer(1000).sleep();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}

}
