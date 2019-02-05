package streams;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

public class WriteWithStreamsTest extends TestCase
{
	WriteWithStreams writeWithStreams;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		emptyWritingFile();
		writeWithStreams = new WriteWithStreams();
	}

	private void emptyWritingFile(){
		try(PrintWriter writer = new PrintWriter(WriteWithStreams.WRITE_RESOURCE_FILENAME)){
			writer.print("");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testWritingWithBytes(){

		try
		{
			String sampleToWrite = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			byte[] bytes = sampleToWrite.getBytes();
			writeWithStreams.writingWithBytes(bytes);
			String result = new String(Files.readAllBytes(Paths.get(WriteWithStreams.WRITE_RESOURCE_FILENAME)));
			assertEquals(sampleToWrite, result);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testWritingWithCharacters(){

		try
		{
			String sampleToWrite = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			char[] chars = sampleToWrite.toCharArray();
			writeWithStreams.writingWithCharacters(chars);
			String result = new String(Files.readAllBytes(Paths.get(WriteWithStreams.WRITE_RESOURCE_FILENAME)));
			assertEquals(sampleToWrite, result);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


}
