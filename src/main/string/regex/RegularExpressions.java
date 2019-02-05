package string.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions
{

	public List<String> getListOfAlphaNumericAndUnderscores(String phrase){
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(phrase);
		List<String> list = new ArrayList();
		while(matcher.find()){
			list.add(matcher.group());
		}
		return list;
	}


}
