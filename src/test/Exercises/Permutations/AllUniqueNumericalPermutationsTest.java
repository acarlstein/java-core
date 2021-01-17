package Exercises.Permutations;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class AllUniqueNumericalPermutationsTest {

    @Test
    public void AllUniquePermutationOfOneValue(){
        int[] values = {1};
        int[][] result = {
                {1}
        };
        assertArrayEquals(findUniquePermutations(values), result);
    }

    @Test
    public void AllUniquePermutationOfTwoValue(){
        int[] values = {1, 2};
        int[][] result = {
                {1, 2},
                {2, 1}
        };
        assertArrayEquals(findUniquePermutations(values), result);
    }

    public int[][] findUniquePermutations(int[] values){
        int[] copy = Arrays.copyOf(values, values.length);
        return findUniquePermutationsHelper(values);
    }

    private int[][] findUniquePermutationsHelper(int[] values){
        int[][] result = {{}};
        return result;
    }
}
