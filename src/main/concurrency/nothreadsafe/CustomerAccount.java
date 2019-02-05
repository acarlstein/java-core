package concurrency.nothreadsafe;

public class CustomerAccount implements Runnable
{
	private RewardsAccount rewardsAccount;

	public LogBalances logBalances;

	public CustomerAccount(RewardsAccount rewardsAccount){
		this.rewardsAccount = rewardsAccount;
		this.logBalances = new LogBalances();
	}

	@Override
	public void run(){}
}
