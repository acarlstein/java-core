package Exercises.Arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class IntersectionBetweenArraysTest {

    @Test
    public void findIntersectionBetweenArraysTest(){
        int[][] arr = {
                {3, 7, 10, 12, 14, 18},
                {4, 7, 8, 11, 14, 19},
                {5, 6, 7, 10, 12, 14}
        };
        int[] result = findIntersectionBetweenArrays(arr);
        int[] expected = {7, 14};
        assertArrayEquals(result, expected);
    }

    private int[] findIntersectionBetweenArrays(int[][] arrays){
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        int numberOfArrays = arrays.length;
        List<Integer> matches = new ArrayList<Integer>();
        Map<Integer, Integer> countMatches = new HashMap<Integer, Integer>();
        for(int[] arr : arrays){
            for(int value: arr){
                if (countMatches.containsKey(value)){
                    Integer i = countMatches.get(value) + 1;
                    countMatches.put(value, i);
                    if (i == numberOfArrays){
                        matches.add(value);
                    }
                }else{
                    countMatches.put(value, 1);
                }
            }
        }
        return matches.stream().mapToInt(i -> i).toArray();
    }
}
