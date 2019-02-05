package streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

public class BufferedStreamsTest extends TestCase
{
	BufferedStreams bufferedStreams;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		emptyWritingFile();
		bufferedStreams = new BufferedStreams();
	}

	private void emptyWritingFile()
	throws IOException{
		try(PrintWriter writer = new PrintWriter(BufferedStreams.WRITE_RESOURCE_FILENAME)){
			writer.print("");
		}
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testBufferedReader()
	throws IOException {
		String result = bufferedStreams.bufferedReader();
		String compare = new String(Files.readAllBytes(Paths.get(BufferedStreams.READ_RESOURCE_FILENAME)));
		assertEquals(result, compare);
	}

	@Test
	public void testBufferedReaderReadLine()
	throws IOException {
		String result = bufferedStreams.bufferedReaderReadLines();
		String compare = new String(Files.readAllBytes(Paths.get(BufferedStreams.READ_RESOURCE_FILENAME)));

		// The reason they are not the same is because the bufferedReader.readLine() method
		// doesn't include the new line character(s)
		assertNotSame(result, compare);

		// Just as an example, I will add the new line manually so they match
		String newLine = System.getProperty("line.separator");
		result = result.replace(".", ".".concat(newLine));
		assertEquals(result, compare);
	}

	@Test
	public void testBufferedWriter()
	throws IOException{
		String sampleToWrite = new String(Files.readAllBytes(Paths.get(BufferedStreams.READ_RESOURCE_FILENAME)));
		String[] stringOfArrays = sampleToWrite.split("\n|\r\n");
		bufferedStreams.bufferedWriter(stringOfArrays);
		String result = new String(Files.readAllBytes(Paths.get(BufferedStreams.WRITE_RESOURCE_FILENAME)));

		// They don't match because our code adds a new line for each element in the stringArray that we write.
		// Therefore, the content of the write.txt file doesn't match those in the read.txt file.
		assertNotSame(sampleToWrite, result);

		// We remove the last new line added since the read.txt file doesn't have it.
		result = result.substring(0, result.lastIndexOf(System.getProperty("line.separator")));
		assertEquals(sampleToWrite, result);
	}

}
