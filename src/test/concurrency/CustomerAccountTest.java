package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import concurrency.nothreadsafe.CustomerAccount;
import concurrency.nothreadsafe.RewardsAccount;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerAccountTest extends TestCase
{
	@Test
	public void testCommonConcurrency()
	throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		RewardsAccount rewardsAccount = new RewardsAccount(100);
		CustomerAccount customerAccount = getCustomerAccountWithLogsFilled(rewardsAccount);

		// Running 1 thread and waiting for it to finish
		Future future = executorService.submit(customerAccount);
		if (future.get() != null){
			throw new Exception("One of the tasks didn't finished correctly");
		}

		executorService.shutdown();

		List<Integer> startBalances = customerAccount.logBalances.startBalances;
		List<Integer> endBalances = customerAccount.logBalances.endBalances;

		/**
		 * Number of threads: 1
		 * Number of times it increases +10: 10
		 * 200 = 100 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 */
		assertTrue(endBalances.contains(200));

		// stream() is "default" method introduced in Java 8
		int[] startBalanceArray = startBalances.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
		int[] expectedStartBalanceArray = {100, 110, 120, 130, 140, 150, 160, 170, 180, 190};
		Assert.assertArrayEquals(startBalanceArray, expectedStartBalanceArray);

		int[] endBalanceArray = endBalances.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
		int[] expectedEndBalanceArray = {110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
		Assert.assertArrayEquals(endBalanceArray, expectedEndBalanceArray);
	}

	private CustomerAccount getCustomerAccountWithLogsFilled(RewardsAccount rewardsAccount){
		return new CustomerAccount(rewardsAccount){
			@Override
			public void run(){
				for(int i = 0; i < 10; ++i)
				{
					logStart(i);
					rewardsAccount.addReward(10);
					logEnd(i);
				}
			}

			private void logStart(int index){
				logBalances.startMessages.add("Start: ".concat(Thread.currentThread().getName()).concat(" with ").concat(String.valueOf(rewardsAccount.getBalance())));
				logBalances.startBalances.add(index, rewardsAccount.getBalance());
			}

			private void logEnd(int index){
				logBalances.endBalances.add(index, rewardsAccount.getBalance());
				logBalances.endMessages.add("End: ".concat(Thread.currentThread().getName()).concat(" with ").concat(String.valueOf(rewardsAccount.getBalance())));
			}
		};
	}

	/**
	 * Due nature of the race issue this unit test must be run a couple of times until it fails.
	 * Some times, you will find out that the
	 */
	@Test
	public void testConcurrencyRaceIssue()
	throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(5, getCustomThreadFactory());
		RewardsAccount rewardsAccount = new RewardsAccount(100);
		CustomerAccount customerAccount = getCustomerAccountWithLogsFilled(rewardsAccount);

		List<Future> futures = new ArrayList<>();

		// Creating 5 threads
		for(int i = 0; i < 5; ++i){
			futures.add(executorService.submit(customerAccount));
		}
		for(Future future: futures){
			if (future.get() != null){
				throw new Exception("One of the tasks didn't finished correctly");
			}
		}

		executorService.shutdown();

		List<Integer> startBalances = customerAccount.logBalances.startBalances;
		assertEquals(startBalances.size(), 50);

		List<Integer> endBalances = customerAccount.logBalances.endBalances;

		/**
		 * The content items inside startBalances and endBalances is not in chronological order
		 * due the nature of a non-synchronized access we get scramble results
		 *
		 * In theory, we should get:
		 *  Number of threads: 5
		 *  Number of times it increases +10: 10
		 *  600 = 100 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 *            + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 *            + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 *            + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 *            + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10 + 10
		 *
		 * In reality, we get:
		 *  The highest value may round between 580 to 590 with the occasional 600
		 */
		assertTrue("We dont even have 580, 590 and/or 600!", endBalances.contains(580) || endBalances.contains(590) || endBalances.contains(600));

		/**
		 * Set your IDE to trigger the breakpoint when the condition of the assert it isn't meet:
		 *  !endBalances.contains(600);
		 */
		assertTrue("600 is missing?! My goodness! What a surprise!", endBalances.contains(600));

		/**
		 * You may find yourself without the correct numbers of balances.
		 * Set your IDE to trigger the breakpoint when the condition of the assert it isn't meet:
		 *   endBalances.size() != 50;
		 */
		assertEquals("We got less elements than we expected! What a surprise! (sarcasm)", endBalances.size(), 50);

	}

	private ThreadFactory getCustomThreadFactory(){
		return new ThreadFactory(){

			final AtomicLong count = new AtomicLong(0);
			@Override
			public Thread newThread(Runnable runnable)
			{
				Thread thread = new Thread(runnable);

				thread.setName("Thread #" + count.getAndIncrement());

				// We don't need to implement background services; therefore,
				// we don't need to produce daemon threads.
				thread.setDaemon(false);

				thread.setPriority(Thread.MAX_PRIORITY);

				return thread;
			}
		};
	}

	@Test
	public void testSynchronizedMethod()
	throws Exception{

		ExecutorService executorService = Executors.newFixedThreadPool(5, getCustomThreadFactory());
		RewardsAccount rewardsAccount = new RewardsAccount(100);
		CustomerAccount customerAccount = getCustomerAccountWithLogsFilled(rewardsAccount);

		List<Future> futures = new ArrayList<>();

		// Creating 5 threads
		for(int i = 0; i < 5; ++i){
			futures.add(executorService.submit(customerAccount));
		}
		for(Future future: futures){
			if (future.get() != null){
				throw new Exception("One of the tasks didn't finished correctly");
			}
		}

		executorService.shutdown();

		List<Integer> startBalances = customerAccount.logBalances.startBalances;
		assertEquals(startBalances.size(), 50);

		List<Integer> endBalances = customerAccount.logBalances.endBalances;

		Collections.sort(endBalances);

		assertTrue("600 is missing?! Something is wrong. Did you synchronized the method?", endBalances.contains(600));

		assertEquals(endBalances.size(), 50);

	}

	/**
	 * Hint: There is another method that needs to be synchronized. Which one is?
	 */
	private RewardsAccount getRewardsAccountWithSynchronizedMethod(int reward){
		return new RewardsAccount(reward){
			@Override
			public synchronized void addReward(int amount){
				balance += amount;
			}
		};
	}

	@Test
	public void testSynchronizedBlock()
	throws Exception{

		ExecutorService executorService = Executors.newFixedThreadPool(5, getCustomThreadFactory());
		RewardsAccount rewardsAccount = getRewardsAccountWithSynchronizedMethod(100);
		CustomerAccount customerAccount = getCustomerAccountWithSynchronizedBlock(rewardsAccount);

		List<Future> futures = new ArrayList<>();

		// Creating 5 threads
		for(int i = 0; i < 5; ++i){
			futures.add(executorService.submit(customerAccount));
		}
		for(Future future: futures){
			if (future.get() != null){
				throw new Exception("One of the tasks didn't finished correctly");
			}
		}

		executorService.shutdown();

		List<Integer> startBalances = customerAccount.logBalances.startBalances;
		assertEquals(startBalances.size(), 50);

		List<Integer> endBalances = customerAccount.logBalances.endBalances;

		assertTrue("600 is missing?! Something is wrong. Did you synchronized the method?", endBalances.contains(600));

		assertEquals(endBalances.size(), 50);

	}

	private CustomerAccount getCustomerAccountWithSynchronizedBlock(RewardsAccount rewardsAccount){
		return new CustomerAccount(rewardsAccount){
			@Override
			public void run(){
				for(int i = 0; i < 10; ++i)
				{
					synchronized(rewardsAccount)
					{
						logStart(i);
						rewardsAccount.addReward(10);
						logEnd(i);
					}
				}
			}

			private void logStart(int index){
				logBalances.startMessages.add("Start: ".concat(Thread.currentThread().getName()).concat(" with ").concat(String.valueOf(rewardsAccount.getBalance())));
				logBalances.startBalances.add(index, rewardsAccount.getBalance());
			}

			private void logEnd(int index){
				logBalances.endBalances.add(index, rewardsAccount.getBalance());
				logBalances.endMessages.add("End: ".concat(Thread.currentThread().getName()).concat(" with ").concat(String.valueOf(rewardsAccount.getBalance())));
			}
		};
	}
}
