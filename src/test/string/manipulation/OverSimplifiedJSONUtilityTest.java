package string.manipulation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.junit.Test;

public class OverSimplifiedJSONUtilityTest extends TestCase
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

	@Test
	public void testJSONUtility(){

		String compare = "{'Name': 'Alejandro', 'Words' : ['One', 'Two', 'Three']}";

		OverSimplifiedJSONUtility JSONUtility = new OverSimplifiedJSONUtility();

		String keyValue = JSONUtility.buildKeyValue("Name", "Alejandro");

		String[] words = {"One", "Two", "Three"};
		String wordsArray = JSONUtility.buildArray(words);

		String keyArrayValue = JSONUtility.buildKeyValue("Words", wordsArray);

		List<String> sections = new ArrayList();
		sections.add(keyValue);
		sections.add(keyArrayValue);

		String result = JSONUtility.buildObject(sections.toArray(new String[sections.size()]));

		assertEquals(result, compare);
	}

}
