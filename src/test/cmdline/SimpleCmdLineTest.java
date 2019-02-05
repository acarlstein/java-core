package cmdline;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;


public class SimpleCmdLineTest extends TestCase
{

	PrintStream systemOutput;
	OutputStream os;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();

		// Original Standard Output Stream
		systemOutput = System.out;

		// We will redirect the println in the main into our output stream
		// In this way, we can use the output for our testings.
		os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();

		// Reset Standard Output Stream
		System.setOut(systemOutput);
	}

	@Test
	public void testMainWithoutArguments()
	{
		String[] args = new String[0];
		SimpleCmdLine.main(args);
		String expected = "No Arguments Received" + System.getProperty("line.separator");
		assertEquals(expected, os.toString());
	}

	@Test
	public void testMainWithOneOrMoreArguments()
	{
		String[] args = {"OneArgument", "TwoArguments"};
		SimpleCmdLine.main(args);
		String expected = "Total Arguments: " + args.length + System.getProperty("line.separator");
		assertEquals(expected, os.toString());
	}


}
