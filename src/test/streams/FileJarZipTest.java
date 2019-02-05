package streams;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FileJarZipTest extends TestCase
{

	FileJarZip fileJarZip;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		fileJarZip = new FileJarZip();
		deleteZipIfExist();
	}

	private void deleteZipIfExist()
	throws IOException {
		File file = new File(FileJarZip.WRITE_RESOURCE_ZIP_FILENAME);
		Files.deleteIfExists(file.toPath());
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testFileJarZipWriteInsideZip()
	throws IOException, URISyntaxException
	{
		FileJarZip fileJarZipSpy = spy(fileJarZip);
		fileJarZipSpy.writeInsideZip();

		verify(fileJarZipSpy, times(1)).createZip(any());
		verify(fileJarZipSpy, times(1)).read(any());

		assertTrue(doFileExistsInZipFile(FileJarZip.READ_RESOURCE_FILENAME));
	}

	private boolean doFileExistsInZipFile(String filename)
	throws IOException {
		Path path = Paths.get(FileJarZip.WRITE_RESOURCE_ZIP_FILENAME);
		InputStream inputStream = Files.newInputStream(path, StandardOpenOption.READ);
		ZipInputStream zip = new ZipInputStream(inputStream);
		ZipEntry entry = null;

		while((entry = zip.getNextEntry()) != null){
			String compareWithFilename = "./".concat(entry.getName());
			if (filename.contains(compareWithFilename)){
				return true;
			}
		}
		return false;
	}

	@Test
	public void testFileJarZipCopyInsideZipWithNewName()
	throws IOException, URISyntaxException {
		FileJarZip fileJarZipSpy = spy(fileJarZip);
		fileJarZipSpy.copyInsideZipWithNewName("Banana.txt");
		assertTrue(doFileExistsInZipFile("Banana.txt"));
	}
}
