package Exercises.Heaps;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class LargestTripleProductHeapTest {

    final static int MAX_ARRAY = 10000;

    @Test
    public void noEnoughElements(){
        int[] source = {1, 2};
        int expected = -1;
        assertEquals(expected, getMaximumProduct(source));
        assertEquals(expected, getMaximumProduct(null));
    }

    @Test
    public void tooMuchElements(){
        int[] source = new int[MAX_ARRAY + 1];
        int expected = -1;
        assertEquals(expected, getMaximumProduct(source));
    }

    @Test
    public void firstCaseTest(){
        int[] source = {1, 2, 3};
        int expected = 6;
        assertEquals(expected, getMaximumProduct(source));
    }

    @Test
    public void secondCaseTest(){
        int[] source = {1, 2, 3, 4};
        int expected = 24;
        assertEquals(expected, getMaximumProduct(source));
    }

    @Test
    public void thirdCaseTest(){
        int[] source = {-5, -5, -3, -2, -1};
        int expected = -6;
        assertEquals(expected, getMaximumProduct(source));
    }

    @Test
    public void fourthCaseTest(){
        int[] source = {-5, -5, 1, 2, 3, 3, 4};
        int expected = 100;
        assertEquals(expected, getMaximumProduct(source));
    }

    @Test
    public void fifthCaseTest(){
        int[] source = {1000, 1000, 1000};
        int expected = 1000 * 1000 * 1000;
        assertEquals(expected, getMaximumProduct(source));
    }

    public int getMaximumProduct(int[] source){
        if (source == null
                || source.length < 3
                || source.length > MAX_ARRAY) return -1;

        Arrays.sort(source);
        int length = source.length;
        int max_candidate = source[length - 1] * source[length - 2] * source[length - 3];
        int min_candidate = 0;
        if (source[0] < 0 && source[1] < 0){
            min_candidate = source[0] * source[1] * source[length - 1];
        }

        return Math.max(max_candidate, min_candidate);
    }

}
