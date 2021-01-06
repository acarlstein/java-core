package Exercises.Palidrome;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;

public class PalidromeStackTest {

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
        Assert.assertFalse(PalidromeStackTest.isValid(null));
        Assert.assertFalse(PalidromeStackTest.isValid(""));
    }

    @Test
    public void isTextValidPalidromeTest() {
        String text = "A man, a plan, a canal: Panama";
        Assert.assertTrue(PalidromeStackTest.isValid(text));
    }

    @Test
    public void isTextInvalidValidPalidromeTest() {
        String text = "race a car";
        Assert.assertFalse(PalidromeStackTest.isValid(text));
    }

    static public boolean isValid(String text) {
        if (text == null || text.length() == 0) return false;
        Stack stack = new Stack();
        for (int i = 0; i < text.length(); ++i){
            if (Character.isLetterOrDigit(text.charAt(i))){
                stack.push(Character.toLowerCase(text.charAt(i)));
            }
        }

        for (int j = text.length() - 1; j > -1; --j){
            if (Character.isLetterOrDigit(text.charAt(j))){
                char c = (char) stack.pop();
                if (c != text.charAt(j)){
                    break;
                }
            }
        }

        return stack.size() > 0;
    }
}

