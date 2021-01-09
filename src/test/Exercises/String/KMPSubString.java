package Exercises.String;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KMPSubString {

    @Before
    public void setUp()
            throws Exception {
    }

    @After
    public void tearDown()
            throws Exception {
    }

    @Test
    public void indexOfUsingKMPTest() {
        String text = "ababcabcabababd";
        String pattern = "ababd";
        assertEquals(KMPSubString.indexOf(text, pattern), 11);
    }

    static protected int indexOf(String text, String pattern){
        if (text == null || text.length() == 0) return -1;
        if (pattern == null || pattern.length() == 0) return -1;
        if (text.length() < pattern.length()) return -1;
        int[] failTable = new int[pattern.length() + 1];
        fillFailTableWithPattern(failTable, pattern);
        return indexOfMatch(failTable, text, pattern);
    }

    static private void fillFailTableWithPattern(int[] failTable, String pattern){
        failTable[0] = -1;
        failTable[1] = 0;
        int left = 0, right = 2;
        while(right < failTable.length){
            if (pattern.charAt(right - 1) == pattern.charAt(left)){
                ++left;
                failTable[right] = left;
                ++right;
            } else if (left > 0){
                left = failTable[left];
            } else {
                failTable[right] = left;
                ++right;
            }
        }
    }

    static private int indexOfMatch(int[] failTable, String text, String pattern){
        int textIndex = 0, patternIndex = 0;
        while(textIndex < text.length()){
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)){
                ++patternIndex;
                if (patternIndex == pattern.length()){
                    return textIndex - (pattern.length() + 1);
                }
                ++textIndex;
            } else if (patternIndex > 0){
                patternIndex = failTable[patternIndex];
            } else {
                ++textIndex;
            }
        }
        return -1;
    }

}