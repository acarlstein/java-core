package threading.multithreading.thread;

import threading.singlethreading.SleepyTimer;

public class ThreadSleepyTimer extends Thread
{

	SleepyTimer sleepyTimer;
	public ThreadSleepyTimer(long trySleepMilliseconds){
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
