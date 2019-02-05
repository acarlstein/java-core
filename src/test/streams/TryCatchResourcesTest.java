package streams;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TryCatchResourcesTest extends TestCase
{
	TryCatchResources tryCatchResources;
	String compare;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		tryCatchResources = new TryCatchResources();
		compare = new String(Files.readAllBytes(Paths.get(TryCatchResources.READ_RESOURCE_FILENAME)));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testTryAndCatchEverything(){
		String result = tryCatchResources.tryAndCatchEverything();
		assertEquals(result, compare);
	}

	@Test(expected = FileNotFoundException.class)
	public void testTryAndCatchEverythingWithFileReaderFileNotFoundException()
	throws IOException
	{
		String exceptionMessage = "FileNotFoundException triggered";
		TryCatchResources tryCatchResources = spy(new TryCatchResources());
		when(tryCatchResources.getReader()).thenThrow(new FileNotFoundException(exceptionMessage));
		String result = tryCatchResources.tryAndCatchEverything();
		assertEquals(result, exceptionMessage);
	}

	@Test(expected = IOException.class)
	public void testTryAndCatchEverythingWithFileReaderIOException()
	throws IOException
	{
		String exceptionMessage = "IOException triggered";
		FileReader fileReaderMock = mock(FileReader.class);
		when(fileReaderMock.read()).thenThrow(new IOException(exceptionMessage));

		TryCatchResources example = spy(new TryCatchResources());
		when(example.getReader()).thenReturn(fileReaderMock);

		String result = example.tryAndCatchEverything();
		assertEquals(result, exceptionMessage);
	}

	@Test(expected = IOException.class)
	public void testTryAndCatchEverythingWithReaderFileCloseIOException()
	throws IOException
	{
		String exceptionMessage = "IOException triggered";
		FileReader fileReaderSpy = spy(new FileReader(TryCatchResources.READ_RESOURCE_FILENAME));
		doThrow(new IOException(exceptionMessage)).when(fileReaderSpy).close();

		TryCatchResources example = spy(new TryCatchResources());
		when(example.getReader()).thenReturn(fileReaderSpy);

		String result = example.tryAndCatchEverything();
		assertEquals(result, exceptionMessage);
	}

	@Test
	public void testTryWithResources(){
		String result = tryCatchResources.tryWithResources();
		assertEquals(result, compare);
	}

	@Test(expected = FileNotFoundException.class)
	public void testTryWithResourcesFileReaderFileNotFoundException()
	throws IOException
	{
		String exceptionMessage = "FileNotFoundException triggered";
		TryCatchResources tryCatchResources = spy(new TryCatchResources());
		when(tryCatchResources.getReader()).thenThrow(new FileNotFoundException(exceptionMessage));
		String result = tryCatchResources.tryWithResources();
		assertEquals(result, exceptionMessage);
	}

	@Test(expected = IOException.class)
	public void testTryWithResourcesWithFileReaderIOException()
	throws IOException
	{
		String exceptionMessage = "IOException triggered";
		FileReader fileReaderMock = mock(FileReader.class);
		when(fileReaderMock.read()).thenThrow(new IOException(exceptionMessage));

		TryCatchResources example = spy(new TryCatchResources());
		when(example.getReader()).thenReturn(fileReaderMock);

		String result = example.tryWithResources();
		assertEquals(result, exceptionMessage);
	}



}
