package string.manipulation;

import java.util.StringJoiner;

/**
 * The StringJoiner is a good alternative to the StringBuffer
 */
public class UsingStringJoiner
{

	public static final String EMPTY_VALUE = "EMPTY VALUE";

	/**
	 *
	 * @return empty string
	 */
	public String getEmptyString(){
		String delimiter = ", ";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param word
	 * @return word
	 */
	public String getSingleWord(String word){
		String delimiter = ", ";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		stringJoiner.add(word);
		return stringJoiner.toString();
	}

	public String getWordSeparatedByCommas(String[] words){
		String delimiter = ", ";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		for(String word : words){
			stringJoiner.add(word);
		}
		return stringJoiner.toString();
	}

	public String joinThreeWordsSeparatedBySpaces(String firstWord, String secondWord, String thirdWord){
		String delimiter = " ";
		StringJoiner sj = new StringJoiner(delimiter);

		// This follows the chain pattern.
		// The method add returns a reference to the string joiner itself.
		sj.add(firstWord).add(secondWord).add(thirdWord);
		return sj.toString();
	}

	/**
	 *
	 * @return {}
	 */
	public String getEmptyStringWithBrackets(){
		String delimiter = ", ";
		String prefix = "{";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param word
	 * @return {word}
	 */
	public String getSingleWordInsideBrackets(String word){
		String delimiter = ", ";
		String prefix = "{";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
		stringJoiner.add(word);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param words
	 * @return {word1, word2, ..., wordN}
	 */
	public String getWordsInsideBracketsSeparatedByCommas(String[] words){
		String delimiter = ", ";
		String prefix = "{";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
		for(String word : words){
			stringJoiner.add(word);
		}
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param words
	 * @return {word1}, {word2}, ..., {wordN}
	 */
	public String getEachWordInsideABracketSeparatedByCommas(String[] words){
		String prefix = "{";
		String delimiter = "}, {";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
		for(String word: words){
			stringJoiner.add(word);
		}
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param defaultEmptyValue
	 * @return defaultEmptyValue
	 */
	public String getDefaultEmptyString(String defaultEmptyValue){
		String delimiter = ", ";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		stringJoiner.setEmptyValue(defaultEmptyValue);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param word
	 * @return word
	 */
	public String getWord(String word){
		String delimiter = ", ";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		stringJoiner.setEmptyValue(EMPTY_VALUE);
		stringJoiner.add(word);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param defaultEmptyValue
	 * @return defaultEmptyValue
	 */
	public String getDefaultEmptyStringWithoutBrackets(String defaultEmptyValue){
		String delimiter = ", ";
		String prefix = "{";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter);
		stringJoiner.setEmptyValue(defaultEmptyValue);
		return stringJoiner.toString();
	}

	/**
	 *
	 * @param word
	 * @return {word}
	 */
	public String getWordWithBrackets(String word){
		String delimiter = ", ";
		String prefix = "{";
		String suffix = "}";
		StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, suffix);
		stringJoiner.setEmptyValue(EMPTY_VALUE);
		stringJoiner.add(word);
		return stringJoiner.toString();
	}


}
