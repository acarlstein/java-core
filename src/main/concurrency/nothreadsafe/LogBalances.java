package concurrency.nothreadsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * No encapsulation because I am lazy
 */
public class LogBalances
{
	public List<Integer> startBalances;
	public List<Integer> endBalances;

	public List<String> startMessages;
	public List<String> endMessages;

	public LogBalances(){
		startBalances = new ArrayList<>(600);
		endBalances = new ArrayList<>(600);

		startMessages = new ArrayList<>(600);
		endMessages = new ArrayList<>(600);
	}
}
