package cmdline;

public class SimpleCmdLine
{
	public static void main(String[] args){
		if (args.length < 1){
			System.out.println("No Arguments Received");
			return;
		}
		System.out.println("Total Arguments: "+ args.length);
	}
}
