package string.regex;

import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;

public class RegularExpressionsTest extends TestCase
{

	RegularExpressions regularExpressions;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		regularExpressions = new RegularExpressions();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetListOfAlphaNumericAndUnderscores(){
		String phrase = "One, 2, Three and Four _%!";
		List<String> list = regularExpressions.getListOfAlphaNumericAndUnderscores(phrase);
		assertTrue(list.contains("One"));
		assertTrue(list.contains("2"));
		assertTrue(list.contains("Three"));
		assertTrue(list.contains("and"));
		assertTrue(list.contains("Four"));
		assertTrue(list.contains("_"));

		// The list doesn't contain anything that is not alphanumeric or underscore.
		assertFalse(list.contains(","));
		assertFalse(list.contains(" "));
		assertFalse(list.contains("%"));
		assertFalse(list.contains("!"));
	}

}
