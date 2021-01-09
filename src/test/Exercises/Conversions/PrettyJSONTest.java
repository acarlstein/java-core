package Exercises.Conversions;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO
public class PrettyJSONTest {

    String json = "{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";

    @Test
    public void prettyJSONEmpty(){
        String json = "";
        String[] compare = new String[0];
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    @Test
    public void prettyJSONBracketsOnly(){
        String json = "{}";
        String[] compare = { "{", "}"};
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    @Test
    public void prettyJSONOneProperty(){
        String json = "{A:\"1\"}";
        String[] compare = { "{", "\tA:\"1\"", "}"};
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    @Test
    public void prettyJSONTwoProperty(){
        String json = "{A:\"1\",B:\"2\"}";
        String[] compare = { "{", "\tA:\"1\",", "\tB:\"2\"", "}"};
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    @Test
    public void prettyJSONOneEmptyObject(){
        String json = "{A:\"1\",B:{}}";
        String[] compare = { "{", "\tA:\"1\",", "\tB:", "{", "}", "}"};
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    @Test
    public void prettyJSONOneObject(){
        String json = "{A:\"1\",B:{C:\"3\"}}";
        String[] compare = { "{", "\tA:\"1\",", "\tB:", "\t{", "\t\tC:\"3\"", "\t}", "}"};
        String[] result = convertToPrettyJSON(json);
        Assert.assertArrayEquals(result, compare);
    }

    private String[] convertToPrettyJSON(String json){
        List<String> list = new ArrayList<String>();
        StringBuilder spaces = new StringBuilder();
        StringBuilder sb;
        for(int i = 0; i < json.length(); ++i){
            sb = new StringBuilder();
            Character c = json.charAt(i);
            switch(c){
                case '{':
                    spaces.append(' ');
                    list.add(sb.append(c).append('\n').append(spaces).toString());
                    break;
                case '}':
                    spaces.deleteCharAt(spaces.length() - 1);
                    //list.add(sb.append('\n'))
                    break;
            }
        }


        return list.toArray(new String[list.size()]);
    }

    private String buildTabs(int numberOfTabs){
        return String.join("", Collections.nCopies(numberOfTabs, "\t"));
    }

}