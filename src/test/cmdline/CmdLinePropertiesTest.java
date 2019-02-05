package cmdline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import junit.framework.TestCase;
import org.junit.Test;


public class CmdLinePropertiesTest extends TestCase
{

	public static final String PROPERTIES_FILE = "./resources/config.properties";
	public static final String PROPERTIES_FILE_XML = "./resources/config.xml";

	Properties props;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
 		props = new Properties();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();

	}


	@Test
	public void testCommonPropertiesMethods(){

		props.setProperty("defaultUser", "dveron");
		assertEquals(props.getProperty("defaultUser"), "dveron");

		assertEquals(props.getProperty("unknownKey"), null);

		assertEquals(props.getProperty("position", "defaultValue"), "defaultValue");
	}

	@Test
	public void testStoringProperties()
	throws IOException
	{
		props.setProperty("username", "jmunoz");
		props.setProperty("fullname", "Juan Munoz");

		Path path = Paths.get(PROPERTIES_FILE);

		try(Writer writer = Files.newBufferedWriter(path)){
			props.store(writer, "Comment to display on first line of property file");
		}

		String fileContent = new String(Files.readAllBytes(path), "UTF-8");
		assertTrue(fileContent.contains("username=jmunoz"));
		assertTrue(fileContent.contains("fullname=Juan Munoz"));
	}

	@Test
	public void testLoadProperties()
	throws Exception
	{

		// We ensure we have properties to load
		// This is bad unit testing. Each unit test should be independent of each other.
		// I am just being lazy here.
		testStoringProperties();

		Path path = Paths.get(PROPERTIES_FILE);

		if (Files.exists(path)){
			try (Reader reader = Files.newBufferedReader(path))
			{
				props.load(reader);
			}
		}

		assertEquals(props.get("username"), "jmunoz");
		assertEquals(props.get("fullname"), "Juan Munoz");

		// Default value in case the property is missing
		assertEquals(props.getProperty("password", "ABC123"), "ABC123");
	}

	@Test
	public void testStorePropertiesAsXML()
	throws Exception{
		props.setProperty("username", "dvarela");
		props.setProperty("fullname", "Dario Varela");

		Path path = Paths.get(PROPERTIES_FILE_XML);

		try(OutputStream os = Files.newOutputStream(path)){
			props.storeToXML(os, "Comment to display on first line of property file");
		}

		String fileContent = new String(Files.readAllBytes(path), "UTF-8");
		assertTrue(fileContent.contains("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"));
		assertTrue(fileContent.contains("<entry key=\"fullname\">Dario Varela</entry>"));
		assertTrue(fileContent.contains("<entry key=\"username\">dvarela</entry>"));
	}


	@Test
	public void testLoadPropertiesFromXML()
	throws Exception{

		// We ensure we have properties to load
		// This is bad unit testing. Each unit test should be independent of each other.
		// I am just being lazy here.
		testStorePropertiesAsXML();

		Path path = Paths.get(PROPERTIES_FILE_XML);

		// Here, we should check if the file exists
		try (Reader reader = Files.newBufferedReader(path))
		{
			props.load(reader);
		}

		assertEquals(props.get("username"), "dvarela");
		assertEquals(props.get("fullname"), "Dario Varela");
	}



}
