package Exercises.Arrays;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class IntersectionBetweenArraysTest {

    int[][] sortedArrays = {
            {3, 7, 10, 12, 14, 18},
            {4, 7, 8, 11, 14, 19},
            {5, 6, 7, 10, 12, 14}
    };

    @Test
    public void findIntersectionBetweenSortedArraysTest(){
        int[] result = findIntersectionBetweenArrays(sortedArrays);
        int[] expected = {7, 14};
        assertArrayEquals(result, expected);
    }

    @Test
    public void findIntersectionBetweenUnSortedArraysTest(){
        int[][] arr = {
                {7, 3, 12, 10, 18, 14},
                {8, 4, 7, 11, 19, 14},
                {14, 7, 10, 6, 12, 5}
        };
        int[] result = findIntersectionBetweenArrays(arr);
        Arrays.sort(result);
        int[] expected = {7, 14};
        assertArrayEquals(result, expected);
    }

    /**
     * Find the intersection between arrays.
     * For this algorithm, we assume that each array can have different lengths
     * , we can use any data structure and the elements of each array could be
     * unsorted.
     * We don't wish to sort the elements in the input arrays.
     * We don't care to sort the elements in the output array.
     * @param arrays
     * @return array of intersections.
     */
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

    @Test
    public void findIntersectionBetweenThreeSortedArraysTest(){
        int[] result = findIntersectionBetweenThreeSortedArrays(sortedArrays);
        int[] expected = {7, 14};
        assertArrayEquals(result, expected);
    }

    /**
     * Find the intersection betwee three arrays.
     * We assume that the three arrays are going to be sorted.
     * This allow us to follow this logic:
     * Lets scan each array, one by one, only increasing their respective indexes
     * when the value of one array is lower than the next.
     * So, if the value of the first array is less than the value of the second,
     * we increase the index of the first array. If the value of the second array,
     * is less than the value of the third, then we increase the index of the second array,
     * else we increase the index of the third array since that value is lower than the
     * first array and/or the second.
     * @param sortedArrays
     * @return
     */
    private int[] findIntersectionBetweenThreeSortedArrays(int[][] sortedArrays) {
       if (sortedArrays == null || sortedArrays.length != 3) return new int[0];
       int[] first = sortedArrays[0];
       int[] second = sortedArrays[1];
       int[] third = sortedArrays[2];
       List<Integer> matches = new ArrayList<Integer>();
       for (int i = 0, j = 0, l = 0
            ; i < first.length && j < second.length && l < third.length;){
           if (first[i] == second[j]
               && second[j] == third[l]){
               matches.add(first[i]);
               ++i; ++j; ++l;
           } else if (first[i] < second[j]){
               ++i;
           } else if (second[j] < third[l]) {
               ++j;
           } else {
               ++l;
           }
       }

       return matches.stream().mapToInt(i -> i).toArray();
    }


}
