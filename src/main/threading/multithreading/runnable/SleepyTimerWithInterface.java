package threading.multithreading.runnable;

import threading.singlethreading.SleepyTimer;

public class SleepyTimerWithInterface implements Runnable
{

	SleepyTimer sleepyTimer;
	public SleepyTimerWithInterface(long trySleepMilliseconds){
		sleepyTimer = new SleepyTimer(trySleepMilliseconds);
	}

	@Override
	public void run(){
		try
		{
			sleepyTimer.sleep();
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}

}
