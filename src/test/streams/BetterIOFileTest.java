package streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

public class BetterIOFileTest extends TestCase
{
	BetterIOFile betterIOFile;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		emptyWritingFile();
		betterIOFile = new BetterIOFile();
	}

	private void emptyWritingFile()
	throws IOException
	{
		try(PrintWriter writer = new PrintWriter(BetterIOFile.WRITE_RESOURCE_FILENAME)){
			writer.print("");
		}
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testFilesNewBufferedReader()
	throws IOException{
		String result = betterIOFile.filesNewBufferedReader();
		String compare = new String(Files.readAllBytes(Paths.get(BetterIOFile.READ_RESOURCE_FILENAME)));
		assertEquals(result, compare);
	}

	@Test
	public void testFilesReadAllLines()
	throws IOException{
		String result = betterIOFile.filesReadAllLines();
		String compare = new String(Files.readAllBytes(Paths.get(BetterIOFile.READ_RESOURCE_FILENAME)));
		assertEquals(result, compare);
	}



}
