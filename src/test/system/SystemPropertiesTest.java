package system;

import java.util.Map;
import java.util.regex.Pattern;

import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class SystemPropertiesTest extends TestCase
{

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();

	}

	/*
		"file.separator" 	Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
		"java.class.path" 	Path used to find directories and JAR archives containing class files. Elements of the class path are separated by a platform-specific character specified in the path.separator property.
		"java.home" 	Installation directory for Java Runtime Environment (JRE)
		"java.vendor" 	JRE vendor name
		"java.vendor.url" 	JRE vendor URL
		"java.version" 	JRE version number
		"line.separator" 	Sequence used by operating system to separate lines in text files
		"os.arch" 	Operating system architecture
		"os.name" 	Operating system name
		"os.version" 	Operating system version
		"path.separator" 	Path separator character used in java.class.path
		"user.dir" 	User working directory
		"user.home" 	User home directory
		"user.name" 	User account name
		*/

	@Test
	public void testSystemProperties(){

		// Is this regular expression correct? What is the problem with it?
		assertTrue(Pattern.matches("\\\\|\\/", System.getProperty("file.separator")));

		assertEquals("Java(TM) SE Runtime Environment", System.getProperty("java.runtime.name"));

		assertEquals("Oracle Corporation", System.getProperty("java.vm.vendor"));

		assertTrue(System.getProperty("java.vm.name").contains("Java"));

		/**
		 * Some escape sequences based on different operatives systems:
		 * - Multics, Unix and Unix-like (Linux, etc): \n
		 * - Commodore, Apple II, TRS-80, Classic Mac OS, OS-9: \r
		 * - Microsoft, CP/M, Palm OS, etc: \r\n
		 * - RISC OS, Accorn BBC: \n\r
		 */
		assertTrue(Pattern.matches("\\n|\\r|\\r\\n|\\n\\r", System.getProperty("line.separator")));
	}

	@Test
	public void testSystemEnvironments(){
		Map<String, String> environmentVariables = System.getenv();

		assertTrue(environmentVariables.size() > 0);

		if (environmentVariables.containsKey("JAVA_VENDOR")){
			assertThat(System.getenv("JAVA_VENDOR"), Matchers.anyOf(Matchers.is("Sun"), Matchers.is("Oracle")));
		}
	}

}
