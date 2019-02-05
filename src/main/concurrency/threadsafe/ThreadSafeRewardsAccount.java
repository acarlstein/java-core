package concurrency.threadsafe;

import concurrency.nothreadsafe.RewardsAccount;

public class ThreadSafeRewardsAccount extends RewardsAccount
{
	public ThreadSafeRewardsAccount(int balance)
	{
		super(balance);
	}

	@Override
	public synchronized  int getBalance()
	{
		return super.getBalance();
	}

	@Override
	public synchronized void addReward(int amount)
	{
		super.addReward(amount);
	}

	public synchronized  void spend(int amount){
		balance -= amount;
	}
}
