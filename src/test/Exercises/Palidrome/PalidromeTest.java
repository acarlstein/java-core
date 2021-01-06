package Exercises.Palidrome;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalidromeTest {

    @Before
    public void setUp()
            throws Exception {
    }

    @After
    public void tearDown()
            throws Exception {
    }

    @Test
    public void isEmptyStringValidTest() {
        Assert.assertFalse(PalidromeTest.isValid(null));
        Assert.assertFalse(PalidromeTest.isValid(""));
    }

    @Test
    public void isTextValidPalidromeTest() {
        String text = "A man, a plan, a canal: Panama";
        Assert.assertTrue(PalidromeTest.isValid(text));
    }

    @Test
    public void isTextInvalidValidPalidromeTest() {
        String text = "race a car";
        Assert.assertFalse(PalidromeTest.isValid(text));
    }

    static public boolean isValid(String text){
        if (text == null || text.length() == 0) return false;
        int left = 0, right = text.length() - 1;
        text = text.toLowerCase();
        while (left < right){

            for (; left < text.length(); ++left){
                if (Character.isLetterOrDigit(text.charAt(left))){
                    break;
                }
            }

            for(; right > -1; --right){
                if (Character.isLetterOrDigit(text.charAt(right))){
                    break;
                }
            }

            if (text.charAt(left) != text.charAt(right)){
                return false;
            }

            ++left;
            --right;
        }
        return true;
    }

}

