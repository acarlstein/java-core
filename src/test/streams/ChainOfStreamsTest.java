package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

public class ChainOfStreamsTest extends TestCase
{
	ChainOfStreams chainOfStreams;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		chainOfStreams = new ChainOfStreams();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testInputStreamChain(){
		try
		{
			String result = chainOfStreams.inputStreamChain();
			String compare = new String(Files.readAllBytes(Paths.get(ChainOfStreams.READ_RESOURCE_FILENAME)));
			assertEquals(result, compare);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
