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
                {2, 6, 9, 11, 13, 17},
                {3, 6, 7, 10, 13, 18},
                {4, 5, 6, 9, 11, 13}
        };
        int[] result = findIntersectionBetweenArrays(arr);
        int[] expected = {6, 13};
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
