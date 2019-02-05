package concurrency.nothreadsafe;

public class RewardsAccount
{
	protected int balance;

	public RewardsAccount(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	/**
	 * In concurrency, this un-synchronized method will give troubles in concurrency.
	 * The reason is that there are three steps done behind the scene:
	 *
	 *  1- From the memory, we read the current value
	 *  2- We perform an addition in the register holding the value
	 *  3- We write back to the memory with the new value
	 *
	 *  Therefore, without synchronization, these steps can be performed in different order,
	 *  as we will show later on and explain.
	 */
	public void addReward(int amount){
		balance += amount;
	}
}
