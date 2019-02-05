package string.manipulation;


import junit.framework.TestCase;
import org.junit.Test;

public class UsingStringJoinerTest extends TestCase
{

	UsingStringJoiner usingStringJoiner;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		usingStringJoiner = new UsingStringJoiner();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetEmptyString(){
		String result = usingStringJoiner.getEmptyString();
		assertEquals(result, "");
	}

	@Test
	public void testGetSingleWord(){
		String compare = "word";
		String result = usingStringJoiner.getSingleWord(compare);
		assertEquals(result, compare);
	}

	@Test
	public void testGetWordSeparatedByCommas(){
		String[] words = {"One", "Two", "Three"};
		String result = usingStringJoiner.getWordSeparatedByCommas(words);
		String compare = String.join(", ", words);
		assertEquals(result, compare);
	}

	@Test
	public void testJoinThreeWordsSeparatedBySpaces(){
		String[] words = {"One", "Two", "Three"};
		String result = usingStringJoiner.joinThreeWordsSeparatedBySpaces(words[0], words[1], words[2]);
		String compare = String.join(" ", words);
		assertEquals(result, compare);
	}

	@Test
	public void testGetEmptyStringWithBrackets(){
		String result = usingStringJoiner.getEmptyStringWithBrackets();
		assertEquals(result, "{}");
	}

	@Test
	public void testGetSingleWordInsideBrackets(){
		String result = usingStringJoiner.getSingleWordInsideBrackets("One");
		assertEquals(result, "{One}");
	}

	@Test
	public void testGetWordsInsideBracketsSeparatedByCommas(){
		String[] words = {"One", "Two", "Three"};
		String result = usingStringJoiner.getWordsInsideBracketsSeparatedByCommas(words);

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.join(", ", words));
		sb.append("}");
		String compare = sb.toString();

		assertEquals(result, compare);
	}

	@Test
	public void testGetEachWordInsideABracketSeparatedByCommas(){
		String[] words = {"One", "Two", "Three"};
		String result = usingStringJoiner.getEachWordInsideABracketSeparatedByCommas(words);

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(String.join("}, {", words));
		sb.append("}");
		String compare = sb.toString();

		assertEquals(result, compare);
	}

	@Test
	public void testGetDefaultEmptyString(){
		String defaultValueToCompare = UsingStringJoiner.EMPTY_VALUE;
		String result = usingStringJoiner.getDefaultEmptyString(defaultValueToCompare);
		assertEquals(result, defaultValueToCompare);
	}

	@Test
	public void testGetWord(){
		String compare = "One";
		String result = usingStringJoiner.getWord(compare);
		assertEquals(result, compare);
	}

	@Test
	public void testGetDefaultEmptyStringWithoutBrackets(){
		String defaultValueToCompare = UsingStringJoiner.EMPTY_VALUE;
		String result = usingStringJoiner.getDefaultEmptyStringWithoutBrackets(defaultValueToCompare);
		assertEquals(result, defaultValueToCompare);
	}

	@Test
	public void testGetWordWithBrackets(){
		String compare = "One";
		String result = usingStringJoiner.getWordWithBrackets(compare);
		assertEquals(result, "{One}");
	}

}
