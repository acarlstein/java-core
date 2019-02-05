package string.manipulation;

import java.util.StringJoiner;

/**
 * We can use StringJoiner to build simple JSON utility
 */
public class OverSimplifiedJSONUtility
{
	public String buildKeyValue(String key, String value){
		if (value.contains("[") && value.contains("]")){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("'").append(key).append("' : ").append(value);
			return stringBuffer.toString();
		}
		return new StringJoiner("': '", "'", "'").add(key).add(value).toString();
	}

	public String buildArray(String[] words){
		StringJoiner sj = new StringJoiner("', '", "['", "']");
		for(String word : words){
			sj.add(word);
		}
		return sj.toString();
	}

	public String buildObject(String[] section){
		StringJoiner sj = new StringJoiner(", ", "{", "}");
		for(String word : section){
			sj.add(word);
		}
		return sj.toString();
	}

}
