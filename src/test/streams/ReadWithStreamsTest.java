package streams;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

public class ReadWithStreamsTest extends TestCase
{
	ReadWithStreams readWithStreams;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		readWithStreams = new ReadWithStreams();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testReadingOneByteAtATime(){
		try
		{
			byte[] bytes = readWithStreams.readingOneByteAtATime();
			assertNotNull(bytes);
			String result = new String(bytes, "UTF-8");
			String compare = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			assertEquals(result, compare);
		}
		catch (IOException e)
		{
			assertTrue("Results doesn't match", false);
			e.printStackTrace();
		}
	}

	@Test
	public void testReadingOneCharacterAtATime(){
		try
		{
			char[] chars = readWithStreams.readingOneCharacterAtATime();
			assertNotNull(chars);
			String result = new String(chars);
			String compare = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			assertEquals(result, compare);
		}
		catch (IOException e)
		{
			assertTrue("Results doesn't match", false);
			e.printStackTrace();
		}
	}

	@Test
	public void testReadingArrayOfBytes(){
		try
		{
			int bufferSize = 10;
			byte[] bytes = readWithStreams.readingArrayOfBytes(bufferSize);
			assertNotNull(bytes);
			String result = new String(bytes, "UTF-8");
			String compare = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			assertEquals(result, compare);
		}
		catch (IOException e)
		{
			assertTrue("Results doesn't match", false);
			e.printStackTrace();
		}
	}

	@Test
	public void testReadingArrayOfCharacters(){
		try
		{
			int bufferSize = 10;
			char[] chars = readWithStreams.readingArrayOfCharacters(bufferSize);
			assertNotNull(chars);
			String result = new String(chars);
			String compare = new String(Files.readAllBytes(Paths.get(ReadWithStreams.READ_RESOURCE_FILENAME)));
			assertEquals(result, compare);
		}
		catch (IOException e)
		{
			assertTrue("Results doesn't match", false);
			e.printStackTrace();
		}
	}


}
