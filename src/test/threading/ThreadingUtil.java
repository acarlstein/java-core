package threading;

import java.util.concurrent.TimeUnit;

public class ThreadingUtil
{
	public static String parseInterval(long interval){
		final long hr = TimeUnit.MILLISECONDS.toHours(interval);
		final long min = TimeUnit.MILLISECONDS.toMinutes(interval) % 60;
		final long sec = TimeUnit.MILLISECONDS.toSeconds(interval) % 60;
		final long ms = TimeUnit.MILLISECONDS.toMillis(interval) % 1000;
		return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
	}
}
